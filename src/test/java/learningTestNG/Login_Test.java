package learningTestNG;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.Login_PO;
import pageObjects.ManagerHomePage_PO;
import utils.WebDriverUtils;

public class Login_Test {
	
	
	@BeforeTest
	
	public void beforeTest()
	{
		//System.setProperty("webdriver.gecko.driver", "E:\\EclipsePraticse\\SeleniumPraticse\\geckodriver.exe");
		driver=new ChromeDriver();
		driver.get("http://www.demo.guru99.com/V4/");
		System.out.println("Browser started");
		WebDriverUtils.driver=driver;
	}

	WebDriver driver;
	@Test
	@Parameters({"username","password"})
	public void login(String username,String password) throws Exception
	{

		Login_PO.bankLogin(username, password);
		ManagerHomePage_PO.LoginValidation(username);
		System.out.println("Login completed");
		Login_PO.bankLogout();
		Login_PO.handleLogoutAlert();
	}
	
	
	
	
}
