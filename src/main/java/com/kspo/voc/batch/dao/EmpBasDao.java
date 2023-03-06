package com.kspo.voc.batch.dao;

import java.util.List;

import com.kspo.voc.batch.mapper.VocMapper;
import com.kspo.voc.batch.model.EmpBasVo;

@VocMapper
public interface EmpBasDao extends IVocDao {
    int deleteNotTmp(Object param);

    List<EmpBasVo> selectTmpList(Object param);

    EmpBasVo selectEmpOrg(Object param);

    int insertLogin(Object param);

    int updateLogin(Object param);
}
