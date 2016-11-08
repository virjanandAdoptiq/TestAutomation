package advertiser;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import toplevel.D;
import toplevel.Helper;
import toplevel.Top;

public class Exchange {
	public static void GotoBuyerEchangePage() throws InterruptedException{	
		String menu = D.$Menu + D.$MenuExchange + ")]";
		String item = D.$MenuLink + D.$ItemExchange + ")]";
		Top.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}	
	public static void SelectCampaign(String campaign) throws InterruptedException{	
		Top.SelectDropdownItem(By.cssSelector(D.$campaign), campaign);
	}
//Select filter
	public static void SelectPhase(String phase) throws InterruptedException{	
		Top.SelectDropdownItem(By.xpath(D.$be_filter_phase), phase);
	}
	public static void SelectMediaType(String type) throws InterruptedException{
		Top.SelectDropdownItem(By.xpath(D.$be_filter_mediatype), type);
	}
	public static void SelectSaleOrg(String saleOrg) throws InterruptedException{	
		String path = D.$be_medium_format_prefix + "Verk.organisatie" + D.$be_medium_format_suffix;
		String item = D.$be_filter_check_box + saleOrg +"')]";
		Top.SelectMenuLink(By.xpath(path), By.xpath(item));
		Top.ClickAway();   //click some where to let the pops up form go back
	}
	public static void SelectMedia(String media) throws InterruptedException{	
		String path = D.$be_medium_format_prefix + "Medium" + D.$be_medium_format_suffix;
		String item = D.$be_filter_check_box + media +"')]";
		Top.SelectMenuLink(By.xpath(path), By.xpath(item));
		Top.ClickAway();
	}
	public static void SelectFrequency(String freq) throws InterruptedException{	
		Top.SelectDropdownItem(By.xpath(D.$be_filter_frequency), freq);
	}
	public static void SelectFormat(String format) throws InterruptedException{	
		String path = D.$be_medium_format_prefix + "Formaatcode" + D.$be_medium_format_suffix;
		String item = D.$be_filter_check_box + format +"')]";
		Top.SelectMenuLink(By.xpath(path), By.xpath(item));
		Top.ClickAway();
	}
	public static void SelectSharedPage(String shareP) throws InterruptedException{	
		String path = D.$be_medium_format_prefix + "Pagina-aandeel" + D.$be_medium_format_suffix;
		String item = D.$be_filter_check_box + shareP +"')]";
		Top.SelectMenuLink(By.xpath(path), By.xpath(item));
		Top.ClickAway();
	}
	public static void SelectWeekday(String wDay) throws InterruptedException{	
		Top.SelectDropdownItem(By.xpath(D.$be_filter_weekday), wDay);
    }
	public static void SelectCategory(String cat) throws InterruptedException{	
		String path = D.$be_medium_format_prefix + "Categorie" + D.$be_medium_format_suffix;
		String item = D.$be_filter_check_box + cat +"')]";
		Top.SelectMenuLink(By.xpath(path), By.xpath(item));
		Top.ClickAway();
	}
	public static void SelectPlusProposition(String yesOrNo) throws InterruptedException{	
		Top.SelectDropdownItem(By.xpath(D.$be_filter_pluspropercition), yesOrNo);
	}
	public static void EnterFromThroughDate(String date) throws InterruptedException{	
		Helper.FindElement(By.cssSelector(D.$be_date_filter));
		List<WebElement> elements = D.driver.findElements(By.cssSelector(D.$be_date_filter));
		for(WebElement ele: elements){
			ele.sendKeys(Keys.chord(Keys.COMMAND, "a"), date);
			Thread.sleep(D.waitTime);
		}
	}

//walk around for DEV-1310
	public static void EnterFromThroughDate2(String date1, String date2) throws InterruptedException{	
		Helper.FindElement(By.cssSelector(D.$be_date_filter));
		List<WebElement> elements = D.driver.findElements(By.cssSelector(D.$be_date_filter));
		int i = 1;
		for(WebElement ele: elements){
			if(i == 1){
			      ele.sendKeys(Keys.chord(Keys.COMMAND, "a"), date1);
			      i = i +1;
			} else {
				ele.sendKeys(Keys.chord(Keys.COMMAND, "a"), date2);
			}
			Thread.sleep(D.waitTime);
		}
	}
//End-walk around for DEV-1310	
	
	public static void AddToMyLots(String name) throws InterruptedException{	
		String path = D.$be_inventory_row_prefix + name + D.$be_inventory_row_suffix;
		Top.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$be_addToMyLots));
	}
	
	public static void SelectAInventory(String name) throws InterruptedException{	
		String path = D.$be_inventory_row_prefix + name + D.$be_inventory_row_suffix;
		Top.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$bm_lot_select_check));
	}
	
	public static String GetAInventoryPrice(String product, String name) throws InterruptedException{	
		String path = D.$be_inventory_row_prefix + product + D.$be_inventory_row_suffix;
		return D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(name)).getText().replace("€", "").replaceAll("\\s","");
	}
	
	//offers
	public static void ClickOfferNotification() throws InterruptedException{
		Top.ClickButton(By.xpath(D.$be_offer_notification));
	}
}
