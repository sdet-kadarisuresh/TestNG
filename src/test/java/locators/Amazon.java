package locators;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@type='text' and contains(@placeholder,'Search') and @name='q']")).click();
		driver.findElement(By.xpath("//input[@type='text' and contains(@placeholder,'Search') and @name='q']")).sendKeys("mobile"+Keys.ENTER);
		
		List<WebElement> parents = driver.findElements(By.xpath("//div[@class='DOjaWF gdgoEp'][.//div[@class='col col-7-12']]"));
		for(WebElement parent : parents) {
		    List<WebElement> children = parent.findElements(By.xpath(".//div[@class='col col-7-12']"));
		    for(WebElement child : children) {
		        System.out.println(child.getText());
		        
		      
				
		        
		    }
		}
		
		/*
		String[] Mobiledetails = Mobile_details.split("\n");

		System.out.println(Arrays.toString(Mobiledetails)); // Output: [a, b, c]
		String name=Mobiledetails[0];

		String[] original_name=name.split("\\(");
		String mobile_name=original_name[0].trim();
		
		
		System.out.println("Mobile Name is ->" +mobile_name);
		
		String ram=Mobiledetails[2];
		System.out.println(ram);
		String[] name_ram= ram.split("R");
		String total_ram=name_ram[0].trim();
		
		String[] rom=name_ram[1].split("\\|");
		String[] rom1=rom[1].split("R");
		String total_rom =rom1[0].trim();
		
		
		System.out.println("total ram "+total_ram);
		System.out.println("total ram "+total_rom);

		
		
		String dis=Mobiledetails[3];
		String[] dis1=dis.split("\\(");
		String[] dis2=dis1[1].split("\\)");
		String Display=dis2[0].trim();
		
		System.out.println("Display "+ Display);
		
		String plx=Mobiledetails[4];
		String[] plx1=plx.split("\\|");
		String Front_cam=plx1[0].trim();
		
		String[] backcam=plx1[1].split("F");
		String Back_cam=backcam[0].trim();


		System.out.println("Front_cam "+Front_cam);
		System.out.println("Back_cam "+Back_cam);
		

		String bt=Mobiledetails[5];
		String[] bt1=bt.split("B");
		String Battery=bt1[0].trim();
		System.out.println("Battery "+Battery);
		

		String process=Mobiledetails[6];
		String[] process1=process.split("M");
		String Processor=process1[0].trim();
		System.out.println("Processor " +Processor);
		
		
		String year=Mobiledetails[7];
		String[] year1=year.split("Y");
		String Warranty=year1[0].trim();
		System.out.println("Warranty" + Warranty);
		

		
		
		
		WebElement price=driver.findElement(By.xpath("//div[@class='Nx9bqj _4b5DiR']"));
		//System.out.println(price.getText());
		
		String priced=price.getText();
		String Total_price = priced.replace("₹", "");
		System.out.println("--------");
		System.out.println(Total_price);
		
		*/
		

		
	}

}
