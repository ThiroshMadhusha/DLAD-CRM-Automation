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
	public void beforeMethod() throws InterruptedException{

		//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(prop.getProperty("browserName"));
		// Validate Login
		driver = validLogin(loginProp.getProperty("validCRMEmailAddress", "validCRMPassword"));
		
		// Sidebar
		driver.findElement(By.xpath("//nav[@class='grid gap-1 px-2']/div[1]")).click();
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
		
		String actualeadActivityListPage = driver.findElement(By.xpath("//div[@class='flex gap-3 items-center']/h1")).getText();
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
	
	@Test(priority = 4)
	public void verifyTheNewActivityLeadWithValidData() throws InterruptedException {
		
		driver.findElement(By.xpath("//div[@class='flex gap-3 flex-row-reverse']/button")).click();
		driver.findElement(By.xpath("//button[@id='lead']")).click();
		driver.findElement(By.xpath("//div[@class='flex items-center border-b px-3']/input[@placeholder='Search']")).sendKeys("Test123");
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@class='relative overflow-hidden']/div/div/div/div/div[1]")).click();
		driver.findElement(By.xpath("//button[@id='type']")).click();
		driver.findElement(By.xpath("//div[@class='flex items-center border-b px-3']/input[@placeholder='Search']")).sendKeys("Call cycle");
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@class='relative overflow-hidden']/div/div/div/div/div[1]")).click();
		driver.findElement(By.xpath("//button[@id='subject']")).click();
		driver.findElement(By.xpath("//div[@class='flex items-center border-b px-3']/input[@placeholder='Search']")).sendKeys("AR Subject 2");
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@class='relative overflow-hidden']/div/div/div/div/div[1]")).click();
		Thread.sleep(2500);

	}
	
	
	
	
	
	
	
}
