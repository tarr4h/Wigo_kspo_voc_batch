package com.kspo.voc.batch.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.kspo.voc.batch.common.util.VocSqlLogger;
import com.kspo.voc.batch.mapper.CrmMapper;

/**
 *
 * <pre>
 * com.ceragem.voc.config - DatabaseConfig.java
 * </pre>
 *
 * @ClassName : DatabaseConfig
 * @Description : DB 설정
 * @author : 김성태
 * @date : 2021. 1. 5.
 * @Version : 1.0
 * @Company : Copyright ⓒ wigo.ai. All Right Reserved
 */
@Primary
@Configuration("vocDatabaseConfig")
@MapperScan(basePackages = {
		"com.kspo.voc.batch.dao" }, value = "최상위 패키지 경로", annotationClass = CrmMapper.class, sqlSessionFactoryRef = "vocSqlSessionFactory")
class VocDatabaseConfig {
	@Autowired
	ApplicationContext applicationContext;

	@Value("${spring.datasource.driver-class-name}")
	String dbDriverClassName;

	@Value("${spring.datasource.url}")
	String dbURL;

	@Value("${spring.datasource.username}")
	String userName;

	@Value("${spring.datasource.password}")
	String password;

	@Value("${spring.datasource.hikari.connection-timeout}")
	int timeout;

	@Value("${spring.datasource.hikari.maximum-pool-size}")
	int poolSize;

	@Primary
	@Bean(name = "vocDataSource")
	DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(dbDriverClassName);
    dataSource.setUrl(dbURL);
    dataSource.setUsername(userName);
    dataSource.setPassword(password);
    return dataSource;
//
////    return dataSource;
//		if (poolSize <= 0)
//			poolSize = 10;
//		if (timeout <= 0)
//			timeout = 60000;
//		HikariConfig hikariConfig = new HikariConfig();
//		hikariConfig.setDriverClassName(dbDriverClassName);
//		hikariConfig.setUsername(userName);
//		hikariConfig.setPassword(password);
//		hikariConfig.setJdbcUrl(dbURL);
//		hikariConfig.setMaximumPoolSize(poolSize);
//		hikariConfig.setConnectionTimeout(timeout);
//		hikariConfig.setLeakDetectionThreshold(30000);
//		hikariConfig.setPoolName("cragem-voc-batch-pool");
//		return new HikariDataSource(hikariConfig);
	}

	@Primary
	@Bean(name = "vocSqlSessionFactory")
	SqlSessionFactory sqlSessionFactory(@Qualifier("vocDataSource") DataSource dataSource,
			@Qualifier("vocSqlLogger") VocSqlLogger interceptor) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean
				.setConfigLocation(applicationContext.getResource("classpath:/config/mybatis/mybatis-config-base.xml"));
		sqlSessionFactoryBean
				.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*_SqlMapper.xml"));
//		sqlSessionFactoryBean.setTypeAliasesPackage("com.ceragem.**.model,com.ceragem.**.entity");
		sqlSessionFactoryBean.setPlugins(interceptor);
		return sqlSessionFactoryBean.getObject();
	}

//
	@Primary
	@Bean(name = "vocSqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate(@Qualifier("vocSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
