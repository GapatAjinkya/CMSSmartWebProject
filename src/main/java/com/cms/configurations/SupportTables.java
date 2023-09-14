package com.cms.configurations;

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

public class SupportTables extends BaseClass{

	public SupportTables(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	Logger logger = LogManager.getLogger("SupportTables");
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(id="menu_item_4")
	private WebElement Configuration;
	public SupportTables clickonConfiguration() throws InterruptedException {
		Thread.sleep(5000);
		click(Configuration);
		logger.info("clickOnConfiguration successful");
		return this;

	}

	@FindBy(id="menu_item_45")
	private WebElement SupportTables;
	public SupportTables clickonSupportTables() throws InterruptedException {
		Thread.sleep(3000);
		click(SupportTables);
		logger.info("clickOnSupportTables successful");
		return this;
	}
	@FindBy(id="menu_item_450")
	private WebElement Cartons;
	public SupportTables clickonCartons() throws InterruptedException {
		Thread.sleep(5000);
		click(Cartons);
		logger.info("clickOnCartons successful");
		return this;
	}
	@FindBy(id="radCode")
	private WebElement radCode;
	public SupportTables SelectCode() throws InterruptedException {
		Thread.sleep(3000);
		click(radCode);
		logger.info("Searchfor successful");
		return this;
	}
	@FindBy(id="txtSCSearch")
	private WebElement Searchfor;
	public SupportTables EnterSearchfor() throws InterruptedException {
		Thread.sleep(3000);
		sendkeys(Searchfor,prop.getProperty("CartonCode"));
		logger.info("Searchfor successful");
		return this;
	}

	@FindBy(xpath ="//button[@onclick='onCartonSearchOkClick()']")
	private WebElement OkClick;
	public SupportTables CartonSearchOkClick() throws InterruptedException {
	Thread.sleep(3000);
		click(OkClick);
	logger.info("CartonSearchOkClick successful");
		return this;
	}
	@FindBy(id ="CartonAdd")
	private WebElement CartonAdd;
	public SupportTables ClickonCartonAdd() throws InterruptedException {

	Thread.sleep(5000);
	click(CartonAdd);
	logger.info("ClickonCartonAdd successful");
		return this;
	}
	//-
	@FindBy(id ="CF_txtCode")
	private WebElement txtCode;
	public SupportTables EntertxtCode() throws InterruptedException {
	Thread.sleep(3000);
	sendkeys(txtCode,prop.getProperty("CartonCode"));
	logger.info("EntertxtCode successful");
		return this;
	}

	@FindBy(id ="CF_txtDescription")
	private WebElement Description;
	public SupportTables EnterDescription() throws InterruptedException {
		Thread.sleep(3000);
	sendkeys(Description,"CartonAG For Testing ");
	logger.info("EnterDescription successful");

		return this;
	}

	@FindBy(id ="CF_txtLength")
	private WebElement Length;
	public SupportTables EnterLength() throws InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].value = '10';", Length);

	Thread.sleep(3000);

