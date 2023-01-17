package com.api.stepdef.batch;

	import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.utilities.Batch;
import com.api.utilities.BatchManager;
import com.api.utilities.ConfigProperties;
import com.api.utilities.Program;
import com.api.utilities.ProgramManager;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

	public class GetBatchByBatchName{
		
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
		

	    @Given("Batch with name {string}, description {string}, status {string} and batchNoOfClasses {string} exists")
		public void setupABatch(String name, String desc, String status,String batchNoOfClasses ) {
			
	    	logger.debug("Start create Batch");
			batch = BatchManager.createBatch(name, desc, status,batchNoOfClasses,program.getProgramId());
			logger.debug("End create Batch");
		}

		@When("The user makes a GET request for the Batch by Batch Name")
		public void readBatchByName() {
		    vResp = RestAssured.given().get(ConfigProperties.getBatchNameUrlPath()+batch.getBatchName());
		}

		@Then("The returned Batch has name as {string}, description as {string} and status as {string}")
		public void validateResponse(String name, String description, String status) {
		    vResp.then()
		    .assertThat()
		    .statusCode(200)
//		    .body("programId", Matchers.equalTo(pId))
//		    .body("programName", Matchers.equalTo(name))
//		    .body("programDescription", Matchers.equalTo(description))
//		    .body("programStatus", Matchers.equalTo(status))
		    .log().all()
		    ;
		}

		@AfterAll
		public static void deletebatch() {
			BatchManager.deleteBatch(batch.getBatchId());
			ProgramManager.deleteProgram(program.getProgramId());
			
		}

	}

