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


@Test//(groups = {"E3"}, dependsOnGroups="E2", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class E3Dev1524BulkBidADV {	  
	  String product1 = D.Cover3HalfStand;
	  String product2 = D.Cover3HalfLying;
	  private String media = Lib.BuyNow;
	  private String theDate = Lib.bidDay1;
	  @BeforeClass
	  public void start() throws InterruptedException{
	        Lib.deleteAllMailsFromInbox();
			Top.StartBroswer();
	  }
	  
	  @Test
	  public void AddToMyLotsAndBid() throws InterruptedException {	
		     Top.Login(Lib.ADV,"Welkom01@1");
			 Exchange.GotoBuyerEchangePage();
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
			 softAssert.assertTrue(Mylots.CheckLotStatus(product1, D.$bm_lot_status_bid));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product2, D.$bm_lot_status_bid));
			 Top.Logout();  
			 Top.CloseBrowser();
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();		     						 			 
	  }	
	  @Test(dependsOnMethods="AddToMyLotsAndBid")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("E3Dev1524BulkBidADV", 4), "emailCorrect");				
			softAssert.assertAll(); 		  
	  }	 		     						 			 
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();  
	  }	
}
