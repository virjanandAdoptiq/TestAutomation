package testcases;

import org.openqa.selenium.By;
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
      String product1 = "CD102VS - Cover 2";
      String product2 = "CD102VL - Cover 3";
      String product3 = "CD102VS - Cover 3";
      
      @BeforeClass
	  public void start() throws InterruptedException{
			Top.StartBroswer();
	  }

      @Test(alwaysRun = true)
	  public void AddInventoriesToMyLots() throws InterruptedException {	
			Top.Login(Lib.MB,"Welkom01@1");
		     			 
		     //add inventory to MyLots
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(Lib.CampaignADV);			 
			 Exchange.EnterFromThroughDate(Lib.weekDay); //buyDay2 for BuyNow 2 no weekDay
			// Exchange.SelectFormat("CD101V");
			 Exchange.SelectMedia(Lib.BuyNow);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));		
			 Exchange.SelectAInventory(product1);
			 Exchange.SelectAInventory(product2);
			 Exchange.SelectAInventory(product3);
			 Lib.ClickButton(By.cssSelector(D.$be_addAllSelectedToMyLots));
	
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

			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			 orders = Lib.SortOrders(Lib.GetTableContent(D.$b_orderoverview_table, 6, 12));  
			 Top.Logout(); 
      }
	  @Test(dataProvider="inputdata", dependsOnMethods="SetBulkDealIdAndBuyAndGetOrderOverview")
	  public void checkOrderResults(String pubDate, String media, String product,
			                        String advertiser, String campaign,
	                                String price, String ppp, String surcharge) {	
		     D.FAILURE_INDICATION = 0; //if test failed, do nothing
		     SoftAssert softAssert = new SoftAssert();
		     			 			 
			 int index =0;
			  for(int i = 0; i < 6; i++){
				  if(orders[i][5].equals(product) && orders[i][1].equals(pubDate)){
					  index = i;
					  softAssert.assertEquals(orders[index][2], media);
					  softAssert.assertEquals(orders[index][6], advertiser);
					  softAssert.assertEquals(orders[index][7], campaign);
				//	  softAssert.assertEquals(orders[index][8], price);
					  softAssert.assertEquals(orders[index][9], ppp);
					  softAssert.assertEquals(orders[index][10], surcharge);	
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
	      {Lib.weekDay,Lib.BuyNow,product1,Lib.ADV,Lib.CampaignADV,"","Nee","0,00"},
	      {Lib.weekDay,Lib.BuyNow,product2,Lib.ADV,Lib.CampaignADV,"","Nee","0,00"},
	      {Lib.weekDay,Lib.BuyNow,product3,Lib.ADV,Lib.CampaignADV,"","Nee","0,00"},
	    };
	  }

	  @Test(dependsOnMethods="checkOrderResults")
	  public void setPricesByPublisher() throws InterruptedException{
		    D.FAILURE_INDICATION = 3; //if test failed, logout and close browser
		    //Get orders from MB
			String menu = D.$Menu + D.$MenuExchange + ")]";
			Top.Login(Lib.UG, "Welkom01@1");
			Lib.ClickButton(By.xpath(menu));
			ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
			Lib.ClickButton(By.cssSelector(D.$p_orderoverview_privateSeat_tab));
			
//			ExchangeP.SetPrivatePrice("CD101V - Pagina 2", "30");			
			ExchangeP.SetPrivatePrice(product2, "50");
			ExchangeP.SetPrivatePrice(product3, "40");
			
			Top.Logout();
	  }
	  
	  @Test(dependsOnMethods="setPricesByPublisher")
	  public void campaignMultipleBuyGetOrderOverview() throws InterruptedException {	
			Top.Login(Lib.MB,"Welkom01@1");
		     			 			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   //Go to OrderOverview
			 orders = Lib.SortOrders(Lib.GetTableContent(D.$b_orderoverview_table, 6, 12));  //get all orders
			 Top.Logout(); 
			 Top.CloseBrowser();			 			     						 			 
	  }	
	  @Test(dataProvider="inputdata2", dependsOnMethods="campaignMultipleBuyGetOrderOverview")
	  public void checkFinalOrderResults(String pubDate, String media, String product,
			                        String advertiser, String campaign,
	                                String price, String ppp, String surcharge) {	
		     D.FAILURE_INDICATION = 0; //if test failed, do nothing
		     SoftAssert softAssert = new SoftAssert();
		     			 			 
			 int index =0;
			  for(int i = 0; i < 6; i++){
				  if(orders[i][5].equals(product) && orders[i][1].equals(pubDate)){
					  index = i;
					  softAssert.assertEquals(orders[index][2], media);
					  softAssert.assertEquals(orders[index][6], advertiser);
					  softAssert.assertEquals(orders[index][7], campaign);
					  softAssert.assertEquals(orders[index][8], price);
					  softAssert.assertEquals(orders[index][9], ppp);
					  softAssert.assertEquals(orders[index][10], surcharge);	
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
	      {Lib.weekDay,Lib.BuyNow,product2,Lib.ADV,Lib.CampaignADV,"2.160,00","Nee","0,00"},
	      {Lib.weekDay,Lib.BuyNow,product3,Lib.ADV,Lib.CampaignADV,"2.592,00","Nee","0,00"},
	    };
	  }


}
