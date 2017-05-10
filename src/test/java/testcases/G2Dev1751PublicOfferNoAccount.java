package testcases;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import admin.Admin;
import advertiser.Exchange;
import publisher.ExchangeP;
import publisher.Media;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"B9"}, dependsOnGroups="B8", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class G2Dev1751PublicOfferNoAccount {
	  String url;
	  String emailAddress = Lib.credential + "@adoptiq.com";
	  String KvK = "34215639";
	  String KvKBrach = "000017018242";
	  String exclusiveDeepLink;
	  String product1 = D.Cover2FullPage;
	  String product2 = D.Cover3FullPage;
	  String productP1 = "CD101V - Cover 2";
	  String productP2 = "CD101V - Cover 3";
	  String[][] orders; 
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
			}	  
	  @Test
	  public void createOffers() throws InterruptedException, UnsupportedFlavorException, IOException {			  			 
			 Top.Login(Lib.UG,"Welkom01@1");		 
	 		 ExchangeP.GotoInventory();
			 Exchange.SelectMedia(Lib.BuyNow);
			 Exchange.EnterFromThroughDate(Lib.bidDay2);		 
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 ExchangeP.SelectRowOverViewTable(productP1);
			 ExchangeP.SelectRowOverViewTable(productP2);
			 Lib.ClickButton(By.cssSelector(D.$p_offer_make_button));
			 Media.CreatePublicOffer(Lib.Today, Lib.buyDay3);
			 
			 exclusiveDeepLink = Media.GetDeepLinkFromOfferOverview("Inventory");
			 System.out.println(exclusiveDeepLink);
			 Top.Logout(); 
			 Top.CloseBrowser();	
	  }	 			 

	  @Test(dependsOnMethods="createOffers")		  
	  public void useDeeplinkWelkom() throws InterruptedException{
			  Top.GetBroswer(exclusiveDeepLink);
			  D.wait = new WebDriverWait(D.driver, D.maxWaitTime);
			  D.longWait = new WebDriverWait(D.driver,D.longWaitTime);			
			  System.out.println("The following have completely been changed.");	
			  Lib.ClickButton(By.xpath(D.$po_welcome_show_offer_button));			  			  
		  }	
	  @Test(dependsOnMethods="useDeeplinkWelkom")		  
	  public void step1SelectOffer() throws InterruptedException{
		      Lib.ClickButton(By.xpath(D.$po_select_all));
		      Lib.ClickButton(By.cssSelector(D.$po_go_further));
		  }	
	  @Test(dependsOnMethods="step1SelectOffer")		  
	  public void step2NoAccount() throws InterruptedException{
		  Lib.InputData(emailAddress, By.cssSelector(D.$po_email_address));
		  Lib.InputData(emailAddress, By.cssSelector(D.$po_email_address_confirm));
		  Lib.ClickButton(By.cssSelector(D.$po_confirm_buy_button));
		  Lib.InputData(KvK, By.cssSelector(D.$po_KvK));
		  Lib.InputData(KvKBrach,By.cssSelector(D.$po_KvKBrach));
		  Lib.ClickButton(By.cssSelector(D.$po_get_data));
		  Lib.ClickButton(By.cssSelector(D.$po_confirm_buy_button));
		  Top.CloseBrowser();
		  }	
	  @Test(dependsOnMethods="step2NoAccount")		  
	  public void step3GetConfirmationLink() throws InterruptedException{
		    Top.StartBroswer();
		    Top.Login(D.adminUser, D.adminPass);
		    Admin.GotoSystem();
		    Lib.ClickButton(By.xpath(D.$ad_system_emails));
		    Lib.ClickButton(By.xpath(D.$ad_emails_sent_tab));
		    Lib.InputData(Lib.credential, By.xpath(D.$ad_emails_sent_search_to));
		    Lib.SendSpecialKey(Keys.ENTER);
		    Lib.DoubleClicky(By.xpath(D.$ad_emails_sent_publicOffer_email));
		    url = D.driver.findElement(By.xpath("//p[contains(.,'https://adoptiq100-test.mendixcloud.com/link/poc/')]")).getText();
            System.out.println(url);
		    Top.Logout();
		    Top.CloseBrowser();
	  }
	  @Test(dependsOnMethods="step3GetConfirmationLink")		  
	  public void step4ClickConfirmationLink() throws InterruptedException, UnsupportedFlavorException, IOException{
		    Top.GetBroswer(url);
		    D.wait = new WebDriverWait(D.driver, D.maxWaitTime);
			D.longWait = new WebDriverWait(D.driver,D.longWaitTime);
		    Lib.FindElement(By.cssSelector(D.$po_look_other_titles_button));
		    SoftAssert softAssert = new SoftAssert();
		    D.driver.findElement(By.cssSelector(D.$po_look_other_titles_button)).isDisplayed();
		    softAssert.assertTrue(D.driver.findElement(By.cssSelector(D.$po_look_other_titles_button)).isDisplayed());
		    D.FAILURE_INDICATION = 0;
			softAssert.assertAll(); 
		    Lib.ClickButton(By.cssSelector(D.$po_look_other_titles_button));
		    Top.CloseBrowser();
		  }	
	  @Test(dependsOnMethods="step4ClickConfirmationLink")		
	  public void UGCheckExchangePlatform() throws InterruptedException, UnsupportedFlavorException, IOException {			  			 
			 Top.StartBroswer(); 
		     Top.Login(Lib.UG,"Welkom01@1");	
		     ExchangeP.GoToExchangePlatform();
		 //  ExchangeP.ExpandANegotiationy("7.650,00");
		     ExchangeP.ExpandANegotiationy("Bulk campagne van HEMA B.V.");
		     Lib.ClickButton(By.xpath(D.$p_negotiation_accept_button)); 
		     Lib.CloseDialogBox();
			 Top.Logout(); 	
	  }	
	  @AfterClass
		public void stop() throws InterruptedException {	
			Top.CloseBrowser();
		} 

		 
}
