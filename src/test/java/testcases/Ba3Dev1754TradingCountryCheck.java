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


@Test//(groups = {"Ba3"}, dependsOnGroups="Ba2", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class Ba3Dev1754TradingCountryCheck {	
	  @BeforeClass
			public void start() throws InterruptedException{
				Top.StartBroswer();
				Top.Login(Lib.MB,"Welkom01@1");				
			}
	  
	  @Test
	  public void CheckDefaultTradingCountry() throws InterruptedException {	
		  SoftAssert softAssert = new SoftAssert();
		  Exchange.GotoBuyerEchangePage();
		  Exchange.SelectCampaign("");
	      String countryName = Exchange.GetTradingCountryName();
	      softAssert.assertEquals(countryName, "Nederland");
		  softAssert.assertAll();  
	  }
	  @Test(dependsOnMethods="CheckDefaultTradingCountry")
	  public void ChangeCountryThenCheck() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();
		  Top.GotoMyAccountPage();
		  Lib.SelectDropdownItem(By.xpath(D.$MyAccount_TradingCountry), "Oostenrijk");
		  Lib.ClickButton(By.xpath(D.$MyAccount_Save));
		  Lib.CloseDialogBox();
		  Exchange.GotoBuyerEchangePage();
		  Lib.CloseDialogBox();
	      String countryName = Exchange.GetTradingCountryName();
	      softAssert.assertEquals(countryName, "Oostenrijk");
		  softAssert.assertAll();
	  }
	  @Test(dependsOnMethods="ChangeCountryThenCheck")
	  public void CampaignTradingCountry() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();
		  Exchange.SelectCampaign(Lib.CampaignADV);
		  String countryName = Exchange.GetTradingCountryName();
	      softAssert.assertEquals(countryName, "Nederland");
	      Exchange.SelectCampaign("");
	      Exchange.GotoBuyerEchangePage();
		  Lib.CloseDialogBox();
	      String countryName2 = Exchange.GetTradingCountryName();
	      softAssert.assertEquals(countryName2, "Oostenrijk");
		  softAssert.assertAll();
		  
		  
	  }
	  @Test(dependsOnMethods="CampaignTradingCountry", alwaysRun = true)
	  public void SetCountryBackThenCheck() throws InterruptedException{
		  Top.GotoMyAccountPage();
		  Lib.SelectDropdownItem(By.xpath(D.$MyAccount_TradingCountry), "Nederland");
		  Lib.ClickButton(By.xpath(D.$MyAccount_Save));
		  Lib.CloseDialogBox();
	  }
	
	  @AfterClass
	  public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
	  } 
}
