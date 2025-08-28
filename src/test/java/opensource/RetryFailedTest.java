package opensource;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer {

    private int retryCount = 0;
    private final int maxRetry = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetry) {
            retryCount++;
            System.out.println("Retrying test: " + result.getName() + " (Attempt: " + retryCount + ")");
            return true;
        }
        return false;
    }
}
