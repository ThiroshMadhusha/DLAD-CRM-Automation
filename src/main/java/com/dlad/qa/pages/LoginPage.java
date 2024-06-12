//package com.dlad.qa.pages;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class LoginPage {
//	
//
//	WebDriver driver;
//	
//	public LoginPage(WebDriver driver) {
//		this.driver = driver;
//		PageFactory.initElements(driver, this);
//	}
//	
//	@FindBy(xpath = "//input[@id='username']")
//	private WebElement enterUserName;
//	
//	@FindBy(xpath = "//input[@id='password']")
//	private WebElement enterPassword;
//	
//	@FindBy(xpath = "//button[@type='submit']")
//	private WebElement clickLoginButton;
//	
//	// Actions
//	
//	public void loginUserName(String userName) {
//		enterUserName.sendKeys(userName);
//	}
//	
//	public void loginPassword(String password) {
//		enterPassword.sendKeys(password);
//	}
//	
//	public void clickOnLoginButton() {
//		clickLoginButton.click();
//	}
//
//}