package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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

//Create New Data before run this
@Test//(groups = {"F4"}, dependsOnGroups="F3", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class F4Dev0000NegotiationSingleAccepted { 
	  String product = D.Cover2HalfStand;   
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
		     Lib.deleteAllMailsFromInbox();
			 Top.StartBroswer();				
	  }
	  @Test(alwaysRun = true)
	  public void AddToMyLots() throws InterruptedException {
			 Top.Login(Lib.MB,"Welkom01@1");
			 Exchange.GotoBuyerEchangePageTileView();
			 Exchange.SelectCampaign(Lib.CampaignADV2);
			 Exchange.ClickAMediaTile(Lib.BuyNow);
			 Exchange.EnterFromThroughDate(Lib.buyDay1);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product);
			 Lib.ClickButton(By.xpath(D.$be_inventory_duplication_add_one));
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 
		     Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeNegotiate() throws InterruptedException {		  
    		 Mylots.SelectALot(product);

			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Mylots.Negotiation("1111", Lib.lmDay1, false);
			 Top.Logout();
	  }	
	  
	  @Test(dependsOnMethods="MakeNegotiate")
	  public void UGAcceptIt() throws InterruptedException {	
		     Top.Login(Lib.Res, "Welkom01@1");
			 ExchangeP.GoToExchangePlatform();
//			 ExchangeP.SelectANegotiationy("1.111,00");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_accept_button));
			 Lib.CloseDialogBox();
			 
			 Top.Logout();
	  }	

	  
	  @Test(dependsOnMethods="UGAcceptIt")
	  public void CheckNegotiationOverview() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();		
		     Top.Login(Lib.MB,"Welkom01@1");
		     Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);
		     Lib.ClickButton(By.xpath(D.$b_negotiation_overview_tab));
		     String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_negotiationoverview_table), 8, 12));
             boolean result = false;
		     for(int i =1;i<8;i++){
		    	 if(orders[i][9].trim().equals("1.111,00") && orders[i][10].trim().equals("Goedgekeurd")){
		    		 result = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(result, "price=1.111,00 and status=Goedgekeurd is not in negotiation overview");
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	
	  @Test(dependsOnMethods="CheckNegotiationOverview")  
	  public void BuyIt() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();		
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_negotiate_accepted));
		     Mylots.SelectALot(product);
		     Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
		     
		     D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="BuyIt")
	  public void CheckOrderPriceIsNegotiationPrice() throws InterruptedException {
		    SoftAssert softAssert = new SoftAssert();		     
		    Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 3, 12));
			 
			boolean result = false;
		    for(int i =1;i<3;i++){
		    	 if(orders[i][5].equals("1/2 pagina volledig staand") && orders[i][9].toString().trim().equals("1.111,00")){
		    		 result = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(result, "Cover2-half with price=1.111,00 is not in the order overview");
			 
		     
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckOrderPriceIsNegotiationPrice")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("F4Dev0000NegotiationSingleAccepted", 5), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 
	 	@AfterClass
		public void stop() throws InterruptedException {
	 	    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
