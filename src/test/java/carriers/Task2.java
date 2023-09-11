
package carriers;

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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Task2 {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Task2");

	@Test
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");

		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("nilesh");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("Nilesh@123");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe

		WebElement ok = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();

		String expectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		String actualTitle = driver.getTitle();
		assert actualTitle.equalsIgnoreCase(expectedTitle) : "Title didn't match";
		System.out.println("Title Matched");
		Thread.sleep(5000);

	}

	@Test
	public void shipvia() throws InterruptedException {

		logger.info(" Opening Process Shipment Menu");
		Thread.sleep(5000);
		
		WebElement Transaction = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2")));																										// Transaction
		wait.until(ExpectedConditions.elementToBeClickable(Transaction));
		Transaction.click();
		logger.info(" Click on Transaction Menu Successful");
		Thread.sleep(5000);
		WebElement Process = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_21")));// To click
																											
		wait.until(ExpectedConditions.elementToBeClickable(Process));
		Process.click();
		logger.info(" Click on Process Shipment Menu Successful");
		// Select the Shipvia

		Thread.sleep(10000);
		WebElement shipviaSearch = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@onclick,'btnSearch_PS()')]"))); // Search
																														// the
		wait.until(ExpectedConditions.elementToBeClickable(shipviaSearch));
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");

		Thread.sleep(10000);
		String shipviacode = "FEXTest4";
	   WebElement sendcode= driver.findElement(By.id("txtSCSearchSS"));
	   wait.until(ExpectedConditions.visibilityOf(shipviaSearch));
	    sendcode.sendKeys(shipviacode);
		logger.info("shipviaSearch Enter Value Successful");
		

		WebElement ok = driver.findElement(By.id("btnSearchOk_PS"));
		wait.until(ExpectedConditions.visibilityOf(ok));
		ok.click();
		logger.info("Click on Ship Via ok search  Successful");

		Thread.sleep(5000);

		// -------------------------------------------------------
		WebElement customer = driver.findElement(By.xpath("//button[@onclick='AddressesClick()']"));
		customer.click();
		logger.info("Clicked on Customer");

		// To Customer Search Criteria
		Thread.sleep(5000);

		WebElement searchcustomer = driver.findElement(By.id("txtSCSearch")); // searchcustomer
		searchcustomer.sendKeys("CMS");

		WebElement List = driver.findElement(By.id("selCutomerList"));
		Select CustomerList = new Select(List);
		CustomerList.selectByValue("1"); // To select Global
		driver.findElement(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")).click(); // click on ok
		Thread.sleep(5000);
		logger.info("Customer Searched");

		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();
		WebElement Customerok = driver.findElement(By.id("addressformOk"));
		Customerok.click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");
//-----------------------------------------------
		WebElement buttonSpecialServices = driver.findElement(By.id("btnSpecialServices"));
		wait.until(ExpectedConditions.visibilityOf(buttonSpecialServices));
		wait.until(ExpectedConditions.elementToBeClickable(buttonSpecialServices));
		buttonSpecialServices.click();
		logger.info("Click on Special Services successful");

		//***
		WebElement NonStandardContainer = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX0")));
		NonStandardContainer.click();

//---------------------------------------------------------			
		Thread.sleep(10000);
		WebElement okbutton = driver.findElement(By.xpath("//*[@id='btnOk']"));
		okbutton.click();
		logger.info("Click on ok successful");

		// ------------------------------------------
		WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
		ManualWeight.sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		WebElement Rate = driver.findElement(By.id("cmdRate"));
		Rate.click();
		logger.info("Click on Rate ");

		Thread.sleep(5000);

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship

		// -----------------------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
		Thread.sleep(5000);
		WebElement List2 = driver.findElement(By.id("selCutomerList"));
		Select CustomerList2 = new Select(List2);

		CustomerList2.selectByValue("1"); // To select Global
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");

//------------------------------------------
		//***
		buttonSpecialServices.click();
		Thread.sleep(5000);
		WebElement AutoPOD = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX1")));
		AutoPOD.click();
		
//*******************************************************
		WebElement okbutton3 = driver.findElement(By.xpath("//*[@id='btnOk']"));
		okbutton3.click();
		logger.info("Click on ok successful");
		
		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		Thread.sleep(5000);

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship

		logger.info("AutoPOD Added");
		// -----------------------------------------------------------------------------------------
		// *******************************************************


		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(3000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
	
		WebElement Lista = driver.findElement(By.id("selCutomerList"));
		Select CustomerLista = new Select(Lista);

		CustomerLista.selectByValue("1"); // To select Global
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
	
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");

//------------------------------------------
		Thread.sleep(5000);
		driver.findElement(By.id("btnSpecialServices")).click();
		Thread.sleep(3000);
		
		WebElement HoldAtLocation = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX2")));
		HoldAtLocation.click();

		driver.findElement(By.xpath("//input[@onclick='ShowFedExWSHoldAtLocationAddress();']")).click(); // address
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnfedexholdAtLocationAddressDataModalOk']"))).click();
     // modify
		Thread.sleep(3000);
		driver.findElement(By.id("txtCompanyNameHoldFedex")).sendKeys("Test");

		driver.findElement(By.id("txtContactNameHoldFedex")).sendKeys("Test");
		driver.findElement(By.id("txtAddress1HoldFedex")).sendKeys("Testaddress");
		driver.findElement(By.id("txtCityHoldFedex")).sendKeys("lorton");
		driver.findElement(By.id("txtStateHoldFedex")).sendKeys("va");
		driver.findElement(By.id("txtZipHoldFedex")).sendKeys("22079");
		Thread.sleep(3000);
		
		WebElement country = driver.findElement(By.id("txtCountryFedexHold"));
		Select countrys = new Select(country);
		countrys.selectByVisibleText("UNITED STATES");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnfedexholdAtLocationAddressDataModalOk"))).click();
		
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship

//-------------------------------------------------------------------------------------------------------------------------------	
		// *******************************************************

		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
		Thread.sleep(5000);
		WebElement List4 = driver.findElement(By.id("selCutomerList"));
		Select CustomerList4 = new Select(List4);
		CustomerList4.selectByValue("1"); // To select Global

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");

//-----------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.id("btnSpecialServices")).click();

		WebElement Dropoff = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX4")));
		Dropoff.click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		Thread.sleep(5000);

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship

		logger.info("Dropoff done ");
		// ---------------------------------------------------------------------------------------

//---------------------

		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
		Thread.sleep(5000);
		WebElement List5 = driver.findElement(By.id("selCutomerList"));
		Select CustomerList5 = new Select(List5);
		CustomerList5.selectByValue("1"); // To select Global

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(5000);
		
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")))
				.click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");
		// ------------------------

	
		driver.findElement(By.id("btnSpecialServices")).click();
		Thread.sleep(6000);
		WebElement RecipientShipAlert = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX5")));
		RecipientShipAlert.click();

		driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		Thread.sleep(5000);

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship

		logger.info("RecipientShipAlert done ");
		// --------------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
		Thread.sleep(5000);
		WebElement List6 = driver.findElement(By.id("selCutomerList"));
		Select CustomerList6 = new Select(List6);
		CustomerList6.selectByValue("1"); // To select Global

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");
		// ------------------------

		
		driver.findElement(By.id("btnSpecialServices")).click();
		Thread.sleep(5000);
		WebElement HazardousMaterials = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX6")));
		HazardousMaterials.click();
		Thread.sleep(5000);
		WebElement selectmaterial = driver.findElement(By.id("SL6"));
		Select dropdown = new Select(selectmaterial);
		dropdown.selectByVisibleText("Hazardous Materials");

		Thread.sleep(10000);
		WebElement okbutton1 = driver.findElement(By.xpath("//*[@id='btnOk']"));
		okbutton1.click();
		logger.info("Click on ok successful");
		// To Add Details
		WebElement Details = driver.findElement(By.id("btnDetails"));
		Details.click(); // Details Button
		Thread.sleep(5000);
		WebElement SelectProduct = driver.findElement(By.id("btnSelectP"));
		SelectProduct.click(); // SelectProduct Button
		Thread.sleep(5000);
	
		WebElement Productname = driver.findElement(By.id("txtProductSearch"));
		Productname.sendKeys("Books");
		logger.info("Books input given ");

		driver.findElement(By.id("btnPOk")).click(); // Click on oK
		Thread.sleep(5000);
		WebElement Books = driver.findElement(By.xpath("//td[contains(text(),'Books')]"));
		Books.click();
		logger.info("Books Selected ");

		Thread.sleep(5000);
		driver.findElement(By.id("btnProductOk")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='btnPDOk']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='btnPdOk']")).click();
		Thread.sleep(3000);
		logger.info("Details added");

		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		Thread.sleep(5000);

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship

		logger.info("HazardousMaterials ");
//-----------------------------------------------------------------------------------------	
		// ***********************

		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer

		WebElement List7 = driver.findElement(By.id("selCutomerList"));
		Select CustomerList8 = new Select(List7);
		CustomerList8.selectByValue("1"); // To select Global

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");

//------------------------------------------------------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.id("btnSpecialServices")).click();

		WebElement FedExInternationaControlledExport = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("BOX7")));
		FedExInternationaControlledExport.click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		Thread.sleep(5000);

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship
		logger.info("FedExInternationaControlledExport Done  ");
