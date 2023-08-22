package com.cms.archivedmanifests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.cms.Base.BaseClass;
import org.testng.Assert;
public class ArchivedManifests extends BaseClass {

	public ArchivedManifests(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	Logger logger = LogManager.getLogger("SupportTables");

	@FindBy(id = "menu_item_2")
	private WebElement Transaction;

	public ArchivedManifests clickOnTransaction() {
		click(Transaction);
		logger.info("Transaction successful");
		return this;
	}

	@FindBy(id = "menu_item_25")
	private WebElement ArchivedManifests;

	public ArchivedManifests clickonArchivedManifests() {
		click(ArchivedManifests);
		logger.info(" click on ArchivedManifests successful");
		return this;
	}

	@FindBy(id = "AMEddlCarriers")
	private WebElement carriers;

	public ArchivedManifests selectcarriers() throws InterruptedException {
		// To select carriers
		Thread.sleep(5000);
		Select dropdown = new Select(carriers);
		dropdown.selectByVisibleText(prop2.getProperty("ArchivedManifestsCarriers"));
		logger.info(" selectcarriers successful");
		return this;
	}

	@FindBy(id = "AMEDateSearch")
	private WebElement dateSearch;

	public ArchivedManifests ClickonDateSearchButton() throws InterruptedException {
		Thread.sleep(3000);
		click(dateSearch);
		logger.info(" selectThedate successful");
		return this;
	}

	@FindBy(id = "AMDFOkClick")
	private WebElement okclick;
	@FindBy(id = "AMDFCancelClick")
	private WebElement CancelClick;
	@FindBy(id = "IWLDatePickerFromAME")
	private WebElement datepicker;

	public ArchivedManifests selectdatefrom() throws InterruptedException {
		Thread.sleep(5000);
		click(datepicker);

//	driver.findElement(By.xpath("//th[@class='prev']")).click();
//	Thread.sleep(5000);
//	driver.findElement(By.xpath("//td[text()='22']")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[@class='day'][contains(.,'28')]")).click();
		Thread.sleep(3000);
		click(okclick);
		logger.info(" selectdatefrom successful");
		return this;

	}

	public ArchivedManifests Toselectdatefrom() throws InterruptedException {

	Thread.sleep(5000);
	
	
	String month="June 2023";
	String day="30";
	
	WebElement datepicker=driver.findElement(By.id("txtManifestFormDatePicker"));
	datepicker.click();
	
	
	while(true){
	String text=driver.findElement(By.xpath("//th[@class='datepicker-switch']")).getText();
	
	if(text.equals(month)) 
	{
		break;
	}
	else 
	{
		driver.findElement(By.xpath("//body[1]/div[103]/div[1]/table[1]/thead[1]/tr[1]/th[3]")).click();
	}
	}
	driver.findElement(By.xpath("//body[1]/div[103]/div[1]/table[1]/tbody[1]/tr/td[contains(text(),"+day+"")).click();
	
/*
    WebElement Dday=driver.findElement(By.xpath("//table[@class=\" table-condensed\"]/tbody/tr/td[contains(text(),30)]"));
		while(true) {
			String text=Monthcheck.getText();
	
			if(text.equals(month)) {
				break;
			}
			else {
				Thread.sleep(2000);
				nextMonth.click();
			}
		}
		Thread.sleep(2000);
		System.out.println("corrct date is " +Dday.getText());
		Dday.click();
		/*
	
		WebElement element = driver.findElement(By.id("IWLDatePickerFromAME"));
	    JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].value ='12/03/2022';", element); // month/date/year
		*/
		click(okclick);

//		click(datepicker);
//		WebElement year=driver.findElement(By.xpath("//th[contains(text(),'May 2023')]"));
//		click(year);
//		WebElement month=driver.findElement(By.xpath("//span[contains(text(),'Jan')]"));
//		click(month);
//		WebElement date=driver.findElement(By.xpath("//td[text()='2']"));
//		click(date);

	/*
		driver.findElement(By.id("IWLDatePickerFromAME")).click();
		WebElement month = driver.findElement(By.xpath("//div[@class='datepicker-months']"));
		while (!month.getText().contains("Aug"))
		{

			driver.findElement(By.xpath("//th[@class='next']")).click();
		}
		int count = driver.findElements(By.xpath("//div[@class='datepicker-days']")).size();
		for (int i = 0; i < count; i++)
		{
			String text = driver.findElements(By.xpath("//div[@class='datepicker-days']")).get(i).getText();
			if (text.equalsIgnoreCase("15"))
			{
				driver.findElements(By.xpath("//div[@class='datepicker-days']")).get(i).click();
				System.out.println(text);
				break;
			}
		}
		
		*/
	//------------------------------------------------------------------------------------------------------
	
	//Final------------------****************************
	/*
	driver.findElement(By.id("IWLDatePickerFromAME")).click();
	WebElement month = driver.findElement(By.xpath("//th[@class='datepicker-switch']"));
	LocalDate currentDate=LocalDate.now();
    System.out.println(currentDate);
	while (!month.getText().contains("December 2023")) {
	    driver.findElement(By.xpath("//th[@class='prev']")).click();
	}
	List<WebElement> days = driver.findElements(By.xpath("//div[@class='datepicker-days']//td[@class='day']"));
	for (WebElement day : days) {
	    String text = day.getText();
	    if (text.equalsIgnoreCase("15")) {
	        day.click();
	        System.out.println(text);
	        break;
	    }
	/*
    LocalDate desiredDate = LocalDate.of(2023, 7, 15);
    int year1 = desiredDate.getYear();
    int month1 = desiredDate.getMonthValue();
    int day1 = desiredDate.getDayOfMonth();
    WebElement datePicker = driver.findElement(By.id("IWLDatePickerFromAME"));
    datePicker.click();
    
    while (true) {
        // Extract the displayed month and year from the date picker
        String displayedMonthYear = driver.findElement(By.xpath("//th[@class='datepicker-switch']")).getText();
        LocalDate displayedDate = LocalDate.parse(displayedMonthYear, DateTimeFormatter.ofPattern("MMMM yyyy"));

        // Compare the displayed month and year with the desired month and year
        if (displayedDate.isEqual(desiredDate)) {
            break; // Exit the loop if the desired month and year are reached
        } else if (displayedDate.isBefore(desiredDate)) {
            // Navigate to the next month
            driver.findElement(By.xpath("//th[@class='prev']")).click();
        } else {
            // Navigate to the previous month
            driver.findElement(By.xpath("//th[@class='prev']")).click();
        }
    }

    // Find and click on the element representing the desired date
    String dateXPath = String.format("//td[@data-date='%02d']", day1);
    driver.findElement(By.xpath(dateXPath)).click();

    // Close the browser
    driver.quit();

    */

	
	// Try this option ****************************************
		/*	
			  driver.findElement(By.id("IWLDatePickerFromAME")).click();
	    	  String Expected_Month_Year="March 2023";
	    	  
	    	  while(true) {
	    	 String m= driver.findElement(By.xpath("//*[@class='ui-datepicker-title']")).getText();
	    	 System.out.println(m);
	    	 
	    	 if(m.equals(Expected_Month_Year)) {
	    		 break; 
	    	 }
	    	 else
	    	 {
	    		 driver.findElement(By.xpath("//*[text()='Next']")).click();
	    	 }
	    	  }
	    	  driver.findElement(By.xpath("//*[@data-date='10']")).click();
	          System.out.println(driver.findElement(By.id("datepeaker")).getText());
	         */
		
		return this;
	}
	@FindBy(id = "IWLDatePickerToAME")
	private WebElement datepickerTo;

	public ArchivedManifests selectdateTo() throws InterruptedException {
		Thread.sleep(3000);
		click(datepickerTo);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[@class='day'][contains(.,'28')]")).click();
		Thread.sleep(3000);
		click(okclick);
		logger.info(" selectdateTo successful");
		return this;
	}

	@FindBy(xpath = "//table/tbody/tr[3]") // to select the carrier
	private WebElement Selectcarrier;

	public ArchivedManifests Selectcarrierfromtable(String newcode) throws InterruptedException {
		
		
//		WebElement okclick = driver.findElement(By.xpath("//button[@onclick='CAF_OkClick()']"));
//		okclick.click();
		Thread.sleep(6000);
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblArchivedManifestList']//td[1]"));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			System.out.println(value);
			if (value.equals(newcode))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
			if(dataStatus) {
				Thread.sleep(5000);
				WebElement Customercode =driver.findElement(By.xpath("//table[@id='tblArchivedManifestList']//td[contains(text(), '" + value + "')]"));
				Customercode.click();
			}
		}
		Assert.assertTrue(dataStatus, "Carrier  is not edit");
		logger.info("Carrier edit is successful ");
		
		
		
		
		
		click(Selectcarrier); // To Select the carrier from table
		logger.info(" Selectcarrierfromtable successful");
		return this;
	}

