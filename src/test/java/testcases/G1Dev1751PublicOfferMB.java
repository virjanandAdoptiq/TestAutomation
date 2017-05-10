package testcases;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import publisher.ExchangeP;
import publisher.Media;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"B9"}, dependsOnGroups="B8", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class G1Dev1751PublicOfferMB {	
	  String exclusiveDeepLink;
	  String product1 = D.VoorpaginaFullPage;
	  String product2 = D.Pagina2HalfLying;
	  String product3 = D.Pagina45HalfLying;
	  String product4 = D.Pagina3HalfLying;
	  String productP1 = "CD101V - Voorpagina";
	  String productP2 = "CD102VL - Pagina 2";
	  String productP3 = "CD102VL - Pagina 4-5";
	  String productP4 = "CD102VL - Pagina 3";
	  String[][] orders; 
	  @BeforeClass
	  public void start() throws InterruptedException{
	        Lib.deleteAllMailsFromInbox();
			Top.StartBroswer();
	  }
	  
	  @Test
	  public void createOffers() throws InterruptedException, UnsupportedFlavorException, IOException {			  
			 
			 Top.Login(Lib.UG2,"Welkom01@1");
			 
	 		 ExchangeP.GotoInventory();
			 Exchange.SelectMedia(Lib.BuyNow2);
			 Exchange.EnterFromThroughDate(Lib.buyDay3);		 
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 ExchangeP.SelectRowOverViewTable(productP1);
			 ExchangeP.SelectRowOverViewTable(productP2);
			 ExchangeP.SelectRowOverViewTable(productP3);
			 ExchangeP.SelectRowOverViewTable(productP4);
			 Lib.ClickButton(By.cssSelector(D.$p_offer_make_button));
			 Media.CreatePublicOffer(Lib.Today, Lib.buyDay3);
			 
			 exclusiveDeepLink = Media.GetDeepLinkFromOfferOverview("Inventory");
			 System.out.println(exclusiveDeepLink);
			 Top.Logout(); 
			 Top.CloseBrowser();
	
	  }	 			 

	  @Test(dependsOnMethods="createOffers")		  
	  public void useDeeplinkWelkom() throws InterruptedException{
			  Top.GetBroswer(exclusiveDeepLink);
			  D.wait = new WebDriverWait(D.driver, D.maxWaitTime);
			  D.longWait = new WebDriverWait(D.driver,D.longWaitTime);
			
			  System.out.println("The following have completely been changed.");	
			  Lib.ClickButton(By.xpath(D.$po_welcome_show_offer_button));			  			  
		  }	
	  @Test(dependsOnMethods="useDeeplinkWelkom")		  
	  public void step1SelectOffer() throws InterruptedException{
		      Lib.ClickButton(By.xpath(D.$po_select_all));
		      Lib.ClickButton(By.cssSelector(D.$po_go_further));
		  }	
	  @Test(dependsOnMethods="step1SelectOffer")		  
	  public void step2LoginAsMB() throws InterruptedException{
		  Lib.InputData(Lib.MB, By.cssSelector(D.$po_login_user));
		  Lib.InputData("Welkom01@1", By.cssSelector(D.$po_login_password));
		  Lib.ClickButton(By.cssSelector(D.$po_login_signIn));
		  }	
	  @Test(dependsOnMethods="step2LoginAsMB")		  
	  public void step3SelectCampaignConfirm() throws InterruptedException{
		  Exchange.SelectCampaign(Lib.CampaignADV);
		  Lib.ClickButton(By.cssSelector(D.$po_confirm_buy_button));
	  }
	  @Test(dependsOnMethods="step3SelectCampaignConfirm")		  
	  public void step4BuyGetOverview() throws InterruptedException{
          Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
		  
		  Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
		  orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 16, 12));
		  }	
	  @Test(dataProvider="orderlist",dependsOnMethods="step4BuyGetOverview", alwaysRun = true)
	  public void checkOrderValue(String pubDate, String media, String format, String page,String advertiser, String campaign,
			             String price, String ppp, String surcharge) throws InterruptedException {	
		  SoftAssert softAssert = new SoftAssert();	
		  int index =0;
		  for(int i = 0; i < 16; i++){
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
		  softAssert.assertNotEquals(index, 16);
		  
		  softAssert.assertAll();
	  }		   
	  @DataProvider
	  public Object[][] orderlist() {
		    return new Object[][] { 
		      {Lib.buyDay3,Lib.BuyNow2,"Volledige pagina","Voorpagina",Lib.ADV,Lib.CampaignADV,"11.000,00","Nee","0,00"},
		      {Lib.buyDay3,Lib.BuyNow2,"1/2 pagina volledig liggend","Pagina 4-5",Lib.ADV,Lib.CampaignADV,"5.500,00","Nee","0,00"},
		      {Lib.buyDay3,Lib.BuyNow2,"1/2 pagina volledig liggend","Pagina 3",Lib.ADV,Lib.CampaignADV,"5.500,00","Nee","0,00"},
		      {Lib.buyDay3,Lib.BuyNow2,"1/2 pagina volledig liggend","Pagina 2",Lib.ADV,Lib.CampaignADV,"5.500,00","Nee","0,00"},
		    };
	  }
	  
	  @Test(dependsOnMethods="checkOrderValue")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("G1Dev1751PublicOfferMB", 3), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	
	  @AfterClass
		public void stop() throws InterruptedException {	
			Top.CloseBrowser();
		} 

		 
}
