package com.scb.batch.controller;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class InfrastructureConfiguration {

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");//jdbc:oracle:thin:@hklpdddasb-scan.hk.standardchartered.com:1622/OCCSS_DEV_01.hk.standardchartered.com
//		dataSource.setUsername("OCCSS_BIZSVC_HK_DEV_01");
		dataSource.setUsername("SYSTEM");
		dataSource.setPassword("system123"); //ikajsyd_123
		return dataSource;
	}
	
}
