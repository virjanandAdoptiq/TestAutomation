package testcases.emails_check;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_D1Dev1101ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertEquals(Lib.checkEmails("D1Dev1101BuyCheckOrdersMB", 6), "emailCorrect");			
		  softAssert.assertAll(); 
	  }
}
