package com.companyname.genericlib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {     // This is super class for all Object Page classes //
	

	@FindBy(xpath="//a[text()='Welcome Admin']")
	WebElement eleWelcomeLink;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement eleLogoutLink;
	
	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnWelcomeLink() {
		eleWelcomeLink.click();
		LogUtils.info("clicked on welcome link");
	}
	
	public void moveToLogOutLink(WebDriver driver) {
		DriverUtils.action(driver, eleLogoutLink);
		LogUtils.info("navigating to logout link");
	}
	
	public void clickOnLogoutLink(WebDriver driver) {
		DriverUtils.getvisibilityOfElementOnScreen(driver, eleLogoutLink);
		eleLogoutLink.click();
		LogUtils.info("clicked on logout link");
	}
	
}
