package testcases;


import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import toplevel.Account;
import toplevel.D;
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"CheckPrice"}, dependsOnGroups="LoginFirstTime", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class Testing1 {		  

	  @BeforeClass
	  public void start() throws InterruptedException{
				Top.StartBroswer();
				Top.Login(Helper.MB,"Welkom01@1");
				D.FAILURE_INDICATION = 0; //if softAssertionAll failed, do not logout close browser
	  }
	  public void findLMInventoryForMedia1() throws InterruptedException{
		 
			 
	  }
	  
	

	@AfterClass
	public void stop() throws InterruptedException {
			    
	//			Top.CloseBrowser();
	} 
}
