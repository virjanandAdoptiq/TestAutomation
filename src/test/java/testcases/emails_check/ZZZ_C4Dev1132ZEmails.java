package testcases.emails_check;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_C4Dev1132ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertEquals(Lib.checkEmails("C4Dev1132OptionPushMB", 14), "emailCorrect");			
		  softAssert.assertAll(); 
	  }
}
