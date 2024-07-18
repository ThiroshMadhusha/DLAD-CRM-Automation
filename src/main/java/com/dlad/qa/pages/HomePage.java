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

    @FindBy(xpath = "//a[@href='/app/lead-activity/list']/div/div[1]")
    private WebElement navigateToLeadActivity;

    @FindBy(xpath = "//a[@href='/app/bp-activity/list']/div/div[1]")
    private WebElement navigateToBpActivity;

    @FindBy(xpath = "//a[@href='/app/business-partners/list']/div/div[1]")
    private WebElement navigateToBusinessPartner;

    @FindBy(xpath = "//a[@href='/app/sales-orders/new']/div/div[1]")
    private WebElement navigateToNewOrder;
    
    @FindBy(xpath = "//a[@href='/app']")
    private WebElement clickOnHomeBtn;
    
    @FindBy(xpath = "//a[@href='/app/lead-activity/list']/div/div/h3[contains(text(), 'Lead Activity')]")
    private WebElement navigatioNToupleCardLeadActivityLabel;
    
    @FindBy(xpath = "//a[@href='/app/bp-activity/list']/div/div/h3[contains(text(), 'BP Activity')]")
    private WebElement navigatioNToupleCardBpActivityLabel;
    
    @FindBy(xpath = "//a[@href='/app/business-partners/list']/div/div/h3[contains(text(), 'BP')]")
    private WebElement navigatioNToupleCardBusinessPartnerLabel;
    
    @FindBy(xpath = "//a[@href='/app/sales-orders/new']/div/div/h3[contains(text(), 'New Order')]")
    private WebElement navigatioNToupleCardNewOrderLabel;
    
    @FindBy(xpath = "//a[@href='/app/lead-activity/list']/div//div[@id='LeadActivity-ICON']")
    private WebElement leadActivityIconSVG;
    
    @FindBy(xpath = "//div[@id='BPActivity-ICON']")
    private WebElement bpActivityIconSVG;
    
    @FindBy(xpath = "//div[@id='BP-ICON']")
    private WebElement businessPartnerIconSVG;
    
    @FindBy(xpath = "//div[@id='NewOrder-ICON']")
    private WebElement salesNewOrderIconSVG;
    /**
     * Actions
     * @param labelElement
     * @return
     */
	
	public boolean leadActivityIconSVG() {
		boolean leadActivityIcon = leadActivityIconSVG.isDisplayed();
		return leadActivityIcon;
	}
	
	public boolean bpActivityIconSVG() {
		boolean bpActivityIcon = bpActivityIconSVG.isDisplayed();
		return bpActivityIcon;
	}
	
	public boolean businessPartnerIconSVG() {
		boolean businessPartnerIcon = businessPartnerIconSVG.isDisplayed();
		return businessPartnerIcon;
	}
	
	public boolean salesNewOrderIconSVG() {
		boolean salesNewOrderIcon = salesNewOrderIconSVG.isDisplayed();
		return salesNewOrderIcon;
	}
	
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
    
    public void navigateToLeadActivity() {
		navigateToLeadActivity.click();
	}
	
	public void navigateToBpActivity() {
		navigateToBpActivity.click();
	}

	public void navigateToBusinessPartner() {
		navigateToBusinessPartner.click();
	}

	public void navigateToNewOrder() {
		navigateToNewOrder.click();
	}
	
	public void clickOnHomeBtn() {
		clickOnHomeBtn.click();
	}
	
	public String navigatioNToupleCardLeadActivityLabel() {
		String navigatioNToupleCardLeadActivityLabelName = navigatioNToupleCardLeadActivityLabel.getText();
		return navigatioNToupleCardLeadActivityLabelName;
	}
	
	public String navigatioNToupleCardBpActivityLabel() {
		String navigatioNToupleCardBpActivityLabelName = navigatioNToupleCardBpActivityLabel.getText();
		return navigatioNToupleCardBpActivityLabelName;
	}
	
	public String navigatioNToupleCardBusinessPartnerLabel() {
		String navigatioNToupleCardBusinessPartnerLabelName = navigatioNToupleCardBusinessPartnerLabel.getText();
		return navigatioNToupleCardBusinessPartnerLabelName;
	}
	
	public String navigatioNToupleCardNewOrderLabel() {
		String navigatioNToupleCardNewOrderLabelName = navigatioNToupleCardNewOrderLabel.getText();
		return navigatioNToupleCardNewOrderLabelName;
	}
    
}

