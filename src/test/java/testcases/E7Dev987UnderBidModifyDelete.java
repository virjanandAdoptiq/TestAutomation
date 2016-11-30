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

@Test(groups = {"E7"}, dependsOnGroups="E6", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class E7Dev987UnderBidModifyDelete {	
	  private String product = "CD102VL - Cover 2";
	  private String media = Lib.BuyNow;
	  private String format = "CD102VL";
	  private String theDate = Lib.lmDay2;

	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}
	  
	  @Test
	  public void UnderbidsAndModify() throws InterruptedException {	
		     Top.Login(Lib.ADV,"Welkom01@1");
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectFormat(format);
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product);  
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 //Underbid
			 Mylots.SelectALot(product);
			 Mylots.ExpandALot(product,"grey");
			 Mylots.SetUnderBidPrice(product,"1000");		
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_bid_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_bid_confirm);
			 
			 //Modify
			 Mylots.SelectALot(product);
			 Mylots.ExpandALot(product,"red");
			 Mylots.ChangeUnderBidPrice(product, "1500");	
			 
			 //delete the underbid
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 
			 //check lot status
			 SoftAssert softAssert = new SoftAssert();
			 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_saved));
			 
			 //delete from MyLots
			 Mylots.SelectALot(product);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 
             Top.Logout();
             
             D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
	  }
	  
	 
	  @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		} 
}
