package annotations;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SecondTest {

	@BeforeClass
	public void setupClass() {
		System.out.println("BeforeClass: Open Browser");
	}

	@BeforeMethod
	public void setupMethod() {
		System.out.println("BeforeMethod: Navigate to URL");
	}

	@Test
	public void loginTest() {
		System.out.println("Executing Login Test");
	}

	@Test
	public void searchTest() {
		System.out.println("Executing Search Test");
	}
	
	@Test
    public void checkoutTest() {
        System.out.println("Executing Checkout Test"); 
    }

	@AfterMethod
	public void teardownMethod() {
		System.out.println("AfterMethod: Logout");
	}

	@AfterClass
	public void teardownClass() {
		System.out.println("AfterClass: Close Browser");
	}
}
