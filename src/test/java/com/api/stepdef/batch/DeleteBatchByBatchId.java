package com.api.stepdef.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.api.utilities.Batch;
import com.api.utilities.BatchManager;
import com.api.utilities.ConfigProperties;
import com.api.utilities.Program;
import com.api.utilities.ProgramManager;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBatchByBatchId {
	
	private static Batch batch;
	private static Program program;
	private Response vResp;
	private static Logger logger = LogManager.getLogger(GetBatchByBatchName.class);
	
	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = ConfigProperties.getBaseUrl();
		logger.debug("Start create Program");
    	program=ProgramManager.createProgram(ConfigProperties.getProperty("program.name"),
				ConfigProperties.getProperty("program.desc"), ConfigProperties.getProperty("program.status"));
    	logger.debug("End create Program");
	}
	
	@Given("A batch with batch ID exists")
	public void a_batch_with_batch_id_exists() {
		logger.debug("Start create Batch");
		batch = BatchManager.createBatch("TestBatch", "ForDelete", "InActive","13"
				,program.getProgramId());
		logger.debug("End create Batch");
	}

	@When("Delete the batch for the given batch Id")
	public void delete_the_batch_for_the_given_batch_id() {
		String path=ConfigProperties.getDeleteBatchPath();
		vResp = RestAssured.given().when().delete(path+batch.getBatchId());
	}

	@Then("The batch should be deleted successfully")
	public void the_batch_should_be_deleted_successfully() {
		Assert.assertEquals(vResp.getBody().asString(), "Message: Batch with Id-"+ batch.getBatchId() + " deleted Successfully!");
	}

}
