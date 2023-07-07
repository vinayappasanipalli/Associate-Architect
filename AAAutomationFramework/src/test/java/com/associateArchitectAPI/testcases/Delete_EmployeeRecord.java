package com.associateArchitectAPI.testcases;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Delete_EmployeeRecord  {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "19"; // Hard coded value - Input for getting details of single employee & update employee
	public Logger logger;
	
	@BeforeClass
public	void deleteEmployee() throws InterruptedException {
		logger = Logger.getLogger("RestAssuredAPI");// added Logger
		PropertyConfigurator.configure(System.getProperty("user.dir")+"//src//test//resources//Configfiles//log4j.properties");// added
		logger.setLevel(Level.DEBUG);
		logger.info("TC#5- Started Test Case Delete Employee Record*******");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		JsonPath jsonPathEvaluator = response.jsonPath();
		String empID=jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE,"/delete/"+empID);
		Thread.sleep(8000);
		
	}

	@Test
	public void checkResponseBody() {
		logger.info("*****This Method is to check the Request Response Body");
		String responseBody = response.getBody().asString();
		logger.info("Response Body = " +responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"),true);
	
}
}
