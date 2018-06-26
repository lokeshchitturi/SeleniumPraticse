package learningTestNG;

import static utils.WebDriverUtils.getWebElement;
import static utils.WebDriverUtils.waitUntilPresenceOfElement;
import static utils.WebDriverUtils.actionSendkeys;
import static utils.WebDriverUtils.jsSendKeys;
import static utils.WebDriverUtils.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.Project_PO;
import utils.ExcelUtils;
import utils.WebDriverUtils;

public class Project_Vultus {

	WebDriver driver;

	@BeforeTest
	public void initalizeDriver() {
		driver = new ChromeDriver();

		driver.get("https://connectqa.vultus.com/");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebDriverUtils.driver = driver;

	}

	@Test(priority = 0)
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
		// driver.quit();

	}

	@Parameters({"tabName"})
	@Test(priority = 1)
	public void navigateTOProjectTab(String tabName) throws Exception {
		try {
			waitUntilPresenceOfElement(30, By.id("modalTitle"));

			WebElement modal = driver.findElement(By.id("staffclose"));
			
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("lblUsernamecompany")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", modal);

			Thread.sleep(3000);

			WebElement projectText = driver.findElement(By.xpath("//*[text()='"+tabName+"']"));

			// moveToElementAndClick(driver, timesheetText);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", projectText);

			Thread.sleep(4000);
			WebDriverUtils.waitUntilPresenceOfElement(40, By.xpath("//*[@id='btnProject']"));

			driver.findElement(By.xpath("//*[@id='btnProject']")).click();

			WebDriverUtils.waitUntilElementClicable(30, By.name("txtprojlistclient"));

			//Thread.sleep(2000);
			//Project_PO.searchProject("test2");
			clickELementJS(driver.findElement(By.id("anc_Project")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.close();
			e.printStackTrace();
		}

	}

	@Test(priority = 3, dataProvider = "testdata")

	public void createProject(String clientName,String AddressName,String Address,String state,String city,String project,String projectstartdate,String projectenddate) {

		try {

			

			Project_PO.fillClientMandatoryFields(clientName,AddressName,Address,state,city);

			getWebElement("xpath__//*[@id='btnsaveClientfromProjects']").click();

			boolean clientExistStatus = Project_PO.clientExitsValidation();

			if(clientExistStatus)
			{
				Project_PO.resetClientMandatoryFields();
			}
			// fills project mandatory fields click on save and navigates to Client Page for new client record 
			else
				
			{
				Project_PO.fillProjectMandatoryFields(project,projectstartdate,projectenddate,AddressName);
				
				getWebElement("xpath__//*[@id='btnSaveClientProjectnew']").click();
				
				//getWebElement("xpath__//div[@class='jconfirm-buttons']/button[text()='NO']").click();
				
				getWebElement("xpath__//div[@class='jconfirm-buttons']/button[text()='YES']").click();
				getWebElement("id__OpensitemapProjects").click();
					
				driver.findElement(By.xpath("//*[@id='btnProject']")).click();

				WebDriverUtils.waitUntilElementClicable(30, By.name("txtprojlistclient"));

				//clicks on + icon of Select Vendor
				clickELementJS(driver.findElement(By.id("anc_Project")));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//driver.close();
		}

	}

	@DataProvider(name = "testdata")

	public Object[][] Testdata() throws Exception {

		Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\HOME\\Desktop\\Testdata.xlsx", "Project");

		return (testObjArray);

	}

}
