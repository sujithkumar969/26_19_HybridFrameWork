package com.companyname.genericlib;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LogUtils extends BaseTest {
	
	
	public static ExtentTest startTestCase(String testName, String message) {
		ExtentTest extentTest = BaseTest.extentReport.startTest(testName, message);
		loggerObject.info("****************************************************************************************");
		loggerObject.info("****************************************************************************************");
		loggerObject.info("$$$$$$$$$$$$$$$$$$$$$                 " +testName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		loggerObject.info("****************************************************************************************");
		loggerObject.info("****************************************************************************************");
		return extentTest;
	}
	
	public static void endTestCase() {
		extentReport.endTest(test);
	}
	
	public static void endLogs() {
		loggerObject.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
		loggerObject.info("X");
		loggerObject.info("X");
		loggerObject.info("X");
		loggerObject.info("X");
	}
	
	public static void testPass(String message) {
	    test.log(LogStatus.PASS, message, "Successful");
	    loggerObject.info(message);
	}
	
	public static void testFail(String message, String imagePath) {
	    test.log(LogStatus.FAIL, message, imagePath);
	    loggerObject.info(message);
	}
	
	public static void testSkip(String message) {
		test.log(LogStatus.SKIP, message, "Successful");
		loggerObject.info(message);
	}
	
	public static void info(String message) {
		test.log(LogStatus.INFO, message, "Successful");
		loggerObject.info(message);
	}
	
	public static void fatal(String message, Throwable t) {
		test.log(LogStatus.FATAL, message, "Successful");
		loggerObject.fatal(message, t);
	}
	
	public static void warning(String message, Throwable t) {
		test.log(LogStatus.WARNING, message, "Successful");
		loggerObject.warn(message, t);
	}

	public static void error(String message, Throwable t) {
		test.log(LogStatus.ERROR, message, "Successful");
		loggerObject.error(message, t);
	}
	
	public static void debug(String message, Throwable t) {
		test.log(LogStatus.UNKNOWN, message, "Successful");
		loggerObject.debug(message, t);
	}
}