package com.api.stepdef.program;



import static io.restassured.RestAssured.given;

import org.hamcrest.core.StringContains;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;

import com.api.utilities.DataPayload;
import com.api.utilities.Program;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import static com.api.utilities.SpecBuilder.requestSpecification;
import static com.api.utilities.Constants.*;

public class SaveProgram {
	private RequestSpecification reqValid;
	private RequestSpecification reqNull;
	private RequestSpecification reqEmpty;
	private ExtractableResponse<Response> responseValid;
	private Response responseNull;
	private Response responseEmpty;
	private Program program;
	
	@Given("User make a service request with a Base URL request alongwith metadata information in header as Application Json")
	public void user_make_a_service_request_with_a_base_url_request_alongwith_metadata_information_in_header_as_application_json() {
		 
		System.out.println("base URL is visible");
		
	    
	}

	@Given("a payload consist of programName, ProgramDescription, programStatus, creationTime, lastModTime")
	public void a_payload_consist_of_program_name_program_description_program_status_creation_time_last_mod_time() throws IOException {
		
		reqValid=given().spec(requestSpecification())
				.body(DataPayload.getSaveData());
		
	}

	@When("POST request is made by the User with an endpoint saveprogram")
	public void post_request_is_made_by_the_user_with_an_endpoint_saveprogram() {
		responseValid = reqValid.when().post(createURI).then().log().all().extract();
	}

	@Then("User validate Status code as twohundredone")
	public void user_validate_status_code_as_twohundredone() {
		Assert.assertEquals(responseValid.statusCode(), 201);
	}

	@Then("response body has ProgramId, programName, programDescription, programStatus, creationTime, lastModTime")
	public void response_body_has_program_id_program_name_program_description_program_status_creation_time_last_mod_time() {
//		System.out.println("**************** " + responseEmpty.body().asPrettyString());
		
		String responseBody = responseValid.body().asString();
		ObjectMapper mapper = new ObjectMapper();
		
		if (responseValid.statusCode() == 201) {
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
			System.out.println(responseValid.body().asString());
		}
	}

	@Given("a payload consist of programName as null value, ProgramDescription, programStatus, creationTime, lastModTime")
	public void a_payload_consist_of_program_name_as_null_value_program_description_program_status_creation_time_last_mod_time() throws IOException {
		
		reqNull=given().spec(requestSpecification())
	    		.body("{ \"programDescription\": \"Master PostgreSql4\",\r\n"
	    				+ " \"programStatus\": \"Active\",\r\n"
	    				+ " \"creationTime\": \"2023-01-07T04:13:00.000+00:00\",\r\n"
	    				+ " \"lastModTime\": \"2023-01-07T04:13:00.000+00:00\"\r\n"
	    				+ "}\r\n"
	    				+ "");	  
	    		
	    		
	}
	
	@When("POST request is made by the User with an endpoint saveprogram with null value")
	public void post_request_is_made_by_the_user_with_an_endpoint_saveprogram_with_null_value() {
		responseNull = reqNull.when().post(createURI);
	}


	@Then("User validate Status code as fourhundred")
	public void user_validate_status_code_as_fourhundred() {
		Assert.assertEquals(responseNull.statusCode(), 400);
	}

	@Then("response body has error message must not be null")
	public void response_body_has_error_message_must_not_be_null() {
		
		JsonPath path = responseNull.body().jsonPath();
		Assert.assertEquals(path.get("errorCode"),"VALIDATION_ERROR");
		
	}

	@Given("a payload consist of programName with empty data, ProgramDescription, programStatus, creationTime, lastModTime")
	public void a_payload_consist_of_program_name_with_empty_data_program_description_program_status_creation_time_last_mod_time() throws IOException {
		reqEmpty=given().spec(requestSpecification())
	    		.body("{\r\n"
	    				+ " \"programName\": \"   \",\r\n"
	    				+ " \"programDescription\": \"Team-20\",\r\n"
	    				+ " \"programStatus\": \"Active\",\r\n"
	    				+ " \"creationTime\": \"2023-01-07T04:13:00.000+00:00\",\r\n"
	    				+ " \"lastModTime\": \"2023-01-07T04:13:00.000+00:00\"\r\n"
	    				+ "}\r\n"
	    				+ "");	
	}
	
	@When("POST request is made by the User with an endpoint saveprogram with empty value")
	public void post_request_is_made_by_the_user_with_an_endpoint_saveprogram_with_empty_value() {
		responseEmpty = reqEmpty.when().post(createURI);
	}
	
	@Then("User validate Status code as FourHundred")
	public void user_validate_status_code_as_FourHundred() {
		Assert.assertEquals(responseEmpty.statusCode(), 400);
	}

	@Then("response body has error message programe name must not be empty string")
	public void response_body_has_error_message_programe_name_must_not_be_blank() {
		JsonPath path = responseEmpty.body().jsonPath();
		Assert.assertEquals(path.get("errorCode"),"VALIDATION_ERROR");
	}


}


