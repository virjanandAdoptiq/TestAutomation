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


@Test//(groups = {"D2"}, dependsOnGroups="D1", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class D2Dev986BuyADV {
	 String product1 = D.VoorpaginaHalfStand;
	 String product2 = D.Pagina2HalfStand;
	 String product3 = D.Pagina3HalfStand;
	 String product4 = D.Pagina45HalfLying;
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
	         Lib.deleteAllMailsFromInbox();
			 Top.StartBroswer();
			 Top.Login(Lib.ADV,"Welkom01@1");				
	  }
	  @Test(alwaysRun = true)
	  public void AddToMyLots() throws InterruptedException {	
		  
			 Exchange.GotoBuyerEchangePageTileView();	
			 Exchange.ClickAMediaTile(Lib.BuyNow2);
			 Exchange.SelectPhase("Buy Now");
	//		 Exchange.SelectMedia(Lib.BuyNow2);
			 Exchange.EnterFromThroughDate(Lib.weekDay);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots(product1);
			 Exchange.AddToMyLots(product2);
			 Exchange.AddToMyLots(product3);
			 Exchange.AddToMyLots(product4);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
	
			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void ADBuyOne() throws InterruptedException {
			 Mylots.SelectALot(product1);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
	  }
	  @Test(dependsOnMethods="ADBuyOne")
	  public void ADBuyMultiple() throws InterruptedException {	 
			 Mylots.SelectALot(product2);
			 Mylots.SelectALot(product3);
			 Mylots.SelectALot(product4);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);	
			 Top.Logout();
	  }
	  @Test(dependsOnMethods="ADBuyMultiple")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("D2Dev986BuyADV", 7), "emailCorrect");				
			D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 		  
	  }	  
	  @AfterClass
	  public void stop() throws InterruptedException {
		  Top.CloseBrowser();
	  } 

	
		 
}
