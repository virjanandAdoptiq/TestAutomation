package testcases;

import java.util.*;
import javax.mail.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.testng.annotations.Test;

import toplevel.D;
import toplevel.Lib;

public class ZZ_CaptureEmailsToFile {
	
	@Test
    public static void email() throws FileNotFoundException, UnsupportedEncodingException {
		
		PrintWriter writer = new PrintWriter("./test-output/emails/TestMail2.txt", "UTF-8");
				
		String[] mails = new String[200];
		String credential = Lib.credential;
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", D.EMAIL, D.EmailPass);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            int numberOfEmails = inbox.getMessageCount();
            System.out.println(numberOfEmails);
            
            if(numberOfEmails > 0){
            Message[] emails = inbox.getMessages();
            for(int i = 0; i < numberOfEmails; i++){
            	Address[] ad = emails[i].getFrom();
            	if(ad[0].toString().equalsIgnoreCase("system@adoptiq.com")){
            		StringBuilder sb = new StringBuilder(); 
            		sb.append(emails[i].getSubject().replaceAll("editie:.*", "editie:").replaceAll("TEST: .*]", "TEST:").replaceAll(credential, "XXXXXX").replaceAll("TEST: .* <>", "TEST: <>"));        		           		
            		//get content
                	Multipart mp = (Multipart) emails[i].getContent();
                	BodyPart bp = mp.getBodyPart(0);
                	Multipart mp2 = (Multipart) bp.getContent();               	
                	               	
                	for (int j = 0; j < mp2.getCount(); j++) {
                        BodyPart bodyPart = mp2.getBodyPart(j);
                        //<[^>]*>  html tag, \\s+ empty space and invisible character
                        sb.append(bodyPart.getContent().toString().replaceAll("<[^>]*>", "").replaceAll("\\s+", "").replaceAll("_*", "").replaceAll("&nbsp;", "").replaceAll(credential, "XXXXXX").replaceAll("\\d{2}-\\d{2}-\\d{4}", "dd-mm-yyyy").replaceAll("editie:[a-z]*\\d{2}[a-z]*\\d{4}", "editie").replaceAll("Nr:\\d+", "Nr:NNNN"));         
            	      }
                	mails[i] = sb.toString();
                	//System.out.println(mails[i]);

                	writer.println(mails[i]);
              
                	}       
                  }
            writer.close();
              }                  
 
            } catch (Exception mex) {
                  mex.printStackTrace();   
            }
	}
}	
