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
      @Test
      public void addLMToMyLots() throws InterruptedException{
        	 Exchange.GotoBuyerEchangePage();
			 Exchange.EnterFromThroughDate(Lib.lmDay1);
			 Exchange.SelectMedia(Lib.BuyNow);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(D.Cover2FullPage);
			 Exchange.AddToMyLots(D.Cover3HalfLying);
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);  
         }
		 @Test(dataProvider="inputData1", dependsOnMethods="addLMToMyLots",alwaysRun = true)
		 public void checkLMPrices(String bidPriceE, String lmPriceE,
				             String product,
				             String rateCardPriceE, String buyNowPriceE, 
				             String crossPriceE, String contractDeductedPriceE, String volumeDedactedPriceE, 
				             String contractPercentageE, String volumePercentageE, String surchargePriceE,
				             String finalPriceE) throws InterruptedException {	

			     SoftAssert softAssert = new SoftAssert();
				 	
				 Mylots.OpenLotInfon(product);
				 //check data in the lot info page
				 String rateCardPrice =  Mylots.GetLotInfo(D.$bm_lot_info_rateCardPrice);
				 softAssert.assertEquals(rateCardPrice, rateCardPriceE);
				 String buyNowPrice =  Mylots.GetLotInfo(D.$bm_lot_info_buyNowPrice);
				 softAssert.assertEquals(buyNowPrice, buyNowPriceE);
				 String bidPrice =  Mylots.GetLotInfo(D.$bm_lot_info_bidPrice);
				 softAssert.assertEquals(bidPrice, bidPriceE);
				 String lmPrice =  Mylots.GetLotInfo(D.$bm_lot_info_lastMinutePrice);
				 softAssert.assertEquals(lmPrice, lmPriceE);
				 
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
				 String lotBidPrice =  Mylots.CheckLotRowPrice(product,D.$bm_lot_bidPrice);
				 softAssert.assertEquals(lotBidPrice, lmPriceE);
			 
				 //Delete it from My lot
				 Mylots.SelectALot(product);
				 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
				 			
				 softAssert.assertAll(); 						 			 	  
	         }	
		  @DataProvider
		  public Object[][] inputData1() {
		    return new Object[][] { 	
		    {"5.000,00","4.500,00",D.Cover2FullPage,"5.000,00","5.500,00","5.500,00","2.750,00","687,50","50,00","25,00","0,00","2.062,50"},
		    {"2.500,00","2.250,00",D.Cover3HalfLying,"2.500,00","2.750,00","2.750,00","1.375,00","343,75","50,00","25,00","0,00","1.031,25"},
		    };
		  }			
		  @Test(dependsOnMethods="checkLMPrices",alwaysRun = true)
	         public void addBidToMyLots() throws InterruptedException{
	        	 Exchange.GotoBuyerEchangePage();
				 Exchange.EnterFromThroughDate(Lib.bidDay1);
				 Exchange.SelectMedia(Lib.BuyNow);
				 Lib.ClickButton(By.cssSelector(D.$be_execute));
				 Exchange.AddToMyLots(D.Cover2FullPage);
				 Exchange.AddToMyLots(D.Cover3HalfLying);
				 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);  
	         }
			 @Test(dataProvider="inputData2", dependsOnMethods="addBidToMyLots", alwaysRun = true)
			 public void checkBidPrices(String bidPriceE, String product,
					             String rateCardPriceE, String buyNowPriceE, 
					             String crossPriceE, String contractDeductedPriceE, String volumeDedactedPriceE, 
					             String contractPercentageE, String volumePercentageE, String surchargePriceE,
					             String finalPriceE) throws InterruptedException {
				 SoftAssert softAssert = new SoftAssert();
				 
				 Mylots.OpenLotInfon(product);
				 //check data in the lot info page
				 String rateCardPrice =  Mylots.GetLotInfo(D.$bm_lot_info_rateCardPrice);
				 softAssert.assertEquals(rateCardPrice, rateCardPriceE);
				 String buyNowPrice =  Mylots.GetLotInfo(D.$bm_lot_info_buyNowPrice);
				 softAssert.assertEquals(buyNowPrice, buyNowPriceE);
				 String bidPrice =  Mylots.GetLotInfo(D.$bm_lot_info_bidPrice);
				 softAssert.assertEquals(bidPrice, bidPriceE);
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

				String lotBidPrice =  Mylots.CheckLotRowPrice(product,D.$bm_lot_bidPrice);
				softAssert.assertEquals(lotBidPrice, bidPriceE);
				
				 //Delete it from My lot
				 Mylots.SelectALot(product);
				 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
				 			
				 softAssert.assertAll(); 						 			 	  	        				 
			 }
			  @DataProvider
			  public Object[][] inputData2() {
			    return new Object[][] { 	
			    {"5.000,00",D.Cover2FullPage,"5.000,00","5.500,00","5.500,00","2.750,00","687,50","50,00","25,00","0,00","2.062,50"},		        
				{"2.500,00",D.Cover3HalfLying,"2.500,00","2.750,00","2.750,00","1.375,00","343,75","50,00","25,00","0,00","1.031,25"},
        	    };
			  }	
			  @Test(dependsOnMethods="checkBidPrices",alwaysRun = true)
		         public void addBuyNowToMyLots() throws InterruptedException{
		        	 Exchange.GotoBuyerEchangePage();
					 Exchange.EnterFromThroughDate(Lib.buyDay1);
					 Exchange.SelectMedia(Lib.BuyNow);
					 Lib.ClickButton(By.cssSelector(D.$be_execute));
					 Exchange.AddToMyLots(D.Cover2FullPage);
					 Exchange.AddToMyLots(D.Cover3FullPage);
					 Exchange.AddToMyLots(D.Cover2HalfStand);
					 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);  
		         }
				 @Test(dataProvider="inputData3", dependsOnMethods="addBuyNowToMyLots",alwaysRun = true)
				 public void checkBuyNowPrices(
						             String product,
						             String rateCardPriceE, String buyNowPriceE, 
						             String crossPriceE, String contractDeductedPriceE, String volumeDedactedPriceE, 
						             String contractPercentageE, String volumePercentageE, String surchargePriceE,
						             String finalPriceE) throws InterruptedException {
					 SoftAssert softAssert = new SoftAssert();
					 
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
					 String lotBuyNowPrice =  Mylots.CheckLotRowPrice(product,D.$bm_lot_buyNowPrice);
					 softAssert.assertEquals(lotBuyNowPrice, finalPriceE); 
					 			 
					 //Delete it from My lot
					 Mylots.SelectALot(product);
					 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
					 			
					 softAssert.assertAll(); 						 			 	  
 
				 }
				  @DataProvider
				  public Object[][] inputData3() {
				    return new Object[][] { 	
					{D.Cover2FullPage,"6.300,00","3.150,00","3.150,00","1.575,00","0,00","50,00","0,00","0,00","1.575,00"},
					{D.Cover3FullPage,"9.000,00","4.500,00","4.500,00","2.250,00","0,00","50,00","0,00","0,00","2.250,00"},		      
					{D.Cover2HalfStand,"2.500,00","2.750,00","2.750,00","1.375,00","343,75","50,00","25,00","0,00","1.031,25"},			    
				        	    };
				  }	
			  
				  @Test(dependsOnMethods="checkBuyNowPrices",alwaysRun = true)
			         public void addWeekDayToMyLots() throws InterruptedException{
			        	 Exchange.GotoBuyerEchangePage();
						 Exchange.EnterFromThroughDate(Lib.weekDay);
						 Exchange.SelectMedia(Lib.BuyNow);
						 Lib.ClickButton(By.cssSelector(D.$be_execute));
						 Exchange.AddToMyLots(D.Cover2FullPage);
						 Exchange.AddToMyLots(D.Cover3FullPage);
						 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);  
			         }
					 @Test(dataProvider="inputData4", dependsOnMethods="addWeekDayToMyLots",alwaysRun = true)
					 public void checkWeekDayPrices(
							             String product,
							             String rateCardPriceE, String buyNowPriceE, 
							             String crossPriceE, String contractDeductedPriceE, String volumeDedactedPriceE, 
							             String contractPercentageE, String volumePercentageE, String surchargePriceE,
							             String finalPriceE) throws InterruptedException {
						 SoftAssert softAssert = new SoftAssert();
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
						 			 
						 //Delete it from My lot
						 Mylots.SelectALot(product);
						 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
						 			
						 softAssert.assertAll(); 						 			 	  
	 
					 }
					  @DataProvider
					  public Object[][] inputData4() {
					    return new Object[][] { 
							{D.Cover2FullPage,"3.780,00","1.890,00","1.890,00","945,00","0,00","50,00","0,00","0,00","945,00"},
							{D.Cover3FullPage,"5.400,00","2.700,00","2.700,00","1.350,00","0,00","50,00","0,00","0,00","1.350,00"},		  								  
	        	           };
                      }
				
		 @Test(dependsOnMethods="checkWeekDayPrices",alwaysRun = true)
		 public void addAnotherSaleOrgToMyLots() throws InterruptedException{
				        	 Exchange.GotoBuyerEchangePage();
							 Exchange.EnterFromThroughDate(Lib.buyDay1);
							 Exchange.SelectMedia(Lib.BuyNow);
							 Lib.ClickButton(By.cssSelector(D.$be_execute));
							 Exchange.AddToMyLots(D.Cover2FullPage);
							 Exchange.AddToMyLots(D.Cover2HalfStand);
							 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);  
		  }	  
		  @Test(dataProvider="inputData5", dependsOnMethods="addAnotherSaleOrgToMyLots",alwaysRun = true)
		  public void checkAnotherSaleOrgPrice(String publisher, String product,
				             String rateCardPriceE, String buyNowPriceE, 
				             String crossPriceE, String contractDeductedPriceE, String volumeDedactedPriceE, 
				             String contractPercentageE, String volumePercentageE, String surchargePriceE,
				             String finalPriceE) throws InterruptedException {	

			     SoftAssert softAssert = new SoftAssert(); 
				 
				 Mylots.ExpandALot(product,"grey");
				 Mylots.SelectPublisher(product,publisher);
				 
				 
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
				 String lotBuyNowPrice =  Mylots.CheckLotRowPrice(product,D.$bm_lot_buyNowPrice);
				 softAssert.assertEquals(lotBuyNowPrice, finalPriceE); 
				 			 
				 //Delete it from My lot
				 Mylots.SelectALot(product);
				 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
				 			
				 softAssert.assertAll(); 						 			 	  
	         }


			 @DataProvider
			  public Object[][] inputData5() {
			    return new Object[][] { 	
				{Lib.Res,D.Cover2FullPage,"5.000,00","5.500,00","5.500,00","2.750,00","687,50","50,00","25,00","0,00","2.062,50"},
			    {Lib.UG,D.Cover2HalfStand,"5.040,00","2.520,00","2.520,00","1.260,00","0,00","50,00","0,00","0,00","1.260,00"},

			    };
			  }

	@AfterClass
	public void stop() throws InterruptedException {
			    Top.Logout(); 
				Top.CloseBrowser();
	} 
}
