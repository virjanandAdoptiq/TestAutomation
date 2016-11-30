package testcases;

import org.testng.annotations.Test;
import toplevel.Lib;

@Test
public class ZZ_CompareEmailsTest {	
	 int mailsNumE = 17;
	 int mailsNumA = 20;
	 String[] expectedMails = new String[mailsNumE];
	
	 String file = "./src/test/resources/TestMail.txt";    //the stored expected emails file

	  @Test
	  public void checkEmails() throws InterruptedException{
		  String[] actualMails = Lib.getMailsFromInbox(mailsNumA);
		  String result[] = new String[mailsNumE];
          expectedMails = Lib.ReadLineFromFileToArray(file,mailsNumE);
		  for(int i =0; i <mailsNumE; i++){
			  result[i] = "0";
			  for(int j = 0; j <mailsNumA; j++){
				  if(expectedMails[i].equals(actualMails[j])){
					  actualMails[j] = null;
					  result[i] = "1";
					  break;					  
				  }
			  }
			  if(result[i].equals("0")){
				 result[i] = expectedMails[i];
				 System.out.println(result[i]);
			  }
		  }
	  } 
	
}
