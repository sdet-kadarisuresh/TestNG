package opensource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected String browser;

    // ExtentReports - static to share across all test instances
    protected static ExtentReports extent;
    protected ExtentTest test; // per-instance test object

    public BaseTest(String browser) {
        this.browser = browser;
    }

    @BeforeSuite
    public void setupReport() {
        if (extent == null) { // ensure single initialization
            ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/AutomationReport.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
    }

    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeMethod
    public void setUpBrowser() {
        System.out.println("Opening Browser: " + browser);
        switch (browser.toLowerCase()) {
            case "chrome": driver = new ChromeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            case "opera": driver = new EdgeDriver(); break;
            default: driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed: " + browser);
        }
    }
}
