package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseDriver {
	public static WebDriver driver;
	public static Properties appconfig = new Properties();
	public static Properties objrepo = new Properties();
	public static Properties keywordlibrary = new Properties();
	public static FileReader configfile;
	public static FileReader objrepofile;
	public static Logger logger;

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
		}
		if (appconfig.getProperty("browser").equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(appconfig.getProperty("applicationurl"));

		} else if (appconfig.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(appconfig.getProperty("applicationurl"));
		}
	}

	@AfterMethod
	public void teardown() throws IOException {
		driver.close();
		

	}
}
