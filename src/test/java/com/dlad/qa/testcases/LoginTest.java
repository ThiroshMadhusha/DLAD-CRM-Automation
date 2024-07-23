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


	/*
	 * Test Cases
	 */
	
	@Test(priority = 1)
	public void verifyTheUserCanRedirectToTheLoginPage() {

		String actualLoginPageTitle = loginPage.successfullyRedirectsToLoginPage();
		Assert.assertEquals(actualLoginPageTitle, loginProp.getProperty("successfullyRedirectsToLoginPage"));
	}

	@Test(priority = 2)
	public void verifyTheLoginWithValidCredentials() {

		loginPage.loginUserName(loginProp.getProperty("validCRMEmailAddress"));
		loginPage.loginPassword(loginProp.getProperty("validCRMPassword"));
		loginPage.clickOnLoginButton();
		String actualLoginSuccessfulMessage = loginPage.loginSuccessfulMessage();
		Assert.assertEquals(actualLoginSuccessfulMessage, loginProp.getProperty("successfulLoginMessage"));
	}

	@Test(priority = 3, dataProvider = "invalidusernameAndPasswordDataSet")
	public void verifyloginWithInvalidCredentials(String invalidUserNames, String invalidPasswords) {

		loginPage.loginUserName(invalidUserNames);
		loginPage.loginPassword(invalidUserNames);
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, loginProp.getProperty("invalidLoginMessage"));
	}
//	
//	@Test(priority = 4)
//	public void verifyTheUserCanViewFieldsRequiredMessages() {
//		
//		loginPage.loginUserName(loginProp.getProperty("invalidUsername"));
//		loginPage.loginPassword(loginProp.getProperty("invalidPassword"));
//		loginPage.clickOnLoginButton();
//		String actualLoginFailMessage = loginPage.loginInvalidErrorMessage();
//		Assert.assertEquals(actualLoginFailMessage, loginProp.getProperty("invalidLoginMessage"));
//		
//		loginPage.clearLoginUserName();
//		String actualUsernameRequiredMsg = loginPage.loginUsernameRequiredMessage();
//		Assert.assertEquals(actualUsernameRequiredMsg, loginProp.getProperty("usernameRequiredMessage"));
//		
//		loginPage.loginUserName(loginProp.getProperty("invalidUsername"));
//		loginPage.clearLoginPassword();
//		String actualPasswordRequiredMsg = loginPage.loginPasswordRequiredMessage();
//		Assert.assertEquals(actualPasswordRequiredMsg, loginProp.getProperty("passwordRequiredMessage"));
//		
//	}

	@Test(priority = 4, dataProvider = "invalidusernameAndPasswordDataSet")
	public void verifyloginWithInvalidEmailAndValidPassword(String invalidUserNames) {

		loginPage.loginUserName(invalidUserNames);
		loginPage.loginPassword(loginProp.getProperty("validCRMPassword"));
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, loginProp.getProperty("invalidLoginMessage"));
	}

	@Test(priority = 5, dataProvider = "invalidusernameAndPasswordDataSet")
	public void verifyloginWithValidEmailAndInValidPassword(String invalidPasswords) {

		loginPage.loginUserName(loginProp.getProperty("validCRMEmailAddress"));
		loginPage.loginPassword(invalidPasswords);
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, loginProp.getProperty("invalidLoginMessage"));
	}

	@Test(priority = 6)
	public void verifyloginWithoutProvidingCredencials() {

		loginPage.loginUserName(" ");
		loginPage.loginPassword(" ");
		loginPage.clickOnLoginButton();
		String actualLoginFailMessage = loginPage.loginInvalidErrorMessage();
		Assert.assertEquals(actualLoginFailMessage, loginProp.getProperty("invalidLoginMessage"));
	}

//	@Test(priority = 6)
//	public void verifyLoginPagePasswordFieldHideAndViewIcon() {
//		
//		loginPage.loginUserName(loginProp.getProperty("validCRMEmailAddress"));
//		loginPage.loginPassword(loginProp.getProperty("validCRMPassword"));
//		String viewHideAndViewIcon = loginProp.getProperty("invalidLoginMessage");
//		String expectedLoginFailMessage = loginPage.loginInvalidErrorMessage();
//		Assert.assertEquals(actualLoginFailMessage, expectedLoginFailMessage);
//	}

	@Test(priority = 7)
	public void verifyTheForgotPasswordLink() {

		loginPage.clickOnForgotPasswordLink();
		String actualForgotPasswordPage = loginPage.forgotPasswordPageTitle();
		Assert.assertEquals(actualForgotPasswordPage, loginProp.getProperty("forgotPasswordTitle"));
	}

	@Test(priority = 8)
	public void verifyRequiredFieldRedAsteriskMark() {

		boolean usernameFieldRedAsteriskMark = loginPage.usernameFieldRedAsteriskMark();
		Assert.assertTrue(usernameFieldRedAsteriskMark);

		boolean passwordFieldRedAsteriskMark = loginPage.passwordFieldRedAsteriskMark();
		Assert.assertTrue(passwordFieldRedAsteriskMark);
	}


	// Create Dataset for Parallel Testing
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
