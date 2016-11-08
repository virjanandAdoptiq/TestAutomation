package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.D;
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"LoginFirstTime"}, dependsOnGroups="CreateData", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class A2Dev1072LoginFirstTime {
	
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void start() throws InterruptedException{
		D.FAILURE_INDICATION = 3; //if test failed, logout and close browser
		Top.StartBroswer();
	}
	
	@Test(dataProvider = "users")
	public void logInChangePass(String user, String email) throws InterruptedException{
	    Top.Login(user, "Welkom01@");
	    softAssert.assertTrue(Top.LoginAcceptTerms());
	    Top.LoginChangePassword("Welkom01@", "Welkom01@1");
	    
	    //set email account
	//    Account.GotoAccountMyAccount();
	//	Account.SetMyAccountEmailAddress(email);
	//	Top.ClickButton(By.xpath(D.$SaveMyAccount));
		
	    Top.Logout();  
	    
		 D.FAILURE_INDICATION = 0; //if test failed, do nothing
		 softAssert.assertAll();
		 D.FAILURE_INDICATION = 3; //if test failed, logout and close browser	
	}
		  	
	@DataProvider
	  public Object[][] users() {
	    return new Object[][] { 
	      { Helper.ADV,D.EMAIL },
	      { Helper.ADV2,D.EMAIL},
	      { Helper.MB,D.EMAIL },
	      { Helper.UG,D.EMAIL }, 
	      { Helper.UG2,D.EMAIL }, 
	      { Helper.Res,D.EMAIL }, 
	      { Helper.Res2,D.EMAIL }  
	    };
	  }
	
	@AfterClass
	public void stop() throws InterruptedException {
		Top.CloseBrowser();
	}
}
