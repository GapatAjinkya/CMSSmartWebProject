package carriers;

import java.time.Duration;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class addshipvia {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Newcarriers");

	@BeforeMethod
	public void setup() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		Thread.sleep(10000);
	}

	@AfterMethod
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}

	@Test
	public void newcarriers() throws InterruptedException {
		
		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");

		WebElement carriers = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_44']")));

		carriers.click();
		Thread.sleep(5000);
		logger.info("Click on Carriers successful");

		WebElement carriersaccount = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_440']")));

		carriersaccount.click();
		Thread.sleep(5000);
		logger.info("Click on carriers account successful");
		
		WebElement ok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='CASFOkClick()']")));

		ok.click();
		Thread.sleep(5000);
		logger.info(" ok click successful");		
		

		WebElement Shipvias = driver.findElement(By.id("CAShipViaButton"));
		wait.until(ExpectedConditions.visibilityOf(Shipvias));
		wait.until(ExpectedConditions.elementToBeClickable(Shipvias));
		Shipvias.click();
		Thread.sleep(15000);
		logger.info("Click on Ship vias successful");

		boolean isError = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();

		if (isError) {
			WebElement text = driver.findElement(By.id("errorMsg"));
			String errortext = text.getText();
			WebElement okerror = driver.findElement(By.id("btnErrorBoxOk"));
			okerror.click();
			System.out.println(" " + text);
		} else {
			System.out.println("Adding new ship via");
		}

		WebElement addshipvia = driver.findElement(By.id("ShipViasAdd"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ShipViasAdd")));
		addshipvia.click();
		logger.info("Click on add Ship vias successful");

		Thread.sleep(10000);
		String carriercode = "UPSPS_Test";
		WebElement selectcarrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbAccountSVF")));
		Select ca = new Select(selectcarrier);
		ca.selectByVisibleText(carriercode);

		Thread.sleep(5000);
//To select service 				
		WebElement ShipviaService = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbServiceSVF")));
		Select SS = new Select(ShipviaService);
		SS.selectByVisibleText("GND -- Ground");
		logger.info("select service  successful");
		Thread.sleep(5000);

//To select Payment
		WebElement payment = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbPaymentSVF")));
		Select pay = new Select(payment);
		pay.selectByVisibleText("Shipper");
		Thread.sleep(5000);
		logger.info("select Payment  successful");
//To select bill	
		WebElement bill = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbBillDutyTaxToSVF")));
		Select billselect = new Select(bill);
		billselect.selectByVisibleText("Recipient");
		Thread.sleep(5000);
		logger.info("select bill successful");

		WebElement Billas = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbBillAsSVF")));
		Select BA = new Select(Billas);
		BA.selectByVisibleText("[SAME] Bill As This ShipVia");
		Thread.sleep(5000);
		logger.info("select billAs successful");

		WebElement Shipviacode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeSVF")));
		Shipviacode.sendKeys("UPSPS_Test_Ground");
		Thread.sleep(5000);
		logger.info("Send ship viacode  successful");

		WebElement Dis = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionSVF")));
		Dis.sendKeys("UPSPS_Test_Ground");
		Thread.sleep(5000);
		logger.info("Send Discription  successful");

		WebElement Dryice = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionSVF")));
		Dryice.click();
		Thread.sleep(5000);
		logger.info(" Dryice  successful");

		WebElement SaturdayDelivery = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("BOX2048")));
		SaturdayDelivery.click();
		Thread.sleep(5000);
		logger.info(" Saturday Delivery  successful");

		WebElement DeclaredInsuredValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("BOX8388608")));
		DeclaredInsuredValue.click();
		Thread.sleep(5000);
		logger.info(" DeclaredInsuredValue successful");

		Thread.sleep(5000);
		driver.findElement(By.id("OkClickSVF")).click();

	}
}