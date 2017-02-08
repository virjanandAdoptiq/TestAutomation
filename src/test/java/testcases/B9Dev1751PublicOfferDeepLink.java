package testcases;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import publisher.ExchangeP;
import publisher.Media;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"B9"}, dependsOnGroups="B8", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class B9Dev1000PublicOfferADV {	
	  String exclusiveDeepLink;
	  String product1 = D.Cover2HalfLying;
	  String product2 = D.Cover2HalfStand;
	  String product3 = D.Cover3HalfLying;
	  String product4 = D.Cover3HalfStand;
	  String productP1 = "CD102VL - Cover 2";
	  String productP2 = "CD102VS - Cover 2";
	  String productP3 = "CD102VL - Cover 3";
	  String productP4 = "CD102VS - Cover 3";
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}
	  
	  @Test
	  public void createOffers() throws InterruptedException, UnsupportedFlavorException, IOException {			  
			 
			 Top.Login(Lib.UG,"Welkom01@1");
			 String menu = D.$Menu + D.$MenuMedia + ")]";
			 Lib.ClickButton(By.xpath(menu));
	 		 ExchangeP.SelectLeftMenu("Inventory");
			 Exchange.SelectMedia(Lib.BuyNow);
			 Exchange.EnterFromThroughDate(Lib.buyDay1);		 
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 ExchangeP.SelectRowOverViewTable(productP1);
			 ExchangeP.SelectRowOverViewTable(productP2);
			 ExchangeP.SelectRowOverViewTable(productP3);
			 ExchangeP.SelectRowOverViewTable(productP4);
			 Lib.ClickButton(By.cssSelector(D.$p_offer_make_button));
			 Media.CreatePublicOffer(Lib.Today, Lib.buyDay3);
			 exclusiveDeepLink = Media.GetDeepLinkFromOfferOverview("Inventory");
			 System.out.println(exclusiveDeepLink);
			 Top.Logout(); 
			 Top.CloseBrowser();
	
	  }	 			 

	  @Test(dependsOnMethods="createOffers")		  
	  public void loginUseDeeplink() throws InterruptedException{
			  D.driver = new FirefoxDriver();	
			  D.driver.manage().window().maximize();
			  D.driver.get(exclusiveDeepLink); 
			  D.wait = new WebDriverWait(D.driver, D.maxWaitTime);
			  D.longWait = new WebDriverWait(D.driver,D.longWaitTime);
			
			  Top.DeeplinkLogin(Lib.MB,"Welkom01@1");	
			  
		  }	
	  @Test(dependsOnMethods="loginUseDeeplink")		  
	  public void addOffersToMyLots() throws InterruptedException{
		     Exchange.SelectAnOffer(product1);
		     Exchange.SelectAnOffer(product2);
		     Exchange.SelectAnOffer(product3);
		     Exchange.SelectAnOffer(product4);
		     Lib.ClickButton(By.xpath(D.$b_offer_add_goto_MyLots));		  
		  }	
	  @Test(dependsOnMethods="addOffersToMyLots")		  
	  public void checkLotsStatus() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertTrue(Mylots.CheckLotStatus(product1,D.$bm_lot_status_saved));
		  softAssert.assertTrue(Mylots.CheckLotStatus(product2,D.$bm_lot_status_saved));
		  softAssert.assertTrue(Mylots.CheckLotStatus(product3,D.$bm_lot_status_saved));
		  softAssert.assertTrue(Mylots.CheckLotStatus(product4,D.$bm_lot_status_saved));
		  
           Mylots.SelectALot(product1);
           Mylots.SelectALot(product2);
           Mylots.SelectALot(product3);
           Mylots.SelectALot(product4);
		   Lib.ClickButton(By.cssSelector(D.$bm_lot_delete_icon));
			 
			 			 			              
            D.FAILURE_INDICATION = 0; //if test failed, do nothing
			softAssert.assertAll();
		  }	
	  
	  @AfterClass
		public void stop() throws InterruptedException {
			Top.Logout();	
			Top.CloseBrowser();
		} 

		 
}
