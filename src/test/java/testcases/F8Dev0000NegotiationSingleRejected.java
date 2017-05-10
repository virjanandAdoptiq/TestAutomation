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
public class F8Dev0000NegotiationSingleRejected { 
	  String product = D.Pagina2HalfLying;   
	  	  
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
			 Exchange.AddToMyLots(product);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 
		     Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeNegotiate() throws InterruptedException {		  
    		 Mylots.SelectALot(product);

			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Mylots.Negotiation("123", Lib.lmDay1, false);
			 Top.Logout();
	  }	
	  
	  @Test(dependsOnMethods="MakeNegotiate")
	  public void Res2RejectIt() throws InterruptedException {	
		     Top.Login(Lib.Res2, "Welkom01@1");
			 ExchangeP.GoToExchangePlatform();
			 ExchangeP.SelectANegotiationy("123,00");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_reject_button)); 
			 Lib.CloseDialogBox();			 
			 Top.Logout();
	  }	
	  @Test(dependsOnMethods="Res2RejectIt")
	  public void MBCheckLotsStatusAndDeleteIt() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
		     Top.Login(Lib.MB,"Welkom01@1");
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     Mylots.ExpandAGroup("Onderhandeling");
	
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_negotiationCancelled));
			
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();
			 
			 Mylots.SelectALot(product);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();
	  }
	  
	  
	  @Test(dependsOnMethods="MBCheckLotsStatusAndDeleteIt")
	  public void CheckNegotiationOverview() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();		
		     Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);
		     Lib.ClickButton(By.xpath(D.$b_negotiation_overview_tab));
		     String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_negotiationoverview_table), 15, 12));
             boolean result = false;
		     for(int i =1;i<15;i++){
		    	 if(orders[i][6].trim().equals("Pagina 2") &&  orders[i][9].trim().equals("123,00") && orders[i][10].trim().equals("Afgewezen")){
		    		 result = true;
		    		 break;
		    	 }
		     }
		     softAssert.assertTrue(result, "price=1.111,00 and status=Goedgekeurd is not in negotiation overview");
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	
	  @Test(dependsOnMethods="CheckNegotiationOverview")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("F8Dev0000NegotiationSingleRejected", 5), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 
	 	@AfterClass
		public void stop() throws InterruptedException {
	 	    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
