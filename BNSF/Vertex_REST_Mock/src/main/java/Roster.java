

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Roster implements Serializable {

	private static final long serialVersionUID = -221233824382406603L;

	private String id;

	private String type;

	private String name;

	private List<Station> stations = new ArrayList<Station>();;

	private List<Employee> employees = new ArrayList<Employee>();;
	
	public Roster() {
		super();
	}
	
	public Roster(String id, String type, String name, List<Station> stations, List<Employee> employees) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.stations = stations;
		this.employees = employees;
	}

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
