package com.wikia.testcases;

import java.io.IOException;
//import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.util.Keywords;

public class LoginTest {

	/**
	 * 
	 * Test for the Login page
	 * @throws MalformedURLException 
	 * 
	 */

	Keywords session = Keywords.getInstance();
	
	@Test  (dataProvider="getData")
	public void doIncorrectLogin(String username, String password, String browserName, String flag) throws IOException {



		if(flag.equals("N"))
			throw new SkipException("Skipping as Flag is N"); 
		session.counter("browserInstance"); 
		session.openBrowser(browserName); //open the browser
		session.navigate("testSiteURL");
		Assert.assertTrue(session.validateTitle("homeworkPageTitle"), "HomeWork page titles did not match");
		Assert.assertTrue(session.validateURL("afterPageLoadURL"), "URL did not match");
		session.click("loginButton"); //click on login button
		Assert.assertTrue(session.waitUntilElementVisible("loginForm"), "Login form not visible");
		session.input("username", username); //enter user name
		session.input("password", password);
		session.click("LogInMenuButton");
		System.out.println("Login Test sucessfull with username : " +username+ "& password: "+password);
		session.validateText("expectedUserName", "loggedInUser");
		
	}



	@DataProvider
	public Object [] [] getData(){
		{
			Object data[] [] = new Object [2] [4];

			data [0] [0] = "singhgurpreet123";
			data [0] [1] = "p@ssw0rD";
			data [0] [2] = "Morzilla";
			data [0] [3] = "Y";

			data [1] [0] = "singhgurpreet123";
			data [1] [1] = "p@ssw0rD";
			data [1] [2] = "Chrome";
			data [1] [3] = "N";	



			return data;
		}


	}

	@AfterSuite
	public void CloseBrowser(){

		session.closeBroser();

	}
}

