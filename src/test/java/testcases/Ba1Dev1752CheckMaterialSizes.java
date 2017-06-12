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


@Test//(groups = {"Ba1"}, dependsOnGroups="B8", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class Ba1Dev1752CheckMaterialSizes {	
	  private String product1 = D.Cover2FullPage;
	  private String product2 = D.Cover2HalfLying;
	  private String product3 = D.Cover2HalfStand;
	  private String media = Lib.BuyNow;
	  private String theDate = Lib.buyDay3;

	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}
	  
	  @Test(alwaysRun = true)
	  public void AddToMyLots() throws InterruptedException {
		     Top.Login(Lib.MB,"Welkom01@1");
			 Exchange.GotoBuyerEchangePage();
			 Exchange.EnterFromThroughDate(theDate);
			 Exchange.SelectMedia(media);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.AddToMyLots(product1); 
			 Exchange.AddToMyLots(product2); 
			 Exchange.AddToMyLots(product3); 
	  }
	  @Test(dataProvider="inputData", dependsOnMethods="AddToMyLots")
	  public void CheckMeterialSize(String product, String hSizeE, String vSizeE, String htrimSizeE, String vtrimSizeE) throws InterruptedException {			  
		     SoftAssert softAssert = new SoftAssert();
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
		     
		     Mylots.SelectALot(product);
			 Lib.ClickButton(By.xpath(D.$bm_lot_report_button));
			 Lib.ClickButton(By.xpath(D.$bm_lot_report_meterial));

			 String hSizeA =  Mylots.GetLotMaterialInfo(D.$bm_lot_materialSize_hsize_info);
			 softAssert.assertEquals(hSizeA, hSizeE);
			 String vSizeA =  Mylots.GetLotMaterialInfo(D.$bm_lot_materialSize_vsize_info);
			 softAssert.assertEquals(vSizeA, vSizeE);	
			 String vtrimSizeA =  Mylots.GetLotMaterialInfo(D.$bm_lot_materialSize_trim_vsize_info);
			 softAssert.assertEquals(vtrimSizeA, vtrimSizeE);
			 String htrimSizeA =  Mylots.GetLotMaterialInfo(D.$bm_lot_materialSize_trim_hsize_info);
			 softAssert.assertEquals(htrimSizeA, htrimSizeE);
			 Lib.ClickButton(By.xpath(D.$bm_lot_materialSize_info_close_button));
			 
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 
			 D.FAILURE_INDICATION = 0;
			 softAssert.assertAll();	 						 			 
	  }	
	  @DataProvider
		public Object[][] inputData() {
		return new Object[][] { 	
			{product1,"266","398","5","5"},
			{product2,"266","190","",""},
			{product3,"158","330","",""},
			};
		}

	  @AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout(); 
			Top.CloseBrowser();
		} 
}
