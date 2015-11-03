package com.hcl.bnsf.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bnsf.domain.Roster;
import com.hcl.bnsf.domain.Station;
import com.hcl.bnsf.service.RosterService;
import com.hcl.bnsf.service.StationService;

@RestController
public class StationController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(StationController.class);
	    
	 private final StationService stationService;

	    @Inject
	    public StationController(final StationService stationService) {
	        this.stationService = stationService;
	    }
	    
	    @RequestMapping(value = "/station/createStation", method = RequestMethod.POST)
	    public String createStation(@RequestBody @Valid final Station station) throws Exception {
	        System.out.println("Received request to create the {}" + station);
	    	LOGGER.debug("Received request to create the {}", station);
	        return stationService.createStation(station);
	    }

	}



