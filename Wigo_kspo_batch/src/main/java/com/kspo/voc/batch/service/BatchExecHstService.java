package com.kspo.voc.batch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kspo.voc.batch.common.util.Utilities;
import com.kspo.voc.batch.dao.BatchExecHstDao;
import com.kspo.voc.batch.dao.IVocDao;
import com.kspo.voc.batch.model.BatchExecHstVo;
import com.kspo.voc.batch.model.BatchInfoBasVo;

/**
 * 
 * @ClassName BatchExecHstService
 * @author 김성태
 * @date 2022. 5. 26.
 * @Version 1.0
 * @description 배치실행이력 Service
 * @Company Copyright ⓒ wigo.ai. All Right Reserved
 */
@Service
public class BatchExecHstService extends AbstractVocService {
	@Autowired
	BatchExecHstDao dao;

	@Override
	public IVocDao getDao() {
		return dao;
	}

	@Override
	public int insert(Object param) throws Exception {
		int ret = super.insert(param);
		BatchExecHstVo vo = (BatchExecHstVo) param;
		if (Utilities.isNotEmpty(vo.getBatchCd()))
			updateBatch(param);
		return ret;
	}

	public int updateBatch(Object param) throws Exception {
		return dao.updateBatch(param);
	}

	public BatchInfoBasVo getBatchInfo(Object param) throws Exception {
		return dao.selectBatchInfo(param);
	}
}
