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
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"UnderBid"}, dependsOnGroups="Bid", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class C2Dev1139UnderBidAutoBid {	
	  private String product = "CD101V - Cover 3";
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
			 Exchange.SelectFormat(format);
			 Exchange.EnterFromThroughDate(theDate);
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
	  @Test(dependsOnMethods="AddToMyLots")
	  public void ADVunderBidautobid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Helper.ADV,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product);
			 Mylots.ExpandALot(product,"grey");
			 Mylots.SetUnderBidPrice(product,"2000");
			 Mylots.SetAutoBidPrice(product,"2");    //1 levels higher than floor price			
			 Top.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_underbid));

			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout			     						 			 
	  }	
	  @Test(dependsOnMethods="ADVunderBidautobid")
	  public void ADV2underBid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Helper.ADV2,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product);
			 Mylots.ExpandALot(product,"grey");
			 Mylots.SetUnderBidPrice(product,"2500");
			 
			 Top.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_underbid));
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout				     						 			 
	  }	
	  @Test(dependsOnMethods="ADV2underBid")
	  public void ResAcceptADV2UnderBid() throws InterruptedException {			  		     
			 Top.Login(Helper.Res,"Welkom01@1");				 
			 ExchangeP.AcceptUnderbid(product);			 
			 Top.Logout();  		     						 			 
	  }	
	  @Test(dependsOnMethods="ResAcceptADV2UnderBid")
	  public void ADVCanBid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Helper.ADV,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(product);			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_outbid)); 
			 Top.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));        //Bug DEV-1138
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm); 
			 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_bid));
			 Top.Logout();  
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout				     						 			 
	  }	
	  @Test(dependsOnMethods="ADVCanBid")
	  public void ADV2LotStatusIsOutbid() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();

			 Top.Login(Helper.ADV2,"Welkom01@1");	
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		 
			 Mylots.SelectALot(product);			      
			 softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_outbid));
			 Top.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			
			  Top.Logout(); 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();			     						 			 
	  }	
	  @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		} 
}
