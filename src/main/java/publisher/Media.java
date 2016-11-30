package publisher;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import toplevel.D;
import toplevel.Lib;

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
		//select MB
		Lib.SelectDropdownItem(By.xpath(D.$p_option_details_mb), mb);
		//select ad
		Lib.SelectDropdownItem(By.xpath(D.$p_option_details_ad), ad);
		//Enter Deal ID and price
		Lib.InputData(dealID, By.xpath(D.$p_option_details_dealid));
		Lib.InputData(price, By.xpath(D.$p_option_details_price));
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
		Lib.SelectDropdownItem(By.xpath(D.$p_option_details_mb), ad);
		//Enter Deal ID and price
		Lib.InputData(dealID, By.xpath(D.$p_option_details_dealid));
		Lib.InputData(price, By.xpath(D.$p_option_details_price));
		//save
		Lib.ClickButton(By.xpath(D.$p_approve_option_save));
		Lib.CloseDialogBox();
	}		
}