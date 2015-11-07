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
import com.hcl.bnsf.controller.RosterController;
import com.hcl.bnsf.domain.Roster;
import com.hcl.bnsf.domain.Employee;
import com.hcl.bnsf.domain.Station;
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
		Employee e1 = new Employee("1", "1", "Emp1", "9999999999", 1);
		List<Employee> employeeList = new ArrayList<Employee>(Arrays.asList(e1));
		roster.setEmployees(employeeList);
		Station station = new Station("1", "Stn1", employeeList);
		List<Station> stations = new ArrayList<Station>(Arrays.asList(station));
		roster.setStations(stations);
		RosterController controller = new RosterController(rosterService);
		String createRoster = controller.createRoster(roster);
		createRoster.contains("SimpleType");
	}


}


