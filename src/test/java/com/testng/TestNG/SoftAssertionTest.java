package com.testng.TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionTest {

    @Test
    public void searchTest() {
        SoftAssert softAssert = new SoftAssert();

        System.out.println("Search Test Started");
        
       // String expectedTitle = "Login Page";
       // String actualTitle = "Login Page";

        //  Assertion
       // softAssert.assertEquals(actualTitle, expectedTitle, "Title does not match!");

        softAssert.assertEquals("ActualTitle", "expectedTitle", "Title mismatch!");
        System.out.println("This line executes even if assertion fails");

        softAssert.assertTrue(true, "Condition failed");

        softAssert.assertAll(); // Important: triggers all assertion results
        System.out.println("Search Test Completed"); // Will execute only after assertAll()
    }
}
