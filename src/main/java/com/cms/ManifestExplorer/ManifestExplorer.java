package com.cms.ManifestExplorer;

import java.util.List;
import java.util.Properties;


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

public class ManifestExplorer extends BaseClass {

	public ManifestExplorer(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	Logger logger = LogManager.getLogger("ManifestExplorer");

	@FindBy(id = "menu_item_2")
	private WebElement Transaction;

	@FindBy(id = "menu_item_24")
	private WebElement manifestexplorer;
	
	public ManifestExplorer ClickonManifestExplorer() throws InterruptedException {
		Thread.sleep(3000);
		click(manifestexplorer);
		logger.info("clickOnManifestExplorer successful");
		logger.info("Manifest Explorer Window opened ");
		return this;
	}

	public ManifestExplorer clickOnTransaction()  {
		click(Transaction);
		logger.info("Transaction successful");
		return this;
	}

	public ManifestExplorer SelectCarriers() throws InterruptedException {
		
		
//		WebElement Manifestcarriers = driver.findElement(By.id("MEddlCarriers"));
//		Select Mcarriers = new Select(Manifestcarriers);
//		Mcarriers.selectByVisibleText(prop2.getProperty("SelectCarriers"));
//		logger.info("Manifest carriers is Selected ");
//        Thread.sleep(4000);
        
		Thread.sleep(6000);
		
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='MEddlCarriers']"));
	//	dropdown.click();
		String Carriers = prop.getProperty("SelectCarriers");
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			// Check the visible text of each option
			String visibleText = option.getText();
			if (visibleText.equalsIgnoreCase(Carriers)) {
				// Select the desired option
				select.selectByVisibleText(visibleText);
				break; // Break out of the loop once the option is selected
			}
		}
//		 assert select.getFirstSelectedOption().getText().equals(prop2.getProperty("SelectCarriers"));
		return this;
	}
	
	@FindBy(id = "MECreate")
	private WebElement MECreate;
	
	public ManifestExplorer clickOnMECreate() throws InterruptedException {
		Thread.sleep(3000);
		click(MECreate);
		logger.info("clickOnMECreate successful");
		return this;
	}
	
	@FindBy(xpath ="//button[@id='btnConfirmBoxOk']")
	private WebElement ConfirmBoxOk;
	
	public ManifestExplorer clickOnConfirmBoxOk() throws InterruptedException {
		Thread.sleep(3000);
		click(ConfirmBoxOk);
		logger.info("clickOnConfirmBoxOk successful");
	
		return this;
}
	@FindBy(id = "txtManifestFormDatePicker")
	private WebElement ManifestFormDatePicker;
	
	
	public ManifestExplorer clickOnManifestFormDatePicker() throws InterruptedException {
		click(ManifestFormDatePicker);
		logger.info("clickOnManifestFormDatePicker successful");
		Thread.sleep(3000);
		return this;
	}
	@FindBy(xpath = "//td[text()='22']")
	private WebElement SelectDate;
	
	public ManifestExplorer clickOnDate() {
		click(SelectDate);
		logger.info("SelectDate successful");
		return this;
	}
	@FindBy(xpath = "//select[@id='MFcmbAccount']")
	private WebElement CarrierAccount;
	
	public ManifestExplorer CarrierAccount() {
		
		
		
		String CarrierAccount = prop.getProperty("CarrierAccount");
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='MFcmbAccount']"));
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
		
//		WebElement selectedOption = select.getFirstSelectedOption();
//		 String selectedText = selectedOption.getText();
//		 System.out.println("CarrierAccount Selected option: " + selectedText);
		 
		return this;
	
	}
	@FindBy(id = "txtMFReferenceID")
	private WebElement MeReference;
	public ManifestExplorer EnterMeReference() {
		//sendkeys(MeReference,prop2.getProperty("MeReference"));
		sendkeys(MeReference,"555");
		
		logger.info("sendkeys Reference successful");
		return this;
	}
	@FindBy(xpath = "//input[@id='Type1']")
	private WebElement ActiveManifestradio;
	public ManifestExplorer ActiveManifestradio() throws InterruptedException {
		Thread.sleep(3000);
		WebElement radioButton = driver.findElement(By.xpath("//input[@id='Type1']"));
		 if (!radioButton.isSelected()) {
	            radioButton.click();
		
		//click(ActiveManifestradio);
		logger.info("ActiveManifestradio successful");
		
	}
		 return this;
	}
	@FindBy(xpath = "//input[@id='Type2']")
	private WebElement FutureManifestradio;
	public ManifestExplorer FutureManifestradio() throws InterruptedException {
		Thread.sleep(4000);
		 WebElement radioButton = driver.findElement(By.xpath("//input[@id='Type2']"));
		 if (!radioButton.isSelected()) {
	            radioButton.click();
//		click(FutureManifestradio);
		logger.info("FutureManifestradio successful");
		
	}
		 return this;
	}
	@FindBy(id = "btnManifestsFormOk")
	private WebElement buttonManifestsFormOk;
	public ManifestExplorer ClickonbuttonManifestsFormOk() {
		click(buttonManifestsFormOk);
		logger.info("Click on ok successful");
		return this;
	}
	@FindBy(id = "btnManifestsFormCancel")
	private WebElement Cancel;
	public ManifestExplorer ClickonbuttonCancel() {
		click(Cancel);
		logger.info("Click on Cancel successful");
		return this;
	}
	
	@FindBy(xpath= "//tr[@data-index='1']")
	private WebElement SelectManifest;
	public ManifestExplorer toSelectManifest() {   // in this we select new created manifest 
		click(SelectManifest);
		return this;
	}
	@FindBy(id= "MEClose")
	private WebElement MEClose;
	public ManifestExplorer ButtonClose() {
		click(MEClose);
		return this;
	}
	@FindBy(id= "MEPost")
	private WebElement Post;
	public ManifestExplorer ButtonPost() {
		click(Post);
		return this;
	}
	
