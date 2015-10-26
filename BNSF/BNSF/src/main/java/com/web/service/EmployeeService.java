package com.web.service;

public interface EmployeeService {

	Boolean addEmployeeToRoster(String rosterId, String employeeId, Integer sequenceNo) throws Exception;
}
