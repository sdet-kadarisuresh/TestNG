package opensource;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Suite Finished: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        BaseTest base = (BaseTest) result.getInstance();
        base.test = BaseTest.extent.createTest(result.getName() + " [" + base.browser + "]");
        base.test.log(Status.INFO, "Test Started: " + result.getName() + " on " + base.browser);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest base = (BaseTest) result.getInstance();
        base.test.log(Status.PASS, "Test Passed: " + result.getName() + " on " + base.browser);
        System.out.println("Test Passed-->: " + result.getName() + " [" + base.browser + "]");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest base = (BaseTest) result.getInstance();
        WebDriver driver = base.driver;

        base.test.log(Status.FAIL, "Test Failed: " + result.getName() + " on " + base.browser);
        System.out.println("Test Failed-->: " + result.getName() + " [" + base.browser + "]");

        // Capture Screenshot
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "./screenshots/" + result.getName() + "_" + base.browser + "_" + timestamp + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(path));
            base.test.addScreenCaptureFromPath(path, "Screenshot for failed test");
            System.out.println("Screenshot captured: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        BaseTest base = (BaseTest) result.getInstance();
        base.test.log(Status.SKIP, "Test Skipped: " + result.getName() + " on " + base.browser);
        System.out.println("Test Skipped-->: " + result.getName() + " [" + base.browser + "]");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }
}
