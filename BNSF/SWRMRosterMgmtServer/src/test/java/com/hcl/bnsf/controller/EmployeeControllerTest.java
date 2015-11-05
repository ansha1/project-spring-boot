package com.hcl.bnsf.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.hcl.bnsf.Application;

import com.hcl.bnsf.domain.Employee;
import com.hcl.bnsf.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class EmployeeControllerTest {

	protected static final Logger LOGGER = LoggerFactory.getLogger(EmployeeControllerTest.class);

	@Autowired
	EmployeeService employeeService;


	@Test
	public void addEmployeeToRoster() throws Exception {
		Employee emp = new Employee("1", "123", "Neeraj", "12345", 123);
		String addEmployeeToRoster = employeeService.addEmployeeToRoster(emp);
		LOGGER.debug("Inside test: " + addEmployeeToRoster);
		assertThat(addEmployeeToRoster, equalTo("Success"));
	}
}
