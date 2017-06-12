package publisher;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.openqa.selenium.By;

import toplevel.D;
import toplevel.Lib;
import toplevel.Top;

public class Media {
	public static void GotoMedia() throws InterruptedException{			 
		 String menu = D.$Menu + D.$MenuMedia + ")]";
		 String item = D.$MenuLink + D.$ItemMedia + ")]";
		 Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}
	public static void PushOption(String mb, String ad, String days, String dealID, String price) throws InterruptedException{		
		Lib.InputData(days, By.xpath(D.$p_option_details_deadLine_days));
		Lib.InputData(price, By.xpath(D.$p_option_details_price));		
		Lib.InputData(dealID, By.xpath(D.$p_option_details_dealid));		
		Lib.ClickButton(By.xpath(D.$p_option_details_mb_button));
		Lib.InputData(Lib.credential, By.xpath(D.$p_option_details_org_input));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_search_execute));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_ad_select + mb + "']"));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_choose_button));		
		Lib.SelectDropdownItem(By.xpath(D.$p_option_details_ad), ad);
		Lib.ClickButton(By.xpath(D.$p_private_offer_save));
		Lib.CloseDialogBox();
	}	
	public static void PushOptionMultiple(String mb, String ad, String days, String dealID, String price) throws InterruptedException{		
		Lib.InputData(days, By.xpath(D.$p_option_multi_details_deadLine_days));
		Lib.InputData(price, By.xpath(D.$p_option_multi_details_price));		
		Lib.InputData(dealID, By.xpath(D.$p_option_multi_details_dealid));		
		Lib.ClickButton(By.xpath(D.$p_option_multi_details_mb_button));
		Lib.InputData(Lib.credential, By.xpath(D.$p_option_details_org_input));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_search_execute));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_ad_select + mb + "']"));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_choose_button));		
		Lib.SelectDropdownItem(By.xpath(D.$p_option_multi_details_ad), ad);
		Lib.ClickButton(By.xpath(D.$p_private_offer_save));
		Lib.CloseDialogBox();
	}
	public static void PushOptionToADVOnly(String ad, String days, String dealID, String price) throws InterruptedException{
		Lib.InputData(days, By.xpath(D.$p_option_details_deadLine_days));
		Lib.InputData(price, By.xpath(D.$p_option_details_price));		
		Lib.InputData(dealID, By.xpath(D.$p_option_details_dealid));		
		Lib.ClickButton(By.xpath(D.$p_option_details_mb_button));
		Lib.InputData(Lib.credential, By.xpath(D.$p_option_details_org_input));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_search_execute));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_ad_select + ad + "']"));
		Lib.ClickButton(By.xpath(D.$p_option_details_org_choose_button));		
		Lib.ClickButton(By.xpath(D.$p_private_offer_save));
		Lib.CloseDialogBox();
	}		
	public static void CreatePublicOffer(String fromDate, String endDate) throws InterruptedException{		
		 Lib.InputData(fromDate, By.xpath(D.$p_offer_startDate));
		 Lib.InputData(endDate, By.xpath(D.$p_offer_endDate));
		 Lib.ClickButton(By.xpath(D.$p_offer_save));
		 Lib.CloseDialogBox();				 
	    }
	public static String GetDeepLinkFromOfferOverview(String type) throws InterruptedException, UnsupportedFlavorException, IOException{
		 ExchangeP.GotoDeeplink();
		 
		 Lib.ClickButton(By.xpath(D.$p_aanbiedingden_sorting));
		 Lib.ClickButton(By.xpath(D.$p_aanbiedingden_sorting));
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
     ExchangeP.GotoDeeplink();
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