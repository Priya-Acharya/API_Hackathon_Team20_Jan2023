package com.api.stepdef.program;

import static com.api.utilities.Constants.createURI;
import static com.api.utilities.Constants.deleteByIdURI;
import static com.api.utilities.SpecBuilder.requestSpecification;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;

import com.api.utilities.DataPayload;
import com.api.utilities.Program;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import com.api.utilities.Program;

import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import static com.api.utilities.SpecBuilder.requestSpecification;
import static com.api.utilities.Constants.*;

public class DeleteByProgramName {

	
	public static RequestSpecification req;
	Response response;
	public Program program;
	
	@Given("User make a service request with BaseURI")
	public void user_make_a_service_request_with_base_uri() throws IOException {
		req=given().spec(requestSpecification())
						.body(DataPayload.getSaveData());
		response = req.when().post(createURI);
		String responseBody = response.getBody().asString();
		ObjectMapper mapper = new ObjectMapper();
		
		if (response.statusCode() == 201) {
			try {
				program = mapper.readValue(responseBody, Program.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Name: " + program.getProgramName());
			System.out.println("Id: " + program.getProgramId());
		} else {
			System.out.println(response.getBody().asString());
		}
		
	   
	}

	@When("User make a DELETE http request with resource as deletebyprogramName")
	public void user_make_a_delete_http_request_with_resource_as_deletebyprogram_name() throws IOException {
		response = null;
		req=given().spec(requestSpecification());

		response = req.when().delete(deleteByNameURI,program.getProgramName());
	  
	}

	@Then("User validate response as Message: Program with name ProgramName deleted Successfully!")
	public void user_validate_response_as_message_program_with_name_program_name_deleted_successfully() {
		Assert.assertEquals(response.getBody().asString(), "Program with Name-" + program.getProgramName() + " deleted Successfully!");
		
	}

	@Then("status code as twohundred OK")
	public void status_code_as_twohundred_ok() {
		Assert.assertEquals(response.statusCode(), 200);
	   
	}
}
