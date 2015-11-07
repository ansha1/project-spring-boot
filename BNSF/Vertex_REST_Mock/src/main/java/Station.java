

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Station implements Serializable {

	private static final long serialVersionUID = 2189855707051227400L;

	private String id;

	private String name;

	private List<Employee> employees = new ArrayList<Employee>();

	private List<Station> stations = new ArrayList<Station>();
	
	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public Station() {
		super();
	}
	
	public Station(String id, String name, List<Employee> employees) {
		//super();
		this.id = id;
		this.name = name;
		this.employees = employees;
	}

	public Station(String id, String name, List<Employee> employees, List<Station> stations) {
		//super();
		this.id = id;
		this.name = name;
		this.employees = employees;
		this.stations = stations;
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

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", name=" + name + "]";
	}

}
