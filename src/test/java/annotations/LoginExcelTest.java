package annotations;

import org.testng.Assert;
import org.testng.annotations.*;

public class LoginExcelTest {

	 @DataProvider(name = "loginData")
	    public Object[][] getData() {
	        String path = "src/test/resources/LoginData.xlsx";
	        return ExcelUtils.getExcelData(path, "Sheet1");
	    }

	    @Test(dataProvider = "loginData")
	    public void loginTest(String username, String password, String role) {
	        System.out.println("Username: " + username + ", Password: " + password + ", Role: " + role);

	        // Simulate login success/failure
	        if(username.equals("invalid")) {
	            System.out.println("Login failed for: " + username);
	            Assert.fail("Invalid login");
	        } else {
	            System.out.println("Login successful for: " + username);
	            Assert.assertTrue(true);
	        }
	    }
	}