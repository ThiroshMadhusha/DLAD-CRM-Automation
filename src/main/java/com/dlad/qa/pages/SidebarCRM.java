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
	
	@FindBy(xpath = "//a[@href='/app']")
	private WebElement HomeTab;
	
	@FindBy(xpath = "//nav[@class='grid gap-1 px-2']/div[1]")
	private WebElement ActivityGroup;
	
	@FindBy(xpath = "//a[@href='/app/activity/overview']")
	private WebElement ActivityOverview;
	
	@FindBy(xpath = "//a[@href='/app/activity/lead/list']")
	private WebElement LeadActivityList;
	
	@FindBy(xpath = "")
	private WebElement BPActivityList;
	
	@FindBy(xpath = "//a[@href='/app/lead/overview']")
	private WebElement LeadOverview;

	@FindBy(xpath = "//nav[@class='grid gap-1 px-2']/div[2]")
	private WebElement LeadListGroup;

	@FindBy(xpath = "//a[@href='/app/lead/list']")
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
	
	public void ActivityGroup() {
		ActivityGroup.click();
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
	
	public void LeadListGroup() {
		LeadListGroup.click();
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
