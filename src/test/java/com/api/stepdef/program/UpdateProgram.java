package com.api.stepdef.program;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.api.utilities.ConfigProperties;
import com.api.utilities.Program;
import com.api.utilities.ProgramManager;

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

public class UpdateProgram {

	private static Program program;
	private Response responsebody;
	private ValidatableResponse response;
	private static Logger logger = LogManager.getLogger(GetProgramById.class);

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = ConfigProperties.getBaseUrl();
		program = ProgramManager.createProgram(ConfigProperties.getProperty("program.name"),
				ConfigProperties.getProperty("program.desc"), ConfigProperties.getProperty("program.status"));
	}

	@Given("A Program exists")
	public void a_program_exists() {
		logger.debug("Background executing..");
		//RestAssured.given().get(ConfigProperties.getProgramsPath()+program.getProgramId()).then().statusCode(200);
	}

	@When("The user makes a PUT request for the program to update {string} and {string}")
	public void the_user_makes_a_put_request_for_the_program_to_update_and(String desc, String status) {
		
		logger.debug("PUT executing..");

		RequestSpecification request = RestAssured.given();
		JSONObject updateprogram = new JSONObject();// {}

		updateprogram.put("programName", program.getProgramName());
		updateprogram.put("programDescription", desc);
		updateprogram.put("programStatus", status);
		String dateStr = ProgramManager.sdf.format(new Date());
		updateprogram.put("creationTime", dateStr);
		updateprogram.put("lastModTime", dateStr);
		responsebody = request.contentType(ContentType.JSON).body(updateprogram.toString())
				.put(ConfigProperties.getUpdatebyIdPath() + program.getProgramId());

	}

	@Then("Validate the status code and the updated program details as {string} and {string}")
	public void validate_the_status_code_and_the_updated_program_details_as(String desc,String status) {
		responsebody.then()//
				.assertThat()//
				.statusCode(200)//
				.body("programId", Matchers.equalTo(program.getProgramId())).body("programName", Matchers.equalTo(program.getProgramName()))
				.body("programDescription", Matchers.equalTo(desc))
				.body("programStatus", Matchers.equalTo(status))//
				.log().all();
	}

	@When("The user makes a PUT request for the program to update {string},{string} and {string}")
	public void the_user_makes_a_put_request_for_the_program_to_update_and(String name, String desc, String progstatus) {
		RequestSpecification request = RestAssured.given();
		JSONObject updateprogram = new JSONObject();// {}

		updateprogram.put("programName", name);
		updateprogram.put("programDescription", desc);
		updateprogram.put("programStatus", progstatus);
		String dateStr = ProgramManager.sdf.format(new Date());
		updateprogram.put("creationTime", dateStr);
		updateprogram.put("lastModTime", dateStr);
		response = request.contentType(ContentType.JSON).body(updateprogram.toString())
				.put(ConfigProperties.getUpdatebyNamePath() + program.getProgramName()).then().log().all();

	}

	@Then("Validate the status code and the updated program details as {string},{string} and {string}")
	public void validate_the_status_code_and_the_updated_program_details_as(String name, String desc, String progstatus) {
		response
				.assertThat()//
				.statusCode(200)//
				.body("programId", Matchers.equalTo(program.getProgramId()))
				.body("programName", Matchers.equalTo(name))
				.body("programDescription", Matchers.equalTo(desc)).body("programStatus", Matchers.equalTo(progstatus))//
				.log().all();
	}

	@AfterAll
	public static void deleteProg() {
		ProgramManager.deleteProgram(program.getProgramId());
	}

}
