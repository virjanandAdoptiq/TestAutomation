package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
public class G4Dev0000NewCampaignCharacterisctcs {	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
			 Top.StartBroswer();
			 Top.Login(Lib.MB,"Welkom01@1");				
	  }
	  @Test(alwaysRun = true)
	  public void OpenCharacteristics() throws InterruptedException {		  
    	    Campaign.GotoCampaignCampaign();
    	    Campaign.SelectACampaign(Lib.ADV);
    	    Lib.ClickButton(By.xpath(D.$bc_campaign_Edit));
    	    Campaign.SelectATab("Kenmerken");
	  }	  
	  @Test(dataProvider="inputData", dependsOnMethods="OpenCharacteristics")
	  public void SelectCharacteristics(String type) throws InterruptedException{
	  	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type(type);
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
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
      @Test(dependsOnMethods="SelectCharacteristics")
      public void Save() throws InterruptedException{
    	    Lib.ClickButton(By.xpath(D.$img_save));      	    
	  }
	  @Test(dependsOnMethods="Save")
	  public void AddMedia() throws InterruptedException {		  
    	    Campaign.GotoCampaignCampaign();
    	    Campaign.SelectACampaign(Lib.ADV);
    	    Lib.ClickButton(By.xpath(D.$bc_campaign_Edit));
    	    Campaign.SelectATab("Mediumfilters");
    	    Lib.ClickButton(By.xpath(D.$img_add));
    	    Lib.InputData(Lib.BuyNow, By.xpath(D.$bc_media_select_name));
    	    Lib.SendSpecialKey(Keys.ENTER);
    	    Lib.ClickButton(By.xpath(D.$img_select_all));
      	    Lib.ClickButton(By.xpath(D.$img_select));    	    
    	    Lib.ClickButton(By.xpath(D.$img_save));     	    
	  }
	  @AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 		 
}
