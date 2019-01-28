package com.companyname.analyser;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

// number of times the failed scripts should execute //

public class RetryLogic implements IRetryAnalyzer {
	
	int counter = 1;
	int retryLimit = 3;

	@Override
	public boolean retry(ITestResult result) {
		
		if(counter <= retryLimit) {
			counter++;
			return true;
		}
		return false;
	}
}
