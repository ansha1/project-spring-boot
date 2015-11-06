import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class EmployeeRosterVerticle extends AbstractVerticle {

	@Override
	public void start(final Future<Void> fut) {
		startWebApp((http) -> completeStartup(http, fut));
	}

	private void startWebApp(Handler<AsyncResult<HttpServer>> next) {
		// Create a router object.
		Router router = Router.router(vertx);

		// Bind "/" to our hello message.
		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/html").end("<h1>Hello from my first Vert.x 3 application</h1>");
		});

		router.post("/bnsfrestservice/employee/addEmployeeToRoster").handler(this::addEmployeeToRoster);
		router.get("/bnsfrestservice/roster/getRoster").handler(this::getRoster);
		router.post("/bnsfrestservice/roster/createRoster").handler(this::createRoster);
		router.post("/bnsfrestservice/station/createStation").handler(this::createStation);
		
		// Create the HTTP server and pass the "accept" method to the request handler.
		vertx.createHttpServer().requestHandler(router::accept).listen(
			// Retrieve the port from the configuration, default to 8080.
			config().getInteger("http.port", 8989), next::handle
		);
	}

	private void completeStartup(AsyncResult<HttpServer> http, Future<Void> fut) {
		if (http.succeeded()) {
			fut.complete();
		} else {
			fut.fail(http.cause());
		}
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}

	private void getRoster(RoutingContext routingContext) {
		JsonObject jsonObj = new JsonObject("{\"id\":\"1\",\"name\":\"text\",\"type\":\"textType\",\"stations\":[{\"id\":\"1\",\"name\":\"station-1\",\"employees\":[{\"rosterId\":\"1\",\"id\":\"1\",\"name\":\"Employee\",\"phoneNo\":\"9890098900\",\"seqNo\":1}]}]}");
		routingContext
			.response()
			.putHeader("content-type", "application/json; charset=utf-8")
			.end(jsonObj.toString()); 
	}
	
	private void addEmployeeToRoster(RoutingContext routingContext) {
		JsonObject jsonObj = new JsonObject("{\"id\":\"1\",\"rosterId\":\"1\",\"name\":\"Employee\",\"seqNo\":1,\"phoneNo\":\"9890098900\"}");
		routingContext
			.response()
			.putHeader("content-type", "application/json; charset=utf-8")
			.end(jsonObj.toString());
	}
	
	private void createRoster(RoutingContext routingContext) {
		JsonObject jsonObj = new JsonObject("{\"id\":\"1\",\"name\":\"text\",\"type\":\"textType\",\"stations\":[{\"id\":\"1\",\"name\":\"station-1\",\"employees\":[{\"rosterId\":\"1\",\"id\":\"1\",\"name\":\"Employee\",\"phoneNo\":\"9890098900\",\"seqNo\":1}]}]}");
		routingContext
			.response()
			.putHeader("content-type", "application/json; charset=utf-8")
			.end(jsonObj.toString());
	}
	
	private void createStation(RoutingContext routingContext) {
		JsonObject jsonObj = new JsonObject("{\"id\":\"1\",\"name\":\"text\",\"employees\":[{\"rosterId\":\"1\",\"id\":\"1\",\"name\":\"Employee\",\"phoneNo\":\"9890098900\",\"seqNo\":1}]}");
		routingContext
			.response()
			.putHeader("content-type", "application/json; charset=utf-8")
			.end(jsonObj.toString());
	}
}
