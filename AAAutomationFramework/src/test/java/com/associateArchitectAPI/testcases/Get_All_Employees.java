package com.associateArchitectAPI.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.associateArchitectAPI.base.BaseDriver;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class Get_All_Employees extends BaseDriver {
	
	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
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