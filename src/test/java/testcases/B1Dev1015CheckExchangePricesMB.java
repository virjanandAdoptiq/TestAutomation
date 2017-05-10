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


@Test//(groups = {"B1"}, dependsOnGroups="A2", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class B1Dev1015CheckExchangePricesMB {	

	  @BeforeClass
	  public void start() throws InterruptedException{
			Top.StartBroswer();
			Top.Login(Lib.MB,"Welkom01@1");
			D.FAILURE_INDICATION = 0; 
	  }	  
	  @Test(alwaysRun = true)
	  public void findLMInventoryForMedia1() throws InterruptedException{
		     Exchange.GotoBuyerEchangePageTileView();
		     Exchange.SelectCampaign("");
			 Exchange.SelectMedia(Lib.BuyNow);
			 Exchange.EnterFromThroughDate(Lib.lmDay2);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.ClickAMediaTile(Lib.BuyNow);
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
		{false,D.Cover2FullPage,"3.150,00","15,75","4.500,00","22,50"},
		{false,D.Cover2HalfStand,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.Cover2HalfLying,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.Cover3HalfStand,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.Cover3HalfLying,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.Cover3FullPage,"4.125,00","20,63","4.500,00","22,50"},
		};
	}
	 @Test(dependsOnMethods="checkLMExchangePrice",alwaysRun = true)
	 public void findBidInventoryForMedia1() throws InterruptedException{
		 Exchange.EnterFromThroughDate(Lib.bidDay2);
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
		{false,D.Cover2FullPage,"3.150,00","15,75","5.000,00","25,00"},
		{false,D.Cover2HalfStand,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.Cover2HalfLying,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.Cover3HalfStand,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.Cover3HalfLying,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.Cover3FullPage,"4.125,00","20,63","5.000,00","25,00"},
		};
	}
	
	 @Test(dependsOnMethods="checkBidExchangePrice",alwaysRun = true)
	 public void findBuyNowInventoryForMedia1() throws InterruptedException{
		 Exchange.EnterFromThroughDate(Lib.buyDay3);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
     }
	 @Test(dataProvider="inputData3", dependsOnMethods="findBuyNowInventoryForMedia1",alwaysRun = true)
	  public void checkBuyNowExchangePrice(String product, String buyNowE,String buyNowCPME) throws InterruptedException{		  
		SoftAssert softAssert = new SoftAssert();
		String BuyNowPrice;
		String BuyNowCPM;
		BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
		softAssert.assertEquals(BuyNowPrice, buyNowE);
		BuyNowCPM = Exchange.GetAInventoryPrice(product,D.$be_inventory_buynow_bidphase_cpm_price);
		softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 						 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData3() {
	return new Object[][] { 	
		{D.Cover2FullPage,"3.150,00","15,75"},
		{D.Cover2HalfStand,"2.062,50","10,31"},
		{D.Cover2HalfLying,"2.062,50","10,31"},
		{D.Cover3HalfStand,"2.062,50","10,31"},
		{D.Cover3HalfLying,"2.062,50","10,31"},
		{D.Cover3FullPage,"4.125,00","20,63"},
		};
	}

	 @Test(dependsOnMethods="checkBuyNowExchangePrice",alwaysRun = true)
	 public void findBuyNowInventoryForMedia1WithCampaign1() throws InterruptedException{
	     Exchange.SelectCampaign(Lib.CampaignADV);
	     Exchange.GotoBuyerEchangePageTileView();
		 Exchange.ClickAMediaTile(Lib.BuyNow);
		 Exchange.EnterFromThroughDate(Lib.buyDay3);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
    }
	 @Test(dataProvider="inputData4", dependsOnMethods="findBuyNowInventoryForMedia1WithCampaign1",alwaysRun = true)
	  public void checkBuyNowExchangePriceWithCampaign1(String product, String buyNowE,String buyNowCPME) throws InterruptedException{		  
		SoftAssert softAssert = new SoftAssert();
		String BuyNowPrice;
		String BuyNowCPM;
		BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
		softAssert.assertEquals(BuyNowPrice, buyNowE);
		BuyNowCPM = Exchange.GetAInventoryPrice(product,D.$be_inventory_buynow_bidphase_cpm_price);
		softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 					 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData4() {
	return new Object[][] { 	
		{D.Cover2FullPage,"1.575,00","7,88"},
		{D.Cover2HalfStand,"1.031,25","5,16"},
		{D.Cover2HalfLying,"1.031,25","5,16"},
		{D.Cover3HalfStand,"1.031,25","5,16"},
		{D.Cover3HalfLying,"1.031,25","5,16"},
		{D.Cover3FullPage,"2.062,50","10,31"},
		};
	}
	 @Test(dependsOnMethods="checkBuyNowExchangePriceWithCampaign1",alwaysRun = true)
	 public void findBuyNowInventoryForMedia1WithCampaign2() throws InterruptedException{
	     Exchange.SelectCampaign(Lib.CampaignADV2);
	     Exchange.GotoBuyerEchangePageTileView();
		 Exchange.ClickAMediaTile(Lib.BuyNow);
		 Exchange.EnterFromThroughDate(Lib.buyDay3);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
   }
	 @Test(dataProvider="inputData5", dependsOnMethods="findBuyNowInventoryForMedia1WithCampaign2",alwaysRun = true)
	  public void checkBuyNowExchangePriceCampaign2(String product, String buyNowE,String buyNowCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();
		String BuyNowPrice;
		String BuyNowCPM;
        BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
		softAssert.assertEquals(BuyNowPrice, buyNowE);
		BuyNowCPM = Exchange.GetAInventoryPrice(product,D.$be_inventory_buynow_bidphase_cpm_price);
		softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 					 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData5() {
	return new Object[][] { 	
		{D.Cover2FullPage,"1.875,00","9,38"},
		{D.Cover2HalfStand,"937,50","4,69"},
		{D.Cover2HalfLying,"937,50","4,69"},
		{D.Cover3HalfStand,"937,50","4,69"},
		{D.Cover3HalfLying,"937,50","4,69"},
		{D.Cover3FullPage,"1.875,00","9,38"},
		};
	}

	@Test(dependsOnMethods="checkBuyNowExchangePriceCampaign2",alwaysRun = true)
	  public void findLMInventoryForMedia2() throws InterruptedException{
             Lib.ClickButton(By.xpath(D.$be_restore));
		     Exchange.SelectCampaign("");
		     Exchange.GotoBuyerEchangePageTileView();
			 Exchange.ClickAMediaTile(Lib.BuyNow2);
			 Exchange.EnterFromThroughDate(Lib.lmDay2);
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
		{false,D.Pagina2FullPage,"4.125,00","20,63","4.500,00","22,50"},
		{false,D.Pagina3FullPage,"4.125,00","20,63","4.500,00","22,50"},
		{false,D.Pagina45FullPage,"4.125,00","20,63","4.500,00","22,50"},
		{false,D.VoorpaginaFullPage,"4.125,00","20,63","4.500,00","22,50"},		
		{false,D.Pagina2HalfStand,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.Pagina2HalfLying,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.Pagina3HalfStand,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.Pagina3HalfLying,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.Pagina45HalfStand,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.Pagina45HalfLying,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.VoorpaginaHalfStand,"2.062,50","10,31","2.250,00","11,25"},
		{false,D.VoorpaginaHalfLying,"2.062,50","10,31","2.250,00","11,25"},		
		};
	}
	 @Test(dependsOnMethods="checkLMExchangePriceM2",alwaysRun = true)
	 public void findBidInventoryForMedia2() throws InterruptedException{
		 Exchange.EnterFromThroughDate(Lib.bidDay2);
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
		{false,D.Pagina2FullPage,"4.125,00","20,63","5.000,00","25,00"},
		{false,D.Pagina3FullPage,"4.125,00","20,63","5.000,00","25,00"},
		{false,D.Pagina45FullPage,"4.125,00","20,63","5.000,00","25,00"},
		{false,D.VoorpaginaFullPage,"4.125,00","20,63","5.000,00","25,00"},		
		{false,D.Pagina2HalfStand,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.Pagina2HalfLying,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.Pagina3HalfStand,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.Pagina3HalfLying,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.Pagina45HalfStand,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.Pagina45HalfLying,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.VoorpaginaHalfStand,"2.062,50","10,31","2.500,00","12,50"},
		{false,D.VoorpaginaHalfLying,"2.062,50","10,31","2.500,00","12,50"},
		};
	}
	
	 @Test(dependsOnMethods="checkBidExchangePriceM2",alwaysRun = true)
	 public void findBuyNowInventoryForMedia2() throws InterruptedException{
		 Exchange.EnterFromThroughDate(Lib.buyDay3);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
  }
	 @Test(dataProvider="inputData23", dependsOnMethods="findBuyNowInventoryForMedia2",alwaysRun = true)
	  public void checkBuyNowExchangePriceM2(String product, String buyNowE,String buyNowCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();
		String BuyNowPrice;
		String BuyNowCPM;
		BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
		softAssert.assertEquals(BuyNowPrice, buyNowE);
		BuyNowCPM = Exchange.GetAInventoryPrice(product,D.$be_inventory_buynow_bidphase_cpm_price);
		softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 						 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData23() {
	return new Object[][] { 	
		{D.Pagina2FullPage,"4.125,00","20,63"},
		{D.Pagina3FullPage,"4.125,00","20,63"},
		{D.Pagina45FullPage,"4.125,00","20,63"},
		{D.VoorpaginaFullPage,"4.125,00","20,63"},	
		{D.Pagina2HalfStand,"2.062,50","10,31"},
		{D.Pagina2HalfLying,"2.062,50","10,31"},
		{D.Pagina3HalfStand,"2.062,50","10,31"},
		{D.Pagina3HalfLying,"2.062,50","10,31"},
		{D.Pagina45HalfStand,"2.062,50","10,31"},
		{D.Pagina45HalfLying,"2.062,50","10,31"},
		{D.VoorpaginaHalfStand,"2.062,50","10,31"},
		{D.VoorpaginaHalfLying,"2.062,50","10,31"},
		};
	}

	 @Test(dependsOnMethods="checkBuyNowExchangePriceM2",alwaysRun = true)
	 public void findBuyNowInventoryForMedia2Campaign1() throws InterruptedException{
	     Exchange.SelectCampaign(Lib.CampaignADV);
	     Lib.ClickButton(By.xpath(D.$bm_backToMedia_Button));
		 Exchange.SelectMedia(Lib.BuyNow2);
		 Exchange.EnterFromThroughDate(Lib.buyDay3);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
		 Exchange.ClickAMediaTile(Lib.BuyNow2);
 }
	 @Test(dataProvider="inputData24", dependsOnMethods="findBuyNowInventoryForMedia2Campaign1",alwaysRun = true)
	  public void checkBuyNowExchangePriceM2Campaign1(String product, String buyNowE,String buyNowCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();
		String BuyNowPrice;
		String BuyNowCPM;

		BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
		softAssert.assertEquals(BuyNowPrice, buyNowE);
		BuyNowCPM = Exchange.GetAInventoryPrice(product,D.$be_inventory_buynow_bidphase_cpm_price);
		softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 
					 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData24() {
	return new Object[][] { 	
		{D.Pagina2FullPage,"4.125,00","20,63"},
		{D.Pagina3FullPage,"4.125,00","20,63"},
		{D.Pagina45FullPage,"4.125,00","20,63"},
		{D.VoorpaginaFullPage,"4.125,00","20,63"},	
		{D.Pagina2HalfStand,"2.062,50","10,31"},
		{D.Pagina2HalfLying,"2.062,50","10,31"},
		{D.Pagina3HalfStand,"2.062,50","10,31"},
		{D.Pagina3HalfLying,"2.062,50","10,31"},
		{D.Pagina45HalfStand,"2.062,50","10,31"},
		{D.Pagina45HalfLying,"2.062,50","10,31"},
		{D.VoorpaginaHalfStand,"2.062,50","10,31"},
		{D.VoorpaginaHalfLying,"2.062,50","10,31"},
		};
	}
	 @Test(dependsOnMethods="checkBuyNowExchangePriceM2Campaign1",alwaysRun = true)
	 public void findBuyNowInventoryForMedia2Campaign2() throws InterruptedException{
	     Exchange.SelectCampaign(Lib.CampaignADV2);
	     Lib.ClickButton(By.xpath(D.$bm_backToMedia_Button));
		 Exchange.SelectMedia(Lib.BuyNow2);
		 Exchange.EnterFromThroughDate(Lib.buyDay3);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
		 Exchange.ClickAMediaTile(Lib.BuyNow2);
}
	 @Test(dataProvider="inputData25", dependsOnMethods="findBuyNowInventoryForMedia2Campaign2",alwaysRun = true)
	  public void checkBuyNowExchangePriceM2Campaign2(String product, String buyNowE,String buyNowCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();
		String BuyNowPrice;
		String BuyNowCPM;
        BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
		softAssert.assertEquals(BuyNowPrice, buyNowE);
        BuyNowCPM = Exchange.GetAInventoryPrice(product,D.$be_inventory_buynow_bidphase_cpm_price);
		softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 					 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData25() {
	return new Object[][] { 	
		{D.Pagina2FullPage,"4.125,00","20,63"},
		{D.Pagina3FullPage,"4.125,00","20,63"},
		{D.Pagina45FullPage,"4.125,00","20,63"},
		{D.VoorpaginaFullPage,"4.125,00","20,63"},	
		{D.Pagina2HalfStand,"2.062,50","10,31"},
		{D.Pagina2HalfLying,"2.062,50","10,31"},
		{D.Pagina3HalfStand,"2.062,50","10,31"},
		{D.Pagina3HalfLying,"2.062,50","10,31"},
		{D.Pagina45HalfStand,"2.062,50","10,31"},
		{D.Pagina45HalfLying,"2.062,50","10,31"},
		{D.VoorpaginaHalfStand,"2.062,50","10,31"},
		{D.VoorpaginaHalfLying,"2.062,50","10,31"},
		};
	}

	 @Test(dependsOnMethods="checkBuyNowExchangePriceM2Campaign2",alwaysRun = true)
	 public void findTuesdayInventoryForMedia1() throws InterruptedException{
	     Exchange.SelectCampaign("");
	     Lib.ClickButton(By.xpath(D.$be_restore));
	     Lib.ClickButton(By.xpath(D.$bm_backToMedia_Button));
		 Exchange.SelectMedia(Lib.BuyNow);
		 Exchange.EnterFromThroughDate(Lib.weekDay);
		 Lib.ClickButton(By.cssSelector(D.$be_execute));
		 Exchange.ClickAMediaTile(Lib.BuyNow);
}
	 @Test(dataProvider="inputData26", dependsOnMethods="findTuesdayInventoryForMedia1",alwaysRun = true)
	  public void checkTuesdayExchangePriceM1(String product, String buyNowE,String buyNowCPME) throws InterruptedException{
		  
		SoftAssert softAssert = new SoftAssert();
		String BuyNowPrice;
		String BuyNowCPM;
		BuyNowPrice =  Exchange.GetAInventoryPrice(product, D.$be_inventory_buynow_bidphase_price);
		softAssert.assertEquals(BuyNowPrice, buyNowE);
		BuyNowCPM = Exchange.GetAInventoryPrice(product,D.$be_inventory_buynow_bidphase_cpm_price);
		softAssert.assertEquals(BuyNowCPM, buyNowCPME);				 					 			
		softAssert.assertAll(); 						 			 	  
	   }	
	@DataProvider
	public Object[][] inputData26() {
	return new Object[][] { 	
		{D.Cover2FullPage,"1.890,00","9,45"},
		{D.Cover2HalfStand,"1.512,00","7,56"},
		{D.Cover2HalfLying,"1.512,00","7,56"},
		{D.Cover3HalfStand,"2.062,50","10,31"},
		{D.Cover3HalfLying,"2.062,50","10,31"},
		{D.Cover3FullPage,"2.700,00","13,50"},
		};
	}


	@AfterClass
	public void stop() throws InterruptedException {
		        Exchange.SelectCampaign("");
			    Top.Logout(); 
				Top.CloseBrowser();
	} 
}