	//sendkeys(Length,"1");
	logger.info("EnterLength successful");
		return this;
	}
	@FindBy(id ="CF_txtWidth")
	private WebElement Width;
	public SupportTables EnterWidth() throws InterruptedException {
		Thread.sleep(3000);
	 js.executeScript("arguments[0].value = '10';", Width);
	logger.info("EnterWidth successful");
		return this;
	}
	@FindBy(id ="CF_txtHeight")
	private WebElement Height;
	public SupportTables EnterHeight() throws InterruptedException {
		Thread.sleep(3000);
	 js.executeScript("arguments[0].value = '10';", Height);
	//sendkeys(Height,"1");
	logger.info("EnterHeight successful");
		return this;

	}
	@FindBy(id ="CF_txtWeight")
	private WebElement Weight;
	public SupportTables EnterWeight() {
	js.executeScript("arguments[0].value = '10';", Weight);
	//sendkeys(Weight,"1");
	logger.info("EnterWeight successful");
		return this;
	}

	@FindBy(id ="CF_txtUom")
	private WebElement UOM;
	public SupportTables Enteruom() throws InterruptedException {
		Thread.sleep(3000);
	sendkeys(UOM,"cm");
	logger.info("ClickonCartonOK successful");
		return this;
	}

	@FindBy(xpath ="//button[@onclick='Carton_OK()']")
	private WebElement CartonOK;
	public SupportTables ClickonCartonOK() throws InterruptedException {
		Thread.sleep(3000);
	click(CartonOK);
	logger.info("ClickonCartonOK successful");
		return this;
	}

	public SupportTables ClearTab() {
		Length.clear();
		Width.clear();
		Height.clear();

			return this;
		}

	//----------------------------------------------------------------------------------------

	@FindBy(id ="CartonEdit")
	private WebElement CartonEdit;
	public SupportTables ClickonCartonEdit() throws InterruptedException {

	Thread.sleep(3000);
	click(CartonEdit);
	logger.info("ClickonCartonEdit successful");
		return this;
	}

	public SupportTables Descriptionedit() throws InterruptedException {
		Thread.sleep(5000);
		Description.clear();
		sendkeys(Description,"EditCartonAG");
		return this;
	}

	@FindBy(id ="CartonDelete")
	private WebElement CartonDelete;
	public SupportTables ClickonCartonDelete() throws InterruptedException {
	Thread.sleep(3000);
	click(CartonDelete);
	logger.info("ClickonCartonDelete successful");
		return this;
	}

	//TO Delete and select the carton

	@FindBy(xpath="//tr[@data-index='0']")
	private WebElement SelectCarton;
	public SupportTables SelectCarton() throws InterruptedException {
		Thread.sleep(3000);
	click(SelectCarton);
	logger.info("SelectCarton successful");
		return this;
	}
	@FindBy(id="btnConfirmBoxOk")
	private WebElement ok;
	public SupportTables ConfirmBoxOk() throws InterruptedException {
		Thread.sleep(3000);
	click(ok);
	logger.info("SelectCarton successful");
		return this;
	}



