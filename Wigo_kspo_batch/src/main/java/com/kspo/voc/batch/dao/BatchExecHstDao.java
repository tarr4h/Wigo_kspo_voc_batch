package com.kspo.voc.batch.dao;

import com.kspo.voc.batch.mapper.CrmMapper;
import com.kspo.voc.batch.model.BatchInfoBasVo;

@CrmMapper
public interface BatchExecHstDao extends IVocDao {

	int updateBatch(Object param) throws Exception;

	BatchInfoBasVo selectBatchInfo(Object param)throws Exception;

}
