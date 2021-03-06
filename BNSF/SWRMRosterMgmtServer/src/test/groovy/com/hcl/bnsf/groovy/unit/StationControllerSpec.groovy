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
import com.hcl.bnsf.service.StationService;
import com.hcl.bnsf.controller.StationController;

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

class StationControllerSpec extends Specification {
	
	StationController stationController;
	StationService stationService;
	
	def setup() {
		stationService = Mock(StationService);
		stationController = new StationController(stationService);
	}
	
	def "call createStation should call appropriate methods on mock object"() {
		given:
			Station station = new Station();
			station.setId("1");
			station.setName("TestName");
			Employee e1 = new Employee("1", "1", "Emp1", "9999999999", 1);
			List<Employee> employeeList = new ArrayList<Employee>(Arrays.asList(e1));
			station.setEmployees(employeeList);
			Station station1 = new Station("1", "Stn1", employeeList);
			List<Station> stations = new ArrayList<Station>(Arrays.asList(station1));
			station.setStations(stations);
		when:
			stationController.createStation(station);
		then:
			1 * stationService.createStation(_);
			
	}
}
