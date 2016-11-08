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


@Test(groups = {"Option"}, dependsOnGroups="CheckPrivateOrder", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class B1Dev1121OptionRequestAcceptReject {		  
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}
	  
	  @Test(dataProvider="inputdata",alwaysRun = true)
	  public void optionRequestAcceptReject(String pubAction, String seller, String buyer, String campaign, String media, String format, 
			                        String theDate, String product) throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();
		     D.FAILURE_INDICATION = 1; //if test failed, logout 
//Request Option
			 Top.Login(buyer,"Welkom01@1");	
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(campaign);
			 Exchange.SelectFormat(format);
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectMedia(media);
			 Top.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product);  
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 

			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();			 

			 Mylots.SelectALot(product);
			 Top.ClickButton(By.cssSelector(D.$bm_lot_option_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_option_confirm);       

			 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_option_requested));
			 Top.Logout();  

//Accept or reject the requested Option			 
			 Top.Login(seller,"Welkom01@1");
			 String menu = D.$Menu + D.$MenuExchange + ")]";
			 Top.ClickButton(By.xpath(menu));
			// Top.ClickButton(By.xpath(D.$Exchange));
			 ExchangeP.SelectLeftMenu("Optie overzicht");
			 ExchangeP.SelectRowOverViewTable(product);
			 if(pubAction.equalsIgnoreCase("accept")){				 
				 Top.ClickButton(By.cssSelector(D.$p_option_approve));
				 ExchangeP.AproveOption("1");		
			 } else {
				 Top.ClickButton(By.cssSelector(D.$p_option_reject));
				 ExchangeP.RejectOption();
			 }				 
			 Top.Logout(); 
//Check if Lots status in Buyer side is changed correctly
			 Top.Login(buyer,"Welkom01@1");
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 if(pubAction.equalsIgnoreCase("accept")){	
				 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_option));
			 } else {
				 softAssert.assertTrue(Mylots.CheckLotStatus(product,D.$bm_lot_status_saved));
			 }
			 
			 Mylots.SelectALot(product);
			 Top.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 Top.CloseDialogBox();
			 Top.Logout();
			 
			 D.FAILURE_INDICATION = 0; //if test failed, do nothing
			 softAssert.assertAll();
			 D.FAILURE_INDICATION = 1; //if test failed, logout 			     						 			 
	  }	
	  @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		} 

	//THE PARAMETERS ARE:
		 @DataProvider
		  public Object[][] inputdata() {
		    return new Object[][] { 
		      {"accept",Helper.Res2,Helper.MB,Helper.CampaignADV2,Helper.BuyNow2,"CD101V",Helper.buyDay1,"CD101V - Pagina 4-5"},
		      {"reject",Helper.UG,Helper.MB,Helper.CampaignADV,Helper.BuyNow,"CD101V",Helper.noTuesday,"CD101V - Cover 3"},
		    };
		  }
}
