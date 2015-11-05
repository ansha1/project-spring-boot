package com.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.web.dto.Employee;
import com.web.dto.Roster;
import com.web.dto.Station;

@Path("/bnsfrestservice")
public class BNSFRestService {

	@POST
	@Path("/employee/addEmployeeToRoster")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployeeToRoster(InputStream incomingData) {
		String response = null;
		try {
			String dataReceived = IOUtils.toString(incomingData);
			System.out.println(dataReceived);
			JSONObject mainObject = (JSONObject) new JSONObject(dataReceived).get("addEmployeeToRoster");
			String rosterId = (String) mainObject.get("roster_id");
			String employeeId = (String) mainObject.get("employee_id");
			Integer sequenceNo = (Integer) mainObject.get("sequence_no");
			System.out.println("Data Received: " + mainObject);
			response = "Employee: " + employeeId + " is assigned to roster: " + rosterId + ". Sequence: " + sequenceNo;
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(response).build();
	}

	@POST
	@Path("/roster/createRoster")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRoster(InputStream incomingData) {
		String response = null;
		try {
			String dataReceived = IOUtils.toString(incomingData);
			System.out.println(dataReceived);
			JSONObject mainObject = (JSONObject) new JSONObject(dataReceived).get("createRoster");
			String rosterType = (String) mainObject.get("roster_type");
			String rosterName = (String) mainObject.get("roster_name");
			JSONArray stationIds = (JSONArray) mainObject.get("station_ids");
			List<String> stations = new ArrayList<String>();
			for (int i = 0; i < stationIds.length(); i++) {
				stations.add((String) stationIds.get(i));
			}
			System.out.println("Data Received: " + mainObject);
			response = "Roster Created. Name: " + rosterName + ", Type: " + rosterType + ", Stations: " + stations;
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(response).build();
	}

	@POST
	@Path("/station/createStation")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createStation(InputStream incomingData) {
		String response = null;
		try {
			String dataReceived = IOUtils.toString(incomingData);
			System.out.println(dataReceived);
			JSONObject mainObject = (JSONObject) new JSONObject(dataReceived).get("createStation");
			String name = (String) mainObject.get("name");
			List<String> stations = new ArrayList<String>();
			List<String> employees = new ArrayList<String>();
			JSONArray employeeIds = (JSONArray) mainObject.get("employee_ids");
			for (int i = 0; i < employeeIds.length(); i++) {
				employees.add((String) employeeIds.get(i));
			}
			JSONArray stationIds = (JSONArray) mainObject.get("station_ids");
			for (int i = 0; i < stationIds.length(); i++) {
				stations.add((String) stationIds.get(i));
			}
			System.out.println("Data Received: " + mainObject);
			response = "Station Created. Name: " + name + ", Employees: " + employeeIds + ", Stations: " + stations;
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(response).build();
	}

	@POST
	@Path("/roster/getRoster")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getRoster(InputStream incomingData) {
		String response = null;
		try {
			String dataReceived = IOUtils.toString(incomingData);
			System.out.println(dataReceived);
			JSONObject mainObject = (JSONObject) new JSONObject(dataReceived).get("getRoster");
			String rosterId = (String) mainObject.get("roster_id");
			Roster roster = new Roster();
			roster.setId(rosterId);
			roster.setName("India");
			roster.setType("Country");
			List<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(new Employee("1", "NAME1", "9999999999", 1));
			employeeList.add(new Employee("2", "NAME2", "8888888888", 2));
			List<Station> stationList = new ArrayList<Station>();
			stationList.add(new Station("Station 1", "Station Name", employeeList));
			roster.setEmployees(employeeList);
			roster.setStations(stationList);
			response = new JSONObject(roster).toString();
			System.out.println("Data Received: " + mainObject);
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(response).build();
	}
}
