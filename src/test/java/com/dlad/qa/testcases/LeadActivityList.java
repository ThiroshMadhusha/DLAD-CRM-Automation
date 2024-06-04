package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;


public class LeadActivityList extends BaseClass {
	
	
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
		driver.findElement(By.xpath("//nav[@class='grid gap-1 px-2']/div[2]")).click();
		driver.findElement(By.xpath("//a[@href='/app/activity/lead/list']")).click();
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */
	
	@Test(priority = 1)
	public void verifyTheUserRedirectsToTheLeadActivityListPage() {
		
		String actualeadActivityListPage = driver.findElement(By.xpath("")).getText();
		Assert.assertEquals(actualeadActivityListPage, "Lead Activities");
	}
	
	
	
	
	
}
