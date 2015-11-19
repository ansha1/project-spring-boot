package com.hcl.bnsf.service.impl;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bnsf.domain.Employee;
import com.hcl.bnsf.service.EmployeeService;
import java.io.IOException;
import java.net.MalformedURLException;


@SuppressWarnings("deprecation")
@Service
@Validated
public class EmployeeServiceImpl extends GenericServiceClient implements EmployeeService {
	protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


	public String addEmployeeToRoster(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.debug("Inside addEmployeeToRoster");
		loadProperties();
		DefaultHttpClient httpClient = null;
		try {
			String method = "addEmployeeToRoster";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url + "/employee/" + method);
			LOGGER.debug("END POINT URL: " + postRequest.getURI());
			
			/**
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("roster_id", employee.getRosterId());
			values.put("employee_id", employee.getId());
			values.put("sequence_no", employee.getSeqNo());
			values.put("name", employee.getName());
			values.put("phone_number", employee.getPhoneNo());
			String body = "{\"" + method + "\": " + OBJECT_MAPPER.writeValueAsString(values) + "}";
			**/

			
			String body = OBJECT_MAPPER.writeValueAsString(employee);
			
			LOGGER.debug("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());
			System.out.println("Response body: " + string);
			LOGGER.debug("Response body: " + string);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			System.out.println("Employee Done");
			httpClient.getConnectionManager().shutdown();
			return string;
		} catch (MalformedURLException e) {
			LOGGER.debug("MalformedURLException in EmployeeServiceImpl:" + e.getMessage());
		} catch (IOException e) {
			LOGGER.debug("IOException in EmployeeServiceImpl:" + e.getMessage());
		} 
//		catch (Exception e) 
//		{
//			LOGGER.debug("Exception in EmployeeServiceImpl:" + e.getMessage());
//		}finally {
//			
//			httpClient.close();
//		}
		return null;
	} 
		}
