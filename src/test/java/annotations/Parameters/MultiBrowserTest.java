package annotations.Parameters;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MultiBrowserTest {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://example.com");
        System.out.println("Browser launched: " + browser);
    }

    @Test
    public void loginTest() {
        System.out.println("Login Test running on browser");
        // Selenium code for login
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
        System.out.println("Browser closed");
    }
}
