package cnarios;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pagination2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.cnarios.com/");
		driver.manage().window().maximize();

		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("//a[@href='/explore']")).click();

		driver.findElement(By.xpath("//button[contains(text(),'Take Challenge')]")).click();

		WebElement searchBtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//body//div[@id='root']//div//div[1]//div[2]//button[1]")));

		int y = searchBtn.getLocation().getY();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0] - 200);", y);

		searchBtn.click();

		List<Map<String, String>> allProducts = new ArrayList<>();
		Map<String, Integer> categoryCount = new HashMap<>();

		boolean hasNextPage = true;

		while (hasNextPage) {		
	

				 List<WebElement> productCards = driver.findElements(By.xpath("//div[contains(@class,'MuiCard-root')]"));

		            for (WebElement card : productCards) {

		                String name = card.findElement(By.xpath(".//h6[1]")).getText();
		                String category = card.findElement(By.xpath(".//p")).getText().replace("Category: ", "").trim();
		                String price = card.findElement(By.xpath(".//h6[contains(@class,'text-green-600')]")).getText();
		                String rating = card.findElement(By.xpath(".//span[@role='img']")).getAttribute("aria-label");

		               System.out.println(name);
		               System.out.println(category);

		               System.out.println(price);

		               System.out.println(rating);

			}

			List<WebElement> nextbtn = driver.findElements(By.xpath("//button[normalize-space()='Next']"));

			if (nextbtn.size() >= 0 && nextbtn.get(0).isEnabled()) {
				nextbtn.get(0).click();
			} else {
				hasNextPage = false;
			}
		}
	}
}
