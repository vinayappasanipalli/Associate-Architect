package com.associateArchitect.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReport {
	
	public static ExtentSparkReporter report = null;
	public static ExtentReports extent =null;
	public static String reportLocation;
	public ExtentTest test =null;
		
public  extentReport() {
		
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
}

public void intializeReport() {
	extentReport report = new extentReport();
}

}


