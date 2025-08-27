package FlipkartSuite;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Listeners(CustomListener.class)
public class ECommerceTests extends BaseTest {

    @Test(retryAnalyzer = RetryFailedTest.class)
    public void loginTest() {
        System.out.println("Executing Login Test");
        try {
            // Close login popup
            WebElement closePopup = driver.findElement(By.cssSelector("button._2KpZ6l._2doB4z"));
            closePopup.click();
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail("Login popup handling failed");
        }
    }

    @Test(retryAnalyzer = RetryFailedTest.class)
    public void searchTest() {
        System.out.println("Executing Search Test");
        try {
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("iPhone 15");
            searchBox.submit();
            Thread.sleep(2000); // Wait for results
            Assert.assertTrue(driver.getTitle().contains("iPhone"));
        } catch (Exception e) {
            Assert.fail("Search failed");
        }
    }

    @Test(retryAnalyzer = RetryFailedTest.class)
    public void checkoutTest() {
        System.out.println("Executing Checkout Test");
        try {
            // Click first product
            WebElement firstProduct = driver.findElement(By.cssSelector("div._1AtVbE:first-child a"));
            firstProduct.click();
            Thread.sleep(2000);

            // Switch to product tab
            for (String win : driver.getWindowHandles()) {
                driver.switchTo().window(win);
            }

            // Simulate add to cart failure (force fail randomly)
            boolean addToCartSuccess = Math.random() > 0.5;
            if (!addToCartSuccess) {
                Assert.fail("Add to cart failed (simulated)");
            }

            WebElement addToCart = driver.findElement(By.cssSelector("button._2KpZ6l._2U9uOA._3v1-ww"));
            addToCart.click();

            Thread.sleep(2000);

            WebElement proceedToBuy = driver.findElement(By.cssSelector("button._2KpZ6l._3HqJxg"));
            proceedToBuy.click();

        } catch (Exception e) {
            Assert.fail("Checkout process failed");
        }
    }
}
