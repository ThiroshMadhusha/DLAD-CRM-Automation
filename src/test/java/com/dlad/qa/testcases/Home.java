package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;


public class Home extends BaseClass {

	
	WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod(){

		//	load Property
		loadPropertiesFile();
		try {
			driver = initializeBrowser(prop.getProperty("browserName"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Sidebar
		driver.findElement(By.xpath("//a[@href='/app']")).click();
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */
	
	
	@Test(priority = 1)
	public void verifyTheUserRedirectsToTheHomePage() {
		
		String homePage = driver.findElement(By.xpath("//h3[@class='tracking-tight text-sm font-medium text-white']")).getText();
		Assert.assertEquals(homePage, "Own Leads");
		
	}
}
