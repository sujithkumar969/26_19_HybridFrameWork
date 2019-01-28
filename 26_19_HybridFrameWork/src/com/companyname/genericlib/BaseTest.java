package com.companyname.genericlib;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest implements AutoConstants {
	
	public WebDriver driver;
	public static ExtentReports extentReport;
	public static ExtentTest test;
	public static ArrayList<String> ssPath = null;
	public static FileInputStream emailFis;
	public static Properties emailProps;
	public static Logger loggerObject = Logger.getLogger(BaseTest.class);    // configure :- log4j //
		
	@BeforeSuite
	public void initialisation() {
		// all screen shot paths will be stored in sspath variable //
		ssPath = new ArrayList<String>();
		// initializing extent report //
		extentReport = new ExtentReports(reportPath);
		
		// properties file initialization //
		try {		
			emailFis = new FileInputStream(emailPropFilePath);
	    	emailProps = new Properties();
	    	emailProps.load(emailFis);
			emailFis.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@BeforeMethod(alwaysRun=true)
	@Parameters({"browser","appUrl"})
	public void preCondition(Method testName, String browser, String appUrl) {
		
		driver = DriverUtils.getWebDriver(browser);
		String name = testName.getName();
		
		driver.get(appUrl);
		test = LogUtils.startTestCase(name, "Test Suite Execution Started");
	}
	
	@AfterMethod(alwaysRun=true)
	public void postCondition(ITestResult testResult) {
		
		String testName = testResult.getName();
		int testStatus = testResult.getStatus();
		
		if(testStatus == 2) {                          // 2 is failure, 1 is success, 3 is skip //
			String Path = LibraryUtils.getScreenShot(driver, screenShotDirectory, testName);
			ssPath.add(Path);
			String imagePath = test.addScreenCapture(Path);
			LogUtils.endLogs();
            LogUtils.testFail("Test is Failed", imagePath);
		}
		else if (testStatus == 1) {
			LogUtils.endLogs();
			LogUtils.testPass("Test is Passed");
		}
		else if (testStatus == 3) {
			LogUtils.endLogs();
			LogUtils.testSkip("Test is Skipped");
		}
		LogUtils.endTestCase();
		driver.quit();
	}
	 
	@AfterSuite
	public void tearDown() {
		
		int smtpPortNo = Integer.parseInt(emailProps.getProperty("smtpPortNo"));
		Boolean SSLOnConet = Boolean.parseBoolean(emailProps.getProperty("SSLOnConet"));		
		extentReport.flush();
		try{			
			LibraryUtils.sendReportByGMail(emailProps.getProperty("hostName"), emailProps.getProperty("fromAddress"), 
			emailProps.getProperty("fromPassword"), SSLOnConet, smtpPortNo, emailProps.getProperty("fromText"), emailProps.getProperty("emailSubject"), 
			emailProps.getProperty("emailBody"), emailProps.getProperty("toAddress1"));
			
		//	LogUtils.info("Email Successfully Sent with Attachments");
			System.out.println("Email Successfully Sent with Attachments");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
