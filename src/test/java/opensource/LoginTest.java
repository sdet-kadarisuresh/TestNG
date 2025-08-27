package opensource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        try {
            // Click My Account → Login
            WebElement myAccount = driver.findElement(By.xpath("//span[text()='My Account']"));
            myAccount.click();
            
            WebElement login = driver.findElement(By.linkText("Login"));
            login.click();
            
            System.out.println("Login page opened successfully");

            // Enter credentials
            WebElement email = driver.findElement(By.id("input-email"));
            email.sendKeys("demo@example.com");
            
            WebElement password = driver.findElement(By.id("input-password"));
            password.sendKeys("demo123");
            
            WebElement loginBtn = driver.findElement(By.cssSelector("input.btn.btn-primary"));
            loginBtn.click();

            // Verify login success by checking for "My Account" page
            WebElement logoutLink = driver.findElement(By.linkText("Logout"));
            Assert.assertTrue(logoutLink.isDisplayed(), "Login failed!");
            System.out.println("Login successful");

        } catch (Exception e) {
            Assert.fail("Login test failed: " + e.getMessage());
        }
    }
}