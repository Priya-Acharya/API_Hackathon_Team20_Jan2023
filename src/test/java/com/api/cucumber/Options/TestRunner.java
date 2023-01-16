package com.api.cucumber.Options;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(plugin = {"pretty", "html:target/cucumberTestNG.html","json:target/cucumber.json"},
				//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter"},
features = {"src/test/resources/features/program/"},glue= {"com.api.stepDefProgram","com.api.stepDefBatch"},monochrome=true)
public class TestRunner extends AbstractTestNGCucumberTests{
	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios()
    {
        return super.scenarios();
    }

}


 
