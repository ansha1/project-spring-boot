package com.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.service.StationService;

@Controller
public class StationController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(StationController.class);

	@Autowired
	StationService stationService;

	@RequestMapping(value = "/station/createStation", method = RequestMethod.GET)
	public String createStation() {
		try {
			LOGGER.debug("Received request to create station");
			String name = "NAME";
			List<String> employeeIds = new ArrayList<>();
			employeeIds.add("AAA");
			employeeIds.add("BBB");
			List<String> stationIds = new ArrayList<>();
			stationIds.add("XXX");
			stationIds.add("YYY");
			Boolean createRoster = stationService.createStation(name, employeeIds, stationIds);
			LOGGER.debug(String.valueOf(createRoster));
		}
		catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			e.printStackTrace();
		}

		return "station/createStation";
	}
}
