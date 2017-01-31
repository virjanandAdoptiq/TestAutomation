package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"B7"}, dependsOnGroups="B6", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class B7Dev1000CheckConflicting {	
	  private String product1 = D.Cover2FullPage;
	  private String product2 = D.Cover2HalfLying;
	  private String media = Lib.BuyNow;
	  private String theDate = Lib.buyDay1;

	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}
	  
	  @Test(alwaysRun = true)
	  public void CheckConflictingLots() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
		     Top.Login(Lib.ADV,"Welkom01@1");
			 Exchange.GotoBuyerEchangePage();
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product1); 
			 Exchange.AddToMyLots(product2);  
             
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
