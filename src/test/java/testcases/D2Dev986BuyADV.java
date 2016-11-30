package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import advertiser.Exchange;
import advertiser.Mylots;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"D2"}, dependsOnGroups="D1", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class D2Dev986BuyADV {
	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
			 Top.StartBroswer();
			 Top.Login(Lib.ADV,"Welkom01@1");				
	  }
	  @Test(alwaysRun = true)
	  public void AddToMyLots() throws InterruptedException {	
		  
			 Exchange.GotoBuyerEchangePage();		 
			 Exchange.SelectPhase("Buy Now");
			 Exchange.SelectMedia(Lib.BuyNow2);
			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));	
			 Exchange.AddToMyLots("CD102VS - Voorpagina");
			 Exchange.AddToMyLots("CD102VS - Pagina 2");
			 Exchange.AddToMyLots("CD102VS - Pagina 3");
			 Exchange.AddToMyLots("CD102VL - Pagina 4-5");
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
	
			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void ADBuyOne() throws InterruptedException {
			 Mylots.SelectALot("CD102VS - Voorpagina");
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
	  }
	  @Test(dependsOnMethods="AddToMyLots")
	  public void ADBuyMultiple() throws InterruptedException {	 
			 Mylots.SelectALot("CD102VS - Pagina 2");
			 Mylots.SelectALot("CD102VS - Pagina 3");
			 Mylots.SelectALot("CD102VL - Pagina 4-5");
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);			 			 		  
	  }
	  	  
	 	@AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
