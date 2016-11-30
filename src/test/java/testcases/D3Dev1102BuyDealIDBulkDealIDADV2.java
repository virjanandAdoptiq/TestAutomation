package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import advertiser.Exchange;
import advertiser.Mylots;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"D3"}, dependsOnGroups="D2", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class D3Dev1102BuyDealIDBulkDealIDADV2 {
      String[][] orders; 	 

      @Test(alwaysRun = true)
	  public void AddInventoriesToMyLots() throws InterruptedException {	
	  		 Top.StartBroswer();
			 Top.Login(Lib.ADV2,"Welkom01@1");
		     			 
			 Exchange.GotoBuyerEchangePage();			 
			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Exchange.SelectMedia(Lib.BuyNow);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));		
			 Exchange.SelectAInventory("CD102VL - Cover 2");
			 Exchange.SelectAInventory("CD102VS - Cover 3");
			 Exchange.SelectAInventory("CD102VL - Cover 3");
			 Lib.ClickButton(By.cssSelector(D.$be_addAllSelectedToMyLots));
	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
		
      }
      @Test(dependsOnMethods="AddInventoriesToMyLots")
	  public void SetSingleDealIdAndBuy() throws InterruptedException {
    	     Mylots.SelectALot("CD102VL - Cover 2"); 
    	     Mylots.ExpandALot("CD102VL - Cover 2", "grey");
			 Mylots.SetDealID("CD102VL - Cover 2", "oneID");
			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);					 			 
	  }	
      @Test(dependsOnMethods="SetSingleDealIdAndBuy")
	  public void SetBulkDealIdAndBuy() throws InterruptedException {
			 Mylots.SelectALot("CD102VS - Cover 3");
			 Mylots.SelectALot("CD102VL - Cover 3");

			 Mylots.SetBulkDealID("BulkDeal1");

			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
			   
			 Top.Logout(); 
			 Top.CloseBrowser();
      }      
}
