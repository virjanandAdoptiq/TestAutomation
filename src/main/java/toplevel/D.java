package toplevel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class D {
	//general variable
	public static String EMAIL = "autotestAdoptiq@gmail.com";
	public static String EmailPass = "$$autotestAdoptiq$$";
	public static String adminUser = "autoTestADMIN";
	public static String adminPass = "Welkom01@1";
	public static String URL = "https://adoptiq100-test.mendixcloud.com/";
	public static String browser = "firefox";
	public static String driverIsNull = "FirefoxDriver: firefox on MAC (null)";
	public static String credentialsFile = "./src/test/resources/credentials.properties";
	public static int waitTime = 600;
	public static int maxWaitTime = 60;
	public static int longWaitTime = 600;
	public static int waitTimeAfterCloseBrowser = 5000;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static WebDriverWait longWait;
	public static int FAILURE_INDICATION = 0; //0 = doing nothing, 1 = logout, 2=close browser, 3=logout and close browser
	//---product names
	public static String Cover2FullPage = "Cover 2Volledige pagina";
	public static String Cover2HalfStand = "Cover 21/2 pagina volledig staand";
	public static String Cover2HalfLying = "Cover 21/2 pagina volledig liggend";
	public static String Cover3FullPage = "Cover 3Volledige pagina";
	public static String Cover3HalfStand = "Cover 31/2 pagina volledig staand";
	public static String Cover3HalfLying = "Cover 31/2 pagina volledig liggend";
	public static String Pagina2FullPage = "Pagina 2Volledige pagina";
	public static String Pagina2HalfStand = "Pagina 21/2 pagina volledig staand";
	public static String Pagina2HalfLying = "Pagina 21/2 pagina volledig liggend";
	public static String Pagina3FullPage = "Pagina 3Volledige pagina";
	public static String Pagina3HalfStand = "Pagina 31/2 pagina volledig staand";
	public static String Pagina3HalfLying = "Pagina 31/2 pagina volledig liggend";
	public static String Pagina45FullPage = "Pagina 4-5Volledige pagina";
	public static String Pagina45HalfStand = "Pagina 4-51/2 pagina volledig staand";
	public static String Pagina45HalfLying = "Pagina 4-51/2 pagina volledig liggend";
	public static String VoorpaginaFullPage = "VoorpaginaVolledige pagina";
	public static String VoorpaginaHalfStand = "Voorpagina1/2 pagina volledig staand";
	public static String VoorpaginaHalfLying = "Voorpagina1/2 pagina volledig liggend";
	//---general
	public static String $infoErroBox = "//h4[text()='";    // + Informatie + "']"; or + Fout + "']";
    public static String $PoweredByMendix = "//div[contains(@class, 'wgt-CKEditorViewerForMendixNode')]";
	//----login page and account
    public static String $forgot_password_link = "//a[text()='Forgot password?']";
    public static String $forgot_password_email = "//html/body/div[3]/div[1]/div[2]/div/div/div/div/div/div/div[2]/div/input";
    public static String $forgot_password_send_button = ".mx-name-microflowButton";
	public static String $username = ".username";
	public static String $password = ".password";
	public static String $signin = ".btn-primary";
	public static String $signout = ".mx-name-signOutButton";
//	public static String $loginAccept = ".mx-name-actionButton1";
	public static String $loginAccept = "//img[contains(@src,'System$Completed.gif')]";
	public static String $oldPassword = ".mx-name-textBox5 input";
	public static String $newPassword = ".mx-name-textBox3 input";
	public static String $confirmNewPassword = ".mx-name-textBox1 input";
	public static String $OK_Button = "//button[text() = 'OK']";
	public static String $Doorgaan_Button = "//button[text() = 'Doorgaan']";
	public static String $changePasswordButton = ".mx-dataview-controls .mx-name-microflowButton2";
	
	public static String $AccountEmail = "//a[text()='E-mailadres']/ancestor::th/following-sibling::td/div/input";
	public static String $SaveMyAccount = "//img[contains(@src,'System$Save.png')]";
	
	//----top menu
	public static String $MenuLink = "//ul[contains(@class, 'mx-navbar-submenu')]//a[contains(text(), "; //+item + ")]"
	public static String $Menu = "//li[contains(@class, 'mx-navbar-item')]/a[contains(text(), "; //+name + ")]"
	public static String $MenuExchange = "'Exchange'";
	public static String $MenuMyLots = "'Mijn kavels'";
	public static String $MenuMedia = "'Media'";
	public static String $MenuAccount = "'Account'";
	public static String $MenuDeveloper = "'Develop'";
	public static String $MenuSystem = "'Systeem'";
    public static String $ItemExchange = "'Exchange'";
    public static String $ItemMyLots = "'Mijn kavels'";
    public static String $ItemOrderOverview = "'Orderoverzicht'";
    public static String $ItemMyAccount = "' Mijn account'";
    public static String $campaign = ".form-control";
    //MyAccount
    public static String $MyAccount_TradingCountry = "//html/body/div[1]/div/div/div/div[2]/div/div/div[1]/div/div/div/div/div[1]/table[2]/tbody/tr[5]/td[1]/div/div/select";
    public static String $MyAccount_Save = "//html/body/div[1]/div/div/div/div[2]/div/div/div[1]/div/div/div/div/div[2]/button[1]";
    //---Media filter search form
    public static String $media_filter_publisher = "//label[text()='Uitgever']/../following-sibling::td/div/input";
    public static String $media_filter_title = "//label[text()='Titel']/../following-sibling::td/div/input";
    public static String $media_filter_execute = "//div[contains(@class, 'modal-dialog')]//button[text()='Uitvoeren']";
    //---buyer exchange
    public static String $be_execute = ".Uitvoeren";
    public static String $be_restore = "//button[text()='Wissen']";
    public static String $be_switchLeftMenuIcon = "//img[contains(@src,'General$HamburgerMenuIcon.png')]";
    public static String $be_deselect_all = "//button[text()='Alles deselecteren']";
    public static String $be_mediafilter_icon = "//button[text()='Mediumfilters']";
    public static String $be_addAllSelectedToMyLots = "button.mx-name-actionButton4";
    //Exchange filters
    public static String $be_filter_phase = "//tr[2]/td/div/select"; 
    public static String $be_filter_mediatype = "//tr[3]/td/div/select"; 
    public static String $be_filter_frequency = "//tr[6]/td/div/select"; 
    public static String $be_filter_positionname = "//tr[7]/td/div/input";
    public static String $be_filter_weekday = "//tr[12]/td/div/select"; 
    public static String $be_filter_saleorg = "//tr[4]/td/div/div/div/button";
    public static String $be_filter_category = "//tr[13]/td/div/div/div/button"; 
    public static String $be_filter_pluspropercition = "//tr[14]/td/div/select"; 
    public static String $be_filter_land = "//tr[15]/td/div/div/div/button";
//  a bit complicated to make all filers share one path.    
    public static String $be_medium_format_prefix = "//label[text()='";
    public static String $be_medium_format_suffix = "']/../following-sibling::td//button";
    public static String $be_date_filter = ".mx-dateinput-input";
    public static String $be_filter_check_box = "//li//label[contains(text(),'";
    public static String $be_inventory_row_prefix = "//table[contains(@class, 'int-table-to-datagrid')]/tbody//*[normalize-space(.)= '";   
    //  [normalize-space(.)=
    public static String $be_inventory_row_suffix = "']/ancestor::table[contains(@class, 'int-table-to-datagrid')]/tbody/tr[1]";
    public static String $be_addToMyLots = "button.mx-name-actionButton3";
    public static String $be_inventory_deselect_all = ".mx-name-actionButton2";
    public static String $be_inventory_add_all = ".mx-name-actionButton4";
    public static String $be_inventory_duplication_add_all = "//button[text()='Alle geselecteerde items toevoegen']";
    public static String $be_inventory_duplication_add_one = "//html/body/div[3]/div[1]/div[2]/div/div/div[2]/button[1]";
    
    public static String $be_inventory_buynow_bidphase_price = ".mx-name-textNextBidCPM2";
    public static String $be_inventory_buynow_bidphase_cpm_price = ".mx-name-textFinalPriceCPM";
    public static String $be_inventory_buynow_price = ".mx-name-textFinalPriceString";
//    public static String $be_inventory_buynow_price = ".mx-name-textNextBidCPM2";
    public static String $be_inventory_buynow_cpm_price = ".mx-name-textFinalPriceCPM1";
    public static String $be_inventory_bid_price = ".mx-name-textNextBidCPM1";
    public static String $be_inventory_bid_cpm_price = ".mx-name-textNextBidCPM";
    //Exchange offer page
    public static String $be_offer_notification = "//img[contains(@src,'Layouts$private_offer_on.png')]";    
    public static String $be_offer_delete = ".mx-name-actionButton6";
    //----buyer My Lots
    public static String $bm_lot_row_prefix = "//li//*[contains(normalize-space(.), '";
    public static String $bm_lot_row_suffix = "')]/ancestor::table[contains(@class, 'mx-name-table44')]/tbody/tr[1]";
    public static String $bm_lot_select_check = ".mx-name-actionExpandInventory3";
    public static String $bm_lot_expand_arrow = ".mx-name-actionExpandInventory13";
    public static String $bm_lot_expand_arrow_red = ".mx-name-actionExpandInventory5";
    public static String $bm_lot_select_publisher = ".mx-name-referenceSelector2 select";
    public static String $bm_lot_select_PPP = ".mx-name-referenceSelector4 select";
    public static String $bm_lot_PPP_Nr = "//table[contains(@class,'mx-name-table3')]/tbody/tr[2]//input";
    public static String $bm_lot_buyNowPrice = ".mx-name-textBuyNowPriceNett5";
    public static String $bm_lot_bidPrice = ".mx-name-textNextBidPriceNett5";
    public static String $bm_lot_info_icon = ".mx-name-actionButton84";
    public static String $bm_lot_lot_select_box = ".mx-name-actionExpandInventory12";
    public static String $bm_lot_delete_icon = ".mx-name-actionButton19";
    public static String $bm_lot_buy_icon = ".mx-name-actionButton22";
    public static String $bm_lot_negotiation_icon = ".mx-name-actionButton71";
    public static String $bm_lot_option_icon = ".mx-name-actionButton20";
    public static String $bm_lot_bid_icon = ".mx-name-actionButton21";
    public static String $bm_lot_bulkDealID_icon = ".mx-name-actionButton34";
    public static String $bm_lot_materialSize_icon = ".mx-name-actionButton4";
    public static String $bm_lot_apply_filter_button = ".mx-name-actionButton32";
    public static String $bm_lot_autobid_button = ".mx-name-actCampaignEdit1";
    public static String $bm_lot_underbid_input = "//table[contains(@class,'mx-name-table4')]/tbody/tr[1]//input";
    public static String $bm_lot_underbid_change_button = ".mx-name-actCampaignEdit3";
    public static String $bm_lot_underbid_change_input = "//body/div[3]/div[1]/div[2]/div/div/div/div/div/div[1]/div/div/input";
    public static String $bm_lot_dealid_input = "//table[contains(@class,'mx-name-table3')]/tbody/tr[1]//input";

    //Enter Bulk DealID
    public static String $bm_bulkDealIDForm_DealID = "//tbody/tr[3]/td[1]/div/input";
    public static String $bm_bulkDealIDForm_Save = "//tbody/tr[7]/td/button";
    //
    public static String $bm_lot_autobid_prices_prefix = ".//div[3]/div/table[2]/tbody/tr["; 
    public static String $bm_lot_autobid_prices_sufix = "]/td/div";
    public static String $bm_lot_autobid_select_button = "//img[contains(@src,'System$Select.png')]";
    //lot select campaign
    public static String $bm_lot_select_campaign_button = ".mx-name-actCampaignEdit";
    //lot negotiation
    public static String $bm_lot_negotiation_proposed_price = "//html/body/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[5]/td/table/tbody/tr[1]/td[1]/div/input";
    public static String $bm_lot_negotiation_Expiration_date = "//html/body/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[5]/td/table/tbody/tr[2]/td[1]/div/div/div/input";
    public static String $bm_lot_negotiation_Bundled = "//html/body/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[5]/td/table/tbody/tr[3]/td[1]/div/div/input";
    public static String $bm_lot_negotiation_Negotiation_Button = "//html/body/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[6]/td/button";
    public static String $bm_lot_negotiation_SaveDraft = "//html/body/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[7]/td/button";
  
    //Lot conflicting poposup
    public static String $bm_lot_conflicting_popup_close_button = "//html/body/div[3]/div[1]/div[1]/button";
    
    //Lot filters
    public static String $bm_lots_order_filter_off = "//img[contains(@src,'Exchange$tick_order_off.png')]";
    public static String $bm_lots_order_filter_on = "//img[contains(@src,'Exchange$tick_order_on.png')]";
    public static String $bm_lots_bid_filter_off = "//img[contains(@src,'Exchange$tick_biedingen_off.png')]";
    public static String $bm_lots_bid_filter_on = "//img[contains(@src,'Exchange$tick_biedingen_on.png')]";

    
    //lot status: should be used with $bm_lot_row_prefix
    public static String $bm_lot_status_option_requested = "//img[contains(@src,'Exchange$status_optie_aagevr.png')]";
    public static String $bm_lot_status_option = "//img[contains(@src,'Exchange$status_optie.png')]";
    public static String $bm_lot_status_option_cancelled = "//img[contains(@src,'Exchange$Exchange_status_optiecancel.png')]";
    public static String $bm_lot_status_saved = "//img[contains(@src,'Exchange$status_bewaard.png')]";
    public static String $bm_lot_status_bid = "//img[contains(@src,'Exchange$status_biedingen.png')]";
    public static String $bm_lot_status_underbid = "//img[contains(@src,'Exchange$status_onderbod.png')]";
    public static String $bm_lot_status_outbid = "//img[contains(@src,'Exchange$status_overboden.png')]";
    public static String $bm_lot_status_conflicting = "//img[contains(@src,'System$Error.gif')]";
    public static String $bm_lot_status_negotiateSubmitted = "//img[contains(@src,'Exchange$Exchange_status_onderhandel.jpg')]";
    //--- Buy,Option, bid lot form
    public static String $bm_lot_exceed_price_confirm = ".modal-dialog .btn-primary";
    public static String $bm_lot_buy_confirm = ".modal-dialog .btn-primary";
    public static String $bm_lot_option_confirm = ".modal-dialog .btn-warning";
    public static String $bm_lot_bid_confirm = ".modal-dialog .btn-success";
    //----buyer My Lots, lot info page
    public static String $bm_lot_info_rateCardPrice = ".mx-name-textBox27>label";
    public static String $bm_lot_info_buyNowPrice = ".mx-name-textBox23>label";
    public static String $bm_lot_info_bidPrice = ".mx-name-textBox14>label";
    public static String $bm_lot_info_lastMinutePrice = ".mx-name-textBox15>label";
    public static String $bm_lot_info_crossPrice = ".mx-name-textBox1>label";
    public static String $bm_lot_info_contractDeductionPrice = ".mx-name-textBox3.int-field-noborder>label";
    public static String $bm_lot_info_volumeDeductionPrice = ".mx-name-textBox4.int-field-noborder>label";
    public static String $bm_lot_info_surchargePrice = ".mx-name-textBox22>label";
    public static String $bm_lot_info_finalPrice = ".mx-name-textBox5.int-field-noborder>label";
    public static String $bm_lot_info_contractPercentage = ".mx-name-textBox20>label";
    public static String $bm_lot_info_volumePercentage = ".mx-name-textBox21>label";
    public static String $bm_lot_info_closeButton = ".mx-name-cancelButton1";
    //  material size info
    public static String $bm_lot_materialSize_hsize_info = "//html/body/div[3]/div[1]/div[2]/div/div/div/div/div/div/table/tbody/tr[2]/td/div/div[3]/div/div/div/div/div/table/tbody/tr[1]/td[10]/div/label";
    public static String $bm_lot_materialSize_vsize_info = "//html/body/div[3]/div[1]/div[2]/div/div/div/div/div/div/table/tbody/tr[2]/td/div/div[3]/div/div/div/div/div/table/tbody/tr[1]/td[12]/div/label";
    public static String $bm_lot_materialSize_info_close_button = "//html/body/div[3]/div[1]/div[1]/button";
    //Warning: out of budget 
    public static String $bm_out_of_budget_OK = "//html/body/div[3]/div[1]/div[2]/div/div/div/table/tbody/tr[6]/td/button";
    //Buy OrderOverview
    public static String $b_orderoverview_table = ".mx-datagrid-body-table"; 
    //Public offer form
    public static String $b_offer_inventory_path = "//div[contains(@class, 'mx-dataview-content')]/table/tbody//*[normalize-space(.)= '";
    public static String $b_offer_add_goto_MyLots = "//button[text()='Toevoegen aan Mijn kavels']";
    public static String $b_offer_select_checkbox = ".mx-name-actionExpandInventory7";
  
    //Publisher exchange
    public static String $p_exchange_left_menu = "//li[contains(@class,'mx-navigationlist')]/label[text() = '";
    public static String $p_exchange_left_orderOverview = "Orderoverzicht";
    public static String $p_exchange_left_optionOverview = "Optie overzicht";
    public static String $p_orderoverview_table = ".mx-datagrid-body-table"; 
    public static String $p_option_approve = ".mx-name-actionButton1";
    public static String $p_option_reject = ".mx-name-actionButton2";
    public static String $p_contents_row = "//div[contains(@class, 'mx-datagrid-data-wrapper') and text()='"; //text']";
    public static String $p_modal_dialog = ".modal-dialog";
    public static String $p_approve_option_deadline_days = "//div/input[contains(@class,'form-control')]";
    public static String $p_approve_option_save = "//img[contains(@src,'https://adoptiq100-test.mendixcloud.com/img/System$Save.png')]";
    public static String $p_reject_option_save = "//button[contains(text(),'Doorgaan')]";
    public static String $p_orderoverview_privateSeat_tab = ".mx-name-tabPage8";
    public static String $p_orderoverview_pluspropersition_tab = ".mx-name-tabPage7";
    public static String $p_private_seat_row_dispaly_button = ".mx-name-microflowButton7";
    public static String $p_plus_propersition_row_dispaly_button = ".mx-name-microflowButton10";
      //Private Seat
    public static String $p_private_seat_publisher_orderID = "//tbody/tr[2]/td[2]/div/input";
    public static String $p_private_seat_discount_percentage = "//tbody/tr[2]/td[1]/div/input";
    public static String $p_private_seat_save_button = ".mx-name-microflowButton1";
    public static String $p_private_seat_PPP_Price = "//tbody/tr[1]/td[2]/div/input";
    //Publisher Media
    public static String $p_option_push_dialog = ".modal-content";
    public static String $p_option_push = ".mx-name-actionButton9"; //text']"
    public static String $p_option_details_deadLine_days = "//table/tbody/tr[4]/td/div/input";
    public static String $p_option_details_mb_button = "//html/body/div[3]/div[1]/div[2]/div/div/div[1]/table/tbody/tr[5]/td/div/button";
    public static String $p_option_details_org_search = "//html/body/div[5]/div[1]/div[2]/div/div/div/div/div/div/div/div/div[2]/div[2]/button[1]";
    public static String $p_option_details_org_input = "//html/body/div[5]/div[1]/div[2]/div/div/div/div/div/div/div/div/div[1]/div[2]/div[1]/div[2]/input";
    public static String $p_option_details_org_search_execute = "//html/body/div[5]/div[1]/div[2]/div/div/div/div/div/div/div/div/div[1]/div[1]/button[1]";
    public static String $p_option_details_org_choose_button = "//html/body/div[5]/div[1]/div[2]/div/div/div/div/div/div/div/div/div[2]/div[2]/button[2]";
    public static String $p_option_details_org_ad_select = "//html/body/div[5]/div[1]/div[2]/div/div/div/div/div/div/div/div/div[3]/div/table[2]/tbody//div[text()='";
    public static String $p_option_details_ad = "//table/tbody/tr[6]/td/div/div/select";
    public static String $p_option_details_dealid = "//body/div[3]/div[1]/div[2]/div/div/div[1]/table/tbody/tr[7]/td/div/input";
    public static String $p_option_details_price = "//body/div[3]/div[1]/div[2]/div/div/div[1]/table/tbody/tr[6]/td/div/input";
//    public static String $p_option_details_price = "//table/tbody/tr[6]/td/div/input";
//    public static String $p_option_details_dealid = "//label[text()='Deal ID')]/../following-sibling::td/div/input";
//    public static String $p_option_details_price = "//label[text()='Dealprijs')]/../following-sibling::td/div/input";
    public static String $p_overview_underbid = "//a[text()='Onderbiedingen']";
    public static String $p_underbid_accept = "//button[text()='Accepteer bod']";
    public static String $p_underbid_reject = "//button[text()='Wijs bod af']";
    //public offer
    public static String $p_offer_make_button = ".mx-name-actionButton10";
    public static String $p_offer_startDate = "//html/body/div[3]/div[1]/div[2]/div/div/div[1]/table/tbody/tr[4]/td/div/div/div/input";
    public static String $p_offer_endDate = "//html/body/div[3]/div[1]/div[2]/div/div/div[1]/table/tbody/tr[5]/td/div/div/div/input";
    public static String $p_offer_save = "//html/body/div[3]/div[1]/div[2]/div/div/div[2]/button[1]";
    //exclusive deeplink
    public static String $p_inventory_aanbiedingen = "//html/body/div[1]/div/div/div/div[2]/div/div/div[1]/div/div/table/tbody/tr/td/ul/li[10]/span";;
    public static String $p_inventory_aanbiedingen_new_button = "//button[text()='Nieuw']";
    public static String $p_aanbiedingenDetails_exclusive_checkbox = "//html/body/div[3]/div[1]/div[2]/div/div/div[1]/table/tbody/tr[3]/td/div/input";
    public static String $p_aanbiedingenDetails_valid_from ="//html/body/div[3]/div[1]/div[2]/div/div/div[1]/table/tbody/tr[5]/td/div/div/div/input";
    public static String $p_aanbiedingenDetails_valid_to ="//html/body/div[3]/div[1]/div[2]/div/div/div[1]/table/tbody/tr[6]/td/div/div/div/input";
    public static String $p_aanbiedingenDetails_Toevoegen ="html/body/div[3]/div[1]/div[2]/div/div/div[1]/table/tbody/tr[11]/td/div/div/div[2]/div[2]/button[1]";
    public static String $p_aanbiedingenDetails_media_select = "//html/body/div[4]/div[1]/div[2]/div/div/div[3]/div/table[2]/tbody/tr[1]/td[2]/div";
    public static String $p_aanbiedingenDetails_media_select_kies = "//html/body/div[4]/div[1]/div[2]/div/div/div[2]/div[2]/button[2]";
    public static String $p_aanbiedingenDetails_save = "//html/body/div[3]/div[1]/div[2]/div/div/div[2]/button[1]";
    public static String $p_aanbiedingenOverview_row = "//div[text()='";  //Media']"; Inventory"];
    public static String $p_aanbiedingenOverview_CopyURL = "//button[text()='Kopieer URL']";
    public static String $Deeplink_login_user = "//html/body/div[2]/form/div[3]/input";
    public static String $Deeplink_login_password = "//html/body/div[2]/form/div[4]/input";
    public static String $Deeplink_login_signIn = "//html/body/div[2]/form/input";
    //----admin page
    public static String $ad_unit_tests = "//label[contains(text(),'Unit tests')]";
    public static String $ad_unit_tests_menu = "//div[@class='mx-name-textBox1']/label[contains(text(), '"; // + menu name + ')]
    public static String $ad_unit_enter_name = ".mx-name-microflowButton5";
    public static String $ad_unit_input_name = "//tbody/tr[1]/td/div/input";
    public static String $ad_schematischeTaken = "//label[contains(text(),'Schematische taken')]";
    public static String $ad_schematischeTaken_dailyreportAD = "//button[text()='SE_Order_SendOverviewOrdersToMA_Adv']";
    public static String $ad_schematischeTaken_dailyreportPU = "//button[text()='SE_Order_SendOverviewToPU']";

    //   public static String $ad_unit_input_name = ".mx-name-textBox3";
    public static String $ad_unit_input_email = "//tbody/tr[2]/td/div/input";
    public static String $ad_unit_input_save = ".mx-dataview-controls button";
    public static String $ad_unit_run = ".mx-name-microflowButton1";
    public static String $ad_progress = ".mx-progress-message";
    //GUI check
    public static String $gui_pagetitle_prefix = "//*[contains(@class,'mx-name-title') and text()='"; // + title + ']
    public static String $gui_adoptiqlogo = "//img[contains(@src,'adoptiq100-test.mendixcloud.com/file')]";   //Adoptiq logo
    public static String $gui_exchangelogo = "//img[contains(@src,'Layouts$icon_veiling.png')]";
    public static String $gui_offerlogo = "//img[contains(@src,'Layouts$icon_tariefkaarten.png')]";
    public static String $gui_availabletitleslogo = "//img[contains(@src,'Layouts$icon_media_zoeken.png')]";
    public static String $gui_publisherlogo = "//img[contains(@src,'Layouts$icon_uitgevers.png')]";
    public static String $gui_mylotslogo = "//img[contains(@src,'Layouts$icon_winkelmand.png')]";
    public static String $gui_bosummarylogo = "//img[contains(@src,'Layouts$icon_publicatieschema.png')]";
    public static String $gui_ordersummarylogo = "//img[contains(@src,'Layouts$icon_orderoverzicht.png')]";
    public static String $gui_campaignlogo = "//img[contains(@src,'Layouts$icon_publicatieplannen.png')]";
    public static String $gui_campaigndashlogo = "//img[contains(@src,'Layouts$icon_uitgever_dashboard.png')]";
    public static String $gui_advertiserlogo = "//img[contains(@src,'Layouts$icon_adverteerders.png')]";
}