//-----------------------------------------------------------------------------------------------
//for The Customer


	@FindBy(id ="menu_item_451")
	private WebElement Customers;
	public SupportTables ClickonCustomers() throws InterruptedException {
		Thread.sleep(3000);
	click(Customers);
	logger.info("ClickonCustomers successful");
		return this;
	}
	@FindBy(id ="CSTCustRadCode")
	private WebElement Code;
	public SupportTables ClickonCustomersCode() throws InterruptedException {
		Thread.sleep(3000);
	click(Code);
	logger.info("ClickonCustomersCode successful");
		return this;
	}

	@FindBy(id ="txtCSTCustSearch")
	private WebElement CustomersSearch;
	public SupportTables EnterCustomersSearch() throws InterruptedException {
		Thread.sleep(3000);
	sendkeys(CustomersSearch,prop.getProperty("CustomerCode"));
	logger.info("EnterCustomersSearch successful");
		return this;
	}
	@FindBy(xpath="//button[@tabindex=\"8\"]")
	private WebElement CustomerOkClick;
	public SupportTables CustomerOkClick() throws InterruptedException {
		Thread.sleep(3000);
	click(CustomerOkClick);
	logger.info("CustomerOkClick successful");
		return this;
	}
	@FindBy(xpath="//table/tbody/tr[1]")
	private WebElement selectcustomerfromtable;
	public SupportTables selectcustomerfromtable() throws InterruptedException {
		Thread.sleep(3000);
		click(selectcustomerfromtable);

		return this;
	}
	@FindBy(id ="CSTCustDelete")
	private WebElement Delete;
	public SupportTables clickonDeleteCustomer() throws InterruptedException {
		Thread.sleep(3000);
		click(Delete);
		return this;
	}

	@FindBy(id ="CSTCustEdit")
	private WebElement CSTCustEdit;
	public SupportTables clickonEditCustomer() throws InterruptedException {
		Thread.sleep(3000);
		click(CSTCustEdit);
		return this;
	}

	@FindBy(id ="CSTCustAdd")
	private WebElement AddCustomer;
	public SupportTables ClickonAddCustomer() throws InterruptedException {

	Thread.sleep(5000);
		click(AddCustomer);
	logger.info("ClickonAddCustomer successful");
		return this;
	}

	@FindBy(id ="CFtxtXRef")
	private WebElement CustomerCode;
	public SupportTables EnterCustomerCode() {
	sendkeys(CustomerCode,prop.getProperty("CustomerCode"));
	logger.info("EnterCustomerCode successful");
		return this;
	}


	public SupportTables editcustomercode() throws InterruptedException {
		Thread.sleep(3000);
		CustomerCode.clear();
		sendkeys(CustomerCode,"TestCanada");
		logger.info("EnterCustomerCode successful");
			return this;

		}
	@FindBy(id ="CFtxtName")
	private WebElement Company;
	public SupportTables EnterCompany() throws InterruptedException {
		Thread.sleep(3000);
	sendkeys(Company,prop.getProperty("Company"));
	logger.info("EnterCompany successful");
		return this;
	}
	@FindBy(id ="CFtxtContact")
	private WebElement Contact;
	public SupportTables EnterContact() {

	sendkeys(Company,prop.getProperty("Contact"));
	logger.info("EnterContact successful");
		return this;
	}
	//--------------------------------------------------------------
	@FindBy(id ="CFtxtAddress1")
	private WebElement Address1;
	@FindBy(id ="CFtxtAddress2")
	private WebElement Address2;
	@FindBy(id ="CFtxtAddress3")
	private WebElement Address3;
	public SupportTables EnterAddressall() throws InterruptedException {
		Thread.sleep(3000);
	sendkeys(Address1,prop.getProperty("Address1"));
	sendkeys(Address2,prop.getProperty("Address2"));
	sendkeys(Address3,prop.getProperty("Address3"));

	logger.info("EnterAddressall successful");
		return this;
	}
	//-----------------------------------------------------------------
	@FindBy(id ="CFtxtAddCity")
	private WebElement AddCity;
	@FindBy(id ="CFtxtAddState")
	private WebElement AddState;
	@FindBy(id ="CFtxtAddZip")
	private WebElement AddZip;
	public SupportTables EnterAddCityStateZip() {
		sendkeys(AddCity,prop.getProperty("AddCity"));
		sendkeys(AddState,prop.getProperty("AddState"));
		sendkeys(AddZip,prop.getProperty("AddZip"));
		logger.info("EnterAddCityStateZip successful");
			return this;
		}

	@FindBy(id ="CFtxtCountry")
	private WebElement Country;

	public SupportTables SelectCountry() {

		String desiredText =prop.getProperty("Country");
		WebElement dropdown = driver.findElement(By.id("CFtxtCountry"));
		Select select = new Select(dropdown);

		select.selectByVisibleText(desiredText);

//		List<WebElement> options = select.getOptions();
//		   for (WebElement option : options) {
//	            // Check the visible text of each option
//	            String visibleText = option.getText();
//	            if (visibleText.equals(desiredText)) {
//	                // Select the desired option
//	                select.selectByVisibleText(visibleText);
//	                break; // Break out of the loop once the option is selected
//	            }
	//}
		   logger.info("EnterAddCityStateZip successful");
		   return this;
}
//---------------------------------------------------------------------------------
	@FindBy(id ="CFtxtAddPhone")
	private WebElement AddPhone;
	public SupportTables EnterPhone() {
		sendkeys(AddPhone,"123456");
		logger.info("EnterPhone successful");
			return this;
		}
	@FindBy(id ="CFtxtFax")
	private WebElement AddFax;
	public SupportTables EnterFax() {
		sendkeys(AddFax,"123456");
		logger.info("EnterFax successful");
			return this;
		}
	@FindBy(id ="CFtxtEmail")
	private WebElement AddEmail;
	public SupportTables EnterEmail() {
		sendkeys(AddEmail,"test@gmail.com");
		logger.info("EnterEmail successful");
			return this;
		}
	@FindBy(xpath ="//button[@onclick='OkClickCustomerForm()']")
	private WebElement OkClickCustomerForm;
	public SupportTables ClickCustomerFormOK() {
		click(OkClickCustomerForm);
		logger.info("EnterEmail successful");
			return this;
		}
//-------------------------------------------------------------------------------
}