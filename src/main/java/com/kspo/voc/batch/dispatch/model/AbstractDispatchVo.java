package com.kspo.voc.batch.dispatch.model;

import java.util.Map;

import com.kspo.voc.batch.common.util.Utilities;

public abstract class AbstractDispatchVo {
	public String toWebParameter() {
		Map<String, Object> param = Utilities.beanToMap(this);
		StringBuffer bf = new StringBuffer();
		for (String strKey : param.keySet()) {
			if (bf.length() > 0)
				bf.append("&");
			bf.append(strKey);
			bf.append("=");
			bf.append(param.get(strKey));
		}
		return bf.toString();
	}
}
