package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_E7Dev987ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("E7Dev987UnderBidModifyDelete", 8), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
