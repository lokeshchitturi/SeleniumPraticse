package learningTestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAnnotations_1 {
	@BeforeTest()
	public void beforeTest()
	{
		System.out.println("before start of any test method");
	}
	
	@AfterTest()
	public void afterTest()
	{
		System.out.println("after stop of all test method");
	}
	
	@BeforeClass
	public void beforeClass()
	{
		System.out.println("Intialize selenium driver object for class 1");
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("Prerequist ----opening browser for each test");
	}
	
	@Test
	public void test1()
	{
		System.out.println("Test Method 1");
	}
	
	@Test
	public void test2()
	{
		System.out.println("Test Method 2");
	}
	
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("close browser for each test");
	}
	
	@AfterClass
	public void afterClass()
	{
		System.out.println("Destroy selenium driver object for class 1");
	}

}
