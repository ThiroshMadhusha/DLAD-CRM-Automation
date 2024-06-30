package com.dlad.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	
	@FindBy(xpath = "//h3[@class='tracking-tight text-sm font-medium']")
	private WebElement homePageHeaderTitle;
	
	@FindBy(xpath = "//h3[contains(text(), 'Leads')]")
	private WebElement ownLeadsCard;
	
	@FindBy(xpath = "//h3[contains(text(), 'Own BPs')]")
	private WebElement ownBpsCard;
	
	@FindBy(xpath = "//h3[contains(text(), 'Today Activities')]")
	private WebElement todayActivitiesCard;
	
	@FindBy(xpath = "//h3[contains(text(), 'Due Activities')]")
	private WebElement dueActivitiesCard;
	
	@FindBy(xpath = "//h3[contains(text(), 'Own Leads')]")
	private WebElement ownLeadsLabel;
	
	@FindBy(xpath = "//h3[contains(text(), 'Own BPs')]")
	private WebElement ownBpsLabel;
	
	@FindBy(xpath = "//h3[contains(text(), 'Today Activities')]")
	private WebElement TodayActivityLabel;
	
	@FindBy(xpath = "//h3[contains(text(), 'Due Activities')]")
	private WebElement dueActivityLabel;
	
	@FindBy(xpath = "./ancestor::div[@class='rounded-xl border text-card-foreground shadow bg-cyan-900']")
	private WebElement ownLeadsParentDiv;
	
	@FindBy(xpath = ".//div[@class='text-2xl font-bold text-white']")
	private WebElement ownLeadsCounts;
	
	@FindBy(xpath = "/ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']")
	private WebElement ParentDiv;
	
	@FindBy(xpath = ".//div[@class='text-2xl font-bold']")
	private WebElement Counts;
	
	/*
	 * Actions
	 */
	
	public String homePageHeaderTitle() {
		String HomePageHeaderName = homePageHeaderTitle.getText();
		return HomePageHeaderName;
	}
	
	public String ownLeadsCard() {
		String ownLeadsCardText = ownLeadsCard.getText();
		return ownLeadsCardText;
	}
	
	public String ownBpsCard() {
		String ownBpsCardText = ownBpsCard.getText();
		return ownBpsCardText;
	}
	
	public String todayActivitiesCard() {
		String todayActivitiesCardText = todayActivitiesCard.getText();
		return todayActivitiesCardText;
	}
	
	public String dueActivitiesCard() {
		String dueActivitiesCardText = dueActivitiesCard.getText();
		return dueActivitiesCardText;
	}
	
}
