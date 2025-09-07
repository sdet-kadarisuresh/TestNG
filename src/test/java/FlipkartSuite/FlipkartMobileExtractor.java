package FlipkartSuite;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartMobileExtractor {

    public static void main(String[] args) throws InterruptedException {
        // Launch Chrome
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");

        // Close login popup if exists
        try {
            driver.findElement(By.xpath("//button[contains(text(),'✕')]")).click();
        } catch(Exception e) {}

        // Search for mobiles
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='text' and contains(@placeholder,'Search') and @name='q']"));
        searchBox.click();
        searchBox.sendKeys("mobile" + Keys.ENTER);

        Thread.sleep(5000); // wait for results to load

        // Get all mobile cards
        List<WebElement> mobiles = driver.findElements(By.xpath("//div[@class='DOjaWF gdgoEp']"));

        for (WebElement mobile : mobiles) {
            String name="NA", ram="NA", rom="NA", display="NA", rearCam="NA", frontCam="NA";
            String battery="NA", processor="NA", warranty="NA", price="NA";

            // Mobile Name
            try { name = mobile.findElement(By.xpath(".//div[contains(@class,'_4rR01T')]")).getText(); } catch(Exception e) {}

            // Specs (RAM, ROM, Display, Cameras, Battery, Processor, Warranty)
            try { 
                List<WebElement> specs = mobile.findElements(By.xpath(".//ul[@class='_1xgFaf']/li"));
                for(WebElement spec : specs) {
                    String s = spec.getText();
                    String lower = s.toLowerCase();
                    if(lower.contains("ram")) ram = s;
                    else if(lower.contains("rom")) rom = s;
                    else if(lower.contains("display")) display = s;
                    else if(lower.contains("rear")) rearCam = s;
                    else if(lower.contains("front")) frontCam = s;
                    else if(lower.contains("mAh")) battery = s;
                    else if(lower.contains("processor")) processor = s;
                    else if(lower.contains("warranty")) warranty = s;
                }
            } catch(Exception e) {}

            // Price
            try { price = mobile.findElement(By.xpath(".//div[contains(@class,'_30jeq3')]")).getText(); } catch(Exception e) {}

            // Print all details
            System.out.println("----------------------------------------------------");
            System.out.println("Mobile Name: " + name);
            System.out.println("RAM: " + ram);
            System.out.println("ROM: " + rom);
            System.out.println("Display: " + display);
            System.out.println("Rear Camera: " + rearCam);
            System.out.println("Front Camera: " + frontCam);
            System.out.println("Battery: " + battery);
            System.out.println("Processor: " + processor);
            System.out.println("Warranty: " + warranty);
            System.out.println("Price: " + price);
        }

        driver.quit();
    }
}
