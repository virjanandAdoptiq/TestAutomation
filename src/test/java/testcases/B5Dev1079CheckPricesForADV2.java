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


@Test(groups = {"B5"}, dependsOnGroups="B4", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class B5Dev1079CheckPricesForADV2 {		  
	  @BeforeClass
	  public void start() throws InterruptedException{
				Top.StartBroswer();
				Top.Login(Lib.ADV2,"Welkom01@1");
				D.FAILURE_INDICATION = 0; //if softAssertionAll failed, do not logout close browser
	  }
	  @Test(alwaysRun = true)
	  public void addToMyLots() throws InterruptedException{
		     Exchange.GotoBuyerEchangePage();
		     Exchange.SelectMedia(Lib.BuyNow2);
		     Lib.ClickButton(By.cssSelector(D.$be_execute));  //sometimes select format does't work, so put this here
			 Exchange.SelectFormat("CD101V");
			 Exchange.SelectFormat("CD102VL");
			 Exchange.EnterFromThroughDate(Lib.buyDay1);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(D.Pagina45FullPage);
			 Exchange.AddToMyLots(D.Pagina3HalfLying);
			 
			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots);  //Go to My Lots 
			 
	  }
	  
		  @Test(dataProvider="inputData", dependsOnMethods="addToMyLots",alwaysRun = true)
		  public void checkPrices(String which, String publisher, 
				             String product,
				             String rateCardPriceE, String buyNowPriceE, 
				             String crossPriceE, String contractDeductedPriceE, String volumeDedactedPriceE, 
				             String contractPercentageE, String volumePercentageE, String surchargePriceE,
				             String finalPriceE) throws InterruptedException {	

			     SoftAssert softAssert = new SoftAssert();
				 
				 if(which.equalsIgnoreCase("AnotherSaleOrg")){
				      Mylots.ExpandALot(product,"grey");
				      Mylots.SelectPublisher(product,publisher);
				 }
				 
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
			   //which, publisher,media,format, theDate, product,rateCardPrice,buyNowPrice,crossPrice,contractDeductedPrice,volumeDedactedPrice,contract,volume,surchargePrice,finalPrice 
	       		{"DefaultBuyNow","defaultSale",D.Pagina45FullPage,"5.000,00","5.500,00","5.500,00","0,00","1.375,00","0,00","25,00","0,00","4.125,00"},
				{"DefaultBuyNow","defaultSale",D.Pagina3HalfLying,"2.500,00","2.750,00","2.750,00","0,00","687,50","0,00","25,00","0,00","2.062,50"},
			    {"AnotherSaleOrg",Lib.UG2,D.Pagina45FullPage,"10.000,00","11.000,00","11.000,00","0,00","0,00","0,00","0,00","0,00","11.000,00"},
			    {"AnotherSaleOrg",Lib.UG2,D.Pagina3HalfLying,"5.000,00","5.500,00","5.500,00","0,00","0,00","0,00","0,00","0,00","5.500,00"},

			    };
			  }
	@Test(dependsOnMethods="checkPrices",alwaysRun = true)		 
	public void deleteLots() throws InterruptedException{
		//Delete it from My lot
		 Mylots.SelectALot(D.Pagina45FullPage);
		 Mylots.SelectALot(D.Pagina3HalfLying);
		 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
		 Lib.CloseDialogBox();
	}

	@AfterClass
	public void stop() throws InterruptedException {
			    Top.Logout(); 
				Top.CloseBrowser();
	} 
}
