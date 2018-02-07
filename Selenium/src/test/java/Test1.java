import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {
	
	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
		
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//*[@id="content"]/p[3]/button
		//driver.findElement(By.xpath("//button[text()='New Message Window']")).click();
		//driver.findElement(By.id("button1")).click();
		driver.findElement(By.xpath("//*[@id='content']/p[4]/button")).click();
		
		Set<String> s=driver.getWindowHandles();
		System.out.println(s.size());
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getTitle());
		for (String string : s) {
			System.out.println(s);
			
			if(!(driver.getWindowHandle().equals(string)))
			{
				driver.switchTo().window(string);
				driver.manage().window().maximize();
				System.out.println(driver.getTitle());
				System.out.println(driver.getPageSource());
				break;
			}
		}
		System.out.println(driver.getTitle());
		//System.out.println(driver.findElement(By.xpath("/html/body")).getText());
		
	}

}
