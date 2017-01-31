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


@Test//(groups = {"D6"}, dependsOnGroups="D5", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class D6Dev1522BuyPlusPMB {
      String[][] orders; 
      String product1 = D.VoorpaginaHalfLying;
      String product2 = D.Pagina45HalfLying;
      String product3 = D.Pagina2FullPage;
      
      @BeforeClass
	  public void start() throws InterruptedException{
	        Lib.deleteAllMailsFromInbox();
			Top.StartBroswer();
	  }
      @Test(alwaysRun = true)
	  public void AddInventoriesToMyLots() throws InterruptedException {	
			Top.Login(Lib.MB,"Welkom01@1");
		     			 
		     //add inventory to MyLots
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(Lib.CampaignADV2);			 
			 Exchange.SelectPhase("Buy Now");
			 Exchange.EnterFromThroughDate(Lib.weekDay);
			 Exchange.SelectFormat("CD101V");
			 Exchange.SelectMedia(Lib.BuyNow2);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product3);
			 
			 Lib.ClickButton(By.xpath(D.$be_restore));
			 Exchange.SelectPhase("Buy Now");
			 Exchange.SelectFormat("CD102VL");	
			 Exchange.EnterFromThroughDate(Lib.weekDay);
			 Exchange.SelectMedia(Lib.BuyNow2);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.SelectAInventory(product2);
			 Exchange.SelectAInventory(product1);
			 Lib.ClickButton(By.cssSelector(D.$be_addAllSelectedToMyLots));
	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
		
      }
      @Test(dependsOnMethods="AddInventoriesToMyLots")
	  public void BuySinglePPP() throws InterruptedException {
   	         Mylots.SelectALot(product1); 
    	     Mylots.ExpandALot(product1, "grey");
			 Mylots.SelectPPP(product1, "PlusProposition item","1");
			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));	 
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);

			// Top.Logout(); 
	  }	
      
      @Test(dependsOnMethods="BuySinglePPP")
	  public void BuyMultiplePPPAndBuyAndGetOrderOverview() throws InterruptedException {
	        // Top.Login(Lib.MB,"Welkom01@1");
    	     Mylots.SelectALot(product3);
		     Mylots.ExpandALot(product3, "grey");
		     Mylots.SelectPPP(product3, "PlusProposition item","3");
		     
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     
		     Mylots.SelectALot(product2);
		     Mylots.ExpandALot(product2, "grey");
		     Mylots.SelectPPP(product2, "PlusProposition item","2");
		     Mylots.SelectALot(product3);
		 						
		     Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
		     Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
		     
		   	 Top.Logout(); 
      }
	

	  @Test(dependsOnMethods="BuyMultiplePPPAndBuyAndGetOrderOverview",alwaysRun = true)
	  public void setPPPricesByPublisher() throws InterruptedException{
		    D.FAILURE_INDICATION = 3; 

			String menu = D.$Menu + D.$MenuExchange + ")]";
			Top.Login(Lib.Res2, "Welkom01@1");
			Lib.ClickButton(By.xpath(menu));
			ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
			Lib.ClickButton(By.cssSelector(D.$p_orderoverview_pluspropersition_tab));
					
			ExchangeP.SetPPPPrice("0,00", "200");  
			ExchangeP.SetPPPPrice("0,00", "200");
			ExchangeP.SetPPPPrice("0,00", "200");
			
			Top.Logout();
	  }
 
	  @Test(dependsOnMethods="setPPPricesByPublisher",alwaysRun = true)
	  public void MBGetOrderOverview() throws InterruptedException {	
			Top.Login(Lib.MB,"Welkom01@1");
		     			 			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			 orders = Lib.SortOrders(Lib.GetTableContent(D.$b_orderoverview_table, 6, 20));  
			 Top.Logout();		 			     						 			 
	  }	
	  @Test(dataProvider="inputdata2", dependsOnMethods="MBGetOrderOverview", alwaysRun = true)
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
	      {Lib.weekDay,Lib.BuyNow2,"1/2 pagina volledig liggend","Voorpagina",Lib.ADV2,Lib.CampaignADV2,"2.062,50","Ja","2.262,50"},
	      {Lib.weekDay,Lib.BuyNow2,"1/2 pagina volledig liggend","Pagina 4-5",Lib.ADV2,Lib.CampaignADV2,"2.062,50","Ja","2.262,50"},
	      {Lib.weekDay,Lib.BuyNow2,"Volledige pagina","Pagina 2",Lib.ADV2,Lib.CampaignADV2,"4.125,00","Ja","4.325,00"},
	    };
	  }
	  @Test(dependsOnMethods="checkFinalOrderResults")
	  public static void checkEmail() throws InterruptedException{
		    Top.CloseBrowser();
		  
		    SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("D6Dev1522BuyPlusPMB", 13), "emailCorrect");				
			softAssert.assertAll(); 		  
	  }	 
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();  
	  }	

}
