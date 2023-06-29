package com.associateArchitect.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


@RunWith(Cucumber.class) 
	@CucumberOptions(
			
			tags="@makeMyTrip_OnewayTrip_TC1 or @makeMyTrip_RoundTrip_TC2 or @makeMyTrip_MultiwayTrip_TC3",
			features="./src/test/resources/featureFiles_Cucumber",
			glue={"com.associateArchitect.StepDefinitions.cucumber"},
			plugin = {"summary","pretty","html:src/test/resources/reports.html",
					"json:src/test/resources/reports.html",
			//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
			"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					},	
     		monochrome=true	
			)
public class testRunner {
//
}

//public class testRunner extends AbstractTestNGCucumberTests {
////		//private TestNGCucumberRunner testNGCucumberRunner;
////		
////		
////		
//}
