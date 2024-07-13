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
	
	/*
	 *  Business Partners List
	 */

	@FindBy(xpath = "//nav[@class='grid gap-1 px-2']/div[1]")
	private WebElement BusinessPartnersGroup;
	
	@FindBy(xpath = "//a[@href='/app/business-partners/own-list']")
	private WebElement MyBpsList;
	
	@FindBy(xpath = "//a[@href='/app/bp-activity/overview']")
	private WebElement BusinessPartnersOverview;
	
	@FindBy(xpath = "//a[@href='/app/bp-activity/list']")
	private WebElement BPsActivityList;
	
	@FindBy(xpath = "//a[@href='/app/business-partners/list']")
	private WebElement BusinessPartnerFullList;
	
	/*
	 *  Lead Activity List
	 */
	@FindBy(xpath = "//nav[@class='grid gap-1 px-2']/div[2]")
	private WebElement LeadListGroup;

	@FindBy(xpath = "//a[@href='/app/lead/own-list']")
	private WebElement MyOwnLeadList;
	
	@FindBy(xpath = "//a[@href='/app/lead-activity/overview']")
	private WebElement LeadActivityOverview;
	
	@FindBy(xpath = "//a[@href='/app/lead-activity/list']")
	private WebElement LeadActivityList;

	@FindBy(xpath = "//a[@href='/app/lead/list']")
	private WebElement LeadList;
	
	/*
	 * Common
	 */
	
	@FindBy(xpath = "//a[@href='/app/sales-quotations/list']")
	private WebElement Quotations;
	
	@FindBy(xpath = "//a[@href='/app/sales-orders/list']")
	private WebElement SalesOrders;
	
	@FindBy(xpath = "//a[@href='/app/calendar']")
	private WebElement Calander;
	
	@FindBy(xpath = "//div[@class='border-b h-[64px] bg-white border-b-gray-300 flex items-center justify-between px-3']/button")
	private WebElement ProfileIcon;
	
	// Actions
	
	public void HomeTab() {
		HomeTab.click();
	}
	
	/*
	 *  Business Partners List
	 */
	
	public void BusinessPartnersGroup() {
		BusinessPartnersGroup.click();
	}
	
	public void MyBpsList() {
		MyBpsList.click();
	}

	public void BusinessPartnersOverview() {
		BusinessPartnersOverview.click();
	}
	
	public void BPsActivityList() {
		BPsActivityList.click();
	}
	
	public void BusinessPartnerFullList() {
		BusinessPartnerFullList.click();
	}
	
	/*
	 *  Lead Activity List
	 */
	
	public void LeadListGroup() {
		LeadListGroup.click();
	}
	
	public void MyOwnLeadList() {
		MyOwnLeadList.click();
	}
	
	public void LeadActivityOverview() {
		LeadActivityOverview.click();
	}
	
	public void LeadActivityList() {
		LeadActivityList.click();
	}
	
	public void LeadList() {
		LeadList.click();
	}
	/*
	 * Common
	 */
	
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
