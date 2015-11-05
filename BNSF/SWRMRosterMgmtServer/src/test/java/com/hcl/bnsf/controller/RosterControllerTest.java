package com.hcl.bnsf.controller;



import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.hcl.bnsf.Application;
import com.hcl.bnsf.domain.Employee;
import com.hcl.bnsf.domain.Roster;
import com.hcl.bnsf.service.RosterService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class RosterControllerTest {

	protected static final Logger LOGGER = LoggerFactory.getLogger(RosterControllerTest.class);

	@Autowired
	RosterService rosterService;


	@Test
	public void createRoster() throws Exception {
		
		Roster roster = new Roster();
		roster.setId("1");
		roster.setName("TestRoster");
		roster.setType("SimpleType");
		List employeeList = new ArrayList<String>(Arrays.asList("1","2","3"));
		roster.setEmployees(employeeList);
		List stations = new ArrayList<String>(Arrays.asList("10","20","30"));
		roster.setStations(stations);
		String createRoster = rosterService.createRoster(roster);
		LOGGER.debug("Inside test: " + createRoster);
		assertThat(createRoster, equalTo("Success"));
	}


}


