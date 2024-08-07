package com.dlad.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//input[@id='username']")
	private WebElement loginUserName;
	
	@FindBy(xpath = "//input[@id='password']")
	private WebElement loginPassword;
	
	@FindBy(xpath = "//button[@type='submit' and contains(text(), 'Sign In')]")
	private WebElement clickOnLoginButton;
	
	@FindBy(xpath = "//ol/li/div[@data-content]/div[text()='Logged in successfully']")
	private WebElement loginSuccessfulMessage;
	
	@FindBy(xpath = "//ol/li/div[@data-content]/div[text()='Invalid login credentials']")
	private WebElement loginInvalidErrorMessage;

	@FindBy(xpath = "//button[@type='button' and contains(text(),'Forgot Password?')]")
	private WebElement clickOnForgotPasswordLink;

	@FindBy(xpath = "//h1[contains(text(), 'Forgot Password')]")
	private WebElement forgotPasswordPageTitle;

	@FindBy(xpath = "//label[contains(text(), 'Username')]/p[@class='text-red-700']")
	private WebElement usernameFieldRedAsteriskMark;

	@FindBy(xpath = "//label[contains(text(), 'Password')]/p[@class='text-red-700']")
	private WebElement passwordFieldRedAsteriskMark;

	@FindBy(xpath = "//h1[@class='text-2xl font-semibold tracking-tight']")
	private WebElement successfullyRedirectsToLoginPage;

	@FindBy(xpath = "//p[@class='text-red-700 text-sm' and contains(text(), 'Username is required')]")
	private WebElement loginUsernameRequiredMessage;

	@FindBy(xpath = "//p[@class='text-red-700 text-sm' and contains(text(), 'Password is required')]")
	private WebElement loginPasswordRequiredMessage;

	@FindBy(xpath = "//input[@name='username' and @placeholder='example@dlad.io']")
	private WebElement displayUsernamePlaceholder;

	@FindBy(xpath = "//input[@name='password' and @placeholder='*****']")
	private WebElement displayPasswordPlaceholder;

	@FindBy(xpath = "//div[@id='show-eye-icon']")
	private WebElement clickHideAndViewIcon;
	
	/*
	 * Actions
	 */
	public void loginUserName(String password) {
		loginUserName.sendKeys(password);
	}
	
	public void loginPassword(String password) {
		loginPassword.sendKeys(password);
	}

	public void clickHideAndViewIcon() {
		clickHideAndViewIcon.click();
	}

//	public void clearLoginUserName() {
//		enterUserName.clear();
//	}
//
//	public void clearLoginPassword() {
//		enterPassword.clear();
//	}

	public void clickOnLoginButton() {
		clickOnLoginButton.click();
	}
	
	public String loginSuccessfulMessage() {
		String loginSuccessMsg = loginSuccessfulMessage.getText();
		return loginSuccessMsg;
	}
	
	public String loginInvalidErrorMessage() {
		String loginErrMsg = loginInvalidErrorMessage.getText();
		return loginErrMsg;
	}

	public void clickOnForgotPasswordLink() {
		clickOnForgotPasswordLink.click();
	}

	public String forgotPasswordPageTitle() {
		String forgotPasswordTitle = forgotPasswordPageTitle.getText();
		return forgotPasswordTitle;
	}

	public boolean usernameFieldRedAsteriskMark() {
		boolean usernameFieldAsteriskMark = usernameFieldRedAsteriskMark.isDisplayed();
		return usernameFieldAsteriskMark;
	}

	public boolean passwordFieldRedAsteriskMark() {
		boolean passwordFieldAsteriskMark = passwordFieldRedAsteriskMark.isDisplayed();
		return passwordFieldAsteriskMark;
	}

	public String successfullyRedirectsToLoginPage() {
		String loginPageTitle = successfullyRedirectsToLoginPage.getText();
		return loginPageTitle;
	}

	public String loginUsernameRequiredMessage() {
		String usernameRequiredMsg = loginUsernameRequiredMessage.getText();
		return usernameRequiredMsg;
	}

	public String loginPasswordRequiredMessage() {
		String passwordRequiredMsg = loginPasswordRequiredMessage.getText();
		return passwordRequiredMsg;
	}

	public String displayUsernamePlaceholder() {
		String usernamePlaceholder = displayUsernamePlaceholder.getText();
		return usernamePlaceholder;
	}

	public String displayPasswordPlaceholder() {
		String passwordPlaceholder = displayPasswordPlaceholder.getText();
		return passwordPlaceholder;
	}
}