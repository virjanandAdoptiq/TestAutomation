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


@Test//(groups = {"E4"}, dependsOnGroups="E3", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class E4Dev1525BulkBidMB {	
	 String product1 = D.Cover3HalfStand;
	 String product2 = D.Cover3HalfLying;
	  private String media = Lib.BuyNow;
	  private String theDate = Lib.bidDay2;
	  @BeforeClass
	  public void start() throws InterruptedException{
	        Lib.deleteAllMailsFromInbox();
			Top.StartBroswer();
	  }
	  
	  @Test
	  public void AddToMyLotsAndBid() throws InterruptedException {	
		     Top.Login(Lib.MB,"Welkom01@1");
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(Lib.CampaignADV2);
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product1);  
			 Exchange.AddToMyLots(product2); 
	 	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product1);
			 Mylots.SelectALot(product2);
		     Lib.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       
			 
			 SoftAssert softAssert = new SoftAssert();
			 Mylots.ExpandAGroup("Bod");
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1, D.$bm_lot_status_bid),"Not in Bid status:" + product1);
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2, D.$bm_lot_status_bid),"Not in Bid status: " + product2);

			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();		     						 			 
	  }	
	  @Test(dependsOnMethods="AddToMyLotsAndBid")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("E4Dev1525BulkBidMB", 6), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 		     						 			 
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();  
	  }	
}
