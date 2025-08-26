package annotations;

import org.testng.annotations.Test;

public class GroupTest {

    @Test(groups = {"smoke"})
    public void loginTest() {
        System.out.println("Login Test - Smoke Group");
    }

    @Test(groups = {"regression"})
    public void searchTest() {
        System.out.println("Search Test - Regression Group");
    }

    @Test(groups = {"smoke", "regression"})
    public void addToCartTest() {
        System.out.println("Add to Cart Test - Smoke & Regression Group");
    }

    @Test(groups = {"regression"})
    public void checkoutTest() {
        System.out.println("Checkout Test - Regression Group");
    }
    @Test(groups = {"sainty"})
    public void sanity()
    {
    	System.out.println("Sanity testing for recent fix issues");
    }
}
