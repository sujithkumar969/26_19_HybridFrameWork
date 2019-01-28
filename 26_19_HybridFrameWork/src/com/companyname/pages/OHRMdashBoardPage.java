package com.companyname.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.companyname.genericlib.BasePage;
import com.companyname.genericlib.DriverUtils;
import com.companyname.genericlib.LogUtils;

public class OHRMdashBoardPage extends BasePage {
	
    @FindBy(xpath="//h1[text()='Dashboard']")
    private WebElement eleDashBoardText;
	
	public OHRMdashBoardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void validateDashboardText(WebDriver driver, String expectedTextOnScreen, String logInformation) {
		String dashBoardText = eleDashBoardText.getText();
		DriverUtils.validateTextOnScreen(driver, eleDashBoardText, expectedTextOnScreen, logInformation);
	}

}
