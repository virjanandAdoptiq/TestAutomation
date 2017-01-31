package toplevel;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import io.github.bonigarcia.wdm.MarionetteDriverManager;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.firefox.MarionetteDriver;

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
		if(!D.driver.toString().equals(D.driverIsNull))
		{
		    D.driver.close();
		    D.driver.quit();
		    Thread.sleep(D.waitTimeAfterCloseBrowser); 
		}
	}
	  
	public static void Login(String user, String pass) throws InterruptedException{ 
		Lib.InputData(user,By.cssSelector(D.$username));
		Lib.InputData(pass,By.cssSelector(D.$password));
		Lib.ClickButton(By.cssSelector(D.$signin));
	}
	
	public static void Logout() throws InterruptedException{
		    Lib.CloseDialogBox();
		    Lib.ClickButton(By.cssSelector(D.$signout));
		 
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
    	Lib.InputData(oldPassword,By.cssSelector(D.$oldPassword));
    	Lib.InputData(newPassword,By.cssSelector(D.$newPassword));
    	Lib.InputData(newPassword,By.cssSelector(D.$confirmNewPassword));
    	Lib.ClickButton(By.cssSelector(D.$changePasswordButton));
	  }

	public static void DeeplinkLogin(String user, String pass) throws InterruptedException{ 
		Lib.InputData(user,By.xpath(D.$Deeplink_login_user));
		Lib.InputData(pass,By.xpath(D.$Deeplink_login_password));
		Lib.ClickButton(By.xpath(D.$Deeplink_login_signIn));
	}
  }
