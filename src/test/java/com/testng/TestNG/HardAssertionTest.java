package com.testng.TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssertionTest {

    @Test
    public void loginTest() {
        System.out.println("Login Test Started");

        String expectedTitle = "Login d Page";
        String actualTitle = "Login Page";

        // Hard Assertion
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match!");

        System.out.println("Login Test Completed"); // Will execute only if assertion passes
    }
}

