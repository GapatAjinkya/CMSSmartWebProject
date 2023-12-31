package com.cms.ProcessShipment;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InternationalShipmentTest {

	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void main(String[] args) throws InterruptedException {

		Logger logger = LogManager.getLogger("Internationalshipment");// object created for logger class and class name is passed


			System.setProperty("webdriver.chrome.driver",
					"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			// options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
			// options.addArguments("--remote-allow-origins=*");

			driver = new ChromeDriver(options);
			logger.info("Browser opend");
			driver.manage().window().maximize();
			driver.get("http://localhost:8090/SmartWeb/#");
			Thread.sleep(3000);
			driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
			driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
			Thread.sleep(3000);
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
			Userlogin.sendKeys("admin");
			WebElement password = driver.findElement(By.id("txtLPPassword")); // password
			password.sendKeys("password");
			driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
			WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
			ok.click();


		logger.info("Login successful");

		Thread.sleep(5000);
		WebElement Transaction= driver.findElement(By.id("menu_item_2")); 	// To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		// Select the Shipvia
		Thread.sleep(5000);
		WebElement shipviaSearch = driver.findElement(By.xpath("//*[@onclick=\"btnSearch_PS()\"]")); // Search the																								// Shipvias list
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");

		Thread.sleep(5000);
		WebElement ShipviaSearchcode = driver.findElement(By.xpath("//input[@id=\"radCodeSS\"]"));
		ShipviaSearchcode.click();
		driver.findElement(By.id("txtSCSearchSS")).sendKeys("FEUS_WS_ID");
		driver.findElement(By.id("btnSearchOk_PS")).click();
		logger.info("shipvia is selected");

		Thread.sleep(5000);

		WebElement customer = driver.findElement(By.xpath("//button[@onclick='AddressesClick()']"));
		customer.click();
		logger.info("Clicked on Customer");

		// To Customer Search Criteria
		Thread.sleep(5000);
		WebElement customercode = driver.findElement(By.id("radCode")); // customercode
		customercode.click();
		WebElement searchcustomer = driver.findElement(By.id("txtSCSearch")); // searchcustomer
		searchcustomer.sendKeys("cms");
		WebElement List = driver.findElement(By.id("selCutomerList"));
		Select CustomerList = new Select(List);
		CustomerList.selectByValue("1"); // To select Global
		driver.findElement(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")).click(); // click on ok
		Thread.sleep(5000);
		logger.info("Customer Searched");

		Thread.sleep(5000);

		driver.findElement(By.xpath("//*[@id='tblCustomerList']/tbody/tr/td[1]")).click();
		WebElement Customerok= driver.findElement(By.id("addressformOk"));
		Customerok.click();          // Click on OK
		Thread.sleep(3000);
		logger.info("Customer Added");

        // To Add Details
		WebElement Details= driver.findElement(By.id("btnDetails"));
		Details.click();       // Details Button
		Thread.sleep(3000);
		WebElement SelectProduct=driver.findElement(By.id("btnSelectP"));
		SelectProduct.click();       // SelectProduct Button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value=\"Code\"]")).click();
		WebElement Productname= driver.findElement(By.id("txtProductSearch"));
		Productname.sendKeys("Books");
		logger.info("Books input given ");

		driver.findElement(By.id("btnPOk")).click();   // Click on oK
		Thread.sleep(3000);
	    WebElement Books= driver.findElement(By.xpath("//td[contains(text(),'Books')]"));
	    Books.click();
		logger.info("Books Selected ");


		Thread.sleep(3000);
	    driver.findElement(By.id("btnProductOk")).click();
		Thread.sleep(3000);
	    driver.findElement(By.xpath("//*[@id=\"btnPDOk\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"btnPdOk\"]")).click();
		Thread.sleep(3000);
		logger.info("Details added");

		// To click on International Button
		driver.findElement(By.id("btniternationalData")).click();
		Thread.sleep(3000);

		// To select FTR Exemption

		WebElement  FTRExemption =driver.findElement(By.id("cmbFTRExemption"));
		Select dropdown= new Select(FTRExemption);
		dropdown.selectByIndex(4);
		logger.info("FTRE added");

		Thread.sleep(3000);
		// TO addTerms of sale
		WebElement Termsofsale=driver.findElement(By.id("cmbIDTermOfSale"));
		Select sale= new Select(Termsofsale);
		sale.selectByVisibleText("DDP - Delivery Duty Paid");
		logger.info("Termsofsale added");

		Thread.sleep(3000);
//
//		// TO add FedEx IOR Codes
//		driver.findElement(By.xpath("//button[@onclick='OnClickfedExIORCode()']")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//tr[@data-index=\"110\"]")).click();
//		driver.findElement(By.id("btnFedExIOROKClick")).click();
//		logger.info("FedEx IOR Codes added");

		Thread.sleep(3000);
		WebElement packagdropdown=driver.findElement(By.id("cmbAdmissibilityPackageType"));
		Select Package= new Select(packagdropdown);
		Package.selectByVisibleText("BOX -- Box");
		Thread.sleep(3000);
		logger.info("FedEx IOR Codes added");


		driver.findElement(By.id("btnInternationalOk")).click();
		Thread.sleep(3000);

		WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
		ManualWeight.sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("btnShipClick")).click();
		logger.info("International Shipment is Done");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "30");
		Thread.sleep(4000);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = "F:\\CMS-WorldLink\\ScreenShot\\International.png";
		  try {
	            // Save the screenshot to the specified path
	            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
	            System.out.println("Screenshot saved successfully at: " + screenshotPath);
	        } catch (IOException e) {
	            System.out.println("Failed to save the screenshot: " + e.getMessage());
	        }
	}
}
