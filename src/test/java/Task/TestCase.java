package Task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;
		driver=new ChromeDriver();
		
		driver.get("https://tools.grihaindia.org/Project/Create");
		
		driver.findElement(By.xpath("//input[@name='PDProjectName']")).sendKeys("abc");
		
		driver.findElement(By.id("showregister")).click();

	}

}
