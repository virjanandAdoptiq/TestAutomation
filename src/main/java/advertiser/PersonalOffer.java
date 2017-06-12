package advertiser;

import org.openqa.selenium.By;

import toplevel.D;
import toplevel.Lib;

public class PersonalOffer {
	public static void SelectAGroupOfOffers(String price) throws InterruptedException{	
		String path = D.$be_offer_group_prefix + price + D.$be_offer_group_suffix + "/td[1]";
		Lib.ClickButton(By.xpath(path));
	}
	public static void ExpandAGroupOfOffers(String price) throws InterruptedException{	
		String path = D.$be_offer_group_prefix + price + D.$be_offer_group_suffix + "/td[2]";
		Lib.ClickButton(By.xpath(path));
	}
}
