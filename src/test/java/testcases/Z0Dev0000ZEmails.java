package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

public class Z0Dev0000ZEmails {
	@Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("Z0Dev0000SendDailyOverview", 7), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
}
