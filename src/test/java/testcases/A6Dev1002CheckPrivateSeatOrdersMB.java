package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Mylots;
import toplevel.D;
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;

@Test(groups = {"CheckPrivateOrder"}, dependsOnGroups="SetPrivateDealPrice", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class A6Dev1002CheckPrivateSeatOrdersMB {
      String[][] orders; 	 

      @Test
	  public void campaignMultipleBuyGetOrderOverview() throws InterruptedException {	
	  		Top.StartBroswer();
			Top.Login(Helper.MB,"Welkom01@1");
		     			 			 
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
					  softAssert.assertEquals(orders[index][8], price);
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
//	      {Helper.buyDay2,Helper.BuyNow2,"CD101V - Pagina 2",Helper.ADV2,Helper.CampaignADV2,"3.500,00","Nee","0,00"},
	      {Helper.buyDay2,Helper.BuyNow2,"CD101V - Pagina 3",Helper.ADV2,Helper.CampaignADV2,"3.000,00","Nee","0,00"},
	      {Helper.buyDay2,Helper.BuyNow2,"CD101V - Pagina 4-5",Helper.ADV2,Helper.CampaignADV2,"2.500,00","Nee","0,00"},
	    };
	  }

}
