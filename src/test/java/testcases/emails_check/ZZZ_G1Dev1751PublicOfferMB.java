package testcases.emails_check;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_G1Dev1751PublicOfferMB {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("G1Dev1751PublicOfferMB", 3), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
