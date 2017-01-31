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
import publisher.ExchangeP;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"E5"}, dependsOnGroups="E4", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class E5Dev1526UnderBidMBADV2 {	
	  private String product = D.Pagina2FullPage;
	  private String productP = "CD101V - Pagina 2";
	  private String media = Lib.BuyNow2;
	  private String format = "CD101V";
	  private String theDate = Lib.lmDay2;

	  @BeforeClass
	  public void start() throws InterruptedException{
	        Lib.deleteAllMailsFromInbox();
			Top.StartBroswer();
	  }
	  
	  @Test(dataProvider="addToMyLots",alwaysRun = true)
	  public void AddToMyLots(String user, String campaign) throws InterruptedException {	
		     Top.Login(user,"Welkom01@1");
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(campaign);
			 Exchange.SelectFormat(format);
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product);  
             Top.Logout();
	  }
	  @DataProvider
		  public Object[][] addToMyLots() {
		    return new Object[][] { 
		      {Lib.MB,Lib.CampaignADV},
		      {Lib.ADV2,""},

		    };
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void MBunderBidautobid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Lib.MB,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product);
			 Mylots.ExpandALot(product,"grey");
			 Mylots.SetUnderBidPrice(product,"2000");
			 Mylots.SetAutoBidPrice(product,"2");    //1 levels higher than floor price			
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_underbid));

			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	

	  @Test(dependsOnMethods="MBunderBidautobid")
	  public void ADV2underBid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Lib.ADV2,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product);
			 Mylots.ExpandALot(product,"grey");
			 Mylots.SetUnderBidPrice(product,"2500");
			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_underbid));
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout				     						 			 
	  }	
	  @Test(dependsOnMethods="ADV2underBid")
	  public void Res2AcceptADV2UnderBid() throws InterruptedException {			  		     
			 Top.Login(Lib.Res2,"Welkom01@1");				 
			 ExchangeP.AcceptUnderbid(productP);			 
			 Top.Logout();  		     						 			 
	  }	
	  @Test(dependsOnMethods="Res2AcceptADV2UnderBid")
	  public void MBLotStatusIsUnderBid() throws InterruptedException {	//Story DEV-1160  this needs to be re-implement
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Lib.MB,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(product);			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_underbid));   //Story DEV-1160
		
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout				     						 			 
	  }	
	  @Test(dependsOnMethods="MBLotStatusIsUnderBid")
	  public void Res2RejectMBUnderBid() throws InterruptedException {			  		     
			 Top.Login(Lib.Res2,"Welkom01@1");				 
			 ExchangeP.RejectUnderbid(productP);			 
			 Top.Logout();  		     						 			 
	  }	
	  @Test(dependsOnMethods="Res2RejectMBUnderBid")
	  public void MBLotStatusIsOutbid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Lib.MB,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		 
			 Mylots.SelectALot(product);			      
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_saved));
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			
			 Top.Logout(); 
			 Top.CloseBrowser();
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();			     						 			 
	  }	
	  @Test(dependsOnMethods="MBLotStatusIsOutbid")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("E5Dev1526UnderBidMBADV2", 10), "emailCorrect");				
			softAssert.assertAll(); 		  
	  }	 
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();  
	  }	
}
