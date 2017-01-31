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


@Test//(groups = {"E1"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class E1Dev1523AutoBidADVBidMB {	
	  private String product = D.Pagina3FullPage;
	//private String product = D.Pagina2FullPage;
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
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectFormat(format);
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product);  
             Top.Logout();
	  }
	  @DataProvider
	  public Object[][] addToMyLots() {
		    return new Object[][] { 
		      {Lib.ADV,""},
		      {Lib.MB,Lib.CampaignADV2},
		    };
	  }
	  @Test(dependsOnMethods="AddToMyLots",alwaysRun = true)
	  public void ADVautobid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Lib.ADV,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product);
			 Mylots.ExpandALot(product,"grey");
			 Mylots.SetAutoBidPrice(product,"4");    //top price = 6500, 4 levels higher than floor price		 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_bid));
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  @Test(dependsOnMethods="ADVautobid",alwaysRun = true)
	  public void MBbidBecomeOutbid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Lib.MB,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product);			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_outbid));
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  @Test(dependsOnMethods="MBbidBecomeOutbid",alwaysRun = true)
	  public void ADVLotStatusIsBid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Lib.ADV,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(product);			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_bid));
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  @Test(dependsOnMethods="ADVLotStatusIsBid",alwaysRun = true)
	  public void MBbidAgainAgainBecomeWinner() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Lib.MB,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		 
			 Mylots.SelectALot(product);			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_outbid));
			 
			 Mylots.SelectALot(product);			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_bid));
			 			 			 
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  @Test(dependsOnMethods="MBbidAgainAgainBecomeWinner",alwaysRun = true)
	  public void ADVIsOutBidADVBidAgain() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Lib.ADV,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(product);			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_outbid));
			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_bid));
			 
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout 			     						 			 
	  }	
	  @Test(dependsOnMethods="ADVIsOutBidADVBidAgain",alwaysRun = true)
	  public void MBIsOutBid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Lib.MB,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(product);			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_outbid));
	         
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();		     						 			 
	  }	
	  @Test(dependsOnMethods="MBIsOutBid")
	  public static void checkEmail() throws InterruptedException{
		    Top.CloseBrowser();
		  
		    SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("E1Dev1523AutoBidADVBidMB", 27), "emailCorrect");				
			softAssert.assertAll(); 		  
	  }	 
	  @AfterClass
	  public void stop() throws InterruptedException{
		  Top.CloseBrowser();  
	  }	
}
