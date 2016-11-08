package toplevel;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import advertiser.Exchange;

public class TestFailureListener extends TestListenerAdapter {

	  private static final Logger LOG = Logger.getLogger(TestFailureListener.class.getName());

	  @Override
	  public void onTestFailure(ITestResult result) {	
		 String className = result.getMethod().getTestClass().getName().replace("testcases.", "");
	  if(D.FAILURE_INDICATION == 0){
		 File scrFile = ((TakesScreenshot)D.driver).getScreenshotAs(OutputType.FILE); 
		 try {
			FileUtils.copyFile(scrFile, new File("test-output/ScreenShots/" + className + ".jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	  }
		  
		//0 = doing nothing, 1 = logout, 2=close browser, 3=logout and close browser)
		switch(D.FAILURE_INDICATION)  {
		case 1:
			try {
			    Exchange.SelectCampaign("");
			    LOG.info(">>>>>>>>>>>>>>>Test failed. clean Campaign filter");
			} catch (Exception e){
				LOG.info(">>>>>>>>>>>>>>>Test failed. No need to clean up Campaign filter");
			}
			
			try {  
		         Top.Logout();
		         LOG.info(">>>>>>>>>>>>>>>Test failed. logout");
			} catch (Exception e){
				LOG.info(">>>>>>>>>>>>>>>Test failed. No need to logout");
			}
			break;
		case 2:
			try {
				Top.CloseBrowser();
			}catch (Exception e){
				LOG.info(">>>>>>>>>>>>>>>Test failed. No need to close browser");
			}
			break;
		case 3:
			try {
			    Exchange.SelectCampaign("");
			    LOG.info(">>>>>>>>>>>>>>>Test failed. clean Campaign filter");
			} catch (Exception e){
				LOG.info(">>>>>>>>>>>>>>>Test failed. No need to clean up Campaign filter");
			}
			
			try {  
		         Top.Logout();
		         LOG.info(">>>>>>>>>>>>>>>Test failed. logout");
			} catch (Exception e){
				LOG.info(">>>>>>>>>>>>>>>Test failed. No need to logout");
			}
			
		}
		
	  }

	}