	@FindBy(id = "AMEView")
	private WebElement View;

	public ArchivedManifests clickonView() {
		click(View);
		logger.info(" Click on View successful");
		return this;
	}

//---------------------------------------------------------------------------------------------------------------	
	@FindBy(id = "AMEPrevious")
	private WebElement AMEPrevious;

	public ArchivedManifests clickonAMEPrevious30days() {
		click(AMEPrevious);
		logger.info(" Click on Previous30days successful");
		return this;
	}

	@FindBy(id = "AMENext")
	private WebElement AMENext;

	public ArchivedManifests clickonAMENext30days() {
		click(AMENext);
		logger.info(" Click on Next30days successful");
		return this;
	}

	@FindBy(xpath = "//table/tbody/tr[1]")
	private WebElement Shipmentstable;
	@FindBy(id = "cmdOk")
	private WebElement ShipmentstablecmdOk;

	public ArchivedManifests CarrierfromShipmentstable() throws InterruptedException {
		Thread.sleep(3000);
		click(Shipmentstable);
		click(ShipmentstablecmdOk);
		logger.info(" Select Carrier from Shipments table successful");
		return this;
	}

	@FindBy(id = "VStxtInvoiceNumber")
	private WebElement Shipmentid;

	public ArchivedManifests assertuse() {

		String text = Shipmentid.getText();
		System.out.println(text);

		return this;
	}

}
