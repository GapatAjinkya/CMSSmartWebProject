package com.cms.processreturn;

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


public class ProcessReturn extends BaseClass{

	public ProcessReturn(WebDriver rdriver) {

		driver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	Logger logger = LogManager.getLogger("ProcessReturn");

	@FindBy(id = "menu_item_2")
	private WebElement Transaction;
	public ProcessReturn clickOnTransaction() throws InterruptedException  {
		Thread.sleep(3000);
		click(Transaction);
		logger.info("Transaction successful");
		return this;
	}
	@FindBy(id = "menu_item_22")
	private WebElement processReturns;
	public ProcessReturn clickOnprocessReturns() throws InterruptedException  {
		Thread.sleep(3000);
		click(processReturns);
		logger.info("processReturns window opned ");
		return this;
	}
	@FindBy(xpath = "//select[@id='PRtxtReturnMethod']")
	private WebElement ReturnMethod;
	public ProcessReturn SelectReturnMethod() throws InterruptedException  {
		Thread.sleep(5000);
		WebElement Rmethod=driver.findElement(By.xpath("//select[@id='PRtxtReturnMethod']"));
		Select returnmethod= new Select (Rmethod);
		returnmethod.selectByVisibleText("1 Pickup Attempt");
		logger.info(" Return Method");
		return this;
	}
	@FindBy(xpath = "//span[@onclick='btnSearch_PS()']")
	private WebElement searchshipvia;
	public ProcessReturn Clickonsearchshipvia() throws InterruptedException  {
		Thread.sleep(5000);
		//WebElement dropdown=driver.findElement(By.xpath("//span[@onclick='btnSearch_PS()']"));
		click(searchshipvia);
		logger.info(" Click on searchshipvia successful");
		return this;
	}
	@FindBy(id = "txtSCSearchSS")
	private WebElement Searchfor;
	public ProcessReturn SendkeysSearchfor() throws InterruptedException {
		Thread.sleep(5000);
		sendkeys(Searchfor,"DHLG_Pro_Plus_SM631");
		logger.info(" SendkeysSearchfor successful");
		return this;
	}
	@FindBy(xpath = "//button[@id='btnSearchOk_PS']")
	private WebElement ok;
	public ProcessReturn Clickonok() throws InterruptedException {
		try {
			Thread.sleep(3000);
			click(ok);
		}catch(Exception ex) {
			System.out.println("An error occurred: " + ex.getMessage());
		}
		logger.info("Click on ok ");
		return this;

	}
	@FindBy(id = "PRIWLDatePicker")
	private WebElement Datepicker;
	public ProcessReturn SelectDate() throws InterruptedException {

		Thread.sleep(3000);
		WebElement element = driver.findElement(By.id("PRIWLDatePicker"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].value ='7/03/2023';", element); // month/date/year
		logger.info(" SelectDate successful");
		return this;
	}
//----------------------------------------------------------------------------

	@FindBy(id = "PRbtnCustomerSearch")
	private WebElement PRbtnCustomerSearch;
	public ProcessReturn Clickoncustomer() throws InterruptedException {
		Thread.sleep(5000);
		click(PRbtnCustomerSearch);
		logger.info(" Clickoncustomer successful");
		return this;
	}
	@FindBy(id = "radCode")
	private WebElement customercode;
	public ProcessReturn customercode() throws InterruptedException {
		Thread.sleep(5000);
		click(customercode);
		logger.info(" customercode  successful");
		return this;
	}
	@FindBy(id = "txtSCSearch")
	private WebElement searchcustomer;
	public ProcessReturn Entersearchcustomer() throws InterruptedException {
		Thread.sleep(5000);
	sendkeys(searchcustomer,prop2.getProperty("EnterCustomerSearch"));
		logger.info(" Entersearchcustomer  successful");
		return this;
	}
	@FindBy(id = "selCutomerList")
	private WebElement selCutomerList;
	public ProcessReturn selCutomerList() throws InterruptedException {
		Thread.sleep(5000);
		WebElement List = driver.findElement(By.id("selCutomerList"));
		Select CustomerList = new Select(List);
		CustomerList.selectByValue("1"); // To select Global
		logger.info(" selCutomerList  successful");
		return this;
	}
	@FindBy(xpath = "//button[@onclick='onCustomerSearchOkClick()']")
	private WebElement CustomerSearchOkClick;
	public ProcessReturn ClickonCustomerSearchOkClick() throws InterruptedException {
		Thread.sleep(5000);
		click(CustomerSearchOkClick);
		logger.info(" CustomerSearchOkClick  successful");
		return this;
	}

	@FindBy(xpath = "//tr[@data-index='0']")
	private WebElement selectcustomer;
	public ProcessReturn selectcustomerfromlist() throws InterruptedException {
		Thread.sleep(5000);
		click(selectcustomer);
		logger.info(" selectcustomer  successful");
		return this;

	}
	@FindBy(id = "addressformOk")
	private WebElement okcustomer;
	public ProcessReturn  ClickonOKcustomer() throws InterruptedException {
		Thread.sleep(5000);
		click(okcustomer);
		logger.info(" ClickonOKcustomer  successful");
		return this;
	}
	@FindBy(xpath  = "//button[@id='btnShipClickPR']")
	private WebElement processReturn;
	public ProcessReturn  ClickonprocessReturn() throws InterruptedException {
		Thread.sleep(5000);
		click(processReturn);
		logger.info(" processReturn  successful");
		return this;
	}


}
