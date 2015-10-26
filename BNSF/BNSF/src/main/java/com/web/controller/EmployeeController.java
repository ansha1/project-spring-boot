package com.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.service.EmployeeService;

@Controller
public class EmployeeController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/employee/addEmployeeToRoster", method = RequestMethod.GET)
	public String addEmployeeToRoster() {

		try {
			LOGGER.debug("Received request to create station");
			String rosterId = "ROSTER ID";
			String employeeId = "ID";
			Integer sequenceNo = 1;
			Boolean createRoster = employeeService.addEmployeeToRoster(rosterId, employeeId, sequenceNo);
			LOGGER.debug(String.valueOf(createRoster));
		}
		catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			e.printStackTrace();
		}

		return "employee/addEmployeeToRoster";
	}

}
