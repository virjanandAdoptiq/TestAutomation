package testcases.emails_check;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_C2Dev1121ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("C2Dev1121OptionRequestAcceptRejectMB", 12), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
