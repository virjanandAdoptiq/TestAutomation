package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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
@Test//(groups = {"F1"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class F1Dev0000NegotiationBundelDeleteCheck {
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
	
			 Mylots.SetFilterOrderNotShow();
			 Mylots.SetFilterBidNotShow();
			 Mylots.ClickApplyFilterButton();

	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeBundleNegotiate() throws InterruptedException {		  
    		 Mylots.SelectALot(product1);
			 Mylots.SelectALot(product2);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Mylots.Negotiation("4444,88", Lib.lmDay1,true);

	  }
	  @Test(dataProvider="inputData",dependsOnMethods="MakeBundleNegotiate")
	  public void CheckGroupRowResult(String BuyNowPrice, String TotalReach, String CPMPrice, String SaleOrg,String NumberOfLots) throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
			 			 
			 softAssert.assertEquals(Mylots.GetGroupLotInfo("Onderhandeling", D.$bm_lot_group_buynow_price), BuyNowPrice);
			 softAssert.assertEquals(Mylots.GetGroupLotInfo("Onderhandeling", D.$bm_lot_group_negotiation_reach), TotalReach);
			 softAssert.assertEquals(Mylots.GetGroupLotInfo("Onderhandeling", D.$bm_lot_group_cpm_price), CPMPrice);
			 softAssert.assertEquals(Mylots.GetGroupLotInfo("Onderhandeling", D.$bm_lot_group_sale_org), SaleOrg);
			 softAssert.assertEquals(Mylots.GetGroupLotInfo("Onderhandeling", D.$bm_lot_group_number_lots), NumberOfLots);
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();			 
	  }
	  @DataProvider
		public Object[][] inputData() {
		return new Object[][] { 	
			{"€ 3.750,00","400.000","€ 9,38",Lib.Res,"2 kavel(s)"},
		};
	  }
	  
	  @Test(dependsOnMethods="CheckGroupRowResult")
	  public void CheckEachLotsStatus() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
		     Mylots.ExpandAGroup("Onderhandeling");
	         Lib.FindElement(By.xpath(D.$bm_lot_status_negotiateSubmitted));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1, D.$bm_lot_status_negotiateSubmitted));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2, D.$bm_lot_status_negotiateSubmitted));
				
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }
	  @Test(dependsOnMethods="CheckEachLotsStatus")
	  public void TryToDeleteALots() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
		     
			 Mylots.SelectALotInsideGroup(product1);
	
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();
			 
			 Mylots.ExpandAGroup("Onderhandeling");
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1, D.$bm_lot_status_negotiateSubmitted));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2, D.$bm_lot_status_negotiateSubmitted));
						 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();			 
	  }	
	   
	  @Test(dependsOnMethods="TryToDeleteALots")
	  public void DeleteAllLotsfromGroup() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();		     
			 Mylots.SelectAGroup("Onderhandeling");	
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product1, D.$bm_lot_status_negotiationCancelled));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product2, D.$bm_lot_status_negotiationCancelled));						 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  
	  @Test(dependsOnMethods="DeleteAllLotsfromGroup")
	  public void CheckNegotiationOverview() throws InterruptedException {
		    SoftAssert softAssert = new SoftAssert();		     
		     Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);
		     Lib.ClickButton(By.xpath(D.$b_negotiation_overview_tab));
		     String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_negotiationoverview_table), 3, 12));
//		     for(int i=0;i<3;i++){
//		    	 for(int j=0;j<12;j++){
//		    		 System.out.println("i=" + i + " j=" + j + " " + orders[i][j]);
//		    	 }
//		     }
//	order: i=1 j=1 22-02-2017
//	order: i=1 j=2 JeAfX3BuyNow
//	order: i=1 j=3 JeAfX3BuyNow, woensdag 22 februari 2017
//	order: i=1 j=4 
//	order: i=1 j=5 Volledige pagina
//	order: i=1 j=6 Cover 3
//	order: i=1 j=7 JeAfX3ADV2
//	order: i=1 j=8 JeAfX3ADV2-Campaign
//	order: i=1 j=9 1.667,00
//	order: i=1 j=10 Geannuleerd
//	order: i=1 j=11 
		     softAssert.assertEquals(orders[1][2].toString().trim(), Lib.BuyNow);
		     softAssert.assertEquals(orders[2][2].toString().trim(), Lib.BuyNow);
			 softAssert.assertEquals(orders[1][10].toString().trim(), "Geannuleerd");
			 softAssert.assertEquals(orders[2][10].toString().trim(), "Geannuleerd");
		     softAssert.assertEquals(orders[1][9].toString().trim(), "2.222,44"); 
		     softAssert.assertEquals(orders[2][9].toString().trim(), "2.222,44");

			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			 
	  }	 
	  @Test(dependsOnMethods="CheckNegotiationOverview")
	  public void DeleteAllLotsfromMyLots() throws InterruptedException {
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     Mylots.SelectALot(product1);
		     Mylots.SelectALot(product2);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();			 	 
	  }	 
	  @Test(dependsOnMethods="DeleteAllLotsfromMyLots")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("F1Dev0000NegotiationBundelDeleteCheck", 6), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	 
	 	@AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