//-----------------------------------------------------------------	
	public ManifestExplorer CreateActiveManifest() throws InterruptedException {	
		click(manifestexplorer);
		logger.info("Click on manifestexplorer successful");
		//click(ActiveManifestradio);
	
		Thread.sleep(5000);
		String Carriers = prop2.getProperty("SelectCarriers");
		WebElement dropdown = driver.findElement(By.id("MEddlCarriers"));
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			// Check the visible text of each option
			String visibleText = option.getText();
			if (visibleText.equals(Carriers)) {

				// Select the desired option
				select.selectByVisibleText(visibleText);
				break; // Break out of the loop once the option is selected
			}
	}
		click(MECreate);
	 	logger.info(" Click on Create successful");
		click(ConfirmBoxOk);
		logger.info(" Click on Confirm successful");
		Thread.sleep(6000);
		WebElement radioButton = driver.findElement(By.xpath("//input[@id='Type1']"));
		 if (!radioButton.isSelected()) {
	            radioButton.click();
	        	logger.info("ActiveManifestradio successful");
		
		String CarrierAccount = prop2.getProperty("CarrierAccount");
		WebElement dropdown1 = driver.findElement(By.xpath("//select[@id='MFcmbAccount']"));
		Select select1 = new Select(dropdown1);
		List<WebElement> options1 = select1.getOptions();
		for (WebElement option : options1) {
			// Check the visible text of each option
			String visibleText = option.getText();
			if (visibleText.equals(CarrierAccount)) {
				// Select the desired option
				select1.selectByVisibleText(visibleText);
				break; // Break out of the loop once the option is selected
			}
			logger.info("sendkeys Reference successful");
}		
		}
		 Thread.sleep(5000);
		 click(buttonManifestsFormOk);
		 logger.info("New Manifest Created successful");
		 return this;
		 }
	
	public ManifestExplorer toselectdatefromdatepicker() throws InterruptedException {
		
		
		Thread.sleep(5000);
		driver.findElement(By.id("txtManifestFormDatePicker")).click();
		WebElement month = driver.findElement(By.xpath("//th[@class='datepicker-switch']"));
	//	LocalDate currentDate=LocalDate.now();
	 //   System.out.println(currentDate);
		
		while (!month.getText().contains("July 2023")) {
		    driver.findElement(By.xpath("//th[@class='next']")).click();
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
		String month="July 2023";
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
			//next
			driver.findElement(By.xpath("//body[1]/div[103]/div[1]/table[1]/thead[1]/tr[1]/th[3]")).click();
		}
		}
		driver.findElement(By.xpath("//body[1]/div[102]/div[1]/table[1]/tbody[1]/tr/td[contains(text()," + day + ")]")).click();

		
		//body[1]/div[102]/div[1]/table[1]/tbody[1]/tr/td[contains(text(),30)]
		
	/*	
		-------------------------------------
		WebElement element=driver.findElement(By.id("txtManifestFormDatePicker"));
		//element.click();
		
		//String dateval=prop2.getProperty("Date");
		JavascriptExecutor js= ((JavascriptExecutor)driver);
		//js.executeScript("arguments[0].setAttribute('value','"+dateval+"');",element);
		js.executeScript("arguments[0].value ='7/3/2023';", element);     //month/date/year
//		 WebElement monthYearElement = driver.findElement(By.xpath("//div[@class='datepicker-months']"));
//	        String currentMonthYear = monthYearElement.getText();
//	        System.out.println(currentMonthYear);
	        
		
		//-----------------------------------------------------------------------------------------------------------
		/*
	        String myval=driver.findElement(By.xpath("//th[@colspan=\"5\"]")).getText();
	        System.out.println(myval);
	        
	        String month=myval.split("")[0].trim();
	        String year=myval.split("")[1].trim();
	        
	        while(!(month.equals("March") && year.equals("2024"))){
	        	driver.findElement(By.xpath("//th[@class='prev']")).click();
	         myval=driver.findElement(By.xpath("//th[@colspan=\"5\"]")).getText();
	 	        System.out.println(myval);
	 	         month=myval.split("")[0].trim();
	 	         year=myval.split("")[1].trim();
	        }
	       
	        driver.findElement(By.xpath("//td[@class='day'][contains(.,'23')]")).click();
	        
//	        WebElement nextButton = driver.findElement(By.xpath("//th[@class='next']"));
//	        WebElement prevButton = driver.findElement(By.xpath("//th[@class='prev']"));
	*/
//----------------------------------------------------------------------------------------------
		//div[@class='datepicker-months']
		//div[@class='datepicker-years']
	/*
		while(true) {
			String month =driver.findElement(By.xpath("//div[@class='datepicker-months']")).getText();
			String years =driver.findElement(By.xpath("//div[@class='datepicker-years']")).getText();
			System.out.println(month);
			System.out.println(month);
		//	System.out.println();
			if(years.equals("2023") && month.equals("Mar")) {
				driver.findElement(By.xpath("//td[@class='day'][contains(.,'23')]")).click();
				break;
			}
			else {
				driver.findElement(By.xpath("//th[@class='prev']")).click();
			}	
		}
		
		
		
		//-----------------------------------------------------------------------------------------
		// Try this option ****************************************
		/*
		  driver.findElement(By.id("IWLDatePickerFromAME")).click();
    	  String Expected_Month_Year="May 2023";
    	  
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
			 
	}
		return this;
	}}