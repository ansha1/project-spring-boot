package com.web.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.web.util.JsonUtil;

@SuppressWarnings("deprecation")
public class BNSFRestServiceClient {

	public static void main(String[] args) {
		try {
			Integer sequenceNo = 1;
			String rosterId = "Roster 1";
			String rosterName = "India";
			String rosterType = "Country";
			String employeeId = "EMPABC";
			String stationName = "Station 1";
			List<String> stationIds = new ArrayList<>();
			stationIds.add("XXX");
			stationIds.add("YYY");
			stationIds.add("ZZZ");
			List<String> employeeIds = new ArrayList<>();
			stationIds.add("AAA");
			stationIds.add("BBB");
			stationIds.add("CCC");
			BNSFRestServiceClient client = new BNSFRestServiceClient();
			client.addEmployeeToRoster(rosterId, employeeId, sequenceNo);
			System.out.println();
			client.createRoster(rosterType, rosterName, stationIds);
			System.out.println();
			client.getRoster(rosterId);
			System.out.println();
			client.createStation(stationName, employeeIds, stationIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String BASE_URL = "http://localhost:8090/BNSFServer/api/bnsfrestservice";

	public Boolean addEmployeeToRoster(String rosterId, String employeeId, Integer sequenceNo) throws Exception {
		System.out.println("Inside addEmployeeToRoster");
		DefaultHttpClient httpClient = null;
		try {
			String method = "addEmployeeToRoster";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(BASE_URL + "/employee/" + method);
			Map<String, Object> values = new HashMap<>();
			values.put("roster_id", rosterId);
			values.put("employee_id", employeeId);
			values.put("sequence_no", sequenceNo);
			String body = "{\"" + method + "\": " + JsonUtil.getJson(values) + "}";
			System.out.println("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());
			System.out.println("Response body: " + string);
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

	public Boolean createRoster(String rosterType, String rosterName, List<String> stationIds) throws Exception {
		System.out.println("Inside createRoster");
		DefaultHttpClient httpClient = null;
		try {
			String method = "createRoster";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(BASE_URL + "/roster/" + method);
			Map<String, Object> values = new HashMap<>();
			values.put("roster_type", rosterType);
			values.put("roster_name", rosterName);
			values.put("station_ids", stationIds);
			String body = "{\"" + method + "\": " + JsonUtil.getJson(values) + "}";
			System.out.println("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());
			System.out.println("Response body: " + string);
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

	public Boolean createStation(String name, List<String> employeeIds, List<String> stationIds) throws Exception {
		System.out.println("Inside createStation");
		DefaultHttpClient httpClient = null;
		try {
			String method = "createStation";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(BASE_URL + "/station/" + method);
			Map<String, Object> values = new HashMap<>();
			values.put("name", name);
			values.put("employee_ids", employeeIds);
			values.put("station_ids", stationIds);
			String body = "{\"" + method + "\": " + JsonUtil.getJson(values) + "}";
			System.out.println("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());
			System.out.println("Response body: " + string);
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

	public Boolean getRoster(String rosterId) throws Exception {
		System.out.println("Inside getRoster");
		DefaultHttpClient httpClient = null;
		try {
			String method = "getRoster";
			httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(BASE_URL + "/roster/" + method);
			Map<String, Object> values = new HashMap<>();
			values.put("roster_id", rosterId);
			String body = "{\"" + method + "\": " + JsonUtil.getJson(values) + "}";
			System.out.println("Request body: " + body);
			StringEntity entity = new StringEntity(body);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);
			String string = IOUtils.toString(response.getEntity().getContent());
			System.out.println("Response body: " + string);
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
}
