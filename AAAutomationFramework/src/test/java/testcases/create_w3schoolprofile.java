package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseDriver;
import utilities.CommonFunctions;
import utilities.ReadTestDataExcel;

public class create_w3schoolprofile extends BaseDriver {
	public static CommonFunctions commonfns;
	@Test(dataProviderClass = ReadTestDataExcel.class, dataProvider = "bvttestdata")
	public void createprofile(String username,String password,String url) {
		
		try {
			logger.info("************Testcase DeleteProfile is Started**************");
			CommonFunctions.cf_login(username, password);
			CommonFunctions.cf_webelementfoundwait(objrepo.getProperty("upgrade"),50);
			logger.info("UserLogged in Successfully");
			Thread.sleep(6000);
			CommonFunctions.cf_weblinktextclick(objrepo.getProperty("profile"));
			Thread.sleep(6000);
			CommonFunctions.cf_webelementsendkeys((objrepo.getProperty("profile_url")), url);
			Thread.sleep(6000);
			CommonFunctions.cf_webelementclick((objrepo.getProperty("profile_save_button")));
			Thread.sleep(6000);
			//String actualrecordsave = "My Profile";
			//String expectedrecordsave="My Profile";
			//Assert.assertEquals(actualrecordsave,expectedrecordsave,"Record is not Saved");
			logger.info("************Testcase CreateProfile is Completed **************");
			} catch (Throwable e) {
				Assert.fail("Create Profile Testcase is Failed", e);
				logger.error("Testcase Failed with the error:", e);	
		}
	
		
		
	}
	
	}

