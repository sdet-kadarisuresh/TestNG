package cnarios;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Slider {

	
	public static void main(String[] args) {
		WebDriver driver;
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.cnarios.com/challenges/product-filtering");
        setSlider(driver,50,200);
        
	}
	public static void setSlider(WebDriver driver, int minValue, int maxValue) {
	    // locate left and right thumbs
	    WebElement leftThumb = driver.findElement(By.xpath("//input[@type='range' and @data-index='0']"));
	    WebElement rightThumb = driver.findElement(By.xpath("//input[@type='range' and @data-index='1']"));

	    Actions actions = new Actions(driver);

	    // Move left thumb (min value)
	    actions.clickAndHold(leftThumb).moveByOffset(50, 0).release().perform();  // adjust 50px as needed

	    // Move right thumb (max value)
	    actions.clickAndHold(rightThumb).moveByOffset(-100, 0).release().perform(); // move left by 100px
	}

}
