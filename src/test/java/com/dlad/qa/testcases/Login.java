package com.dlad.qa.testcases;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;

public class Login extends BaseClass {

	public WebDriver driver;

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

	//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(configProp.getProperty("browserName"));
	
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	/**
	 * From Excell Sheet Data
	 * 
	 * @param Email
	 * @param Password
	 * @throws InterruptedException
	 */
	
	@Test
	public void verifyLoginWithValidCredentials() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(loginProp.getProperty("validCRMEmailAddress"));
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(loginProp.getProperty("validCRMPassword"));
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}

}
