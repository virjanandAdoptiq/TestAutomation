package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import admin.Admin;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;

@Test(groups = {"A1"}) 
@Listeners(TestFailureListener.class)
public class A1Dev1028UnitCreate {
	@BeforeClass
	public void start() throws InterruptedException{
		D.FAILURE_INDICATION = 3; 
		Top.StartBroswer();
	}
	public void CreateData() throws InterruptedException {
		Top.Login(D.adminUser, D.adminPass);
	    Admin.GotoDevelop();
	    Admin.GotoUnitTest();
	    Admin.GotoUnitMenu("TestDataGenerator");
	    Admin.EnterName(Lib.ReadFromFile(D.credentialsFile, "username"), D.EMAIL);
	    Admin.StartRun();
	    Top.Logout();     
	 }
	 
	 @AfterClass
	 public void stop() throws InterruptedException {
		Top.CloseBrowser();
	 }
}
