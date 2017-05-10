package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import advertiser.Exchange;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;

@Test//(groups = {"F1"}, alwaysRun = true)
@Listeners(TestFailureListener.class)
public class G5Dev0000CampaignMediaMatchInExchange {	  	  
	  @BeforeClass
	  public void start() throws InterruptedException{
			 Top.StartBroswer();
			 Top.Login(Lib.MB,"Welkom01@1");				
	  }
	  @Test
	  public void UseTheNewampaignFindMediaPercentage() throws InterruptedException {	
          Exchange.GotoBuyerEchangePage();
          Exchange.SetTileView();
          Exchange.SelectCampaign(Lib.CampaignADV);

          Exchange.FindAMediaWithPercentage(Lib.BuyNow, "100%");
          Exchange.FindAMediaWithPercentage(Lib.BuyNow2, "45%");
	  }
	  @AfterClass
		public void stop() throws InterruptedException {
		    Top.Logout();
			Top.CloseBrowser();
		} 		 
}
