package com.api.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty", "html:target/cucumberTestNG.html", "json:target/cucumber.json",
		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" }, 
				monochrome = true,
				publish = true, features = { "src/test/resources/features/Batch/createbatch.feature" }, 
                 glue = "com.api.stepdef")

public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
