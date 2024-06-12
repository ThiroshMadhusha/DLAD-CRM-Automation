package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;


public class LeadOverview extends BaseClass {

	
	WebDriver driver;
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException{

		//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(prop.getProperty("browserName"));
		// Validate Login
		driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));
		
		// Sidebar
		driver.findElement(By.xpath("//nav[@class='grid gap-1 px-2']/div[2]")).click();
		driver.findElement(By.xpath("//a[@href='/app/lead/overview']")).click();
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */
	
	@Test(priority = 1)
	public void verifyTheUserRedirectsToTheLeadOverviewPage() {
		
		String leadOverview = driver.findElement(By.xpath("//div[@class='flex gap-3 items-center']/h1")).getText();
		Assert.assertEquals(leadOverview, "Leads");

	}
	
}
