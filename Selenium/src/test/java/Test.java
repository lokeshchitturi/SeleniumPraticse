import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class Test {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver","D:\\SeleniumPraticse\\Selenium\\src\\main\\driver\\chromedriver.exe" );
		WebDriver driver=new ChromeDriver();
		driver.get("http://demo.automationtesting.in/Register.html");
		
		
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@type='text'][@placeholder='First Name']")).sendKeys("Test");
		
		
		List<WebElement> list=driver.findElements(By.xpath("//input[@ng-model='radiovalue']"));
		
		String userinput="FeMale";
		
		System.out.println(list.size());
		
		
		
		driver.findElement(By.xpath("//input[@value='"+userinput+"']")).click();
		
		
		List<WebElement> checkboxes=driver.findElements(By.xpath("//*[@type='checkbox']"));
		
		String checkValue="Cricket";
		
		for (WebElement web : checkboxes) {
			System.out.println(web.getAttribute("value"));
			
			String webText=web.getAttribute("value");
			System.out.println(webText);
			
			if(webText.equalsIgnoreCase(checkValue))
			{
				web.click();
				break;
			}
			
		}
		
		
		WebElement dropdown=driver.findElement(By.id("Skills"));
		Select select =new Select(dropdown);
		
		
		
		
	/*	for (WebElement webElement : list) {
	
			System.out.println(webElement.getAttribute("value"));
			
			if(webElement.getAttribute("value").equalsIgnoreCase(userinput))
			{
				webElement.click();
				break;
			}
		}*/
		
		
		
		
		//driver.close();
		
	}
}
