package bestwayvias;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Bestwayviarate {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("BestWaysTest");

	String bestwaycode = "TestAG1";

	@Test
	public void bestwaynew() throws InterruptedException {
		shipment();
		addCustomer("CMS");
		Weight();
		Rate();
		ShipViasthatqualifiedforrating();
		ShipButton();
	}

	public void ShipViasthatqualifiedforrating() {

	List<WebElement> Svqfr = driver.findElements(By.xpath("//table[@id='tblBestWayRatesList']//tr//td[1]"));

	for (WebElement ele : Svqfr)
	{
		String value = ele.getText();
		logger.info("ShipVias that qualified for rating:- "+value);
		ele.click();
		break;
}
	WebElement ok = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='btnOk_BestWayRatesForm']")));
	ok.click();
	}

	public void ShipButton() {

		WebElement SB = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='btnShipClick']")));
		SB.click();
	}

	public void shipment() throws InterruptedException {
		Thread.sleep(5000);
		WebElement Transaction = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2")));
		Transaction.click();
		Thread.sleep(5000);
		WebElement ProcessS = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_21")));
		ProcessS.click();
		Thread.sleep(15000);

		WebElement shipviaSearch = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@onclick='btnSearch_PS()']")));
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");
		Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearchSS")).sendKeys(bestwaycode);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnSearchOk_PS"))).click();
		logger.info("shipvia is selected");

//		WebElement SelectShipvia = wait.until(ExpectedConditions.presenceOfElementLocated(
//				By.xpath("//select[@id='txtShipVia']//option[contains(.,'" + bestwaycode + "')]")));
//		SelectShipvia.click();

	}

	public void Weight() throws InterruptedException {
		Thread.sleep(5000);
		WebElement Weight = driver.findElement(By.xpath("//input[@id='txtManual']"));

		Weight.sendKeys("1.00");
		logger.info("Manual Weight is fill ");
	}

	public void Rate() throws InterruptedException {
		Thread.sleep(5000);
		WebElement Ratebutton = driver.findElement(By.xpath("//button[@id='cmdRate']"));
		Ratebutton.click();
		logger.info("Click on Rate ");
	}

	private void addCustomer(String customerName) throws InterruptedException {
		Thread.sleep(5000);
		WebElement customer = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='AddressesClick()']")));
		wait.until(ExpectedConditions.elementToBeClickable(customer));
		customer.click();
		// logger.info("Clicked on Customer");
		Thread.sleep(5000);
		WebElement searchCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSCSearch"))); // searchcustomer
		searchCustomer.sendKeys(customerName);
		WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selCutomerList")));
		Select customerList = new Select(list);
		customerList.selectByValue("1"); // To select Global
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']"))).click(); // ok
		Thread.sleep(5000);
		// logger.info("Customer Searched");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), '" + customerName + "')]"))).click();
		WebElement customerOk = driver.findElement(By.id("addressformOk"));
		customerOk.click(); // Click on OK
		Thread.sleep(5000);
		// logger.info("Customer Added");
	}

	public void CreateBestWay() throws InterruptedException {
		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement carriers = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_44']")));
		wait.until(ExpectedConditions.elementToBeClickable(carriers));
		carriers.click();
		logger.info("Click on Carriers successful");
		Thread.sleep(5000);
		WebElement bestWayVias = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_442']")));
		wait.until(ExpectedConditions.elementToBeClickable(bestWayVias));
		bestWayVias.click();
	}

	@BeforeClass
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
		Thread.sleep(10000);
	}

}
