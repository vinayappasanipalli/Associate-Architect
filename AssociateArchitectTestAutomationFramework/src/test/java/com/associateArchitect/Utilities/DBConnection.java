package com.associateArchitect.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
/*****************************************************************************************************
*Class Name : DB Connection
*Description : This class holds the collection of all common functions functionality for interacting
* with an Oracle database by performing insert, update, delete, and select operations on the "users" 
* table using JDBC.
* ******************************************************************************************************/


public class DBConnection {
	public static WebDriver driver;	
	
	public static void insertData() throws SQLException {
		//JDBC Connection Established
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/pdborcl","vappasanipalli","Infy123");
	    //Create a Statement
		Statement stmt = con.createStatement();
		//Execute Insert Statement
		String s = "insert into users table(102,'vappasanipalli','Test@123')";
		stmt.executeQuery(s); 
		con.close();
   	
	}
	public static void updateData() throws SQLException {
		//JDBC Connection Established
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/pdborcl","vappasanipalli","Infy123");
	    //Create a Statement
		Statement stmt = con.createStatement();
		//Execute Insert Statement
		String s = "update users set uname='vinay2' where userid=102";
		stmt.executeQuery(s); 
		con.close();
   	
	}
	public static void deleteData() throws SQLException {
		//JDBC Connection Established
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/pdborcl","vappasanipalli","Infy123");
	    //Create a Statement
		Statement stmt = con.createStatement();
		//Execute Insert Statement
		String s = "delete user where userid=102";
		stmt.executeQuery(s); 
		con.close();
   	
	}
	public static void selectData() throws SQLException {
		//JDBC Connection Established
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/pdborcl","vappasanipalli","Infy123");
	    //Create a Statement
		Statement stmt = con.createStatement();
		//Execute Insert Statement
		String s = "select uname,passwrd from users";
		ResultSet rs = stmt.executeQuery(s); 
		while(rs.next()) {
			String username = rs.getString("uname");
			String password = rs.getString("passwrd");
			driver.findElement(By.name("username")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);

		}
		con.close();
   	
	}
}
