package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import admin.Admin;
import toplevel.D;
import toplevel.TestFailureListener;
import toplevel.Top;

@Test(groups = {"DeleteTestData"})
@Listeners(TestFailureListener.class)
public class DEV1073UnitDelete {
	@BeforeClass
	public void start() throws InterruptedException{
		D.FAILURE_INDICATION = 3; //if test failed, logout and close browser
		Top.StartBroswer();
	}
	 public void steps1() throws InterruptedException {
		    Top.Login(D.adminUser, D.adminPass);
		    Admin.GotoDevelop();
		    Admin.GotoUnitTest();
		    Admin.GotoUnitMenu("General");
		    Admin.StartRun();
		    Top.Logout();     
		 }
	 @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		}
}
