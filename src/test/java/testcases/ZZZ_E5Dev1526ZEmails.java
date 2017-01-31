package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_E5Dev1526ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("E5Dev1526UnderBidMBADV2", 10), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
