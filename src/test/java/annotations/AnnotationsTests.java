package annotations;

import org.testng.annotations.*;

public class AnnotationsTests {

    @BeforeMethod
    public void setup() {
        System.out.println("Setup: Open Browser & Navigate to URL");
    }

    @Test(priority = 1)
    public void loginTest() {
        System.out.println("Login Test");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void searchProductTest() {
        System.out.println("Search Product Test");
    }

    @Test(priority = 3, dependsOnMethods = {"searchProductTest"})
    public void addToCartTest() {
        System.out.println("Add to Cart Test");
    }

    @Test(priority = 4, enabled = false, dependsOnMethods = {"loginTest"})
    public void paymentTest() {
        System.out.println("Payment Test - Skipped");
    }

    @AfterMethod
    public void teardown() {
        System.out.println("Teardown: Logout & Close Browser");
    }
}
