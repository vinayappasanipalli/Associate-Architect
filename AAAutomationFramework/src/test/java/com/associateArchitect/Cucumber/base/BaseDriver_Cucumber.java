package com.associateArchitect.Cucumber.base;

import java.io.FileReader;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig;
import com.associateArchitect.Utilities.extentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseDriver_Cucumber implements ITestListener {
	public static WebDriver driver;
	public static Properties appconfig = new Properties();
	public static Properties objrepo = new Properties();
	public static Properties keywordlibrary = new Properties();
	public static FileReader configfile;
	public static FileReader objrepofile;
	public static Logger logger;	
	public static com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig config;  
	
	public static ExtentSparkReporter report = null;
	public static ExtentReports extent =null;
	public ExtentTest test =null;

	
//	
	
	
	public void beforeSuite() throws Exception {
	extentReport extentReport = new extentReport();
	extentReport.intializeReport();
	}
	

	public void afterSuite() throws Exception {
		File htmlFile = new File(extentReport.reportLocation);
		Desktop.getDesktop().browse(htmlFile.toURI());
	 
	
	}
	
	
	public void setup() throws IOException {
		if(driver==null) {
	    configfile = new FileReader(System.getProperty("user.dir") + "//src//test//resources//configfiles//applicationconfiguration.properties");
	    objrepofile = new FileReader(System.getProperty("user.dir") + "//src//test//resources//configfiles//objectrepository.properties");
	    appconfig.load(configfile);
	    objrepo.load(objrepofile);
	    logger = Logger.getLogger("WebAutomation");
	    PropertyConfigurator.configure(System.getProperty("user.dir") + "//src//test//resources//configfiles//log4j.properties");
	    logger.setLevel(Level.DEBUG);
	    config = ConfigFactory.create(FrameworkConfig.class);
	    
		}
	    
		   if (appconfig.getProperty("browser").equalsIgnoreCase("firefox")) {
	        WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();
	        driver.manage().window().maximize();
			 String applicationUrl = config.applicationurl();
			 driver.get(applicationUrl);
	        
	    } else if (appconfig.getProperty("browser").equalsIgnoreCase("chrome")) {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
		 String applicationUrl = config.applicationurl();
			   driver.get(applicationUrl);
	    }
		  
	}
	

public void teardown() throws IOException {
			
	driver.close();
	

		}

			
	public static ExtentReports reportsetup() {
		
		String reportLocation = "src/test/resources/reports/CucumberTestExecutionReport.html";
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
	
	public static void testStepHandle(String teststatus, ExtentTest test, Throwable throwable) {
	    switch (teststatus) {
	        case "FAIL":
	            test.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
	            test.log(Status.FAIL, throwable);

	            if (driver != null) {
	                String screenshot = CommonFunctions.capturescreenshot(driver);
	                if (screenshot != null) {
	                    test.addScreenCaptureFromPath(screenshot);
	                } else {
	                    System.out.println("Failed to capture screenshot. Check capturescreenshot() method implementation.");
	                }

	                driver.quit();
	            }
	            break;

	        case "PASS":
	            test.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
	            break;

	        default:
	            break;
	    }
	}
	
	public void onStart(ITestContext testContext)
	{
		extent = reportsetup();
	}
		
public void onTestSuccess(ITestResult result) {
	
	
		
	
	}
	public void onTestFailure(ITestResult result) {
//		try {
//			test = extent.createTest(result.getName());
//			test.log(Status.FAIL, "Test Case Failed IS" + result.getName());
//			test.log(Status.FAIL, "Testcase Failure Reason" + result.getThrowable());
//			String screenshot = CommonFunctions.capturescreenshot(driver);
//			test.addScreenCaptureFromPath(screenshot);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.err.println("Error during test failure reporting: " + e.getMessage());
//		}

	}
	
	
	public void onTestSkip(ITestResult result) {
		

	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

	
	
}

		
	
	
	




	

	