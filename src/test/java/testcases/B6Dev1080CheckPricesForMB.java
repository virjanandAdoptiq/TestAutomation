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
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"B6"}, dependsOnGroups="B5", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class B6Dev1080CheckPricesForMB {	
	  String media = Lib.BuyNow;
	  String format = "CD101V";
	  String product = "CD101V - Cover 2";
	  String theDay = Lib.buyDay1;
	
	  @BeforeClass
	  public void start() throws InterruptedException{
				Top.StartBroswer();
				Top.Login(Lib.MB,"Welkom01@1");
				D.FAILURE_INDICATION = 0; //if softAssertionAll failed, do not logout close browser
	  }
	  
	  public void addToMyLots() throws InterruptedException{
		     Exchange.GotoBuyerEchangePage();
		     Exchange.SelectCampaign("");
			 Exchange.EnterFromThroughDate(theDay);
			 Exchange.SelectFormat(format);			 
			 Exchange.SelectMedia(media);	
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);  //Go to My Lots 
			 
	  }
	  
		  @Test(dataProvider="inputData", dependsOnMethods="addToMyLots",alwaysRun = true)
		  public void checkPrices(String publisher, String campaign,
				             String rateCardPriceE, String buyNowPriceE, 
				             String crossPriceE, String contractDeductedPriceE, String volumeDedactedPriceE, 
				             String contractPercentageE, String volumePercentageE, String surchargePriceE,
				             String finalPriceE) throws InterruptedException {	

			     SoftAssert softAssert = new SoftAssert();
				 
			     Mylots.ExpandALot(product,"grey");
			     Mylots.SelectPublisher(product,publisher);
			     Mylots.SetCampaign(product, campaign);
			     
				 Mylots.OpenLotInfon(product);
				 //check data in the lot info page
				 String rateCardPrice =  Mylots.GetLotInfo(D.$bm_lot_info_rateCardPrice);
				 softAssert.assertEquals(rateCardPrice, rateCardPriceE);
				 String buyNowPrice =  Mylots.GetLotInfo(D.$bm_lot_info_buyNowPrice);
				 softAssert.assertEquals(buyNowPrice, buyNowPriceE);
				 String crossPrice =  Mylots.GetLotInfo(D.$bm_lot_info_crossPrice);
				 softAssert.assertEquals(crossPrice, crossPriceE);
				 String contractDeductedPrice =  Mylots.GetLotInfo(D.$bm_lot_info_contractDeductionPrice);
				 softAssert.assertEquals(contractDeductedPrice, contractDeductedPriceE);
				 String volumeDeductedPrice =  Mylots.GetLotInfo(D.$bm_lot_info_volumeDeductionPrice);
				 softAssert.assertEquals(volumeDeductedPrice, volumeDedactedPriceE);
				 String contractPercentage =  Mylots.GetLotInfo(D.$bm_lot_info_contractPercentage);
				 softAssert.assertEquals(contractPercentage, contractPercentageE);
				 String volumePercentage =  Mylots.GetLotInfo(D.$bm_lot_info_volumePercentage);
				 softAssert.assertEquals(volumePercentage, volumePercentageE);
				 String surchargePrice =  Mylots.GetLotInfo(D.$bm_lot_info_surchargePrice);
				 softAssert.assertEquals(surchargePrice, surchargePriceE);
				 String finalPrice =  Mylots.GetLotInfo(D.$bm_lot_info_finalPrice);
				 softAssert.assertEquals(finalPrice, finalPriceE);
				 
				 Mylots.CloseLotInfo();
				 //check bid price on the lot row
				 String lotBuyNowPrice =  Mylots.CheckLotRowPrice(product,D.$bm_lot_buyNowPrice);
				 softAssert.assertEquals(lotBuyNowPrice, finalPriceE); 
				 			
				 softAssert.assertAll(); 						 			 	  
	         }	
			 @DataProvider
			  public Object[][] inputData() {
			    return new Object[][] { 	
			   //publisher,campaign,rateCardPrice,buyNowPrice,crossPrice,contractDeductedPrice,volumeDedactedPrice,contract,volume,surchargePrice,finalPrice 
	       		{Lib.UG,"1","6.300,00","3.150,00","3.150,00","1.575,00","0,00","50,00","0,00","0,00","1.575,00"},
				{Lib.UG,"2","6.300,00","3.150,00","6.300,00","3.150,00","0,00","50,00","0,00","0,00","3.150,00"},
			    {Lib.Res,"1","5.000,00","5.500,00","5.500,00","2.750,00","687,50","50,00","25,00","0,00","2.062,50"},
			    {Lib.Res,"2","5.000,00","5.500,00","5.000,00","2.500,00","625,00","50,00","25,00","0,00","1.875,00"},
			    };
			  }
	@Test(dependsOnMethods="checkPrices",alwaysRun = true)		 
	public void deleteLots() throws InterruptedException{
		//Delete it from My lot
		 Mylots.SelectALot(product);
		 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
		 Lib.CloseDialogBox();
	}

	@AfterClass
	public void stop() throws InterruptedException {
			    Top.Logout(); 
				Top.CloseBrowser();
	} 
}
