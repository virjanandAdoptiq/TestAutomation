package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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


@Test(groups = {"C1"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class C1Dev1002OptionRequestAcceptRejectADV {	
	  String product1 = "CD102VS - Voorpagina";
	  String product2 = "CD102VL - Voorpagina";
	  String media = Lib.BuyNow2;
	  String theDay = Lib.weekDay;
	  String buyer = Lib.ADV;
	  String seller = Lib.Res2;
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}
	  
	  @Test
	  public void optionRequestAcceptReject() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();
		     D.FAILURE_INDICATION = 1; 

			 Top.Login(buyer,"Welkom01@1");	
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.EnterFromThroughDate(theDay);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product1);  
			 Exchange.AddToMyLots(product2);
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product1);
			 Mylots.SelectALot(product2);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_option_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_option_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product1,D.$bm_lot_status_option_requested));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product2,D.$bm_lot_status_option_requested));
			 Top.Logout();  
		 
			 Top.Login(seller,"Welkom01@1");
			 String menu = D.$Menu + D.$MenuExchange + ")]";
			 Lib.ClickButton(By.xpath(menu));
			 ExchangeP.SelectLeftMenu("Optie overzicht");
			 ExchangeP.SelectRowOverViewTable(product1);				 
			 Lib.ClickButton(By.cssSelector(D.$p_option_approve));
			 ExchangeP.AproveOption("1");		
			 ExchangeP.SelectRowOverViewTable(product2);
			 Lib.ClickButton(By.cssSelector(D.$p_option_reject));
			 ExchangeP.RejectOption();
			 
			 Top.Logout(); 

			 Top.Login(buyer,"Welkom01@1");
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 softAssert.assertTrue(Mylots.CheckLotStatus(product1,D.$bm_lot_status_option));
			 softAssert.assertTrue(Mylots.CheckLotStatus(product2,D.$bm_lot_status_saved));

			 
			 Mylots.SelectALot(product1);
			 Mylots.SelectALot(product2);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();
			 Top.Logout();
			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; 			     						 			 
	  }	
	  @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		} 		 
}
