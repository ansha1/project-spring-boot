package com.web.service;

import java.util.List;

import com.web.dto.Roster;

public interface RosterService {

	Boolean createRoster(String rosterType, String rosterName, List<String> stationIds) throws Exception;

	Roster getRoster(String rosterId) throws Exception;

}
