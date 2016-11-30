package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"B3"}, dependsOnGroups="B2", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class B3Dev998CheckExchangePricesADV2 {	

	  @BeforeClass
	  public void start() throws InterruptedException{
				Top.StartBroswer();
				Top.Login(Lib.ADV2,"Welkom01@1");
				D.FAILURE_INDICATION = 0; //if softAssertionAll failed, do not logout close browser
	  }	
//Media1
	  @Test(alwaysRun = true)
	  public void findLMInventoryForMedia1() throws InterruptedException{
		     Exchange.GotoBuyerEchangePage();
			 Exchange.SelectMedia(Lib.BuyNow);
			 Exchange.EnterFromThroughDate(Lib.lmDay1);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
	  }
	  @Test(dataProvider="inputData", dependsOnMethods="findLMInventoryForMedia1",alwaysRun = true)
	  public void checkLMExchangePrice(boolean BuyPriceHigher,String product, String buyNowE,String buyNowCPME, String bidE,String bidCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();
			 if(BuyPriceHigher){
				 String BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
				 softAssert.assertEquals(BuyNowPrice, buyNowE);
				 String BuyNowCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_cpm_price);
				 softAssert.assertEquals(BuyNowCPM, buyNowCPME);
			 } else {
				 String BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_price);
				 softAssert.assertEquals(BuyNowPrice, buyNowE);
				 String BuyNowCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_cpm_price);
				 softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 
			 }			 
			 String BidPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_bid_price);
			 softAssert.assertEquals(BidPrice, bidE);
			 String BidCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_bid_cpm_price);
			 softAssert.assertEquals(BidCPM, bidCPME);
						 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData() {
	return new Object[][] { 	
		{false,"CD101V - Cover 2","1.875,00","9,38","4.500,00","22,50"},
		{false,"CD102VS - Cover 2","937,50","4,69","2.250,00","11,25"},
		{false,"CD102VL - Cover 2","937,50","4,69","2.250,00","11,25"},
		{false,"CD102VS - Cover 3","937,50","4,69","2.250,00","11,25"},
		{false,"CD102VL - Cover 3","937,50","4,69","2.250,00","11,25"},
		{false,"CD101V - Cover 3","1.875,00","9,38","4.500,00","22,50"},
		};
	}
	 @Test(dependsOnMethods="checkLMExchangePrice",alwaysRun = true)
	 public void findBidInventoryForMedia1() throws InterruptedException{
		 Exchange.EnterFromThroughDate(Lib.bidDay1);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
     }
	 @Test(dataProvider="inputData2", dependsOnMethods="findBidInventoryForMedia1",alwaysRun = true)
	  public void checkBidExchangePrice(boolean BuyPriceHigher,String product, String buyNowE,String buyNowCPME, String bidE,String bidCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();
			 if(BuyPriceHigher){
				 String BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
				 softAssert.assertEquals(BuyNowPrice, buyNowE);
				 String BuyNowCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_cpm_price);
				 softAssert.assertEquals(BuyNowCPM, buyNowCPME);
			 } else {
				 String BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_price);
				 softAssert.assertEquals(BuyNowPrice, buyNowE);
				 String BuyNowCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_cpm_price);
				 softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 
			 }			 
			 String BidPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_bid_price);
			 softAssert.assertEquals(BidPrice, bidE);
			 String BidCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_bid_cpm_price);
			 softAssert.assertEquals(BidCPM, bidCPME);
						 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData2() {
	return new Object[][] { 	
		{false,"CD101V - Cover 2","1.875,00","9,38","5.000,00","25,00"},
		{false,"CD102VS - Cover 2","937,50","4,69","2.500,00","12,50"},
		{false,"CD102VL - Cover 2","937,50","4,69","2.500,00","12,50"},
		{false,"CD102VS - Cover 3","937,50","4,69","2.500,00","12,50"},
		{false,"CD102VL - Cover 3","937,50","4,69","2.500,00","12,50"},
		{false,"CD101V - Cover 3","1.875,00","9,38","5.000,00","25,00"},
		};
	}
	
	 @Test(dependsOnMethods="checkBidExchangePrice",alwaysRun = true)
	 public void findBuyNowInventoryForMedia1() throws InterruptedException{
		 Exchange.EnterFromThroughDate(Lib.buyDay1);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
     }
	 @Test(dataProvider="inputData3", dependsOnMethods="findBuyNowInventoryForMedia1",alwaysRun = true)
	  public void checkBuyNowExchangePrice(String product, String buyNowE,String buyNowCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();

				 String BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_price);
				 if(BuyNowPrice.isEmpty()){
					 BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
				 }
				 softAssert.assertEquals(BuyNowPrice, buyNowE);
				 String BuyNowCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_cpm_price);
				 if(BuyNowCPM.isEmpty()){
					 BuyNowCPM = Exchange.GetAInventoryPrice(product,D.$be_inventory_buynow_bidphase_cpm_price);
				 }
				 softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 

						 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData3() {
	return new Object[][] { 	
		{"CD101V - Cover 2","1.875,00","9,38"},
		{"CD102VS - Cover 2","937,50","4,69"},
		{"CD102VL - Cover 2","937,50","4,69"},
		{"CD102VS - Cover 3","937,50","4,69"},
		{"CD102VL - Cover 3","937,50","4,69"},
		{"CD101V - Cover 3","1.875,00","9,38"},
		};
	}
	
