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
import toplevel.D;
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"Buy"}, dependsOnGroups="CheckPriceMB", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class A4Dev1101CampaignBuy {
	  	  
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
				Top.Login(Helper.MB,"Welkom01@1");
				D.FAILURE_INDICATION = 0; //if test failed, do nothing
			}
	  
	  @Test(dataProvider="inputdata", alwaysRun = true)
	  public void SelectCmpBuyOne(String campaign, String media, String format, 
			                        String theDate, String product,
                                    String finalPriceE) throws InterruptedException {	
		  
		     SoftAssert softAssert = new SoftAssert();
		     
		     //inventory search
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(campaign);			 
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectFormat(format);
			 Exchange.SelectMedia(media);
			 Top.ClickButton(By.cssSelector(D.$be_execute));		
			 Exchange.AddToMyLots(product);

			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); //Go to My Lots
			 //do not show orders
			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();

			 //check buy now price on the lot row
			 String lotBuyNowPrice =  Mylots.GetLotInfo(D.$bm_lot_buyNowPrice);
			 softAssert.assertEquals(lotBuyNowPrice, finalPriceE);
			 
			 //Buy it from My lot
			 Mylots.SelectALot(media);
			 Top.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
			 
			 //Clean up the campaign
			 Exchange.SelectCampaign("");
			  			 		 
			 softAssert.assertAll();
						     						 			 
	  }	
	  @DataProvider
	  public Object[][] inputdata() {
	    return new Object[][] { 
	      {Helper.CampaignADV,Helper.BuyNow,"CD101V",Helper.noTuesday,"CD101V - Cover 2","1.575,00"},
	    };
	  }
	  @Test(dataProvider="inputdata2", alwaysRun = true)
	  public void SelectCmpBuyTwoTogether(String campaign, String media, String format, String format2,
			                        String theDate, String product, String product2
                                   ) throws InterruptedException {	
		     
		     //inventory search
			 Exchange.GotoBuyerEchangePage();
			 Exchange.SelectCampaign(campaign);
			 Exchange.SelectMedia(media);	
			 Top.ClickButton(By.cssSelector(D.$be_execute));	//sometimes doesn't work
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectFormat(format);
			 Exchange.SelectFormat(format2);
			 Top.ClickButton(By.cssSelector(D.$be_execute));		
			 Exchange.AddToMyLots(product);
			 Exchange.AddToMyLots(product2);

			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); //Go to My Lots
			 //do not show orders
			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();

			 
			 //Buy it from My lot
			 Mylots.SelectALot(product);
			 Mylots.SelectALot(product2);
			 //Mylots.ClickBuyBidOptionLot(D.$bm_lot_buy_icon); 
			 Top.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);
			 
			 //Clean up the campaign
			 Exchange.SelectCampaign("");
						     						 			 
	  }
	  @DataProvider
	  public Object[][] inputdata2() {
	    return new Object[][] { 
	      {Helper.CampaignADV2,Helper.BuyNow2,"CD101V","CD102VS", Helper.buyDay1,"CD101V - Pagina 2","CD102VS - Pagina 3"},
	    };
	  }

	  @AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 

	
		 
}
