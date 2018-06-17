package utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	
	public static WebDriver driver=null;
	
	
	private static void until(WebDriver driver,Function<WebDriver, Boolean> waitCondition,Long i)
	{
			WebDriverWait webDriverWait=new WebDriverWait(driver, i);
			webDriverWait.withTimeout(20, TimeUnit.SECONDS);
			try {
				webDriverWait.until(waitCondition);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
	}
	
	
	public static void untilJQueryIsDone(Long timeoutInSeconds)
	{	
		until(driver, (driver) ->{
			Boolean isJqueryCallDone = (Boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
			if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
			return isJqueryCallDone;
		}, timeoutInSeconds);
	}
	public static WebElement getWebElement(String args) throws Exception
	{
		try {
			WebElement element=null;
			String[] value=args.split("__");
			String locatorType=value[0];
			String locatorValue=value[1];
			switch (locatorType) {
			case "xpath":
				element=driver.findElement(By.xpath(locatorValue));
				break;
			case "css":
				element=driver.findElement(By.cssSelector(locatorValue));
				break;
			case "name":
				element=driver.findElement(By.name(locatorValue));
				break;
			case "linkText":	
				element=driver.findElement(By.linkText(locatorValue));
				break;
			default:
				throw new Exception(locatorType+" not found in the locator list");
			}
			return element;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		
	}
	
	public static void scrollIntoElementView(WebElement ele)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].scrollIntoView()", ele);
	}
	
	public String captureScreenShot(String fileName) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			String destLocation=System.getProperty("user.dir")+"/Screenshots/"+fileName;
			FileUtils.copyFile(src, new File(destLocation));
			
			return destLocation;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void moveToElement(WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	public static void moveToElementAndClick(WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).click(element).build().perform();
	}
	
	
	public static void waitUntilPresenceOfElement(int timeoutSeconds,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeoutSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.withTimeout(timeoutSeconds, TimeUnit.SECONDS);
		//new WebDriverWait(driver,10).until(ExpectedConditions.)
	}
	
	
	public static void waitUntilElementClicable(int timeoutSeconds,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeoutSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		wait.withTimeout(timeoutSeconds, TimeUnit.SECONDS);
		
	}
	
	
	public static void waitUntilVisible(int timeoutSeconds,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, timeoutSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		wait.withTimeout(timeoutSeconds, TimeUnit.SECONDS);
		
	}

	public static void javaScriptClickElement(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
	}

}
