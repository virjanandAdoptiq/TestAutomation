package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
	  public void AddCharacteristicsUG() throws InterruptedException {	
			Top.Login(Lib.UG,"Welkom01@1");	
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
		    Top.Logout();
	  }
	  @Test(dependsOnMethods="AddCharacteristicsUG")
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
