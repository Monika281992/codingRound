package CodingChallenge.TestVagrant;



import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class flightBookingTest {
	
	public WebDriver driver;
	String URL = "https://www.cleartrip.com/";
	String chromedriverpath = "//Users//monikachaudhary//Downloads//chromedriver";
	String pastdate = "Looks like your dates are in the the past";
			
	
	@BeforeTest
	public void browserinititae(){
		
	   if (Platform.MAC!= null) {
		    System.setProperty("webdriver.chrome.driver", chromedriverpath);
		    driver = new  ChromeDriver();}
	   else if (Platform.WINDOWS!= null) {
		    System.setProperty("webdriver.chrome.driver", chromedriverpath);
		    driver = new  ChromeDriver();}
	   else if (Platform.LINUX!= null) {
		    System.setProperty("webdriver.chrome.driver", chromedriverpath);
		    driver = new  ChromeDriver();}
	   }

    @Test
    public  void Onewayjourney() throws InterruptedException {
    	
    	    driver.get(URL); 
            driver.findElement(By.xpath("//input[@id='OneWay']")).click();
            driver.findElement(By.id("FromTag")).click();
            driver.findElement(By.id("FromTag")).sendKeys("bangalore");
            
            //wait for the auto complete options to appear for the origin

            Thread.sleep(5000);
            driver.findElement(By.id("FromTag")).sendKeys(Keys.ARROW_DOWN);
            driver.findElement(By.id("FromTag")).sendKeys(Keys.ENTER);
            driver.findElement(By.id("ToTag")).sendKeys("Delhi");
            
            //wait for the auto complete options to appear for the destination

            Thread.sleep(5000);
            driver.findElement(By.id("ToTag")).sendKeys(Keys.ARROW_DOWN);
            driver.findElement(By.id("ToTag")).sendKeys(Keys.ENTER);
            driver.findElement(By.id("DepartDate")).clear();
            driver.findElement(By.id("DepartDate")).click();
            driver.findElement(By.xpath("a[contains(@class,'ui-state-default ui-state-highlight ui-state-active')]")).click();
            
            //all fields filled in. Now click on search
            driver.findElement(By.id("SearchBtn")).submit();  
            Thread.sleep(5000);
            
            //verify that result appears for the provided journey search
            Assert.assertTrue(isElementPresent(By.className("searchSummary")));
            
            
            // print the warning message
             if (driver.findElement(By.xpath("//div[@class='warningMessage']//p//following-sibling::h2")).isDisplayed()) {
            	 String Waringmessage = driver.findElement(By.xpath("//div[@class='warningMessage']//p//following-sibling::h2")).getText();
            	 System.out.println("Warning message = " + Waringmessage); 
             }else {
            	 System.out.println("No warning"); 
             }
			
           
        
          }
   
           private boolean isElementPresent(By by) {
           try {
            driver.findElement(by);
            return true;
          }  catch (NoSuchElementException e) {
            return false;
        }
    }

       @AfterTest
       public void driverQuit() {
    	   
       //close the browser
	   driver.quit();
	
       }}


	
	



