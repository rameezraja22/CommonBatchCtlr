package com.scb.batch.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
public class JobConfiguration {
	
	@Autowired
	private DataSource dataSource;

	@Bean
	public JobRepository getJobRepository(PlatformTransactionManager transactionManager) throws Exception {
		JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setDatabaseType("oracle");
		factoryBean.setTransactionManager(transactionManager);
		factoryBean.afterPropertiesSet();
		return (JobRepository) factoryBean.getObject();
	}


}
