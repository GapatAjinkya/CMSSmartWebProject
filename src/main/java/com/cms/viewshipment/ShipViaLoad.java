package com.cms.viewshipment;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;
import com.cms.Base.BaseClass;

public class ShipViaLoad extends BaseClass {

	public ShipViaLoad(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	Logger logger = LogManager.getLogger("ShipViaLoad");
	@FindBy(id = "menu_item_2")
	private WebElement Transaction;

	@FindBy(id = "menu_item_23")
	private WebElement ViewShipment;

	@FindBy(xpath = "//button[@id='btnShipViaLoad']")
	private WebElement ShipViaLoad; // btnShipViaLoad

	@FindBy(id = "ddlUserGroup")
	private WebElement UserGroup;

	@FindBy(xpath = "//button[@onclick='SelectGroupOkClick()']")
	private WebElement SelectGroupOkClick;

	@FindBy(id = "cmbShipVia")
	private WebElement cmbShipViaid; // to select Shipvia -----------------------
	// to select Shipvia -----------------------

	@FindBy(xpath = "//option[text()='Proship1 -- ProshipUPS1']")
	private WebElement Proship1; // To select Carriers

	@FindBy(xpath = "//option[@value='35']")
	private WebElement Sites; // Sites

	@FindBy(id = "btnLoadAccount")
	private WebElement CarriersLoadAccount;

	@FindBy(xpath = "//tr[@data-index=\"0\"]")
	private WebElement selectShipmentid;

	@FindBy(xpath = "//tr[@data-index=\"0\"]")
	private WebElement selectCarrier;

	@FindBy(xpath = "//button[@id='cmdOk']")
	private WebElement cmdOk;

	@FindBy(id = "btnSitesLoad")
	private WebElement SitesLoad;

	@FindBy(id = "btnOrgLoad")
	private WebElement OrganizationsLoad;

	@FindBy(id = "cmbPackType")
	private List<WebElement> PackageType; //

	@FindBy(id = "txtInvoiceFrom")
	private WebElement ShipmentIdFrom;
	
	@FindBy(xpath ="//input[@id='txtInvoiceTo']")
	private WebElement ShipmentIdTO;

	@FindBy(id = "txtPackageFrom")
	private WebElement PackageIdFrom;
	
	@FindBy(id = "txtPackageTo")
	private WebElement PackageIdTo;

	@FindBy(id = "txtTrackingFrom")
	private WebElement TrackingNumber;

	@FindBy(xpath= "//button[@id='SearchCriteriaOkClick']")
	private WebElement SearchCriteriaOkClick;
	
	@FindBy(id = "txtCustomer")
	private WebElement CustomerCompany;
	
	@FindBy(xpath = "//tr[@data-index='0']")
	private WebElement SelectShipment;

	@FindBy(id = "IWLDatePickerFrom")
	private WebElement iWLDatePickerFrom;

	@FindBy(id = "VScmdTrackShipment")
	private WebElement TrackShipment;

	@FindBy(id = "VScmdQuery")
	private WebElement VScmdQuery;

	public ShipViaLoad clickOnTransaction() throws InterruptedException {
		Thread.sleep(5000);
		click(Transaction);
		logger.info("Transaction successful");
		return this;

	}
	public ShipViaLoad clickOnViewShipment() throws InterruptedException {

		waitForVisibilityWait(ViewShipment);
		click(ViewShipment);
		logger.info("ViewShipment successful");
		Thread.sleep(2000);
		return this;
	}
	public ShipViaLoad clickOnShipViaLoad() throws InterruptedException {
		Thread.sleep(6000);
		click(ShipViaLoad);
		logger.info("ShipViaLoad successful");
		return this;
	}
	public ShipViaLoad clickOnUserGroup() throws InterruptedException {
		
		WebElement usergroup = driver.findElement(By.xpath("//Select[@id='ddlUserGroup']"));
		Select Vavg = new Select(usergroup);
		Vavg.selectByValue("45"); // VA_UG

		logger.info("Login successful");
		return this;
	}

	public ShipViaLoad clickOnSelectGroupOkClick() throws InterruptedException { // for the shipvia

		click(SelectGroupOkClick);
		logger.info("clickOnSelectGroupOkClick successful");
		Thread.sleep(2000);
		return this;
	}
	public ShipViaLoad clickOnSelectShipvia() throws InterruptedException { // to select shipvia-----------------------
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.id("cmbShipVia"));
		Select shipvia = new Select(dropdown);

		shipvia.selectByVisibleText(prop.getProperty("shipviaload"));
		// click(cmbShipViaid);

		logger.info("Select Shipvia successful");
		
		return this;
	}
	public ShipViaLoad clickOnSelectCarriers() throws InterruptedException { // to select shipvia-----------------------

		WebElement dropdown = driver.findElement(By.id("cmbAccount"));
		Select Carriers = new Select(dropdown);

		Carriers.selectByVisibleText(prop.getProperty("CarriersLoad"));
		// click(cmbShipViaid);

		logger.info("Select Carriers successful");
		Thread.sleep(3000);

		return this;
	}
	public ShipViaLoad clickOnSelectOrganizations() throws InterruptedException { // to select shipvia-----------------------

		WebElement dropdown = driver.findElement(By.id("cmbOrg"));
		Select Organizations = new Select(dropdown);

		Organizations.selectByVisibleText(prop.getProperty("OrganizationsLoad"));
		// click(cmbShipViaid);

		logger.info("Select Organizations successful");
		Thread.sleep(3000);

		return this;
	}
	public ShipViaLoad clickOnSelectSite() throws InterruptedException { // to select shipvia-----------------------
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.id("VScmbSite"));
		Select Site = new Select(dropdown);

