package com.api.stepDefBatch;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.JSONObject;

//import com.api.stepdef.program.GetProgramById;
import com.api.util.Batch;
import com.api.util.BatchManager;
import com.api.util.ConfigProperties;

//import com.api.util.ProgramManager;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class UpdateBatchByID {
	private static Batch batch;
	private Response responsebody;
	private ValidatableResponse response;
	////private static Logger logger = LogManager.getLogger(GetBatchById.class);

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = ConfigProperties.getBaseUrl();
		batch = BatchManager.createBatch(ConfigProperties.getProperty("batchname"),
				ConfigProperties.getProperty("batchdesc"), ConfigProperties.getProperty("batchstatus"));
	}

	@Given("A Batch exists")
	public void a_batch_exists() {
		////logger.debug("Background executing..");
		//RestAssured.given().get(ConfigProperties.getProgramsPath()+program.getProgramId()).then().statusCode(200);
	}

	@When("The user makes a PUT request for the batch to update {string}  and {string}")
	public void the_user_makes_a_put_request_for_the_batch_to_update_and(String desc, String status) {
		
		////logger.debug("PUT executing..");

		RequestSpecification request = RestAssured.given();
		JSONObject updatebatch = new JSONObject();// {}

		updatebatch.put("batchName", batch.getBatchName());
		updatebatch.put("batchDescription", desc);
		updatebatch.put("batchStatus", status);
		String dateStr = BatchManager.sdf.format(new Date());
		updatebatch.put("creationTime", dateStr);
		updatebatch.put("lastModTime", dateStr);
		responsebody = request.contentType(ContentType.JSON).body(updatebatch.toString())
				.put(ConfigProperties.getUpdatebyIdPath() + batch.getBatchId());

	}

	@Then("Validate the status code and the updated batch details as {string} and {string}")
	public void validate_the_status_code_and_the_updated_batch_details_as(String desc,String status) {
		responsebody.then()//
				.assertThat()//
				.statusCode(200)//
				.body("batchId", Matchers.equalTo(batch.getBatchId())).body("batchName", Matchers.equalTo(batch.getBatchName()))
				.body("batchDescription", Matchers.equalTo(desc))
				.body("batchStatus", Matchers.equalTo(status))//
				.log().all();
	}

	/*@When("The user makes a PUT request for the batch to update {string},{string} and {string}")
	public void the_user_makes_a_put_request_for_the_batch_to_update_and(String name, String desc, String bstatus) {
		RequestSpecification request = RestAssured.given();
		JSONObject updatebatch = new JSONObject();// {}

		updatebatch.put("batchName", name);
		updatebatch.put("batchDescription", desc);
		updatebatch.put("batchStatus", bstatus);
		String dateStr = BatchManager.sdf.format(new Date());
		updatebatch.put("creationTime", dateStr);
		updatebatch.put("lastModTime", dateStr);
		response = request.contentType(ContentType.JSON).body(updatebatch.toString())
				.put(ConfigProperties.getUpdatebyNamePath() + batch.getBatchName()).then().log().all();

	}

	@Then("Validate the status code and the updated batch details as {string},{string} and {string}")
	public void validate_the_status_code_and_the_updated_batch_details_as(String name, String desc, String bstatus) {
		response
				.assertThat()//
				.statusCode(200)//
				.body("batchId", Matchers.equalTo(batch.getBatchId()))
				.body("batchName", Matchers.equalTo(name))
				.body("batchDescription", Matchers.equalTo(desc)).body("batchStatus", Matchers.equalTo(bstatus))//
				.log().all();
	}*/

	@AfterAll
	public static void deleteBatch() {
		BatchManager.deleteBatch(batch.getBatchId());
	}

	

}
