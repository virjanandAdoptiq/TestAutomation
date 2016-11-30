package testcases;

import org.apache.commons.lang3.ArrayUtils;
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


@Test//(groups = {"D1"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class D1Dev1101BuyCheckOrdersMB {
	  String[][] orders; 
	  String[][] ordersUG; 
	  String[][] ordersRes; 
	  String[][] ordersRes2;
	  String[][] ordersPub;
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
			Top.StartBroswer();
			Top.Login(Lib.MB,"Welkom01@1");
			D.FAILURE_INDICATION = 0; 
	  }
	  @Test(dataProvider="inputdata", alwaysRun = true)
	  public void SelectCmpBuyOne(String campaign, String media, String format, 
			                        String theDate, String product,
                                    String finalPriceE) throws InterruptedException {	
		  
		     SoftAssert softAssert = new SoftAssert();
		     
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(campaign);			 
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectFormat(format);
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));		
			 Exchange.AddToMyLots(product);

			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();

			 String lotBuyNowPrice =  Mylots.GetLotInfo(D.$bm_lot_buyNowPrice);
			 softAssert.assertEquals(lotBuyNowPrice, finalPriceE);
			 
			 Mylots.SelectALot(media);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
			 
			 Exchange.SelectCampaign("");
			  			 		 
			 softAssert.assertAll();
						     						 			 
	  }	
	  @DataProvider
	  public Object[][] inputdata() {
	    return new Object[][] { 
	      {Lib.CampaignADV,Lib.BuyNow,"CD101V",Lib.buyDay1,"CD101V - Cover 2","1.575,00"},
	    };
	  }

	  @Test(dataProvider="inputdata2", dependsOnMethods="SelectCmpBuyOne", alwaysRun = true)
	  public void SelectCmpBuyTwoTogether(String campaign, String media, String format, String format2,
			                        String theDate, String product, String product2
                                   ) throws InterruptedException {	
		     
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(campaign);
			 Exchange.SelectMedia(media);	
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	//sometimes, it doesn't work
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectFormat(format);
			 Exchange.SelectFormat(format2);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));		
			 Exchange.AddToMyLots(product);
			 Exchange.AddToMyLots(product2);

			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();

			 Mylots.SelectALot(product);
			 Mylots.SelectALot(product2);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
			 
			 Exchange.SelectCampaign("");
						     						 			 
	  }
	  @DataProvider
	  public Object[][] inputdata2() {
	    return new Object[][] { 
	      {Lib.CampaignADV2,Lib.BuyNow2,"CD101V","CD102VS", Lib.buyDay1,"CD101V - Pagina 2","CD102VS - Pagina 3"},
	    };
	  }
	  
	 // @Test(dependsOnMethods={"SelectCmpBuyTwoTogether", "SelectCmpBuyOne"})
	  @Test(dependsOnMethods="SelectCmpBuyTwoTogether")
	  public void fatchOrders() throws InterruptedException{
		    D.FAILURE_INDICATION = 3; 
	
			Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			orders = Lib.SortOrders(Lib.GetTableContent(D.$b_orderoverview_table, 3, 12));
			Top.Logout(); 

			Top.Login(Lib.UG, "Welkom01@1");
			String menu = D.$Menu + D.$MenuExchange + ")]";
			Lib.ClickButton(By.xpath(menu));
			ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
			ordersUG = Lib.GetTableContent(D.$p_orderoverview_table, 1, 10);
			Top.Logout(); 

			Top.Login(Lib.Res2, "Welkom01@1");
			Lib.ClickButton(By.xpath(menu));
			ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
			ordersRes2 = Lib.GetTableContent(D.$p_orderoverview_table, 2, 10);
			Top.Logout();
				
			ordersPub = Lib.SortOrders(ArrayUtils.addAll(ArrayUtils.addAll(ordersUG, ordersRes),ordersRes2));
			
			D.FAILURE_INDICATION = 0; 
	  }
	  @Test(dependsOnMethods="fatchOrders")
	  public void compareOrdersPubSideAndMBSide(){
		  SoftAssert softAssert = new SoftAssert();	
		  for(int i = 0; i < 3; i++){
			  softAssert.assertEquals(orders[i][0],ordersPub[i][0]);   //the order number 
			  softAssert.assertEquals(orders[i][1], ordersPub[i][2]);  
			  softAssert.assertEquals(orders[i][2], ordersPub[i][3]);
			  softAssert.assertEquals(orders[i][5], ordersPub[i][6]);
			  softAssert.assertEquals(ordersPub[i][7],Lib.MB);
			  softAssert.assertEquals(orders[i][6], ordersPub[i][8]);
			  softAssert.assertEquals(orders[i][8], ordersPub[i][9]);
		  }
		  softAssert.assertAll();
	  }
	  
	  @Test(dataProvider="orderlist",dependsOnMethods="compareOrdersPubSideAndMBSide", alwaysRun = true)
	  public void checkOrderValue(String pubDate, String media, String product,String advertiser, String campaign,
			             String price, String ppp, String surcharge) throws InterruptedException {	
		  SoftAssert softAssert = new SoftAssert();	
		  int index =0;
		  for(int i = 0; i < 4; i++){
			  if(orders[i][5].equals(product)){
				  index = i;
				  softAssert.assertEquals(orders[index][1], pubDate);
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
		  softAssert.assertNotEquals(index, 4);
		  
		  softAssert.assertAll();
	  }		   
	  @DataProvider
	  public Object[][] orderlist() {
		    return new Object[][] { 
		      {Lib.buyDay1,Lib.BuyNow,"CD101V - Cover 2",Lib.ADV,Lib.CampaignADV,"1.575,00","Nee","0,00"},
		      {Lib.buyDay1,Lib.BuyNow2,"CD102VS - Pagina 3",Lib.ADV2,Lib.CampaignADV2,"2.062,50","Nee","0,00"},
		      {Lib.buyDay1,Lib.BuyNow2,"CD101V - Pagina 2",Lib.ADV2,Lib.CampaignADV2,"4.125,00","Nee","0,00"},
		    };
	  }
	  @AfterClass
	  public void stop() throws InterruptedException{
			  Top.CloseBrowser();
	  }	 
}
