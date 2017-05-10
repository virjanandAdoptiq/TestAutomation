package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
public class Fa3Dev0000NegotiationPingPongNonBundleMB { 
	  String product1 = D.Pagina45HalfStand;   
	  String product2 = D.Pagina2HalfStand;   
	  String product3 = D.Pagina3HalfStand;
	  	  
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
			 Exchange.ClickAMediaTile(Lib.BuyNow2);
			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product1);
			 Exchange.AddToMyLots(product2);
			 Exchange.AddToMyLots(product3);			 					 
	  }	  
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeNegotiate() throws InterruptedException {		
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 	
    		 Mylots.SelectALot(product1);
    		 Mylots.SelectALot(product2);
    		 Mylots.SelectALot(product3);

			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Lib.SendSpecialKey(Keys.SPACE);
			 Mylots.Negotiation("9000", Lib.bidDay2, false);
			 Top.Logout();
	  }	 
	  @Test(dependsOnMethods="MakeNegotiate")
	  public void Res2PingPong1() throws InterruptedException {
		     Top.Login(Lib.Res2, "Welkom01@1");
		     ExchangeP.GoToExchangePlatform();
			 ExchangeP.ExpandANegotiationy("9.000,00");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));
			 ExchangeP.NegotiationyGiveCounterPrice("1", "6000");
			 ExchangeP.NegotiationyGiveCounterPrice("2", "6000");
			 ExchangeP.NegotiationyGiveCounterPrice("3", "6000");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_edit_nonB_counteroffer));
			 Lib.ClickButton(By.xpath(D.$Doorgaan_Button));
			 Lib.CloseDialogBox();			 
			 Top.Logout();	 
	  }
	  @Test(dependsOnMethods="Res2PingPong1")
	  public void MBPingPong1() throws InterruptedException {				     
			 Top.Login(Lib.MB,"Welkom01@1");
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);
			 Mylots.SelectAGroup("Onderhandeling");
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Lib.SendSpecialKey(Keys.SPACE);
			 Mylots.NegotiationPingPongPrice("12000");
			 Top.Logout();
	  }	 
	  @Test(dependsOnMethods="MBPingPong1")
	  public void Res2PingPong2() throws InterruptedException {
		     Top.Login(Lib.Res2, "Welkom01@1");
		     ExchangeP.GoToExchangePlatform();
			 ExchangeP.ExpandANegotiationy("12.000,00");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));
			 ExchangeP.NegotiationyGiveCounterPrice("1", "5000");
			 ExchangeP.NegotiationyGiveCounterPrice("2", "5000");
			 ExchangeP.NegotiationyGiveCounterPrice("3", "5000");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_edit_nonB_counteroffer));
			 Lib.ClickButton(By.xpath(D.$Doorgaan_Button));
			 Lib.CloseDialogBox();			 
			 Top.Logout();	 
	  }
	  @Test(dependsOnMethods="Res2PingPong2")
	  public void MBPingPong2() throws InterruptedException {				     
			 Top.Login(Lib.MB,"Welkom01@1");
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);
			 Mylots.SelectAGroup("Onderhandeling");
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Lib.SendSpecialKey(Keys.SPACE);
			 Mylots.NegotiationPingPongPrice("12030");
			 Top.Logout();
	  }	
	  @Test(dependsOnMethods="MBPingPong2")
	  public void Res2PingPong3() throws InterruptedException {	
		     Top.Login(Lib.Res2, "Welkom01@1");
			 ExchangeP.GoToExchangePlatform();
			 ExchangeP.ExpandANegotiationy("12.030,00");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_top_edit_icon));
			 ExchangeP.NegotiationyGiveCounterPrice("1", "4500");
			 ExchangeP.NegotiationyGiveCounterPrice("2", "4500");
			 ExchangeP.NegotiationyGiveCounterPrice("3", "4500");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_edit_nonB_counteroffer));
			 Lib.ClickButton(By.xpath(D.$Doorgaan_Button));
			 Lib.CloseDialogBox();			 
			 Top.Logout();	 
	  }	
	  @Test(dependsOnMethods="Res2PingPong3")
	  public void MBBuyIt() throws InterruptedException {				     
			 Top.Login(Lib.MB,"Welkom01@1");
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);
			 Mylots.SelectAGroup("Onderhandeling");		
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Lib.SendSpecialKey(Keys.SPACE);
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);

	  }	
	  @Test(dependsOnMethods="MBBuyIt")
	  public void CheckNegotiationOverview() throws InterruptedException {  
		     SoftAssert softAssert = new SoftAssert();	
		     Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);
		     Lib.ClickButton(By.xpath(D.$b_negotiation_overview_tab));
		     String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_negotiationoverview_table), 9, 12));
             boolean resultCover2 = false;
		     for(int i =1;i<9;i++){
		    	 if(orders[i][6].trim().equals("Pagina 2") && orders[i][9].trim().equals("4.500,00") && orders[i][10].trim().equals("Goedgekeurd")){
		    		 resultCover2 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultCover2, "Pagina 2, price=4.500,00 and status=Goedgekeurd is not in negotiation overview");
		     boolean resultCover3 = false;
		     for(int i =1;i<9;i++){
		    	 if(orders[i][6].trim().equals("Pagina 4-5") && orders[i][9].trim().equals("4.500,00") && orders[i][10].trim().equals("Goedgekeurd")){
		    		 resultCover3 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultCover3, "Pagina 4-5, price=4.500,00 and status=Goedgekeurd is not in negotiation overview");
		     boolean resultPagina3 = false;
		     for(int i =1;i<9;i++){
		    	 if(orders[i][6].trim().equals("Pagina 3") && orders[i][9].trim().equals("4.500,00") && orders[i][10].trim().equals("Goedgekeurd")){
		    		 resultPagina3 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultPagina3, "Pagina 3, price=4.500,00 and status=Goedgekeurd is not in negotiation overview");
		
		
		     D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckNegotiationOverview")
	  public void CheckOrderPriceIsNegotiationPrice() throws InterruptedException {
		    SoftAssert softAssert = new SoftAssert();		     
		    Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 9, 12));
			 
			boolean resultCover2 = false;
		    for(int i =1;i<9;i++){
		    	 if(orders[i][5].equals("1/2 pagina volledig staand") && orders[i][6].equals("Pagina 2") && orders[i][9].toString().trim().equals("4.500,00")){
		    		 resultCover2 = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(resultCover2, "Pagina 2 with price=4.500,00 is not in the order overview");
		    boolean resultCover3 = false;
		    for(int i =1;i<9;i++){
		    	 if(orders[i][5].equals("1/2 pagina volledig staand") && orders[i][6].equals("Pagina 4-5") && orders[i][9].toString().trim().equals("4.500,00")){
		    		 resultCover3 = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(resultCover3, "Pagina 4-5 with price=4.500,00 is not in the order overview");
		    boolean resultCoverPagina3 = false;
		    for(int i =1;i<9;i++){
		    	 if(orders[i][5].equals("1/2 pagina volledig staand") && orders[i][6].equals("Pagina 3") && orders[i][9].toString().trim().equals("4.500,00")){
		    		 resultCoverPagina3 = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(resultCoverPagina3, "Pagina 3 with price=4.500,00 is not in the order overview");
			     
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckOrderPriceIsNegotiationPrice")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("Fa3Dev0000NegotiationPingPongNonBundleMB", 15), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 

	 	@AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		} 

	
		 
}
