package com.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.dto.Roster;
import com.web.service.RosterService;

@Controller
public class RosterController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(RosterController.class);

	@Autowired
	protected RosterService rosterService;

	@RequestMapping(value = "/roster/createRoster", method = RequestMethod.GET)
	public String createRoster() {
		try {
			LOGGER.debug("Received request to create roster");
			String rosterType = "TYPE";
			String rosterName = "NAME";
			List<String> stationIds = new ArrayList<>();
			stationIds.add("XXX");
			stationIds.add("YYY");
			stationIds.add("ZZZ");
			Boolean createRoster = rosterService.createRoster(rosterType, rosterName, stationIds);
			LOGGER.debug(String.valueOf(createRoster));
		}
		catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			e.printStackTrace();
		}
		return "roster/createRoster";
	}

	@RequestMapping(value = "/roster/getRoster", method = RequestMethod.GET)
	public String getRoster() {
		try {
			LOGGER.debug("Received request to get roster");
			Roster roster = rosterService.getRoster("1");
			LOGGER.debug(String.valueOf(roster));
		}
		catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			e.printStackTrace();
		}
		return "roster/getRoster";
	}
}
