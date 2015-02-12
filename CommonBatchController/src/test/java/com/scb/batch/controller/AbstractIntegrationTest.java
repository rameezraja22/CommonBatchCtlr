package com.scb.batch.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scb.batch.configuration.BatchConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { BatchConfiguration.class, InfrastructureConfiguration.class })
public abstract class AbstractIntegrationTest {

}
