package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"Gui"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class ZZZ_GuiCheckMB {	
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
				Top.Login(Lib.MB,"Welkom01@1");				
			}
	  
	  @Test(dataProvider = "pages")
	  public void MainPageCheck(String icon, String title) throws InterruptedException {	
		  SoftAssert softAssert = new SoftAssert();
		  Lib.ClickButton(By.xpath(D.$gui_adoptiqlogo));
		  Lib.ClickButton(By.xpath(icon));	
		  String path = D.$gui_pagetitle_prefix + title + "']";
		  Lib.FindElement(By.xpath(path));		  
		  softAssert.assertEquals(D.driver.findElements(By.xpath(path)).size(), 1);
		  
		  softAssert.assertAll();
	  }
	  @DataProvider
	  public Object[][] pages() {
	    return new Object[][] { 
	      {D.$gui_exchangelogo, "Exchange"},
	      {D.$gui_offerlogo, "Persoonlijk aanbod"},
	      {D.$gui_availabletitleslogo, "Beschikbare titels"}, 
		  {D.$gui_publisherlogo, "Uitgevers"},
	      {D.$gui_mylotslogo,"Mijn Kavels" }, 
//		  {D.$gui_bosummarylogo, "Bieding- en orderoverzicht"},
		  {D.$gui_ordersummarylogo, "Orderoverzicht"}, 
		  {D.$gui_campaignlogo, "Campagnes"},
		  {D.$gui_campaigndashlogo,"Campagne Dashboard"},
		  {D.$gui_advertiserlogo, "Adverteerders"}

	    };
	  }
	
	 	  @AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 
}
