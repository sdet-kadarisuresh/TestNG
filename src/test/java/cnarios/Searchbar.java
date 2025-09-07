package cnarios;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Searchbar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
WebDriver driver=new ChromeDriver();

		
		driver.get("https://www.cnarios.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//a[@href='/explore']")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Take Challenge')]")).click();
		
		driver.findElement(By.xpath("//div[7]//div[2]//button[1]")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys("React Testing");
		
		WebElement btn=driver.findElement(By.xpath("//button[contains(.,'Search')]"));
		
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//WebElement btn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Search')]")));
	
		int y = btn.getLocation().getY();

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0] - 200);", y);

		
		//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollViewInto;", btn);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
		btn.click();
		
		
		
		List<WebElement> urls=driver.findElements(By.cssSelector(".space-y-4 a"));
		System.out.println("total links are"+ urls.size());
		
		for(WebElement urlLink:urls) {
			System.out.println(urlLink.getText());
		System.out.println(urlLink.getAttribute("href"));
		


	}

}
}
