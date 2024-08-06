package com.dlad.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {

    WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Locators
     */
	
	@FindBy(xpath = "//button[@type='button' and contains(text(),'Forgot Password?')]")
	private WebElement forgotPasswordLink;
	
    @FindBy(xpath = "//h1[@class='text-2xl font-semibold tracking-tight' and contains(text(),'Forgot Password')]")
    private WebElement forgotPasswordPageHeader;
    
    @FindBy(xpath = "//input[@type='text' and @id='user']")
    private WebElement forgotPasswordEmailTextField;
	
	@FindBy(xpath = "//ol/li/div[@data-content]/div[text()='Password reset link sent']")
	private WebElement passwordResetSuccessfulMessage;

	@FindBy(xpath = "//button[@type='submit' and contains(text(),'Submit')]")
	private WebElement clickOnSubmitButton;

	@FindBy(xpath = "//ol/li/div[@data-content]/div[text()='There was an error.']")
	private WebElement passwordResetErrorMessage;
	
    /**
     * Actions
     * @param labelElement
     * @return
     */
	
	public boolean forgotPasswordLink() {
		boolean forgotPassword = forgotPasswordLink.isDisplayed();
		return forgotPassword;
	}

	public String forgotPasswordLinkText() {
		String forgotPasswordLinkName = forgotPasswordLink.getText();
		return forgotPasswordLinkName;
	}

	public void forgotPasswordLinkClick() {
		forgotPasswordLink.click();
	}

	public String forgotPasswordPageHeader() {
		String forgotPasswordPageText = forgotPasswordPageHeader.getText();
		return forgotPasswordPageText;
	}
	
	public void forgotPasswordEmailTextField(String emailText) {
		forgotPasswordEmailTextField.sendKeys(emailText);
	}
	
	public String passwordResetSuccessfulMessage() {
		String resetPasswordSuccessMsg = passwordResetSuccessfulMessage.getText();
		return resetPasswordSuccessMsg;
	}
	
	public String passwordResetErrorMessage() {
		String resetPasswordErrMsg = passwordResetErrorMessage.getText();
		return resetPasswordErrMsg;
	}
	public void clickOnSubmitButton() {
		clickOnSubmitButton.click();
	}
	
}

