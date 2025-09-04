package ToolsQA;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Home {
	WebDriver driver;
	
	
	
	@Test(priority = 1)
	public void open() {
		driver=new ChromeDriver();
		driver.get("https://www.toolsqa.com/");
		
	}
	@Test(priority = 2)
	public void StateTesting() {
		driver.findElement(By.cssSelector(".navbar__tutorial-menu--menu-bars")).click();
		
		
		WebElement backend=	driver.findElement(By.xpath("//span[normalize-space()='Back-End Testing Automation']"));
		
		
		Actions a=new Actions(driver);
		 a.moveToElement(backend).click().perform();	
		 
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		// WebElement backend_java=driver.findElement(By.xpath("//a[@title='Selenium in Java']"));

		 WebElement Bavk_java=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='active']//a[@title='TestProject'][normalize-space()='TestProject']")));
		
		a.moveToElement(Bavk_java).click().perform();
		
			}
	@Test(priority = 3)
	public void Author() {
		 WebElement Author=	driver.findElement(By.cssSelector(".name.text-tools-qa-black"));
			
		 System.out.println(Author);

	}

}
