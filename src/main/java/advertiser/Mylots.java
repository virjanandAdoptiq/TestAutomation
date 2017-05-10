package advertiser;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import toplevel.D;
import toplevel.Lib;

public class Mylots {
	
	public static void SelectMyLotsMenuItem(String item) throws InterruptedException{
		//String path = D.$MyLotsLink + item + ")]";
		String menu = D.$Menu + D.$MenuMyLots + ")]";
		String path = D.$MenuLink + item + ")]";
		Lib.SelectMenuLink(By.xpath(menu), By.xpath(path));
	}
	
	
	public static void ClickApplyFilterButton() throws InterruptedException{	
 		List<WebElement> elements = D.driver.findElements(By.cssSelector(D.$bm_lot_apply_filter_button));
 		for(WebElement ele: elements){
 			if(ele.isDisplayed()){
 				ele.click();
 				Thread.sleep(D.waitTime);
 			}
 		}
 /*		
		if(D.driver.findElement(By.cssSelector(D.$bm_lot_apply_filter_button)).isDisplayed()){
			D.driver.findElement(By.cssSelector(D.$bm_lot_apply_filter_button)).click();
			Thread.sleep(D.waitTime);
		}
*/		
	}

	public static void SetFilterOrderShow() throws InterruptedException{
		Thread.sleep(D.waitTime);
		if(D.driver.findElements(By.xpath(D.$bm_lots_order_filter_on)).isEmpty()){
			D.driver.findElement(By.xpath(D.$bm_lots_order_filter_off)).click();
			Thread.sleep(D.waitTime);	
		} 
	}
    public static void SetFilterOrderNotShow() throws InterruptedException{
    	Thread.sleep(D.waitTime * 2);
    	if(D.driver.findElements(By.xpath(D.$bm_lots_order_filter_off)).isEmpty()){
			D.driver.findElement(By.xpath(D.$bm_lots_order_filter_on)).click();
			Thread.sleep(D.waitTime);	
		} 
	}
    public static void SetFilterBidNotShow() throws InterruptedException{
    	Thread.sleep(D.waitTime);
    	if(D.driver.findElements(By.xpath(D.$bm_lots_bid_filter_off)).isEmpty()){
			D.driver.findElement(By.xpath(D.$bm_lots_bid_filter_on)).click();
			Thread.sleep(D.waitTime);	
		} 
	}
    
	public static void SelectALot(String product) throws InterruptedException{		
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/td[1]/table/tbody/tr/td/span/img";
		Lib.ClickButton(By.xpath(path));	
	}
	public static void SelectAllLot(String medium) throws InterruptedException{		
		String path = D.$bm_lot_row_prefix + medium + D.$bm_lot_row_suffix + "/td[1]/table/tbody/tr/td/span/img";
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
			
		List<WebElement> elements = D.driver.findElements(By.xpath(path));
		for(WebElement ele: elements){
			ele.click();
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
	public static boolean CheckLotCoflicting(String product, String cof) throws InterruptedException{		
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/td[4]" + cof;
		try {
		    D.driver.findElement(By.xpath(path));
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	public static void BuyBidOptionConfirm(String which) throws InterruptedException{
		Lib.FindElement(By.cssSelector(which));
		Lib.ClickButton(By.cssSelector(which));
		D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
		Thread.sleep(D.waitTime);
		Lib.CloseDialogBox();
	}
	public static void ExpandALot(String product, String color) throws InterruptedException{		
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix;
		if(color.equalsIgnoreCase("grey")){
		  Lib.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$bm_lot_expand_arrow));
		  return;
		}
		if(color.equalsIgnoreCase("red")){
		  Lib.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$bm_lot_expand_arrow_red));
		}
	}
	public static void SelectPublisher(String product, String publisher) throws InterruptedException{	
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
		WebElement mySelectElm = D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(D.$bm_lot_select_publisher));
		Select mySelect= new Select(mySelectElm);
		Thread.sleep(D.waitTime);
		mySelect.selectByVisibleText(publisher);
		Thread.sleep(D.waitTime);
		D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
	}
	public static void SetAutoBidPrice(String product,String priceLevel) throws InterruptedException{	
		String fatherpath = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
		D.driver.findElement(By.xpath(fatherpath)).findElement(By.cssSelector(D.$bm_lot_autobid_button)).click();
		//select price from the pops up
		String path = D.$bm_lot_autobid_prices_prefix + priceLevel +  D.$bm_lot_autobid_prices_sufix;
		Lib.ClickButton(By.xpath(path));
		Lib.ClickButton(By.xpath(D.$bm_lot_autobid_select_button));
		Lib.CloseDialogBox();
	}
