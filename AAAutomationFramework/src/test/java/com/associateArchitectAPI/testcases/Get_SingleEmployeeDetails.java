package com.associateArchitectAPI.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.associateArchitectAPI.base.BaseDriver;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Get_SingleEmployeeDetails extends BaseDriver {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	public void getsingleEmployee() throws InterruptedException {
		logger.info("*********** Started Get_Single_EmployeeDetails Testcase");
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees" + empID);
		Thread.sleep(10000);

	}

	@Test
	public void checkResponseBody() {
		logger.info("*******This Method is Check the Request Response Body****");
		String responseBody = response.getBody().asString();
		logger.info("Response Body = " + responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);
	}

	@Test
	public void checkStatusCode() {
		logger.info("*******TC#2 - This Method is Check the Request Status Code****");
		int statuscode = response.getStatusCode();
		logger.info("Status Code = " + statuscode);
		Assert.assertEquals(statuscode, 200);
	}

	@Test
	public void checkResponseTime() {
		logger.info("This Method is to check the Response Time of the Request");
		long responseTime = response.getTime();
		logger.info("Response Time is ==" + responseTime);
		if (responseTime > 3000) {
			logger.warn("Response Time is morethan 3000");
			Assert.assertTrue(responseTime < 3000);
		}
	}

	@Test
	public void checkstatusLine() {
		logger.info("This Method is to check the Response's Status Line");
		String statusline = response.getStatusLine();
		logger.info("Response's Status Line =" + statusline);
		Assert.assertEquals(statusline, "Http/1.1 200 OK");
	}

	@Test
	public void checkContentType() {
		logger.info("This Method is to check the Status line of the Request");
		//String contentType = response.header("Content-Type");
		String contentType = response.header("Content-TYpe");
		logger.info("Content-Type is == " + contentType);
		Assert.assertEquals(contentType, "text/html;chartset=UTF-8");
		
	}

	@Test

	public void checkserverType() {
		logger.info("This Method is to check the Server Type of the Request");
		String servertype = response.header("Server");
		logger.info("Server Type == " + servertype);
		Assert.assertEquals(servertype, "nginx/1.21.6");
	}

	@Test
	public void checkcontentLength() {
		logger.info("This Method is to check the Content Lenght of the Request");
		String contentlength = response.header("Content-Length");
		logger.info("Content-Length is == " + contentlength);
		Assert.assertTrue(Integer.parseInt(contentlength) < 1500);

	}


@AfterClass
	public void tearDown() {
		logger.info("*******TC#2- Finished TestCase Get_SingleEmployeeDetails*******");
	}
}

