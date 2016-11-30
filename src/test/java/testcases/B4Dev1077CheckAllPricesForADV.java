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


@Test(groups = {"B4"}, dependsOnGroups="B3", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class B4Dev1077CheckAllPricesForADV {		  
	  @BeforeClass
	  public void start() throws InterruptedException{
				Top.StartBroswer();
				Top.Login(Lib.ADV,"Welkom01@1");
				D.FAILURE_INDICATION = 0; //if softAssertionAll failed, do not logout close browser
	  }
	  
		  @Test(dataProvider="inputData", alwaysRun = true)
		  public void checkAllPrices(String which, String publisher, String bidPriceE, String lmPriceE,
				             String media, String format, String theDate, String product,
				             String rateCardPriceE, String buyNowPriceE, 
				             String crossPriceE, String contractDeductedPriceE, String volumeDedactedPriceE, 
				             String contractPercentageE, String volumePercentageE, String surchargePriceE,
				             String finalPriceE) throws InterruptedException {	

			     SoftAssert softAssert = new SoftAssert();

			     //inventory search
				 Exchange.GotoBuyerEchangePage();
				 Exchange.EnterFromThroughDate(theDate);
				 Exchange.SelectFormat(format);
				 Exchange.SelectMedia(media);
				 Lib.ClickButton(By.cssSelector(D.$be_execute));
				 Exchange.AddToMyLots(product);
				 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);  //Go to My Lots 
				 
				 if(which.equalsIgnoreCase("AnotherSaleOrg")){
				      Mylots.ExpandALot(media,"grey");
				      Mylots.SelectPublisher(product,publisher);
				 }
				 
				 Mylots.OpenLotInfon(product);
				 //check data in the lot info page
				 String rateCardPrice =  Mylots.GetLotInfo(D.$bm_lot_info_rateCardPrice);
				 softAssert.assertEquals(rateCardPrice, rateCardPriceE);
				 String buyNowPrice =  Mylots.GetLotInfo(D.$bm_lot_info_buyNowPrice);
				 softAssert.assertEquals(buyNowPrice, buyNowPriceE);
				 if(which.equalsIgnoreCase("lastMinute") || which.equalsIgnoreCase("bid")){
				     String bidPrice =  Mylots.GetLotInfo(D.$bm_lot_info_bidPrice);
				     softAssert.assertEquals(bidPrice, bidPriceE);
				 }
				 if(which.equalsIgnoreCase("lastMinute")){
				    String lmPrice =  Mylots.GetLotInfo(D.$bm_lot_info_lastMinutePrice);
				    softAssert.assertEquals(lmPrice, lmPriceE);
				 }
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
				 if(which.equalsIgnoreCase("bid")){
					String lotBidPrice =  Mylots.CheckLotRowPrice(product,D.$bm_lot_bidPrice);
					softAssert.assertEquals(lotBidPrice, bidPriceE);
				 } 
				 else if(which.equalsIgnoreCase("lastMinute")){
				    String lotBidPrice =  Mylots.CheckLotRowPrice(product,D.$bm_lot_bidPrice);
				    softAssert.assertEquals(lotBidPrice, lmPriceE);
				 } else if(theDate.equals(Lib.buyDay1)) {                            //for Tuesday it might be in bid phase.
					 String lotBuyNowPrice =  Mylots.CheckLotRowPrice(product,D.$bm_lot_buyNowPrice);
					 softAssert.assertEquals(lotBuyNowPrice, finalPriceE); 
				 }				 
				 //Delete it from My lot
				 Mylots.SelectALot(media);
				 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
				 			
				 softAssert.assertAll(); 						 			 	  
	         }	

			 @DataProvider
			  public Object[][] inputData() {
			    return new Object[][] { 	
			   //which, publisher,bidPrice,lmPrice,media,format, theDate, product,rateCardPrice,buyNowPrice,crossPrice,contractDeductedPrice,volumeDedactedPrice,contract,volume,surchargePrice,finalPrice 
			    {"lastMinute","defaultSale","5.000,00","4.500,00",Lib.BuyNow,"CD101V",Lib.lmDay1,"CD101V - Cover 2","5.000,00","5.500,00","5.500,00","2.750,00","687,50","50,00","25,00","0,00","2.062,50"},
			    {"lastMinute","defaultSale","2.500,00","2.250,00",Lib.BuyNow,"CD102VL",Lib.lmDay1,"CD102VL - Cover 3","2.500,00","2.750,00","2.750,00","1.375,00","343,75","50,00","25,00","0,00","1.031,25"},
			    {"bid","defaultSale","5.000,00","NoLM",Lib.BuyNow,"CD101V",Lib.bidDay1,"CD101V - Cover 2","5.000,00","5.500,00","5.500,00","2.750,00","687,50","50,00","25,00","0,00","2.062,50"},		        
			    {"bid","defaultSale","2.500,00","NoLM",Lib.BuyNow,"CD102VL",Lib.bidDay1,"CD102VL - Cover 3","2.500,00","2.750,00","2.750,00","1.375,00","343,75","50,00","25,00","0,00","1.031,25"},
				{"DefaultBuyNow","defaultSale","NoBid","NoLM",Lib.BuyNow,"CD101V",Lib.buyDay1,"CD101V - Cover 2","6.300,00","3.150,00","3.150,00","1.575,00","0,00","50,00","0,00","0,00","1.575,00"},
				{"DefaultBuyNow","defaultSale","NoBid","NoLM",Lib.BuyNow,"CD101V",Lib.buyDay1,"CD101V - Cover 3","9.000,00","4.500,00","4.500,00","2.250,00","0,00","50,00","0,00","0,00","2.250,00"},		      
				{"DefaultBuyNow","defaultSale","NoBid","NoLM",Lib.BuyNow,"CD102VS",Lib.buyDay1,"CD102VS - Cover 2","5.040,00","2.520,00","2.520,00","1.260,00","0,00","50,00","0,00","0,00","1.260,00"},
				{"DefaultBuyNow","defaultSale","NoBid","NoLM",Lib.BuyNow,"CD101V",Lib.weekDay,"CD101V - Cover 2","3.780,00","1.890,00","1.890,00","945,00","0,00","50,00","0,00","0,00","945,00"},
				//DEV-1320  (Either exchange not correct or My Lots not correct) The diff comes from Tuesday is bid or buy. Need more program to check this.
				{"DefaultBuyNow","defaultSale","NoBid","NoLM",Lib.BuyNow,"CD101V",Lib.weekDay,"CD101V - Cover 3","5.400,00","2.700,00","2.700,00","1.350,00","0,00","50,00","0,00","0,00","1.350,00"},		  		
				{"AnotherSaleOrg",Lib.Res,"NoBid","NoLM",Lib.BuyNow,"CD101V",Lib.buyDay1,"CD101V - Cover 2","5.000,00","5.500,00","5.500,00","2.750,00","687,50","50,00","25,00","0,00","2.062,50"},
			    {"AnotherSaleOrg",Lib.UG,"NoBid","NoLM",Lib.BuyNow,"CD102VS",Lib.buyDay1,"CD102VS - Cover 2","5.040,00","2.520,00","2.520,00","1.260,00","0,00","50,00","0,00","0,00","1.260,00"},

			    };
			  }

	@AfterClass
	public void stop() throws InterruptedException {
			    Top.Logout(); 
				Top.CloseBrowser();
	} 
}
