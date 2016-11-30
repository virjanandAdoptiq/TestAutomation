package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;
import toplevel.Top;

@Test
public class ZZ_TestCaseTemplate {
	@BeforeClass
	public void start() throws InterruptedException{
		Top.StartBroswer();
	}
	
	@Test
	public void step1() throws InterruptedException{
		SoftAssert softAssert = new SoftAssert();
		// the test steps, for example:
		Top.Login(Lib.ADV,"Welkom01@1");
		Thread.sleep(2000);
		//
		Top.Logout();
		softAssert.assertAll();		 
	}

	@AfterClass
	public void stop() throws InterruptedException {
		Top.CloseBrowser();
	}
}