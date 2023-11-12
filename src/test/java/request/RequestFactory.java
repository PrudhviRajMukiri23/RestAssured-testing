package request;

import client.RestClient;
import io.restassured.response.Response;

public class RequestFactory {

	RestClient restClient;
	
	public RequestFactory() {
		restClient = new RestClient();
	}
	
	public Response getResponse() {
		return restClient.SendGetRequest("/users");
	}
	
	public Response addResponse(Object requestPayload) {
		return restClient.SendPostRequest("/users", requestPayload);
	}
	
	public Response updateResponse(Object requestPayload) {
		return restClient.SendPutRequest("/users/2", requestPayload);
	}
	
	public Response deletionOfResponse() {
		return restClient.SendDeleteRequest("/users/2");
	}
	
}
