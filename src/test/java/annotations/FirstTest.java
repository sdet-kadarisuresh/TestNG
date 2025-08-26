package annotations;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {
	
	@Test
	public void testHello()
	{
		System.out.println("hello test");
		
		
	}
	
	@Test
	public void testOne() { System.out.println("Test 1"); }

	@Test
	public void testTwo() { System.out.println("Test 2"); }

	@Test
	public void testThree() { System.out.println("Test 3"); }
	
	
	
	
}
