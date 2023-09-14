package test;

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

public class HazardousMaterials {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("SpecialServicess");

	@Test
	public void setup() throws InterruptedException {
	ChromeOptions options = new ChromeOptions();
	WebDriverManager.chromedriver().setup();
	options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
	options.addArguments("--remote-allow-origins=*");
	driver = new ChromeDriver(options);

	 wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
	public void shipvia() throws InterruptedException {


	  String shipviacode="FEXTest4";

		logger.info(" Opening Process Shipment Menu");
		Thread.sleep(5000);
		WebElement Transaction= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2"))); 	// To click on Transaction
		wait.until(ExpectedConditions.elementToBeClickable(Transaction));
		Transaction.click();
		logger.info(" Click on Transaction Menu Successful");
		Thread.sleep(5000);
		WebElement Process=	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_21")));// To click on Process ShipmentS
		wait.until(ExpectedConditions.elementToBeClickable(Process));
		Process.click();
		logger.info(" Click on Process Shipment Menu Successful");
		// Select the Shipvia

		Thread.sleep(10000);
		WebElement shipviaSearch = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@onclick,'btnSearch_PS()')]"))); // Search the
		wait.until(ExpectedConditions.elementToBeClickable(shipviaSearch));
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");

		Thread.sleep(10000);
//		WebElement ShipviaSearchcode = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='radCodeSS']")));
//		wait.until(ExpectedConditions.elementToBeClickable(ShipviaSearchcode));
//		ShipviaSearchcode.click();
//		logger.info("shipviaSearch code Selected");
		// Search
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtSCSearchSS"))).sendKeys(shipviacode);
		logger.info("shipviaSearch Enter Value Successful");
		Thread.sleep(5000);

		WebElement ok=driver.findElement(By.id("btnSearchOk_PS"));
		wait.until(ExpectedConditions.elementToBeClickable(ok));
		ok.click();
		logger.info("Click on Ship Via ok search  Successful");

		Thread.sleep(5000);



		  //-------------------------------------------------------
		 Thread.sleep(5000);
			driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();//Customers
			 Thread.sleep(5000);
			driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
			 Thread.sleep(5000);
			WebElement List5 = driver.findElement(By.id("selCutomerList"));
			Select CustomerList5 = new Select(List5);
			CustomerList5.selectByValue("1"); // To select Global

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']"))).click();

			Thread.sleep(5000);
			logger.info("Customer Searched");
			 Thread.sleep(5000);
			 driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]")).click();

			driver.findElement(By.id("addressformOk")).click();          // Click on OK
			Thread.sleep(5000);
			logger.info("Customer Added");

//------------------------------------------------------------------------------------------------------------------------

		 Thread.sleep(5000);
			driver.findElement(By.id("btnSpecialServices")).click();

			 WebElement HazardousMaterials=wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX6")));
			 HazardousMaterials.click();
			 Thread.sleep(5000);
			 WebElement selectmaterial=driver.findElement(By.id("SL6"));
			 Select dropdown=new Select(selectmaterial);
			 dropdown.selectByVisibleText("Hazardous Materials");

			 Thread.sleep(10000);
			  WebElement okbutton=driver.findElement(By.xpath("//*[@id='btnOk']"));
			  okbutton.click();
			  logger.info("Click on ok successful");
			  // To Add Details
				WebElement Details= driver.findElement(By.id("btnDetails"));
				Details.click();       // Details Button
				Thread.sleep(5000);
				WebElement SelectProduct=driver.findElement(By.id("btnSelectP"));
				SelectProduct.click();       // SelectProduct Button
				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[@value=\"Code\"]")).click();
				WebElement Productname= driver.findElement(By.id("txtProductSearch"));
				Productname.sendKeys("Books");
				logger.info("Books input given ");

				driver.findElement(By.id("btnPOk")).click();   // Click on oK
				Thread.sleep(10000);
			    WebElement Books= driver.findElement(By.xpath("//td[contains(text(),'Books')]"));
			    Books.click();
				logger.info("Books Selected ");


				Thread.sleep(10000);
			    driver.findElement(By.id("btnProductOk")).click();
				Thread.sleep(10000);
			    driver.findElement(By.xpath("//*[@id='btnPDOk']")).click();
				Thread.sleep(10000);
				driver.findElement(By.xpath("//*[@id='btnPdOk']")).click();
				Thread.sleep(10000);
				logger.info("Details added");



			//------------------------------------------
			WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
			ManualWeight.sendKeys("1.00");
			logger.info("Manual Weight is fill ");

			WebElement Rate = driver.findElement(By.id("cmdRate"));
			Rate.click();
			logger.info("Click on Rate ");

 			driver.findElement(By.id("btnShipClick")).click(); //Click on ship
 			logger.info("HazardousMaterials Done");

}
}