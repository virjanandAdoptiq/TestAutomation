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


@Test//(groups = {"D1"}, dependsOnGroups="C4", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class D1Dev1101BuyCheckOrdersMB {
	  String[][] orders; 
	  String[][] ordersUG; 
	  String[][] ordersRes; 
	  String[][] ordersRes2;
	  String[][] ordersPub;
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
	        Lib.deleteAllMailsFromInbox();
			Top.StartBroswer();
			Top.Login(Lib.MB,"Welkom01@1");
			D.FAILURE_INDICATION = 0; 
	  }

	  @Test(dataProvider="inputdata", alwaysRun = true)
	  public void SelectCmpBuyOne(String campaign, String media, String format, 
			                        String theDate, String product,
                                    String finalPriceE) throws InterruptedException {	
		  
		     SoftAssert softAssert = new SoftAssert();
		     
			 Exchange.GotoBuyerEchangePageTileView();
			 Exchange.SelectCampaign(campaign);			 
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectFormat(format);
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.ClickAMediaTile(media);
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
	      {Lib.CampaignADV,Lib.BuyNow,"CD101V",Lib.buyDay3,D.Cover2FullPage,"1.575,00"},
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
	      {Lib.CampaignADV2,Lib.BuyNow2,"CD101V","CD102VS", Lib.buyDay3,D.Pagina2FullPage,D.Pagina3HalfStand},
	    };
	  }
 
	  @Test(dependsOnMethods="SelectCmpBuyTwoTogether")
	  public void fatchOrders() throws InterruptedException{
    
			Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 3, 12));
	
			Top.Logout(); 

			Top.Login(Lib.UG, "Welkom01@1");
			ExchangeP.GotoOderOverview();
			ordersUG = Lib.GetTableContent(By.xpath(D.$p_orderoverview_table), 1, 10);
			Top.Logout(); 

			Top.Login(Lib.Res2, "Welkom01@1");
			ExchangeP.GotoOderOverview();
			ordersRes2 = Lib.GetTableContent(By.xpath(D.$p_orderoverview_table), 2, 10);
			Top.Logout();
				
			ordersPub = Lib.SortOrders(ArrayUtils.addAll(ArrayUtils.addAll(ordersUG, ordersRes),ordersRes2));
			
	  }
  
	  @Test(dependsOnMethods="fatchOrders")
	  public void compareOrdersPubSideAndMBSide(){
		  SoftAssert softAssert = new SoftAssert();	
		  for(int i = 0; i < 3; i++){
			  softAssert.assertEquals(orders[i][0].toString(),ordersPub[i][0].toString());   //the order number 
			  softAssert.assertEquals(orders[i][1], ordersPub[i][2]);    //pub date
			  softAssert.assertEquals(orders[i][2], ordersPub[i][3]);    //medium

			  softAssert.assertEquals(ordersPub[i][7],Lib.MB);
			  softAssert.assertEquals(orders[i][9], ordersPub[i][9]);     //price

		  }
		  softAssert.assertAll();
	  }

	  @Test(dataProvider="orderlist",dependsOnMethods="compareOrdersPubSideAndMBSide", alwaysRun = true)
	  public void checkOrderValue(String pubDate, String media, String format, String page,String advertiser, String campaign,
			             String price, String ppp, String surcharge) throws InterruptedException {	
		  SoftAssert softAssert = new SoftAssert();	
		  int index =0;
		  for(int i = 0; i < 3; i++){
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
		  softAssert.assertNotEquals(index, 4);
		  
		  softAssert.assertAll();
	  }		   
	  @DataProvider
	  public Object[][] orderlist() {
		    return new Object[][] { 
		      {Lib.buyDay1,Lib.BuyNow,"Volledige pagina","Cover 2",Lib.ADV,Lib.CampaignADV,"1.575,00","Nee","0,00"},
		      {Lib.buyDay1,Lib.BuyNow2,"1/2 pagina volledig staand","Pagina 3",Lib.ADV2,Lib.CampaignADV2,"2.062,50","Nee","0,00"},
		      {Lib.buyDay1,Lib.BuyNow2,"Volledige pagina","Pagina 2",Lib.ADV2,Lib.CampaignADV2,"4.125,00","Nee","0,00"},
		    };
	  }
	  @Test(dependsOnMethods="checkOrderValue")
	  public static void checkEmail() throws InterruptedException{		  
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("D1Dev1101BuyCheckOrdersMB", 6), "emailCorrect");				
			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			  
	  }
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();  
	  }	 
}