		Site.selectByVisibleText(prop.getProperty("SiteLoad"));
		// click(cmbShipViaid);

		logger.info("Select Site successful");
		Thread.sleep(3000);

		return this;
	}
	
	public ShipViaLoad SvIdSelect() throws InterruptedException {

		WebElement d = driver.findElement(By.id("tblShipmentList"));
		Select s = new Select(d);
		List<WebElement> dr = s.getOptions();
		System.out.println("total month is.." + dr.size());

		//shipvia.selectByVisibleText(prop.getProperty("shipviaload"));
		// click(cmbShipViaid);
          
		logger.info("clickOnSelectShipviaLogin successful");
		Thread.sleep(9000);
		return this;
	}

	public ShipViaLoad clickOnSearchCriteriaOk() throws InterruptedException { // Click on ok on Search Criteria tab
		
		Thread.sleep(3000);
		click(SearchCriteriaOkClick);
		logger.info("clickOnSearchCriteriaOkClick successful");
		return this;
	}

	public ShipViaLoad clickOnSelectShipment() throws InterruptedException {
		Thread.sleep(3000);
		click(SelectShipment);
		logger.info("clickOnSearchCriteriaOkClick successful");
		return this;
	}

	public ShipViaLoad clickOnselectShipmentid() throws InterruptedException {
		Thread.sleep(3000);
		click(selectShipmentid);
		logger.info("clickOnselectShipmentidsuccessful");
		return this;
	}

	public ShipViaLoad clickOncmdOk() throws InterruptedException {
		Thread.sleep(3000);
		click(cmdOk);
		logger.info("clickOncmdOk successful");
		return this;
	}

	public ShipViaLoad clickOnCarriersLoadAccount() throws InterruptedException {
		Thread.sleep(3000);
		click(CarriersLoadAccount);
		logger.info("clickOnCarriersLoadAccount successful");
		return this;
	}

	public ShipViaLoad clickOnSitesLoad() throws InterruptedException {
		Thread.sleep(3000);
		click(SitesLoad);
		logger.info("clickOnSitesLoad successful");
		return this;
	}

	public ShipViaLoad clickOnOrganizationsLoad() {
		click(OrganizationsLoad);
		logger.info("LoginclickOnOrganizationsLoad successful");
		return this;

	}

	public ShipViaLoad clickOnPackageType() throws InterruptedException {
		Thread.sleep(3000);
		// click(PackageType);
//		Thread.sleep(6000);
//		logger.info(" clickOnPackageTypesuccessful"); // PackageType
//
//		for (int i = 0; i < PackageType.size(); i++) {
//			String dropdownValue = PackageType.get(i).getText();
//
//			if (dropdownValue.equalsIgnoreCase("Return")) {
//				PackageType.get(i).click();
//
//				System.out.println("birthmonthValue is " + dropdownValue);
//			}
//		}
		String desiredText =prop.getProperty("PackageType");
		WebElement dropdown = driver.findElement(By.id("cmbPackType"));
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		   for (WebElement option : options) {
	            // Check the visible text of each option
	            String visibleText = option.getText();
	            if (visibleText.equals(desiredText)) {
	                // Select the desired option
	                select.selectByVisibleText(visibleText);
	                break; // Break out of the loop once the option is selected
	            }
	        }
		
//		Thread.sleep(6000);
//		WebElement usergroup = driver.findElement(By.id("cmbPackType"));
//		Thread.sleep(5000);
//		Select Return = new Select(usergroup); // Return
//		Return.selectByValue("3");
		return this;
	}
	public ShipViaLoad EnterOnCustomerCompany() {
		sendkeys(CustomerCompany, prop.getProperty("Customer/Company"));
		logger.info("Enter Customer/Company successful"); // To send ShipmentId
		return this;
	}
	public ShipViaLoad EnterOnShipmentIdFrom() {
		sendkeys(ShipmentIdFrom, prop.getProperty("ShipmentIdFrom"));
		logger.info("Enter ShipmentId successful"); // To send ShipmentId
		return this;
	}
	public ShipViaLoad EnterOnShipmentIdTO() {
		sendkeys(ShipmentIdTO, prop.getProperty("ShipmentIdTo"));
		logger.info("Enter ShipmentId successful"); // To send ShipmentId
		return this;
	}

	public ShipViaLoad EnterOnPackageIdFrom() {
		sendkeys(PackageIdFrom, prop.getProperty("PackageIdFrom")); // To send PackageId

		logger.info("Enter PackageIdFrom successful");
		return this;
	}
	public ShipViaLoad EnterOnPackageIdTo() {
		sendkeys(PackageIdTo, prop.getProperty("PackageIdTo")); // To send PackageId

		logger.info("Enter PackageIdTo successful");
		return this;
	}

	public ShipViaLoad EnterOnTrackingNumber() throws InterruptedException {
		sendkeys(TrackingNumber, prop.getProperty("TrackingNumber")); // // To send TrackingNumber
		logger.info("Enter TrackingNumber successful");
		Thread.sleep(4000);
		return this;
	}

	public ShipViaLoad clickOnCarrier() {
		click(selectCarrier);
		logger.info("selectCarrier successful");
		return this;
	}

	public ShipViaLoad clickOnProship1() throws InterruptedException {
		click(Proship1);
		logger.info("Proship1 successful");
		return this;
	}

	public ShipViaLoad clickOnSite() throws InterruptedException {
		click(Sites);
		logger.info("Sites successful");
		return this;
	}

	public ShipViaLoad datepickfrom() throws InterruptedException {
		Thread.sleep(5000);
		//driver.findElement(By.id("IWLDatePickerFrom")).click();
		//driver.findElement(By.xpath("//td[text()='12']")).click(); // TO select the date
		
		
		WebElement element=driver.findElement(By.id("IWLDatePickerFrom"));
		JavascriptExecutor js= ((JavascriptExecutor)driver);
		//String date=prop2.getProperty("Date");
		js.executeScript("arguments[0].value ='02/03/2023';", element);     //month/date/year
		//js.executeScript("arguments[0].value = arguments[1];", element, date);
//		String Monthyear="February 2023";
//		while(true) {
//			String m=driver.findElement(By.xpath("//th[@class='datepicker-years']")).getText();
//			System.out.println(m);
//		
//		if(m.equals(Monthyear)) {
//			
//			break;
//		}
//		else {
//			driver.findElement(By.xpath("//th[@class='next']")).click();
//		}
//		
//		driver.findElement(By.xpath("//td[text()='12']")).click();  // TO select the date 
//		}
			
		return this;
	}
	public ShipViaLoad VSManifestedDate() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, 15);
//		WebElement d = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='IWLDatePickerFrom']"))));
//		d.click();
		Thread.sleep(5000);
		driver.findElement(By.id("IWLDatePickerFrom")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[text()='1']")).click(); // TO select the date
		return this;
	}
	public ShipViaLoad VSManifestedDateTo() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, 15);
//		WebElement d = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='IWLDatePickerFrom']"))));
//		d.click();
		Thread.sleep(5000);
		driver.findElement(By.id("IWLDatePickerTo")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[text()='16']")).click(); // TO select the date
		return this;
	}

	public ShipViaLoad clickOnTrackShipment() {
		click(TrackShipment);
		logger.info("TrackShipment successful");
		return this;
	}

	public ShipViaLoad clickOnVScmdQuery() {
		click(VScmdQuery);
		logger.info("VScmdQuery successful");
		return this;
	}
	public ShipViaLoad checkmethod() {
		
		WebElement elementid= driver.findElement(By.id("VStxtInvoiceNumber"));
	     String expetatedID=prop.getProperty("ShipmentIdFrom");
		 String actualID = elementid.getText();
		return this;
	}

}