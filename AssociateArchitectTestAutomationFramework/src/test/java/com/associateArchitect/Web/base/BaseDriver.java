package com.associateArchitect.Web.base;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseDriver {
	public static WebDriver driver;
	public static Properties appconfig = new Properties();
	public static Properties objrepo = new Properties();
	public static Properties keywordlibrary = new Properties();
	public static FileReader configfile;
	public static FileReader objrepofile;
	public static Logger logger;
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "19"; // Hard coded value - Input for getting details of single employee & update employee
	
	public static com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig config;  
	@BeforeMethod	
	public void setup() throws IOException {
		if (driver == null) {
			configfile = new FileReader(System.getProperty("user.dir")
					+ "//src//test//resources//configfiles//applicationconfiguration.properties");
			objrepofile = new FileReader(System.getProperty("user.dir")
					+ "//src//test//resources//configfiles//objectrepository.properties");
			appconfig.load(configfile);
			objrepo.load(objrepofile);
			logger = Logger.getLogger("WebAutomation");// added Logger
			PropertyConfigurator.configure(
					System.getProperty("user.dir") + "//src//test//resources//Configfiles//log4j.properties");// added
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
		
			String applicationUrl = config.applicationurl();
			driver.get(applicationUrl);
		}
	}

	@AfterMethod
	//@After
	 public void teardown() throws IOException {
      driver.close();
            
		}
}
