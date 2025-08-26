package annotations;

import org.testng.annotations.Test;

public class DependencyTest {

    @Test
    public void loginTest() {
        System.out.println("Login Test");
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void searchTest() {
        System.out.println("Search Test");
    }

    @Test(dependsOnMethods = {"searchTest"})
    public void checkoutTest() {
        System.out.println("Checkout Test");
    }
}
