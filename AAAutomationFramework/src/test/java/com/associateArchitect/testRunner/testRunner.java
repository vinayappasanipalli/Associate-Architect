package com.associateArchitect.testRunner;

/*****************************************************************************************************
*Class Name   : testRunner
**Description : This class serves as the test execution entry point for running Cucumber scenarios. 
*               It configures the test options, glues the step definitions, sets up hooks 
*               for setup/teardown operations, and generates test reports using the specified plugins.       
 ******************************************************************************************************/


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig;

import io.cucumber.java.After;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
//@RunWith(Cucumber.class) 
@CucumberOptions(
		
		tags="@roundtripTC1 or @onewaytripTC2 or @multitripTC3",
		//tags="@parallelExecution",
		features="./src/test/resources/featureFiles",
		glue={"com.associateArchitect.StepDefinitions"},
		plugin = {"summary","pretty",
				//"json:src/test/resources/reports.html",
		//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				},	
 		monochrome=true	
		)

public class testRunner extends AbstractTestNGCucumberTests{
	//private TestNGCucumberRunner testNGCucumberRunner;
	
//@Override
//@DataProvider(parallel=true)
//public Object[][] scenarios(){
//return super.scenarios();
//	
//}
	
}
	
