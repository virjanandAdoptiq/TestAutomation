package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"Gui"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class Z1Dev0000ForgorPassword {	
	  @BeforeClass
	  public void start() throws InterruptedException{
			Top.StartBroswer();			
	  }	  
	  @Test
	  public void forgotPassword() throws InterruptedException {	
		    Lib.ClickButton(By.xpath(D.$forgot_password_link));	    
		    Lib.InputData(Lib.ADV, By.xpath(D.$forgot_password_email));			
		    Lib.ClickButton(By.cssSelector(D.$forgot_password_send_button));
 	  }		
	  @AfterClass
	  public void stop() throws InterruptedException {
			Top.CloseBrowser();
	  } 
}
