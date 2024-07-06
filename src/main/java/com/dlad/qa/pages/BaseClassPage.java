package com.dlad.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BaseClassPage {
	
	WebDriver driver;
	
	public BaseClassPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	
	@FindBy(xpath = "//input[@id='username']")
	private WebElement EnterTheUserName;
	
	@FindBy(xpath = "//input[@id='password']")
	private WebElement EnterThePassword;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement ClickOnSubmit;
	
	/**
	 * Actions
	 */
	
    public String getLabelText(WebElement labelElement) {
        return labelElement.getText();
    }
    
	public String EnterTheUserName() {
		String userName = EnterTheUserName.getText();
		return userName;
	}
	
	public String EnterThePassword() {
		String password = EnterThePassword.getText();
		return password;
	}
	
	public String ClickOnSubmit() {
		String clickSubmit = ClickOnSubmit.getText();
		return clickSubmit;
	}

}
