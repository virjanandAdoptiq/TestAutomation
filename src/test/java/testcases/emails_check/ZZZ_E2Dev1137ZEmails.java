package testcases.emails_check;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_E2Dev1137ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("E2Dev1137AutoBidMBBidADV2", 27), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
