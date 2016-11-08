package advertiser;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import toplevel.D;
import toplevel.Helper;
import toplevel.Top;

public class Mylots {
	
	public static void SelectMyLotsMenuItem(String item) throws InterruptedException{
		//String path = D.$MyLotsLink + item + ")]";
		String menu = D.$Menu + D.$MenuMyLots + ")]";
		String path = D.$MenuLink + item + ")]";
		Top.SelectMenuLink(By.xpath(menu), By.xpath(path));
	}
	
	
	public static void ClickApplyFilterButton() throws InterruptedException{	
		if(D.driver.findElement(By.cssSelector(D.$bm_lot_apply_filter_button)).isDisplayed()){
			D.driver.findElement(By.cssSelector(D.$bm_lot_apply_filter_button)).click();
			Thread.sleep(D.waitTime);
		}
	}

	public static void SetFilterOrderShow() throws InterruptedException{
		if(D.driver.findElements(By.xpath(D.$bm_lots_order_filter_on)).isEmpty()){
			D.driver.findElement(By.xpath(D.$bm_lots_order_filter_off)).click();
			Thread.sleep(D.waitTime);	
		} 
	}
    public static void SetFilterOrderNotShow() throws InterruptedException{
    	if(D.driver.findElements(By.xpath(D.$bm_lots_order_filter_off)).isEmpty()){
			D.driver.findElement(By.xpath(D.$bm_lots_order_filter_on)).click();
			Thread.sleep(D.waitTime);	
		} 
	}
    
	public static void SelectALot(String product) throws InterruptedException{		
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/td[1]";
		Top.ClickButton(By.xpath(path));	
	}
	public static void SelectAllLot(String medium) throws InterruptedException{		
		String path = D.$bm_lot_row_prefix + medium + D.$bm_lot_row_suffix;
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
			
		List<WebElement> elements = D.driver.findElements(By.xpath(path));
		for(WebElement ele: elements){
			ele.findElement(By.cssSelector(D.$bm_lot_select_check)).click();
			Thread.sleep(D.waitTime);
		}
	}

	public static boolean CheckLotStatus(String product, String status) throws InterruptedException{		
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/td[3]" + status;
		try {
		    D.driver.findElement(By.xpath(path));
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	public static void BuyBidOptionConfirm(String which) throws InterruptedException{	
		Top.ClickButton(By.cssSelector(which));
		D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
		Thread.sleep(D.waitTime);
		Top.CloseDialogBox();
	}
	public static void ExpandALot(String product, String color) throws InterruptedException{		
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix;
		if(color.equalsIgnoreCase("grey")){
		  Top.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$bm_lot_expand_arrow));
		  return;
		}
		if(color.equalsIgnoreCase("red")){
		  Top.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$bm_lot_expand_arrow_red));
		}
	}
	public static void SelectPublisher(String product, String publisher) throws InterruptedException{	
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
		WebElement mySelectElm = D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(D.$bm_lot_select_publisher));
		Select mySelect= new Select(mySelectElm);
		mySelect.selectByVisibleText(publisher);
		Thread.sleep(D.waitTime);
		D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
	}
	public static void SetAutoBidPrice(String product,String priceLevel) throws InterruptedException{	
		String fatherpath = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
		D.driver.findElement(By.xpath(fatherpath)).findElement(By.cssSelector(D.$bm_lot_autobid_button)).click();
		//select price from the pops up
		String path = D.$bm_lot_autobid_prices_prefix + priceLevel +  D.$bm_lot_autobid_prices_sufix;
		Top.ClickButton(By.xpath(path));
		Top.ClickButton(By.xpath(D.$bm_lot_autobid_select_button));
		Top.CloseDialogBox();
	}
	public static void SetUnderBidPrice(String product, String price) throws InterruptedException{
	   String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
	   Top.ClickContextSensitiveItem(By.xpath(path), By.xpath(D.$bm_lot_underbid_input));
	   D.driver.findElement(By.xpath(path)).findElement(By.xpath(D.$bm_lot_underbid_input)).sendKeys(price);
	   Top.SendSpecialKey(Keys.TAB);
	   Thread.sleep(D.waitTime); 
	}
	public static void ChangeUnderBidPrice(String product, String price) throws InterruptedException{
		   String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
		   D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(D.$bm_lot_underbid_change_button)).click();
		   Top.SendSpecialKey(Keys.CLEAR);
		   Top.InputData(price, By.xpath(D.$bm_lot_underbid_change_input));
		   Top.SendSpecialKey(Keys.TAB);
		   Thread.sleep(D.waitTime); 
		   Top.SendSpecialKey(Keys.ENTER);
		   Thread.sleep(D.waitTime); 
		   Top.CloseDialogBox();
		}
	public static void SetCampaign(String product,String campaignNo) throws InterruptedException{	
		String fatherpath = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
		D.driver.findElement(By.xpath(fatherpath)).findElement(By.cssSelector(D.$bm_lot_select_campaign_button)).click();
		//select campaign from the pops up
		String path = D.$bm_lot_autobid_prices_prefix + campaignNo +  D.$bm_lot_autobid_prices_sufix;
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
		Thread.sleep(D.waitTime);
		D.driver.findElement(By.xpath(path)).click();
		Top.ClickButton(By.xpath(D.$bm_lot_autobid_select_button));
		Top.CloseDialogBox();
	}
	public static void SetBulkDealID(String dealID) throws InterruptedException{
		Top.ClickButton(By.cssSelector(D.$bm_lot_bulkDealID_icon));
		Top.InputData(dealID, By.xpath(D.$bm_bulkDealIDForm_DealID));
		Top.ClickButton(By.xpath(D.$bm_bulkDealIDForm_Save));
		
	}
	public static void OpenLotInfon(String medium) throws InterruptedException{	
		String path = D.$bm_lot_row_prefix + medium + D.$bm_lot_row_suffix;
		Top.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$bm_lot_info_icon));
	}
	public static void CloseLotInfo() throws InterruptedException{	
		Top.ClickButton(By.cssSelector(D.$bm_lot_info_closeButton));
	}
	public static String GetLotInfo(String name) throws InterruptedException{	
		try {
//		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(name)));
		Thread.sleep(D.waitTime);
		return D.driver.findElement(By.cssSelector(name)).getText().replace("€", "").replaceAll("\\s","");
		} catch (Exception e) {
			return "No this info";
		}
	}
	
	public static String CheckLotRowPrice(String product, String name) throws InterruptedException{	
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix;
		Thread.sleep(D.waitTime);
		return D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(name)).getText().replace("€", "").replaceAll("\\s","");
	}
}
