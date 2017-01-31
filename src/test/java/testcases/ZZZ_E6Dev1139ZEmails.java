package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_E6Dev1139ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("E6Dev1139UnderBidADVMB", 10), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
