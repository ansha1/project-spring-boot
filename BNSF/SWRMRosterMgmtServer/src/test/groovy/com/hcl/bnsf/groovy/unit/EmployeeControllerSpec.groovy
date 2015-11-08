package com.hcl.bnsf.groovy.unit;

import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate

import com.hcl.bnsf.domain.Employee
import com.hcl.bnsf.service.EmployeeService;
import com.hcl.bnsf.controller.EmployeeController;

import spock.lang.Shared
import spock.lang.Specification
 
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
 
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.boot.test.SpringApplicationContextLoader
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.boot.test.IntegrationTest
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.annotation.Autowired;
import com.jayway.jsonpath.JsonPath;

import com.hcl.bnsf.Application;

class EmployeeControllerSpec extends Specification {
	
	EmployeeController employeeController;
	EmployeeService employeeService;
	
	def setup() {
		employeeService = Mock(EmployeeService);
		employeeController = new EmployeeController(employeeService);
	}
	
	def "call addEmployeeToRoster should call appropriate methods on mock object"() {
		given: 
			Employee emp = new Employee("1", "123", "Neeraj", "12345", 123);
		when:
			employeeController.addEmployeeToRoster(emp);
		then:
			1 * employeeService.addEmployeeToRoster(_);
			
	}
}
