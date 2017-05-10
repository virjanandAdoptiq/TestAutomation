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


@Test//(groups = {"C1"}, dependsOnGroups="Ba3", alwaysRun = true)
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
	  public void optionRequestAccept() throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();
		     D.FAILURE_INDICATION = 1; 
			 Top.Login(buyer,"Welkom01@1");	
			 Exchange.GotoBuyerEchangePageTileView();
			 Exchange.ClickAMediaTile(media);
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
			 Mylots.ExpandAGroup("Optie");
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1,D.$bm_lot_status_option_requested));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2,D.$bm_lot_status_option_requested));
			 Top.Logout();  		 
			 Top.Login(seller,"Welkom01@1");
				 
			 ExchangeP.GoToExchangePlatform();
			 ExchangeP.ExpandANegotiationy("5.500,00");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_accept_button));
			 ExchangeP.AproveOption("1");
			 		 
			 Top.Logout(); 

			 Top.Login(buyer,"Welkom01@1");
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 			 
			 Mylots.ExpandAGroup("Optie");
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1, D.$bm_lot_status_option),"Not in option status:" + product1);
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2, D.$bm_lot_status_option),"Not in option status: " + product2);			 
			 Mylots.SelectAGroup("Optie");	
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();
			 Mylots.ExpandAGroup("Optie");
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1,D.$bm_lot_status_option_cancelled));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2,D.$bm_lot_status_option_cancelled));
			 Mylots.SelectAGroup("Optie");
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();
			 Top.Logout();			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();		     						 			 
	  }	
	  @Test(dependsOnMethods="optionRequestAccept")
	  public void optionRequestReject() throws InterruptedException {			  
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
			 Mylots.ExpandAGroup("Optie");
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1,D.$bm_lot_status_option_requested));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2,D.$bm_lot_status_option_requested));
			 Top.Logout();  		 
			 Top.Login(seller,"Welkom01@1");
		 
			 ExchangeP.GoToExchangePlatform();
			 ExchangeP.ExpandANegotiationy("5.500,00");
			 Lib.ClickButton(By.xpath(D.$p_negotiation_reject_button));
			 Lib.CloseDialogBox();
			 
			 Top.Logout(); 

			 Top.Login(buyer,"Welkom01@1");
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 		
			 Mylots.ExpandAGroup("Optie");
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product1,D.$bm_lot_status_option_cancelled));
			 softAssert.assertTrue(Mylots.CheckGroupLotStatus(product2,D.$bm_lot_status_option_cancelled));
	         
			 Mylots.SelectAGroup("Optie");
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();

			 Top.Logout();			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();		     						 			 
	  }	
	  @Test(dependsOnMethods="optionRequestReject")
	  public static void checkEmail() throws InterruptedException{
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(Lib.checkEmails("C1Dev1002OptionRequestAcceptRejectADV", 16), "emailCorrect");				
			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();			  
	  }
	  @AfterClass
		public void stop() throws InterruptedException {
		  Top.CloseBrowser();
		} 		 
}
