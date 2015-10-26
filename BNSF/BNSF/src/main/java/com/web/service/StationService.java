package com.web.service;

import java.util.List;

public interface StationService {

	Boolean createStation(String name, List<String> employeeIds, List<String> stationIds) throws Exception;
}
