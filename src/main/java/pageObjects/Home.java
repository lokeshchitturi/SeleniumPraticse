package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WebDriverUtils;

public class Home extends WebDriverUtils{

	private static String dialog_next1="xpath__//*[text()='next']";
	private static String dialog_next2="xpath__//*[text()='next']";
	private static String dialog_done="xpath__//*[text()='done']";
	private static String login_icon="xpath__//*[@id='loginButton']/div/i";
	
	
	public static void handle_welcomePopUp() throws Exception
	{
		getWebElement(dialog_next1).click();
		getWebElement(dialog_next2).click();
		getWebElement(dialog_done).click();
		
	}
	
	
	public static void loginIntoApplication() throws Exception
	{
		getWebElement(login_icon).click();
		
	}
	
	
	
	
	
	
	
}
