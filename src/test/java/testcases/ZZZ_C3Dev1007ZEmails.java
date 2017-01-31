package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_C3Dev1007ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertEquals(Lib.checkEmails("C3Dev1007OptionPushADV", 2), "emailCorrect");			
		  softAssert.assertAll(); 
	  }
}
