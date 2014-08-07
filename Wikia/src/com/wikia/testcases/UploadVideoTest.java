package com.wikia.testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wikia.util.Keywords;

public class UploadVideoTest {

	Keywords session = Keywords.getInstance();

	@Test
	public void uploadVideoTest() throws MalformedURLException, InterruptedException{

		session.openBrowser("Morzilla");
		session.counter("browserInstance");
		session.navigate("testSiteURL");
		Assert.assertTrue(session.validateTitle("homeworkPageTitle"), "HomeWork page titles did not match");
		Assert.assertTrue(session.validateURL("afterPageLoadURL"), "URL did not match");
		session.click("contributeClick");
		session.validateText("contributeFormText", "contributeFormId");
		session.clickLink("uploadVideo");
		session.inputURL("videoURLId", "videoURL");
		session.click("videoAddButton");
		String fileURL = "http://testhomework.wikia.com/wiki/"+session.readText("videoName").replaceAll(" ", "_");
		try{

			session.validateText("videoFile1", "videoName");

		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		session.waitUntilElementClickable("videoName");
		session.click("videoName");
		Thread.sleep(1000L);	// could use wait until the title matches the fileName or unitl page load
		System.out.println(session.readURL());
		System.out.println(fileURL);
		Assert.assertEquals(session.readURL(), fileURL);


	}
}
