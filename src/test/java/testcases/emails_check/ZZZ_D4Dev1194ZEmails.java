package testcases.emails_check;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_D4Dev1194ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("D4Dev1194BuyDealIDBulkDealIDMB", 12), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
