package annotations;

import org.testng.annotations.*;

public class SearchTests {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("SearchTests: BeforeMethod - Navigate to Search Page ________>BeforeMethod search ");
    }

    @Test
    public void searchProduct() {
        System.out.println("SearchTests: Test - Search Product");
    }
    
	@BeforeClass
	public void setupClass() {
		System.out.println("BeforeClass: Open Browser-----------> Before search class");
	}

	@AfterClass
	public void teardownClass() {
		System.out.println("AfterClass: Close Browser ==>after search class ");
	}
	

    @AfterMethod
    public void afterMethod() {
        System.out.println("SearchTests: AfterMethod - Clear Search _______>after method search");
    }
}
