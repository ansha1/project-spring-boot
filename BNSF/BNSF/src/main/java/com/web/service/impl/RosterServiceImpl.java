package com.web.service.impl;

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

import com.google.gson.Gson;
import com.web.dto.Roster;
import com.web.service.RosterService;
import com.web.util.JsonUtil;

@SuppressWarnings("deprecation")
@Service
public class RosterServiceImpl extends GenericServiceClient implements RosterService {

	@Override
	public Boolean createRoster(String rosterType, String rosterName, List<String> stationIds) throws Exception {
		LOGGER.debug("Inside createRoster");
		loadProperties();
		DefaultHttpClient httpClient = null;
		try {
			String method = "createRoster";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url + "/roster/" + method);
			LOGGER.debug("END POINT URL: " + postRequest.getURI());
			Map<String, Object> values = new HashMap<>();
			values.put("roster_type", rosterType);
			values.put("roster_name", rosterName);
			values.put("station_ids", stationIds);
			String body = "{\"" + method + "\": " + JsonUtil.getJson(values) + "}";
			LOGGER.debug("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());
			LOGGER.debug("Response body: " + string);
			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			httpClient.getConnectionManager().shutdown();
			return Boolean.TRUE;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
		return Boolean.FALSE;

	}

	@Override
	public Roster getRoster(String rosterId) throws Exception {
		LOGGER.debug("Inside getRoster");
		DefaultHttpClient httpClient = null;
		try {
			String method = "getRoster";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url + "/roster/" + method);
			LOGGER.debug("END POINT URL: " + postRequest.getURI());
			Map<String, Object> values = new HashMap<>();
			values.put("roster_id", rosterId);
			String body = "{\"" + method + "\": " + JsonUtil.getJson(values) + "}";
			LOGGER.debug("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());

			LOGGER.debug("Response body: " + string);
			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
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
