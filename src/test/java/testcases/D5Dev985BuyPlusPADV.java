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


@Test//(groups = {"D5"}, dependsOnGroups="D4", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class D5Dev985BuyPlusPADV {
      String[][] orders; 
      String product = D.Pagina45HalfStand;
      
      @BeforeClass
	  public void start() throws InterruptedException{
	        Lib.deleteAllMailsFromInbox();
			Top.StartBroswer();
	  }
      @Test(alwaysRun = true)
	  public void AddInventoriesToMyLots() throws InterruptedException {	
			Top.Login(Lib.ADV,"Welkom01@1");
		     			 
		     //add inventory to MyLots
			 Exchange.GotoBuyerEchangePage();			 
			 Exchange.SelectPhase("Buy Now");
			 Exchange.SelectMedia(Lib.BuyNow2);
			 Exchange.SelectFormat("CD102VS");
			 Exchange.EnterFromThroughDate(Lib.weekDay);
			 Exchange.SelectPlusProposition("Ja");
			 Lib.ClickButton(By.cssSelector(D.$be_execute));		
			 Exchange.SelectAInventory(product);
			 Lib.ClickButton(By.cssSelector(D.$be_addAllSelectedToMyLots));
	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
		
      }
      @Test(dependsOnMethods="AddInventoriesToMyLots")
	  public void BuySinglePPP() throws InterruptedException {
   	         Mylots.SelectALot(product); 
    	     Mylots.ExpandALot(product, "grey");
			 Mylots.SelectPPP(product, "PlusProposition item","1");
			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));	 
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);

			 Top.Logout();
	  }	

	  @Test(dependsOnMethods="BuySinglePPP")
	  public void setPPPricesByPublisher() throws InterruptedException{
		    D.FAILURE_INDICATION = 3; 

			Top.Login(Lib.Res2, "Welkom01@1");
				
			ExchangeP.GoToExchangePlatform();
			Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));
			ExchangeP.SetPPPPrice("100");  
			
			Top.Logout();
	  }
  
	  @Test(dependsOnMethods="setPPPricesByPublisher")
	  public void GetOrderOverview() throws InterruptedException {	
			Top.Login(Lib.ADV,"Welkom01@1");
		     			 			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   //Go to OrderOverview
			 orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 12, 20));  //get all orders
			 Top.Logout();		 			     						 			 
	  }	
	  @Test(dataProvider="inputdata2", dependsOnMethods="GetOrderOverview")
	  public void checkFinalOrderResults(String pubDate, String media, String format, String page,
			                        String advertiser, String campaign,
	                                String price, String ppp, String surcharge) {	
		     D.FAILURE_INDICATION = 0; 
		     SoftAssert softAssert = new SoftAssert();
		     			 			 
			 int index =0;
			  for(int i = 0; i < 12; i++){
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
			  softAssert.assertNotEquals(index, 12);
			  			  
			  softAssert.assertAll();			  
	  }
	  @DataProvider
	  public Object[][] inputdata2() {
	    return new Object[][] { 
	      {Lib.weekDay,Lib.BuyNow2,"1/2 pagina volledig staand","Pagina 4-5",Lib.ADV," ","2.062,50","Ja","2.162,50"},
	    };
	  }
	  @Test(dependsOnMethods="checkFinalOrderResults")
	  public static void checkEmail() throws InterruptedException{
		    
		    SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("D5Dev985BuyPlusPADV", 6), "emailCorrect");				
			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			  
	  }	 
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();  
	  }	

}
