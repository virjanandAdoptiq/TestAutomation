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


@Test//(groups = {"C1"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class C1Dev1002OptionRequestAcceptRejectADV {	
	  String format1 = "CD102VS";
	  String productP1 = "CD102VS - Voorpagina";
	  String product1 = D.VoorpaginaHalfStand;	
	  String format2 = "CD102VL";
	  String productP2 = "CD102VL - Voorpagina";
	  String product2 = D.VoorpaginaHalfLying;
	  String media = Lib.BuyNow2;
	  String theDay = Lib.weekDay;
	  String buyer = Lib.ADV;
	  String seller = Lib.Res2;
	  @BeforeClass
			public void start() throws InterruptedException{
		        Lib.deleteAllMailsFromInbox();
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
			 Exchange.SelectFormat(format1);
			 Exchange.EnterFromThroughDate(theDay);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product1);
			 Exchange.SelectFormat(format1);
			 Exchange.SelectFormat(format2);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
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
			 ExchangeP.SelectRowOverViewTable(productP1);				 
			 Lib.ClickButton(By.cssSelector(D.$p_option_approve));
			 ExchangeP.AproveOption("1");		
			 ExchangeP.SelectRowOverViewTable(productP2);
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
			 Mylots.SelectALot(product1);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();
			 Top.Logout();
			 Top.CloseBrowser();
			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();		     						 			 
	  }	
	  @Test(dependsOnMethods="optionRequestAcceptReject")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("C1Dev1002OptionRequestAcceptRejectADV", 8), "emailCorrect");				
			softAssert.assertAll(); 		  
	  }
	  @AfterClass
		public void stop() throws InterruptedException {
		  Top.CloseBrowser();
		} 		 
}
