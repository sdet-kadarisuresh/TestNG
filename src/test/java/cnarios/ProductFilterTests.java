package cnarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class ProductFilterTests {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.cnarios.com/challenges/product-filtering");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    // ---------- Locators ----------
    By productCards = By.xpath("//div[contains(@class,'border p-3')]"); 
    By productName = By.xpath(".//p[1]");   // first <p> = product name
    By productDetails = By.xpath(".//p[2]"); // second <p> = category • price • rating
    By inStockLabel = By.xpath(".//span[contains(@class,'MuiTypography-caption')]");

    // ---------- Test: Filter products by category ----------
    @Test(priority = 1)
    public void testFilterByCategory() {
        // Open dropdown
        driver.findElement(By.xpath("//div[@role='combobox']")).click();
        // Select Electronics
        driver.findElement(By.xpath("//li[normalize-space()='Electronics']")).click();

        // Fetch all product cards
        List<WebElement> cards = driver.findElements(productCards);

        for (WebElement card : cards) {
            String name = card.findElement(productName).getText();
            String details = card.findElement(productDetails).getText(); 
            String stock = card.findElement(inStockLabel).getText();

            // Split details -> Category, Price, Rating
            String[] parts = details.split("•");
            String category = parts[0].trim();
            String price = parts[1].trim();
            String rating = parts[2].replace("⭐","").trim();

            System.out.println("Name: " + name);
            System.out.println("Category: " + category);
            System.out.println("Price: " + price);
            System.out.println("Rating: " + rating);
            System.out.println("Stock: " + stock);
            System.out.println("---------------------------");

            // Assert category matches Electronics
            Assert.assertEquals(category, "Electronics", "Product not matching category!");
        }
    }
}
