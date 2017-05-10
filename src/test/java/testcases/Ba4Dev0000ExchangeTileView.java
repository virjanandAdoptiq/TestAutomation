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

@Test//(groups = {"Ba4"}, dependsOnGroups="Ba3", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class Ba4Dev0000ExchangeTileView {		  
	@BeforeClass
	public void start() throws InterruptedException{
			Top.StartBroswer();
			Top.Login(Lib.MB,"Welkom01@1"); 
	}
	@Test
	public void fromMediaTileAddToMyLots() throws InterruptedException{
		     Exchange.GotoBuyerEchangePageTileView();
		     Exchange.ClickAMediaTile(Lib.BuyNow2);  
			 Exchange.SelectFormat("CD101V");
			 Exchange.SelectFormat("CD102VL");
			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(D.Pagina45FullPage);
			 Exchange.AddToMyLots(D.Pagina3HalfLying);			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(D.Pagina45FullPage);
		     Mylots.SelectALot(D.Pagina3HalfLying);
		     Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
		     Lib.CloseDialogBox();
	}	  
	@Test
	public void withProfileGetMediaTile() throws InterruptedException{
		     Exchange.GotoBuyerEchangePageTileView();
		     Exchange.SelectMedia(Lib.BuyNow2);  
			 Exchange.SelectFormat("CD101V");
			 Exchange.SelectFormat("CD102VL");
			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.ClickAMediaTile(Lib.BuyNow2);
			 Exchange.AddToMyLots(D.Pagina45FullPage);
			 Exchange.AddToMyLots(D.Pagina3HalfLying);			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SelectALot(D.Pagina45FullPage);
		     Mylots.SelectALot(D.Pagina3HalfLying);
		     Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
		     Lib.CloseDialogBox();
	}	  
	@AfterClass
	public void stop() throws InterruptedException {
			 Top.Logout(); 
			 Top.CloseBrowser();
	} 
}
