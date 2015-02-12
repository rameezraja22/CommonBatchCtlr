package com.scb.batch.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.scb.batch.entity.BatchJobInstance;

public class BatchConfigurationIntegrationTest extends AbstractIntegrationTest {
	
	@Autowired
	RestBatchController restBatchController;
	
	/*@Test
	public void init(){
		System.out.println("Country Code Batch Module");
	}*/
	
	@Test
	public void getBatchJobInstance() throws Exception{
		List<BatchJobInstance> batchJobInstance = restBatchController.getJobInstances("importCountryCode");
		System.out.println(batchJobInstance.size());
		
	}
}
