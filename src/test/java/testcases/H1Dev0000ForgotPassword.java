package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Account;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"H1"}, dependsOnGroups="G5", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class H1Dev0000ForgotPassword {  
	  String URL;
      @BeforeClass
	  public void start() throws InterruptedException{
	        Lib.deleteAllMailsFromInbox();
			Top.StartBroswer();
	  }
      @Test
	  public void forgotPassword() throws InterruptedException {	
		    Lib.ClickButton(By.xpath(D.$forgot_password_link));	    
		    Lib.InputData(Lib.Res, By.xpath(D.$forgot_password_username));	
		    Lib.InputData(D.EMAIL, By.xpath(D.$forgot_password_email));
		    Lib.ClickButton(By.xpath(D.$img_email_send));
	        Top.CloseBrowser();  
 	  }	
      @Test(dependsOnMethods="forgotPassword")
	  public void getEmailHttp() throws InterruptedException{
			URL = Lib.getPasswordResetEmailHttp().toString();
			System.out.println(URL);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(URL.startsWith("http"));
			softAssert.assertAll();					  
	  }
	  @Test(dependsOnMethods="getEmailHttp")
	  public void gotoURLChangeLogin() throws InterruptedException{
		  Top.GetBroswer(URL);
		  D.wait = new WebDriverWait(D.driver, D.maxWaitTime);
		  D.longWait = new WebDriverWait(D.driver,D.longWaitTime);	
		  Lib.InputData("Welkom01@2", By.xpath(D.$forgot_password_new_password));
		  Lib.InputData("Welkom01@2", By.xpath(D.$forgot_password_new_password_confirm));
		  Lib.ClickButton(By.xpath(D.$forgot_password_new_password_send));
		  Lib.CloseDialogBox();
		  Top.Login(Lib.Res, "Welkom01@2");
	  }
	  @Test(dependsOnMethods="gotoURLChangeLogin")
	  public void ChangePasswordBack() throws InterruptedException{
          Account.GotoAccountMyAccount();
          Lib.ClickButton(By.xpath(D.$img_change_password));
          Lib.InputData("Welkom01@2", By.xpath(D.$MyAccountOldPassword));
          Lib.InputData("Welkom01@1", By.xpath(D.$MyAccountNewPassword));
          Lib.InputData("Welkom01@1", By.xpath(D.$MyAccountRepeatPassword));
          Lib.ClickButton(By.xpath(D.$MyAccountResetPasswordSave));
          
		  Top.Logout();
	  }
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();  
	  }	

}
