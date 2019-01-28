package com.companyname.analyser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

// it will execute the failed test scripts for multiple times(defined in RetryLogic.java) //

public class ReTestFailedScripts implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		annotation.setRetryAnalyzer(RetryLogic.class);
		
	}

}
