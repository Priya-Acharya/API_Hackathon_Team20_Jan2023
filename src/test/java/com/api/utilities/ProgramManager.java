package com.api.utilities;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import io.restassured.RestAssured;

public class ProgramManager {
	
	private static Logger logger = LogManager.getLogger(ProgramManager.class);
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	

	public static Program createProgram(String name, String desc, String status) {
		JSONObject program = new JSONObject();//{}
		
		program.put("programName", name + (new Random().nextInt()));
		program.put("programDescription", desc);
		program.put("programStatus", status);
		
		String dateStr = sdf.format(new Date());

		program.put("creationTime", dateStr);
		program.put("lastModTime", dateStr);
		
		logger.debug("Sending {} to {}", program, ConfigProperties.getProgramPath());
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				  .uri(URI.create(ConfigProperties.getBaseUrl()+ConfigProperties.getProgramPath()))
				  .header("Content-Type", "application/json")
				  .POST(HttpRequest.BodyPublishers.ofString(program.toString()))
				  .build();
		HttpResponse resp;
		try {
			resp = client.send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		assertEquals(resp.statusCode(),201);
		
		String respString = resp.body().toString();
		
		logger.debug("Got body {} from post req", respString);

		JSONObject respJson = new JSONObject(respString);
		
		Program prog = new Program();
		prog.setProgramId(respJson.getInt("programId"));
		prog.setProgramName(respJson.getString("programName"));
		return prog;
	}
	
	public static void deleteProgram(int id) {
		String path=ConfigProperties.getDeleteProgramPath();
		RestAssured.given().delete(path+id).then().log().all();
	}
	
}
