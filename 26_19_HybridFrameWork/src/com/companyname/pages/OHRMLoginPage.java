package com.companyname.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.companyname.genericlib.BasePage;
import com.companyname.genericlib.LogUtils;

public class OHRMLoginPage {
	
	@FindBy(id="txtUsername")
	private WebElement eleUserNameTextBox;
	
	@FindBy(id="txtPassword")
	private WebElement elePasswordTextBox;
	
	@FindBy(id="btnLogin")
	private WebElement eleLoginButton;
	
	@FindBy(xpath="//span[text()='Invalid credentials']")
	private WebElement eleWarningMsg;
	
	public OHRMLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterUserName(String userName) {
		eleUserNameTextBox.sendKeys(userName);
		LogUtils.info("Entered the user name");
	}
	
	public void enterPassword(String passWord) {
		elePasswordTextBox.sendKeys(passWord);
		LogUtils.info("Entered the password");
	}
	
	public void clickOnLoginButton() {
		eleLoginButton.click();
		LogUtils.info("Clicked on login button");
	}

}
