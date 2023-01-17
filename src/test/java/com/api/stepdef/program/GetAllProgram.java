package com.api.stepdef.program;

import org.testng.Assert;

import com.api.util.ConfigProperties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllProgram {

	private Response response;

	@Given("User make a service request with a Base URL")
	public void user_make_a_service_request_with_a_base_url() {

		RestAssured.baseURI = ConfigProperties.getBaseUrl();
	}

	@When("GET http request is made by the User with an endpoint allPrograms")
	public void get_http_request_is_made_by_the_user_with_an_endpoint_allprograms() {
		
		RequestSpecification httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, (ConfigProperties.getAllprogramsPath()));
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response Body:  \n" + responseBody);
	}

	@Then("User validate statuscode and response body as {string}, {string}, {string}, {string}, {string}, {string}")
	public void User_validate_statuscode_and_response_body_as(String string, String string2, String string3,
			String string4, String string5, String string6) {
		
		{
			int statusCode = response.statusCode();
			System.out.println("Status Code is : " + statusCode);
			Assert.assertEquals(statusCode, 200);

		}

	}
}