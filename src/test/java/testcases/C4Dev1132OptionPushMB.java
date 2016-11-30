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
import publisher.ExchangeP;
import publisher.Media;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"C4"}, dependsOnGroups="C3", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class C4Dev1132OptionPushMB {		  
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}
	  
	  @Test(dataProvider="inputdata")
	  public void optionPush(String seller, String buyer, String ad, String media, String format, 
			                        String theDate,String price, String product) throws InterruptedException {			  
			 
			 Top.Login(seller,"Welkom01@1");
			 String menu = D.$Menu + D.$MenuMedia + ")]";
			 Lib.ClickButton(By.xpath(menu));
	 		 ExchangeP.SelectLeftMenu("Inventory");
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
          {Lib.Res2,Lib.MB,Lib.ADV2,Lib.BuyNow2,"CD101V",Lib.buyDay1,"400", "CD101V - Voorpagina"},
	      {Lib.UG2,Lib.MB,Lib.ADV2,Lib.BuyNow2,"CD102VL",Lib.buyDay1,"300", "CD102VL - Pagina 3"},
	      {Lib.Res,Lib.MB,Lib.ADV,Lib.BuyNow,"CD102VL",Lib.buyDay1,"200", "CD102VL - Cover 3"},
	      {Lib.UG,Lib.MB,Lib.ADV,Lib.BuyNow,"CD102VS",Lib.buyDay1,"100","CD102VS - Cover 3"},
	    };
	  }
					 
//MB reacts to the offer
	  @Test(dataProvider="inputdata2",dependsOnMethods="optionPush", alwaysRun = true)
	  public void notificationNoCampaignDeselect(String prod11,String prod12,String prod21,String prod22) throws InterruptedException{		  
			 Top.Login(Lib.MB,"Welkom01@1");
			 Exchange.ClickOfferNotification();
			 Exchange.SelectCampaign("");
			 Exchange.SelectAInventory(prod11);
			 Exchange.SelectAInventory(prod12);
			 Exchange.SelectAInventory(prod21);
			 Exchange.SelectAInventory(prod22);
			 Lib.ClickButton(By.cssSelector(D.$be_inventory_deselect_all));

	  }
	  @DataProvider
	  public Object[][] inputdata2() {
	    return new Object[][] { 
          {"CD102VL - Pagina 3","CD101V - Voorpagina","CD102VL - Cover 3","CD102VS - Cover 3"},
	    };
	  }
	  
	  @Test(dataProvider="inputdata3",dependsOnMethods="notificationNoCampaignDeselect", alwaysRun = true)
	  public void deletAnOffer(String product) throws InterruptedException{
		  Exchange.SelectAInventory(product);
		  //Top.DoubleClicky(By.cssSelector(D.$be_offer_delete));
		  Lib.ClickButton(By.cssSelector(D.$be_offer_delete));
		  D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(D.$OK_Button)));
		  Lib.CloseDialogBox();

	  }
	  @DataProvider
	  public Object[][] inputdata3() {
	    return new Object[][] { 
          {"CD102VS - Cover 3"},
	    };
	  }
	  @Test(dataProvider="inputdata4",dependsOnMethods="deletAnOffer", alwaysRun = true)
	  public void campaignAddAll(String campaign,String prod1, String prod2) throws InterruptedException{
		  		  
		  Exchange.SelectCampaign(campaign);
		  Exchange.SelectAInventory(prod1);
		  Exchange.SelectAInventory(prod2);
		  Lib.ClickButton(By.cssSelector(D.$be_inventory_add_all));

		  D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(D.$OK_Button)));
		  Lib.CloseDialogBox();
		  
	  }	
	  @DataProvider
	  public Object[][] inputdata4() {
	    return new Object[][] { 
          {Lib.CampaignADV2,"CD101V - Voorpagina","CD102VL - Pagina 3"},
	    };
	  }
	  @Test(dataProvider="inputdata5",dependsOnMethods="campaignAddAll", alwaysRun = true)
	  public void campaignAddOne(String camp,String product) throws InterruptedException{
		  
		  Exchange.SelectCampaign(camp);
		  Exchange.AddToMyLots(product);    
		  D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(D.$OK_Button)));
		  Lib.CloseDialogBox();
		  
	  }	
	  @DataProvider
	  public Object[][] inputdata5() {
	    return new Object[][] { 
          {Lib.CampaignADV,"CD102VL - Cover 3"},
	    };
	  }
	  @Test(dependsOnMethods="campaignAddOne", alwaysRun = true)
	  public void CheckStatusBuyTwoDeleteOne() throws InterruptedException{
		  Exchange.SelectCampaign("");
		  Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);
		  List<WebElement> elements = D.driver.findElements(By.xpath(D.$bm_lot_status_option));
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertEquals(elements.size(), 3);
		  //buy two
		  Mylots.SelectALot("CD101V - Voorpagina");
		  Mylots.SelectALot("CD102VL - Pagina 3");
		  Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
		  Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
		
		  //delete one
		  Mylots.SelectALot("CD102VL - Cover 3");
		  Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
		  Lib.CloseDialogBox();
	
		  Top.Logout();
		  D.FAILURE_INDICATION = 0;
		  softAssert.assertAll();
	  }	

	  @AfterClass
		public void stop() throws InterruptedException { 
			Top.CloseBrowser();
		} 

		 
}
