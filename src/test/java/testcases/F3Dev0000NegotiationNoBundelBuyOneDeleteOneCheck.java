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
@Test//(groups = {"F3"}, dependsOnGroups="F2", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class F3Dev0000NegotiationNoBundelBuyOneDeleteOneCheck { 
	  String product = D.Cover2HalfStand;   
	  	  
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
			 Exchange.AddToMyLots(product);
			 Exchange.AddToMyLots(product);
			 Lib.ClickButton(By.xpath(D.$be_inventory_duplication_add_one));
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 	
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeNoBundleNegotiate() throws InterruptedException {		  
    		 Mylots.SelectAllLot(product);

			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Mylots.Negotiation("6000", Lib.lmDay1, false);
	  }	
	  @Test(dependsOnMethods="MakeNoBundleNegotiate")
	  public void BuyOne() throws InterruptedException {
		     Mylots.ExpandAGroup("Onderhandeling");
			 Mylots.SelectALotInsideGroup(product);
	
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);			 				 
	  }	
	  @Test(dependsOnMethods="BuyOne")
	  public void DeleteOne() throws InterruptedException {
		     Mylots.SelectAGroup("Onderhandeling");	
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();	
			 
			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
			 
		     SoftAssert softAssert = new SoftAssert();
		     Mylots.CheckLotStatus(product, D.$bm_lot_status_negotiationCancelled);
		     Mylots.SelectALot(product);
		     Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();	
		     			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();
	  } 

	  @Test(dependsOnMethods="DeleteOne")
	  public void CheckNegotiationOverview() throws InterruptedException {
		    SoftAssert softAssert = new SoftAssert();		     
		     Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);
		     Lib.ClickButton(By.xpath(D.$b_negotiation_overview_tab));
		     String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_negotiationoverview_table), 7, 12));

		     for(int i =1;i<7;i++){
			    softAssert.assertEquals(orders[i][10].toString().trim(), "Geannuleerd");
		     }
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckNegotiationOverview")
	  public void CheckOrderPriceIsBuyNowPrice() throws InterruptedException {
		    SoftAssert softAssert = new SoftAssert();		     
		    Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 3, 12));
			 
		    int index = -1;
		     for(int i =1;i<3;i++){
		    	 if(orders[i][6].equals("Cover 2")){
			          softAssert.assertEquals(orders[i][9].toString().trim(), "937,50");
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
			softAssert.assertEquals(Lib.checkEmails("F3Dev0000NegotiationNoBundelBuyOneDeleteOneCheck", 10), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 
	 	@AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
