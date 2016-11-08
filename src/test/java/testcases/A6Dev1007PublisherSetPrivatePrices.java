package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import publisher.ExchangeP;
import toplevel.D;
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;

@Test(groups = {"SetPrivateDealPrice"}, dependsOnGroups="BuyBulkDealID", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class A6Dev1007PublisherSetPrivatePrices {

	  @Test
	  public void setPrices() throws InterruptedException{
		    D.FAILURE_INDICATION = 3; //if test failed, logout and close browser
		    //Get orders from MB
			Top.StartBroswer();
			String menu = D.$Menu + D.$MenuExchange + ")]";
			Top.Login(Helper.Res2, "Welkom01@1");
			Top.ClickButton(By.xpath(menu));
			ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
			Top.ClickButton(By.cssSelector(D.$p_orderoverview_privateSeat_tab));
			
//			ExchangeP.SetPrivatePrice("CD101V - Pagina 2", "30");			
			ExchangeP.SetPrivatePrice("CD101V - Pagina 4-5", "50");
			ExchangeP.SetPrivatePrice("CD101V - Pagina 3", "40");
			
			Top.Logout();
			Top.CloseBrowser();
	  }
	 }
