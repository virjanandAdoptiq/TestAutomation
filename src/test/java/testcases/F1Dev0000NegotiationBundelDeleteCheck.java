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


@Test//(groups = {"F1"}, dependsOnGroups="D1", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class F1Dev0000NegotiationADV {
	  String product1 = D.Cover3FullPage;   //buyday1
	  String product2 = D.Cover2HalfStand;   //weekday
	  String product3 = D.Cover2HalfLying;  //buyday3
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
		     Lib.deleteAllMailsFromInbox();
			 Top.StartBroswer();
			 Top.Login(Lib.MB,"Welkom01@1");				
	  }
	  @Test(alwaysRun = true)
	  public void AddToMyLots() throws InterruptedException {	
		  
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(Lib.CampaignADV2);
			 Exchange.SelectMedia(Lib.BuyNow);
			 Exchange.EnterFromThroughDate(Lib.buyDay1);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product1);
			 Exchange.EnterFromThroughDate(Lib.weekDay);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product2);
			 Lib.ClickButton(By.xpath(D.$be_inventory_duplication_add_one));
			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product3);
			 Lib.ClickButton(By.xpath(D.$be_inventory_duplication_add_one));
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
	
			 Mylots.SetFilterOrderNotShow();
			 Mylots.SetFilterBidNotShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MakeBundleNegotiate() throws InterruptedException {
		  SoftAssert softAssert = new SoftAssert();
			 Mylots.SelectALot(product1);
			 Mylots.SelectALot(product2);
			 Mylots.SelectALot(product3);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
			 Lib.ClickButton(By.xpath(D.$bm_out_of_budget_OK));

			 Mylots.Negotiation("3333,99", Lib.lmDay1,true);

			 softAssert.assertTrue(Mylots.CheckLotStatus(product1, D.$bm_lot_status_negotiateSubmitted));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product2, D.$bm_lot_status_negotiateSubmitted));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product3, D.$bm_lot_status_negotiateSubmitted));
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 
	  }
	  @Test(dependsOnMethods="MakeBundleNegotiate")
	  public void ImpossibleToDeleteOne() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
			 Mylots.SelectALot(product1);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();

			 softAssert.assertTrue(Mylots.CheckLotStatus(product1, D.$bm_lot_status_negotiateSubmitted));
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();			 
	  }
	  @Test(dependsOnMethods="MakeBundleNegotiate")
	  public void DeleteAll() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
			 Mylots.SelectALot(product1);
			 Mylots.SelectALot(product2);
			 Mylots.SelectALot(product3);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));

			 softAssert.assertTrue(Mylots.CheckLotStatus(product1, D.$bm_lot_status_saved));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product2, D.$bm_lot_status_saved));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product3, D.$bm_lot_status_saved));
			 
			 Mylots.SelectALot(product1);
			 Mylots.SelectALot(product2);
			 Mylots.SelectALot(product3);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();			 
	  }	  
	 	@AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
