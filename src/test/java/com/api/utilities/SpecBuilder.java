package com.api.utilities;

import java.io.IOException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

	public static RequestSpecification req;

	public static RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			req = new RequestSpecBuilder().setBaseUri(Constants.baseUrl)
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;

	}
}
