package com.hcl.bnsf.service;

import com.hcl.bnsf.domain.Roster;

public interface RosterService {
	String createRoster(Roster roster) throws Exception;
	String getRoster(String rosterId);
}

