package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test(groups = {"Ba2"}, dependsOnGroups="Ba1", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class Ba2Dev1753GuiCheckExchange {	
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
				Top.Login(Lib.MB,"Welkom01@1");				
			}
	  
	  @Test
	  public void ExchangePageFilterCheck() throws InterruptedException {	
		  SoftAssert softAssert = new SoftAssert();
		  Exchange.GotoBuyerEchangePage();
		  Exchange.SelectPhase("Buy Now");
		  Exchange.SelectMediaType("Krant (Landelijk)");
		  Exchange.SelectSaleOrg(Lib.Res2);
		  Exchange.SelectMedia(Lib.BuyNow2);
		  Exchange.SelectFormat("CD101V");
		  Exchange.SelectSharedPage("1/1 pagina");
		  Exchange.SelectFrequency("Dagelijks");
		  Exchange.SelectPlusProposition("Ja");
		  Lib.ClickButton(By.cssSelector(D.$be_execute));
		  
		  Exchange.SelectAInventory(D.Pagina2FullPage);
		  
		  Exchange.SelectWeekday("Maandag");
		  Exchange.SelectCategory("Uitverkoop");
		  Lib.ClickButton(By.cssSelector(D.$be_execute));
		  softAssert.assertEquals(false, Lib.isBox("Fout"));
		  Lib.CloseDialogBox();
		  softAssert.assertAll();  
	  }
	  @Test(dependsOnMethods="ExchangePageFilterCheck")
	  public void ExchangeOtherGUICheck() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();
		  Lib.ClickButton(By.xpath(D.$be_restore));
		  Lib.ClickButton(By.xpath(D.$be_switchLeftMenuIcon));
		  softAssert.assertEquals(false, Lib.isBox("Fout"));
		  Lib.ClickButton(By.xpath(D.$be_switchLeftMenuIcon));
		  Lib.ClickButton(By.xpath(D.$be_deselect_all));
		  softAssert.assertEquals(false, Lib.isBox("Fout"));
		  
		  softAssert.assertAll();    

	  }
	  
	  @Test(dependsOnMethods="ExchangeOtherGUICheck")
	  public void MediaSearchGUICheck() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();
		  Exchange.GotoBuyerEchangePage();
		  Lib.ClickButton(By.xpath(D.$be_mediafilter_icon));
		  softAssert.assertEquals(true, Lib.isBox("Zoekfilters"));
		  Lib.InputData(Lib.UG, By.xpath(D.$media_filter_publisher));
		  Lib.InputData(Lib.BuyNow, By.xpath(D.$media_filter_title));
		  Lib.ClickButton(By.xpath(D.$media_filter_execute));
		  
		  Lib.InputData("Cover 2", By.xpath(D.$be_filter_positionname));
		  Lib.ClickButton(By.cssSelector(D.$be_execute));

		  Exchange.SelectAInventory(D.Cover2FullPage);
		  Lib.ClickButton(By.xpath(D.$be_deselect_all));
		  
		  softAssert.assertAll();
	  }

	
	  @AfterClass
	  public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
	  } 
}
