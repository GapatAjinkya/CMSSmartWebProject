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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PSHolds {

	
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("PSHolds");
	
	@Test(priority = 0)
	public void TestShipToHold() throws InterruptedException {
		OpenPs();
		ShipVia("UPSProshipG");
		addCustomer("CMS");
		Manual("1");
		ShiptoHold();
	}
	@Test(priority = 1)
	public void testHold() throws InterruptedException {
		SelectHold();
		Ship();
	}
	public void HoldButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("btnHolds")).click();
	}
	public void Ship() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("btnShipClick")).click();
		logger.info("Click on  Shipment ");
	}

	public void SelectHold() throws InterruptedException {
		String textValue = null; 
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='shipmentHistoryTable']//td[2]"));
		for (WebElement ele : alldata) {
		  textValue = ele.getText();  
		    System.out.println("This is the Shipment id - "+textValue);
		    HoldButton();
		    Thread.sleep(2000);
		    WebElement inputBox = driver.findElement(By.id("PSHSF_txtSearch"));
		    inputBox.sendKeys(textValue);
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//button[@onclick='PSHSF_OkClick()']")).click();
		}		
		 Thread.sleep(3000);
		List<WebElement> Pktable = driver.findElements(By.xpath("//table[@id='PHF_PackageOnHoldsList']//td[1]"));
		for (WebElement elepk : Pktable) {
			 String Shipid = elepk.getText();
			if(Shipid.equalsIgnoreCase(textValue)) 
			{
				elepk.click();
				 Thread.sleep(2000);
			driver.findElement(By.id("PHF_OkButton")).click();
			}
		}
	}
	public void ShipVia(String ShipViaCode) throws InterruptedException {
		Thread.sleep(3000);
		WebElement shipviaSearch = driver.findElement(By.xpath("//*[@onclick=\"btnSearch_PS()\"]")); // Search the																								// Shipvias list
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");
		Thread.sleep(3000);
		WebElement ShipviaSearchcode = driver.findElement(By.xpath("//input[@id=\"radCodeSS\"]"));
		ShipviaSearchcode.click();
		WebElement code=driver.findElement(By.id("txtSCSearchSS"));
		code.sendKeys(ShipViaCode);

		driver.findElement(By.id("btnSearchOk_PS")).click();

	}
	public void ShiptoHold() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("cmdShipToHold")).click();
		logger.info("Click on  Shipment ");
	}
	 public void Manual(String Weight) throws InterruptedException {
	    	Thread.sleep(2000);
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
		logger.info("Customer Added");
	}
	
	
	
	
	
	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process Returns
		Thread.sleep(2000);
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
