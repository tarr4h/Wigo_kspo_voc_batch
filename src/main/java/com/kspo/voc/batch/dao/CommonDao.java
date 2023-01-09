package com.kspo.voc.batch.dao;

@com.kspo.voc.batch.mapper.VocMapper
public interface CommonDao extends IVocDao {

	String getAutoSeq(Object param);

	String endcryptText(String param);

	String decryptText(String param);
}
