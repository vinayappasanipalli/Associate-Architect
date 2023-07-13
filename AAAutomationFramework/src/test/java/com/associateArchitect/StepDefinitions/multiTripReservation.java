package com.associateArchitect.StepDefinitions;

import java.time.Duration;

import org.testng.Assert;

import com.associateArchitect.Pages.makemytripHomepage;
import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.base.BaseDriver;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;


/*****************************************************************************************************
*Class Name : multiTripReservation
*Description : This class extends BaseDriver_Cucumber class. This class class represents the 
*              step definitions for a Cucumber scenario related to multi-way trip reservation on the
*               Make-my-trip web-site. It interacts with page objects, performs actions, 
*               handles assertions, captures screenshots, and utilizes reporting for test execution.       
 ******************************************************************************************************/

public class multiTripReservation extends BaseDriver {
	makemytripHomepage mTH;

	@Given("User Login to MakemyTrip site for multiwaytrip")
	public void login() throws Exception {
		setup();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ExtentTest logInfo = null;
		try {
			test = extent.createTest(Feature.class, "Flight Booking for MultiwayTrip");
			test = test.createNode(Scenario.class, "Make a multiwayTrip flight reservation");
			logInfo = test.createNode(new GherkinKeyword("Given"),
					"User Login to MakemyTrip site for multiwaytrip");
			mTH = new makemytripHomepage(driver);
			mTH.waitforElementPresent();

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("User selects multiwaytrip option")
	public void selectroundTrip() {
		ExtentTest logInfo = null;
		try {

			logInfo = test.createNode(new GherkinKeyword("Then"), "User selects multiwaytrip option");
			mTH = new makemytripHomepage(driver);
			mTH.waitforElementPresent();
			mTH.clickroundtripRadiobutton();

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("Take a Screenshot when a multiwaytrip radio button is selected")
	public void takeScreenshot() {
		boolean actualValueRound = mTH.isroundtripRadiobuttonSelected();
		if (actualValueRound) {
			CommonFunctions.capturescreenshot(driver);

		}
	}

	@When("Randomly select a Destination for the multiwaytrip")
	public void selectDestination() {
		mTH.fromSelectionClick();
		mTH.userRandomlySelectsFromPlace();
	}

	@And("Randomly select a Arrival for the multiway")
	public void selectArrival() {
		mTH.toSelectionClick();
		mTH.userRandomlySelectsToPlace();
	}

	@When("Select the lowest fare flight for the multiwaytrip")
	public void selectlowestfareFlight() {
		//mTH.selectFlightWithLowestPrice();
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("When"),"User select the flight with the lowest price for a round trip");
		
		String actualrecordsave = "My Profile123";
		String expectedrecordsave="My Profile";
		Assert.assertEquals(actualrecordsave,expectedrecordsave,"Record is not Saved");
		logInfo.pass("Test Case Pass");
		String screenshot = CommonFunctions.capturescreenshot(driver);
		logInfo.addScreenCaptureFromPath(screenshot);
		mTH.selectFlightWithLowestPrice();
		
		}
	catch (AssertionError | Exception e) {
		testStepHandle("FAIL",logInfo, e);
		}
	}

	@Then("Select the random Travellers for the multiwaytrip")
	public void selectrandomTravellers() {
		mTH.clickTravellers();
		mTH.clickNoofAdults();
		mTH.clickNoofChildren();
		mTH.clickTravellersApply();
	}

	@Then("User performs Search and taken a screenshot of the results for a multiway trip")
	public void performflightSearch() {
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"),"User performs Search and taken a screenshot of the results for a round trip");
			mTH.clickFlightSearchButton();
		String screenshot = CommonFunctions.capturescreenshot(driver);
		logInfo.addScreenCaptureFromPath(screenshot);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL",logInfo, e);
			}

	}

	@Then("Logout from the makemytrip application")
	public void logout() throws Exception {
		teardown();
	}
}
