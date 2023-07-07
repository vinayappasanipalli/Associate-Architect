package com.associateArchitect.StepDefinitions;

import java.io.FileReader;
import java.io.IOException;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

/*****************************************************************************************************
*Class Name : oneTripReservation
*Description : This class extends BaseDriver_Cucumber class. This class represents the 
*              step definitions for a Cucumber scenario related to one-way trip reservation on the
*               Makemytrip website. It interacts with page objects, performs actions, 
*               handles assertions, captures screenshots, and utilizes reporting for test execution.       
 ******************************************************************************************************/

public class oneTripReservation extends BaseDriver_Cucumber {
	makemytripHomepage mTH;

	@Given("User Login to MakemyTrip site for onewaytrip")
	public void login() throws Exception {
		setup();
		ExtentTest logInfo = null;
		try {
			test = extent.createTest(Feature.class, "Flight Booking for Oneway Trip");
			test = test.createNode(Scenario.class, "Make a OnewayTrip flight reservation");
			logInfo = test.createNode(new GherkinKeyword("Then"),
					"User Login to MakemyTrip site for onewaytrip");
			mTH = new makemytripHomepage(driver);
			mTH.waitforElementPresent();

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("User selects onewaytrip option")
	public void selectroundTrip() {
		ExtentTest logInfo = null;
		try {

			logInfo = test.createNode(new GherkinKeyword("Then"), "User selects onewaytrip option");
			mTH = new makemytripHomepage(driver);
			mTH.waitforElementPresent();
			mTH.clickroundtripRadiobutton();

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("Take a Screenshot when a onewaytrip radio button is selected")
	public void takeScreenshot() {
		boolean actualValueRound = mTH.isroundtripRadiobuttonSelected();
		if (actualValueRound) {
			CommonFunctions.capturescreenshot(driver);

		}
	}

	@When("Randomly select a Destination for the onewaytrip")
	public void selectDestination() {
		mTH.fromSelectionClick();
		mTH.userRandomlySelectsFromPlace();
	}

	@And("Randomly select a Arrival for the onewaytrip")
	public void selectArrival() {
		mTH.toSelectionClick();
		mTH.userRandomlySelectsToPlace();
	}

	@When("Select the lowest fare flight for the onewaytrip")
	public void selectlowestfareFlight() {
		mTH.selectFlightWithLowestPrice();
	}

	@Then("Select the random Travellers for the onewaytrip")
	public void selectrandomTravellers() {
		mTH.clickTravellers();
		mTH.clickNoofAdults();
		mTH.clickNoofChildren();
		mTH.clickTravellersApply();
	}

	@Then("User performs Search and taken a screenshot of the results for a oneway trip")
	public void performflightSearch() {
		mTH.clickFlightSearchButton();
		CommonFunctions.capturescreenshot(driver);

	}

	@Then("User Logout from the makemytrip application")
	public void logout() throws Exception {
		teardown();
	}
}
