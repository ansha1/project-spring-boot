package com.hcl.bnsf.java.functional;


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
import com.hcl.bnsf.controller.StationController;
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
		Employee e1 = new Employee("1", "1", "Emp1", "9999999999", 1);
		List<Employee> employeeList = new ArrayList<Employee>(Arrays.asList(e1));
		station.setEmployees(employeeList);
		Station station1 = new Station("1", "Stn1", employeeList);
		List<Station> stations = new ArrayList<Station>(Arrays.asList(station1));
		station.setStations(stations);
		StationController controller = new StationController(stationService);
		String createStation = controller.createStation(station);
		createStation.contains("TestName");
	}


}


