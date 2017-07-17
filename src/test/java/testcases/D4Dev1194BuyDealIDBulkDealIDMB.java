package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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


@Test//(groups = {"D4"}, dependsOnGroups="D3", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class D4Dev1194BuyDealIDBulkDealIDMB {
      String[][] orders; 
      String product1 = D.Cover2HalfStand;
      String product2 = D.Cover3HalfLying;
      String product3 = D.Cover3HalfStand;
      String productP1 = "CD102VS - Cover 2";
      String productP2 = "CD102VL - Cover 3";
      String productP3 = "CD102VS - Cover 3";
      
      @BeforeClass
	  public void start() throws InterruptedException{
	        Lib.deleteAllMailsFromInbox();
			Top.StartBroswer();
	  }
      @Test(alwaysRun = true)
	  public void AddInventoriesToMyLots() throws InterruptedException {	
			 Top.Login(Lib.MB,"Welkom01@1");
		     			 
			 Exchange.GotoBuyerEchangePageTileView();
			 Exchange.SelectCampaign(Lib.CampaignADV);			 
			 Exchange.EnterFromThroughDate(Lib.weekDay); 
			 Exchange.SelectMedia(Lib.BuyNow);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));		
			 Exchange.ClickAMediaTile(Lib.BuyNow);
			 Exchange.SelectAInventory(product1);
			 Exchange.SelectAInventory(product2);
			 Exchange.SelectAInventory(product3);
			 Lib.ClickButton(By.cssSelector(D.$be_addAllSelectedToMyLotsTileView));
	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); //Go to My Lots
			 //do not show orders
			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
		
      }
      @Test(dependsOnMethods="AddInventoriesToMyLots")
	  public void SetSingleDealIdAndBuy() throws InterruptedException {
   	         Mylots.SelectALot(product1); 
    	     Mylots.ExpandALot(product1, "grey");
			 Mylots.SetDealID(product1, "oneID2");
			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);		
			 
			 Top.Logout();  //work around

	  }	
      @Test(dependsOnMethods="SetSingleDealIdAndBuy")
	  public void SetBulkDealIdAndBuyAndGetOrderOverview() throws InterruptedException {
    	     Top.Login(Lib.MB,"Welkom01@1");
       	     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);
			 Mylots.SelectALot(product2);
			 Mylots.SelectALot(product3);

			 Mylots.SetBulkDealID("BulkDeal2");

			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);
			 Mylots.SelectALot(product2);
			 Mylots.SelectALot(product3);
			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			 orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 6, 12));  
			 Top.Logout(); 
      }
	  @Test(dataProvider="inputdata", dependsOnMethods="SetBulkDealIdAndBuyAndGetOrderOverview")
	  public void checkOrderResults(String pubDate, String media, String format, String page,
			                        String advertiser, String campaign,
	                                String price, String ppp, String surcharge) {	
		     D.FAILURE_INDICATION = 0; //if test failed, do nothing
		     SoftAssert softAssert = new SoftAssert();
		     			 			 
			 int index =0;
			  for(int i = 0; i < 6; i++){
				  if(orders[i][5].equals(format) && orders[i][6].equals(page) && orders[i][1].equals(pubDate)){
					  index = i;
					  softAssert.assertEquals(orders[index][2], media);
					  softAssert.assertEquals(orders[index][7], advertiser);
					  softAssert.assertEquals(orders[index][8], campaign);
					  softAssert.assertEquals(orders[index][9], price);
					  softAssert.assertEquals(orders[index][10], ppp);
					  softAssert.assertEquals(orders[index][11], surcharge);	
					  break;
				  }
				  index = i + 1;
			  }
			  softAssert.assertNotEquals(index, 6);
			  
			  
			  softAssert.assertAll();			  
	  }
	  @DataProvider
	  public Object[][] inputdata() {
	    return new Object[][] { 
	      {Lib.weekDay,Lib.BuyNow,"1/2 pagina volledig staand", "Cover 2",Lib.ADV,Lib.CampaignADV," ","Nee","0,00"},
	      {Lib.weekDay,Lib.BuyNow,"1/2 pagina volledig liggend","Cover 3",Lib.ADV,Lib.CampaignADV," ","Nee","0,00"},
	      {Lib.weekDay,Lib.BuyNow,"1/2 pagina volledig staand","Cover 3",Lib.ADV,Lib.CampaignADV," ","Nee","0,00"},
	    };
	  }

	  @Test(dependsOnMethods="checkOrderResults")
	  public void setPricesByUG() throws InterruptedException{
		    D.FAILURE_INDICATION = 3; //if test failed, logout and close browser

			Top.Login(Lib.UG, "Welkom01@1");

			ExchangeP.GoToExchangePlatform();
			Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));		
			ExchangeP.SetPrivatePrice("30");
			Top.Logout();
	  }
	  @Test(dependsOnMethods="setPricesByUG")
	  public void setPricesByRes() throws InterruptedException{
			Top.Login(Lib.Res, "Welkom01@1");
			ExchangeP.GoToExchangePlatform();
			ExchangeP.ExpandANegotiationy("5.500,00");

			Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));
			ExchangeP.SetPrivatePrice("40");
			
			Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));
			ExchangeP.SetPrivatePrice("40");
			
			Top.Logout();
	  }
	  
	  @Test(dependsOnMethods="setPricesByRes", alwaysRun = true)
	  public void campaignMultipleBuyGetOrderOverview() throws InterruptedException {	
			Top.Login(Lib.MB,"Welkom01@1");
		     			 			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   //Go to OrderOverview
			 orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 6, 12));  //get all orders
			 Top.Logout(); 		 			     						 			 
	  }	
	  @Test(dataProvider="inputdata2", dependsOnMethods="campaignMultipleBuyGetOrderOverview")
	  public void checkFinalOrderResults(String pubDate, String media, String format, String page,
			                        String advertiser, String campaign,
	                                String price, String ppp, String surcharge) {	
		     D.FAILURE_INDICATION = 0; //if test failed, do nothing
		     SoftAssert softAssert = new SoftAssert();
		     			 			 
			 int index =0;
			  for(int i = 0; i < 6; i++){
				  if(orders[i][5].equals(format) && orders[i][6].equals(page) && orders[i][1].equals(pubDate)){
					  index = i;
					  softAssert.assertEquals(orders[index][2], media);
					  softAssert.assertEquals(orders[index][7], advertiser);
					  softAssert.assertEquals(orders[index][8], campaign);
					  softAssert.assertEquals(orders[index][9], price);
					  softAssert.assertEquals(orders[index][10], ppp);
					  softAssert.assertEquals(orders[index][11], surcharge);	
					  break;
				  }
				  index = i + 1;
			  }
			  softAssert.assertNotEquals(index, 6);
			  
			  D.FAILURE_INDICATION = 0;
			  softAssert.assertAll();			  
	  }
	  @DataProvider
	  public Object[][] inputdata2() {
	    return new Object[][] { 
//	      {Lib.buyDay2,Lib.BuyNow2,"CD101V - Pagina 2",Lib.ADV2,Lib.CampaignADV2,"3.500,00","Nee","0,00"},
	      {Lib.weekDay,Lib.BuyNow,"1/2 pagina volledig liggend","Cover 3",Lib.ADV,Lib.CampaignADV,"1.500,00","Nee","0,00"},
	      {Lib.weekDay,Lib.BuyNow,"1/2 pagina volledig staand","Cover 3",Lib.ADV,Lib.CampaignADV,"1.500,00","Nee","0,00"},
	    };
	  }
	  @Test(dependsOnMethods="checkFinalOrderResults")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("D4Dev1194BuyDealIDBulkDealIDMB", 12), "emailCorrect");				
			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			  
	  }	 
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();  
	  }	
}
