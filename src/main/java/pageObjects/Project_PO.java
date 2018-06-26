package pageObjects;

import static utils.WebDriverUtils.actionSendkeys;
import static utils.WebDriverUtils.clickELementJS;
import static utils.WebDriverUtils.getWebElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.WebDriverUtils;

public class Project_PO extends WebDriverUtils{
	
	public static void fillProjectMandatoryFields(String projectname,String projectstartdate,String projectenddate,String addressNames) throws Exception
	{
		Thread.sleep(2000);
		getWebElement("name__txtprojectname").sendKeys(projectname);
		getWebElement("name__txtprojectname").sendKeys(Keys.TAB);
		Thread.sleep(4000);
		
		actionSendkeys("name__txtProjStartDate", projectstartdate);
		actionSendkeys("name__txtProjEnddate", projectenddate);
		
		
		WebDriverUtils.javaScriptClickElement(getWebElement("xpath__//span[text()='Please Select']//parent::div"));
		//getWebElement("xpath__//span[text()='Please Select']//parent::div").click();
		
		//span[text()='Please Select']//following-sibling::ul//li/span//preceding-sibling::input
		
		List<WebElement> checkboxList=WebDriverUtils.getWebElements("xpath__//span[text()='Please Select']//following-sibling::ul//li/span");
		
		for (WebElement webElement : checkboxList) {
			if(webElement.getText().equals(addressNames))
			{
				webElement.findElement(By.xpath("preceding-sibling::input")).click();
				break;
			}
		}
		getWebElement("id__txtprojectdescription").click();
	}
	
	public static void searchProject(String projectName) throws Exception
	{
		getWebElement("name__txtprojlistclient").sendKeys(projectName.substring(0, 3));
		Thread.sleep(3000);
		
		WebDriverUtils.javaScriptClickElement(getWebElement("id__btnprojectlistclient"));
		
		
		List<WebElement> list=getWebElements("xpath__//li[@class='ui-menu-item']/a");
		
		Thread.sleep(3000);
		
		for (WebElement webElement : list) {
			if(webElement.getText().equalsIgnoreCase(projectName))
			{
				webElement.click();
				break;
			}
		}
		
	}
	
	
	
	public static void fillClientMandatoryFields(String clientName,String AddressName,String Address,String state,String city) throws Exception
	{

		
		WebDriverUtils.waitUntilElementClicable(30,By.name("txtClientname"));
		
		getWebElement("name__txtClientname").sendKeys(clientName);
		getWebElement("id__txtfriendlyaddressname").sendKeys(AddressName);
		
		getWebElement("id__txtprojClientStreet").sendKeys(Address);
		
		Thread.sleep(3000);
		
		WebDriverUtils.waitUntilElementClicable(30,By.xpath("//div[@id='s2id_ddlState']//a"));
		
		Actions ation=new Actions(driver);
		
		WebElement stateDrpDwn=getWebElement("xpath__//div[@id='s2id_ddlState']//a");
		ation.moveToElement(stateDrpDwn).doubleClick(stateDrpDwn);
	
		ation.build().perform();
		//clickELementJS(driver.findElement(By.xpath("//div[@id='s2id_ddlState']//span[text()='--Select--']")));
		driver.findElement(By.xpath("//div[@id='select2-drop']//input")).clear();
		driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys(state);
		driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys(Keys.ENTER);
		
		
		getWebElement("id__txtprojClientCity").sendKeys(city);
		
	}
	
	public static boolean clientExitsValidation()
	{
		try {
			String s=getWebElement("id__lblclientnamealreadyexist").getAttribute("style");
			boolean flag=false;
			if(s.contains("block"))
			{
				System.out.println("client already exist");
				flag=true;
			}
			return flag;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
	public static boolean projectExitsValidation()
	{
		try {
			String s=getWebElement("id__lblprojectexists").getAttribute("style");
			boolean flag=false;
			if(s.contains("block"))
			{
				System.out.println("project already exist");
				flag=true;
			}
			return flag;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public static void reserProjectMandatoryFields() throws Exception
	{
		Thread.sleep(2000);
		getWebElement("name__txtprojlistclient").clear();
		getWebElement("name__txtprojectname").clear();
		getWebElement("name__txtprojectname").sendKeys(Keys.TAB);
		
		getWebElement("name__txtProjStartDate").clear();
		getWebElement("name__txtProjEnddate").clear();

		
	}
	
	public static void resetClientMandatoryFields() throws Exception
	{
		Thread.sleep(3000);
		getWebElement("name__txtClientname").clear();
		getWebElement("id__txtfriendlyaddressname").clear();
		
		getWebElement("id__txtprojClientStreet").clear();
		
		Actions ation=new Actions(driver);
		
		WebElement stateDrpDwn=getWebElement("xpath__//div[@id='s2id_ddlState']//a");
		ation.moveToElement(stateDrpDwn).doubleClick(stateDrpDwn);
	
		ation.build().perform();
		
		driver.findElement(By.xpath("//div[@id='select2-drop']//input")).clear();
		driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("select");
		driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys(Keys.ENTER);
		
		getWebElement("id__txtprojClientCity").clear();
	}

	public static void main(String[] args) {
			
	}
}
