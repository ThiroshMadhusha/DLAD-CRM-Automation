package com.dlad.qa.pages;

import org.openqa.selenium.By;
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

    /**
     * Locators
     */
	
	@FindBy(xpath = "//h3[@class='tracking-tight text-sm font-medium']")
	private WebElement homePageHeaderTitle;
	
    @FindBy(xpath = "//h3[contains(text(), 'Owned Leads')]")
    private WebElement leadsLabel;

    @FindBy(xpath = "//h3[contains(text(), 'Owned BP')]")
    private WebElement ownBpsLabel;

    @FindBy(xpath = "//h3[contains(text(), 'Lead Activities')]")
    private WebElement leadActivitiesLabel;

    @FindBy(xpath = "//h3[contains(text(), 'BP Activities')]")
    private WebElement bpActivitiesLabel;

    @FindBy(xpath = ".//div[@class='text-2xl font-bold']")
    private WebElement countElement;

    /**
     * Actions
     * @param labelElement
     * @return
     */
    
    public String getLabelText(WebElement labelElement) {
        return labelElement.getText();
    }

    public String getCount(WebElement labelElement) {
        WebElement parentDiv = labelElement.findElement(By.xpath("./ancestor::div[@class='rounded-xl border bg-card text-card-foreground shadow']"));
        WebElement countElement = parentDiv.findElement(By.xpath(".//div[@class='text-2xl font-bold']"));
        return countElement.getText();
    }
	
	public String homePageHeaderTitle() {
		String HomePageHeaderName = homePageHeaderTitle.getText();
		return HomePageHeaderName;
	}
	
	public String leadLabel() {
		String leadLabelName = leadsLabel.getText();
		return leadLabelName;
	}

	public String ownBpsLabel() {
		String leadLabelName = ownBpsLabel.getText();
		return leadLabelName;
	}
	
	public String leadActivitiesLabel() {
		String leadLabelName = leadActivitiesLabel.getText();
		return leadLabelName;
	}
	
	public String bpActivitiesLabel() {
		String leadLabelName = bpActivitiesLabel.getText();
		return leadLabelName;
	}

    public String getOwnLeadsCount() {
        return getCount(leadsLabel);
    }

    public String getOwnBpsCount() {
        return getCount(ownBpsLabel);
    }

    public String getLeadActivitiesCount() {
        return getCount(leadActivitiesLabel);
    }

    public String getBpActivitiesCount() {
        return getCount(bpActivitiesLabel);
    }
}

