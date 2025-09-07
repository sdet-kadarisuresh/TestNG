package locators;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartMobileExtractorExcel {

    public static void main(String[] args) throws InterruptedException, IOException {
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

        Thread.sleep(5000); // Wait for page to load

        // Parent divs containing mobiles
        List<WebElement> parents = driver.findElements(By.xpath("//div[@class='DOjaWF gdgoEp'][.//div[@class='col col-7-12']]"));

        // Excel setup
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Flipkart Mobiles");
        Row header = sheet.createRow(0);
        String[] columns = {"Mobile Name", "RAM", "ROM", "Display", "Rear Camera", "Front Camera", "Battery", "Processor", "Warranty", "Price"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
        }

        int rowNum = 1; // Excel row counter

        for (WebElement parent : parents) {
            List<WebElement> children = parent.findElements(By.xpath(".//div[@class='col col-7-12']"));

            for (WebElement child : children) {
                String text = child.getText();
                String[] lines = text.split("\n");

                String name="NA", ram="NA", rom="NA", display="NA", rearCam="NA", frontCam="NA", battery="NA", processor="NA", warranty="NA", price="NA";

                if(lines.length>0) name = lines[0].split("\\(")[0].trim();
                if(lines.length>1) {
                    String ramRom = lines[1];
                    String[] rr = ramRom.split("\\|");
                    ram = rr.length>0 ? rr[0].trim() : "NA";
                    rom = rr.length>1 ? rr[1].trim() : "NA";
                }
                if(lines.length>2) display = lines[2].trim();
                if(lines.length>3) {
                    String cams = lines[3];
                    String[] c = cams.split("\\|");
                    rearCam = c.length>0 ? c[0].trim() : "NA";
                    frontCam = c.length>1 ? c[1].trim() : "NA";
                }
                if(lines.length>4) battery = lines[4].trim();
                if(lines.length>5) processor = lines[5].trim();
                if(lines.length>6) warranty = lines[6].trim();

                // Price extraction (dynamic)
                try {
                    WebElement priceElement = child.findElement(By.xpath(".//following-sibling::div[@class='Nx9bqj _4b5DiR']"));
                    price = priceElement.getText().replace("₹","").replace(",","").trim();
                } catch(Exception e) {
                    try {
                        WebElement priceElement = child.findElement(By.xpath(".//ancestor::div[@class='DOjaWF gdgoEp']//div[@class='Nx9bqj _4b5DiR']"));
                        price = priceElement.getText().replace("₹","").replace(",","").trim();
                    } catch(Exception ex) {
                        price = "NA";
                    }
                }

                // Print on console
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

                // Write to Excel
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(name);
                row.createCell(1).setCellValue(ram);
                row.createCell(2).setCellValue(rom);
                row.createCell(3).setCellValue(display);
                row.createCell(4).setCellValue(rearCam);
                row.createCell(5).setCellValue(frontCam);
                row.createCell(6).setCellValue(battery);
                row.createCell(7).setCellValue(processor);
                row.createCell(8).setCellValue(warranty);
                row.createCell(9).setCellValue(price);
            }
        }

        // Auto-size columns
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to file
        FileOutputStream fileOut = new FileOutputStream("FlipkartMobiles.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

        driver.quit();
        System.out.println("Data extraction completed. Excel file created successfully.");
    }
}
