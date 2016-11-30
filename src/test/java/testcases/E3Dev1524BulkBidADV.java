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


@Test(groups = {"E3"}, dependsOnGroups="E2", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class E3Dev1524BulkBidADV {	
	  private String media = Lib.BuyNow;
	  private String theDate = Lib.bidDay1;
	  @BeforeClass
	  public void start() throws InterruptedException{
				Top.StartBroswer();
	  }
	  
	  @Test
	  public void AddToMyLotsAndBid() throws InterruptedException {	
		     Top.Login(Lib.ADV,"Welkom01@1");
			 Exchange.GotoBuyerEchangePage();
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots("CD102VS - Cover 3");  
			 Exchange.AddToMyLots("CD102VL - Cover 3"); 
	 	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot("CD102VS - Cover 3");
			 Mylots.SelectALot("CD102VL - Cover 3");
		     Lib.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       
			 
			 SoftAssert softAssert = new SoftAssert();
			 softAssert.assertTrue(Mylots.CheckLotStatus("CD102VS - Cover 3", D.$bm_lot_status_bid));
			 softAssert.assertTrue(Mylots.CheckLotStatus("CD102VL - Cover 3", D.$bm_lot_status_bid));
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  		     						 			 
	  @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		} 
}
