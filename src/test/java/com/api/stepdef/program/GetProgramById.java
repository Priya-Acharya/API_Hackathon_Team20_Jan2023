package com.api.stepdef.program;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;

import com.api.util.ConfigProperties;
import com.api.util.ProgramManager;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetProgramById{
	
	private static int pId;
	private Response vResp;
	private static Logger logger = LogManager.getLogger(GetProgramById.class);
	
	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = ConfigProperties.getBaseUrl();
	}
	
	@Given("Program with name {string}, decription {string} and status {string} exists")
	public void setupAProgram(String name, String desc, String status) {
		pId = ProgramManager.createProgram(name, desc, status).getProgramId();
	}

	@When("The user makes a GET request for the program")
	public void readProgram() {
	    vResp = RestAssured.given().get(ConfigProperties.getProgramsPath()+pId);
	}

	@Then("The returned program has name as {string}, decription as {string} and status as {string}")
	public void validateResponse(String name, String description, String status) {
	    vResp.then()//
	    .assertThat()//
	    .statusCode(200)//
	    .body("programId", Matchers.equalTo(pId))
	    .body("programName", Matchers.equalTo(name))
	    .body("programDescription", Matchers.equalTo(description))
	    .body("programStatus", Matchers.equalTo(status))//
	    .log().all()
	    ;
	}

	@AfterAll
	public static void deleteProg() {
		ProgramManager.deleteProgram(pId);
	}

}
