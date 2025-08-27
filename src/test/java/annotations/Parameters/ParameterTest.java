package annotations.Parameters;

import org.testng.annotations.*;

public class ParameterTest {

    @Parameters({"username", "password"})
    @Test
    public void loginTest(String username, String password) {
        System.out.println("Login Test - Username: " + username + ", Password: " + password);
        // Here you can put Selenium code to login
    }
    
    @Parameters({"browser"})
    @BeforeMethod
    public void setup(String browser) {
        System.out.println("Setup Browser: " + browser);
        // Initialize WebDriver based on browser parameter
    }
    
    @Parameters({"env"})
    @BeforeTest
    public void setEnvironment(String env) {
        System.out.println("Running tests on environment: " + env);
    }
    
    @Parameters({"username","password","role"})
    @Test
    public void loginTest(String username, String password, String role) {
        System.out.println("Username: " + username + ", Password: " + password + ", Role: " + role);
    }

    
}
