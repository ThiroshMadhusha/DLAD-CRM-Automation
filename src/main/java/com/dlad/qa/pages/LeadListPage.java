package com.dlad.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadListPage {
	
	WebDriver driver;
	
	public LeadListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	
	@FindBy(xpath = "//div[@class='flex gap-3 items-center']/h1[contains(text(),'Leads')]")
	private WebElement leadPageHeaderTitle;
	
	@FindBy(xpath = "//div[@class='flex gap-3 flex-row-reverse']/button")
	private WebElement clickNewLeadFormBtn;
	
	@FindBy(xpath = "//h2[contains(text(),'Create New Lead')]")
	private WebElement verifyOpenNewLeadForm;
	
	@FindBy(xpath = "//button[@type='button']/*[@class='h-4 w-4']")
	private WebElement closeNewLeadFormIcon;
	
	@FindBy(xpath = "//input[@placeholder='Lead Name']")
	private WebElement leadNameInputTextField;
	
	@FindBy(xpath = "//input[@id='about_company']")
	private WebElement companyNameInputTextField;
	
	@FindBy(xpath = "//button[@id='lead_group']")
	private WebElement clickOnLeadGropDropdownField;
	
	@FindBy(xpath = "//div[@class='flex items-center border-b px-3']/input[@placeholder='Search']")
	private WebElement clickSearchField;
	
	@FindBy(xpath = "//button[@id='rating']")
	private WebElement clickOnRatingsDropdownField;
	
	@FindBy(xpath = "//div[@class='relative overflow-hidden']/div/div/div/div/div[1]")
	private WebElement selectDropdownOption;
	
	@FindBy(xpath = "//button[contains(text(),'Save changes')]")
	private WebElement clickSaveChangesBtn;
	
	@FindBy(xpath = "//div[@class='flex items-center gap-2']/h2[contains(text(),'Lead')]")
	private WebElement leadPageViewHeaderTitle;
	
	@FindBy(xpath = "//button[contains(text(),'Save changes')]")
	private WebElement leadNameRequiredWarningMessage;
	
	@FindBy(xpath = "//button[contains(text(),'Save changes')]")
	private WebElement leadGroupRequiredWaringMessage;
	
	@FindBy(xpath = "//button[contains(text(),'Save changes')]")
	private WebElement ratingRequiredWarningMessage;
	
	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	private WebElement clickOnCancelBtn;
	
	@FindBy(xpath = "//p[contains(text(),'Lead name is required')]")
	private WebElement leadNameErrorMessage;
	
	@FindBy(xpath = "//p[contains(text(),'Lead group is required')]")
	private WebElement leadGroupErrorMessage;
	
	@FindBy(xpath = "//p[contains(text(),'Rating is required')]")
	private WebElement ratingErrorMessage;
	
	@FindBy(xpath = "//div[@class = 'border rounded-lg bg-white p-2  hover:bg-gray-100 transition-all cursor-pointer']")
	private WebElement clickPageBackBtn;
	
	@FindBy(xpath = "//tr[@class = 'border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted'][1]/td[2]")
	private WebElement verifyCreatedLeadNameInTableView;
	
	@FindBy(xpath = "//tr[@class = 'border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted'][1]/td[3]")
	private WebElement verifyLeadStatusInTableView;
	
	/**
	 * Actions
	 */
	
    public String getLabelText(WebElement labelElement) {
        return labelElement.getText();
    }
    
	public String leadPageHeaderTitle() {
		String leadPageName = leadPageHeaderTitle.getText();
		return leadPageName;
	}

	public void clickNewLeadFormBtn() {
		clickNewLeadFormBtn.click();
	}
	
	public void clickOnCancelBtn() {
		clickOnCancelBtn.click();
	}
	
	public String leadNameErrorMessage() {
		String leadNameError = leadNameErrorMessage.getText();
		return leadNameError;
	}
	
	public String leadGroupErrorMessage() {
		String leadGroupError = leadGroupErrorMessage.getText();
		return leadGroupError;
	}
	
	public String ratingErrorMessage() {
		String ratingError = ratingErrorMessage.getText();
		return ratingError;
	}
	
	public String verifyOpenNewLeadForm() {
		String openNewLeadForm = verifyOpenNewLeadForm.getText();
		return openNewLeadForm;
	}
	
	public boolean closeNewLeadFormIcon() {
		boolean closeBtnIcon = closeNewLeadFormIcon.isDisplayed();
		return closeBtnIcon;
	}

	public void leadNameInputTextField(String leadNameText) {
		leadNameInputTextField.sendKeys(leadNameText);
	}
	
	public void companyNameInputTextField(String companyNameText) {
		companyNameInputTextField.sendKeys(companyNameText);
	}
	
	public void clickOnLeadGropDropdownField() {
		clickOnLeadGropDropdownField.click();
	}
	
	public void clickOnRatingsDropdownField() {
		clickOnRatingsDropdownField.click();
	}

	public void selectDropdownOption() {
		selectDropdownOption.click();
	}
	
	public void clickSearchField(String searchFields) {
		clickSearchField.sendKeys(searchFields);
	}

	public void clickSaveChangesBtn() {
		clickSaveChangesBtn.click();
	}
	
	public void closeNewLeadForm() {
		closeNewLeadFormIcon.click();
	}
	
	public void clickPageBackBtn() {
		clickPageBackBtn.click();
	}
	
	public String leadPageViewHeaderTitle() {
		String leadviewPage = leadPageViewHeaderTitle.getText();
		return leadviewPage;
	}
	
	public String verifyCreatedLeadNameInTableView() {
		String leadNameInTableView = verifyCreatedLeadNameInTableView.getText();
		return leadNameInTableView;
	}
	
	public String verifyLeadStatusInTableView() {
		String leadStatusInTableView = verifyLeadStatusInTableView.getText();
		return leadStatusInTableView;
	}
	
}
