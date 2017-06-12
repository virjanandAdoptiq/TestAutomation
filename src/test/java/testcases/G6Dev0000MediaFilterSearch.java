package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import advertiser.Campaign;
import advertiser.Exchange;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;

@Test//(groups = {"F1"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class G6Dev0000MediaFilterSearch {	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
			 Top.StartBroswer();
			 Top.Login(Lib.MB,"Welkom01@1");				
	  }
	  @Test
	  public void OpenMediaFilter() throws InterruptedException {	
          Exchange.GotoBuyerEchangePage();
          Exchange.SelectCampaign("");
          Exchange.SetTileView();
		  Lib.ClickButton(By.xpath(D.$be_mediafilter_icon));
		  Lib.ClickButton(By.xpath(D.$media_filter_goto_characteristics));
	  }
	  @Test(dataProvider="inputData", dependsOnMethods="OpenMediaFilter")
	  public void Set_chracteristics(String type) throws InterruptedException{
		  Campaign.Media_filter_Characteristics_select_type(type);
          Lib.ClickButton(By.xpath(D.$media_filter_characteristic_first_one));
	  }  
	  @DataProvider
		public Object[][] inputData() {
		return new Object[][] { 	
			{"Leeftijd"},
			{"Opleidingsniveau"},
			{"Gezinsfase"},
			{"Geslacht"},
			{"Inkomensklasse"},
			{"Leefstijl"},
			{"Mediumsoort"},
			{"Oplageverklaring vaktijdschriften"},
			{"Oplageverklaring publiekstijdschriften"},
			{"Sociale klasse"},
			{"Welstand"},
			{"Krant (huis aan huis) kenmerken"},
			};
		}

	  @Test(dependsOnMethods="Set_chracteristics")	
	  public void Click_Select() throws InterruptedException{
          Lib.ClickButton(By.xpath(D.$img_select));		  
		  Lib.ClickButton(By.xpath(D.$media_filter_execute));
	  }
	  @Test(dependsOnMethods="Click_Select")	
	  public void CheckResult() throws InterruptedException {	
		  Lib.ClickButton(By.cssSelector(D.$be_execute));
          Exchange.FindAMediaWithPercentage(Lib.BuyNow, "100%");
          Exchange.FindAMediaWithPercentage(Lib.BuyNow2, "45%");
	  }
	  @AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 		 
}
