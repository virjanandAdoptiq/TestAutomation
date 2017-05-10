package testcases.emails_check;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_Fa1Dev0000ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("Fa1Dev0000NegotiationNonBundleCounterOfferAcceptReject", 14), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
