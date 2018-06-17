package pageObjects;

import org.testng.Assert;

import utils.WebDriverUtils;

public class ManagerHomePage_PO extends WebDriverUtils{

	private static String userIdDisplay_loc="xpath__//*[contains(text(),'Manger Id')]";
	
	public static void LoginValidation(String expectedUsername)
	{
		try {
			String[] actuatext=getWebElement(userIdDisplay_loc).getText().split(" ");
			if(actuatext[actuatext.length-1].equals(expectedUsername))
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
