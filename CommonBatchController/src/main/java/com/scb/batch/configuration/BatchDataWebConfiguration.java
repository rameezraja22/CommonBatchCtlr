package com.scb.batch.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import com.scb.batch.controller.InfrastructureConfiguration;


@Configuration
@ComponentScan
@EnableWebMvc
//@Import(value = {InfrastructureConfiguration.class})
public class BatchDataWebConfiguration {

}
