package com.api.stepdefbatch;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;

import com.api.util.ExcelUtil;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateBatch {

	private ExtractableResponse<Response> response;
	private String bName;
	private String Desc;
	private String NoOfClasses;
	private String status;
	private String pId;
	private RequestSpecification req;

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms/";

	}

	public ExcelUtil utility = new ExcelUtil(new File(".").getAbsolutePath() + "/src/test/resources/Data.xlsx");

	@Given("Service request with base url and payload with batchName, batchDescription, batchStatus, batchNoOfClasses, programId")
	public void service_request_with_base_url_and_payload_with_batch_name_batch_description_batch_status_batch_no_of_classes_program_id() {
	 
		Map<String, String> map = new HashMap();
		bName = utility.getCellData("Sheet1", 1, 0);
		Desc = utility.getCellData("Sheet1", 1, 1);
		status = utility.getCellData("Sheet1", 1, 2);
		NoOfClasses = utility.getCellData("Sheet1", 1, 3);
		pId = utility.getCellData("Sheet1", 1, 4);
		map.put("batchName", bName + "-" + System.currentTimeMillis());
		map.put("batchDescription", Desc);
		map.put("batchNoOfClasses", NoOfClasses);
		map.put("batchStatus", status);
		map.put("programId", pId);
		// {"batchName":"Jan23-CRUD Interfacing
		// Ninjas-SDET-20-1673908517901","batchNoOfClasses":"Active","batchDescription":"Python","batchStatus":"12","programId":"1001"}
		ObjectMapper mapper = new ObjectMapper();
		String batchBody = null;
		try {
			batchBody = mapper.writeValueAsString(map);
			req = given().baseUri("https://lms-backend-service.herokuapp.com/lms/")
					.headers("Content-Type", "application/json").body(batchBody);
			System.out.println("Response Body: " + batchBody);

		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}
	}
	
	@When("POST request is made with Endpoint batches")
	public void post_request_is_made_with_endpoint_batches() {

		response = req.when().post("batches").then().log().all().extract();
		// response = RestAssured.given().post("batches");
	}

	@Then("User validate Status and response body with batchID, batchName, batchDescription, batchStatus, batchNoOfClasses, programId, programName")
	public void user_validate_status_and_response_body_with_batch_id_batch_name_batch_description_batch_status_batch_no_of_classes_program_id_program_name() {
		int statusCode = response.statusCode();
		System.out.println("Status Code is : " + statusCode);
		Assert.assertEquals(statusCode, 201);

	}

	@Given("Service request with base url and payload with batchName, batchDescription, batchStatus, batchNoOfClasses, programId forsecondbatch")
	public void service_request_with_base_url_and_payload_with_batch_name_batch_description_batch_status_batch_no_of_classes_program_id_forsecondbatch() {
		Map<String, String> map = new HashMap();
		bName = utility.getCellData("Sheet1", 2, 0);
		Desc = utility.getCellData("Sheet1", 2, 1);
		status = utility.getCellData("Sheet1", 2, 2);
		NoOfClasses = utility.getCellData("Sheet1", 2, 3);
		pId = utility.getCellData("Sheet1", 2, 4);
		map.put("batchName", bName + "-" + System.currentTimeMillis());
		map.put("batchDescription", Desc);
		map.put("batchNoOfClasses", NoOfClasses);
		map.put("batchStatus", status);
		map.put("programId", pId);
		// {"batchName":"Jan23-CRUD Interfacing
		// Ninjas-SDET-20-1673908517901","batchNoOfClasses":"Active","batchDescription":"Python","batchStatus":"12","programId":"1001"}
		ObjectMapper mapper = new ObjectMapper();
		String batchBody = null;
		try {
			batchBody = mapper.writeValueAsString(map);
			req = given().baseUri("https://lms-backend-service.herokuapp.com/lms/")
					.headers("Content-Type", "application/json").body(batchBody);
			System.out.println("Response Body: " + batchBody);

		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}

	}

	@When("POST request is made with Endpoint batches forsecondbatch")
	public void post_request_is_made_with_endpoint_batches_forsecondbatch() {
		response = req.when().post("batches").then().log().all().extract();
	}

	@Then("User validate Status and response body with batchID, batchName, batchDescription, batchStatus, batchNoOfClasses, programId, programName forsecondbatch")
	public void user_validate_status_and_response_body_with_batch_id_batch_name_batch_description_batch_status_batch_no_of_classes_program_id_program_name_forsecondbatch() {		int statusCode = response.statusCode();
		System.out.println("Status Code is : " + statusCode);
		Assert.assertEquals(statusCode, 201);

	}
	
}