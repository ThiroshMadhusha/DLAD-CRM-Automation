package com.dlad.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BPActivityOverviewPage {

	WebDriver driver;

	public BPActivityOverviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}

	@FindBy(xpath = "//div[@class='flex gap-3 items-center']/h1")
	private WebElement LeadActivityPageTitle;

	@FindBy(xpath = "//h3[contains(text(), 'Dead')]")
	private WebElement DeadLabel;

	@FindBy(xpath = "//h3[contains(text(), 'Converted')]")
	private WebElement ConvertedLabel;

	@FindBy(xpath = "//h3[contains(text(), 'Account Application Received')]")
	private WebElement AccountApplicationReceiveLabel;

	@FindBy(xpath = "//h3[contains(text(), 'Pricing')]")
	private WebElement PricingLabel;

	@FindBy(xpath = "//h3[contains(text(), '4-Account Application Sent')]")
	private WebElement AccountApplicationSentLabel;

	@FindBy(xpath = "//h3[contains(text(), 'Sampling')]")
	private WebElement SamplingLabel;

	@FindBy(xpath = "//h3[contains(text(), 'Contact Made')]")
	private WebElement ContactMadeLabel;

	@FindBy(xpath = "//h3[contains(text(), 'New')]")
	private WebElement NewLabel;

    public String getLabelText(WebElement labelElement) {
        return labelElement.getText();
    }

    public String getCount(WebElement labelElement) {
        WebElement parentDiv = labelElement.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement countElement = parentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        return countElement.getText();
    }

	public String DeadLabel() {
		String DeadLabelName = DeadLabel.getText();
		return DeadLabelName;
	}

	public String ConvertedLabel() {
		String DeadLabelName = ConvertedLabel.getText();
		return DeadLabelName;
	}

	public String AccountApplicationReceiveLabel() {
		String AccountApplicationReceiveLabelName = AccountApplicationReceiveLabel.getText();
		return AccountApplicationReceiveLabelName;
	}

	public String PricingLabel() {
		String PricingLabelName = PricingLabel.getText();
		return PricingLabelName;
	}

	public String AccountApplicationSentLabel() {
		String AccountApplicationSentLabelName = AccountApplicationSentLabel.getText();
		return AccountApplicationSentLabelName;
	}

	public String SamplingLabel() {
		String SamplingLabelName = SamplingLabel.getText();
		return SamplingLabelName;
	}

	public String ContactMadeLabel() {
		String ContactMadeLabelName = ContactMadeLabel.getText();
		return ContactMadeLabelName;
	}

	public String NewLabel() {
		String NewLabelName = NewLabel.getText();
		return NewLabelName;
	}


}