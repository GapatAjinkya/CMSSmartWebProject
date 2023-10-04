package processhipmentnegative;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.netty.handler.timeout.TimeoutException;

public class SwosWithPackVsScript {
	public static WebDriver driver;
	public static WebDriverWait wait;
	private String textValue = null;
	Logger logger = LogManager.getLogger("SwosWithPackVsScript");

	public static String Custcode = "SWOS";
	public static String ShipVia = "FSMS(MPS:YES)_GN";
	
	@Test(priority = 0)
	public void TestaddGroups() throws InterruptedException {
		OpenPs();
		CheckGroups();
	}

	@Test(priority = 1)
	public void shipviatest() throws InterruptedException {
		selecshipvia();
	}

	@Test(priority = 2, dataProvider = "customer")
	public void addcustomer(String Customer) throws InterruptedException {
		addCustomer(Customer);
		addProduct("Books", "1"); // product,Quantity
		Manual("1.00");
		Ship();
		captureError();
	}

	@DataProvider(name = "customer")
	public Object[][] getdata() {
		Object[][] data = { { "CMS" }, { "US" } };
		return data;
	}

	@Test(priority = 3)
	public void SelectMaster() throws InterruptedException {
		SelectMasterShip();
		captureError();
	}

	@Test(priority = 4)
	public void TestPack() throws InterruptedException {
		Pack();
		CreatePackage(); // ------------ to create pack
		Manual("1.00");
		Ship();
		ManualFinal("1");
	}

	@Test(priority = 5)
	public void TestCaptureShipmentid() {
		Shipmentid();
	}
	
	@Test(priority = 6,dependsOnMethods = "TestaddGroups")
	public void ViewShipment() throws InterruptedException {
		driver.findElement(By.id("menu_item_2")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("menu_item_23")).click();
		Thread.sleep(2000);

		EnterShipmentIdVS();
		driver.findElement(By.id("SearchCriteriaOkClick")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//table[@id='tblShipmentList']//tr[1]//td[1]")).click(); // To select o-index
		Thread.sleep(2000);
		driver.findElement(By.id("cmdOk")).click();

		WebElement Shipmentid = driver.findElement(By.xpath("//Select[@id='VStxtInvoiceNumber']"));
		String text = Shipmentid.getText();
		System.out.println("This is SWOS Shipment Details - " + text + " ");

	}

	public void CheckGroups() {
		try {
			Thread.sleep(2000);
			WebElement GropudButton = driver.findElement(By.id("btnGroups"));
			GropudButton.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@onclick='onSWOSGrSearchPSOkClick()']")).click(); // Search ok
			Thread.sleep(3000);
			// Locate the Code table and find all rows
			List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblSWOSGroupFormListPS']//td"));
			boolean codeFound = false;
			for (WebElement ele : alldata) {
				String value = ele.getText();
				if (value.contains(Custcode)) {
					codeFound = true;
					ele.click();// Select that group
					WebElement undoPackButton = driver.findElement(By.id("UndoButtonSGFPS"));
					if (undoPackButton.isEnabled()) {
						undoPackButton.click();
						Thread.sleep(2000);
						driver.findElement(By.id("btnConfirmBoxOk")).click();
						Thread.sleep(2000);
						
						driver.findElement(By.id("ViewButtonSGFPS")).click();// click on view
						Thread.sleep(4000);
						driver.findElement(By.xpath("//*[@id='SWOSShipmentsList']/tbody//tr[1]")).click();// Select
																											// Master
						Thread.sleep(2000);
						driver.findElement(By.id("DoneButtonSSL")).click();
						Thread.sleep(2000);
						driver.findElement(By.id("btnOkButtonSGFPS")).click();
						Thread.sleep(2000);
						
						WebElement popup = driver.findElement(By.id("btnErrorBoxOk"));
						if (popup.isDisplayed()) {
							popup.click();
							CreatePackageCheck();
						}		
					} else {
						// If the "undo pack" button is disabled, click the "OK" button directly
						WebElement okButton = driver.findElement(By.id("btnOkButtonSGFPS"));
						okButton.click();
						Thread.sleep(3000);
						WebElement popup = driver.findElement(By.id("btnErrorBoxOk"));
						if (popup.isDisplayed()) {
							popup.click();
							CreatePackageCheck();
						}
					}
					Thread.sleep(2000);
					// Click the "OK" button
					WebElement ok = driver.findElement(By.id("btnOkButtonSGFPS"));
					 ok.click();
					break;
				}
			}
			
			if (!codeFound) {
				// If the code is not found, click the "Add" button
				WebElement addButton = driver.findElement(By.id("AddNewButtonSGFPS"));
				addButton.click();
				Thread.sleep(3000);
				WebElement Customercode = driver.findElement(By.id("SWOSFrmtxtCustomerCodePS"));
				Customercode.sendKeys(Custcode);
				WebElement dis = driver.findElement(By.id("SWOSFrmtxtDescriptionPS"));
				dis.sendKeys("Test");
				WebElement checkbox = driver.findElement(By.id("chkRecurrentPS"));
				checkbox.click();
				WebElement okButton = driver.findElement(By.xpath("//button[@onclick='OkClickSWOSGroupsForm()']"));
				okButton.click(); // Click "Ok" to create the new group
				Thread.sleep(2000);
				WebElement ok = driver.findElement(By.id("btnOkButtonSGFPS"));
				 ok.click();			 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Shipmentid() {
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='shipmentHistoryTable']//td[2]"));
		for (WebElement ele : alldata) {
			textValue = ele.getText();
		}
	}

	public void EnterShipmentIdVS() {
		Shipmentid();
		WebElement shipid = driver.findElement(By.id("txtInvoiceFrom"));
		shipid.sendKeys(textValue);

	}

	public void selecshipvia() throws InterruptedException {
		Thread.sleep(3000);
		WebElement shipviaSearch = driver.findElement(By.xpath("//*[@id=\"card4\"]/div[2]/form/div[1]/div[3]/span")); // Shipvias
																														// list
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");
		Thread.sleep(3000);
		WebElement code = driver.findElement(By.id("txtSCSearchSS"));
		code.sendKeys(ShipVia);
		driver.findElement(By.id("btnSearchOk_PS")).click();
	}

	public void Pack() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("cmdPack")).click(); // cmdPack
	}

