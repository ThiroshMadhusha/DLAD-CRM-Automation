package com.dlad.qa.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.LoginPage;

public class Login extends BaseClass {

	public WebDriver driver;
	LoginPage loginPage;
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

	//	load Property
		loadPropertiesFile();
		driver = initializeBrowser(configProp.getProperty("browserName"));
		
		loginPage = new LoginPage(driver);
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
	
	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() throws InterruptedException {
		
		loginPage.loginUserName(loginProp.getProperty("validCRMEmailAddress"));
		loginPage.loginPassword(loginProp.getProperty("validCRMPassword"));
		loginPage.clickOnLoginButton();
		String actualLoginSuccessfulMessage= loginProp.getProperty("successfulLoginMessage");
		String expectedLoginSuccessMessage = loginPage.loginSuccessfulMessage();
		Assert.assertEquals(actualLoginSuccessfulMessage, expectedLoginSuccessMessage);
		System.out.println(expectedLoginSuccessMessage);
		Thread.sleep(3000);
	}

	
	@Test(priority = 2)
	public void verifyloginWithInvalidCredentials() {
		
		
	}
	
	@Test(priority = 3)
	public void verifyloginWithInvalidEmailAndValidPassword() {
		
		
	}
	
	@Test(priority = 4)
	public void verifyloginWithValidEmailAndInValidPassword() {
		
		
	}
	
	@Test(priority = 5)
	public void verifyloginWithoutProvidingCredencials() {
		
		
	}
}
