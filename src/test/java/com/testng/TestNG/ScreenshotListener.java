package com.testng.TestNG;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener{
	
	WebDriver driver;
	  public void onTestFailure(ITestResult result) {
	        System.out.println("Test Failed: " + result.getName());
	      //  TakesScreenshot ts=(TakesScreenshot) driver;
	        File sc=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(sc, new File("screenshots/" + result.getName() + ".png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}