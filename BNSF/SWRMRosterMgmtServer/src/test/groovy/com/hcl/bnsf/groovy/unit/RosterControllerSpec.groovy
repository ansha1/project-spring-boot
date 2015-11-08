package com.hcl.bnsf.groovy.unit;

import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate

import com.hcl.bnsf.domain.Roster;
import com.hcl.bnsf.domain.Employee;
import com.hcl.bnsf.domain.Station;
import com.hcl.bnsf.service.RosterService;
import com.hcl.bnsf.controller.RosterController;

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

class RosterControllerSpec extends Specification {
	
	RosterController rosterController;
	RosterService rosterService;
	
	def setup() {
		rosterService = Mock(RosterService);
		rosterController = new RosterController(rosterService);
	}
	
	def "call createRoster should call appropriate methods on mock object"() {
		given: 
			Roster roster = new Roster();
			roster.id = "1";
			roster.name = "TestRoster";
			roster.type = "SimpleType";
			Employee e1 = new Employee("1", "1", "Emp1", "9999999999", 1);
			List<Employee> employeeList = new ArrayList<Employee>(Arrays.asList(e1));
			roster.setEmployees(employeeList);
			Station station = new Station("1", "Stn1", employeeList);
			List<Station> stations = new ArrayList<Station>(Arrays.asList(station));
			roster.setStations(stations);
		when:
			rosterController.createRoster(roster);
		then:
			1 * rosterService.createRoster(_);
			
	}
}
