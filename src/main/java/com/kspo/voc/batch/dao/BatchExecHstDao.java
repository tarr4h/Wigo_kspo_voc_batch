package com.kspo.voc.batch.dao;

import com.kspo.voc.batch.mapper.VocMapper;
import com.kspo.voc.batch.model.BatchInfoBasVo;

@VocMapper
public interface BatchExecHstDao extends IVocDao {

	int updateBatch(Object param) throws Exception;

	BatchInfoBasVo selectBatchInfo(Object param)throws Exception;

}
