package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import advertiser.Campaign;
import publisher.Media;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;

@Test//(groups = {"F1"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class G3Dev0000MediaAddCharacterisctcs {	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
			 Top.StartBroswer();			
	  }
	  @Test
	  public void OpenBuyNowCharacteristics() throws InterruptedException {	
			Top.Login(Lib.UG,"Welkom01@1");	
		    Media.GotoMedia();
		    Lib.ClickButton(By.xpath(D.$img_edit));
    	    Campaign.SelectATab("Kenmerken");
	  }
	  @Test(dataProvider="inputData", dependsOnMethods="OpenBuyNowCharacteristics")
	  public void SelectCharacteristicsBuyNow(String type) throws InterruptedException{
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
     
	  @Test(dependsOnMethods="SelectCharacteristicsBuyNow")
	  public void SaveLogOut() throws InterruptedException{
    	    Lib.ClickButton(By.xpath(D.$img_save));   
		    Top.Logout();
	  }
	  @Test(dependsOnMethods="SaveLogOut")
	  public void AddCharacteristicsUG2() throws InterruptedException {	
			Top.Login(Lib.UG2,"Welkom01@1");	
		    Media.GotoMedia();
		    Lib.ClickButton(By.xpath(D.$img_edit));
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
               	    
    	    Lib.ClickButton(By.xpath(D.$img_save));   
		    Top.Logout();
	  }

	  @AfterClass
		public void stop() throws InterruptedException {
			Top.CloseBrowser();
		} 

	
		 
}
