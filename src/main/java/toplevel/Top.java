package toplevel;

import java.util.List;

//from selenium.webdriver.common.desired_capabilities import DesiredCapabilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.MarionetteDriverManager;

public class Top {
	public static void  StartBroswer() throws InterruptedException
	{	
		if(D.browser.equals("firefox")){
/*			System.setProperty("webdriver.gecko.driver", "/Users/dejuan/Testing/gecko/geckodriver");

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			D.driver = new FirefoxDriver(capabilities);
*/
		    D.driver = new FirefoxDriver();
		} else
		if(D.browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "/Users/dejuan/Testing/driver/chromedriver");
			D.driver = new ChromeDriver();
			
		}else{
			System.out.println("This browser: " + D.browser + " is not supported yet");
			System.exit(0);			
		}
		D.driver.manage().window().maximize();
		D.driver.get(D.URL); 
		
		//initial the wait and long wait
		D.wait = new WebDriverWait(D.driver, D.maxWaitTime);
		D.longWait = new WebDriverWait(D.driver,D.longWaitTime);
	}
 
	public static void CloseBrowser() throws InterruptedException {
		    D.driver.close();
		    D.driver.quit();
		    Thread.sleep(D.waitTimeAfterCloseBrowser);    //for clean up
	}
	  
	public static void Login(String user, String pass) throws InterruptedException{ 
		InputData(user,By.cssSelector(D.$username));
		InputData(pass,By.cssSelector(D.$password));
		ClickButton(By.cssSelector(D.$signin));
	}
	
	public static void Logout() throws InterruptedException{
		Top.CloseDialogBox();
		ClickButton(By.cssSelector(D.$signout));
		}
	
	public static boolean LoginAcceptTerms() throws InterruptedException {
		try {
	    	  D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(D.$loginAccept)));	      
	    	  Thread.sleep(D.waitTime * 2); 
			  D.driver.findElement(By.xpath(D.$loginAccept)).click();
			  Thread.sleep(D.waitTime * 3);  
			  return true;
		} catch (Exception ex) {
			return false;
		}
	  }
	
    public static void LoginChangePassword(String oldPassword, String newPassword) throws InterruptedException {	
    	InputData(oldPassword,By.cssSelector(D.$oldPassword));
    	InputData(newPassword,By.cssSelector(D.$newPassword));
    	InputData(newPassword,By.cssSelector(D.$confirmNewPassword));
    	ClickButton(By.cssSelector(D.$changePasswordButton));
	  }

    public static void CloseDialogBox() throws InterruptedException{
    	D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
		Thread.sleep(D.waitTime);
    	if(D.driver.findElements(By.xpath(D.$OK_Button)).isEmpty() == false){
    		Thread.sleep(D.waitTime);
			D.driver.findElement(By.xpath(D.$OK_Button)).click();   
			Thread.sleep(D.waitTime);
    	}
	}
    
    public static void InputData(String value, By field) throws InterruptedException{
    	Helper.FindElement(field);
  //  	D.driver.findElement(field).click();
    	D.driver.findElement(field).clear();
		D.driver.findElement(field).sendKeys(value);
		Thread.sleep(D.waitTime);  
    }
    public static void ClickButton(By name) throws InterruptedException{			
 		Helper.FindElement(name);
 		List<WebElement> elements = D.driver.findElements(name);
 		for(WebElement ele: elements){
 			if(ele.isDisplayed()){
 				ele.click();
 				D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
 				Thread.sleep(D.waitTime);
 				break;
 			}
 		}
 	}
    public static void SendSpecialKey(Keys name) throws InterruptedException{
    	Thread.sleep(D.waitTime);
    	Actions builder = new Actions(D.driver);
    	builder.sendKeys(name).perform();
    	Thread.sleep(D.waitTime);
    	D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
    }
    public static void DoubleClicky(By name) throws InterruptedException{
    	Thread.sleep(D.waitTime);
    	Actions builder = new Actions(D.driver);
    	builder.moveToElement(D.driver.findElement(name)).doubleClick().build().perform();
    	Thread.sleep(D.waitTime);
    	D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
    }
    public static void SelectMenuLink(By menu, By link) throws InterruptedException{
    	Helper.FindElement(menu);
    	Thread.sleep(D.waitTime);
		D.driver.findElement(menu).click();
		Thread.sleep(D.waitTime);
		if(D.driver.findElements( link ).size() == 0){
			D.driver.findElement(menu).click();
			Thread.sleep(D.waitTime);
		}
		if(D.driver.findElements( link ).size() != 0){
		     D.driver.findElement(link).click();
		     D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
		Thread.sleep(D.waitTime);
		} else {
			Assert.fail();
		}
    }
    public static void SelectDropdownItem(By dlist, String item) throws InterruptedException{	
		Helper.FindElement(dlist);
		Thread.sleep(D.waitTime * 2);
		WebElement mySelectElm = D.driver.findElement(dlist); 
		Select mySelect= new Select(mySelectElm);
		mySelect.selectByVisibleText(item);
		D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
		Thread.sleep(D.waitTime);
	}
    
	public static void ClickContextSensitiveItem(By context, By item) throws InterruptedException{	
		Helper.FindElement(context);
		D.driver.findElement(context).findElement(item).click();
		Thread.sleep(D.waitTime);
	}
	
	
	public static boolean isBox(String name){
		String path = D.$infoErroBox + name + "']";
		try {
			D.driver.findElement(By.xpath(path));
			return true;
		} catch (Exception e) {
		   return false;
		}
	}
//Clicking somewhere harmless to trigger the action for a field 	
	public static void ClickAway() throws InterruptedException{
		D.driver.findElement(By.xpath(D.$PoweredByMendix)).click();
		Thread.sleep(D.waitTime);
	}
}
