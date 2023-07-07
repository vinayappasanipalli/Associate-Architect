package com.associateArchitect.Cucumber.base;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

/*****************************************************************************************************
*Class Name : BaseDriver_Cucumber
*Description : This class holds the functions utilized by all Cucumber testcases.Its responsibilities includes 
               loading/reading configurations from properties files,initializing the WebDriver,
               Extent Report, Logger,and Retry Failed test cases, and closing the driver instance           
                 
******************************************************************************************************/

public class BaseDriver_Cucumber implements IRetryAnalyzer,IAnnotationTransformer,ITestListener  {
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

	/*****************************************************************************************************
	*Function Name : setup
	*Description : This function initializes the necessary configurations,loads property files,sets up logging           
	*			   and sets up the WebDriver for either Firefox or Chrome browser based on the configuration	
	                 
	******************************************************************************************************/
		
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
	
	/*****************************************************************************************************
	*Function Name : teardown
	*Description : This function is responsible for closing the current browser and clean up resources           
	*			   after completing the test execution or to prepare for the next test.	
		                 
	******************************************************************************************************/	

public void teardown() throws IOException {
			
	driver.close();
	

		}

/*****************************************************************************************************
*Function Name : Extent Reports
*Description : These functions are used for setting up the ExtentReports framework, handling test step statuses, 
*			   and executing specific actions during different test events like test start, success, failure, 
*              skip, and completion.*                 
******************************************************************************************************/	
			
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


	}
	
	
	public void onTestSkip(ITestResult result) {
		

	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

	
	
	/*****************************************************************************************************
	*Function Name : Retry Analyzer
	*Description : This class servers as a retry analyzer and annotation transformer. The retry() method  
	*              determines whether a test should be retried based on the "retrycount" and "maxcount"              
	*              variables.The transform() method sets the retry analyzer for tests using this class.
	******************************************************************************************************/	
	
	private int retrycount = 0;
	private static final int maxcount = 2;

	@Override
	public boolean retry(ITestResult result) {

		if (retrycount < maxcount) {
			retrycount++;
			return true;
		}
		return false;
	}

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstuctor, Method testMethod) {

		annotation.setRetryAnalyzer(CommonFunctions.class);
	}
	
	
}

		
	
	
	




	

	