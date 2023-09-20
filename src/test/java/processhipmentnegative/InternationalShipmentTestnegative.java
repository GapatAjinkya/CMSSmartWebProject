package processhipmentnegative;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InternationalShipmentTestnegative {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("InternationalShipmentTestnegative");

	@Test(priority = 0)
	public void Fexdexshipment() throws InterruptedException {
		OpenPs();
		ShipVia("FEUS_WS_ID");
		addCustomer("AdminCA");
		addProduct("Books");
		Manual("1.000");
		Rate();
		captureError();
	}

	@Test(priority = 1)
	public void XTNNumber() throws InterruptedException {
		addInternational();
		Po("123");
		Rate();
		Ship();
	}

	public void addInternational() throws InterruptedException {
		driver.findElement(By.id("btniternationalData")).click();
		Thread.sleep(9000);
		// To select FTR Exemption
		WebElement FTRExemption = driver.findElement(By.id("cmbFTRExemption"));
		Select dropdown = new Select(FTRExemption);
		dropdown.selectByIndex(4);
		logger.info("FTRE added");
		Thread.sleep(3000);
		// TO addTerms of sale
		WebElement Termsofsale = driver.findElement(By.id("cmbIDTermOfSale"));
		Select sale = new Select(Termsofsale);
		sale.selectByVisibleText("DDP - Delivery Duty Paid");
		logger.info("Termsofsale added");
		Thread.sleep(3000);
		driver.findElement(By.id("btnInternationalOk")).click();
		Thread.sleep(9000);
	}

	public void addProduct(String Product) throws InterruptedException {
		// To Add Details
		WebElement Details = driver.findElement(By.id("btnDetails"));
		Details.click(); // Details Button
		Thread.sleep(5000);
		WebElement SelectProduct = driver.findElement(By.id("btnSelectP"));
		SelectProduct.click(); // SelectProduct Button
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@value=\"Code\"]")).click();
		WebElement Productname = driver.findElement(By.id("txtProductSearch"));
		Productname.sendKeys(Product);
		logger.info("Books input given ");

		driver.findElement(By.id("btnPOk")).click(); // Click on oK
		Thread.sleep(10000);
		WebElement Books = driver.findElement(By.xpath("//td[contains(text(),'" + Product + "')]"));
		Books.click();
		logger.info("Books Selected ");
		Thread.sleep(10000);
		driver.findElement(By.id("btnProductOk")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id=\"btnPDOk\"]")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id=\"btnPdOk\"]")).click();
		Thread.sleep(10000);
		logger.info("Details added");
	}

	public void ShipVia(String ShipViaCode) throws InterruptedException {
		Thread.sleep(3000);
		WebElement shipviaSearch = driver.findElement(By.xpath("//*[@onclick=\"btnSearch_PS()\"]")); // Search the //
																										// Shipvias list
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");
		Thread.sleep(5000);
		WebElement ShipviaSearchcode = driver.findElement(By.xpath("//input[@id=\"radCodeSS\"]"));
		ShipviaSearchcode.click();
		WebElement code = driver.findElement(By.id("txtSCSearchSS"));
		code.sendKeys(ShipViaCode);
		driver.findElement(By.id("btnSearchOk_PS")).click();
		logger.info("shipvia is selected");
	}

	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage.equals("No destination information has been specified!")) {
			System.out.println("Handling error message:-  " + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "No destination information has been specified!",
					"Incorrect error message");
		} else if (actualErrorMessage.equals("Error In Shipment Data: Package #1: Weight must be greater than zero")) {
			System.out.println("Handling error message.:- " + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,
					"Error In Shipment Data: Package #1: Weight must be greater than zero", "Incorrect error message");
		} else if (actualErrorMessage.equals(
				"IOR Code is required when processing FedEx IPD/IED/IGD/IPF shipments! Please click on the International button and enter the IOR Code.")) {
			System.out.println("Handling error message:-  " + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,
					"IOR Code is required when processing FedEx IPD/IED/IGD/IPF shipments! Please click on the International button and enter the IOR Code.",
					"Incorrect error message");
		} else if (actualErrorMessage
				.equals("XTN Suffix Number and/or FTSR Exemption Number is required when Shipper is filing AES.")) {
			System.out.println("Handling error message:-  " + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,
					"XTN Suffix Number and/or FTSR Exemption Number is required when Shipper is filing AES.",
					"Incorrect error message");
		} else if (actualErrorMessage.equals("An Active Manifest already exists in the system. Cannot create new manifest.")) {
			System.out.println("Handling error message.:- " + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,
					"An Active Manifest already exists in the system. Cannot create new manifest.", "Incorrect error message");
		} else {
			System.out.println("Unexpected error message: " + actualErrorMessage);
		}
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
	}

	public void Po(String ponumber) {

		WebElement po = driver.findElement(By.id("txtPO"));
		po.sendKeys(ponumber);
	}

	public void Ship() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("btnShipClick")).click();
		logger.info("Click on  Shipment ");
	}

	public void Rate() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("cmdRate")).click();
		logger.info("Click on  Shipment ");
	}

	public void Manual(String Weight) throws InterruptedException {
		Thread.sleep(3000);
		WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
		ManualWeight.sendKeys(Weight);
		logger.info("Manual Weight is fill ");
	}

	public void addCustomer(String Customer) throws InterruptedException {
		Thread.sleep(5000);
		WebElement customer = driver.findElement(By.xpath("//button[@onclick='AddressesClick()']"));
		customer.click();
		logger.info("Clicked on Customer");
		// To Customer Search Criteria
		Thread.sleep(5000);
		WebElement customercode = driver.findElement(By.id("radCode")); // customercode
		customercode.click();
		WebElement searchcustomer = driver.findElement(By.id("txtSCSearch")); // searchcustomer
		searchcustomer.sendKeys(Customer);
		WebElement List = driver.findElement(By.id("selCutomerList"));
		Select CustomerList = new Select(List);
		CustomerList.selectByValue("1"); // To select Global

		driver.findElement(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")).click(); // click on ok
		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//td[contains(text(),'" + Customer + "')]")).click();
		WebElement Customerok = driver.findElement(By.id("addressformOk"));
		Customerok.click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");
	}

	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		Thread.sleep(5000);
	}

	@BeforeClass
	public void setup() throws InterruptedException {

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
	}
}
