package com.associateArchitect.StepDefinitions;

import java.io.FileReader;
import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.associateArchitect.Cucumber.base.BaseDriver_Cucumber;
import com.associateArchitect.Pages.makemytripHomepage;
import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.Utilities.CommonFunctions.FrameworkConfig;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;

public class multiTripReservation extends BaseDriver_Cucumber {
	makemytripHomepage mTH;

	@Given("User Login to MakemyTrip site for multiwaytrip")
	public void login() throws Exception {
		setup();
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
//		mTH.clickFlightSearchButton();
//		CommonFunctions.capturescreenshot(driver);
		ExtentTest logInfo = null;
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"),"User performs Search and taken a screenshot of the results for a round trip");
		// mTH.selectFairTye();
		mTH.clickFlightSearchButton();
		String screenshot = CommonFunctions.capturescreenshot(driver);
		logInfo.addScreenCaptureFromPath(screenshot);
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
