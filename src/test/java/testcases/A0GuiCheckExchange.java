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
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"Gui"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class A0GuiCheckExchange {	
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
				Top.Login(Helper.MB,"Welkom01@1");				
			}
	  
	  @Test//(dataProvider = "pages")
	  public void ExchangePageGUICheck1() throws InterruptedException {	
		  SoftAssert softAssert = new SoftAssert();
		  Exchange.GotoBuyerEchangePage();
		  Exchange.SelectPhase("Buy Now");
		  Exchange.SelectMediaType("Krant (Landelijk)");
		  Exchange.SelectSaleOrg(Helper.Res);
		  Exchange.SelectMedia(Helper.BuyNow);
		  Exchange.SelectFormat("CD101V");
		  Exchange.SelectSharedPage("1/1 pagina");
		  Exchange.SelectFrequency("Dagelijks");
		  Exchange.SelectWeekday("Maandag");
		  Exchange.SelectCategory("Uitverkoop");
		  Exchange.SelectPlusProposition("Ja");
		  Top.ClickButton(By.cssSelector(D.$be_execute));
		  
		  softAssert.assertEquals(false, Top.isBox("Fout"));
		  Top.CloseDialogBox();
		  Top.ClickButton(By.xpath(D.$be_restore));
		  Top.ClickButton(By.xpath(D.$be_switchLeftMenuIcon));
		  softAssert.assertEquals(false, Top.isBox("Fout"));
		  Top.ClickButton(By.xpath(D.$be_switchLeftMenuIcon));
		  Top.ClickButton(By.xpath(D.$be_deselect_all));
		  softAssert.assertEquals(false, Top.isBox("Fout"));
		  
		  softAssert.assertAll();    

	  }
	  
	  @Test
	  public void MediaSearchGUICheck() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();
		  Exchange.GotoBuyerEchangePage();
		  Top.ClickButton(By.xpath(D.$be_mediafilter_icon));
		  softAssert.assertEquals(true, Top.isBox("Zoekfilters"));
		  Top.InputData(Helper.UG, By.xpath(D.$media_filter_publisher));
		  Top.InputData(Helper.BuyNow, By.xpath(D.$media_filter_title));
		  Top.ClickButton(By.xpath(D.$media_filter_execute));
		  softAssert.assertEquals(false, Top.isBox("Fout"));
		  Exchange.SelectAInventory(Helper.BuyNow);
		  Top.ClickButton(By.xpath(D.$be_deselect_all));
		  
		  softAssert.assertAll();
	  }

	
	 	  @AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 
}
