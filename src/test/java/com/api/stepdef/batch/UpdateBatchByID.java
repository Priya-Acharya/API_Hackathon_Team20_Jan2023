package com.api.stepdef.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.api.utilities.Batch;
import com.api.utilities.BatchManager;
import com.api.utilities.ConfigProperties;
import com.api.utilities.Program;
import com.api.utilities.ProgramManager;

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
	private static Program program;
	private static Logger logger = LogManager.getLogger(UpdateBatchByID.class);

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = ConfigProperties.getBaseUrl();
		logger.debug("Start create Program");
    	program=ProgramManager.createProgram(ConfigProperties.getProperty("program.name"),
				ConfigProperties.getProperty("program.desc"), ConfigProperties.getProperty("program.status"));
    	logger.debug("End create Program");
	}
	
	 @Given("Batch exists with name {string}, description {string}, status {string} and batchNoOfClasses {string}")
		public void setupABatch(String name, String desc, String status,String batchNoOfClasses ) {
			
	    	logger.debug("Start create Batch");
			batch = BatchManager.createBatch(name, desc, status,batchNoOfClasses,program.getProgramId());
			logger.debug("End create Batch");
		}
	 
	@When("The user makes a PUT request for the batch to update {string}  and {string}")
	public void the_user_makes_a_put_request_for_the_batch_to_update_and(String desc, String status) {
		
		////logger.debug("PUT executing..");

		RequestSpecification request = RestAssured.given();
		JSONObject updatebatch = new JSONObject();// {}
		updatebatch.put("batchId", batch.getBatchId());
		updatebatch.put("batchName", batch.getBatchName());
		updatebatch.put("batchDescription", desc);
		updatebatch.put("batchStatus", status);
		updatebatch.put("batchNoOfClasses", batch.getbatchNoOfClasses());
		updatebatch.put("programId", program.getProgramId());
		updatebatch.put("programName", program.getProgramName());
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


	@AfterAll
	public static void deletebatch() {
		BatchManager.deleteBatch(batch.getBatchId());
		ProgramManager.deleteProgram(program.getProgramId());
		
	}
	

}
