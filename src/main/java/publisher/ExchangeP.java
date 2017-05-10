package publisher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import toplevel.D;
import toplevel.Lib;

public class ExchangeP {
	public static void GotoInventory() throws InterruptedException{			 
		 String menu = D.$Menu + D.$MenuInventory + ")]";
//		 String item = D.$MenuLink + D.$ItemInventory + ")]"; (doesn't work and cannot find why)
		 String item = D.$ItemInventory;
		 Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}
	public static void GotoDeeplink() throws InterruptedException{			 
		 String menu = D.$Menu + D.$MenuInventory + ")]";
		 String item = D.$MenuLink + D.$ItemDeeplink + ")]";
		 Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}
	public static void GotoOptionOverview() throws InterruptedException{			 
		 String menu = D.$Menu + D.$MenuExchange + ")]";
		 String item = D.$MenuLink + D.$ItemOptionOverview + ")]";
		 Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}
	public static void GotoOderOverview() throws InterruptedException{			 
		 String menu = D.$Menu + D.$MenuExchange + ")]";
		 String item = D.$MenuLink + D.$ItemPOrderOverview + ")]";
		 Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}
	public static void GoToExchangePlatform() throws InterruptedException{
		 String menu = D.$Menu + D.$MenuExchange + ")]";
		 String item = D.$MenuLink + D.$ItemExchangePlatform + ")]";
		 Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}
	public static void SelectRowOverViewTable(String text) throws InterruptedException{
		String path = D.$p_contents_row + text + "']";
		Lib.ClickButton(By.xpath(path));
	}
	public static void AproveOption(String days) throws InterruptedException{
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(D.$p_modal_dialog)));
		Thread.sleep(D.waitTime);
		Lib.InputData(days, By.xpath(D.$p_approve_option_deadline_days));
		
		Lib.ClickButton(By.xpath(D.$p_approve_option_save));
		Lib.CloseDialogBox();
	}
	public static void RejectOption() throws InterruptedException{
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(D.$p_modal_dialog)));
		Thread.sleep(D.waitTime);
		Lib.ClickButton(By.xpath(D.$p_reject_option_save));
	}	
	public static void SetPrivatePrice(String pricePerc) throws InterruptedException{
		Lib.InputData(pricePerc, By.xpath(D.$p_private_seat_discount_percentage));
		Lib.SendSpecialKey(Keys.TAB);
		Lib.SendSpecialKey(Keys.TAB);
		Lib.ClickButton(By.cssSelector(D.$p_private_seat_save_button));
		
	}
	public static void SetPPPPrice(String price) throws InterruptedException{
		Lib.InputData(price, By.xpath(D.$p_private_seat_PPP_Price));
		Lib.SendSpecialKey(Keys.TAB);
		Lib.SendSpecialKey(Keys.TAB);
		Lib.ClickButton(By.cssSelector(D.$p_private_seat_save_button));
		
	}

	public static void SelectANegotiationy(String price) throws InterruptedException{	
		String path = D.$p_negotiation_row_prefix + price + D.$p_negotiation_row_suffix;
		Lib.ClickButton(By.xpath(path));
	}
	public static void ExpandANegotiationy(String price) throws InterruptedException{	
		String path = D.$p_negotiation_row_prefix + price + D.$p_negotiation_row_suffix;
		Lib.ClickContextSensitiveItem(By.xpath(path), By.xpath(D.$p_negotiation_expand));
	}
	public static void NegotiationyGiveCounterPrice(String seq, String price) throws InterruptedException{	
		String path = D.$p_negotiation_edit_row_prefix + seq + "]/div/div/table/tbody/tr/td[9]/input";
		Lib.ClickButton(By.xpath(path));
		D.driver.findElement(By.xpath(path)).clear();
		Thread.sleep(D.waitTime / 2); 
		D.driver.findElement(By.xpath(path)).sendKeys(price);
		Lib.SendSpecialKey(Keys.TAB);
		Thread.sleep(D.waitTime); 
		D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
	}
	public static void EditNegotiationyDeselectAll() throws InterruptedException{	
 		List<WebElement> elements = D.driver.findElements(By.xpath(D.$p_negotiation_check_box));
 		for(WebElement ele: elements){
 			if(ele.isDisplayed()){
 				ele.click();
 				D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
 				Thread.sleep(D.waitTime);
 			}
 		}
	}
	public static void EditNegotiationySelectOne(String negSeq) throws InterruptedException{	
		String path = D.$p_negotiation_edit_row_prefix + negSeq + "]/div/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/span/img";
		Lib.ClickButton(By.xpath(path));
		Thread.sleep(D.waitTime); 
		D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
	}
	public static void ExpandedNegotiationyRejectOne(String productName) throws InterruptedException{	
		String path = D.$p_negotiation_expanded_row_prefix + productName + D.$p_negotiation_expanded_row_reject_suffix;
		Lib.ClickButton(By.xpath(path));
		Lib.CloseDialogBox();
	}
	public static void ExpandedNegotiationyAcceptOne(String productName) throws InterruptedException{	
		String path = D.$p_negotiation_expanded_row_prefix + productName + D.$p_negotiation_expanded_row_accept_suffix;
		Lib.ClickButton(By.xpath(path));
		Lib.CloseDialogBox();
	}
	public static void ExpandedNegotiationyEditOne(String productName) throws InterruptedException{	
		String path = D.$p_negotiation_expanded_row_prefix + productName + D.$p_negotiation_expanded_row_edit_suffix;
		Lib.ClickButton(By.xpath(path));
	}
}
