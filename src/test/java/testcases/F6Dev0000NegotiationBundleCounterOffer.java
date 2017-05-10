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
@Test//(groups = {"F5"}, dependsOnGroups="F4", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class F6Dev0000NegotiationBundleCounterOffer { 
	  String product1 = D.Cover3FullPage;   
	  String product2 = D.Cover2FullPage;   
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
		     Lib.deleteAllMailsFromInbox();
			 Top.StartBroswer();				
	  }
	  @Test(alwaysRun = true)
	  public void AddToMyLots() throws InterruptedException {	
			 Top.Login(Lib.MB,"Welkom01@1");
			 Exchange.GotoBuyerEchangePageTileView();
			 Exchange.ClickAMediaTile(Lib.BuyNow);
			 Exchange.SelectCampaign(Lib.CampaignADV2);
			 Exchange.EnterFromThroughDate(Lib.weekDay);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product1);
			 Exchange.AddToMyLots(product2);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 
		     Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeNegotiate() throws InterruptedException {		
		     Mylots.ExpandALot(product1,"grey");
		     Mylots.SelectPublisher(product1,Lib.UG);
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     Mylots.ExpandALot(product2,"grey");
		     Mylots.SelectPublisher(product2,Lib.UG);
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     
    		 Mylots.SelectALot(product1);
    		 Mylots.SelectALot(product2);

			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Mylots.Negotiation("2000", Lib.lmDay1, true);
			 Top.Logout();
	  }	  
	  
	  @Test(dependsOnMethods="MakeNegotiate")
	  public void UGGiveCounterOffer() throws InterruptedException {	
		     Top.Login(Lib.UG, "Welkom01@1");
			 ExchangeP.GoToExchangePlatform();
			 ExchangeP.SelectANegotiationy("2.000,00");
			 ExchangeP.ExpandANegotiationy("2.000,00");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));
			 ExchangeP.NegotiationyGiveCounterPrice("1", "1823");
			 ExchangeP.NegotiationyGiveCounterPrice("2", "1823");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_edit_send_counter_offer));
			 Lib.ClickButton(By.xpath(D.$Doorgaan_Button));
			 Lib.CloseDialogBox();			 
			 Top.Logout();
	  }	

	  @Test(dependsOnMethods="UGGiveCounterOffer")
	  public void MBCheckEachLotsStatus() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
		     Top.Login(Lib.MB,"Welkom01@1");
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     Mylots.ExpandAGroup("Onderhandeling");
	
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1, D.$bm_lot_status_negotiateCounterOffer));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2, D.$bm_lot_status_negotiateCounterOffer));
				
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }
	  @Test(dependsOnMethods="MBCheckEachLotsStatus")
	  public void MBBuyCounterOfferLots() throws InterruptedException {
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     Mylots.SelectAGroup("Onderhandeling");
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);						 
	  }
	  
	  @Test(dependsOnMethods="MBBuyCounterOfferLots")
	  public void CheckNegotiationOverview() throws InterruptedException {  
		     SoftAssert softAssert = new SoftAssert();		     
		     Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);
		     Lib.ClickButton(By.xpath(D.$b_negotiation_overview_tab));
		     String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_negotiationoverview_table), 10, 12));
             boolean resultCover2 = false;
		     for(int i =1;i<10;i++){
		    	 if(orders[i][6].trim().equals("Cover 2") && orders[i][9].trim().equals("1.823,00") && orders[i][10].trim().equals("Goedgekeurd")){
		    		 resultCover2 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultCover2, "Cover2, price=1.823,00 and status=Goedgekeurd is not in negotiation overview");
		     boolean resultCover3 = false;
		     for(int i =1;i<10;i++){
		    	 if(orders[i][6].trim().equals("Cover 3") && orders[i][9].trim().equals("1.823,00") && orders[i][10].trim().equals("Goedgekeurd")){
		    		 resultCover3 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultCover3, "Cover3, price=1.823,00 and status=Goedgekeurd is not in negotiation overview");
		
		     D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckNegotiationOverview")
	  public void CheckOrderPriceIsNegotiationPrice() throws InterruptedException {
		    SoftAssert softAssert = new SoftAssert();		     
		    Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 6, 12));
			 
			boolean resultCover2 = false;
		    for(int i =1;i<6;i++){
		    	 if(orders[i][5].equals("Volledige pagina") && orders[i][6].equals("Cover 2") && orders[i][9].toString().trim().equals("1.823,00")){
		    		 resultCover2 = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(resultCover2, "Cover2 with price=1.823,00 is not in the order overview");
		    boolean resultCover3 = false;
		    for(int i =1;i<6;i++){
		    	 if(orders[i][5].equals("Volledige pagina") && orders[i][6].equals("Cover 3") && orders[i][9].toString().trim().equals("1.823,00")){
		    		 resultCover3 = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(resultCover3, "Cover3 with price=1.823,00 is not in the order overview");
		
			 
		     
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckOrderPriceIsNegotiationPrice")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("F6Dev0000NegotiationBundleCounterOffer", 6), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 
	 	@AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