//Media 2   Exchange.SelectPhase("LastMinute"); Exchange.SelectPhase("Bidding"); Exchange.SelectPhase("BuyNow");
	@Test(dependsOnMethods="checkBuyNowExchangePrice",alwaysRun = true)
	  public void findLMInventoryForMedia2() throws InterruptedException{
	         Lib.ClickButton(By.xpath(D.$be_restore));
			 Exchange.SelectMedia(Lib.BuyNow2);
	//		 Exchange.SelectPhase("LastMinute");
			 Exchange.EnterFromThroughDate(Lib.lmDay1);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
	  }
	  @Test(dataProvider="inputData_2", dependsOnMethods="findLMInventoryForMedia2",alwaysRun = true)
	  public void checkLMExchangePriceM2(boolean BuyPriceHigher,String product, String buyNowE,String buyNowCPME, String bidE,String bidCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();
			 if(BuyPriceHigher){
				 String BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
				 softAssert.assertEquals(BuyNowPrice, buyNowE);
				 String BuyNowCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_cpm_price);
				 softAssert.assertEquals(BuyNowCPM, buyNowCPME);
			 } else {
				 String BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_price);
				 softAssert.assertEquals(BuyNowPrice, buyNowE);
				 String BuyNowCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_cpm_price);
				 softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 
			 }			 
			 String BidPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_bid_price);
			 softAssert.assertEquals(BidPrice, bidE);
			 String BidCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_bid_cpm_price);
			 softAssert.assertEquals(BidCPM, bidCPME);
						 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData_2() {
	return new Object[][] { 	
		{false,"CD101V - Pagina 2","4.125,00","20,63","4.500,00","22,50"},
		{false,"CD101V - Pagina 3","4.125,00","20,63","4.500,00","22,50"},
		{false,"CD101V - Pagina 4-5","4.125,00","20,63","4.500,00","22,50"},
		{false,"CD101V - Voorpagina","4.125,00","20,63","4.500,00","22,50"},		
		{false,"CD102VS - Pagina 2","2.062,50","10,31","2.250,00","11,25"},
		{false,"CD102VL - Pagina 2","2.062,50","10,31","2.250,00","11,25"},
		{false,"CD102VS - Pagina 3","2.062,50","10,31","2.250,00","11,25"},
		{false,"CD102VL - Pagina 3","2.062,50","10,31","2.250,00","11,25"},
		{false,"CD102VS - Pagina 4-5","2.062,50","10,31","2.250,00","11,25"},
		{false,"CD102VL - Pagina 4-5","2.062,50","10,31","2.250,00","11,25"},
		{false,"CD102VS - Voorpagina","2.062,50","10,31","2.250,00","11,25"},
		{false,"CD102VL - Voorpagina","2.062,50","10,31","2.250,00","11,25"},		
		};
	}
	 @Test(dependsOnMethods="checkLMExchangePriceM2",alwaysRun = true)
	 public void findBidInventoryForMedia2() throws InterruptedException{
//		 Exchange.SelectPhase("Bidding");
		 Exchange.EnterFromThroughDate(Lib.bidDay1);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
  }
	 @Test(dataProvider="inputData22", dependsOnMethods="findBidInventoryForMedia2",alwaysRun = true)
	  public void checkBidExchangePriceM2(boolean BuyPriceHigher,String product, String buyNowE,String buyNowCPME, String bidE,String bidCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();
			 if(BuyPriceHigher){
				 String BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
				 softAssert.assertEquals(BuyNowPrice, buyNowE);
				 String BuyNowCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_cpm_price);
				 softAssert.assertEquals(BuyNowCPM, buyNowCPME);
			 } else {
				 String BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_price);
				 softAssert.assertEquals(BuyNowPrice, buyNowE);
				 String BuyNowCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_cpm_price);
				 softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 
			 }			 
			 String BidPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_bid_price);
			 softAssert.assertEquals(BidPrice, bidE);
			 String BidCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_bid_cpm_price);
			 softAssert.assertEquals(BidCPM, bidCPME);
						 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData22() {
	return new Object[][] { 	
		{false,"CD101V - Pagina 2","4.125,00","20,63","5.000,00","25,00"},
		{false,"CD101V - Pagina 3","4.125,00","20,63","5.000,00","25,00"},
		{false,"CD101V - Pagina 4-5","4.125,00","20,63","5.000,00","25,00"},
		{false,"CD101V - Voorpagina","4.125,00","20,63","5.000,00","25,00"},		
		{false,"CD102VS - Pagina 2","2.062,50","10,31","2.500,00","12,50"},
		{false,"CD102VL - Pagina 2","2.062,50","10,31","2.500,00","12,50"},
		{false,"CD102VS - Pagina 3","2.062,50","10,31","2.500,00","12,50"},
		{false,"CD102VL - Pagina 3","2.062,50","10,31","2.500,00","12,50"},
		{false,"CD102VS - Pagina 4-5","2.062,50","10,31","2.500,00","12,50"},
		{false,"CD102VL - Pagina 4-5","2.062,50","10,31","2.500,00","12,50"},
		{false,"CD102VS - Voorpagina","2.062,50","10,31","2.500,00","12,50"},
		{false,"CD102VL - Voorpagina","2.062,50","10,31","2.500,00","12,50"},
		};
	}
	
	 @Test(dependsOnMethods="checkBidExchangePriceM2",alwaysRun = true)
	 public void findBuyNowInventoryForMedia2() throws InterruptedException{
//		 Exchange.SelectPhase("BuyNow");
		 Exchange.EnterFromThroughDate(Lib.buyDay1);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
  }
	 @Test(dataProvider="inputData23", dependsOnMethods="findBuyNowInventoryForMedia2",alwaysRun = true)
	  public void checkBuyNowExchangePriceM2(String product, String buyNowE,String buyNowCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();

				 String BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_price);
				 if(BuyNowPrice.isEmpty()){
					 BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
				 }
				 softAssert.assertEquals(BuyNowPrice, buyNowE);
				 String BuyNowCPM = Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_cpm_price);
				 if(BuyNowCPM.isEmpty()){
					 BuyNowCPM = Exchange.GetAInventoryPrice(product,D.$be_inventory_buynow_bidphase_cpm_price);
				 }
				 softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 

						 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData23() {
	return new Object[][] { 	
		{"CD101V - Pagina 2","4.125,00","20,63"},
		{"CD101V - Pagina 3","4.125,00","20,63"},
		{"CD101V - Pagina 4-5","4.125,00","20,63"},
		{"CD101V - Voorpagina","4.125,00","20,63"},	
		{"CD102VS - Pagina 2","2.062,50","10,31"},
		{"CD102VL - Pagina 2","2.062,50","10,31"},
		{"CD102VS - Pagina 3","2.062,50","10,31"},
		{"CD102VL - Pagina 3","2.062,50","10,31"},
		{"CD102VS - Pagina 4-5","2.062,50","10,31"},
		{"CD102VL - Pagina 4-5","2.062,50","10,31"},
		{"CD102VS - Voorpagina","2.062,50","10,31"},
		{"CD102VL - Voorpagina","2.062,50","10,31"},
		};
	}

	@AfterClass
	public void stop() throws InterruptedException {
			    Top.Logout(); 
				Top.CloseBrowser();
	} 
}
