package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_D3Dev1102ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("D3Dev1102BuyDealIDBulkDealIDADV2", 7), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
