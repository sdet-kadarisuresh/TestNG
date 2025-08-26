package annotations;

import org.testng.annotations.*;

public class LoginTests {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("LoginTests: BeforeMethod - Navigate to Login Page--->method befour");
    }

	@BeforeClass
	public void setupClass() {
		System.out.println("BeforeClass: Open Browser====> befour login class");
	}

	@AfterClass
	public void teardownClass() {
		System.out.println("AfterClass: Close Browser ==>after login class ");
	}
	
	
    @Test
    public void loginValidUser() {
        System.out.println("LoginTests: Test - Valid User Login");
    }

    @Test
    public void loginInvalidUser() {
        System.out.println("LoginTests: Test - Invalid User Login");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("LoginTests: AfterMethod - Logout-----after method");
    }
}
