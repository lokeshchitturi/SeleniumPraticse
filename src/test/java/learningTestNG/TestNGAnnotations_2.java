package learningTestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAnnotations_2 {

	@BeforeTest
	public void beforeTest()
	{
		System.out.println("Start Test Execution in class 2");
	}
	
	@BeforeClass
	public void beforeClass()
	{
		System.out.println("Initialize selenium object for class 2");
	}
	
	@AfterClass
	public void afterClass()
	{
		System.out.println("destroy selenium object for class 2");
	}
	
	@Test
	public void test3()
	{
		System.out.println("Test Method 3");
	}
}
