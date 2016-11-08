package publisher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import toplevel.D;
import toplevel.Top;

public class ExchangeP {
	public static void SelectLeftMenu(String item) throws InterruptedException{	
		String path = D.$p_exchange_left_menu + item +"']";
		Top.ClickButton(By.xpath(path));
	}
	
	public static void SelectRowOverViewTable(String text) throws InterruptedException{
		String path = D.$p_contents_row + text + "']";
		Top.ClickButton(By.xpath(path));
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

		Top.ClickButton(By.xpath(D.$p_approve_option_save));
		Top.CloseDialogBox();
	}
	public static void RejectOption() throws InterruptedException{
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(D.$p_modal_dialog)));
		Thread.sleep(D.waitTime);
		Top.ClickButton(By.xpath(D.$p_reject_option_save));
	}
	public static void GotoUnderbidTab() throws InterruptedException{
		 String menu = D.$Menu + D.$MenuExchange + ")]";
		 Top.ClickButton(By.xpath(menu));
		 ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
		 Top.ClickButton(By.xpath(D.$p_overview_underbid));
	}
	public static void AcceptUnderbid(String product) throws InterruptedException{
		 GotoUnderbidTab();
/*		 String menu = D.$Menu + D.$MenuExchange + ")]";
		 Top.ClickButton(By.xpath(menu));
		 ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
		 Top.ClickButton(By.xpath(D.$p_overview_underbid));
*/
		 ExchangeP.SelectRowOverViewTable(product);
		 Top.ClickButton(By.xpath(D.$p_underbid_accept));
		 Top.SendSpecialKey(Keys.SPACE);
	}
	public static void SetPrivatePrice(String product, String pricePerc) throws InterruptedException{
		String path = D.$p_contents_row + product + "']";
		Top.ClickButton(By.xpath(path));
		Top.ClickButton(By.cssSelector(D.$p_content_row_dispaly_button));
		Top.InputData(pricePerc, By.xpath(D.$p_private_seat_discount_percentage));
		Top.SendSpecialKey(Keys.TAB);
		Top.SendSpecialKey(Keys.TAB);
		Top.ClickButton(By.cssSelector(D.$p_private_seat_save_button));
		
	}
}
