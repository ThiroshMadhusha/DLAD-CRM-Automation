package com.dlad.qa.testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dlad.qa.base.BaseClass;
import com.dlad.qa.pages.LoginPage;

public class LoginTest extends BaseClass {

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
	}

	
	@Test(priority = 2, dataProvider = "invalidusernameAndPasswordDataSet")
	public void verifyloginWithInvalidCredentials(String invalidUserNames, String invalidPasswords) {
		
		loginPage.loginUserName(invalidUserNames);
		loginPage.loginPassword(invalidUserNames);
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage= loginProp.getProperty("invalidLoginMessage");
		String expectedLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, expectedLoginFailMessage);
	}
	
	@Test(priority = 3, dataProvider = "invalidusernameAndPasswordDataSet")
	public void verifyloginWithInvalidEmailAndValidPassword(String invalidUserNames) {
		
		loginPage.loginUserName(invalidUserNames);
		loginPage.loginPassword(loginProp.getProperty("validCRMPassword"));
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage= loginProp.getProperty("invalidLoginMessage");
		String expectedLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, expectedLoginFailMessage);
	}
	
	@Test(priority = 4, dataProvider = "invalidusernameAndPasswordDataSet")
	public void verifyloginWithValidEmailAndInValidPassword(String invalidPasswords) {
		
		loginPage.loginUserName(loginProp.getProperty("validCRMEmailAddress"));
		loginPage.loginPassword(invalidPasswords);
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage= loginProp.getProperty("invalidLoginMessage");
		String expectedLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, expectedLoginFailMessage);
	}
	
	@Test(priority = 5)
	public void verifyloginWithoutProvidingCredencials() {
		
		loginPage.loginUserName(" ");
		loginPage.loginPassword(" ");
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage= loginProp.getProperty("invalidLoginMessage");
		String expectedLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, expectedLoginFailMessage);
	}
	
	@DataProvider(name = "invalidusernameAndPasswordDataSet")
	public Object[][] invalidusernameAndPasswordDataSet(){
		
		String[] invalidUserNames = {"malaka123@dlad.io", "dinesh123@dlad.io", "hesha123@dlad.io", "0520abcd@dlad.io"};
		String[] invalidPasswords = {"DQDE@#ED", "NCIJ@NC*@HC*@", "NX@&Qwq23e2", "32eWQQWUDUq&E"};
		
		Object[][] data = new Object[invalidUserNames.length][2];
		
		for(int i = 0; i < invalidUserNames.length; i++) {
			data[i][0] = invalidUserNames[i];
			data[i][1] = invalidPasswords[i];
		}
		return data;
	}
}
