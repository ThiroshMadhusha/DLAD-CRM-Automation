package com.dlad.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.SidebarCRM;
import com.dlad.qa.utils.Utils;

public class LeadList extends BaseClass {
	
	WebDriver driver;
	SidebarCRM sidebarCRM;

	@BeforeMethod
	public void beforeMethod() {

		//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(prop.getProperty("browserName"));
		// Validate Login
		driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));
		
		// Sidebar
		sidebarCRM = new SidebarCRM(driver);
		sidebarCRM.LeadListGroup();
		sidebarCRM.LeadList();

	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/*
	 * Test Cases
	 */

	@Test(priority = 1)
	public void verifyTheUserRedirectsToTheLeadListPage() {
		
		String actualeadListPage = driver.findElement(By.xpath("//div[@class='flex gap-3 items-center']/h1[contains(text(),'Leads')]")).getText();
		Assert.assertEquals(actualeadListPage, "Leads");
	}
	
	@Test(priority = 2)
	public void verifyTheCreateNewLeadFormOpen() {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		String actualForm = driver.findElement(By.xpath("//h2[contains(text(),'Create New Lead')]")).getText();
		Assert.assertEquals(actualForm, "Create New Lead", "Error Lead List Form Opening...!");
	}
	//div[@class='flex gap-3 items-center']/h1
	@Test(priority = 3)
	public void verifyTheCreateNewLeadFormCloseIcon() throws InterruptedException {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		boolean leadCloseIcon = driver.findElement(By.xpath("//button[@type='button']/*[@class='h-4 w-4']")).isDisplayed();
		Assert.assertTrue(leadCloseIcon);
		driver.findElement(By.xpath("//button[@type='button']/*[@class='h-4 w-4']")).click();
	}
		
	
	@Test(priority = 4)
	public void verifyTheCreateNewLeadWithValidData() throws InterruptedException {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		driver.findElement(By.xpath("//input[@id='lead_name']")).sendKeys(Utils.nameGenerate());
		driver.findElement(By.xpath("//input[@id='about_company']")).sendKeys(Utils.companyNameGenerate());
		driver.findElement(By.xpath("//button[@id='lead_group']")).click();
		driver.findElement(By.xpath("//div[@class='flex items-center border-b px-3']/input[@placeholder='Search']")).sendKeys("Cafe");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='relative overflow-hidden']/div/div/div/div/div[1]")).click();
		driver.findElement(By.xpath("//button[@id='rating']")).click();
		driver.findElement(By.xpath("//div[@class='flex items-center border-b px-3']/input[@placeholder='Search']")).sendKeys("Warm");
		driver.findElement(By.xpath("//div[@class='relative overflow-hidden']/div/div/div/div/div[1]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Save changes')]")).click();
		String pageHeader = driver.findElement(By.xpath("//div[@class='flex items-center gap-2']/h2[contains(text(),'Lead')]")).getText();
		Assert.assertEquals(pageHeader, "Lead");
		
	}
	
	@Test(priority = 5)
	public void verifyTheClickOnCreateButtonWithWithoutFillingData() throws InterruptedException {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Save changes')]")).click();
	}
	
	@Test(priority = 6)
	public void cancellButtonNewLeadForm() throws InterruptedException {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		
	}
}
