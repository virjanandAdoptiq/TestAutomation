package toplevel;

import org.openqa.selenium.By;

import toplevel.Top;

public class Account {
	public static void GotoAccountMyAccount() throws InterruptedException{	
		String menu = D.$Menu + D.$MenuAccount + ")]";
		String item = D.$MenuLink + D.$ItemMyAccount + ")]";
		Top.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}	
	public static void SetMyAccountEmailAddress(String address) throws InterruptedException{	
		Top.InputData(address, By.xpath(D.$AccountEmail));
	}	
	}
