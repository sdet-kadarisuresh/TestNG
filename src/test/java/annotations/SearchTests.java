package annotations;

import org.testng.annotations.*;

public class SearchTests {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("SearchTests: BeforeMethod - Navigate to Search Page");
    }

    @Test
    public void searchProduct() {
        System.out.println("SearchTests: Test - Search Product");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("SearchTests: AfterMethod - Clear Search");
    }
}
