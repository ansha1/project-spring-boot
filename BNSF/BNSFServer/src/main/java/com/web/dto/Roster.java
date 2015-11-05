package com.web.dto;

import java.io.Serializable;
import java.util.List;

public class Roster implements Serializable {

	private static final long serialVersionUID = -221233824382406603L;

	private String id;

	private String type;

	private String name;

	private List<Station> stations;

	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Station> getStations() {
		return stations;
	}

	public String getType() {
		return type;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Roster [id=" + id + ", type=" + type + ", name=" + name + ", stations=" + stations + ", employees=" + employees + "]";
	}

}
