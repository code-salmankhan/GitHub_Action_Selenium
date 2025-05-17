package Start;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumTest {

    @Test
    void googleToBing() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 1: Go to Google
        driver.get("https://www.google.com");

        // Assertion: Title contains "Google"
        String googleTitle = driver.getTitle();
        System.out.println("Google Title : " + googleTitle);
        Assert.assertTrue(googleTitle.contains("Google"), "Title does not contain 'Google'");

        WebElement textField = driver.findElement(By.xpath("//textarea[@title='Search']"));
        textField.sendKeys("World");

        // Take screenshot of Google search
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("screenshots/Google.png"));

        // Assertion: aria-label of search box is "Search"
        String ariaLabel = textField.getAttribute("aria-label");
        System.out.println("getAttribute value : " + ariaLabel);
        Assert.assertEquals(ariaLabel, "Search", "aria-label is not 'Search'");

        // Step 2: Open Bing in new tab
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://www.bing.com/', '_blank');");

        // Switch to Bing tab
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
            if (driver.getCurrentUrl().contains("bing.com")) {
                break;
            }
        }

        // Assertion: Bing title contains "Bing"
        String bingTitle = driver.getTitle();
        System.out.println("Bing Title : " + bingTitle);
        Assert.assertTrue(bingTitle.contains("Bing"), "Title does not contain 'Bing'");

        driver.quit();
    }
}
