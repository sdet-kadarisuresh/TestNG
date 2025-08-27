package annotations.Parameters;


import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTests {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://mail.google.com/"); // Replace with real URL
        System.out.println("LoginTests - Browser launched: " + browser);
    }

    @Test
    public void validLoginTest() {
        System.out.println("LoginTests - Executing validLoginTest on thread: " + Thread.currentThread().getId());
        // Add Selenium code for login here
    }

    @Test
    public void invalidLoginTest() {
        System.out.println("LoginTests - Executing invalidLoginTest on thread: " + Thread.currentThread().getId());
        // Add Selenium code for negative login scenario
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("LoginTests - Browser closed");
    }
}
