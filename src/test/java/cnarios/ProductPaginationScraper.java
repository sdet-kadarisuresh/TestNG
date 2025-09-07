package cnarios;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPaginationScraper {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

		driver.get("https://www.cnarios.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("//a[@href='/explore']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Take Challenge')]")).click();
		   WebElement searchBtn = wait.until(
	                ExpectedConditions.elementToBeClickable(By.xpath("//body//div[@id='root']//div//div[1]//div[2]//button[1]"))
	        );

	        int y = searchBtn.getLocation().getY();
	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0] - 200);", y);

	        searchBtn.click();
        
        
        
        List<Map<String, String>> allProducts = new ArrayList<>();
        Map<String, Integer> categoryCount = new HashMap<>();

        boolean hasNext = true;

        while (hasNext) {

            List<WebElement> productCards = driver.findElements(By.xpath("//div[contains(@class,'product-card')]"));

            for (int i = 0; i < productCards.size(); i++) {
                Map<String, String> product = new HashMap<>();

                int retries = 0;
                boolean success = false;

                while (!success && retries < 3) {
                    try {
                        // Always re-locate product card fresh (prevents stale)
                        WebElement card = driver.findElements(By.xpath("//div[contains(@class,'product-card')]")).get(i);

                        String name = card.findElement(By.xpath(".//h2")).getText();
                        String price = card.findElement(By.xpath(".//span[contains(@class,'price')]")).getText();
                        String category = card.findElement(By.xpath(".//span[contains(@class,'category')]")).getText();

                        String rating = "No rating";
                        try {
                            rating = card.findElement(By.xpath(".//span[contains(@class,'rating')]")).getText();
                        } catch (Exception e) {
                            // ignore if not found
                        }

                        product.put("Name", name);
                        product.put("Price", price);
                        product.put("Category", category);
                        product.put("Rating", rating);

                        allProducts.add(product);

                        categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);

                        success = true; // got data, break retry loop
                    } catch (StaleElementReferenceException e) {
                        retries++;
                        Thread.sleep(500); // small wait before retry
                    }
                }
            }

            // Pagination: handle Next button
            List<WebElement> nextBtn = driver.findElements(By.xpath("//button[normalize-space()='Next']"));
            if (!nextBtn.isEmpty() && nextBtn.get(0).isEnabled()) {
                nextBtn.get(0).click();
                Thread.sleep(2000); // wait for next page to load
            } else {
                hasNext = false;
            }
        }

        // Print all products
        System.out.println("===== All Products =====");
        int index = 1;
        for (Map<String, String> product : allProducts) {
            System.out.println(index++ + ". " + product);
        }

        // Print category wise
        System.out.println("\n===== Category Wise Count =====");
        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Print total products
        System.out.println("\nTotal Products: " + allProducts.size());

        driver.quit();
    }
}
