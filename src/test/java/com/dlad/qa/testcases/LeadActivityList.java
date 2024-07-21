package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.SidebarCRM;


public class LeadActivityList extends BaseClass {
	
	
	public WebDriver driver;
	SidebarCRM sidebarCRM;

	@BeforeMethod
	public void beforeMethod() {

		//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(configProp.getProperty("browserName"));
		// Validate Login
		driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));
		
		// Sidebar
		sidebarCRM = new SidebarCRM(driver);
		sidebarCRM.LeadListGroup();
		sidebarCRM.LeadActivityList();
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
		
		String actualeadActivityListPage = driver.findElement(By.xpath("//div[@class='flex gap-3 items-center']/h1[contains(text(),'Lead Activities')]")).getText();
		Assert.assertEquals(actualeadActivityListPage, "Lead Activities");
	}
	
	@Test(priority = 2)
	public void verifyTheCreatenewLeadActivityListFormOpen() {

		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		String actualLeadActivityForm = driver.findElement(By.xpath("//div[@class='flex flex-col space-y-1.5 text-center sm:text-left']/h2")).getText();
		Assert.assertEquals(actualLeadActivityForm, "Create New Lead Activity", "Error Activity Form Opening...!");
	}
	
	@Test(priority = 3)
	public void verifyCreateNewActivityLeadsCloseButton() {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		boolean leadActivityCloseIcon = driver.findElement(By.xpath("//button[@type='button']/*[@class='h-4 w-4']")).isDisplayed();
		Assert.assertTrue(leadActivityCloseIcon);
		driver.findElement(By.xpath("//button[@type='button']/*[@class='h-4 w-4']")).click();
	}
	
	
}
