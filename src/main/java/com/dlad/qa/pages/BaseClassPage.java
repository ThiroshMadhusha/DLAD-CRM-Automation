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
    
	public void EnterTheUserName(String username) {
		EnterTheUserName.sendKeys(username);
	}
	
	public void EnterThePassword(String password) {
		EnterThePassword.sendKeys(password);
	}
	
	public void ClickOnSubmit() {
		ClickOnSubmit.click();
	}

}
