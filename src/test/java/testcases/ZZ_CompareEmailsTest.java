package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import toplevel.Lib;

@Test
public class ZZ_CompareEmailsTest {	
	  @Test
	  public void test() throws InterruptedException{
		  SoftAssert softAssert = new SoftAssert();

		  softAssert.assertEquals(Lib.checkEmails("E4Dev1525BulkBidMB", 4), "emailCorrect");
			
		  softAssert.assertAll(); 
	  }
	
	  
}
