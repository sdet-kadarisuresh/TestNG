package annotations.Parameters;


import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchTests {

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
        System.out.println("SearchTests - Browser launched: " + browser);
    }

    @Test
    public void searchProductTest() {
        System.out.println("SearchTests - Executing searchProductTest on thread: " + Thread.currentThread().getId());
        // Add Selenium code for searching a product
    }

    @Test
    public void filterProductTest() {
        System.out.println("SearchTests - Executing filterProductTest on thread: " + Thread.currentThread().getId());
        // Add Selenium code for applying filters
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("SearchTests - Browser closed");
    }
}
