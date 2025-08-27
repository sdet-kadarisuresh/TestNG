package FlipkartSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("./reports/FlipkartAutomationReport.html");
            spark.config().setDocumentTitle("Flipkart Automation Report");
            spark.config().setReportName("E-Commerce Test Suite");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
