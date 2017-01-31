package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_E4Dev1525ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("E4Dev1525BulkBidMB", 6), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
