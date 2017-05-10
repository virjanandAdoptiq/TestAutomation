package testcases.emails_check;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_F5Dev0000ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("F5Dev0000NegotiationBundleAccepted", 5), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
