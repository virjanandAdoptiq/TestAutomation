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
public class F9Dev0000NegotiationNonBundleAccepted { 
	  String product1 = D.Pagina2FullPage;   
	  String product2 = D.Pagina3HalfStand;   
	  	  
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
			 Exchange.EnterFromThroughDate(Lib.buyDay1);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product1);
			 Exchange.AddToMyLots(product2);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 
		     Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeNonNegotiate() throws InterruptedException {		  
    		 Mylots.SelectALot(product1);
    		 Mylots.SelectALot(product2);

			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Mylots.Negotiation("888", Lib.lmDay1, false);
			 Top.Logout();
	  }	
  
	  @Test(dependsOnMethods="MakeNonNegotiate")
	  public void UGAcceptIt() throws InterruptedException {	
		     Top.Login(Lib.Res2, "Welkom01@1");
			 ExchangeP.GoToExchangePlatform();
			 ExchangeP.SelectANegotiationy("888,00");	
			 ExchangeP.ExpandANegotiationy("888,00");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_accept_button)); 

			 Lib.CloseDialogBox();
			 
			 Top.Logout();
	  }	

	  
	  @Test(dependsOnMethods="UGAcceptIt")
	  public void CheckNegotiationOverview() throws InterruptedException {
		     Top.Login(Lib.MB,"Welkom01@1");
		     SoftAssert softAssert = new SoftAssert();		     
		     Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);
		     Lib.ClickButton(By.xpath(D.$b_negotiation_overview_tab));
		     String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_negotiationoverview_table), 16, 12));
             boolean resultCover2 = false;
		     for(int i =1;i<16;i++){
		    	 if(orders[i][6].trim().equals("Pagina 2") && orders[i][9].trim().equals("592,00") && orders[i][10].trim().equals("Goedgekeurd")){
		    		 resultCover2 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultCover2, "Pagina 2, price=592,00 and status=Goedgekeurd is not in negotiation overview");
		     boolean resultCover3 = false;
		     for(int i =1;i<16;i++){
		    	 if(orders[i][6].trim().equals("Pagina 3") && orders[i][9].trim().equals("296,00") && orders[i][10].trim().equals("Goedgekeurd")){
		    		 resultCover3 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultCover3, "Pagina 3, price=296,00 and status=Goedgekeurd is not in negotiation overview");
		
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
		    	 if(orders[i][6].equals("Pagina 3") && orders[i][9].toString().trim().equals("296,00")){
		    		 resultCover2 = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(resultCover2, "Pagina 3 with price=296,00 is not in the order overview");
		    boolean resultCover3 = false;
		    for(int i =1;i<9;i++){
		    	 if(orders[i][6].equals("Pagina 2") && orders[i][9].toString().trim().equals("592,00")){
		    		 resultCover3 = true;
		    		 break;
		    	 }
		     }
		    softAssert.assertTrue(resultCover3, "Pagina 2 with price=592,00 is not in the order overview");
		
			 
		     
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckOrderPriceIsNegotiationPrice")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("F9Dev0000NegotiationNonBundleAccepted", 5), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 
	 	@AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
