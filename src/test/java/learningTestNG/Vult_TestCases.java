package learningTestNG;

import static utils.WebDriverUtils.waitUntilPresenceOfElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ExcelUtils;
import utils.WebDriverUtils;

public class Vult_TestCases {
	WebDriver driver; 
	
	@BeforeTest
	public void initalizeDriver()
	{
		  driver=new ChromeDriver();
		  
		  driver.get("https://connectqa.vultus.com/");

		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		  
		  WebDriverUtils.driver=driver;
		
	}
	
	
	@Test(priority=0)
	  public void login() throws InterruptedException {
		  
		
		  try {
			System.out.println(driver.getTitle());
			  driver.findElement(By.id("email")).sendKeys("r.aviconnectpersonal@gmail.com");
			  
			
			  
			  driver.findElement(By.id("password")).sendKeys("123");
			  
			  
			  driver.findElement(By.id("LoginBtn")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  //driver.quit();
		  
			  }
	
	@Test(priority=1)
	public void navigateTOUserTab() throws Exception
	{
		  try {
			waitUntilPresenceOfElement(30, By.id("modalTitle"));
			  //new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("modalTitle")));
			  
			  WebElement modal=driver.findElement(By.id("staffclose"));
			  /*waitUntilPresenceOfElement(driver, 15, By.id("lblUsernamecompany"));
			  waitUntilElementClicable(driver, 15, By.id("lblUsernamecompany"));*/
			  
			  new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("lblUsernamecompany")));
			  
			  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", modal);

			  Thread.sleep(3000);
			  
			  WebElement usersText=driver.findElement(By.xpath("//*[text()='Users']"));
			  
			  //moveToElementAndClick(driver, timesheetText);
			  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", usersText);
			  
			  Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test (priority=3,dataProvider="testdata")
	
	public void createUser(String firstName,String lastName,String assignrole,String workEmail,String personalEmail,String Address1,String city,String state,String zipcode,String date)
	{
		
		try {
			
			Thread.sleep(3000);
			WebDriverUtils.waitUntilPresenceOfElement(40, By.xpath("//*[@id='spnusercreate']/i"));
			
			List<WebElement> userList=driver.findElements(By.xpath("//a[@class='select2-search-choice-close']"));
			if(userList.size()!=0)
			{
				userList.get(0).click();
			}
			
			driver.findElement(By.xpath("//*[@id='spnusercreate']/i")).click();
			WebDriverUtils.waitUntilElementClicable(15,By.name("txtfirstname"));
			
			WebElement txt_firstname=driver.findElement(By.name("txtfirstname"));
			WebElement txt_workemail=driver.findElement(By.name("WorkEmailField"));
			WebElement txt_personalmail=driver.findElement(By.name("PersonalEmailField"));
			
			txt_workemail.sendKeys(workEmail);
			txt_personalmail.sendKeys(personalEmail);
			txt_firstname.sendKeys(firstName);
			boolean workEmailStatus=driver.findElement(By.xpath("//*[@name='WorkEmailField']//following-sibling::label")).getAttribute("style").contains("block");
			boolean personalEmailStatus=driver.findElement(By.xpath("//*[@name='PersonalEmailField']//following-sibling::label")).getAttribute("style").contains("block");
			
			if(workEmailStatus||personalEmailStatus)
			{
				//WebDriverUtils.waitUntilVisible(30, By.xpath("//a[@id='OpensitemapUsers']"));
				//driver.findElement(By.xpath("//a[@id='OpensitemapUsers']")).click();
				
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='OpensitemapUsers']")));
				
			}
			
			else
			{
				WebElement txt_lastname=driver.findElement(By.name("LNameField"));
				
				Select drp_assignrole=new Select(driver.findElement(By.id("ddlrolesassigned")));
				
				WebElement txt_adress=driver.findElement(By.name("Address1Field"));
				WebElement txt_city=driver.findElement(By.name("CityField"));
				Select drp_state=new Select(driver.findElement(By.name("ddlStatemail")));
				WebElement txt_zipcode=driver.findElement(By.name("txtmailzip"));
				WebElement txt_date=driver.findElement(By.id("txtstartdate"));
				
				txt_lastname.sendKeys(lastName);
				txt_adress.sendKeys(Address1);
				txt_city.sendKeys(city);
				drp_state.selectByVisibleText(city);
				txt_zipcode.sendKeys(zipcode);
				
				
				waitUntilPresenceOfElement(30, By.xpath("//*[@id='s2id_ddlrolesassigned']"));
				drp_assignrole.selectByVisibleText(assignrole);
				
				
				driver.findElement(By.id("userNext")).click();
				txt_date.sendKeys(date);
				driver.findElement(By.id("afinish")).click();
			}
			
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("_______________In Testase");
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(workEmail);
	}
	
	
	 @DataProvider(name="testdata")

	    public Object[][] Testdata() throws Exception{

	         Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\HOME\\Desktop\\Testdata.xlsx","Sheet1");

	         return (testObjArray);

			}


}
