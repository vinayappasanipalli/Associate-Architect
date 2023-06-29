package com.associateArchitectAPI.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.associateArchitectAPI.base.BaseDriver;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Delete_EmployeeRecord extends BaseDriver {
	
	RequestSpecification httpRequest;
	Response response;
//	String empName = RestAPI_Utilities.empName();
//	String empSalary = RestAPI_Utilities.empSal();
//	String empAge = RestAPI_Utilities.empAge();
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException {
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
