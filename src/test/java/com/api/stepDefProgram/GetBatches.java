package com.api.stepDefProgram;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;

public class GetBatches {
	Response response;
	@Given("User request for a service with base URL  along with metadata information in header")
	public void user_request_for_a_service_with_base_url_along_with_metadata_information_in_header() {
		 RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";

	}

	@When("GET request is made by the user with an endpoint {string}")
	public void get_request_is_made_by_the_user_with_an_endpoint(String string) {
	response=given().when().get(string);
	response.then().log().all();
	
	
	}

	@Then("User get the status code as {string} and validate response body")
	public void user_get_the_status_code_as_and_validate_response_body(String string) {
		
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine,string);
			      
	}
}
