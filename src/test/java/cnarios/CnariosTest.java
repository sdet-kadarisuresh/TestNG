package cnarios;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CnariosTest {
	
	
	public static void main(String[] args) {
		
		WebDriver driver=new ChromeDriver();

		
		driver.get("https://www.cnarios.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//a[@href='/explore']")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'Take Challenge')]")).click();
		
		
		//clicking role bases task
		driver.findElement(By.xpath("//div[@class='grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6']/div[3]//button[1]")).click();
		/*
		// tc01 login without user name and password show validation msg
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		WebElement s=driver.findElement(By.xpath("//div[contains(text(),'Both fields are required.')]"));	
		String txt=s.getText();
		Assert.assertEquals(txt, "Both fields are required.", "pass"); */
		
		//tc2 invalid user and password validate msg
		
	/*	driver.findElement(By.xpath("//label[contains(text(),'Username')]/following-sibling::div//input")).sendKeys("hellouser");
		driver.findElement(By.xpath("//label[contains(.,'Password')]/following-sibling::div//input")).sendKeys("1234ed44");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		WebElement s=driver.findElement(By.xpath("(//div[@class='MuiAlert-icon css-131is6g'])[2]/following-sibling::div"));	
		System.out.println(s.getText());
		Assert.assertEquals(s.getText(), "Invalid username or password.","pass"); */
		
		// tc3 
		/* Test Steps & Details
Description:
Login with valid User credentials and verify User dashboard

Steps to Execute:
Navigate to login page
Enter username 'user' and password 'user123'
Click on login button
Verify welcome message for 'user'
Verify User dashboard content is shown
tc4
Description:
Login with valid Admin credentials and verify Admin dashboard

Steps to Execute:
Navigate to login page
Enter username 'admin' and password 'admin123'
Click on login button
Verify welcome message for 'admin'
Verify Admin dashboard content is shown

tc5
Test Steps & Details
Description:
Logout from User/Admin dashboard and verify return to login form

Steps to Execute:
Login with valid credentials
Click on logout button
Verify login form is displayed
Verify fields are reset to empty
*/
		
		//tc3
/*		
		driver.findElement(By.xpath("//label[contains(text(),'Username')]/following-sibling::div//input")).sendKeys("user");
		driver.findElement(By.xpath("//label[contains(.,'Password')]/following-sibling::div//input")).sendKeys("user123");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		
		WebElement usermsg=driver.findElement(By.xpath("//h5[@class='MuiTypography-root MuiTypography-h5 text-center font-bold css-jcahak']"));
		
		
		Assert.assertEquals(usermsg.getText(),"Welcome, user 👋","pass");
		
WebElement userdahsboard=driver.findElement(By.xpath("//div[contains(@role,'alert')]/following-sibling::div"));
System.out.println(userdahsboard.getText());

*/
		/*tc4
		
		driver.findElement(By.xpath("//label[contains(text(),'Username')]/following-sibling::div//input")).sendKeys("admin");
		driver.findElement(By.xpath("//label[contains(.,'Password')]/following-sibling::div//input")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		
		WebElement usermsg=driver.findElement(By.xpath("//h5[@class='MuiTypography-root MuiTypography-h5 text-center font-bold css-jcahak']"));
		String ss=usermsg.getText().concat("admin");
		//String user="admin";
		Assert.assertTrue(ss.toLowerCase().contains("admin"), "User is  admin!");

		
	//	Assert.assertEquals("admin",ss,"pass");
		
WebElement userdahsboard=driver.findElement(By.xpath("//div[contains(@role,'alert')]/following-sibling::div"));
System.out.println(userdahsboard.getText());
		
		*/
		
		
		driver.findElement(By.xpath("//label[contains(text(),'Username')]/following-sibling::div//input")).sendKeys("admin");
		driver.findElement(By.xpath("//label[contains(.,'Password')]/following-sibling::div//input")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		
		driver.findElement(By.xpath("//button[contains(.,'Logout')]")).click();
		
		
		WebElement logintext=driver.findElement(By.xpath("//h5[contains(.,'Login')]"));
		
		String login=logintext.getText();
		
		
		Assert.assertTrue(login.toLowerCase().contains("login"), "pass");
		
		
		
		
		driver.findElement(By.xpath("//label[contains(text(),'Username')]/following-sibling::div//input")).clear();
		driver.findElement(By.xpath("//label[contains(.,'Password')]/following-sibling::div//input")).clear();
		
		
		/*
		 * List<WebElement> all= driver.findElements(By.
		 * xpath("//div[@class='grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6']")
		 * ); for(WebElement e:all) { System.out.println(e.getText());
		 * System.out.println(e.getDomProperty("class")); }
		 */
		
	}

}
