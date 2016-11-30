package publisher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import toplevel.D;
import toplevel.Lib;

public class ExchangeP {
	public static void SelectLeftMenu(String item) throws InterruptedException{	
		String path = D.$p_exchange_left_menu + item +"']";
		Lib.ClickButton(By.xpath(path));
	}
	
	public static void SelectRowOverViewTable(String text) throws InterruptedException{
		String path = D.$p_contents_row + text + "']";
		Lib.ClickButton(By.xpath(path));
	}
	
	public static void AproveOption(String days) throws InterruptedException{
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(D.$p_modal_dialog)));
		Thread.sleep(D.waitTime);
		//there are other elements with the same identifier, but they are not displayed.
		List<WebElement> elements = D.driver.findElements(By.xpath(D.$p_approve_option_deadline_days));
		for(WebElement ele: elements){
			if(ele.isDisplayed()){
				ele.sendKeys(Keys.chord(Keys.COMMAND, "a"), days);
				break;
			}
		}

		Lib.ClickButton(By.xpath(D.$p_approve_option_save));
		Lib.CloseDialogBox();
	}
	public static void RejectOption() throws InterruptedException{
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(D.$p_modal_dialog)));
		Thread.sleep(D.waitTime);
		Lib.ClickButton(By.xpath(D.$p_reject_option_save));
	}
	public static void GotoUnderbidTab() throws InterruptedException{
		 String menu = D.$Menu + D.$MenuExchange + ")]";
		 Lib.ClickButton(By.xpath(menu));
		 ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
		 Lib.ClickButton(By.xpath(D.$p_overview_underbid));
	}
	public static void AcceptUnderbid(String product) throws InterruptedException{
		 GotoUnderbidTab();
		 ExchangeP.SelectRowOverViewTable(product);
		 Lib.ClickButton(By.xpath(D.$p_underbid_accept));
		 Lib.SendSpecialKey(Keys.SPACE);
	}
	public static void RejectUnderbid(String product) throws InterruptedException{
		 GotoUnderbidTab();
		 ExchangeP.SelectRowOverViewTable(product);
		 Lib.ClickButton(By.xpath(D.$p_underbid_reject));
		 Lib.SendSpecialKey(Keys.SPACE);
	}
	public static void SetPrivatePrice(String product, String pricePerc) throws InterruptedException{
		String path = D.$p_contents_row + product + "']";
		Lib.ClickButton(By.xpath(path));
		Lib.ClickButton(By.cssSelector(D.$p_private_seat_row_dispaly_button));
		Lib.InputData(pricePerc, By.xpath(D.$p_private_seat_discount_percentage));
		Lib.SendSpecialKey(Keys.TAB);
		Lib.SendSpecialKey(Keys.TAB);
		Lib.ClickButton(By.cssSelector(D.$p_private_seat_save_button));
		
	}
	public static void SetPPPPrice(String product, String price) throws InterruptedException{
		String path = D.$p_contents_row + product + "']";
		Lib.ClickButton(By.xpath(path));
		Lib.ClickButton(By.cssSelector(D.$p_plus_propersition_row_dispaly_button));
		Lib.InputData(price, By.xpath(D.$p_private_seat_PPP_Price));
		Lib.SendSpecialKey(Keys.TAB);
		Lib.SendSpecialKey(Keys.TAB);
		Lib.ClickButton(By.cssSelector(D.$p_private_seat_save_button));
		
	}
}
