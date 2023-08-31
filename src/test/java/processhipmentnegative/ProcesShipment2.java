package processhipmentnegative;

import java.util.concurrent.TimeUnit;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProcesShipment2 {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ProcesShipment");

	  @DataProvider(name = "shipmentData")
	    public Object[][] shipmentData() {
	        return new Object[][] {
	            {"UPSGround", "USAG", "0.00"},
	            {"UPSGround","","1.00"},
	            {"UPSGround","USAG","1.00"}  
	        };
	    }

	    @Test(priority = 1, dataProvider = "shipmentData")
	    public void testShipment(String shipViaCode,  String customer,String manualWeight) throws InterruptedException {
	        OpenPs();
	        ShipVia(shipViaCode);
	        addCustomer(customer);
	        Manual(manualWeight);
	        Ship();
	        captureError();
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
	public void Ship() {
		driver.findElement(By.id("btnShipClick")).click();
		logger.info("Click on  Shipment ");
	}
	public void Rate() {
		driver.findElement(By.id("cmdRate")).click();
		logger.info("Click on  Shipment ");
	}
    public void Manual(String Weight) {
	WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
	ManualWeight.sendKeys(Weight);
	logger.info("Manual Weight is fill ");
}
	public void addCustomer(String Customer) throws InterruptedException {
		
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
		WebElement shipviaSearch = driver.findElement(By.xpath("//*[@onclick=\"btnSearch_PS()\"]")); // Search the																								// Shipvias list
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");
		Thread.sleep(5000);
		WebElement ShipviaSearchcode = driver.findElement(By.xpath("//input[@id=\"radCodeSS\"]"));
		ShipviaSearchcode.click();
		WebElement code=driver.findElement(By.id("txtSCSearchSS"));
		code.sendKeys(ShipViaCode);
		driver.findElement(By.id("btnSearchOk_PS")).click();
		logger.info("shipvia is selected");
	}
	public void OpenPs() throws InterruptedException {
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		Thread.sleep(5000);
	}
	@BeforeClass
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();
		Thread.sleep(5000);
	}

}
