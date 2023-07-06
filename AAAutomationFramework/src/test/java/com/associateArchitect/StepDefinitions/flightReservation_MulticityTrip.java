package com.associateArchitect.StepDefinitions.cucumber;

import java.io.FileReader;
import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.associateArchitect.Cucumber.base.baseDriver_Cucumber;
import com.associateArchitect.Pages.makemytripHomepage;
import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;


public class flightReservation_MulticityTrip extends baseDriver_Cucumber {
	public static com.associateArchitect.Utilities.CommonFunctions commonfns;
	public static WebDriver driver;
	makemytripHomepage mTH;

	@Before
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
			driver.manage().window().maximize();
			String applicationUrl = config.applicationurl();
			driver.get(applicationUrl);
		}
	}

	@Given("User is on the flight booking page for the multicity trip")
	public void userIsOnTheFlightBookingPage() {
		mTH = new makemytripHomepage(driver);
		mTH.waitforElementPresent();
 	}

	@When("User clicks on the multi city radio button")
	public void userClicksOnRoundTripRadioButton() {
		mTH.clickroundtripRadiobutton();
	}

	@Then("Multi city radio button should be selected")
	public void roundTripRadioButtonShouldBeSelected() {
		boolean actualValueRound = mTH.isroundtripRadiobuttonSelected();
		boolean expectedValueRound = true;
		Assert.assertEquals(expectedValueRound, actualValueRound);
		logger.info("One Way Radio button is clicked: " + actualValueRound);
	}

	@Then("A screenshot is taken when multcity trip radio button is selected")
	public void takeScreenshotWhenRoundTripRadioButtonIsSelected() {
		boolean actualValueRound = mTH.isroundtripRadiobuttonSelected();
		if (actualValueRound) {
			String ScreenshotPath = CommonFunctions.capturescreenshot(driver);
			logger.info("Screenshot taken when Round Way radio button is selected.");
		}
	}
		@When("User randomly selects a From Place for multicity trip")
		 public void userRandomlySelectsFromPlace() {
			mTH.fromSelectionClick();			
	       mTH.userRandomlySelectsFromPlace();
	       logger.info("User Selected Random value from the From dropdown");			
	        
	    }
		@And("User randomly selects a To Place for a multicity trip")
		 public void userRandomlySelectsToPlace() {
			mTH.toSelectionClick();			
	       mTH.userRandomlySelectsToPlace();
	       logger.info("User Selected Random value from the To dropdown");			
	        
	    }

	
		
		  @When("User select the flight with the lowest price for a multicity trip")
		    public void selectFlightWithLowestPrice() {
			  mTH.selectFlightWithLowestPrice();
			 
			  logger.info("User select the flight with the lowest price");	
			 
			  
		  }
		  
		  @Then("User selects the random number of travelers and clicks Apply for a multicity trip")
		  public void selectRandomNumberofTravellers(){
			  mTH.clickTravellers();
			  mTH.clickNoofAdults();
			  mTH.clickNoofChildren();
			 mTH.clickTravellersApply();
			 
		  }
		  
		  @Then("User performs Search and taken a screenshot of the results for a multicity trip")
		  public void performFlightSearch(){
			//mTH.selectFairTye();
			mTH.clickFlightSearchButton();
			CommonFunctions.capturescreenshot(driver);
		  }
	 



	@After
	public void teardown() throws IOException {
		driver.close();
	}

	}


