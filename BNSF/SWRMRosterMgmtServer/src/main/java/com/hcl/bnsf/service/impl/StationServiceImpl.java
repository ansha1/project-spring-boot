package com.hcl.bnsf.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.hcl.bnsf.domain.Station;
import com.hcl.bnsf.service.StationService;

@Service
@Validated
public class StationServiceImpl extends GenericServiceClient implements StationService{
	protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public String createStation(Station station) throws Exception {
		LOGGER.debug("Inside createStation");
		

		DefaultHttpClient httpClient = null;
		try {
			loadProperties();
			String method = "createStation";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url + "/station/" + method);
			if(postRequest ==null)
				throw new MalformedURLException();
			LOGGER.debug("END POINT URL: " + postRequest.getURI());
			/**
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("id", station.getId());
			values.put("name", station.getName());
			values.put("employee_ids", station.getEmployees());
			values.put("station_ids", station.getStations());
			String body = "{\"" + method + "\": " + OBJECT_MAPPER.writeValueAsString(values) + "}";
			**/
			
			String body = OBJECT_MAPPER.writeValueAsString(station);
			LOGGER.debug("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			if(httpClient == null)
				throw new NullPointerException();
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());
			System.out.println("Response body Stations: " + string);
			LOGGER.debug("Response body: " + string);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			
			System.out.println("Create Station Done");
			httpClient.getConnectionManager().shutdown();
			return string;
		} catch (MalformedURLException e) {
			LOGGER.debug("MalformedURLException in StationServiceImpl :" +e.getMessage());
		} catch (IOException e) {
			LOGGER.debug("IOException in StationServiceImpl :" +e.getMessage());
		} catch (Exception e) 
		{
			LOGGER.debug("Exception in StationServiceImpl :" +e.getMessage());
		}finally {
			httpClient.close();
		}
		return null;
	}

}