/*	public static void SetUnderBidPrice(String product, String price) throws InterruptedException{
	   String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
	   Lib.ClickContextSensitiveItem(By.xpath(path), By.xpath(D.$bm_lot_underbid_input));
	   D.driver.findElement(By.xpath(path)).findElement(By.xpath(D.$bm_lot_underbid_input)).sendKeys(price);
	   Lib.SendSpecialKey(Keys.TAB);
	   Thread.sleep(D.waitTime); 
	}
	public static void ChangeUnderBidPrice(String product, String price) throws InterruptedException{
		   String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
		   D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(D.$bm_lot_underbid_change_button)).click();
		   Lib.SendSpecialKey(Keys.CLEAR);
		   Lib.InputData(price, By.xpath(D.$bm_lot_underbid_change_input));
		   Lib.SendSpecialKey(Keys.TAB);
		   Thread.sleep(D.waitTime); 
		   Lib.SendSpecialKey(Keys.ENTER);
		   Thread.sleep(D.waitTime); 
		   Lib.CloseDialogBox();
		}
*/	
	public static void SetCampaign(String product,String campaignNo) throws InterruptedException{	
		String fatherpath = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
		D.driver.findElement(By.xpath(fatherpath)).findElement(By.cssSelector(D.$bm_lot_select_campaign_button)).click();
		//select campaign from the pops up
		String path = D.$bm_lot_autobid_prices_prefix + campaignNo +  D.$bm_lot_autobid_prices_sufix;
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
		Thread.sleep(D.waitTime);
		D.driver.findElement(By.xpath(path)).click();
		Lib.ClickButton(By.xpath(D.$bm_lot_autobid_select_button));
		Lib.CloseDialogBox();
	}
	public static void SelectPPP(String product, String thePPP, String number) throws InterruptedException{		   
			String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
			WebElement mySelectElm = D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(D.$bm_lot_select_PPP));
			Select mySelect= new Select(mySelectElm);
			mySelect.selectByVisibleText(thePPP);
			Thread.sleep(D.waitTime);
			
			Lib.ClickContextSensitiveItem(By.xpath(path), By.xpath(D.$bm_lot_PPP_Nr));
			D.driver.findElement(By.xpath(path)).findElement(By.xpath(D.$bm_lot_PPP_Nr)).sendKeys(number);
			Lib.SendSpecialKey(Keys.TAB);
			Thread.sleep(D.waitTime); 
			D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
		}
	public static void SetDealID(String product, String theID) throws InterruptedException{
		   String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix + "/following-sibling::tr";
		   Lib.ClickContextSensitiveItem(By.xpath(path), By.xpath(D.$bm_lot_dealid_input));
		   D.driver.findElement(By.xpath(path)).findElement(By.xpath(D.$bm_lot_dealid_input)).sendKeys(theID);
		   Lib.SendSpecialKey(Keys.TAB);
		   Thread.sleep(D.waitTime); 
		}
	public static void SetBulkDealID(String dealID) throws InterruptedException{
		Lib.ClickButton(By.cssSelector(D.$bm_lot_bulkDealID_icon));
		Lib.InputData(dealID, By.xpath(D.$bm_bulkDealIDForm_DealID));
		Lib.ClickButton(By.xpath(D.$bm_bulkDealIDForm_Save));
		
	}
	public static void OpenLotInfon(String medium) throws InterruptedException{	
		String path = D.$bm_lot_row_prefix + medium + D.$bm_lot_row_suffix;
		Lib.ClickContextSensitiveItem(By.xpath(path), By.cssSelector(D.$bm_lot_info_icon));
	}
	public static void CloseLotInfo() throws InterruptedException{	
		Lib.ClickButton(By.cssSelector(D.$bm_lot_info_closeButton));
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
	
	public static String GetLotMaterialInfo(String name) throws InterruptedException{	
		try {
		Thread.sleep(D.waitTime);
		return D.driver.findElement(By.xpath(name)).getText().replaceAll("\\s","");
		} catch (Exception e) {
			return "No this info";
		}
	}
	
	public static String CheckLotRowPrice(String product, String name) throws InterruptedException{	
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix;
		Thread.sleep(D.waitTime);
		return D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(name)).getText().replace("€", "").replaceAll("\\s","");
	}
	public static void OpenConflictingLotInfon(String product) throws InterruptedException{	
		String path = D.$bm_lot_row_prefix + product + D.$bm_lot_row_suffix;
		Lib.ClickContextSensitiveItem(By.xpath(path), By.xpath(D.$bm_lot_status_conflicting));
	}
	public static void Negotiation(String price, String date, boolean bundle) throws InterruptedException{	
		Lib.InputData(price, By.xpath(D.$bm_lot_negotiation_proposed_price));
		Lib.InputData(date, By.xpath(D.$bm_lot_negotiation_Expiration_date));
		if(bundle){
			Lib.DoubleClicky(By.xpath(D.$bm_lot_negotiation_Bundled));
		}
		Lib.ClickButton(By.xpath(D.$bm_lot_negotiation_Negotiation_Button));
		Lib.CloseDialogBox();
	}
	public static void NegotiationPingPongPrice(String price) throws InterruptedException{	
		Lib.InputData(price, By.xpath(D.$bm_lot_negotiation_proposed_price));
		Lib.SendSpecialKey(Keys.TAB);
		Lib.ClickButton(By.xpath(D.$bm_lot_negotiation_Negotiation_Button));
		Lib.CloseDialogBox();
	}
	public static void NegotiationDraft(String price) throws InterruptedException{	
		Lib.InputData(price, By.xpath(D.$bm_lot_negotiation_proposed_price));
		Lib.ClickButton(By.xpath(D.$bm_lot_negotiation_SaveDraft));
		Lib.ClickAway();
		Lib.ClickButton(By.xpath(D.$bm_lot_negotiation_draft_confirm_save));
	}
	//Group
	public static void SelectAGroup(String type) throws InterruptedException{		
		String path = D.$bm_lot_group_row_prefix + type + D.$bm_lot_group_row_suffix + "/td[1]/table/tbody/tr/td/span/img";
		Lib.ClickButton(By.xpath(path));	
	}	
	public static String GetGroupLotInfo(String type, String name) throws InterruptedException{	
		String path = D.$bm_lot_group_row_prefix + type + D.$bm_lot_group_row_suffix;
		Thread.sleep(D.waitTime);
		return D.driver.findElement(By.xpath(path)).findElement(By.cssSelector(name)).getText().trim();
	}
	public static void ExpandAGroup(String type) throws InterruptedException{		
		String path = D.$bm_lot_group_row_prefix + type + D.$bm_lot_group_row_suffix;
		if(D.driver.findElements(By.xpath(D.$bm_lot_group_closed)).isEmpty()){
			return;
		} else {
		  Lib.ClickContextSensitiveItem(By.xpath(path), By.xpath(D.$bm_lot_group_closed));
		  return;
		}
	}
	public static void SelectALotInsideGroup(String product) throws InterruptedException{		
		String path = D.$bm_lot_group_inside_row_prefix + product + D.$bm_lot_group_inside_row_suffix + "/td[1]/table/tbody/tr/td/span/img";
		Lib.ClickButton(By.xpath(path));
	}
	public static boolean CheckGroupLotStatus(String product, String status) throws InterruptedException{		
		String path = D.$bm_lot_group_inside_row_prefix + product + D.$bm_lot_group_inside_row_suffix + "/td[3]" + status;
		try {
		    D.driver.findElement(By.xpath(path));
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
