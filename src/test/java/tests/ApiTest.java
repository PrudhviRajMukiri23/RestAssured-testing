package tests;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import request.RequestFactory;

public class ApiTest extends BaseStep{
	
	@Test (enabled = true)
	public void validateGetRequest() {
		extentrep.createExtentTest("Validate Get response --");
		ValidatableResponse valRes = requestFactory.getResponse().then();
		//valRes.statusCode(200);
		valRes.statusCode(200);//test value for getting error
		extentrep.addLogToTest(Status.INFO, valRes.log().all().toString());
	}
	
	@Test (enabled = true)
	public void validateAddResponse() {
		extentrep.createExtentTest("Validate Add response --");
		String requestPayload = "{\r\n"
				+ "    \"name\": \"Prudhvi\",\r\n"
				+ "    \"job\": \"Leader\"\r\n"
				+ "}";
		
		Response response =	requestFactory.addResponse(requestPayload);
				response.then().log().all().statusCode(201);
		
		extentrep.addLogToTest(Status.INFO, response.body().asString());
	}
	
	@Test (enabled = false)
	public void validateAddResponsewithRequestPayloadAsMap() {
		Map<String, Object> requestPayload = new HashMap<String, Object>();
		requestPayload.put("name", "Prudhvi");
		requestPayload.put("job", "Leader");
		
		requestFactory.addResponse(requestPayload).then().log().all().statusCode(201);
	}
	
	@Test (enabled = false)
	public void endToEndTest() {
		
		//Post the response
		Map<String, Object> requestPayload = new HashMap<String, Object>();
		requestPayload.put("name", "Prudhvi");
		requestPayload.put("job", "Leader");
		
		Response res = requestFactory.addResponse(requestPayload);
		
		res.then().statusCode(201);
		
		String result = res.body().asString();
		
		assertEquals(result.contains("Prudhvi"), true);
		
		//get the response
		requestFactory.getResponse().body().prettyPrint();
		
		//put the edited response
		Map<String, Object> requestPayload1 = new HashMap<String, Object>();
		requestPayload1.put("name", "Prudhvi");
		requestPayload1.put("job", "QA");
		
		Response res1 = requestFactory.updateResponse(requestPayload1);
		
		res1.then().statusCode(201);
		
		//delete the response
		Response res2 = requestFactory.deletionOfResponse();
		
		res2.then().statusCode(204);
		
		String result2 = res2.body().asString();
		
		//verify the deletion
		assertEquals(result2, "");
	}
	
	
}
