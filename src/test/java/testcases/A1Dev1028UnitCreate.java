package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import admin.Admin;
import toplevel.D;
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;

@Test(groups = {"CreateData"}) 
@Listeners(TestFailureListener.class)
public class A1Dev1028UnitCreate {
	@BeforeClass
	public void start() throws InterruptedException{
		D.FAILURE_INDICATION = 3; //if test failed, logout and close browser
		Top.StartBroswer();
	}
	 public void CreateData() throws InterruptedException {
		Top.Login(D.adminUser, D.adminPass);
	    Admin.GotoDevelop();
	    Admin.GotoUnitTest();
	    Admin.GotoUnitMenu("Media");
	    Admin.EnterName(Helper.ReadFromFile(D.credentialsFile, "username"), D.EMAIL);
//	    Admin.EnterName(D.credentials);
	    Admin.StartRun();
	    Top.Logout();     
	 }
	 
	 @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		}

}
