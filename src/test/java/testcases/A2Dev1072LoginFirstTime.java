package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"A2"}, dependsOnGroups="A1")
@Listeners(TestFailureListener.class)
public class A2Dev1072LoginFirstTime {
	
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void start() throws InterruptedException{
		D.FAILURE_INDICATION = 3; 
		Top.StartBroswer();
	}
	
	@Test(dataProvider = "users")
	public void logInChangePass(String user, String email) throws InterruptedException{
	    Top.Login(user, "Welkom01@");
	    softAssert.assertTrue(Top.LoginAcceptTerms());
	    Top.LoginChangePassword("Welkom01@", "Welkom01@1");

	    Top.Logout();  
	    
		D.FAILURE_INDICATION = 0; 
		softAssert.assertAll();
		D.FAILURE_INDICATION = 3; 	
	}
		  	
	@DataProvider
	  public Object[][] users() {
	    return new Object[][] { 
	      { Lib.ADV,D.EMAIL },
	      { Lib.ADV2,D.EMAIL},
	      { Lib.MB,D.EMAIL },
	      { Lib.UG,D.EMAIL }, 
	      { Lib.UG2,D.EMAIL }, 
	      { Lib.Res,D.EMAIL }, 
	      { Lib.Res2,D.EMAIL }  
	    };
	  }
	
	@AfterClass
	public void stop() throws InterruptedException {
		 Top.CloseBrowser();
	}
}
