package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_C1Dev1002ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("C1Dev1002OptionRequestAcceptRejectADV", 8), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
