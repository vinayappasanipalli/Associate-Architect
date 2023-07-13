package com.associateArchitect.Utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class extentReportListener extends TestListenerAdapter {
	
	public static ExtentSparkReporter report = null;
	public static ExtentReports extent =null;
	public ExtentTest test =null;
	
	@BeforeSuite
	public static ExtentReports setup() {
		
		String reportLocation = "./Reports/CucumberTestExecutionReport.html";
		report = new ExtentSparkReporter(reportLocation);
		report.config().setDocumentTitle("Cucumber Automation Test Execution Report");
		report.config().setReportName("Automation Test Report");
		report.config().setTheme(Theme.DARK);
		//report.start(extent);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "Associate Architect - Automation Test Framework");
		extent.setSystemInfo("Host Name", "Localhost");
		extent.setSystemInfo("Enviornment", "QA Instance");
		extent.setSystemInfo("User", "Vinay");
		//report.start(extent);
		return extent;
	
	}
	
	public static void testStepHandle(String teststatus,WebDriver driver,ExtentTest test,Throwable throwable1) {
		switch(teststatus) {
		case "FAIL":
			test.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
			//test.error(throwable1.fillInStackTrace()

			
			if (driver!=null) {
				driver.quit();
			}
			break;
		case "PASS":
			test.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
			default:
				break;
		}
			
			
		
		}
	
	public void onStart(ITestContext testContext)
	{
		extent = setup();
	}
		
public void onTestSuccess(ITestResult result) {
		
	
	}
	public void onTestFailure(ITestResult result) {
	
	}
	public void onTestSkip(ITestResult result) {
		

	}
	
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}

		
	
	
	


