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
import com.hcl.bnsf.service.RosterService;

@RestController
public class RosterController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(StationController.class);
	    
	 private final RosterService rosterService;

	    @Inject
	    public RosterController(final RosterService rosterService) {
	        this.rosterService = rosterService;
	    }
	    
	    @RequestMapping(value = "/roster/createRoster", method = RequestMethod.POST)
	    public String createRoster(@RequestBody @Valid final Roster roster) throws Exception {
	        System.out.println("Received request to create the {}" + roster);
	    	LOGGER.debug("Received request to create the {}", roster);
	        return rosterService.createRoster(roster);
	    }


	    @RequestMapping(value = "/roster/getRoster", method = RequestMethod.GET)
		public String getRoster() {
			try {
		        System.out.println("Received request to create the {get}");
				LOGGER.debug("Received request to get roster");
				String roster = rosterService.getRoster("1");
				LOGGER.debug(roster);
			}
			catch (Exception e) {
				LOGGER.debug(e.getMessage(), e);
			}
			return rosterService.getRoster("1");
		}
	}



