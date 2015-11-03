package com.hcl.bnsf.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bnsf.domain.Roster;
import com.hcl.bnsf.service.RosterService;
import com.google.gson.Gson;

@Service
@Validated
public class RosterServiceImpl extends GenericServiceClient implements RosterService {
	protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public String createRoster(Roster roster) throws Exception {
		LOGGER.debug("Inside createRoster");
		loadProperties();
		DefaultHttpClient httpClient = null;
		try {
			String method = "createRoster";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url + "/roster/" + method);
			LOGGER.debug("END POINT URL: " + postRequest.getURI());
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("roster_id", roster.getId());
			values.put("roster_type", roster.getType());
			values.put("roster_name", roster.getName());
			values.put("station_ids", roster.getStations());
			values.put("employee_ids", roster.getEmployees());
			
			
			String body = "{\"" + method + "\": " + OBJECT_MAPPER.writeValueAsString(values) + "}";
			LOGGER.debug("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());
			System.out.println("Response body Roster: " + string);
			LOGGER.debug("Response body: " + string);
			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			System.out.println("Roster Done");
			httpClient.getConnectionManager().shutdown();
			return string;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
		return null;
	}

	public Roster getRoster(String rosterId) {
		LOGGER.debug("Inside getRoster");
		DefaultHttpClient httpClient = null;
		try {
			String method = "getRoster";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url + "/roster/" + method);
			LOGGER.debug("END POINT URL: " + postRequest.getURI());
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("roster_id", rosterId);
			String body = "{\"" + method + "\": " + OBJECT_MAPPER.writeValueAsString(values) + "}"; 
			LOGGER.debug("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());
			System.out.println("Response body: " + string);
			LOGGER.debug("Response body: " + string);
			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			System.out.println("getRoster Done");
			httpClient.getConnectionManager().shutdown();
			Gson gson = new Gson();
			return gson.fromJson(string, Roster.class);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
		return null;

	} 

	
}


