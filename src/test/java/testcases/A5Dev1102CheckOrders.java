package testcases;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Mylots;
import publisher.ExchangeP;
import toplevel.D;
import toplevel.Helper;
import toplevel.TestFailureListener;
import toplevel.Top;

@Test(groups = {"CheckOrder"}, dependsOnGroups="Buy", alwaysRun = true)
@Listeners(TestFailureListener.class)
public class A5Dev1102CheckOrders {
	String[][] orders; 
	String[][] ordersUG; 
	String[][] ordersRes; 
	String[][] ordersRes2;
	String[][] ordersPub;
	
	  @BeforeClass
	  public void fatchData() throws InterruptedException{
		    D.FAILURE_INDICATION = 3; //if test failed, logout and close browser
		    //Get orders from MB
			Top.StartBroswer();
			Top.Login(Helper.MB,"Welkom01@1");	
			Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   //Go to OrderOverview
			orders = Helper.SortOrders(Helper.GetTableContent(D.$b_orderoverview_table, 3, 12));
			Top.Logout(); 
			//Get orders from UG,  = MB index=3	 
			Top.Login(Helper.UG, "Welkom01@1");
			String menu = D.$Menu + D.$MenuExchange + ")]";
			Top.ClickButton(By.xpath(menu));
			//Top.ClickButton(By.xpath(D.$Exchange));
			ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
			ordersUG = Helper.GetTableContent(D.$p_orderoverview_table, 1, 10);
			Top.Logout(); 
			//Get orders from Res2, = MB index=0,1
			Top.Login(Helper.Res2, "Welkom01@1");
			 Top.ClickButton(By.xpath(menu));
			ExchangeP.SelectLeftMenu(D.$p_exchange_left_orderOverview);
			ordersRes2 = Helper.GetTableContent(D.$p_orderoverview_table, 2, 10);
			Top.Logout();
			Top.CloseBrowser();
			
			ordersPub = Helper.SortOrders(ArrayUtils.addAll(ArrayUtils.addAll(ordersUG, ordersRes),ordersRes2));
			
			D.FAILURE_INDICATION = 0; //if the later test failed, do nothing
	  }
	  public void compareOrdersPubSideAndMBSide(){
		  SoftAssert softAssert = new SoftAssert();	
		  for(int i = 0; i < 3; i++){
			  softAssert.assertEquals(orders[i][0],ordersPub[i][0]);   //the order number 
			  softAssert.assertEquals(orders[i][1], ordersPub[i][2]);  
			  softAssert.assertEquals(orders[i][2], ordersPub[i][3]);
			  softAssert.assertEquals(orders[i][5], ordersPub[i][6]);
			  softAssert.assertEquals(ordersPub[i][7],Helper.MB);
			  softAssert.assertEquals(orders[i][6], ordersPub[i][8]);
			  softAssert.assertEquals(orders[i][8], ordersPub[i][9]);
		  }
		  softAssert.assertAll();
	  }
	  
	  @Test(dataProvider="inputdata", alwaysRun = true)
	  public void checkOrderValue(String pubDate, String media, String product,String advertiser, String campaign,
			             String price, String ppp, String surcharge) throws InterruptedException {	
		  SoftAssert softAssert = new SoftAssert();	
		  int index =0;
		  for(int i = 0; i < 4; i++){
			  if(orders[i][5].equals(product)){
				  index = i;
				  softAssert.assertEquals(orders[index][1], pubDate);
				  softAssert.assertEquals(orders[index][2], media);
				  softAssert.assertEquals(orders[index][6], advertiser);
				  softAssert.assertEquals(orders[index][7], campaign);
				  softAssert.assertEquals(orders[index][8], price);
				  softAssert.assertEquals(orders[index][9], ppp);
				  softAssert.assertEquals(orders[index][10], surcharge);	
				  break;
			  }
			  index = i + 1;
		  }
		  softAssert.assertNotEquals(index, 4);
		  
		  softAssert.assertAll();
	  }		   
	//THE PARAMETERS ARE:
		 @DataProvider
		  public Object[][] inputdata() {
		    return new Object[][] { 
		      {Helper.noTuesday,Helper.BuyNow,"CD101V - Cover 2",Helper.ADV,Helper.CampaignADV,"1.575,00","Nee","0,00"},
		      {Helper.buyDay1,Helper.BuyNow2,"CD102VS - Pagina 3",Helper.ADV2,Helper.CampaignADV2,"2.062,50","Nee","0,00"},
		      {Helper.buyDay1,Helper.BuyNow2,"CD101V - Pagina 2",Helper.ADV2,Helper.CampaignADV2,"4.125,00","Nee","0,00"},
		    };
		  }
}
