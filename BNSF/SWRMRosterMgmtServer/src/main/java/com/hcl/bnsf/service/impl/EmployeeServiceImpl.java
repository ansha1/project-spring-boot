package com.hcl.bnsf.service.impl;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bnsf.domain.Employee;
import com.hcl.bnsf.service.EmployeeService;


import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class EmployeeServiceImpl extends GenericServiceClient implements EmployeeService {
	protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


	public String addEmployeeToRoster(Employee employee) throws Exception {
			return "true";
	} 

		}
