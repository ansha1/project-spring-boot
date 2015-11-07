package com.hcl.bnsf.java.functional;


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
import com.hcl.bnsf.service.EmployeeService;
import com.hcl.bnsf.controller.EmployeeController;

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
		Employee employee = new Employee("1", "123", "Neeraj", "12345", 123);
		EmployeeController controller = new EmployeeController(employeeService);
		String output = controller.addEmployeeToRoster(employee);
		System.out.println(output);
		output.contains("Neeraj");
	}
}
