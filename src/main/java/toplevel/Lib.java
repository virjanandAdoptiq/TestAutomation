package toplevel;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.IOException;
//import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.io.FileReader;
import java.util.*;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Lib {
	public static String credential =  ReadFromFile(D.credentialsFile, "username");
//	public static String credential =  D.credentials;
	//all the login names
	public static String ADV = credential+"ADV";
	public static String ADV2 = credential+"ADV2";
	public static String MB = credential+"MB";
	public static String UG = credential+"UG";
	public static String UG2 = credential+"UG2";
	public static String Res = credential+"Res";
	public static String Res2 = credential+"Res2";
	//campaign
	public static String CampaignADV = credential+"ADV-Campaign";
	public static String CampaignADV2 = credential+"ADV2-Campaign";
	//all the media names
	public static String BuyNow = credential+"BuyNow";
	public static String BuyNow2 = credential+"BuyNow2";
    //publication dates
	public static String Today = dateFormat(addHoursToToday(0),"dd-MM-yyyy");
	public static String lmDay1 = dateFormat(addDayToToday(2),"dd-MM-yyyy");
	public static String lmDay2 = dateFormat(addDayToToday(3),"dd-MM-yyyy");
	public static String bidDay1 = dateFormat(addDayToToday(4),"dd-MM-yyyy");
	public static String bidDay2 = dateFormat(addDayToToday(5),"dd-MM-yyyy");
	public static String buyDay1 = dateFormat(addDayToToday(6),"dd-MM-yyyy");
//	public static String weekDay = dateFormat(addHoursToToday(176),"dd-MM-yyyy");
	public static String weekDay = dateFormat(addDayToToday(7),"dd-MM-yyyy");
	public static String buyDay3 = dateFormat(addDayToToday(8),"dd-MM-yyyy");

	
	public static String ReadFromFile(String file, String name) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				String[] s = line.split("[=]+");
				if ((s != null) && (s.length == 2)) {
					if (s[0].trim().compareToIgnoreCase(name) == 0) {
						return s[1].trim();
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			CloseFile(reader);
		}

		return null;
	}
	public static String[] ReadLineFromFileToArray(String file, int length) {
		String ret[] = new String[length];
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
	
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				ret[i] = line;
				i++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			CloseFile(reader);
		}

		return ret;
	}
	public static Map<String, String> GetMapFromFile(String file) {

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));

			if (reader != null) {
				Map<String, String> newMap = new HashMap<String, String>();

				String line = null;
				while ((line = reader.readLine()) != null) {
					line = line.trim();

					String[] s = line.split("[=]+");

					if ((s != null) && (s.length == 2)) {
						String left = s[0].trim();
						String right = s[1].trim();

						if ((left.length() > 0) && (right.length() > 0)) {
							newMap.put(left, right);
						}
					}
				}
				
				return newMap;
			}
		}

		catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			CloseFile(reader);
		}

		return null;
	}
	private static void CloseFile(BufferedReader reader) {
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static Date addDayToToday(int d) {
		Calendar c = Calendar.getInstance(); 
		Date dt = new Date();
		c.setTime(dt); 
		c.add(Calendar.DATE, d);
		dt = c.getTime();
		return dt;
	}
	public static Date addHoursToToday(int h) {
		Calendar c = Calendar.getInstance(); 
		Date dt = new Date();
		c.setTime(dt);
		c.add(Calendar.HOUR_OF_DAY, h);
		dt = c.getTime();  
		
		return dt;
	}
	public static Date getTuesday(Date dt) {
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		switch (weekday) {
		   case 1:  c.add(Calendar.DATE,2);
		            break;
		   case 2:  c.add(Calendar.DATE,1);
           			break;
		   case 3:  //c.add(Calendar.DATE,7);
           			break;
		   case 4:  c.add(Calendar.DATE,6);
           			break;
		   case 5:  c.add(Calendar.DATE,5);
           			break;
		   case 6:  c.add(Calendar.DATE,4);
           			break;
		   case 7:  c.add(Calendar.DATE,3);
           			break;
		}
		dt = c.getTime();
		c.setTime(dt);
		return dt; //sdf.format(dt);
	}
	public static Date notTuseday(Date lastDate){
		Calendar c = Calendar.getInstance(); 
		Date dt = new Date();
		c.setTime(lastDate);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		if(weekday == 3) {
			c.add(Calendar.DATE, -1);
			dt = c.getTime();
			return dt;
		}else{
			return lastDate;
		}
		
	}
	public static String dateFormat(Date dt, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);
	}

	public static String[][] GetTableContent(By tableID, int rows, int cols) throws InterruptedException{		
		String[][] orders = new String[rows][cols];
		FindElement(tableID);
		WebElement table = D.driver.findElement(tableID);
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int i = 0;
		for (WebElement row : allRows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
           int j = 0; 
		   for (WebElement cell : cells) {
			  orders[i][j] = cell.getText();
		      j = j + 1;
		      if(j > cols - 1) break;
		   }
		   i = i + 1;
		   if(i > rows - 1) {
			   break;
		   }
		}
/*
 public static String[][] GetTableContent(String tableID, int rows, int cols) throws InterruptedException{		
		String[][] orders = new String[rows][cols];
		FindElement(By.cssSelector(tableID));
		WebElement table = D.driver.findElement(By.cssSelector(tableID));
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		int i = 0;
		for (WebElement row : allRows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
           int j = 0; 
		   for (WebElement cell : cells) {
			//   WebElement we = cell.findElement(By.tagName("div"));
			  orders[i][j] = cell.getText();
		      j = j + 1;
		      if(j > cols - 1) break;
		   }
		   i = i + 1;
		   if(i > rows - 1) {
			   break;
		   }
		}
 */		

		return orders;
		}

	public static String[][] SortOrders(String[][] orders){
		java.util.Arrays.sort(orders, new java.util.Comparator<String[]>() {
			  public int compare(String[] a, String[] b) {
			    return (a[0].compareTo(b[0]) < 0 ? -1 : (a[0].compareTo(b[0]) == 0 ? 0 : 1));
			  }
			});
		return orders;
	}
	
	public static void Highlight(WebElement element){
//		JavascriptExecutor js = (JavascriptExecutor) D.driver;
//	    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
//	        "color: blue; border: 2px solid blue;");
		
	}
	
	public static void FindElement(By element) throws InterruptedException {
		   D.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		   Thread.sleep(D.waitTime);
		   Highlight(D.driver.findElement(element));
		   Thread.sleep(D.waitTime);
	}
