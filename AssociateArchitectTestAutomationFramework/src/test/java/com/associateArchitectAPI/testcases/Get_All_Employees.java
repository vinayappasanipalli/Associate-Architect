package com.associateArchitectAPI.testcases;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Get_All_Employees {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "19"; // Hard coded value - Input for getting details of single employee & update employee
	public Logger logger;
	
	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
		logger = Logger.getLogger("RestAssuredAPI");// added Logger
		//PropertyConfigurator.configure("log4j.properties");// added
		PropertyConfigurator.configure(System.getProperty("user.dir")+"//src//test//resources//Configfiles//log4j.properties");// added
		logger.setLevel(Level.DEBUG);

		logger.info("*********** Started Get_All_Employees Testcase");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		Thread.sleep(3);
		
	}
@Test
public void checkResponseBody() {
	logger.info("*****This Method is to check the Request Response Body");
	String responseBody = response.getBody().asString();
	logger.info("Response Body = " +responseBody);
	Assert.assertTrue(responseBody!=null);
	
}
@Test
public void checkStatusCode() {
	logger.info("********This Method is to check the status COde of the Response");
	int statusCode = response.getStatusCode();
	logger.info("Status Code is ==" + statusCode);
	Assert.assertEquals(statusCode,200);
}

@Test
public void checkResponseTime() {
	logger.info("This Methos is to check the Response Time of the Request");
	long responseTime = response.getTime();
	logger.info("Response Time is ==" + responseTime);
	if(responseTime>3000) {
		logger.warn("Response Time is morethan 3000");
		Assert.assertTrue(responseTime<3000);
	}
	
	
}

@Test
public void checkContentType() {
	logger.info("This Method is to check the Status line of the Request");
	String contentType = response.header("Content-Type");
	logger.info("Content-Type is == "+contentType);
	Assert.assertEquals(contentType, "text/html;chartset=UTF-8");
				
}

@AfterClass
public void tearDown() {
	logger.info("*******Finished TestCase Get_All_Employees*******");
}
}