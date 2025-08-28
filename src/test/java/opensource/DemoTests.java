package opensource;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListener.class)
public class DemoTests extends BaseTest {

    public DemoTests(String browser) {
        super(browser);
    }

    @Test(dataProvider = "testData", retryAnalyzer = RetryFailedTest.class)
    public void runTest(String testName, String url, String input, String expected) {
        driver.get(url);

        switch (testName) {
            case "Google":
                WebElement searchBox = driver.findElement(By.name("q"));
                searchBox.sendKeys(input);
                searchBox.sendKeys(Keys.ENTER);
                Assert.assertTrue(driver.getTitle().contains(expected));
                break;
            case "Facebook":
                WebElement email = driver.findElement(By.id("email"));
                WebElement pass = driver.findElement(By.id("pass"));
                email.sendKeys(input);
                pass.sendKeys("demo123"); // or from Excel
                Assert.assertTrue(email.isDisplayed());
                break;
            case "Amazon":
                WebElement searchAmazon = driver.findElement(By.id("twotabsearchtextbox"));
                searchAmazon.sendKeys(input);
                searchAmazon.sendKeys(Keys.ENTER);
                Assert.assertTrue(driver.getTitle().contains(expected));
                break;
            case "FlipJet":
                Assert.assertTrue(driver.getTitle().contains("FlipJet"), "FlipJet site not found");
                break;
        }
    }

    @org.testng.annotations.DataProvider(name = "testData", parallel = true)
    public Object[][] getData() {
        return new Object[][]{
                {"Google", "https://www.google.com", "OpenAI GPT-5", "OpenAI"},
                {"Facebook", "https://www.facebook.com", "demo@example.com", ""},
                {"Amazon", "https://www.amazon.com", "Laptop", "Laptop"},
                {"FlipJet", "https://www.flipjet.com", "", "FlipJet"}
        };
    }
}
