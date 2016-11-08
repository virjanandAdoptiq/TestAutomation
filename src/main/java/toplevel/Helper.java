package toplevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.io.FileReader;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Helper {
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
	public static String Bid = credential+"Bid";
	public static String Bid2 = credential+"Bid2";
	public static String LM = credential+"LM";
	public static String LM2 = credential+"LM2";
    //publication dates
	public static String theTuesday = dateFormat(getTuesday(addHoursToToday(40)),"dd-MM-yyyy");
//	public static String noTuesday = dateFormat(notTuseday(addHoursToToday(200)), "dd-MM-yyyy");
	public static String noTuesday = dateFormat(notTuseday(addHoursToToday(176)), "dd-MM-yyyy");
	public static String lmDay1 = dateFormat(addHoursToToday(40),"dd-MM-yyyy");
	public static String lmDay2 = dateFormat(addHoursToToday(64),"dd-MM-yyyy");
	public static String lmNoTuesday = dateFormat(notTuseday(addHoursToToday(72)), "dd-MM-yyyy");
	public static String bidDay1 = dateFormat(addHoursToToday(104),"dd-MM-yyyy");
	public static String bidDay2 = dateFormat(addHoursToToday(128),"dd-MM-yyyy");
	public static String bidNoTuesday = dateFormat(notTuseday(addHoursToToday(120)), "dd-MM-yyyy");
	public static String buyDay1 = dateFormat(addHoursToToday(152),"dd-MM-yyyy");
	public static String buyDay2 = dateFormat(addHoursToToday(176),"dd-MM-yyyy");

	
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

	public static String[][] GetTableContent(String tableID, int rows, int cols) throws InterruptedException{		
		String[][] orders = new String[rows][cols];
		WebElement table = D.driver.findElement(By.cssSelector(tableID));
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
		JavascriptExecutor js = (JavascriptExecutor) D.driver;
	    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
	        "color: blue; border: 2px solid blue;");
		
	}
	
	public static void FindElement(By element) throws InterruptedException {
		   D.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		   Thread.sleep(D.waitTime);
		   Helper.Highlight(D.driver.findElement(element));
		   Thread.sleep(D.waitTime);
	}

}
