package utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport_Listeners extends CommonFunctions implements ITestListener {
//public class ExtentReport_Listeners extends TestListenerAdapter{
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	@Override
	public void onStart(ITestContext testContext)
	{
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"//src//test//resources//reports//WebAutomationReport.html");
		htmlReporter.config().setDocumentTitle("WebAutomation Test Report");
		htmlReporter.config().setReportName("WebAutomation Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name","Associate Architect - WebAutomation Test Framework");
		extent.setSystemInfo("Host Name","Localhost");
		extent.setSystemInfo("Enviornment","QA Instance");
		extent.setSystemInfo("User","Vinay");
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		
		try {
			test=extent.createTest(result.getName());
			test.log(Status.PASS,"Test Case PASSED IS " + result.getName());
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onTestFailure(ITestResult result) {

		try {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL,"Test Case Failed IS" + result.getName());
		test.log(Status.FAIL,"Testcase Failure Reason" + result.getThrowable());
		String screenshot = CommonFunctions.capturescreenshot();
		test.addScreenCaptureFromPath(screenshot);
		
		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
		System.err.println("Error during test failure reporting: " +e.getMessage());
		 }
			
			
		}
		
	public void onTestSkip(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.log(Status.SKIP,"Test Case SKIPED IS " + result.getName());
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
