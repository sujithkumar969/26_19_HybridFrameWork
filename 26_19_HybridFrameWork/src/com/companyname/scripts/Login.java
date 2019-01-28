/**
 * 
 */
package com.companyname.scripts;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.companyname.applndata.OHRMdata;
import com.companyname.genericlib.BaseTest;
import com.companyname.genericlib.DataDriven;
import com.companyname.genericlib.DriverUtils;
import com.companyname.pages.OHRMLoginPage;
import com.companyname.pages.OHRMdashBoardPage;

/**
 * @author S SUJITH KUMAR
 *
 */
public class Login extends BaseTest {
	
	String userName = "";
	String passWord = "";
	
	@Factory(dataProvider="loginPageData", dataProviderClass=OHRMdata.class)
	public Login(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	@Test
	public void testLogin() {
		
		OHRMLoginPage login = new OHRMLoginPage(driver);
		login.enterUserName(userName);
		login.enterPassword(passWord);
		login.clickOnLoginButton();
		
		OHRMdashBoardPage dashBoard = new OHRMdashBoardPage(driver);
		dashBoard.validateDashboardText(driver, "Dashboard", "Dashboard text is validated");
		dashBoard.clickOnWelcomeLink();
		dashBoard.clickOnLogoutLink(driver);
		
	}

}
