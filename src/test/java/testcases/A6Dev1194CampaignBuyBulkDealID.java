package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import toplevel.D;
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"BuyBulkDealID"}, dependsOnGroups="CheckOrder", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class A6Dev1194CampaignBuyBulkDealID {
      String[][] orders; 	 

      @Test
	  public void campaignMultipleBuyGetOrderOverview() throws InterruptedException {	
	  		Top.StartBroswer();
			Top.Login(Helper.MB,"Welkom01@1");
		     			 
		     //add inventory to MyLots
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(Helper.CampaignADV2);			 
			 Exchange.EnterFromThroughDate(Helper.buyDay2);
//			 Exchange.EnterFromThroughDate(Helper.buyDay1);
			 Exchange.SelectFormat("CD101V");
			 Exchange.SelectMedia(Helper.BuyNow2);
			 Top.ClickButton(By.cssSelector(D.$be_execute));		
			 Exchange.SelectAInventory("CD101V - Pagina 2");
			 Exchange.SelectAInventory("CD101V - Pagina 3");
			 Exchange.SelectAInventory("CD101V - Pagina 4-5");
			 Top.ClickButton(By.cssSelector(D.$be_addAllSelectedToMyLots));
	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); //Go to My Lots
			 //do not show orders
			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
			 
			 //Select all and buy
			 Mylots.SelectALot("CD101V - Pagina 2");
			 Mylots.SelectALot("CD101V - Pagina 3");
			 Mylots.SelectALot("CD101V - Pagina 4-5");
			 
			 //Bulk DealID 
			 Mylots.SetBulkDealID("BulkDeal1");

			 Top.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   //Go to OrderOverview
			 orders = Helper.SortOrders(Helper.GetTableContent(D.$b_orderoverview_table, 6, 12));  //get all orders
			 Top.Logout(); 
			 Top.CloseBrowser();
			 			     						 			 
	  }	
	  
	  @Test(dataProvider="inputdata", dependsOnMethods="campaignMultipleBuyGetOrderOverview")
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
	      {Helper.buyDay2,Helper.BuyNow2,"CD101V - Pagina 2",Helper.ADV2,Helper.CampaignADV2,"","Nee","0,00"},
	      {Helper.buyDay2,Helper.BuyNow2,"CD101V - Pagina 3",Helper.ADV2,Helper.CampaignADV2,"","Nee","0,00"},
	      {Helper.buyDay2,Helper.BuyNow2,"CD101V - Pagina 4-5",Helper.ADV2,Helper.CampaignADV2,"","Nee","0,00"},
	    };
	  }

}
