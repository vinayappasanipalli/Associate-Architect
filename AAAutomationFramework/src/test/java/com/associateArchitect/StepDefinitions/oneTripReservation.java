package com.associateArchitect.StepDefinitions;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

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
*               Make-my-trip web-site. It interacts with page objects, performs actions, 
*               handles assertions, captures screenshots, and utilizes reporting for test execution.       
 ******************************************************************************************************/

public class oneTripReservation extends BaseDriver_Cucumber {
	makemytripHomepage mTH;

	@Given("User Login to MakemyTrip site for onewaytrip")
	public void login() throws Exception {
		setup();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
	public void selectonewayTrip() {
		ExtentTest logInfo = null;
		try {

			
			mTH = new makemytripHomepage(driver);
			mTH.waitforElementPresent();
			mTH.clickOnewayRadiobutton();
			logInfo = test.createNode(new GherkinKeyword("Then"), "User selected onewaytrip option");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("Take a Screenshot when a onewaytrip radio button is selected")
	public void takeScreenshot() {
		ExtentTest logInfo = null;
		try {
		boolean actualValueRound = mTH.isOnewayRadiobuttonSelected();
		if (actualValueRound) {
			CommonFunctions.capturescreenshot(driver);
			logInfo = test.createNode(new GherkinKeyword("Then"), "A Screenshot is taken when  onewaytrip option");

		}
		}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@When("Randomly select a Destination for the onewaytrip")
	public void selectDestination() {
		ExtentTest logInfo = null;
		try {
		
		mTH.fromSelectionClick();
		mTH.userRandomlySelectsFromPlace();
		logInfo = test.createNode(new GherkinKeyword("When"), "User Randomly selected a destination for the oneway trip");
	}
		
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@And("Randomly select a Arrival for the onewaytrip")
	public void selectArrival() {
		ExtentTest logInfo = null;
		try {
		mTH.toSelectionClick();
		mTH.userRandomlySelectsToPlace();
		logInfo = test.createNode(new GherkinKeyword("And"), "User Randomly selected and Arrival for the oneway trip");
	}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@When("Select the lowest fare flight for the onewaytrip")
	public void selectlowestfareFlight() {
		ExtentTest logInfo = null;
		try {
		mTH.selectFlightWithLowestPrice();
		logInfo = test.createNode(new GherkinKeyword("When"), "User selected the lowest fair flight for the one way trip");
	}
		catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);

		}
	}

	@Then("Select the random Travellers for the onewaytrip")
	public void selectrandomTravellers() {
		ExtentTest logInfo = null;
		try {
		mTH.clickTravellers();
		mTH.clickNoofAdults();
		mTH.clickNoofChildren();
		mTH.clickTravellersApply();
		logInfo = test.createNode(new GherkinKeyword("Then"), "User randomly selected the travellers for the  one way trip");
		}
			catch (AssertionError | Exception e) {
				testStepHandle("FAIL", logInfo, e);

			}
	}

	@Then("User performs Search and taken a screenshot of the results for a oneway trip")
	public void performflightSearch() {
		ExtentTest logInfo = null;
		try {
		mTH.clickFlightSearchButton();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		CommonFunctions.capturescreenshot(driver);
		logInfo = test.createNode(new GherkinKeyword("Then"), "User performed flight search for the one way trip");
		}
			catch (AssertionError | Exception e) {
				testStepHandle("FAIL", logInfo, e);

			}

	}

	@Then("User Logout from the makemytrip application")
	public void logout() throws Exception {
		teardown();
	}
}
