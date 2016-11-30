package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import admin.Admin;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;

@Test(groups = {"AA"}) 
@Listeners(TestFailureListener.class)
public class Z0Dev1000SendDailyOverview {
	@BeforeClass
	public void start() throws InterruptedException{
		D.FAILURE_INDICATION = 3; 
		Top.StartBroswer();
	}
	public void CreateData() throws InterruptedException {
		Top.Login(D.adminUser, D.adminPass);
	    Admin.GotoSystem();
	    Lib.ClickButton(By.xpath(D.$ad_schematischeTaken));
	    Lib.ClickButton(By.xpath(D.$ad_schematischeTaken_dailyreport));
	    Top.Logout();     
	 }
	 
	 @AfterClass
	 public void stop() throws InterruptedException {
		Top.CloseBrowser();
	 }
}
