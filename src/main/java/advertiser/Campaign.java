package advertiser;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import toplevel.D;
import toplevel.Lib;

public class Campaign {
	public static void GotoCampaignAdvertiser() throws InterruptedException{	
		String menu = D.$Menu + D.$MenuCampaign + ")]";
		String item = D.$MenuLink + D.$ItemAdvertiser + ")]";
		Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}
	public static void GotoCampaignCampaign() throws InterruptedException{	
		String menu = D.$Menu + D.$MenuCampaign + ")]";
		String item = D.$MenuLink + D.$ItemCampaign + ")]";
		Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}

	public static void SelectACampaign(String adName) throws InterruptedException{	
		Lib.ClickButton(By.xpath("//*[contains(text(), '" + adName + "')]"));
		Lib.SendSpecialKey(Keys.ENTER);
		Lib.ClickButton(By.xpath(D.$bc_campaign_first_one));
	}
	public static void SelectATab(String tabName) throws InterruptedException{	
		Lib.ClickButton(By.xpath("//*[contains(text(), '" + tabName + "')]"));
	}
	//$bc_characteristic_type_list
	public static void Characteristics_select_type(String type) throws InterruptedException{	
		Lib.SelectDropdownItem(By.xpath(D.$bc_characteristic_type_list), type);
		Lib.SendSpecialKey(Keys.ENTER);
	}
	public static void Media_filter_Characteristics_select_type(String type) throws InterruptedException{	
		Lib.SelectDropdownItem(By.xpath(D.$media_filter_characteristic_type_list), type);
		Lib.SendSpecialKey(Keys.ENTER);
	}
}