	public void CreatePackageCheck() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.id("cmdPack")).click(); // cmdPack
		Thread.sleep(2000);
		List<WebElement> checkboxes = driver
				.findElements(By.xpath("//input[contains(@id, 'frameConsolidatedBOX') and contains(@id, '')]"));// Check
																												// Box																										// ////table[@id='frameConsolidated']//input[@type='checkbox']
		int checkboxCount = checkboxes.size();
		System.out.println("Number of Package in the table: " + checkboxCount);
		for (int i = 0; i <checkboxCount; i++) {
			Thread.sleep(2000);

			driver.findElement(By.id("btnCreatePackage")).click(); // Create Package
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id, 'radPack') and contains(@id, '" + i + "')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id, 'frameConsolidatedBOX') and contains(@id, '0')]"))
					.click();
			Thread.sleep(2000);
			WebElement quantityElement = driver.findElement(By.xpath("//table[@id='frameConsolidated']//tr//td[5]"));
			String quantityText = quantityElement.getText();
			int quantity = Integer.parseInt(quantityText);
			WebElement inputBox = driver.findElement(By.id("frameConsolidatedtxt0"));
			// Enter the quantity into the input box
			inputBox.clear();
			inputBox.sendKeys(String.valueOf(quantity));
			Thread.sleep(2000);
			driver.findElement(By.id("btnAddItems")).click();
		}
		Thread.sleep(2000);
		driver.findElement(By.id("btnCompletePack")).click();// submit button 	
		//This is for Ship
		for (int i = 0; i < checkboxCount; i++) {
			Thread.sleep(3000);
			WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
			ManualWeight.clear();
			ManualWeight.sendKeys("1");
			logger.info("Manual Weight is fill ");
			WebElement txtLength = driver.findElement(By.xpath("//input[@id='txtLength']"));
			txtLength.clear();
			txtLength.sendKeys("1");
			WebElement txtWidth = driver.findElement(By.xpath("//input[@id='txtWidth']"));
			txtWidth.clear();
			txtWidth.sendKeys("1");
			WebElement txtHeights = driver.findElement(By.xpath("//input[@id='txtHeights']"));
			txtHeights.clear();
			txtHeights.sendKeys("1");
			Thread.sleep(3000);		
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnShipClick")));
			element.click();
			logger.info("Click on  Shipment ");
		}	
		Assert.fail("");
	}

	public void CreatePackage() throws InterruptedException {
		Thread.sleep(3000);
		Object[][] testData = getdata();
		int rowCount = testData.length;
		for (int i = 0; i < rowCount; i++) {

			driver.findElement(By.id("btnCreatePackage")).click(); // Create Package
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id, 'radPack') and contains(@id, '" + i + "')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id, 'frameConsolidatedBOX') and contains(@id, '0')]"))
					.click();
			WebElement onebook = driver.findElement(By.id("frameConsolidatedtxt0"));
			onebook.sendKeys("1");
			Thread.sleep(2000);
			driver.findElement(By.id("btnAddItems")).click();
		}
		Thread.sleep(2000);
		driver.findElement(By.id("btnCompletePack")).click();// submit button
		
		
	}

	public void addCustomer(String Customer) throws InterruptedException {

		Thread.sleep(3000);
		WebElement customer = driver.findElement(By.xpath("//button[@onclick='AddressesClick()']"));
		customer.click();
		logger.info("Clicked on Customer");
		// To Customer Search Criteria
		Thread.sleep(2000);
		WebElement customercode = driver.findElement(By.id("radCode")); // customercode
		customercode.click();
		WebElement searchcustomer = driver.findElement(By.id("txtSCSearch")); // searchcustomer
		searchcustomer.sendKeys(Customer);
		WebElement List = driver.findElement(By.id("selCutomerList"));
		Select CustomerList = new Select(List);
		CustomerList.selectByValue("1"); // To select Global
		driver.findElement(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")).click(); // click on ok
		Thread.sleep(2000);
		logger.info("Customer Searched");
		driver.findElement(By.xpath("//td[contains(text(),'" + Customer + "')]")).click();
		WebElement Customerok = driver.findElement(By.id("addressformOk"));
		Customerok.click(); // Click on OK
		Thread.sleep(2000);
		logger.info("Customer Added");
		WebElement newcode = driver.findElement(By.id("txtCustomerCode"));
		newcode.clear();
		newcode.sendKeys(Custcode);

	}

	public void swosgroupok() {
		driver.findElement(By.xpath("//button[@onclick='OkClickSGFP()']")).click(); // Search ok
	}

	public void view() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("ViewButtonSGFPS")).click(); // View
	}

	public void Ship() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("btnShipClick")).click();
		logger.info("Click on  Shipment ");
	}

	public void Manual(String Weight) throws InterruptedException {
		Thread.sleep(2000);
		WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
		ManualWeight.clear();
		ManualWeight.sendKeys(Weight);
		logger.info("Manual Weight is fill ");
		WebElement txtLength = driver.findElement(By.xpath("//input[@id='txtLength']"));
		txtLength.clear();
		txtLength.sendKeys("1");

		WebElement txtWidth = driver.findElement(By.xpath("//input[@id='txtWidth']"));
		txtWidth.clear();
		txtWidth.sendKeys("1");

		WebElement txtHeights = driver.findElement(By.xpath("//input[@id='txtHeights']"));
		txtHeights.clear();
		txtHeights.sendKeys("1");
	}

	public void ManualFinal(String Weight) throws InterruptedException {
		Object[][] testData = getdata();
		int row = testData.length;
		for (int i = 1; i < row; i++) {
			Thread.sleep(3000);
			WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
			ManualWeight.clear();
			ManualWeight.sendKeys(Weight);
			logger.info("Manual Weight is fill ");
			WebElement txtLength = driver.findElement(By.xpath("//input[@id='txtLength']"));
			txtLength.clear();
			txtLength.sendKeys("1");
			WebElement txtWidth = driver.findElement(By.xpath("//input[@id='txtWidth']"));
			txtWidth.clear();
			txtWidth.sendKeys("1");
			WebElement txtHeights = driver.findElement(By.xpath("//input[@id='txtHeights']"));
			txtHeights.clear();
			txtHeights.sendKeys("1");
			Thread.sleep(2000);
			driver.findElement(By.id("btnShipClick")).click();
			logger.info("Click on  Shipment ");
		}

	}

	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		Thread.sleep(3000);
	}

	public void clickship() {
		driver.findElement(By.id("btnShipClick")).click();
	}

	public void swosgroupselecttable(String shipcode) throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblSWOSGroupFormListPS']//td[1]"));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(shipcode)) {
				System.out.println(value);
				dataStatus = true;
				ele.click(); // Click on the element if found
				break;
			}
		}
		logger.info("Customer Code Found Selected ");
	}
	public void addProduct(String Product, String Quantity) throws InterruptedException {
		// To Add Details
		WebElement Details = driver.findElement(By.id("btnDetails"));
		Details.click(); // Details Button
		Thread.sleep(2000);
		WebElement SelectProduct = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSelectP")));
		SelectProduct.click(); // SelectProduct Button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value=\"Code\"]")).click();
		WebElement Productname = driver.findElement(By.id("txtProductSearch"));
		Productname.sendKeys(Product);
		logger.info("Books input given ");
		driver.findElement(By.id("btnPOk")).click(); // Click on oK
		Thread.sleep(2000);
		WebElement Books = driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr/td[1]"));
		Books.click();
		logger.info("Books Selected ");
		driver.findElement(By.id("btnProductOk")).click();
		Thread.sleep(2000);
		WebElement QuantityEnter = driver.findElement(By.id("txtQuantity"));
		QuantityEnter.clear();
		QuantityEnter.sendKeys(Quantity);
		driver.findElement(By.xpath("//*[@id=\"btnPDOk\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"btnPdOk\"]")).click();

		logger.info("Details added");
	}

	public void SelectMasterShip() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Groups = driver.findElement(By.id("btnGroups"));
		Groups.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@onclick='onSWOSGrSearchPSOkClick()']")).click(); // Search ok
		Thread.sleep(5000);
		List<WebElement> alldatao = driver.findElements(By.xpath("//table[@id='tblSWOSGroupFormListPS']//td[1]"));
		boolean dataStatuso = false;
		for (WebElement ele : alldatao) {
			String value = ele.getText();
			if (value.equals(Custcode)) {
				System.out.println(value);
				ele.click();
				dataStatuso = true;
				break;
			}
		}
		Assert.assertTrue(dataStatuso, "");
		Thread.sleep(2000);
		// Click on view
		driver.findElement(By.id("ViewButtonSGFPS")).click();

		Thread.sleep(2000);
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='SWOSShipmentsList']//td[1]"));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(Custcode)) {
				System.out.println(value);
				ele.click();
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "");
		Thread.sleep(2000);
		driver.findElement(By.id("DoneButtonSSL")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btnOkButtonSGFPS")).click();
	}

	public void captureError() throws InterruptedException {
		Thread.sleep(3000);
		try {
			WebElement errorMessage = driver.findElement(By.xpath("//span[@id='errorMsg']"));
			Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
			String actualErrorMessage = errorMessage.getText();
			switch (actualErrorMessage) {
			case "No records found!":
				Assert.assertEquals(actualErrorMessage, "No records found!", "Incorrect error message");
				break;
			case "Field value cannot be blank. Please try again.":
				Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.",
						"Incorrect error message");
				break;
			case "Please remember to adjust the Box Information for this SWOS shipment.":
				Assert.assertEquals(actualErrorMessage,
						"Please remember to adjust the Box Information for this SWOS shipment.",
						"Incorrect error message");
				break;
			case "Description more than 100 characters. Please try again.":
				Assert.assertEquals(actualErrorMessage, "Description more than 100 characters. Please try again.",
						"Incorrect error message");
				break;
			case "Shipment will be put on SWOS hold.":
				Assert.assertEquals(actualErrorMessage, "Shipment will be put on SWOS hold.",
						"Incorrect error message");
				break;
			default:
				// Handle other cases or unexpected errors
				System.out.println("Unexpected error message: " + actualErrorMessage);
				break;
			}
			WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
			error.click();
		} catch (TimeoutException e) {
			// Handle the case where the error message doesn't appear within the expected
			// time
			System.out.println("Error message not found within expected time.");
		}
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}
}
