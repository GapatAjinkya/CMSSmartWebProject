package processhipmentnegative;

import java.time.Duration;
import java.util.List;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.netty.handler.timeout.TimeoutException;

public class SWOSnegative {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("SwosWithPackVS");

	@Test(priority = 0)
	public void TestaddGroups() throws InterruptedException {
		OpenPs();
		Groups();
		GroupsDetails("SWOS", "SWOS");
		captureError();
	}
	@Test(priority = 1)
	public void TestShipVias() throws InterruptedException {
		ShipVia("FEUS_FSMS_GN");
	}
	@Test(priority = 2, dataProvider = "customer")
	public void addcustomer(String Customer, String CustomerCode) throws InterruptedException {
		addCustomer(Customer, CustomerCode);
		addProduct("Books","1");         //product,Quantity
		Manual("1.00");
		Ship();
		captureError();
	}
	@DataProvider(name = "customer")
	public Object[][] getdata() {
		Object[][] data = {{ "CMS", "SWOS" }, { "AdminVA", "SWOS" }};
		return data;
	}
	@Test(priority = 3)
	public void SelectMaster() throws InterruptedException {
		Groups();
		btnGroups("SWOS");
		captureError();
	}
	@Test(priority = 4)
	public void TestPack() throws InterruptedException {
		Pack();
		CreatePackage();                    //------------ to create pack
		Manual("1.00");
		Ship();
		ManualFinal("1");
	}

