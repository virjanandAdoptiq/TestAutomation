package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class ZZZ_D6Dev1522ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("D6Dev1522BuyPlusPMB", 13), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
