package carriers;

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

public class Common {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	Logger logger = LogManager.getLogger("SpecialServicess");

	@Test()
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

		wait = new WebDriverWait(driver, 20);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");

		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
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
	public void testTwo() throws InterruptedException 
	{
		
		String shipviacode = "FEXTest4";

		logger.info(" Opening Process Shipment Menu");
		Thread.sleep(5000);
		WebElement Transaction = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2"))); // To
																												// click
																												// on
																												// Transaction
		wait.until(ExpectedConditions.elementToBeClickable(Transaction));
		Transaction.click();
		logger.info(" Click on Transaction Menu Successful");
		Thread.sleep(5000);
		WebElement Process = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_21")));// To click
																											// on
																											// Process
																											// ShipmentS
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

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtSCSearchSS"))).sendKeys(shipviacode);
		logger.info("shipviaSearch Enter Value Successful");
		Thread.sleep(5000);

		WebElement ok = driver.findElement(By.id("btnSearchOk_PS"));
		wait.until(ExpectedConditions.elementToBeClickable(ok));
		ok.click();
		logger.info("Click on Ship Via ok search  Successful");

		Thread.sleep(5000);
		

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
		
	}
	@Test
	public void SelectSpecialService() throws InterruptedException {
		
		
		WebElement buttonSpecialServices = driver.findElement(By.id("btnSpecialServices"));
		wait.until(ExpectedConditions.visibilityOf(buttonSpecialServices));
		wait.until(ExpectedConditions.elementToBeClickable(buttonSpecialServices));
		buttonSpecialServices.click();
		logger.info("Click on Special Services successful");
	}
	
	@Test
	public void Click1() throws InterruptedException 
	{
		
		WebElement NonStandardContainer = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX0")));
		NonStandardContainer.click();

//---------------------------------------------------------			
		Thread.sleep(10000);
		WebElement okbutton = driver.findElement(By.xpath("//*[@id='btnOk']"));
		okbutton.click();
		logger.info("Click on ok successful");
		
		
	}
}