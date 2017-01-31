package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Mylots;
import toplevel.D;
import toplevel.Lib;
import toplevel.Top;

@Test
public class ZZ_TestCaseTemplate {
	   String[][] orders; 
	     String product1 = "1/2 pagina volledig staandCover 2";
	      String product2 = "1/2 pagina volledig liggendCover 3";
	      String product3 = "1/2 pagina volledig staandCover 3";

	@BeforeClass
	public void start() throws InterruptedException{
		Top.StartBroswer();
	}
	
	@Test
	public void step1() throws InterruptedException{
//		SoftAssert softAssert = new SoftAssert();
		// the test steps, for example:
		Top.Login(Lib.MB,"Welkom01@1");
		 Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
		 orders = Lib.SortOrders(Lib.GetTableContent(D.$b_orderoverview_table, 6, 12));  
		//
		Top.Logout();
//		softAssert.assertAll();		 
	}
	 @Test(dependsOnMethods="step1")
	  public void checkOrderResults() {	
		     D.FAILURE_INDICATION = 0; //if test failed, do nothing
		     System.out.println(orders[0][0].toString());
			 System.out.println(orders[0][1].toString());
			 System.out.println(orders[0][2].toString());
			 System.out.println(orders[0][3].toString());
			 System.out.println(orders[0][4].toString());
			 System.out.println(orders[0][5].toString());
			 System.out.println(orders[0][6].toString());
			 System.out.println(orders[0][7].toString());
			 System.out.println(orders[0][8].toString());
			 System.out.println(orders[0][9].toString());
			 System.out.println(orders[0][10].toString());
			 System.out.println(orders[0][11].toString());		  
	  }
	 


	@AfterClass
	public void stop() throws InterruptedException {
		Top.CloseBrowser();
	}
}
