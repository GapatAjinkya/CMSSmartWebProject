package customersnegative;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomersCode {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("CustomersCode");

	@Test(priority = 0)
	public void CustomerSearchTest() throws InterruptedException {
		CreateCustomers();
		SearchCustomer("USA_AG");
		captureError();
	}
	@Test(priority = 1)
	public void addCustomerTest() throws InterruptedException {
		addcustomer();
		CustomerCode("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/1");
		customerdetails();
		okclick();
		captureError();
	}
	@Test(priority = 2)
	public void TestCompanyTextBox() throws InterruptedException {
		CustomerCode("CMS");
		WebElement Comp = driver.findElement(By.id("CFtxtName"));
		wait.until(ExpectedConditions.elementToBeClickable(Comp));
		Comp.clear();
		Comp.sendKeys("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/1");
		okclick();
		captureError();
	}
	@Test(priority = 3)
	public void Testblankcode() throws InterruptedException {
		CustomerCode("");
		okclick();
		captureError();
	}
	@Test(priority = 4)
	public void TestblankCompany() throws InterruptedException {
		CustomerCode("CMS");
		WebElement Comp = driver.findElement(By.id("CFtxtName"));
		wait.until(ExpectedConditions.elementToBeClickable(Comp));
		Comp.clear();
		Comp.sendKeys("");
		okclick();
		captureError();
	}
	public void CustomerCode(String CCode) {
		WebElement CustomerCode = driver.findElement(By.id("CFtxtXRef"));
		wait.until(ExpectedConditions.elementToBeClickable(CustomerCode));
		CustomerCode.clear();
		CustomerCode.sendKeys(CCode);
	}
	public void customerdetails() {
		WebElement Comp = driver.findElement(By.id("CFtxtName"));
		wait.until(ExpectedConditions.elementToBeClickable(Comp));
		Comp.sendKeys("CMS");
		WebElement Contact = driver.findElement(By.id("CFtxtContact"));
		wait.until(ExpectedConditions.elementToBeClickable(Contact));
		Contact.sendKeys("CMS");
		driver.findElement(By.id("CFtxtAddress1")).sendKeys("Testing1");
		driver.findElement(By.id("CFtxtAddress2")).sendKeys("Testing1");
		driver.findElement(By.id("CFtxtAddress3")).sendKeys("Testing1");
		driver.findElement(By.id("CFtxtAddCity")).sendKeys("Toronto");
		driver.findElement(By.id("CFtxtAddState")).sendKeys("Ontario");
		driver.findElement(By.id("CFtxtAddZip")).sendKeys("23456");

		String desiredText ="UNITED STATES";
		WebElement dropdown = driver.findElement(By.id("CFtxtCountry"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(desiredText);
		logger.info(" Customers Address  successful");

		driver.findElement(By.id("CFtxtAddPhone")).sendKeys("8600");
		driver.findElement(By.id("CFtxtFax")).sendKeys("555");
		driver.findElement(By.id("CFtxtEmail")).sendKeys("test@gamil.com");
	}
	public void okclick() {
		driver.findElement(By.xpath("//button[@onclick='OkClickCustomerForm()']")).click();
	}
	public void addcustomer() throws InterruptedException {
		Thread.sleep(5000);
		WebElement AddCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CSTCustAdd")));
		wait.until(ExpectedConditions.elementToBeClickable(AddCustomer));
		AddCustomer.click();
		logger.info(" Click on add Customer ");
	}
	public void CreateCustomers() throws InterruptedException {
		Thread.sleep(5000);
		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		Configuration.click();
		logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement SupportTables = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_45']")));
		SupportTables.click();
		logger.info("Click on Carriers successful");
		WebElement Customers = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_451']")));
		Customers.click();
	}

	public void SearchCustomer(String code) {
		WebElement CustomerCode = driver.findElement(By.id("txtCSTCustSearch"));
		wait.until(ExpectedConditions.elementToBeClickable(CustomerCode));
		CustomerCode.sendKeys(code);
		WebElement CustomerOkClick = driver.findElement(By.xpath("//button[@onclick='onCustomerSearchPrivateOkClick()']"));
		wait.until(ExpectedConditions.visibilityOf(CustomerOkClick));
		CustomerOkClick.click();
	}

	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage.equals("No records found!")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "No records found!", "Incorrect error message");
		} else if (actualErrorMessage.equals("The Company field value must be less than 50 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "The Company field value must be less than 50 characters. Please try again.",
					"Incorrect error message");
		} else if (actualErrorMessage.equals("undefined")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "undefined", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"Field value cannot be blank. Please try again.", "Incorrect error message");
		}
		else {
			System.out.println("Unexpected error message: " + actualErrorMessage);
		}

		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
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

	@AfterClass
	public void teardown() {
		driver.quit();

	}
}