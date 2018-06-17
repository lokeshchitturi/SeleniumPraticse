package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import utils.WebDriverUtils;

public class Login_PO extends WebDriverUtils{
	
	
	private static String username_loc="name__uid";
	private static String password_loc="name__password";
	private static String loginBtn_loc="name__btnLogin";
	private static String logout_loc="linkText__Log out";
	public static ManagerHomePage_PO bankLogin(String username,String password) throws Exception
	{
		try {
			getWebElement(username_loc).sendKeys(username);
			getWebElement(password_loc).sendKeys(password);
			getWebElement(loginBtn_loc).click();
			return new ManagerHomePage_PO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public static void bankLogout() throws Exception
	{
		try {
			
			WebElement logout_ele=getWebElement(logout_loc);
			WebDriverUtils.scrollIntoElementView(logout_ele);
			logout_ele.click();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	
	public static void handleLogoutAlert() throws Exception
	{
		try {
			Alert alert=driver.switchTo().alert();
			
			if(alert.getText().contains("Logged Out"))
			{
				alert.dismiss();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	
	
	

}
