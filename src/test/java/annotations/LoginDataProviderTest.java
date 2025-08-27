package annotations;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDataProviderTest {

	@DataProvider(name="loginTest")
		public Object[][] getData(){
			return new Object[][] {
			{"user1","password1"},
			{"user2","password2"},
			{	"user3","password3"},
			{	"user4","password4"},
			{	"user5","password5"},
			{	"user6","password6"}
			};
		}
	
	@Test(dataProvider = "loginTest" )
	public void Login(String username,String password) {
        System.out.println("Login Test with Username: " + username + " Password: " + password);
        System.out.println("Open Browser");
        System.out.println("Navigate to Login Page");
        System.out.println("Enter Username: " + username);
        System.out.println("Enter Password: " + password);
        System.out.println("Click Login");
        System.out.println("Close Browser\n");
		
	}
	
	@DataProvider(name = "loginData1")
	public Object[][] getData1() {
	    return new Object[][] {
	        {"user1", "pass1", "admin"},
	        {"user2", "pass2", "manager"},
	        {"user3", "pass3", "customer"}
	    };
	}

	@Test(dataProvider = "loginData1")
	public void loginTest(String username, String password, String role) {
	    System.out.println("Username: " + username + ", Password: " + password + ", Role: " + role);
	}
	
	
	  // Step 1: DataProvider with 3 usernames
    @DataProvider(name = "loginData2")
    public Object[][] getLoginData2() {
        return new Object[][] {
            {"user1", "admin"},      // Row 1
            {"user2", "manager"},    // Row 2
            {"user3", "guest"}       // Row 3
        };
    }

    // Step 2: Login test using DataProvider
    @Test(dataProvider = "loginData2")
    public void loginTest(String username, String role) {
        System.out.println("Executing login for: " + username + " with role: " + role);

        // Simulate a failure for demonstration
        if(username.equals("user2")) {
            Assert.fail("Login failed for " + username);
        } else {
            Assert.assertTrue(true);
        }
    }

	}

