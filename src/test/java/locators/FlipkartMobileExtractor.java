package locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartMobileExtractor {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();

        // Close login popup
        try {
            driver.findElement(By.xpath("//button[contains(text(),'✕')]")).click();
        } catch (Exception e) {}

        // Search for mobiles
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='text' and contains(@placeholder,'Search') and @name='q']"));
        searchBox.sendKeys("mobile");
        searchBox.sendKeys(Keys.ENTER);

        Thread.sleep(4000); // Wait for page to load results

        // Parent divs containing mobiles
        List<WebElement> parents = driver.findElements(By.xpath("//div[@class='DOjaWF gdgoEp'][.//div[@class='col col-7-12']]"));

        for (WebElement parent : parents) {
            List<WebElement> children = parent.findElements(By.xpath(".//div[@class='col col-7-12']"));

            for (WebElement child : children) {
                String text = child.getText();
                String[] lines = text.split("\n");

                String name="NA", ram="NA", rom="NA", display="NA", rearCam="NA", frontCam="NA", battery="NA", processor="NA", warranty="NA", price="NA";

                if (lines.length > 0) name = lines[0].split("\\(")[0].trim();

                // Iterate dynamically over remaining lines
                for (int i = 1; i < lines.length; i++) {
                    String line = lines[i].toLowerCase();

                    if (line.contains("ram") && line.contains("rom")) {
                        String[] rr = lines[i].split("\\|");
                        ram = rr.length > 0 ? rr[0].trim() : "NA";
                        rom = rr.length > 1 ? rr[1].trim() : "NA";
                    } else if (line.contains("display")) {
                        display = lines[i].trim();
                    } else if (line.contains("rear") || line.contains("camera")) {
                        String[] cams = lines[i].split("\\|");
                        rearCam = cams.length > 0 ? cams[0].trim() : "NA";
                        frontCam = cams.length > 1 ? cams[1].trim() : "NA";
                    } else if (line.contains("mAh")) {
                        battery = lines[i].trim();
                    } else if (line.contains("processor")) {
                        processor = lines[i].trim();
                    } else if (line.contains("warranty")) {
                        warranty = lines[i].trim();
                    }
                }

                // Flexible XPath for price
                try {
                    WebElement priceElement = child.findElement(By.xpath(".//following-sibling::div[contains(@class,'_30jeq3')]"));
                    price = priceElement.getText().replace("₹","").trim();
                } catch (Exception e) {
                    price = "NA";
                }

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
        }

        driver.quit();
    }
}
