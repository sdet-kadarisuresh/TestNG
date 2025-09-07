package cnarios;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Searchbar {

	public static void main(String[] args){
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.cnarios.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//a[@href='/explore']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Take Challenge')]")).click();
		driver.findElement(By.xpath("//div[7]//div[2]//button[1]")).click();

		/*
		 * tc 1 Enter a valid query and confirm that results display with title, URL,
		 * and snippet.
		 * 
		 * Steps to Execute: Locate the search input and type 'React Testing' Click the
		 * search button Verify that at least 3 results appear Validate each result
		 * includes a clickable title, URL, and snippet
		 * driver.findElement(By.xpath("//input[@placeholder='Search...']")).
		 * sendKeys("React Testing");
		 * 
		 * WebElement
		 * btn=driver.findElement(By.xpath("//button[contains(.,'Search')]"));
		 * 
		 * //WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		 * //WebElement btn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//button[contains(.,'Search')]")));
		 * 
		 * int y = btn.getLocation().getY();
		 * 
		 * ((JavascriptExecutor)
		 * driver).executeScript("window.scrollTo(0, arguments[0] - 200);", y); //
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollViewInto;",
		 * btn); //((JavascriptExecutor)
		 * driver).executeScript("arguments[0].scrollIntoView(true);", btn);
		 * 
		 * //((JavascriptExecutor)
		 * driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",
		 * btn); btn.click();
		 * 
		 * List<WebElement> urls=driver.findElements(By.cssSelector(".space-y-4 a"));
		 * System.out.println("total links are"+ urls.size());
		 * 
		 * for(WebElement urlLink:urls) { System.out.println(urlLink.getText());
		 * System.out.println(urlLink.getAttribute("href"));
		 * 
		 * }
		 */

		// tc2
		
		/*Ensure the search input is empty
		Click the search button
		Verify results section remains empty
		
		driver.findElement(By.xpath("//input[@placeholder='Search...']")).sendKeys("");
		WebElement btn = driver.findElement(By.xpath("//button[contains(.,'Search')]"));
		int y = btn.getLocation().getY();
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, arguments[0] - 200);", y);
		btn.click();
		
		*/

		//tc3
		WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Search...']"));
        searchInput.sendKeys("Flights to London");

        WebElement searchBtn = driver.findElement(By.xpath("//button[contains(.,'Search')]"));        
        int y = searchBtn.getLocation().getY();
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, arguments[0] - 200);", y);
        searchBtn.click();

        
        searchInput.sendKeys(Keys.CONTROL + "a"); // Select all text
        searchInput.sendKeys(Keys.DELETE);         
        
        
        searchInput.sendKeys("Hotels in Paris");
        searchBtn.click();
        
        List<WebElement> results = driver.findElements(By.cssSelector(".space-y-4 a"));
        System.out.println("Total links after recovery: " + results.size());
        for (WebElement url : results) {
            System.out.println(url.getText() + " -> " + url.getAttribute("href"));
        }

        searchInput.sendKeys(Keys.CONTROL + "a"); // Select all text
        searchInput.sendKeys(Keys.DELETE);         
                searchInput.sendKeys("Node.js tutorials");
        searchBtn.click();

        results = driver.findElements(By.cssSelector(".space-y-4 a"));
        System.out.println("Total links for second query: " + results.size());
        for (WebElement url : results) {
            System.out.println(url.getText() + " -> " + url.getAttribute("href"));
        }
        
        /*Try typing into the old input reference after results have been rendered.

Steps to Execute:
Locate the search input and enter 'Flights to London'
Click the search button
Wait for search results to load (input re-renders)
Attempt to type into the old input handle
Observe stale element exception
Recover by re-locating the search input and enter 'Hotels in Paris'

tc4After recovering from a stale element, confirm automation still works for a new query.

Steps to Execute:
Re-locate the search input
Enter 'Node.js tutorials'
Click the search button
Verify that new results render correctly */
	}
	

}
