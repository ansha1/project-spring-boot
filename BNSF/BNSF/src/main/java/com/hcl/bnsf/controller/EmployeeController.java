package com.hcl.bnsf.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bnsf.domain.Employee;
import com.hcl.bnsf.service.EmployeeService;

@RestController
public class EmployeeController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	    
	 private final EmployeeService employeeService;

	    @Inject
	    public EmployeeController(final EmployeeService employeeService) {
	        this.employeeService = employeeService;
	    }
	    
	    @RequestMapping(value = "/employee/addEmployeeToRoster", method = RequestMethod.POST)
	    public String addEmployeeToRoster(@RequestBody @Valid final Employee employee) throws Exception {
	        System.out.println("Received request to create the {}" + employee);
	    	LOGGER.debug("Received request to create the {}", employee);
	        return employeeService.addEmployeeToRoster(employee);
	    }

	}
