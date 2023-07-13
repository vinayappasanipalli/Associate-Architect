package com.associateArchitect.StepDefinitions;

import java.time.Duration;

import com.associateArchitect.Pages.makemytripHomepage;
import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.base.BaseDriver;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;

/*****************************************************************************************************
*Class Name : roundTripReservation
*Description : This class extends BaseDriver_Cucumber class. This class represents the 
*              step definitions for a Cucumber scenario related to round-way trip reservation on the
*               Make my-trip web-site. It interacts with page objects, performs actions, 
*               handles assertions, captures screenshots, and utilizes reporting for test execution.       
 ******************************************************************************************************/
public class roundTripReservation extends BaseDriver {
	makemytripHomepage mTH;

	@Given("User Login to MakemyTrip")
	public void login() throws Exception {
		setup();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ExtentTest logInfo = null;
		try {
			test = extent.createTest(Feature.class, "Flight Booking for Roundtrip Trip");
			test = test.createNode(Scenario.class, "Make a RoundTrip flight reservation");
			logInfo = test.createNode(new GherkinKeyword("Then"),
					"User is on the flight booking page to make oneway trip");
			mTH = new makemytripHomepage(driver);
			mTH.waitforElementPresent();

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("User selects Roundtrip option")
	public void selectroundTrip() {
		ExtentTest logInfo = null;
		try {

			logInfo = test.createNode(new GherkinKeyword("Then"), "User selects Roundtrip option");
			mTH = new makemytripHomepage(driver);
			mTH.waitforElementPresent();
			mTH.clickroundtripRadiobutton();

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("Take a Screenshot when a Roundtrip radio button is selected")
	public void takeScreenshot() {
		ExtentTest logInfo=null;
		try{
			boolean actualValueRound = mTH.isroundtripRadiobuttonSelected();
			if (actualValueRound) {
				String screenshot = CommonFunctions.capturescreenshot(driver);
				//CommonFunctions.capturescreenshot(driver);
				logInfo = test.createNode(new GherkinKeyword("Then"),"A Screenshot is taken when a Roundtrip radio button is selected");
				logInfo= test.pass("Screenshot Captured",MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
			}
		}
			catch (AssertionError | Exception e) {
				testStepHandle("FAIL", logInfo, e);

			}

		
	}

	@When("Randomly select a Destination for the trip")
	public void selectDestination() {
		ExtentTest logInfo=null;
		try {
		
		mTH.fromSelectionClick();
		mTH.userRandomlySelectsFromPlace();
		logInfo = test.createNode(new GherkinKeyword("When"),"User Randomly selected a Destination for the trip");
	}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@And("Randomly select a Arrival for the trip")
	public void selectArrival() {
		ExtentTest logInfo=null;
		try {
		mTH.toSelectionClick();
		mTH.userRandomlySelectsToPlace();
		logInfo = test.createNode(new GherkinKeyword("And"),"User Randomly selected a Arrival for the trip");
	}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}
	
	@When("Select the lowest fare flight for the roundtrip")
	public void selectlowestfareFlight() {
		ExtentTest logInfo=null;
		try {
		mTH.selectFlightWithLowestPrice();
		logInfo = test.createNode(new GherkinKeyword("When"),"User Randomly selected the flight with lowest price");
	}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("Select the random Travellers for the roundtrip")
	public void selectrandomTravellers() {
		ExtentTest logInfo=null;
		try {
		mTH.clickTravellers();
		mTH.clickNoofAdults();
		mTH.clickNoofChildren();
		mTH.clickTravellersApply();
		logInfo = test.createNode(new GherkinKeyword("Then"),"User Randomly selected the travellers");
	}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("User performs Search and taken a screenshot of the results for a round trip")
	public void performflightSearch() {
		ExtentTest logInfo=null;
		try {
		mTH.clickFlightSearchButton();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		CommonFunctions.capturescreenshot(driver);
		logInfo = test.createNode(new GherkinKeyword("Then"),"User performed flight search");

	}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("User Logout from the application")
	public void logout() throws Exception {
		teardown();
	}
}
