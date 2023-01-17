package com.api.utilities;

	import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import io.restassured.RestAssured;

	public class BatchManager {
		
		private static Logger logger = LogManager.getLogger(BatchManager.class);
		public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		

		public static Batch createBatch(String name, String desc, String status,String batchNoOfClasses,int pId) {
			JSONObject batch = new JSONObject();//{}
			
			batch.put("batchName", "B"+name);
			batch.put("batchDescription", desc);
			batch.put("batchStatus", status);
			batch.put("batchNoOfClasses", batchNoOfClasses);
			batch.put("programId",pId );
			
//			String dateStr = sdf.format(new Date());
	//
//			batch.put("creationTime", dateStr);
//			batch.put("lastModTime", dateStr);
			
			logger.debug("Sending {} to {}", batch, ConfigProperties.createBatchPath());
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(URI.create(ConfigProperties.getBaseUrl()+ConfigProperties.createBatchPath()))
					  .header("Content-Type", "application/json")
					  .POST(HttpRequest.BodyPublishers.ofString(batch.toString()))
					  .build();
			HttpResponse resp;
			try {
				resp = client.send(request, BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			assertEquals(resp.statusCode(),201);
			
			String respString = 		resp.body().toString();
			
			logger.debug("Got body {} from post req", respString);

			JSONObject respJson = new JSONObject(respString);
			
			Batch bat = new Batch();
			bat.setBatchId(respJson.getInt("batchId"));
			bat.setBatchName(respJson.getString("batchName"));
			return bat;
		}
		
		public static void deleteBatch(int id) {
			String path=ConfigProperties.getDeleteBatchPath();
			RestAssured.given().delete(path+id).then().log().all();
		}
}