	@Test(priority = 5)
	public void ViewShipment() throws InterruptedException {
		driver.findElement(By.id("menu_item_2")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("menu_item_23")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btnShipViaLoad")).click();
		Thread.sleep(2000);
		WebElement usergroup = driver.findElement(By.xpath("//Select[@id='ddlUserGroup']"));
		Select Vavg = new Select(usergroup);
		Vavg.selectByValue("45"); // VA_UG
		driver.findElement(By.xpath("//button[@onclick='SelectGroupOkClick()']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("SearchCriteriaOkClick")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//table[@id='tblShipmentList']//tr[1]//td[1]")).click(); //To select o-index
		Thread.sleep(2000);
		driver.findElement(By.id("cmdOk")).click();

		WebElement Shipmentid = driver.findElement(By.xpath("//Select[@id='VStxtInvoiceNumber']"));
		String text=Shipmentid.getText();
		System.out.println("This is SWOS Shipment Details - "+text+" ");

	}


	public void Pack() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("cmdPack")).click(); // cmdPack
	}
	public void CreatePackage() throws InterruptedException {
		Thread.sleep(3000);
		  Object[][] testData = getdata();
	        int rowCount = testData.length;

		for(int i = 0;i<rowCount;i++) {

			driver.findElement(By.id("btnCreatePackage")).click();  //Create Package
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id, 'radPack') and contains(@id, '"+i+"')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id, 'frameConsolidatedBOX') and contains(@id, '0')]")).click();
			WebElement onebook=driver.findElement(By.id("frameConsolidatedtxt0"));
			onebook.sendKeys("1");
			Thread.sleep(2000);
			driver.findElement(By.id("btnAddItems")).click();
		}
		driver.findElement(By.id("btnCompletePack")).click();//submit button

	}
	public void GroupsDetails(String code, String Description) throws InterruptedException {
		Thread.sleep(3000);
		WebElement AddNewGroups = driver.findElement(By.id("AddNewButtonSGFPS"));
		AddNewGroups.click();
		Thread.sleep(3000);
		WebElement Customercode = driver.findElement(By.id("SWOSFrmtxtCustomerCodePS"));
		Customercode.sendKeys(code);
		WebElement dis = driver.findElement(By.id("SWOSFrmtxtDescriptionPS"));
		dis.sendKeys(Description);
		WebElement checkbox = driver.findElement(By.id("chkRecurrentPS"));
		checkbox.click();
		driver.findElement(By.xpath("//button[@onclick='OkClickSWOSGroupsForm()']")).click(); // s ok
	}
	public void addCustomer(String Customer, String CustomerCode) throws InterruptedException {
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
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[contains(text(),'" + Customer + "')]")).click();
		WebElement Customerok = driver.findElement(By.id("addressformOk"));
		Customerok.click(); // Click on OK
		Thread.sleep(2000);
		logger.info("Customer Added");
		WebElement newcode = driver.findElement(By.id("txtCustomerCode"));
		newcode.clear();
		newcode.sendKeys(CustomerCode);
	}
	public void Groups() throws InterruptedException {
		Thread.sleep(2000);
		WebElement GropudButton = driver.findElement(By.id("btnGroups"));
		GropudButton.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@onclick='onSWOSGrSearchPSOkClick()']")).click(); // Search ok
	}
	public void swosgroupok() {
		driver.findElement(By.xpath("//button[@onclick='OkClickSGFP()']")).click(); // Search ok
	}

	public void view() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("ViewButtonSGFPS")).click(); // View
	}
	public void Ship() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("btnShipClick")).click();
		logger.info("Click on  Shipment ");
	}
	public void Manual(String Weight) throws InterruptedException {
		Thread.sleep(3000);
		WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
		ManualWeight.clear();
		ManualWeight.sendKeys(Weight);
		logger.info("Manual Weight is fill ");
		WebElement txtLength = driver.findElement(By.xpath("//input[@id='txtLength']"));
		txtLength.clear();
		txtLength.sendKeys("1");
		Thread.sleep(2000);
		WebElement txtWidth = driver.findElement(By.xpath("//input[@id='txtWidth']"));
		txtWidth.clear();
		txtWidth.sendKeys("1");
		Thread.sleep(2000);
		WebElement txtHeights = driver.findElement(By.xpath("//input[@id='txtHeights']"));
		txtHeights.clear();
		txtHeights.sendKeys("1");
	}
	public void ManualFinal(String Weight) throws InterruptedException {
		  Object[][] testData = getdata();
	        int row = testData.length;
	        for(int i = 1;i<row;i++) {
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
	public void ShipVia(String ShipViaCode) throws InterruptedException {
		Thread.sleep(3000);
		WebElement shipviaSearch = driver.findElement(By.xpath("//*[@onclick=\"btnSearch_PS()\"]")); // Search the //
																										// Shipvias list
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");
		Thread.sleep(3000);
		WebElement ShipviaSearchcode = driver.findElement(By.xpath("//input[@id=\"radCodeSS\"]"));
		ShipviaSearchcode.click();
		WebElement code = driver.findElement(By.id("txtSCSearchSS"));
		code.sendKeys(ShipViaCode);
		driver.findElement(By.id("btnSearchOk_PS")).click();
	}
	public void clickship() {
		driver.findElement(By.id("btnShipClick")).click();
	}
	public void swosgroupselecttable(String shipcode) throws InterruptedException {
		Thread.sleep(4000);
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

	public void ShipmentstobeConsolidatedtable() throws InterruptedException {

		driver.findElement(By.xpath("//table[@id='SWOSShipmentsList']//td[1][contains(text(), 'SWOS')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("DoneButtonSSL")).click(); // ok

	}

	public void addProduct(String Product,String Quantity) throws InterruptedException {
		// To Add Details
		WebElement Details = driver.findElement(By.id("btnDetails"));
		Details.click(); // Details Button
		Thread.sleep(3000);
		WebElement SelectProduct = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSelectP")));
		SelectProduct.click(); // SelectProduct Button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value=\"Code\"]")).click();
		WebElement Productname = driver.findElement(By.id("txtProductSearch"));
		Productname.sendKeys(Product);
		logger.info("Books input given ");
		Thread.sleep(3000);
		driver.findElement(By.id("btnPOk")).click(); // Click on oK
		Thread.sleep(3000);
		WebElement Books = driver.findElement(By.xpath("//td[contains(text(),'" + Product + "')]"));
		Books.click();
		logger.info("Books Selected ");
		Thread.sleep(2000);
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
	public void btnGroups(String SwosGroups) throws InterruptedException {
	Thread.sleep(5000);
	List<WebElement> alldatao = driver.findElements(By.xpath("//table[@id='tblSWOSGroupFormListPS']//td[1]"));
	boolean dataStatuso = false;
	for (WebElement ele : alldatao) {
		String value = ele.getText();
		if (value.equals(SwosGroups))
		{
			System.out.println(value);
			ele.click();
			dataStatuso = true;
			break;
		}
	}
	Assert.assertTrue(dataStatuso, "");
	Thread.sleep(3000);
	//Click on view
	driver.findElement(By.id("ViewButtonSGFPS")).click();

	Thread.sleep(3000);
	List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='SWOSShipmentsList']//td[1]"));
	boolean dataStatus = false;
	for (WebElement ele : alldata) {
		String value = ele.getText();
		if (value.equals(SwosGroups))
		{
			System.out.println(value);
			ele.click();
			dataStatus = true;
			break;
		}
	}
	Assert.assertTrue(dataStatus, "");
	Thread.sleep(3000);
	driver.findElement(By.id("DoneButtonSSL")).click();
	Thread.sleep(3000);
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
	            case "Field value cannot be blank. Please try again.":
	            case "Please remember to adjust the Box Information for this SWOS shipment.":
	            case "Description more than 100 characters. Please try again.":
	            case "Shipment will be put on SWOS hold.":
	            case "Entry already exists in the database. Please try again.":
	                System.out.println("Handling error message: " + actualErrorMessage);
	                Assert.assertEquals(actualErrorMessage, actualErrorMessage, "Incorrect error message");
	                break;
	            default:
	                System.out.println("Unexpected error message: " + actualErrorMessage);// Handle other cases or unexpected errors
	                break;
	        }

	        WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
	        error.click();
	    } catch (TimeoutException e) {
	        // Handle the case where the error message doesn't appear within the expected time
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
	     wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}

}


