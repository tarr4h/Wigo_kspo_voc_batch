package com.kspo.voc.batch.job;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kspo.voc.batch.common.constant.Constants;
import com.kspo.voc.batch.common.util.Utilities;
import com.kspo.voc.batch.common.util.VocJobListener;
import com.kspo.voc.batch.dao.EmpBasDao;
import com.kspo.voc.batch.model.EmpBasVo;
import com.kspo.voc.batch.model.UserBasVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SyncEmpJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    @Autowired
    VocJobListener jobListener;

    @Autowired
    EmpBasDao empDao;

    int deletedCount = -1;
    List<EmpBasVo> empList = null;

    @Bean("empJob")
    Job empJob() {
        log.debug("empJob");
        return jobBuilderFactory.get("empJob").listener(jobListener).start(empStep()).build();
    }

    @Bean("empStep")
    Step empStep() {
        log.debug("empStep");
        return stepBuilderFactory.get("empStep").<EmpBasVo, EmpBasVo>chunk(Constants.FETCH_COUNT).reader(empReader()).writer(empWriter()).build();
    }

    @Bean("empReader")
    ItemReader<EmpBasVo> empReader() {
        return new ItemReader<EmpBasVo>() {
            @Override
            public EmpBasVo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                if (empList == null)
                    empList = empDao.selectTmpList(null);
                if (empList.size() == 0)
                    return null;
                return empList.remove(0);
            }
        };
    }

    @Bean("empWriter")
    ItemWriter<EmpBasVo> empWriter() {
        return new ItemWriter<EmpBasVo>() {

            @Override
            public void write(List<? extends EmpBasVo> items) throws Exception {
                if (deletedCount < 0)
                    deletedCount = empDao.deleteNotTmp(null);
                for (int i = 0; i < items.size(); i++) {
                    EmpBasVo emp = items.get(i);
                    String orgId = emp.getOrgId();
                    String orgCnCrt = emp.getOrgCncrt();

                    EmpBasVo old = empDao.selectEmpOrg(emp);
                    if (old == null) {
                        empDao.insert(emp);

                    } else {
                        empDao.update(emp);
                    }

                    UserBasVo user = new UserBasVo();

                    user.setUserId(emp.getEmpId());
                    user.setLoginId(emp.getLoginId());
                    user.setUserNm(emp.getEmpNm());
                    user.setEmailAddr(emp.getEmailAddr());
                    user.setMphonNo(emp.getMphonNo());
                    user.setDelYn("30".equals(emp.getStatusCd()) ? "Y" : "N");

                    if ("N".equals(emp.getStat())) {
                        empDao.insertLogin(user);
                    } else {
                        empDao.updateLogin(user);
                    }
                    if (Utilities.isNotEmpty(orgId) && Utilities.isNotEmpty(orgCnCrt) && !orgId.equals(orgCnCrt)) {
                        emp.setCncrtYn("Y");
                        emp.setOrgId(emp.getOrgCncrt());
                        empDao.insert(emp);
                    }
                }

            }
        };
    }
}
