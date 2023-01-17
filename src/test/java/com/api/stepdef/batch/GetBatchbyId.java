package com.api.stepdef.batch;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;

import com.api.utilities.ConfigProperties;
import com.api.utilities.ExcelUtil;

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

public class GetBatchbyId {
	private ExtractableResponse<Response> response;
	private String bName;
	private String Desc;
	private String NoOfClasses;
	private String status;
	private String pId;
	private int bId;
	private RequestSpecification req;
	private Response getResponse;
	private String batchName;

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = ConfigProperties.getBaseUrl();

	}

	public ExcelUtil utility = new ExcelUtil(new File(".").getAbsolutePath() + "/src/test/resources/Data.xlsx");

	@Given("uSer make a service request with a Base URL")
	public void u_ser_make_a_service_request_with_a_base_url() {
		
		Map<String, String> map = new HashMap();
		bName = utility.getCellData("Sheet1", 1, 0);
		Desc = utility.getCellData("Sheet1", 1, 1);
		status = utility.getCellData("Sheet1", 1, 2);
		NoOfClasses = utility.getCellData("Sheet1", 1, 3);
		pId = utility.getCellData("Sheet1", 1, 4);
		batchName = bName + "-" + System.currentTimeMillis();
		map.put("batchName", batchName);
		map.put("batchDescription", Desc);
		map.put("batchNoOfClasses", NoOfClasses);
		map.put("batchStatus", status);
		map.put("programId", pId);
		ObjectMapper mapper = new ObjectMapper();
		String batchBody = null;
		try {
			batchBody = mapper.writeValueAsString(map);
			req = given().baseUri("https://lms-backend-service.herokuapp.com/lms/")
					.headers("Content-Type", "application/json").body(batchBody);
			response = req.when().post(ConfigProperties.createBatchPath()).then().log().all().extract();
			String resString = response.body().asString();
			//System.out.println("Response Body: " + batchBody);
			JSONObject respJson = new JSONObject(resString);
			bId = respJson.getInt("batchId");

		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}

	}

	@When("GET Request is made by the User with an endpoint batches slash {string}")
	public void get_request_is_made_by_the_user_with_an_endpoint_batches_slash(String string) {

		getResponse = RestAssured.given().get(ConfigProperties.getBatchbyIdPath() + bId);
	}

	@Then("User validate Statuscode with response body as {string}, {string}, {string}, {string}, {string}, {string}, {string}")
	public void User_validate_Statuscode_with_response_body_as(String string, String string2, String string3,
			String string4, String string5, String string6, String string7) {
		int statusCode = response.statusCode();
		System.out.println("Status Code is : " + statusCode);
		Assert.assertEquals(statusCode, 201);
		getResponse.then()
		    .assertThat()
		    .statusCode(200)
		    .body("batchId", Matchers.equalTo(bId))
		    .body("batchName", Matchers.equalTo(batchName))
		    .body("batchDescription", Matchers.equalTo(Desc))
		    .body("batchStatus", Matchers.equalTo(status))
		    .body("programId", Matchers.equalTo(Integer.parseInt(pId)))
		    .log().all()
		    ;
	}
}