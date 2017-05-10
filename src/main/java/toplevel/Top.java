package toplevel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Top {
	public static void  StartBroswer() throws InterruptedException
	{	
        GetBroswer(D.URL);
		//initial the wait and long wait
		D.wait = new WebDriverWait(D.driver, D.maxWaitTime);
		D.longWait = new WebDriverWait(D.driver,D.longWaitTime);
	}
	public static void  GetBroswer(String URL) throws InterruptedException
	{	
		if(D.browser.equals("firefox")){
//			System.setProperty("webdriver.gecko.driver", "/Users/dejuan/Testing/driver/geckodriver2");
		    D.driver = new FirefoxDriver();
		    D.driver.manage().window().maximize();
		    D.driver.get(URL); 
		    
		} else
		if(D.browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver/chromedriver-4");
			//Switch off save password pops up
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("password_manager_enabled", false); 
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			D.driver = new ChromeDriver(capabilities);
			//D.driver = new ChromeDriver();

			Lib.maximizeScreen(D.driver);
			D.driver.get(URL); 
			
			//Let browser be in front
			Robot rb;
			try {
			rb = new Robot();
			rb.mouseMove(D.driver.manage().window().getPosition().getX(),D.driver.manage().window().getPosition().getY());
			rb.mousePress(InputEvent.BUTTON1_MASK);
			rb.mouseRelease(InputEvent.BUTTON1_MASK);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			
		}else{
			System.out.println("This browser: " + D.browser + " is not supported yet");
			System.exit(0);			
		}
	}
 
	public static void CloseBrowser() throws InterruptedException {
		if(!D.driver.toString().contains("(null)"))
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
	public static void GotoMyAccountPage() throws InterruptedException{	
		String menu = D.$Menu + D.$MenuAccount + ")]";
		String item = D.$MenuLink + D.$ItemMyAccount + ")]";
		Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}

//    public static void checkOnEmails() throws InterruptedException{
//		String menu = D.$Menu + D.$MenuAccount + ")]";
//		String path = D.$MenuLink + D.$ItemMyOrganisation + ")]";
//		Lib.SelectMenuLink(By.xpath(menu), By.xpath(path));		
//		Lib.ClickButton(By.xpath(D.$MyAccount_Email_Setting));
//		
//		Lib.ClickButton(By.xpath(D.$MyAccount_Email_Option));
//		Lib.ClickAll(By.xpath(D.$MyAccount_Email_Checkbox));
//		Lib.ClickButton(By.xpath(D.$MyAccount_Email_Bid));
//		Lib.ClickAll(By.xpath(D.$MyAccount_Email_Checkbox));
//		Lib.ClickButton(By.xpath(D.$MyAccount_Email_Order));
//		Lib.ClickAll(By.xpath(D.$MyAccount_Email_Checkbox));
//		Lib.ClickButton(By.xpath(D.$MyAccount_Email_PrivateOrder));
//		Lib.ClickAll(By.xpath(D.$MyAccount_Email_Checkbox));
//		Lib.ClickButton(By.xpath(D.$MyAccount_Email_Negotiation));
//		Lib.ClickAll(By.xpath(D.$MyAccount_Email_Checkbox));
//		Lib.ClickButton(By.xpath(D.$MyAccount_Email_DailyOverView));
//		Lib.ClickAll(By.xpath(D.$MyAccount_Email_Checkbox));
//		
//		Lib.ClickButton(By.xpath(D.$MyAccount_Save));
//    }
}
