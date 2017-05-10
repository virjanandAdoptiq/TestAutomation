package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
	  public void AddAdvertiser() throws InterruptedException {	
//		     Campaign.GotoCampaignAdvertiser();
//		     Lib.ClickButton(By.xpath(D.$bc_all_advertisers_tab));
//		     Lib.InputData("PROJOE", By.xpath(D.$bc_all_advertisers_name));
//		     Lib.ClickButton(By.xpath(D.$bc_all_advertisers_search_button));
//		     Lib.ClickButton(By.xpath(D.$bc_all_advertiser_first_one));
//		     Lib.ClickButton(By.xpath(D.$bc_all_advertisers_addToMyadvertiser));		     	
	  }
	  @Test(dependsOnMethods="AddAdvertiser")
	  public void CreateCampaign() throws InterruptedException {		  
//    	    Campaign.GotoCampaignCampaign();
//    	    Lib.ClickButton(By.xpath(D.$bc_new_campaign_button));
//    	    Lib.InputData("AUTO_CP", By.xpath(D.$bc_campaign_code));
//    	    Lib.InputData("AutoCP_PROJOE", By.xpath(D.$bc_campaign_name));
//    	    Lib.InputData(Lib.Today, By.xpath(D.$bc_campaign_from_time));
//    	    Lib.InputData("01-12-2019", By.xpath(D.$bc_campaign_end_time));
//    	    Lib.InputData("10000", By.xpath(D.$bc_campaign_total_budget));  
//    	    Lib.InputData("20000", By.xpath(D.$bc_campaign_reach));
//    	    Lib.SelectDropdownItem(By.xpath(D.$bc_campaign_advertiser), "PROJOE");
//    	    Lib.ClickButton(By.xpath(D.$bc_campaign_save));    	    
	  }
	  @Test(dependsOnMethods="CreateCampaign")
	  public void AddCharacteristics() throws InterruptedException {		  
    	    Campaign.GotoCampaignCampaign();
    	    Campaign.SelectACampaign(Lib.ADV);
    	    Lib.ClickButton(By.xpath(D.$bc_campaign_Edit));
    	    Campaign.SelectATab("Kenmerken");
    	    
    	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Leeftijd");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
            
       	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Opleidingsniveau");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
            
      	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Gezinsfase");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
            
      	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Geslacht");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
            
      	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Inkomensklasse");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
            
      	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Leefstijl");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
            
      	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Mediumsoort");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
            
      	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Oplageverklaring vaktijdschriften");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
                   
      	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Oplageverklaring publiekstijdschriften");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
                        
      	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Sociale klasse");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
                        
      	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Welstand");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
                        
      	    Lib.ClickButton(By.xpath(D.$img_add));
            Campaign.Characteristics_select_type("Krant (huis aan huis) kenmerken");
            Lib.ClickButton(By.xpath(D.$bc_characteristic_first_one));
            Lib.ClickButton(By.xpath(D.$img_select));
                	    
    	    Lib.ClickButton(By.xpath(D.$img_save));      	    
	  }
	  @Test(dependsOnMethods="AddCharacteristics")
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
