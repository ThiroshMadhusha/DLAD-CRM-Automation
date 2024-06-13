package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.SidebarCRM;


public class Home extends BaseClass {

	
	WebDriver driver;
	SidebarCRM sidebarCRM;
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException{

		//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(prop.getProperty("browserName"));
		// Validate Login
		driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));
		
		// Sidebar
		sidebarCRM = new SidebarCRM(driver);
		sidebarCRM.HomeTab();
		
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */
	
	
	@Test(priority = 1)
	public void verifyTheUserRedirectsToTheHomePage() throws InterruptedException {
		
		String homePage = driver.findElement(By.xpath("//h3[@class='tracking-tight text-sm font-medium text-white']")).getText();
		Assert.assertEquals(homePage, "Own Leads");
	}
}
