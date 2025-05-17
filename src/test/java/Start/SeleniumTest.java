package Start;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;


public class SeleniumTest {

	@Test
    void googleToBing() throws IOException
    {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
		WebDriver driver = new ChromeDriver(options);
	  
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  
	   
	  driver.get("https://www.google.com");
	  
      System.out.println("Title : " + driver.getTitle());
	  System.out.println("Current URl : " + driver.getCurrentUrl());
	  
	  WebElement textField = driver.findElement(By.xpath("//textarea[@title='Search']"));
	  textField.sendKeys("World");
	 // textField.sendKeys(Keys.ENTER); 
	  
	  File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(src, new File("screenshots/Google.png"));
	 
	 
	  System.out.println("getAttribute value : " + textField.getAttribute("aria-label"));
	  
	  
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("window.open('https://www.bing.com/', '_blank');");
	  
	  
	  System.out.println("Windowhandle : " + driver.getWindowHandles());
	  
	  String currentWindow = driver.getWindowHandle();
      
	 
	  
	  for(String window: driver.getWindowHandles())
	  {
		   
		   driver.switchTo().window(window);
		
		  
		    if(driver.getTitle().equals("Search - Microsoft Bing"))
			  {
			    break;
			    
			  }
	  }
	  
	  System.out.println("Title : " +driver.getTitle());
	  
	  driver.close();
	  
	  driver.quit();
	  
	  
  }


}