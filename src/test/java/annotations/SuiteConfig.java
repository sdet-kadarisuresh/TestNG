package annotations;

import org.testng.annotations.*;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite: Initialize DB / Reports++++++Suite started++++++++++");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite: Close DB / Reports++++++++Suite ending++++++++++++");
    }


    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest: Setup for this module <----Start Test---------->");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest: Cleanup for this module<--------Ending Test--------->");
    }
}
