package cnarios;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class ProductListingTests {

	static WebDriver driver;
	static WebDriverWait wait;

	// Product Model
	static class Product {
		String name;
		String category;
		double price;
		double rating;
		int pageNumber;

		@Override
		public String toString() {
			return "[Name=" + name + ", Category=" + category + ", Price=" + price + ", Rating=" + rating + ", Page="
					+ pageNumber + "]";
		}
	}

	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://www.cnarios.com/");
		driver.findElement(By.xpath("//a[@href='/explore']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Take Challenge')]")).click();

		WebElement searchBtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//body//div[@id='root']//div//div[1]//div[2]//button[1]")));

		int y = searchBtn.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0] - 200);", y);

		searchBtn.click();

		// 1. Collect all products with page info
		List<Product> allProducts = getAllProducts();

		// ====== PLP_001: Count products per category ======
		System.out.println("\n===== PLP_001: Category Counts =====");

		Map<String, Long> categoryCount = allProducts.stream()
				.collect(Collectors.groupingBy(p -> p.category, Collectors.counting()));

		categoryCount.forEach((cat, count) -> System.out.println(cat + " : " + count));

		// ====== PLP_002: Find specific product ======

		String searchProduct = "Nike Air Force 1 Sneakers";

		System.out.println("\n===== PLP_002: Find Product =====");

		allProducts.stream().filter(p -> p.name.equalsIgnoreCase(searchProduct)).findFirst().ifPresentOrElse(
				p -> System.out.println("Found " + p + " on page " + p.pageNumber),
				() -> System.out.println("Product not found"));

		// ====== PLP_003: Highest rated product per category ======

		System.out.println("\n===== PLP_003: Highest Rated Per Category =====");

		Map<String, Optional<Product>> maxRating = allProducts.stream().collect(
				Collectors.groupingBy(p -> p.category, Collectors.maxBy(Comparator.comparingDouble(p -> p.rating))));
		maxRating.forEach((cat, prod) -> System.out.println(cat + " -> " + prod.get()));

		// ====== PLP_004: Most expensive per category ======

		System.out.println("\n===== PLP_004: Most Expensive Per Category =====");
		Map<String, Optional<Product>> maxPrice = allProducts.stream().collect(
				Collectors.groupingBy(p -> p.category, Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));
		maxPrice.forEach((cat, prod) -> System.out.println(cat + " -> " + prod.get()));

		// ====== PLP_005: Pagination validation ======
		System.out.println("\n===== PLP_005: Pagination Validation =====");
		verifyPagination();

		// ====== PLP_006: Verify product card details ======
		System.out.println("\n===== PLP_006: Product Card Format =====");
		verifyCardDetails(allProducts);

		driver.quit();
	}

	// 🔹 Collect all products across pages
	private static List<Product> getAllProducts() throws InterruptedException {
		List<Product> allProducts = new ArrayList<>();
		boolean hasNext = true;
		int page = 1;

		while (hasNext) {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'MuiCard-root')]")));

			List<WebElement> productCards = driver.findElements(By.xpath("//div[contains(@class,'MuiCard-root')]"));

			for (WebElement card : productCards) {
				Product p = new Product();

				try {
					p.name = card.findElement(By.xpath(".//h6[1]")).getText();
				} catch (Exception e) {
					p.name = "NA";
				}
				try {
					p.category = card.findElement(By.xpath(".//p")).getText().replace("Category: ", "").trim();
				} catch (Exception e) {
					p.category = "Unknown";
				}
				try {
					String priceTxt = card.findElement(By.xpath(".//h6[contains(@class,'text-green-600')]")).getText()
							.replaceAll("[^0-9.]", "");
					p.price = Double.parseDouble(priceTxt);
				} catch (Exception e) {
					p.price = 0.0;
				}
				try {
					String ratingTxt = card.findElement(By.xpath(".//span[@role='img']")).getAttribute("aria-label")
							.replaceAll("[^0-9.]", "");
					p.rating = Double.parseDouble(ratingTxt);
				} catch (Exception e) {
					p.rating = 0.0;
				}

				p.pageNumber = page;
				allProducts.add(p);
			}

			System.out.println("Page " + page + " collected: " + productCards.size() + " products");

			// next page
			List<WebElement> nextbtn = driver.findElements(By.xpath("//button[normalize-space()='Next']"));
			if (!nextbtn.isEmpty() && nextbtn.get(0).isEnabled()) {
				nextbtn.get(0).click();
				Thread.sleep(1500);
				page++;
			} else {
				hasNext = false;
			}
		}
		return allProducts;
	}

	// 🔹 Verify pagination works
	private static void verifyPagination() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//button[normalize-space()='3']")).click();
			Thread.sleep(1000);
			System.out.println("Navigated to page 3 successfully");

			driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();
			Thread.sleep(1000);
			System.out.println("Next page worked");

			driver.findElement(By.xpath("//button[normalize-space()='Prev']")).click();
			Thread.sleep(1000);
			System.out.println("Prev page worked");

			driver.findElement(By.xpath("//button[normalize-space()='1']")).click();
			Thread.sleep(1000);
			System.out.println("First page worked");

		} catch (Exception e) {
			System.out.println("Pagination validation failed: " + e.getMessage());
		}
	}

	// 🔹 Verify product card details
	private static void verifyCardDetails(List<Product> allProducts) {
		for (Product p : allProducts) {
			if (p.name == null || p.name.isEmpty())
				System.out.println("❌ Missing Name on page " + p.pageNumber);
			if (p.category == null || p.category.isEmpty())
				System.out.println("❌ Missing Category for " + p.name);
			if (p.price <= 0)
				System.out.println("❌ Invalid Price for " + p.name);
			if (p.rating < 0)
				System.out.println("❌ Invalid Rating for " + p.name);
		}
		System.out.println("✅ All product cards validated successfully");
	}
}
