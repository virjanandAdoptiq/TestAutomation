package publisher;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import toplevel.D;
import toplevel.Lib;
import toplevel.Top;

public class Media {
	public static void PushOption(String mb, String ad, String days, String dealID, String price) throws InterruptedException{
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(D.$p_option_push_dialog)));
		Thread.sleep(D.waitTime);
		//there are other elements with the same identifier, but they are not displayed.
		List<WebElement> elements = D.driver.findElements(By.xpath(D.$p_option_details_deadLine_days));
		for(WebElement ele: elements){
			if(ele.isDisplayed()){
				ele.sendKeys(Keys.chord(Keys.COMMAND, "a"), days);
				break;
			}
		}
		//Enter price
/*
		List<WebElement> pricefield = D.driver.findElements(By.xpath(D.$p_option_details_price));
		for(WebElement ele: pricefield){
				if(ele.isEnabled()){
				ele.sendKeys(price);
				break;
			}
		}
*/		
		Lib.InputData(price, By.xpath(D.$p_option_details_price));
		
		Lib.InputData(dealID, By.xpath(D.$p_option_details_dealid));
		
		
		//select MB
		Lib.ClickButton(By.xpath(D.$p_option_details_mb_button));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_search));
		Lib.InputData(Lib.credential, By.xpath(D.$p_option_details_org_input));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_search_execute));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_ad_select + mb + "']"));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_choose_button));
		//Lib.SelectDropdownItem(By.xpath(D.$p_option_details_mb_button), mb);
		//select ad
		Lib.SelectDropdownItem(By.xpath(D.$p_option_details_ad), ad);
	
		//save
		Lib.ClickButton(By.xpath(D.$p_approve_option_save));
		Lib.CloseDialogBox();
	}		

	public static void PushOptionToADVOnly(String ad, String days, String dealID, String price) throws InterruptedException{
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(D.$p_option_push_dialog)));
		Thread.sleep(D.waitTime);
		//there are other elements with the same identifier, but they are not displayed.
		List<WebElement> elements = D.driver.findElements(By.xpath(D.$p_option_details_deadLine_days));
		for(WebElement ele: elements){
			if(ele.isDisplayed()){
				ele.sendKeys(Keys.chord(Keys.COMMAND, "a"), days);
				break;
			}
		}
		//Enter price
/*
		List<WebElement> pricefield = D.driver.findElements(By.xpath(D.$p_option_details_price));
		for(WebElement ele: pricefield){
				ele.sendKeys(price);
		}
*/
		Lib.InputData(price, By.xpath(D.$p_option_details_price));
		Lib.InputData(dealID, By.xpath(D.$p_option_details_dealid));
		
		Lib.ClickButton(By.xpath(D.$p_option_details_mb_button));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_search));
		Lib.InputData(Lib.credential, By.xpath(D.$p_option_details_org_input));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_search_execute));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_ad_select + ad + "']"));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_choose_button));
		
	//	Lib.SelectDropdownItem(By.xpath(D.$p_option_details_mb_button), ad);
		//save
		Lib.ClickButton(By.xpath(D.$p_approve_option_save));
		Lib.CloseDialogBox();
	}		
	public static void CreatePublicOffer(String fromDate, String endDate) throws InterruptedException{		
		 Lib.InputData(fromDate, By.xpath(D.$p_offer_startDate));
		 Lib.InputData(endDate, By.xpath(D.$p_offer_endDate));
		 Lib.ClickButton(By.xpath(D.$p_offer_save));
		 Lib.CloseDialogBox();				 
	    }
	public static String GetDeepLinkFromOfferOverview(String type) throws InterruptedException, UnsupportedFlavorException, IOException{
		String menu = D.$Menu + D.$MenuMedia + ")]";
		 Lib.ClickButton(By.xpath(menu));
		 Lib.ClickButton(By.xpath(D.$p_inventory_aanbiedingen));
		 if(type.equalsIgnoreCase("inventory")){
		     Lib.ClickButton(By.xpath(D.$p_aanbiedingenOverview_row + "Inventory']"));
		 } else {
			 Lib.ClickButton(By.xpath(D.$p_aanbiedingenOverview_row + "Media']"));
		 }
		 Lib.ClickButton(By.xpath(D.$p_aanbiedingenOverview_CopyURL));
		 String ret = Lib.getFromClipboard();
	 
		 return ret;
	}

    public static String CreateExclusiveDeepLink(String user,String fromDate, String endDate) throws InterruptedException, UnsupportedFlavorException, IOException{
   	 Top.Login(user,"Welkom01@1");
	 String menu = D.$Menu + D.$MenuMedia + ")]";
	 Lib.ClickButton(By.xpath(menu));
	 Lib.ClickButton(By.xpath(D.$p_inventory_aanbiedingen));
	 Lib.ClickButton(By.xpath(D.$p_inventory_aanbiedingen_new_button));
	 Lib.ClickButton(By.xpath(D.$p_aanbiedingenDetails_exclusive_checkbox));
	 Lib.InputData(fromDate, By.xpath(D.$p_aanbiedingenDetails_valid_from));
	 Lib.InputData(endDate, By.xpath(D.$p_aanbiedingenDetails_valid_to));
	 Lib.ClickButton(By.xpath(D.$p_aanbiedingenDetails_Toevoegen));
	 Lib.ClickButton(By.xpath(D.$p_aanbiedingenDetails_media_select));
	 Lib.ClickButton(By.xpath(D.$p_aanbiedingenDetails_media_select_kies));
	 Lib.ClickButton(By.xpath(D.$p_aanbiedingenDetails_save));
	 Lib.CloseDialogBox();
	 Lib.ClickButton(By.xpath(D.$p_aanbiedingenOverview_row + "Media']"));
	 Lib.ClickButton(By.xpath(D.$p_aanbiedingenOverview_CopyURL));
	 String ret = Lib.getFromClipboard();
	 Top.Logout(); 
 
	 return ret;
	
	 

    }
}