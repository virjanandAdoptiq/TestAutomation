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
public class F2Dev0000NegotiationMB {
	  String product1 = "Cover 21/2 pagina volledig staand";
	  String product2 = "Pagina 4-51/2 pagina volledig staand";
	  String product3 = "Voorpagina1/2 pagina volledig liggend";
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
			 Top.StartBroswer();
			 Top.Login(Lib.MB,"Welkom01@1");				
	  }
	  @Test(alwaysRun = true)
	  public void AddToMyLots() throws InterruptedException {	
		  
			 Exchange.GotoBuyerEchangePage();	
			 Exchange.SelectCampaign(Lib.CampaignADV);	
			 Exchange.SelectMedia(Lib.BuyNow);
			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product1);
			 
			 Lib.ClickButton(By.xpath(D.$be_restore));
			 Exchange.SelectMedia(Lib.BuyNow2);
			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product2);
			 Exchange.AddToMyLots(product3);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
	
			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void TryNegotiateFromTwoSaleOrg() throws InterruptedException {
//		    SoftAssert softAssert = new SoftAssert();
			 Mylots.SelectALot(product1);
			 Mylots.SelectALot(product2);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));
//			 softAssert.assertTrue(Lib.isBox("Waarschuwing"));
			 Lib.ClickButton(By.xpath(D.$OK_Button));			 
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(product1);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
//			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
//			 softAssert.assertAll();
			 
	  }
	  @Test(dependsOnMethods="TryNegotiateFromTwoSaleOrg")
	  public void NegotiateTwoBundle() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
			 Mylots.SelectALot(product2);
			 Mylots.SelectALot(product3);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_negotiation_icon));

			 Mylots.Negotiation("300", Lib.lmDay1,true);

			 softAssert.assertTrue(Mylots.CheckLotStatus(product2, D.$bm_lot_status_negotiateSubmitted));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product3, D.$bm_lot_status_negotiateSubmitted));
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();			 
	  }	  
	  @Test(dependsOnMethods="NegotiateTwoBundle")
	  public void TryToCancelOneFromBundle() throws InterruptedException {
		     SoftAssert softAssert = new SoftAssert();
			 Mylots.SelectALot(product2);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));

	//		 softAssert.assertTrue(Lib.isBox("Info"));
			 Lib.CloseDialogBox();
			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product2, D.$bm_lot_status_negotiateSubmitted));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product3, D.$bm_lot_status_negotiateSubmitted));
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();			 
	  }	  
	 	@AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
