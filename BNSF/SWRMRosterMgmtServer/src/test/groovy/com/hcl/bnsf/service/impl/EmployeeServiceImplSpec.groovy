package com.hcl.bnsf.service.impl

import com.hcl.bnsf.domain.Employee;

import spock.lang.Shared
import spock.lang.Specification

class EmployeeServiceImplSpec extends Specification {
	
	   EmployeeServiceImpl esi = new EmployeeServiceImpl();
	
	   def "add employee to roster should return Success"() {
		   given:
		   	Employee emp = new Employee("1", "123", "Neeraj", "12345", 123);
   
		   when:
		   	String addEmployeeToRoster = esi.addEmployeeToRoster(emp);
		 
		   then:
		   	addEmployeeToRoster == "Success"
	   }
   }
