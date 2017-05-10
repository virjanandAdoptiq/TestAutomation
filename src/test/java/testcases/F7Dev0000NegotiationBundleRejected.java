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
public class F7Dev0000NegotiationBundleRejected { 
	  String product1 = D.Pagina2FullPage;   
	  String product2 = D.Pagina3FullPage;   
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
		     Lib.deleteAllMailsFromInbox();
			 Top.StartBroswer();				
	  }
	  @Test(alwaysRun = true)
	  public void AddToMyLots() throws InterruptedException {	
			 Top.Login(Lib.MB,"Welkom01@1");
			 Exchange.GotoBuyerEchangePageTileView();
			 Exchange.SelectCampaign("");
			 Exchange.ClickAMediaTile(Lib.BuyNow2);
			 Exchange.SelectCampaign(Lib.CampaignADV2);
			 Exchange.EnterFromThroughDate(Lib.buyDay1);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product1);
			 Exchange.AddToMyLots(product2);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 
		     Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeNegotiate() throws InterruptedException {		  
    		 Mylots.SelectALot(product1);
    		 Mylots.SelectALot(product2);

			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Mylots.Negotiation("7777", Lib.lmDay1, true);
			 Top.Logout();
	  }	
  
	  @Test(dependsOnMethods="MakeNegotiate")
	  public void Res2RejectIt() throws InterruptedException {	
		     Top.Login(Lib.Res2, "Welkom01@1");
			 ExchangeP.GoToExchangePlatform();
			 ExchangeP.SelectANegotiationy("7.777,00");		
			 ExchangeP.ExpandANegotiationy("7.777,00");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_reject_button)); 
	//DEV-1818	
//JOptionPane.showMessageDialog(null,"DEV-1818. Expand it to reject");
			 
			 Lib.CloseDialogBox();			 
			 Top.Logout();
	  }	
	  
	  @Test(dependsOnMethods="Res2RejectIt")
	  public void MBCheckLotsStatusAndDeleteThem() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
		     Top.Login(Lib.MB,"Welkom01@1");
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     Mylots.ExpandAGroup("Onderhandeling");
	
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1, D.$bm_lot_status_negotiationCancelled));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2, D.$bm_lot_status_negotiationCancelled));				
			 
			 Mylots.SelectALotInsideGroup(product1);
			 Mylots.SelectALotInsideGroup(product2);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();
			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();
	  }
	  
	  @Test(dependsOnMethods="MBCheckLotsStatusAndDeleteThem")
	  public void CheckNegotiationOverview() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();		     
		     Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);
		     Lib.ClickButton(By.xpath(D.$b_negotiation_overview_tab));
		     String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_negotiationoverview_table), 14, 12));
             boolean resultCover2 = false;
		     for(int i =1;i<14;i++){
		    	 if(orders[i][6].trim().equals("Pagina 3") && orders[i][9].trim().equals("3.888,50") && orders[i][10].trim().equals("Afgewezen")){
		    		 resultCover2 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultCover2, "Pagina 3, price=3.888,50 and status=Goedgekeurd is not in negotiation overview");
		     boolean resultCover3 = false;
		     for(int i =1;i<14;i++){
		    	 if(orders[i][6].trim().equals("Pagina 2") && orders[i][9].trim().equals("3.888,50") && orders[i][10].trim().equals("Afgewezen")){
		    		 resultCover3 = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(resultCover3, "Pagina 2, price=3.888,50 and status=Goedgekeurd is not in negotiation overview");
		
		     D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	
	  @Test(dependsOnMethods="CheckNegotiationOverview")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("F7Dev0000NegotiationBundleRejected", 5), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 
	 	@AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
