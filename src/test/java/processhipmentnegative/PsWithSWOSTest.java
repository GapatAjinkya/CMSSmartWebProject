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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.netty.handler.timeout.TimeoutException;

public class PsWithSWOSTest {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("PsWithSWOSTest");

	@Test(priority = 0)
	public void testSwos() throws InterruptedException {
		ProcesShipment();
		ShipVia("FEX_Test1_GN1");
	}

	@Test(priority = 1,dataProvider = "customer")
	public void addcustomer(String Customer, String CustomerCode) throws InterruptedException {
		addCustomer(Customer, CustomerCode);
		Manual("1.00");
		Ship();
		captureError();
	}
	
	@Test(priority = 2)
	public void testGroupsswos() throws InterruptedException {
		btnGroups("AJ1");
		captureError();
		Manual("5.00");
		Ship();
	}
	@DataProvider(name = "customer")
	public Object[][] getdata() {

	//	Object[][] data = { { "CMS", "AG" }, { "AdminVA", "AG" }, { "USAG2", "AG" }, };
		Object[][] data = { { "CMS", "AJ1" }, { "AdminVA", "AJ1" } };
		return data;
	}

	public void ProcesShipment() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		Thread.sleep(5000);

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

	public void addCustomer(String Customer, String CustomerCode) throws InterruptedException {
		Thread.sleep(3000);
		WebElement customer = driver.findElement(By.xpath("//button[@onclick='AddressesClick()']"));
		customer.click();
		logger.info("Clicked on Customer");
		// To Customer Search Criteria
		Thread.sleep(3000);
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
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[contains(text(),'" + Customer + "')]")).click();
		WebElement Customerok = driver.findElement(By.id("addressformOk"));
		Customerok.click(); // Click on OK
		Thread.sleep(4000);
		logger.info("Customer Added");

		WebElement newcode = driver.findElement(By.id("txtCustomerCode"));
		newcode.clear();
		newcode.sendKeys(CustomerCode);
	}

	public void customercodechange(String CustomerCode) {
		WebElement newcode = driver.findElement(By.id("txtCustomerCode"));
		newcode.sendKeys(CustomerCode);
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
}
	public void btnGroups(String SwosGroups) throws InterruptedException {
    	Thread.sleep(5000);
	WebElement btnGroups = driver.findElement(By.id("btnGroups"));
	btnGroups.click();
	Thread.sleep(5000);
	driver.findElement(By.id("txtCSTSWOSGrSearchPS")).sendKeys(SwosGroups);
	driver.findElement(By.xpath("//button[@onclick='onSWOSGrSearchPSOkClick()']")).click();
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
			case "Shipment will be put on SWOS hold.":
				Assert.assertEquals(actualErrorMessage, "Shipment will be put on SWOS hold.",
						"Incorrect error message");
				break;
			case "Please remember to adjust the Box Information for this SWOS shipment.":
				Assert.assertEquals(actualErrorMessage, "Please remember to adjust the Box Information for this SWOS shipment.",
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
