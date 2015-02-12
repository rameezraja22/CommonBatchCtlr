package com.scb.batch.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class BatchWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(BatchDataWebConfiguration.class);
		applicationContext.setServletContext(context);
		Dynamic servlet = context.addServlet("dispatcher", new DispatcherServlet(applicationContext));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}

}
