package admin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import toplevel.D;
import toplevel.Lib;
import toplevel.Top;

public class Admin {
	public static void GotoDevelop() throws InterruptedException{	
		String menu = D.$Menu + D.$MenuDeveloper + ")]";
		Lib.ClickButton(By.xpath(menu));
	}
	public static void GotoSystem() throws InterruptedException{	
		String menu = D.$Menu + D.$MenuSystem + ")]";
		Lib.ClickButton(By.xpath(menu));
	}
	public static void GotoUnitTest() throws InterruptedException{	
		Lib.ClickButton(By.xpath(D.$ad_unit_tests));
	}

	public static void GotoUnitMenu(String menu) throws InterruptedException{	//menu=Media|General
		String path = D.$ad_unit_tests_menu + menu + "')]";
		Lib.ClickButton(By.xpath(path));	
	}
	
	public static void EnterName(String name, String email) throws InterruptedException{	
		Lib.ClickButton(By.cssSelector(D.$ad_unit_enter_name));
		Lib.InputData(name, By.xpath(D.$ad_unit_input_name));
		Lib.InputData(email, By.xpath(D.$ad_unit_input_email));
		Lib.SendSpecialKey(Keys.TAB);
		Lib.ClickButton(By.cssSelector(D.$ad_unit_input_save));
	}
	
	public static void StartRun() throws InterruptedException{	
//	don't use	Top.ClickButton(D.$ad_unit_run);   
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(D.$ad_unit_run)));
		Thread.sleep(D.waitTime);
		D.driver.findElement(By.cssSelector(D.$ad_unit_run)).click(); 	
			
		Thread.sleep(D.waitTime * 100);
		D.longWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
		
	}
	

}
