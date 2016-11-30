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
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"C2"}, dependsOnGroups="C1", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class C2Dev1121OptionRequestAcceptRejectMB {		  
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}
	  
	  @Test(dataProvider="inputdata",alwaysRun = true)
	  public void optionRequestAcceptReject(String pubAction, String seller, String buyer, String campaign, String media, String format, 
			                        String theDate, String product) throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();
		     D.FAILURE_INDICATION = 1; 

			 Top.Login(buyer,"Welkom01@1");	
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(campaign);
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.SelectFormat(format);
			 Exchange.EnterFromThroughDate(theDate);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product);  
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_option_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_option_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_option_requested));
			 Top.Logout();  
		 
			 Top.Login(seller,"Welkom01@1");
			 String menu = D.$Menu + D.$MenuExchange + ")]";
			 Lib.ClickButton(By.xpath(menu));
			 ExchangeP.SelectLeftMenu("Optie overzicht");
			 ExchangeP.SelectRowOverViewTable(product);
			 if(pubAction.equalsIgnoreCase("accept")){				 
				 Lib.ClickButton(By.cssSelector(D.$p_option_approve));
				 ExchangeP.AproveOption("1");		
			 } else {
				 Lib.ClickButton(By.cssSelector(D.$p_option_reject));
				 ExchangeP.RejectOption();
			 }				 
			 Top.Logout(); 

			 Top.Login(buyer,"Welkom01@1");
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 if(pubAction.equalsIgnoreCase("accept")){	
				 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_option));
			 } else {
				 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_saved));
			 }
			 
			 Mylots.SelectALot(product);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Lib.CloseDialogBox();
			 Top.Logout();
			 
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; 			     						 			 
	  }	
	  @DataProvider
	  public Object[][] inputdata() {
	    return new Object[][] { 
	      {"accept",Lib.Res2,Lib.MB,Lib.CampaignADV2,Lib.BuyNow2,"CD101V",Lib.buyDay1,"CD101V - Pagina 4-5"},
	      {"reject",Lib.UG,Lib.MB,Lib.CampaignADV,Lib.BuyNow,"CD101V",Lib.buyDay1,"CD101V - Cover 3"},
	    };
	  }
	  @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		} 		 
}
