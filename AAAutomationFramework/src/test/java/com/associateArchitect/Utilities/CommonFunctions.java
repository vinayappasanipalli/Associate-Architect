package com.associateArchitect.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.ITestAnnotation;

import com.associateArchitect.Cucumber.base.BaseDriver_Cucumber;
import com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig;
import com.associateArchitect.Web.base.BaseDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonFunctions implements IRetryAnalyzer, IAnnotationTransformer, ITestListener {
	public static WebDriver driver;
	public static Logger logger;

	public static String capturescreenshot(WebDriver driver) {
		String screenshotPath="";
		try {
			
		if(driver!=null) {
		Date currentdate = new Date();
		String screenshotfilename = currentdate.toString().replace(" ", "-").replace(":", "-");
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots/" + screenshotfilename + ".png");
		
			FileUtils.copyFile(sourceFile, destFile);
			screenshotPath=destFile.getAbsolutePath();
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotPath;
	}

//	public static void cf_login(String username, String password) throws Throwable {
//
//		cf_webelementclick((objrepo.getProperty("Login_Button")));
//		//cf_webelementfoundwait(objrepo.getProperty("Password"), 30);
//		cf_webelementsendkeys((objrepo.getProperty("Email")), username);
//		cf_webelementsendkeys((objrepo.getProperty("Password")), password);
//		cf_webelementclick((objrepo.getProperty("Login")));
//
//		Thread.sleep(4000);
//
//	}

	public static void cf_webelementclick(String locator) {
		try {

			driver.findElement(By.xpath(locator)).click();
			logger.info("Webelement is clicked");
		} catch (Exception e) {
			logger.info("Webelement is not clicked");
		}

	}

	public static void cf_webelementsendkeys(String locator, String text) {
		driver.findElement(By.xpath(locator)).sendKeys(text);

	}

	public static WebElement cf_explicitwaitForElementPresent(WebDriver driver,By locator,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		//Assert.assertTrue(driver.findElement(locator).isDisplayed());
		return driver.findElement(locator);
	}
	
	public static void cf_explicitwaituntilWebelementVisible(String locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	

	public static void cf_weblinktextclick(String locator) {
		try {
			driver.findElement(By.linkText(locator)).click();
			logger.info("Weblinktext is clicked");
		} catch (Exception e) {
			logger.info("Weblinktext is not clicked");

		}

	}
	
	public static WebElement cf_webelementfoundwait(String locator,long waitTimeSeconds) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(waitTimeSeconds));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		return element;
	}

	@DataProvider(name = "bvttestdata")
	public String[][] gettestdata(Method m) throws Exception {
		String excelSheetName = m.getName();
		File testdataxls = new File(System.getProperty("user.dir") + "//src//test//resources//testdata//testdata.xlsx");
		FileInputStream fis = new FileInputStream(testdataxls);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheetName = wb.getSheet(excelSheetName);
		int totalRows = sheetName.getLastRowNum();
		Row rowcells = sheetName.getRow(0);
		int totalCols = rowcells.getLastCellNum();
		String testData[][] = new String[totalRows][totalCols];
		DataFormatter format = new DataFormatter();
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				testData[i - 1][j] = format.formatCellValue(sheetName.getRow(i).getCell(j));
			}
		}
		return testData;
	}

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

	
	//**---------Extent Report Listener Methods**------------//
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@Override
	public void onStart(ITestContext testContext) {
		htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "//src//test//resources//reports//AutomationReport.html");
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("Automation Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "Associate Architect - Automation Test Framework");
		extent.setSystemInfo("Host Name", "Localhost");
		extent.setSystemInfo("Enviornment", "QA Instance");
		extent.setSystemInfo("User", "Vinay");
		

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		try {
			test = extent.createTest(result.getName());
			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {

		try {
			test = extent.createTest(result.getName());
			test.log(Status.FAIL, "Test Case Failed IS" + result.getName());
			test.log(Status.FAIL, "Testcase Failure Reason" + result.getThrowable());
			String screenshot = CommonFunctions.capturescreenshot(driver);
			test.addScreenCaptureFromPath(screenshot);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error during test failure reporting: " + e.getMessage());
		}

	}

	public void onTestSkip(ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case SKIPED IS " + result.getName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

	//***** RestAPI Functions***/
	
	public static String empName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return("infy"+generatedString);
	}
	public static String empSal() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return("sal"+generatedString);
	}
		
		public static String empAge() {
			String generatedString = RandomStringUtils.randomNumeric(2);
			return("age"+generatedString);
		}
		
		//** Function to call and read the environment variable from config file**//
	
		@Config.LoadPolicy(Config.LoadType.MERGE)
		@Config.Sources({
		//"system.properties",
		//"System.env",
		//"classpath:configfiles/applicationconfiguration.properties",
		"file:${user.dir}/src/test/resources/configfiles/applicationconfiguration.properties"})
       // "file:/src/test/resources/configfiles/applicationconfiguration.properties"})
        //"file:C:\\Users\\BSA\\git\\Associate-Architect\\AAAutomationFramework\\src\\test\\resources\\configfiles\\applicationconfiguration.properties"})
		public static interface FrameworkConfig extends Config{
		
			@Key("browser")
			String browser();
			@Key("environment")
			String environment();
			//String username();
			@Key("${environment}.applicationurl")
			String applicationurl();
			
		
		}
}
	