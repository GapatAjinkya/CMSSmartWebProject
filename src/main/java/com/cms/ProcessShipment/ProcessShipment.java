package com.cms.ProcessShipment;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cms.Base.BaseClass;

public class ProcessShipment extends BaseClass{
	
	public ProcessShipment(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	Logger logger = LogManager.getLogger("ProcessShipment");
	
	@FindBy(id = "menu_item_2")
	private WebElement Transaction;

	@FindBy(id = "menu_item_21")
	private WebElement processshipment;
	
	public ProcessShipment clickOnTransaction() throws InterruptedException  {
		Thread.sleep(3000);
		click(Transaction);
		logger.info("Transaction successful");
		return this;
	}
	public ProcessShipment Clickonprocessshipment() {
		click(processshipment);
		logger.info("clickOnprocessshipment successful");
		logger.info("processshipment Explorer Window opened ");
		return this;
	}
//---------------------------------------------------------------------------------	
	@FindBy(xpath ="//span[@onclick='btnSearch_PS()']")
	private WebElement ShipViaSearch;
	public ProcessShipment ClickonShipViaSearch() throws InterruptedException {
		Thread.sleep(10000);
		click(ShipViaSearch);
		logger.info("clickOnShipViaSearch successful");
		return this;
	}
//------------------------------------------------------------------------------------------	
	@FindBy(xpath ="//select[@id='txtShipVia']")
	private WebElement ShipViaDropdownid;
//	public ProcessShipment ShipViaDropdownid() throws InterruptedException {
//		String abc=prop.getProperty("SelectShipvia");
//		//click(ShipViaDropdownid);
//		Thread.sleep(10000);	
//		WebElement dropdown = driver.findElement(By.xpath("//select[@id='txtShipVia']"));
//		Select select = new Select(dropdown);
//		select.selectByValue(abc);
//		
//		logger.info("Shipvia Select  successful");	
//		return this;
//	}
	
	@FindBy(xpath = "//input[@id='radCodeSS']")
	private WebElement radio;
	public ProcessShipment codeselect() throws InterruptedException {
		Thread.sleep(3000);
		 WebElement radioButton = driver.findElement(By.xpath("//input[@id='radCodeSS']"));
		 if (!radioButton.isSelected()) {
	            radioButton.click();
	            logger.info("ShipVias Field Selected  ");
	}
			return this;
	}
	@FindBy(id = "txtSCSearchSS")
	private WebElement Searchfor;
	public ProcessShipment EnterSearchfor() throws InterruptedException {
		Thread.sleep(3000);
		sendkeys(Searchfor,prop.getProperty("SelectShipvia"));
		logger.info("Sent Search for");
		return this;
		
	}
	@FindBy(xpath ="//button[@id='btnSearchOk_PS']")
	private WebElement ClickonOk;
	public ProcessShipment ClickonOk() throws InterruptedException {
		Thread.sleep(5000);
		click(ClickonOk);
		return this;
	}
	@FindBy(id = "radCode")
	private WebElement CustomerCode;
	public ProcessShipment CustomerCode() {
		click(CustomerCode);
		logger.info("clicked on Customercode");
		return this;
	}
	@FindBy(xpath = "//button[@onclick='AddressesClick()']")
	private WebElement AddCustomer;
	public ProcessShipment AddCustomer() {
		click(AddCustomer);
		logger.info("clicked on AddCustomer");
		return this;
	}
	
	@FindBy(id = "txtSCSearch")
	private WebElement CustomerSearch;
	public ProcessShipment EnterCustomerSearch() {
		sendkeys(CustomerSearch,prop2.getProperty("EnterCustomerSearch"));
		logger.info("EnterCustomerSearch");
		return this;
	}
	
	@FindBy(id = "selCutomerList")
	private WebElement CustomerList;
	public ProcessShipment SelectCustomerList() {
		String CarrierAccount = prop2.getProperty("CustomerType");
		WebElement dropdown = driver.findElement(By.id("selCutomerList"));
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			// Check the visible text of each option
			String visibleText = option.getText();
			if (visibleText.equals(CarrierAccount)) {
				// Select the desired option
				select.selectByVisibleText(visibleText);
				break; // Break out of the loop once the option is selected
			}		
	}
		logger.info("SelectCustomerList");
		return this;
}
	@FindBy(xpath = "//button[@onclick='onCustomerSearchOkClick()']")
	private WebElement CustomerSearchOk;
	public ProcessShipment ButtonOk() {
		click(CustomerSearchOk);
		logger.info("CustomerSearchOk");
		return this;
	}
	@FindBy(xpath ="//tr[@data-index='0']")
	private WebElement SelectCustomer;
	public ProcessShipment SelectCustomer() {
		click(SelectCustomer);
		logger.info("Customer Selected");
		return this;
	}
	@FindBy(id ="addressformOk")
	private WebElement CustomerOk;
	public ProcessShipment ClickonOkCustomer() {
		click(CustomerOk);
		logger.info("Click on ok ");
		return this;
	}
	@FindBy(id ="txtManual")
	private WebElement Weight;
	public ProcessShipment EnterWeight() {
		sendkeys(Weight,"1");
		logger.info("Weight Entered");
		return this;
	}
	@FindBy(id ="btnShipClick")
	private WebElement Shipbutton;
	public ProcessShipment ClickonShipbutton() {
		click(Shipbutton);
		logger.info("Shipbutton");
		return this;
	}
	
	public ProcessShipment AddCustomerProcess() throws InterruptedException {
		
		Thread.sleep(5000);
		click(AddCustomer);
		logger.info("Click on add Customer");
		sendkeys(CustomerSearch,prop2.getProperty("EnterCustomerSearch"));
		String CarrierAccount = prop2.getProperty("CustomerType");
		WebElement dropdown = driver.findElement(By.id("selCutomerList"));
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			// Check the visible text of each option
			String visibleText = option.getText();
			if (visibleText.equals(CarrierAccount)) {
				// Select the desired option
				select.selectByVisibleText(visibleText);
				break; // Break out of the loop once the option is selected
			}		
	}
		logger.info("SelectCustomerList");
		Thread.sleep(4000);
		click(CustomerSearchOk);
		logger.info("CustomerSearchOk ");
		Thread.sleep(5000);
		click(SelectCustomer);
		Thread.sleep(5000);
		click(CustomerOk);
		return this;
	}
	}