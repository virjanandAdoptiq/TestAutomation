package testcases;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import publisher.Media;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"B8"}, dependsOnGroups="B7", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class B8Dev1000ExclusiveDeepLink {	
      String exclusiveDeepLink;
	  private String product1 = D.Pagina2FullPage;
	  private String product2 = D.Pagina2HalfLying;
	
	  @Test
	  public void makeExclusiveDeepLink() throws InterruptedException, UnsupportedFlavorException, IOException {			  
		  Top.StartBroswer(); 
		  exclusiveDeepLink = Media.CreateExclusiveDeepLink(Lib.UG2, Lib.Today, Lib.buyDay3);		 
		  System.out.println(exclusiveDeepLink);
		  Top.CloseBrowser();
	  }
	  
	//  String exclusiveDeepLink = "https://adoptiq100-test.mendixcloud.com/link/sis/inbehyk1";
	  @Test(dependsOnMethods="makeExclusiveDeepLink")
	  public void loginUseDeeplink() throws InterruptedException{
		  D.driver = new FirefoxDriver();	
		  D.driver.manage().window().maximize();
		  D.driver.get(exclusiveDeepLink); 
		  D.wait = new WebDriverWait(D.driver, D.maxWaitTime);
		  D.longWait = new WebDriverWait(D.driver,D.longWaitTime);
		
		  Top.DeeplinkLogin(Lib.MB,"Welkom01@1");	
	  }	
	  @Test(dependsOnMethods="loginUseDeeplink")
	  public void addToMyLots() throws InterruptedException{
		     Exchange.GotoBuyerEchangePage();
			 Exchange.EnterFromThroughDate(Lib.bidDay1);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product1); 
			 Exchange.AddToMyLots(product2);  
	  }	
	  @Test(dependsOnMethods="addToMyLots")
	  public void CheckConflictingLots() throws InterruptedException{		 
		     SoftAssert softAssert = new SoftAssert();
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product1, D.$bm_lot_status_conflicting));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product2, D.$bm_lot_status_conflicting));
			 
			 softAssert.assertAll();
	  }	
	  @Test(dependsOnMethods="CheckConflictingLots")
	  public void CheckClickConflictingSign() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

		     Mylots.OpenConflictingLotInfon(product1);	
		     
		     softAssert.assertEquals(D.driver.findElements(By.xpath(D.$bm_lot_conflicting_popup_close_button)).size(), 0);
		     Lib.ClickButton(By.xpath(D.$bm_lot_conflicting_popup_close_button));

			 
			 softAssert.assertAll();	 						 			 
	  }	

	  @Test(dependsOnMethods="CheckClickConflictingSign")
	  public void CheckNoMoreConflicting() throws InterruptedException {			  			 
		     SoftAssert softAssert = new SoftAssert();

		     Mylots.SelectALot(product1);			      
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product2, D.$bm_lot_status_saved));

		     Mylots.SelectALot(product2);			      
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));

			 Top.Logout();  
			 
			 softAssert.assertAll();
	  }	
	  @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		}
}
