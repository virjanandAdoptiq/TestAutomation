package testcases;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import advertiser.Exchange;
import toplevel.D;
import toplevel.Lib;
import toplevel.Top;

public class ZZZ {
	
	@Test
	public void test() throws InterruptedException{
	/*	
		System.setProperty("webdriver.gecko.driver", "/Users/dejuan/Testing/driver/geckodriver");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		WebDriver driver = new FirefoxDriver(capabilities);
		driver.manage().window().maximize();
		driver.get(D.URL);
	*/	
		//works fine with 2.53.1  but not for 3.0.1 (Class not found)
/*		
		System.setProperty("webdriver.chrome.driver", "/Users/dejuan/Testing/driver/chromedriver");	
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("marionette", true);
		WebDriver driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.get(D.URL);
*/		 
		Lib.deleteAllMailsFromInbox();


	}

}
