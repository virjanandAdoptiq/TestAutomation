package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_E1Dev1523ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("E1Dev1523AutoBidADVBidMB", 27), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
