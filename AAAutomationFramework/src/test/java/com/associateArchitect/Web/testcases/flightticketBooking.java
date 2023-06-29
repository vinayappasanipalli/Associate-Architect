package com.associateArchitect.Web.testcases;

import java.io.File;

import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.associateArchitect.Utilities.CommonFunctions;
import com.associateArchitect.Web.base.BaseDriver;

public class flightticketBooking extends BaseDriver {
	public static com.associateArchitect.Utilities.CommonFunctions commonfns;

	// @Test(dataProviderClass = com.utilities.CommonFunctions.class, dataProvider =
	// "bvttestdata")
	@Test
	public void flightReservation() {
		try {
			com.associateArchitect.Pages.makemytripHomepage mTH = new com.associateArchitect.Pages.makemytripHomepage(driver);
			mTH.clickroundtripRadiobutton();
			boolean actualValueRound = mTH.isroundtripRadiobuttonSelected();
			boolean expectedValueRound = mTH.isroundtripRadiobuttonSelected();
			Assert.assertEquals(actualValueRound, expectedValueRound);
			logger.info("One Way Radio button is clicked" + actualValueRound);
			if (actualValueRound) {
				CommonFunctions.capturescreenshot(driver);
				logger.info("Screenshot taken when Round Way radio button is selected.");
			}

		} catch (Throwable e) {
			Assert.fail("Flight Reservation Testcase Failed", e);
			logger.error("Testcase Failed with the error:", e);
		}

	}
}
