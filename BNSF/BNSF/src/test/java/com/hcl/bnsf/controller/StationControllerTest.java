package com.hcl.bnsf.controller;


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
import com.hcl.bnsf.domain.Station;
import com.hcl.bnsf.service.RosterService;
import com.hcl.bnsf.service.StationService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class StationControllerTest {

	protected static final Logger LOGGER = LoggerFactory.getLogger(StationControllerTest.class);

	@Autowired
	StationService stationService;


	@Test
	public void createStation() throws Exception {
		
		Station station = new Station();
		station.setId("1");
		station.setName("TestName");
		
		
		List employeeList = new ArrayList<String>(Arrays.asList("1","2","3"));
		station.setEmployees(employeeList);
		List stations = new ArrayList<String>(Arrays.asList("10","20","30"));
		station.setStations(stations);
		String createStation = stationService.createStation(station);
		LOGGER.debug("Inside test: " + createStation);
	}


}


