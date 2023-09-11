package carriers;

import java.time.Duration;
import java.util.ArrayList;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testcasespecialservice {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Testcasespecialservice");

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

	@BeforeMethod
	public void shipvia() throws InterruptedException {

		logger.info(" Opening Process Shipment Menu");
		Thread.sleep(5000);

		WebElement Transaction = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2"))); // Transaction
		wait.until(ExpectedConditions.elementToBeClickable(Transaction));
		Transaction.click();
		logger.info(" Click on Transaction Menu Successful");
		Thread.sleep(5000);
		WebElement Process = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_21")));// To click

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
		String shipviacode = "FEXTest4";
		WebElement sendcode = driver.findElement(By.id("txtSCSearchSS"));
		wait.until(ExpectedConditions.visibilityOf(shipviaSearch));
		sendcode.sendKeys(shipviacode);
		logger.info("shipviaSearch Enter Value Successful");

		WebElement ok = driver.findElement(By.id("btnSearchOk_PS"));
		wait.until(ExpectedConditions.visibilityOf(ok));
		ok.click();
		logger.info("Click on Ship Via ok search  Successful");

		Thread.sleep(5000);
	}

	@Test
	public void testcase() throws InterruptedException {
		By checkBoxLocator = By.xpath("//input[@type='checkbox' and contains(@id,'BOX')]");
		int numberOfIterations = 16;
		// creating an Empty Integer List
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
	arr.add(2);
	arr.add(11);
	arr.add(12);
	arr.add(14);
		for (int i = 0; i < numberOfIterations; i++) {
			if (arr.contains(i)) {
				addcustomer("CMS");
				SpecialServicesclick();

				List<WebElement> checkboxes = wait
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(checkBoxLocator));
				if (i < checkboxes.size()) {
					WebElement checkbox = checkboxes.get(i);
					checkboxes.get(i).click();
					String checkboxText = checkbox.getText();

					System.out.println("-----------===----------" + i + checkboxText);
					WebElement okclick=driver.findElement(By.xpath("//button[@id='btnOk']"));
					okclick.click();
				}
				EnterWeight();
				clickRate();
				Clickship();
			}
		}
	}

	public void addcustomer(String customercode) throws InterruptedException {
		WebElement customer = driver.findElement(By.xpath("//button[@onclick='AddressesClick()']"));
		customer.click();
		logger.info("Clicked on Customer");

		// To Customer Search Criteria
		Thread.sleep(5000);

		WebElement searchcustomer = driver.findElement(By.id("txtSCSearch")); // searchcustomer
		searchcustomer.sendKeys(customercode);

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
//-----------------------------------------------
	}
	public void SpecialServicesclick() {

		WebElement buttonSpecialServices = driver.findElement(By.id("btnSpecialServices"));
		wait.until(ExpectedConditions.visibilityOf(buttonSpecialServices));
		wait.until(ExpectedConditions.elementToBeClickable(buttonSpecialServices));
		buttonSpecialServices.click();
		logger.info("Click on Special Services successful");
	}
	private void EnterWeight() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='txtManual']"))).sendKeys("1.00");
		// logger.info("Manual Weight is fill ");
	}
	private void clickRate() throws InterruptedException {
		WebElement Rate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmdRate")));
		wait.until(ExpectedConditions.elementToBeClickable(Rate));
		Rate.click();
		// logger.info("Click on Rate ");
	}
	private void Clickship() throws InterruptedException {
		Thread.sleep(5000);
		WebElement ship = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnShipClick']")));
		wait.until(ExpectedConditions.elementToBeClickable(ship));
		ship.click();
	}	
	
}
