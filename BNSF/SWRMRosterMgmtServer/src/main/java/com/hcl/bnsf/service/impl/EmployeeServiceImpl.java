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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally {
			
			httpClient.close();
		}
		return null;
	} 
		}
