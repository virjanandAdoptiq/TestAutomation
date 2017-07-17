package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import publisher.ExchangeP;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"D3"}, dependsOnGroups="D2", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class D3Dev1102BuyDealIDBulkDealIDADV2 {
      String[][] orders; 	
      String product1 = D.Cover2HalfLying;
      String product2 = D.Cover3HalfStand;
      String product3 = D.Cover3HalfLying;

      @Test(alwaysRun = true)
	  public void AddInventoriesToMyLots() throws InterruptedException {	
	         Lib.deleteAllMailsFromInbox();
	  		 Top.StartBroswer();
			 Top.Login(Lib.ADV2,"Welkom01@1");
		     			 
			 Exchange.GotoBuyerEchangePageTileView();	
			 Exchange.ClickAMediaTile(Lib.BuyNow);
			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));		
			 Exchange.SelectAInventory(product2);
			 Exchange.SelectAInventory(product3);
			 Lib.ClickButton(By.cssSelector(D.$be_addAllSelectedToMyLotsTileView));
			 
			 
			 Lib.ClickButton(By.xpath(D.$be_restore));
			 Exchange.EnterFromThroughDate(Lib.weekDay);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product1);
	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
		
      }
      @Test(dependsOnMethods="AddInventoriesToMyLots")
	  public void SetSingleDealIdAndBuy() throws InterruptedException {
    	     Mylots.SelectALot(product1); 
    	     Mylots.ExpandALot(product1, "grey");
			 Mylots.SetDealID(product1, "oneID");
			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);					 			 
	  }	
      @Test(dependsOnMethods="SetSingleDealIdAndBuy")
	  public void SetBulkDealIdAndBuy() throws InterruptedException {
			 Mylots.SelectALot(product2);
			 Mylots.SelectALot(product3);

			 Mylots.SetBulkDealID("BulkDeal1");
//work around
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(product2);
			 Mylots.SelectALot(product3);
//
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
			   
			 Top.Logout(); 
      }  
	  @Test(dependsOnMethods="SetBulkDealIdAndBuy")
	  public void setPricesByRes() throws InterruptedException{
		    D.FAILURE_INDICATION = 3; //if test failed, logout and close browser

			Top.Login(Lib.Res, "Welkom01@1");

			ExchangeP.GoToExchangePlatform();
			Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));		
			ExchangeP.SetPrivatePrice("30");
			ExchangeP.ExpandANegotiationy("5.500,00");

			Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));
			ExchangeP.SetPrivatePrice("40");
			
			Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));
			ExchangeP.SetPrivatePrice("40");
			
			Top.Logout();
	  }
	  @Test(dependsOnMethods="setPricesByRes")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("D3Dev1102BuyDealIDBulkDealIDADV2", 13), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();  
	  }	
}
