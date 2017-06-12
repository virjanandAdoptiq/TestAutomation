package advertiser;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import toplevel.D;
import toplevel.Lib;

public class Exchange {
	public static void GotoBuyerEchangePage() throws InterruptedException{	
		String menu = D.$Menu + D.$MenuExchange + ")]";
		String item = D.$MenuLink + D.$ItemExchange + ")]";
		Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
		SetListView();
	}
	public static void GotoBuyerEchangePageTileView() throws InterruptedException{	
		String menu = D.$Menu + D.$MenuExchange + ")]";
		String item = D.$MenuLink + D.$ItemExchange + ")]";
		Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
		SetTileView();
	}
	public static void SetListView() throws InterruptedException{	
		if(D.driver.findElements(By.xpath(D.$be_Show_ListView_Button)).size() != 0){
			Lib.ClickButton(By.xpath(D.$be_Show_ListView_Button));
		}
	}
	public static void SetTileView() throws InterruptedException{	
		if(D.driver.findElements(By.xpath(D.$be_Show_TileView_Button)).size() != 0){
			Lib.ClickButton(By.xpath(D.$be_Show_TileView_Button));
		}
		
	}
	public static void SelectCampaign(String campaign) throws InterruptedException{	
		Lib.SelectDropdownItem(By.cssSelector(D.$campaign), campaign);
	}
	public static void ClickAMediaTile(String mediaName) throws InterruptedException{	
		String path = D.$be_media_tile_prefix + mediaName + D.$be_media_tile_suffix;
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
		Thread.sleep(D.waitTime);
		D.driver.findElement(By.xpath(path)).click();	
		Thread.sleep(D.waitTime);
		D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(D.$be_media_tile_LookDetails_Button)).click();		
		Thread.sleep(D.waitTime);
		D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
//		Lib.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$be_media_tile_LookDetails_Button));
	}
//Select filter
	public static void SelectPhase(String phase) throws InterruptedException{	
		Lib.SelectDropdownItem(By.xpath(D.$be_filter_phase), phase);
	}
	public static void SelectMediaType(String type) throws InterruptedException{
		//Lib.SelectDropdownItem(By.xpath(D.$be_filter_mediatype), type);
		String item = D.$be_filter_check_box + type +"')]";
		Lib.SelectMenuLink(By.xpath(D.$be_filter_mediatype), By.xpath(item));
		Lib.ClickAway();
	}
	public static void SelectSaleOrg(String saleOrg) throws InterruptedException{	
		String path = D.$be_medium_format_prefix + "Verk.organisatie" + D.$be_medium_format_suffix;
		String item = D.$be_filter_check_box + saleOrg +"')]";
		Lib.SelectMenuLink(By.xpath(path), By.xpath(item));
		Lib.ClickAway();   
	}
	public static void SelectMedia(String media) throws InterruptedException{	
		String path = D.$be_medium_format_prefix + "Medium" + D.$be_medium_format_suffix;
		String item = D.$be_filter_check_box + media +"')]";
		Lib.SelectMenuLink(By.xpath(path), By.xpath(item));
		Lib.ClickAway();
	}
	public static void SelectFrequency(String freq) throws InterruptedException{	
		Lib.SelectDropdownItem(By.xpath(D.$be_filter_frequency), freq);
	}
	public static void SelectFormat(String format) throws InterruptedException{	
		String path = D.$be_medium_format_prefix + "Formaatcode" + D.$be_medium_format_suffix;
		String item = D.$be_filter_check_box + format +"')]";
		Lib.SelectMenuLink(By.xpath(path), By.xpath(item));
		Lib.ClickAway();
	}
	public static void SelectSharedPage(String shareP) throws InterruptedException{	
		String path = D.$be_medium_format_prefix + "Pagina-aandeel" + D.$be_medium_format_suffix;
		String item = D.$be_filter_check_box + shareP +"')]";
		Lib.SelectMenuLink(By.xpath(path), By.xpath(item));
		Lib.ClickAway();
	}
	public static void SelectWeekday(String wDay) throws InterruptedException{	
		Lib.SelectDropdownItem(By.xpath(D.$be_filter_weekday), wDay);
    }
	public static void SelectCategory(String cat) throws InterruptedException{	
		String path = D.$be_medium_format_prefix + "Contextueel" + D.$be_medium_format_suffix;
		String item = D.$be_filter_check_box + cat +"')]";
		Lib.SelectMenuLink(By.xpath(path), By.xpath(item));
		Lib.ClickAway();
	}
	public static void SelectPlusProposition(String yesOrNo) throws InterruptedException{	
		Lib.SelectDropdownItem(By.xpath(D.$be_filter_pluspropercition), yesOrNo);
	}
	public static String GetTradingCountryName() throws InterruptedException{
		Thread.sleep(D.waitTime);
		return D.driver.findElement(By.xpath(D.$be_filter_land)).getText();
	}
	public static void EnterFromThroughDate(String date) throws InterruptedException{	
		Lib.FindElement(By.cssSelector(D.$be_date_filter));
		List<WebElement> elements = D.driver.findElements(By.cssSelector(D.$be_date_filter));
		for(WebElement ele: elements){
			ele.clear();
			ele.sendKeys(Keys.chord(Keys.COMMAND, "a"), date);
			Thread.sleep(D.waitTime);
		}
	}

//walk around for DEV-1310
	public static void EnterFromThroughDate2(String date1, String date2) throws InterruptedException{	
		Lib.FindElement(By.cssSelector(D.$be_date_filter));
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
		Lib.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$be_addToMyLots));
	}
	
	public static void SelectAInventory(String name) throws InterruptedException{	
		String path = D.$be_inventory_row_prefix + name + D.$be_inventory_row_suffix;
		Lib.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$bm_lot_select_check));
	}
	
	public static String GetAInventoryPrice(String product, String name) throws InterruptedException{	
		String path = D.$be_inventory_row_prefix + product + D.$be_inventory_row_suffix;
		return D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(name)).getText().replace("€", "").replaceAll("\\s","");
	}
	public static void FindAMediaWithPercentage(String mediaName, String percentage) throws InterruptedException{	
		String path = "//span[text()='" + mediaName + "']/../../..//span[text()='" + percentage + "']";
		
		Lib.FindElement(By.xpath(path));
	}
	
	//private offers
	public static void GoToPersonalOffer() throws InterruptedException{
		//Lib.ClickButton(By.xpath(D.$be_offer_notification));
		String menu = D.$Menu + D.$MenuExchange + ")]";
		String item = D.$MenuLink + D.$ItemPersonalOffer + ")]";
		Lib.SelectMenuLink(By.xpath(menu), By.xpath(item));
	}
	//public offer
	public static void SelectAnOffer(String name) throws InterruptedException{	
		String path = D.$b_offer_inventory_path + name + "']/..";
		Lib.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$b_offer_select_checkbox));
	}
}
