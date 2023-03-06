package com.kspo.voc.batch.dao;

import java.util.List;

import com.kspo.voc.batch.mapper.VocMapper;
import com.kspo.voc.batch.model.OrgBasVo;

@VocMapper
public interface OrgBasDao extends IVocDao {

    int deleteNotTmp(Object param);

    List<OrgBasVo> selectTmpList(Object param);
}
