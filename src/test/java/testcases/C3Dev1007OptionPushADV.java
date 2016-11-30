package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import publisher.ExchangeP;
import publisher.Media;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"C3"}, dependsOnGroups="C2", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class C3Dev1007OptionPushADV {	
	  String product = "CD102VL - Voorpagina";
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}
	  
	  @Test
	  public void optionPush() throws InterruptedException {			  
			 
			 Top.Login(Lib.UG2,"Welkom01@1");
			 String menu = D.$Menu + D.$MenuMedia + ")]";
			 Lib.ClickButton(By.xpath(menu));
	 		 ExchangeP.SelectLeftMenu("Inventory");
			 Exchange.SelectMedia(Lib.BuyNow2);
			 Exchange.EnterFromThroughDate(Lib.buyDay1);		 
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 ExchangeP.SelectRowOverViewTable(product);
			 Lib.ClickButton(By.cssSelector(D.$p_option_push));
			 Media.PushOptionToADVOnly(Lib.ADV, "1", "ID1", "123");
			 Top.Logout(); 
	
	  }	 			 

	  @Test(dependsOnMethods="optionPush", alwaysRun = true)
	  public void offerScreen() throws InterruptedException{		  
		  Top.Login(Lib.ADV,"Welkom01@1");
		  Exchange.ClickOfferNotification();
		  Exchange.SelectAInventory(product);
		  Lib.ClickButton(By.cssSelector(D.$be_inventory_add_all));
		  D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(D.$OK_Button)));
		  Lib.CloseDialogBox();

		  Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertTrue(Mylots.CheckLotStatus(product, D.$bm_lot_status_option));
		
		  //delete one
		  Mylots.SelectALot(product);
		  Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
		  Lib.CloseDialogBox();
	
		  Top.Logout();
		  D.FAILURE_INDICATION = 0;
		  softAssert.assertAll();
	  }	

	  @AfterClass
		public void stop() throws InterruptedException { 
			Top.CloseBrowser();
		} 

		 
}