//
	public static void CloseDialogBox() throws InterruptedException{
	    	D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
			Thread.sleep(D.waitTime);
	    	if(D.driver.findElements(By.xpath(D.$OK_Button)).isEmpty() == false){
	    		Thread.sleep(D.waitTime);
				D.driver.findElement(By.xpath(D.$OK_Button)).click();   
				Thread.sleep(D.waitTime);
	    	}
		}
	    
	public static void InputData(String value, By field) throws InterruptedException{
	    	FindElement(field);
	    	D.driver.findElement(field).click();
	    	Thread.sleep(D.waitTime / 2); 
	    	D.driver.findElement(field).clear();
	    	Thread.sleep(D.waitTime / 2); 
			D.driver.findElement(field).sendKeys(value);
			Thread.sleep(D.waitTime);  
	    }
	public static void ClickButton(By name) throws InterruptedException{			
	 		FindElement(name);
	 		List<WebElement> elements = D.driver.findElements(name);
	 		for(WebElement ele: elements){
	 			if(ele.isDisplayed()){
	 				ele.click();
	 				D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
	 				Thread.sleep(D.waitTime);
	 				break;
	 			}
	 		}
	 	}
	public static void ClickAll(By name) throws InterruptedException{			
 		FindElement(name);
 		List<WebElement> elements = D.driver.findElements(name);
 		for(WebElement ele: elements){
 			if(ele.isDisplayed()){
 				ele.click();
 				D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
 				Thread.sleep(D.waitTime);
 			}
 		}
 	}
	public static void SendSpecialKey(Keys name) throws InterruptedException{
	    	Thread.sleep(D.waitTime);
	    	Actions builder = new Actions(D.driver);
	    	builder.sendKeys(name).perform();
	    	Thread.sleep(D.waitTime);
	    	D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
	}
	public static void DoubleClicky(By name) throws InterruptedException{
	    	FindElement(name);
	    	Thread.sleep(D.waitTime);
	    	Actions builder = new Actions(D.driver);
	    	builder.moveToElement(D.driver.findElement(name)).doubleClick().build().perform();
	    	Thread.sleep(3 * D.waitTime);
	    	D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
	}
	public static void SelectMenuLink(By menu, By link) throws InterruptedException{
	    	FindElement(menu);
	    	Thread.sleep(D.waitTime);
			D.driver.findElement(menu).click();
			Thread.sleep(D.waitTime);
			D.wait.until(ExpectedConditions.visibilityOfElementLocated(link));
//			if(D.driver.findElements( link ).size() == 0){
//				D.driver.findElement(menu).click();
//				Thread.sleep(D.waitTime);
//			}
			if(D.driver.findElements( link ).size() != 0){
				 D.wait.until(ExpectedConditions.visibilityOfElementLocated(link));
				 Actions act = new Actions(D.driver);
				 WebElement theItem = D.driver.findElement(link);
				 act.moveToElement(theItem).click().perform();
				 Thread.sleep(D.waitTime);    	     
			     D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
			Thread.sleep(D.waitTime);
			} else {
				Assert.fail();
			}
	    }
	public static void SelectDropdownItem(By dlist, String item) throws InterruptedException{	
			FindElement(dlist);
			Thread.sleep(D.waitTime * 2);
			WebElement mySelectElm = D.driver.findElement(dlist); 
			Select mySelect= new Select(mySelectElm);
			mySelect.selectByVisibleText(item);
			D.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(D.$ad_progress)));
			Thread.sleep(D.waitTime);
	}   
	public static void ClickContextSensitiveItem(By context, By item) throws InterruptedException{	
			FindElement(context);
			D.driver.findElement(context).findElement(item).click();
			Thread.sleep(D.waitTime);
	}		
	public static boolean isBox(String name){
			String path = D.$infoErroBox + name + "']";
			try {
				D.driver.findElement(By.xpath(path));
				return true;
			} catch (Exception e) {
			   return false;
			}
	}	
	public static void ClickAway() throws InterruptedException{
			D.driver.findElement(By.xpath(D.$PoweredByMendix)).click();
			Thread.sleep(D.waitTime);
	}
	public static String[] getMailsFromInbox(int maxLine){	
			    String[] actualMails = new String[maxLine];
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
		            
		            if(numberOfEmails < maxLine){
			            store.close();
		            	Thread.sleep(D.waitTime * 10);
		            	session = Session.getInstance(props, null);
				        store = session.getStore();
		            	store.connect("imap.gmail.com", D.EMAIL, D.EmailPass);
			            inbox = store.getFolder("INBOX");
			            inbox.open(Folder.READ_ONLY);
			            numberOfEmails = inbox.getMessageCount();
			            System.out.println(numberOfEmails);
		            }
		            
		            if(numberOfEmails < maxLine){
			            store.close();
		            	Thread.sleep(D.waitTime * 10);
		            	session = Session.getInstance(props, null);
				        store = session.getStore();
		            	store.connect("imap.gmail.com", D.EMAIL, D.EmailPass);
			            inbox = store.getFolder("INBOX");
			            inbox.open(Folder.READ_ONLY);
			            numberOfEmails = inbox.getMessageCount();
			            System.out.println(numberOfEmails);
		            }
		            
		            if(numberOfEmails < maxLine){
		            	actualMails[0] = "NumberOfEmailIsSmaller";
		            	return actualMails;
		            }
		            if(numberOfEmails > maxLine){
		            	actualMails[0] = "NumberOfEmailIsBigger";
		            	return actualMails;
		            }
		            
		            if(numberOfEmails > 0){
		            Message[] emails = inbox.getMessages();
		            for(int i = 0; i < numberOfEmails; i++){
		            	Address[] ad = emails[i].getFrom();
		         //   	if(ad[0].toString().equalsIgnoreCase("system@adoptiq.com")){
		            	if(ad[0].toString().equalsIgnoreCase("systemmail@adoptiq.com")){
		            		StringBuilder sb = new StringBuilder(); 
		            		sb.append(emails[i].getSubject().replaceAll("TEST: .*]", "TEST:").replaceAll("TEST: .* <>", "TEST: <>").replaceAll(credential, "XXXXXX").replaceAll("[a-z]* \\d{2} [a-z]* \\d{4}.*", "Edition:"));        		           				            		
		            		//get content
		                	Multipart mp = (Multipart) emails[i].getContent();
		                	BodyPart bp = mp.getBodyPart(0);
		                	Multipart mp2 = (Multipart) bp.getContent();               	
		                	               	
		                	for (int j = 0; j < mp2.getCount(); j++) {
		                        BodyPart bodyPart = mp2.getBodyPart(j);
		                  //      sb.append(bodyPart.getContent().toString().replaceAll("<[^>]*>", "").replaceAll("\\s+", "").replaceAll("_*", "").replaceAll("&nbsp;", "").replaceAll(credential, "XXXXXX").replaceAll("\\d{2}-\\d{2}-\\d{4}", "dd-mm-yyyy").replaceAll("[a-z]*\\d{2}[a-z]*\\d{4}", "editie").replaceAll("Nr:\\d+", "Nr:NNNN").replaceAll("OptieID:[0-9]*","OptieID:XXXX").replaceAll("bodnummer:[0-9]*", "bodnummer:XXXX").replaceAll("Bodnummer:[0-9]*", "Bodnummer:XXXX").replaceAll("ordernummer:[0-9]*", "ordernummer:XXXX").replaceAll("OnderhandelingID:[0-9]*", "OnderhandelingID:XXXX").replaceAll("PrivatedealID:[0-9]*", "PrivatedealID:XXXX"));          
		                        sb.append(bodyPart.getContent().toString().replaceAll("<[^>]*>", "").replaceAll("\\s+", "").replaceAll("_*", "").replaceAll("&nbsp;", "").replaceAll(credential, "XXXXXX").replaceAll("\\d{2}-\\d{2}-\\d{4}", "dd-mm-yyyy").replaceAll("[a-z]*\\d{2}[a-z]*\\d{4}", "editie").replaceAll("Nr:\\d+", "Nr:NNNN").replaceAll("ID:[0-9]*","ID:XXXX").replaceAll("bodnummer:[0-9]*", "bodnummer:XXXX").replaceAll("Bodnummer:[0-9]*", "Bodnummer:XXXX").replaceAll("ordernummer:[0-9]*", "ordernummer:XXXX"));          			                	
		                	}
		                	actualMails[i] = sb.toString();	  	              
		                	}       
		                  }
		              }                  
		 
		            } catch (Exception mex) {
		                  mex.printStackTrace();   
		            }
		        return actualMails;
		  }

		public static String checkEmails(String fileName, int numberOfMails) throws InterruptedException{
			String ret = "emailCorrect";  
			String[] expectedMails = new String[numberOfMails];
			  String file = "./src/test/resources/mails/" + fileName + ".txt";
			  String[] actualMails = getMailsFromInbox(numberOfMails);
			if(actualMails[0].equalsIgnoreCase("NumberOfEmailIsSmaller") || actualMails[0].equalsIgnoreCase("NumberOfEmailIsBigger")) {
				return actualMails[0];
			}
			  String result[] = new String[numberOfMails];
	          expectedMails = ReadLineFromFileToArray(file,numberOfMails);
			  for(int i =0; i <numberOfMails; i++){
				  result[i] = "0";
				  for(int j = 0; j <numberOfMails; j++){
					  if(expectedMails[i].equals(actualMails[j])){
						  actualMails[j] = null;
						  result[i] = "1";
						  break;					  
					  }
				  }
				  if(result[i].equals("0")){
					 ret = "email X";
					 result[i] = expectedMails[i];
					 System.out.println(result[i]);
				  }
			  }
			  
			  return ret;
		  } 
		
		public static void deleteAllMailsFromInbox(){	
	        Properties props = new Properties();
	        props.setProperty("mail.store.protocol", "imaps");
	        try {
	            Session session = Session.getInstance(props, null);
	            Store store = session.getStore();
	            store.connect("imap.gmail.com", D.EMAIL, D.EmailPass);
	            Folder inbox = store.getFolder("INBOX");
	            inbox.open(Folder.READ_WRITE);
	            int numberOfEmails = inbox.getMessageCount();
	            
	            if(numberOfEmails > 0){
	            Message[] emails = inbox.getMessages();
	            for(int i = 0; i < numberOfEmails; i++){
	            	emails[i].setFlag(Flags.Flag.DELETED, true);
	              	}
	            inbox.close(true);
	            }
	            } catch (Exception mex) {
	                  mex.printStackTrace();   
	            }
	       
	  }
		public static String getPasswordResetEmailHttp() throws InterruptedException{
			String[] actualMails = getMailsFromInbox(1);
			if(actualMails[0].equalsIgnoreCase("NumberOfEmailIsSmaller") || actualMails[0].equalsIgnoreCase("NumberOfEmailIsBigger")) {
			     return actualMails[0];
			}
			System.out.println(actualMails[0].toString());
			int httpStarts = actualMails[0].indexOf("http");
			int httpEnds = actualMails[0].indexOf("Ifyoudidnotreques");
			return actualMails[0].substring(httpStarts, httpEnds);
//			return actualMails[0].toString().startsWith("DEVTEST: Please confirm your password reset");
 
	 } 
		    
		
		public static String getFromClipboard() throws UnsupportedFlavorException, IOException{
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			return(String) clipboard.getData(DataFlavor.stringFlavor);
		}
		
		public static void maximizeScreen(WebDriver driver) {
			    java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			    Point position = new Point(0, 0);
			    driver.manage().window().setPosition(position);
			    Dimension maximizedScreenSize =
			        new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
			    driver.manage().window().setSize(maximizedScreenSize);
			  }
	
//		public static void sortColumn(By columnName,By aORd) throws InterruptedException{			
//		 		FindElement(columnName);
//		 		while (D.driver.findElements(aORd).size() == 0) {
//		 			Lib.ClickButton(columnName);
//		 		}
//		 	}
		 
}
