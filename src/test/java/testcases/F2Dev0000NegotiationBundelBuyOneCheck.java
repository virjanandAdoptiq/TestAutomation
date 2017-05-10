package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;

//Create New Data before run this
@Test//(groups = {"F2"}, dependsOnGroups="F1", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class F2Dev0000NegotiationBundelBuyOneCheck {
	  String product1 = D.Cover3FullPage;   
	  String product2 = D.Cover2FullPage;   
	  
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
		     Lib.deleteAllMailsFromInbox();
			 Top.StartBroswer();
			 Top.Login(Lib.MB,"Welkom01@1");				
	  }
	  @Test(alwaysRun = true)
	  public void AddToMyLots() throws InterruptedException {			  
		     Exchange.GotoBuyerEchangePageTileView();
			 Exchange.SelectCampaign(Lib.CampaignADV2);
			 Exchange.ClickAMediaTile(Lib.BuyNow);
			 Exchange.EnterFromThroughDate(Lib.buyDay1);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product1);
			 Exchange.AddToMyLots(product2);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
	
			 Mylots.SetFilterOrderShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeBundleNegotiate() throws InterruptedException {		  
    		 Mylots.SelectALot(product1);
			 Mylots.SelectALot(product2);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Mylots.Negotiation("3333,99", Lib.lmDay1,true);
	  }	
	  @Test(dependsOnMethods="MakeBundleNegotiate")
	  public void BuyOne() throws InterruptedException {
		     Mylots.ExpandAGroup("Onderhandeling");
			 Mylots.SelectALotInsideGroup(product1);
	
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);			 				 
	  }	
	   
	  @Test(dependsOnMethods="BuyOne")
	  public void CheckLotStatus() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();		     		 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product1, D.$bm_lot_status_order));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product2, D.$bm_lot_status_negotiationCancelled));						 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="BuyOne")
	  public void DeleteTheRest() throws InterruptedException {
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     Mylots.SelectALot(product2);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();			 	 
	  }	
	  
	  @Test(dependsOnMethods="DeleteTheRest")
	  public void CheckNegotiationOverview() throws InterruptedException {
		    SoftAssert softAssert = new SoftAssert();		     
		     Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);
		     Lib.ClickButton(By.xpath(D.$b_negotiation_overview_tab));
		     String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_negotiationoverview_table), 5, 12));

		     for(int i =1;i<5;i++){
			    softAssert.assertEquals(orders[i][10].toString().trim(), "Geannuleerd");
		     }
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckNegotiationOverview")
	  public void CheckOrderPriceIsBuyNowPrice() throws InterruptedException {
		    SoftAssert softAssert = new SoftAssert();		     
		    Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 2, 12));
			 
		    int index = -1;
		     for(int i =1;i<2;i++){
		    	 if(orders[i][2].equals(Lib.BuyNow)){
			          softAssert.assertEquals(orders[i][9].toString().trim(), "1.875,00");
			          index = i;
			          break;
		    	 }
		     }
		     softAssert.assertNotEquals(index, -1);
		     
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckOrderPriceIsBuyNowPrice")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("F2Dev0000NegotiationBundelBuyOneCheck", 10), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 
	 	@AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
