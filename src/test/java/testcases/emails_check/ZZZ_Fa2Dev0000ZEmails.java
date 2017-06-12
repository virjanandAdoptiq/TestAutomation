package testcases.emails_check;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_Fa2Dev0000ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("Fa2Dev0000NegotiationPingPongBundleADV", 31), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
