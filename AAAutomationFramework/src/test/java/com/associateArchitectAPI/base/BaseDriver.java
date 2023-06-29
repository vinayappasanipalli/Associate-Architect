package com.associateArchitectAPI.base;


import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseDriver {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "19"; // Hard coded value - Input for getting details of single employee & update employee
	public Logger logger;

	@BeforeClass
	public void setup() throws IOException {
		logger = Logger.getLogger("RestAssuredAPI");// added Logger
		//PropertyConfigurator.configure("log4j.properties");// added
		PropertyConfigurator.configure(System.getProperty("user.dir")+"//src//test//resources//Configfiles//log4j.properties");// added
		logger.setLevel(Level.DEBUG);

	}

}
