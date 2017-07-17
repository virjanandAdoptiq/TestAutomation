package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import advertiser.Exchange;
import advertiser.Mylots;
import advertiser.PersonalOffer;
import publisher.ExchangeP;
import publisher.Media;
import toplevel.D;
import toplevel.Lib;
import toplevel.TestFailureListener;
import toplevel.Top;


@Test//(groups = {"I1"})
@Listeners(TestFailureListener.class)
public class I1Dev0000PrivateOfferSpaceManagement1 {		  
	  @BeforeClass
			public void start() throws InterruptedException{
		        Lib.deleteAllMailsFromInbox();
				Top.StartBroswer();
			}
	  @Test(dataProvider="inputdataX")
	  public void optionMultiplePush(String seller, String buyer, String ad, String media, 
			                        String theDate,String price, String product1, String product2, boolean bundle,boolean reserve) throws InterruptedException {			  
			 
			 Top.Login(seller,"Welkom01@1");
	 		 ExchangeP.GotoInventory();
			 Exchange.SelectMedia(media);
			 Exchange.EnterFromThroughDate(theDate);		 
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 ExchangeP.SelectRowOverViewTable(product1);
			 ExchangeP.SelectRowOverViewTable(product2);
			 Lib.ClickButton(By.cssSelector(D.$p_option_push));
			 Media.PushOptionMultiple(buyer, ad, "1", ad + "1", price,bundle,reserve);
			 Top.Logout(); 
	
	  }	 
	  @DataProvider
	  public Object[][] inputdataX() {
	    return new Object[][] { 
	      {Lib.Res,Lib.MB,Lib.ADV,Lib.BuyNow,Lib.buyDay3,"200", "CD101V - Cover 2","CD101V - Cover 3", false,false},
	      {Lib.Res2,Lib.MB,Lib.ADV,Lib.BuyNow2,Lib.buyDay3,"400", "CD101V - Pagina 2","CD101V - Pagina 3", true,true},
	    };
	  }		
	  @Test(dependsOnMethods="optionMultiplePush")
	  public void ADV2CheckBundleOfferInExchange() throws InterruptedException{	
			 Top.Login(Lib.ADV2,"Welkom01@1");
			 Exchange.GotoBuyerEchangePageTileView();	 
			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Exchange.SelectMedia(Lib.BuyNow2);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.ClickAMediaTile(Lib.BuyNow2);
			 
			 SoftAssert softAssert = new SoftAssert();
			 softAssert.assertFalse(Exchange.CheckAInventoryExist(D.Pagina2FullPage));
			 softAssert.assertFalse(Exchange.CheckAInventoryExist(D.Pagina2HalfLying));
			 softAssert.assertFalse(Exchange.CheckAInventoryExist(D.Pagina2HalfStand));
			 softAssert.assertFalse(Exchange.CheckAInventoryExist(D.Pagina3FullPage));
			 softAssert.assertFalse(Exchange.CheckAInventoryExist(D.Pagina3HalfLying));
			 softAssert.assertFalse(Exchange.CheckAInventoryExist(D.Pagina3HalfStand));
			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();

	  }
	  @Test(dependsOnMethods="ADV2CheckBundleOfferInExchange",alwaysRun = true)
	  public void ADV2BuyTheInventories() throws InterruptedException{	
			 Exchange.GotoBuyerEchangePageTileView();	 

			 Exchange.EnterFromThroughDate(Lib.buyDay3);
			 Exchange.SelectMedia(Lib.BuyNow);
			 Lib.ClickButton(By.cssSelector(D.$be_execute));
			 Exchange.ClickAMediaTile(Lib.BuyNow);
			 Exchange.AddToMyLots(D.Cover2FullPage);
			 Exchange.AddToMyLots(D.Cover3FullPage);

			 Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();

			 Mylots.SelectALot(D.Cover2FullPage);
			 Mylots.SelectALot(D.Cover3FullPage);
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);

			 Top.Logout();
	  }
	  @Test(dependsOnMethods="ADV2BuyTheInventories", alwaysRun = true)
	  public void MBCheckPersonalOffers() throws InterruptedException{		  
			 Top.Login(Lib.MB,"Welkom01@1");
			 Exchange.GoToPersonalOffer();
             Exchange.SelectCampaign("");
		           
             SoftAssert softAssert = new SoftAssert();
			 softAssert.assertFalse(PersonalOffer.CheckIfOfferExist("€ 200,00"));
             
			 Exchange.SelectCampaign(Lib.CampaignADV);
			 PersonalOffer.SelectAGroupOfOffers("€ 400,00");
			 Lib.ClickButton(By.xpath(D.$be_inventory_add_all));
			 Lib.CloseDialogBox();

			 D.FAILURE_INDICATION = 0; 
			 softAssert.assertAll();
	  }
	  @Test(dependsOnMethods="MBCheckPersonalOffers")
	  public void MBBuyOffers() throws InterruptedException{		  
		     Mylots.SelectMyLotsMenuItem(D.$ItemMyLots); 
			 Mylots.SetFilterOrderNotShow();
			 Mylots.ClickApplyFilterButton();
			 Mylots.SelectAGroup("Private Deal");
			 Lib.ClickButton(By.cssSelector(D.$bm_lot_buy_icon));
			 Mylots.BuyBidOptionConfirm(D.$bm_lot_buy_confirm);        

	  }
	  @Test(dependsOnMethods="MBBuyOffers")
	  public void CheckOrderPriceIsBuyNowPrice() throws InterruptedException {	  
		    SoftAssert softAssert = new SoftAssert();		     
		    Mylots.SelectMyLotsMenuItem(D.$ItemOrderOverview);   
			String[][] orders = Lib.SortOrders(Lib.GetTableContent(By.xpath(D.$b_orderoverview_table2), 3, 12));
			 
		    int index = -1;
		     for(int i =1;i<3;i++){
		    	 if(orders[i][6].equals("Pagina 2")){
			          softAssert.assertEquals(orders[i][9].toString().trim(), "200,00");
			          index = i;
			          break;
		    	 }
		     }
		    softAssert.assertNotEquals(index, -1);
			index = -1;
			for(int i =1;i<3;i++){
			    if(orders[i][6].equals("Pagina 3")){
				      softAssert.assertEquals(orders[i][9].toString().trim(), "200,00");
				      index = i;
				      break;
			    }
			}
			softAssert.assertNotEquals(index, -1);
			D.FAILURE_INDICATION = 0; 
			softAssert.assertAll();			 
	  }	 
	  @AfterClass
	  public void stop() throws InterruptedException { 
		  Top.Logout();
		  Top.CloseBrowser();
	  } 

		 
}
