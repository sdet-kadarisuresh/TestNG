package locators;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		Actions Action = new Actions(driver);
	
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement mobile=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Electronics']")));
	mobile.click();
	
	WebElement mobile1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Gaming']")));
	
	Action.moveToElement(mobile1).perform();
	
	WebElement mobile2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Games']")));
	Action.moveToElement(mobile2).perform();
	mobile2.click();
	driver.findElement(By.xpath("//a[text()='Black Myth Wukong']")).click();
	
	String parent=driver.getWindowHandle();

	
	
	Set<String> All=driver.getWindowHandles();
	
	for(String win:All) {
		
		if(!win.equals(parent))
		{
		driver.switchTo().window(win);
		
		}
		
	}
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add to cart')]"))).click();

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Place Order')]"))).click();
	driver.close();
	driver.switchTo().window(parent);
	//driver.findElement(By.xpath(parent));
	driver.findElement(By.xpath("//div[contains(text(),'Price -- Low to High')]")).click();
		

		
	}
}