//----------------------------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
		Thread.sleep(5000);
		WebElement Listaa = driver.findElement(By.id("selCutomerList"));
		Select CustomerListb = new Select(Listaa);
		CustomerListb.selectByValue("1"); // To select Global

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");

//------------------------------------------------------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.id("btnSpecialServices")).click();

		WebElement COD = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX8")));
		COD.click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		Thread.sleep(5000);

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship
		logger.info("COD Done  ");
//--------------------------------------------------------------------------------------------------------		

		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
		Thread.sleep(5000);
		WebElement Listc = driver.findElement(By.id("selCutomerList"));
		Select CustomerListd = new Select(Listc);
		CustomerListd.selectByValue("1"); // To select Global

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");

//------------------------------------------------------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.id("btnSpecialServices")).click();

		WebElement FedExShipAlert = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX9")));
		FedExShipAlert.click();
	
		
		driver.findElement(By.xpath("//*[@id='button3']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("chkShipperShipAlert"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnShipAlertOk"))).click();
		driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.id("btnEmailNotification")).click();
		
		WebElement email=driver.findElement(By.id("txtSenderEmail"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSenderEmail")));
		Thread.sleep(3000);
		email.sendKeys("cms@gmail.com");
//**
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnModalOk"))).click();
		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		Thread.sleep(5000);

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship
		logger.info("FedEx ShipAlert® Done  ");
		
//--------------------------------------------------------------------------------------------		
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
		Thread.sleep(5000);
		WebElement Liste = driver.findElement(By.id("selCutomerList"));
		Select CustomerListf = new Select(Liste);
		CustomerListf.selectByValue("1"); // To select Global

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");

//------------------------------------------------------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.id("btnSpecialServices")).click();

		WebElement Tireloss = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX11")));
		Tireloss.click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		Thread.sleep(5000);

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship
		logger.info("Tireloss Done  ");		
//----------------------------------------------------------------------------------		
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
		Thread.sleep(5000);
		WebElement Listj = driver.findElement(By.id("selCutomerList"));
		Select CustomerListh = new Select(Listj);
		CustomerListh.selectByValue("1"); // To select Global

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");

//------------------------------------------------------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.id("btnSpecialServices")).click();

		WebElement FedExDeliverySignatureOptions = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX12")));
		FedExDeliverySignatureOptions.click();
		

		WebElement selecta =driver.findElement(By.id("SL12"));
		Select select =new Select (selecta);
		select.selectByVisibleText("Signature Required");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		Thread.sleep(5000);

		driver.findElement(By.id("btnShipClick")).click(); // Click on ship
		logger.info("FedExDeliverySignatureOptions Done  ");		
//------------------------------------------------------------------------------------------------------		
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
		Thread.sleep(5000);
		WebElement Listcustomer = driver.findElement(By.id("selCutomerList"));
		Select CustomerListone = new Select(Listcustomer);
		CustomerListone.selectByValue("1"); // To select Global

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");

//------------------------------------------------------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.id("btnSpecialServices")).click();

		WebElement Oversize = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX13")));
		Oversize.click();
		WebElement Oversizeselect= driver.findElement(By.id("SL13"));
		Select os=new Select(Oversizeselect);
		os.selectByVisibleText("Oversized Handling");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		Thread.sleep(5000);

		
		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		
		driver.findElement(By.id("btnShipClick")).click(); // Click on ship
		logger.info("Oversize Done  ");		
//-------------------------------------------------------------------------------------------------------		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
		Thread.sleep(5000);
		WebElement Listz = driver.findElement(By.id("selCutomerList"));
		Select CustomerListx = new Select(Listz);
		CustomerListx.selectByValue("1"); // To select Global

		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
				.click();

		Thread.sleep(5000);
		logger.info("Customer Searched");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

		driver.findElement(By.id("addressformOk")).click(); // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");

//------------------------------------------------------------------------------------------------------------------------

		Thread.sleep(5000);
		driver.findElement(By.id("btnSpecialServices")).click();

		WebElement DeclaredValueThirdPartyInsurance = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX15")));
		DeclaredValueThirdPartyInsurance.click();
		Thread.sleep(5000);

		WebElement DV= driver.findElement(By.id("SL15"));
		Select Declared=new Select(DV);
		Declared.selectByVisibleText("Declared Value");		
		
		driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		
		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");

		driver.findElement(By.id("cmdRate")).click();

		logger.info("Click on Rate ");
		
		driver.findElement(By.id("btnShipClick")).click(); // Click on ship
		logger.info("DeclaredValueThirdPartyInsurance Done  ");		
		
	}
}
