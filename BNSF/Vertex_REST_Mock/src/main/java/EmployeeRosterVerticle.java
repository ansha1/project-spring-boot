import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class EmployeeRosterVerticle extends AbstractVerticle {

	// Store our rosters
	private Map<Integer, Roster> rosters = new LinkedHashMap<>();
			
	@Override
	public void start(final Future<Void> fut) {

		Router router = Router.router(vertx);

		// Bind "/" to our hello message.
		router.route("/").handler(routingContext -> {
			   HttpServerResponse response = routingContext.response();
			   response.putHeader("content-type", "text/html").end("<h1>Welcome to Vert.x 3 Micro Services</h1>");
		});
	
		router.route("/bnsfrestservice/employee/addEmployeeToRoster*").handler(BodyHandler.create());
		router.post("/bnsfrestservice/employee/addEmployeeToRoster").handler(this::addEmployeeToRoster);
		
		router.get("/bnsfrestservice/roster/getRoster").handler(this::getRoster);
		
		router.route("/bnsfrestservice/roster/createRoster*").handler(BodyHandler.create());
		router.post("/bnsfrestservice/roster/createRoster").handler(this::createRoster);
		
		router.route("/bnsfrestservice/station/createStation*").handler(BodyHandler.create());
		router.post("/bnsfrestservice/station/createStation").handler(this::createStation);
		
		// Create some product
		List<Employee> e1 = new ArrayList<>();
		e1.add(new Employee("1", "1", "Employee1", "9999999999", 1));
		List<Station> s1 = new ArrayList<>();
		s1.add(new Station("1", "Station1", e1));
		Roster r1 = new Roster("1", "Roster1", "Roster1", s1, e1);
		rosters.put(1, r1);
		
		List<Employee> e2 = new ArrayList<>();
		e2.add(new Employee("2", "2", "Employee2", "9999999999", 2));
		List<Station> s2 = new ArrayList<>();
		s2.add(new Station("2", "Station2", e2));
		Roster r2 = new Roster("2", "Roster2", "Roster2", s2, e2);
		rosters.put(2, r2);
				
		// Create the HTTP server and pass the "accept" method to the request handler.
		vertx
			.createHttpServer()
			.requestHandler(router::accept)
			.listen(
				// Retrieve the port from the configuration, default to 8080.
				config().getInteger("http.port", 8989), 
				result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail(result.cause());
					}
				}
			);
	}

	private void getRoster(RoutingContext routingContext) {
		routingContext
			.response()
			.putHeader("content-type", "application/json; charset=utf-8")
			.end(Json.encodePrettily(rosters.values())); 
	}
	
	private void addEmployeeToRoster(RoutingContext routingContext) {
		final Employee employee = Json.decodeValue(routingContext.getBodyAsString(), Employee.class);
		routingContext
			.response()
			.putHeader("content-type", "application/json; charset=utf-8")
			.end(Json.encodePrettily(employee));
	}
	
	private void createRoster(RoutingContext routingContext) {
		final Roster roster = Json.decodeValue(routingContext.getBodyAsString(), Roster.class);
		routingContext
			.response()
			.putHeader("content-type", "application/json; charset=utf-8")
			.end(Json.encodePrettily(roster));
	}
	
	private void createStation(RoutingContext routingContext) {
		final Station station = Json.decodeValue(routingContext.getBodyAsString(), Station.class);
		routingContext
			.response()
			.putHeader("content-type", "application/json; charset=utf-8")
			.end(Json.encodePrettily(station));
	}
}
