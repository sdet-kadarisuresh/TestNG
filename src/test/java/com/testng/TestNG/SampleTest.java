package com.testng.TestNG;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Listeners(CustomListener.class)
public class SampleTest {

    WebDriver driver;

    @Test
    public void testGoogleSearch() {
        driver = new ChromeDriver();
        driver.get("https://google.com");

        // Inject driver to listener
        org.testng.ITestContext context = org.testng.Reporter.getCurrentTestResult().getTestContext();
        context.setAttribute("driver", driver);

        // Example step (this will fail to test listener)
        org.testng.Assert.assertTrue(false);

        driver.quit();
    }
}

