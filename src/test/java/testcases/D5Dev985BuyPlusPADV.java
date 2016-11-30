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
      String product = "CD102VS - Pagina 4-5";
      
      @BeforeClass
	  public void start() throws InterruptedException{
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

			String menu = D.$Menu + D.$MenuExchange + ")]";
			Top.Login(Lib.Res2, "Welkom01@1");
			Lib.ClickButton(By.xpath(menu));
			ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
			Lib.ClickButton(By.cssSelector(D.$p_orderoverview_pluspropersition_tab));
					
			ExchangeP.SetPPPPrice("0,00", "100");  //should use product, but it doesn't work
			
			Top.Logout();
	  }
  
	  @Test(dependsOnMethods="setPPPricesByPublisher")
	  public void GetOrderOverview() throws InterruptedException {	
			Top.Login(Lib.ADV,"Welkom01@1");
		     			 			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   //Go to OrderOverview
			 orders = Lib.SortOrders(Lib.GetTableContent(D.$b_orderoverview_table, 6, 20));  //get all orders
			 Top.Logout();		 			     						 			 
	  }	
	  @Test(dataProvider="inputdata2", dependsOnMethods="GetOrderOverview")
	  public void checkFinalOrderResults(String pubDate, String media, String product,
			                        String advertiser, String campaign,
	                                String price, String ppp, String surcharge) {	
		     D.FAILURE_INDICATION = 0; 
		     SoftAssert softAssert = new SoftAssert();
		     			 			 
			 int index =0;
			  for(int i = 0; i < 12; i++){
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
			  softAssert.assertNotEquals(index, 12);
			  			  
			  softAssert.assertAll();			  
	  }
	  @DataProvider
	  public Object[][] inputdata2() {
	    return new Object[][] { 
	      {Lib.weekDay,Lib.BuyNow2,product,Lib.ADV," ","2.062,50","Ja","2.162,50"},
	    };
	  }
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();
	  }

}
