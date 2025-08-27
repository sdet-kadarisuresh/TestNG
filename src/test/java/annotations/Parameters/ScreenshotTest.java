package annotations.Parameters;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ScreenshotTest {

    WebDriver driver;

    @Test
    public void takeScreenshotTest() {
        // Initialize driver
        driver = new ChromeDriver();
        driver.get("https://example.com");

        try {
            // Take screenshot
            File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scr, new File("./screenshots/myScreenshot.png"));
            System.out.println("Screenshot saved successfully!");
        } catch (IOException e) {
            System.out.println("Error while saving screenshot: " + e.getMessage());
        } finally {
            driver.quit(); // Close browser
        }
    }
}
