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
public class Fa1Dev0000NegotiationNonBundleCounterOfferAcceptReject { 
	  String product1 = D.VoorpaginaFullPage;   
	  String product2 = D.Pagina45FullPage;   
	  String product3 = D.Pagina3HalfLying;
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
		     Lib.deleteAllMailsFromInbox();
			 Top.StartBroswer();				
	  }
	  @Test(alwaysRun = true)
	  public void AddToMyLots() throws InterruptedException {	
			 Top.Login(Lib.MB,"Welkom01@1");
			 Exchange.GotoBuyerEchangePageTileView();
			 Exchange.SelectCampaign(Lib.CampaignADV);
			 Exchange.ClickAMediaTile(Lib.BuyNow2);
			 Exchange.EnterFromThroughDate(Lib.buyDay1);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product1);
			 Exchange.AddToMyLots(product2);
			 Exchange.AddToMyLots(product3);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 
		     Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeNegotiate() throws InterruptedException {				     
    		 Mylots.SelectALot(product1);
    		 Mylots.SelectALot(product2);
    		 Mylots.SelectALot(product3);

			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Mylots.Negotiation("9999", Lib.lmDay1, false);
			 Top.Logout();
	  }	  
	  @Test(dependsOnMethods="MakeNegotiate")
	  public void Res2Reaction() throws InterruptedException {	
		     Top.Login(Lib.Res2, "Welkom01@1");
			 ExchangeP.GoToExchangePlatform();
			 
			 ExchangeP.ExpandANegotiationy("9.999,00");

			 ExchangeP.ExpandedNegotiationyRejectOne("CD102VL - Pagina 3");
			 ExchangeP.ExpandedNegotiationyAcceptOne("CD101V - Voorpagina");
			 
			 ExchangeP.GoToExchangePlatform();
			 Lib.ClickButton(By.xpath(D.$img_edit));
//			 ExchangeP.ExpandedNegotiationyEditOne("CD101V - Pagina 4-5");		walkaround 
			 ExchangeP.NegotiationyGiveCounterPrice("1", "4000");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_edit_nonB_counteroffer));
			 Lib.ClickButton(By.xpath(D.$Doorgaan_Button));
			 Lib.CloseDialogBox();	
			 
			 Top.Logout();
	  }	

	  @Test(dependsOnMethods="Res2Reaction")
	  public void MBCheckLotsStatus() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
		     Top.Login(Lib.MB,"Welkom01@1");
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     Mylots.ExpandAGroup("Onderhandeling");
	
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1, D.$bm_lot_status_order));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2, D.$bm_lot_status_negotiateCounterOffer));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product3, D.$bm_lot_status_negotiationCancelled));
				
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }
	  @Test(dependsOnMethods="MBCheckLotsStatus")
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
		     String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_negotiationoverview_table), 16, 12));
             boolean resultCover2 = false;
		     for(int i =1;i<16;i++){
		    	 if(orders[i][6].trim().equals("Voorpagina") && orders[i][9].trim().equals("3.999,60") && orders[i][10].trim().equals("Goedgekeurd")){
		    		 resultCover2 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultCover2, "Voorpagina, price=3.999,60 and status=Goedgekeurd is not in negotiation overview");
		     boolean resultCover3 = false;
		     for(int i =1;i<16;i++){
		    	 if(orders[i][6].trim().equals("Pagina 4-5") && orders[i][9].trim().equals("4.000,00") && orders[i][10].trim().equals("Goedgekeurd")){
		    		 resultCover3 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultCover3, "Pagina 4-5, price=4.000,00 and status=Goedgekeurd is not in negotiation overview");
		     boolean resultPagina3 = false;
		     for(int i =1;i<16;i++){
		    	 if(orders[i][6].trim().equals("Pagina 3") && orders[i][9].trim().equals("1.999,80") && orders[i][10].trim().equals("Afgewezen")){
		    		 resultPagina3 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultPagina3, "Pagina 4-5, price=1.999,80 and status=Afgeweze is not in negotiation overview");
		
		
		     D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckNegotiationOverview")
	  public void CheckOrderPriceIsNegotiationPrice() throws InterruptedException {
		    SoftAssert softAssert = new SoftAssert();		     
		    Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 12, 12));
			 
			boolean resultCover2 = false;
		    for(int i =1;i<12;i++){
		    	 if(orders[i][5].equals("Volledige pagina") && orders[i][6].equals("Voorpagina") && orders[i][9].toString().trim().equals("3.999,60")){
		    		 resultCover2 = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(resultCover2, "Voorpagina with price=3.999,60 is not in the order overview");
		    boolean resultCover3 = false;
		    for(int i =1;i<12;i++){
		    	 if(orders[i][5].equals("Volledige pagina") && orders[i][6].equals("Pagina 4-5") && orders[i][9].toString().trim().equals("4.000,00")){
		    		 resultCover3 = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(resultCover3, "Pagina 4-5 with price=4.000,00 is not in the order overview");
		    boolean resultCoverPagina3 = false;
		    for(int i =1;i<12;i++){
		    	 if(orders[i][5].equals("1/2 pagina volledig liggend") && orders[i][6].equals("Pagina 3") && orders[i][9].toString().trim().equals("2.062,50")){
		    		 resultCoverPagina3 = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(resultCoverPagina3, "Pagina 3 with price=2.062,50 is not in the order overview");
			     
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckOrderPriceIsNegotiationPrice")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("Fa1Dev0000NegotiationNonBundleCounterOfferAcceptReject", 14), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 
	 	@AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
