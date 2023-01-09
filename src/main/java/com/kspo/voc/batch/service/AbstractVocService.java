package com.kspo.voc.batch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kspo.voc.batch.common.util.Utilities;
import com.kspo.voc.batch.dao.IVocDao;
import com.kspo.voc.batch.model.BaseVo;

/**
 * <pre>
 * com.kspo.voc.batch.common.service.AbstractVocService
 * </pre>
 *
 * @ClassName : AbstractVocService
 * @Description : AbstractVocService
 * @author : 김성태
 * @date : 2021. 4. 27.
 * @Version : 1.0
 * @Company : Copyright ⓒ wigo.CO.LTD. All Right Reserved
 */
public abstract class AbstractVocService {
	public abstract IVocDao getDao();

	public <T> List<T> getList(Object param) throws Exception {
		return getDao().selectList(param);
	}

	public <T> T get(Object param) throws Exception {
		return getDao().select(param);
	}

	public int getListCount(Object param) throws Exception {
		return getDao().selectListCount(param);
	}

	public int insert(Object param) throws Exception {
		return getDao().insert(param);
	}

	public int update(Object param) throws Exception {
		return getDao().update(param);
	}

	public int delete(Object param) throws Exception {
		return getDao().delete(param);
	};

	public Map<String, Object> save(BaseVo vo) throws Exception {
		if ("C".equalsIgnoreCase(vo.getStat()))
			return Utilities.getInsertResult(insert(vo), vo);
		else if ("U".equalsIgnoreCase(vo.getStat()))
			return Utilities.getUpdateResult(update(vo), vo);
		else if ("D".equalsIgnoreCase(vo.getStat()))
			return Utilities.getDeleteResult(delete(vo));
		return Utilities.getUpdateResult(0, vo);
	}

	public Map<String, Object> insertList(List<? extends BaseVo> list) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			result.add(Utilities.getInsertResult(insert(list.get(i)), list.get(i)));
		}
		return Utilities.getSaveResult(result);
	}

	public Map<String, Object> updateList(List<? extends BaseVo> list) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			result.add(Utilities.getUpdateResult(update(list.get(i)), list.get(i)));
		}
		return Utilities.getSaveResult(result);
	}

	public Map<String, Object> deleteList(List<? extends BaseVo> list) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			result.add(Utilities.getDeleteResult(delete(list.get(i))));
		}
		return Utilities.getSaveResult(result);
	}

	public Map<String, Object> saveList(List<? extends BaseVo> list) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			result.add(save(list.get(i)));
		}
		return Utilities.getSaveResult(result);
	}

}
