package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import advertiser.PersonalOffer;
import publisher.ExchangeP;
import publisher.Media;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"C4"}, dependsOnGroups="C3", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class C4Dev1132OptionPushMB {		  
	  @BeforeClass
			public void start() throws InterruptedException{
		        Lib.deleteAllMailsFromInbox();
				Top.StartBroswer();
			}
	  
	  @Test(dataProvider="inputdata")
	  public void optionPush(String seller, String buyer, String ad, String media, String format, 
			                        String theDate,String price, String product) throws InterruptedException {			  
			 
     		 Top.Login(seller,"Welkom01@1");
	 		 ExchangeP.GotoInventory();
			 Exchange.SelectMedia(media);
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectFormat(format);			 
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 ExchangeP.SelectRowOverViewTable(product);
			 Lib.ClickButton(By.cssSelector(D.$p_option_push));
     		 Media.PushOption(buyer, ad, "1", ad + "1", price);
			 Top.Logout(); 
	
	  }	 
	  @DataProvider
	  public Object[][] inputdata() {
	    return new Object[][] { 
          {Lib.Res2,Lib.MB,Lib.ADV2,Lib.BuyNow2,"CD101V",Lib.buyDay3,"400", "CD101V - Voorpagina"},
	      {Lib.UG2,Lib.MB,Lib.ADV2,Lib.BuyNow2,"CD102VL",Lib.buyDay3,"300", "CD102VL - Pagina 3"},
	    };
	  }
	  @Test(dataProvider="inputdataX",dependsOnMethods="optionPush", alwaysRun = true)
	  public void optionMultiplePush(String seller, String buyer, String ad, String media, 
			                        String theDate,String price, String product1, String product2) throws InterruptedException {			  
			 
			 Top.Login(seller,"Welkom01@1");
	 		 ExchangeP.GotoInventory();
			 Exchange.SelectMedia(media);
			 Exchange.EnterFromThroughDate(theDate);		 
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 ExchangeP.SelectRowOverViewTable(product1);
			 ExchangeP.SelectRowOverViewTable(product2);
			 Lib.ClickButton(By.cssSelector(D.$p_option_push));
			 Media.PushOptionMultiple(buyer, ad, "1", ad + "1", price);
			 Top.Logout(); 
	
	  }	 
	  @DataProvider
	  public Object[][] inputdataX() {
	    return new Object[][] { 
	      {Lib.Res,Lib.MB,Lib.ADV,Lib.BuyNow,Lib.buyDay3,"200", "CD102VL - Cover 3","CD102VS - Cover 3"},
	    };
	  }				 

	  @Test(dataProvider="inputdata2",dependsOnMethods="optionMultiplePush", alwaysRun = true)
	  public void notificationNoCampaignDeselect(String prod11,String prod12,String prod21,String prod22) throws InterruptedException{		  
			 Top.Login(Lib.MB,"Welkom01@1");
			 Exchange.GoToPersonalOffer();
			 Exchange.SelectCampaign("");
			 PersonalOffer.SelectAGroupOfOffers("€ 200,00");
			 PersonalOffer.SelectAGroupOfOffers("€ 400,00");
			 PersonalOffer.SelectAGroupOfOffers("€ 300,00");
			 Lib.ClickButton(By.xpath(D.$img_deselect_all));

	  }
	  @DataProvider
	  public Object[][] inputdata2() {
	    return new Object[][] { 
          {D.Pagina3HalfLying,D.VoorpaginaFullPage,D.Cover3HalfLying,D.Cover3HalfStand},
	    };
	  }
	  
	  @Test(dataProvider="inputdata3",dependsOnMethods="notificationNoCampaignDeselect", alwaysRun = true)
	  public void deletAnOffer(String product) throws InterruptedException{
		  PersonalOffer.ExpandAGroupOfOffers("€ 200,00");
		  Exchange.SelectAInventory(product);
		  Lib.ClickButton(By.cssSelector(D.$be_offer_delete));
		  
		//  D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(D.$Doorgaan_Button)));
		//  Lib.ClickButton(By.xpath(D.$Doorgaan_Button));
		  

	  }
	  @DataProvider
	  public Object[][] inputdata3() {
	    return new Object[][] { 
          {D.Cover3HalfStand},
	    };
	  }
	  @Test(dataProvider="inputdata4",dependsOnMethods="deletAnOffer", alwaysRun = true)
	  public void campaignAddAll(String campaign,String price1, String price2) throws InterruptedException{
		  		  
		  Exchange.SelectCampaign(campaign);
		  PersonalOffer.SelectAGroupOfOffers(price1);
		  PersonalOffer.SelectAGroupOfOffers(price2);
		  Lib.ClickButton(By.xpath(D.$be_inventory_add_all));

		  D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(D.$OK_Button)));
		  Lib.CloseDialogBox();
		  
	  }	
	  @DataProvider
	  public Object[][] inputdata4() {
	    return new Object[][] { 
          {Lib.CampaignADV2,"€ 400,00","€ 300,00"},
	    };
	  }
	  @Test(dataProvider="inputdata5",dependsOnMethods="campaignAddAll", alwaysRun = true)
	  public void campaignAddOne(String camp,String price, String product) throws InterruptedException{
		  
		  Exchange.SelectCampaign(camp);
		  PersonalOffer.ExpandAGroupOfOffers(price);
		  Exchange.AddToMyLots(product);    
		  D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(D.$OK_Button)));
		  Lib.CloseDialogBox();
		  
	  }	
	  @DataProvider
	  public Object[][] inputdata5() {
	    return new Object[][] { 
          {Lib.CampaignADV,"€ 100,00",D.Cover3HalfLying},
	    };
	  }
	  @Test(dependsOnMethods="campaignAddOne", alwaysRun = true)
	  public void CheckStatusBuyTwoDeleteOne() throws InterruptedException{
		  Exchange.SelectCampaign("");
		  Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);
		  Lib.FindElement(By.xpath(D.$bm_lot_status_option));
		  List<WebElement> elements = D.driver.findElements(By.xpath(D.$bm_lot_status_option));
		  
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertEquals(elements.size(), 3);
		  //buy two
		  Mylots.SelectALot(D.VoorpaginaFullPage);
		  Mylots.SelectALot(D.Pagina3HalfLying);
		  Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
		  Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
		
		  //delete one
		  Mylots.SelectALot(D.Cover3HalfLying);
		  Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
		  Lib.CloseDialogBox();
		  softAssert.assertTrue(Mylots.CheckLotStatus(D.Cover3HalfLying, D.$bm_lot_status_option_cancelled));
		  Mylots.SelectALot(D.Cover3HalfLying);
		  Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
		  Lib.CloseDialogBox();
	
		  Top.Logout();
		  
		  D.FAILURE_INDICATION = 0;
		  softAssert.assertAll();
	  }	
	  @Test(dependsOnMethods="CheckStatusBuyTwoDeleteOne")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			Thread.sleep(D.waitTime * 4);
			softAssert.assertEquals(Lib.checkEmails("C4Dev1132OptionPushMB", 14), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }
	  @AfterClass
		public void stop() throws InterruptedException { 
		  Top.CloseBrowser();
		} 

		 
}
