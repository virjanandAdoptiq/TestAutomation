package admin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import toplevel.D;
import toplevel.Top;

public class Admin {
	public static void GotoDevelop() throws InterruptedException{	
//		Top.ClickButton(By.xpath(D.$ad_developer));
		String menu = D.$Menu + D.$MenuDeveloper + ")]";
		Top.ClickButton(By.xpath(menu));
	}

	public static void GotoUnitTest() throws InterruptedException{	
		Top.ClickButton(By.xpath(D.$ad_unit_tests));
	}

	public static void GotoUnitMenu(String menu) throws InterruptedException{	//menu=Media|General
		String path = D.$ad_unit_tests_menu + menu + "')]";
		Top.ClickButton(By.xpath(path));	
	}
	
	public static void EnterName(String name, String email) throws InterruptedException{	
		Top.ClickButton(By.cssSelector(D.$ad_unit_enter_name));
		Top.InputData(name, By.xpath(D.$ad_unit_input_name));
		Top.InputData(email, By.xpath(D.$ad_unit_input_email));
		Top.SendSpecialKey(Keys.TAB);
		Top.ClickButton(By.cssSelector(D.$ad_unit_input_save));
	}
	
	public static void StartRun() throws InterruptedException{	
//	don't use	Top.ClickButton(D.$ad_unit_run);   
		D.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(D.$ad_unit_run)));
		Thread.sleep(D.waitTime);
		D.driver.findElement(By.cssSelector(D.$ad_unit_run)).click(); 	
			
		Thread.sleep(D.waitTime * 150);
		D.longWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
		
	}
	

}
