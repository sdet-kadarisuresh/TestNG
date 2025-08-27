package opensource;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTests extends BaseTest {

    @Test
    public void googleSearchTest() {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("OpenAI GPT-5");
        searchBox.sendKeys(Keys.ENTER);
        System.out.println("Google search executed");
        Assert.assertTrue(driver.getTitle().contains("OpenAI"), "Google search failed");
    }

    @Test
    public void facebookTest() {
        driver.get("https://www.facebook.com");
        WebElement emailBox = driver.findElement(By.id("email"));
        emailBox.sendKeys("demo@example.com");
        WebElement passBox = driver.findElement(By.id("pass"));
        passBox.sendKeys("demo123");
        System.out.println("Facebook login simulated");
        // Not clicking login to avoid real login
        Assert.assertTrue(emailBox.isDisplayed(), "Facebook test failed");
    }

    @Test
    public void amazonTest() {
        driver.get("https://www.amazon.com");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Laptop");
        searchBox.sendKeys(Keys.ENTER);
        System.out.println("Amazon search executed");
        Assert.assertTrue(driver.getTitle().contains("Laptop"), "Amazon search failed");
    }

    @Test
    public void flipJetTest() {
        driver.get("https://www.flipjet.com"); // Non-existing website to simulate failure
        System.out.println("Attempted to open FlipJet");
        // This will fail and trigger failure listener later
        Assert.assertTrue(driver.getTitle().contains("FlipJet"), "FlipJet site not found");
    }
}
