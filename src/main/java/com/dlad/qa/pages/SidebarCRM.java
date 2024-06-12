package com.dlad.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SidebarCRM {

	WebDriver driver;
	
	public SidebarCRM (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "")
	private WebElement HomeTab;
	
	@FindBy(xpath = "")
	private WebElement ActivityOverview;
	
	@FindBy(xpath = "")
	private WebElement LeadActivityList;
	
	@FindBy(xpath = "")
	private WebElement BPActivityList;
	
	@FindBy(xpath = "")
	private WebElement LeadOverview;

	@FindBy(xpath = "")
	private WebElement LeadList;
	
	@FindBy(xpath = "")
	private WebElement BusinessPartnersOverview;
	
	@FindBy(xpath = "")
	private WebElement BusinessPartnerList;
	
	@FindBy(xpath = "")
	private WebElement Quotations;
	
	@FindBy(xpath = "")
	private WebElement SalesOrders;
	
	@FindBy(xpath = "")
	private WebElement Calander;
	
	@FindBy(xpath = "")
	private WebElement ProfileIcon;
	
	// Actions
	
	public void HomeTab() {
		HomeTab.click();
	}
	
	public void ActivityOverview() {
		ActivityOverview.click();
	}
	
	public void LeadActivityList() {
		LeadActivityList.click();
	}
	
	public void BPActivityList() {
		BPActivityList.click();
	}
	
	public void LeadOverview() {
		LeadOverview.click();
	}
	
	public void LeadList() {
		LeadList.click();
	}
	
	public void BusinessPartnersOverview() {
		BusinessPartnersOverview.click();
	}
	
	public void BusinessPartnerList() {
		BusinessPartnerList.click();
	}
	
	public void Quotations() {
		Quotations.click();
	}
	
	public void SalesOrders() {
		SalesOrders.click();
	}
	
	public void Calander() {
		Calander.click();
	}
	
	public void ProfileIcon() {
		Calander.click();
	}
}
