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
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"Bid"}, dependsOnGroups="Offer", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class C1Dev1137BidAutoBid {	
	  private String product = "CD101V - Cover 2";
	  private String media = Helper.BuyNow;
	  private String format = "CD101V";
	  private String theDate = Helper.bidNoTuesday;
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}
	  
	  @Test(dataProvider="addToMyLots",alwaysRun = true)
	  public void AddToMyLots(String user) throws InterruptedException {	
		     Top.Login(user,"Welkom01@1");
			 Exchange.GotoBuyerEchangePage();
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectFormat(format);
			 Exchange.SelectMedia(media);
			 Top.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product);  
             Top.Logout();
	  }
	  @DataProvider
		  public Object[][] addToMyLots() {
		    return new Object[][] { 
		      {Helper.ADV},
		      {Helper.ADV2},

		    };
	  }
	  @Test(dependsOnMethods="AddToMyLots",alwaysRun = true)
	  public void ADVautobid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Helper.ADV,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product);
			 Mylots.ExpandALot(product,"grey");
			 Mylots.SetAutoBidPrice(product,"4");    //top price = 6500, 4 levels higher than floor price		 
			 Top.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_bid));
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  @Test(dependsOnMethods="ADVautobid",alwaysRun = true)
	  public void ADV2bidBecomeOutbid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Helper.ADV2,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product);			 
			 Top.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_outbid));
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  @Test(dependsOnMethods="ADV2bidBecomeOutbid",alwaysRun = true)
	  public void ADVLotStatusIsBid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Helper.ADV,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(product);			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_bid));
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  @Test(dependsOnMethods="ADVLotStatusIsBid",alwaysRun = true)
	  public void ADV2bidAgainAgainBecomeWinner() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Helper.ADV2,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		 
			 Mylots.SelectALot(product);			 
			 Top.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_outbid));
			 
			 Mylots.SelectALot(product);			 
			 Top.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_bid));
			 			 			 
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  @Test(dependsOnMethods="ADV2bidAgainAgainBecomeWinner",alwaysRun = true)
	  public void ADVIsOutBidADVBidAgain() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Helper.ADV,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(product);			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_outbid));
			 
			 Top.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_bid));
			 
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout 			     						 			 
	  }	
	  @Test(dependsOnMethods="ADVIsOutBidADVBidAgain",alwaysRun = true)
	  public void ADV2IsOutBid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Helper.ADV2,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(product);			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_outbid));
	         
			 Top.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		} 
}
