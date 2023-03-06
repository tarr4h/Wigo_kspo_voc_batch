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
import com.kspo.voc.batch.common.util.VocJobListener;
import com.kspo.voc.batch.dao.OrgBasDao;
import com.kspo.voc.batch.model.OrgBasVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SyncOrganizationJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    @Autowired
    VocJobListener jobListener;

    @Autowired
    OrgBasDao orgDao;

    int deletedCount = -1;
    List<OrgBasVo> orgList = null;

    @Bean("organizationJob")
    Job organizationJob() {
        log.debug("organizationJob");
        return jobBuilderFactory.get("organizationJob").listener(jobListener).start(organizationStep()).build();
    }

    @Bean("organizationStep")
    Step organizationStep() {
        log.debug("organizationStep");
        return stepBuilderFactory.get("organizationStep").<OrgBasVo, OrgBasVo>chunk(Constants.FETCH_COUNT).reader(organizationReader())
                .writer(organizationWriter()).build();
    }

    @Bean("organizationReader")
    ItemReader<OrgBasVo> organizationReader() {
        return new ItemReader<OrgBasVo>() {
            @Override
            public OrgBasVo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                if (orgList == null)
                    orgList = orgDao.selectTmpList(null);
                if (orgList.size() == 0)
                    return null;
                return orgList.remove(0);
            }
        };
    }

    @Bean("organizationWriter")
    ItemWriter<OrgBasVo> organizationWriter() {
        return new ItemWriter<OrgBasVo>() {

            @Override
            public void write(List<? extends OrgBasVo> items) throws Exception {
                if (deletedCount < 0)
                    deletedCount = orgDao.deleteNotTmp(null);
                for (int i = 0; i < items.size(); i++) {
                    OrgBasVo org = items.get(i);
                    if ("N".equals(org.getStat())) {
                        orgDao.insert(org);
                    } else {
                        orgDao.update(org);
                    }
                }

            }
        };
    }
}
