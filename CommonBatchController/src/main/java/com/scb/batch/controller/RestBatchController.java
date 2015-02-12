package com.scb.batch.controller;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scb.batch.entity.BatchJobInstance;
import com.scb.batch.repository.JobInstanceRepository;

@RestController
@RequestMapping("/batch")
public class RestBatchController {
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	JobRegistry jobRegistry;
	
	@Autowired
	private JobInstanceRepository jobInstanceRepository;
	
	@RequestMapping(value="/getJobInstance", method= RequestMethod.POST)
	public List<BatchJobInstance> getJobInstances(@RequestParam (value="jobName") String jobName) throws Exception{
		Job job = jobRegistry.getJob(jobName);
		JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
		return getBatchJobInstance(jobName);
	}
	
	private List<BatchJobInstance> getBatchJobInstance(String jobName) {
		Iterable<BatchJobInstance> batchJobInstances = jobInstanceRepository.findByJobName(jobName);
		List<BatchJobInstance> jobInstances = (List<BatchJobInstance>) batchJobInstances;
		return jobInstances;
	}
}
