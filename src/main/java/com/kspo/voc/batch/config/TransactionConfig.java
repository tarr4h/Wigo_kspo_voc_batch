package com.kspo.voc.batch.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SuppressWarnings("deprecation")
@Configuration
@EnableTransactionManagement
public class TransactionConfig {
	@Primary
	@Bean(name = "txManager")
	DataSourceTransactionManager crmTxManager(@Qualifier("vocDataSource") DataSource dataSource) throws Exception {
		DataSourceTransactionManager tx = new DataSourceTransactionManager(dataSource);
		tx.setDefaultTimeout(3600 *2);
		return tx;
	}


}
