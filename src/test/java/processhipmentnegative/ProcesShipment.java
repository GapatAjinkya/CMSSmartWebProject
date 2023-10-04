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

public class ProcesShipment {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ProcesShipment");

	@Test(priority = 1)
	public void TestShipMent() throws InterruptedException {
		OpenPs();
		ShipVia("UPSGround");
		Rate();
		captureError();
		Ship();
		captureError();
	}
	@Test(priority = 2)
	public void TestCustomer() throws InterruptedException {
		ShipVia("UPSGround");
		addCustomer("USAG");
		Rate();
		captureError();
		Ship();
		captureError();
	}
	@Test(priority = 3)
	public void TestManual() throws InterruptedException {
		ShipVia("UPSGround");
		addCustomer("USAG");
		Manual("0.000");
		Rate();
		captureError();
		Ship();
		captureError();
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
		Thread.sleep(3000);
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
		driver.findElement(By.xpath("//td[contains(text(),'"+Customer+"')]")).click();
		WebElement Customerok= driver.findElement(By.id("addressformOk"));
		Customerok.click();          // Click on OK
		Thread.sleep(5000);
		logger.info("Customer Added");
	}
	public void ShipVia(String ShipViaCode) throws InterruptedException {
		Thread.sleep(4000);
		WebElement shipviaSearch = driver.findElement(By.xpath("//*[@onclick=\"btnSearch_PS()\"]")); // Search the																								// Shipvias list
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");
		Thread.sleep(5000);
		WebElement ShipviaSearchcode = driver.findElement(By.xpath("//input[@id=\"radCodeSS\"]"));
		ShipviaSearchcode.click();
		WebElement code=driver.findElement(By.id("txtSCSearchSS"));
		code.sendKeys(ShipViaCode);

		driver.findElement(By.id("btnSearchOk_PS")).click();

	}
	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		Thread.sleep(5000);
	}
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage.equals("No destination information has been specified!")) {
			System.out.println("Handling error message:-  " + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "No destination information has been specified!", "Incorrect error message");
		} else if (actualErrorMessage.equals("Error In Shipment Data: Package #1: Weight must be greater than zero")) {
			System.out.println("Handling error message.:- " + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "Error In Shipment Data: Package #1: Weight must be greater than zero", "Incorrect error message");
		}
		 else if (actualErrorMessage.equals("Error In Shipment Data: Package #1: Weight must be greater than zero")) {
				System.out.println("Handling error message:-  " + actualErrorMessage);
				Assert.assertEquals(actualErrorMessage, "Error In Shipment Data: Package #1: Weight must be greater than zero", "Incorrect error message");
			}
		else {
			System.out.println("Unexpected error message: " + actualErrorMessage);
		}

		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();

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