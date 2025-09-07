package cnarios;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductListingTest {

    static WebDriver driver;
    static WebDriverWait wait;

    // Product POJO
    static class Product {
        String id;
        String name;
        String price;
        double priceValue;
        String category;
        double rating;

        @Override
        public String toString() {
            return "[ID=" + id + ", Name=" + name + ", Price=" + price +
                    ", Category=" + category + ", Rating=" + rating + "]";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.cnarios.com/"); // open site
        driver.findElement(By.xpath("//a[@href='/explore']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Take Challenge')]")).click();
        
        driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();

        // ====== Collect all products ======
        List<Product> allProducts = getAllProducts();

        System.out.println("\n===== All Products Collected =====");
        allProducts.forEach(System.out::println);
        System.out.println("Total Products: " + allProducts.size());

        // ====== Category wise count ======
        System.out.println("\n===== Category Wise Count =====");
        Map<String, Long> categoryCount = allProducts.stream()
                .collect(Collectors.groupingBy(p -> p.category, Collectors.counting()));
        categoryCount.forEach((cat, count) -> System.out.println(cat + " : " + count));

        // ====== Find a specific product and its page ======
        String searchName = "Nike Air Force 1 Sneakers"; // sample product
        int pageFound = findProductPage(allProducts, searchName);
        System.out.println("\nProduct [" + searchName + "] found on page: " + pageFound);

        // ====== Highest rated per category ======
        System.out.println("\n===== Highest Rated Products Per Category =====");
        highestRatedPerCategory(allProducts);

        // ====== Most expensive per category ======
        System.out.println("\n===== Most Expensive Products Per Category =====");
        mostExpensivePerCategory(allProducts);

        // ====== Verify pagination ======
        System.out.println("\n===== Verifying Pagination =====");
        verifyPagination();

        driver.quit();
    }

    // Scrape all products across pages
    private static List<Product> getAllProducts() throws InterruptedException {
        List<Product> allProducts = new ArrayList<>();
        boolean hasNext = true;
        int page = 1;

        while (hasNext) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'MuiCard-root')]")));

            List<WebElement> productCards = driver.findElements(By.xpath("//div[contains(@class,'MuiCard-root')]"));
            for (int i = 0; i < productCards.size(); i++) {
                try {
                    WebElement card = driver.findElements(By.xpath("///div[contains(@class,'MuiCard-root')]")).get(i);

                    Product p = new Product();
                    try { p.id = card.getAttribute("data-id"); } catch (Exception e) { p.id = "NA"; }
                    try { p.name = card.findElement(By.xpath(".//h6[1]")).getText(); } catch (Exception e) { p.name = "NA"; }
                    try { p.price = card.findElement(By.xpath(".//span[contains(@class,'price')]")).getText(); } catch (Exception e) { p.price = "0"; }
                    try {
                        p.priceValue = Double.parseDouble(p.price.replaceAll("[^0-9.]", ""));
                    } catch (Exception e) { p.priceValue = 0.0; }
                    try { p.category = card.findElement(By.xpath(".//span[contains(@class,'category')]")).getText(); } catch (Exception e) { p.category = "Unknown"; }
                    try { 
                        p.rating = Double.parseDouble(card.findElement(By.xpath(".//span[contains(@class,'rating')]")).getText());
                    } catch (Exception e) { p.rating = 0.0; }

                    allProducts.add(p);
                } catch (StaleElementReferenceException se) {
                    i--; // retry current card
                    Thread.sleep(200);
                }
            }

            System.out.println("Page " + page + " products collected: " + productCards.size());

            // Next page
            List<WebElement> nextBtn = driver.findElements(By.xpath("//button[normalize-space()='Next']"));
            if (!nextBtn.isEmpty() && nextBtn.get(0).isEnabled()) {
                nextBtn.get(0).click();
                Thread.sleep(1500);
                page++;
            } else {
                hasNext = false;
            }
        }
        return allProducts;
    }

    // Find product page number
    private static int findProductPage(List<Product> products, String productName) {
        int perPage = 10; // adjust if different
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).name.equalsIgnoreCase(productName)) {
                return (i / perPage) + 1;
            }
        }
        return -1;
    }

    // Highest rated per category
    private static void highestRatedPerCategory(List<Product> products) {
        Map<String, Optional<Product>> maxRating = products.stream()
                .collect(Collectors.groupingBy(p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.rating))));
        maxRating.forEach((cat, prod) -> System.out.println(cat + " -> " + prod.get()));
    }

    // Most expensive per category
    private static void mostExpensivePerCategory(List<Product> products) {
        Map<String, Optional<Product>> maxPrice = products.stream()
                .collect(Collectors.groupingBy(p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.priceValue))));
        maxPrice.forEach((cat, prod) -> System.out.println(cat + " -> " + prod.get()));
    }

    // Verify pagination functionality
    private static void verifyPagination() throws InterruptedException {
        // Example: Click Prev, Next, specific page
        try {
            // Click page number 3
            driver.findElement(By.xpath("//button[normalize-space()='3']")).click();
            Thread.sleep(1000);
            System.out.println("Page 3 loaded successfully");

            // Click Next
            driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();
            Thread.sleep(1000);
            System.out.println("Next page loaded successfully");

            // Click Prev
            driver.findElement(By.xpath("//button[normalize-space()='Prev']")).click();
            Thread.sleep(1000);
            System.out.println("Prev page loaded successfully");

            // Navigate first page
            driver.findElement(By.xpath("//button[normalize-space()='1']")).click();
            Thread.sleep(1000);
            System.out.println("First page loaded successfully");

        } catch (Exception e) {
            System.out.println("Pagination verification failed: " + e.getMessage());
        }
    }
}
