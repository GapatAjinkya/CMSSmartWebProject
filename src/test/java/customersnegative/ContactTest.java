package customersnegative;

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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ContactTest");

	@Test
	public void ContactTextboxTest() throws InterruptedException {
		CreateCustomers();
		SearchCustomer("");
		addcustomer();
		customerdetails();
		okclick();
		captureError();
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
	public void addcustomer() throws InterruptedException {
		Thread.sleep(5000);
		WebElement AddCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CSTCustAdd")));
		wait.until(ExpectedConditions.elementToBeClickable(AddCustomer));
		AddCustomer.click();
		logger.info(" Click on add Customer ");
	}
	public void customerdetails() throws InterruptedException {	
		Thread.sleep(5000);
		WebElement CustomerCode = driver.findElement(By.id("CFtxtXRef"));
		wait.until(ExpectedConditions.elementToBeClickable(CustomerCode));
		CustomerCode.clear();
		CustomerCode.sendKeys("cms");
		WebElement Comp = driver.findElement(By.id("CFtxtName"));
		wait.until(ExpectedConditions.elementToBeClickable(Comp));
		Comp.sendKeys("CMS");
		
		WebElement Contact = driver.findElement(By.id("CFtxtContact"));
		wait.until(ExpectedConditions.elementToBeClickable(Contact));
		Contact.sendKeys("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovb");
		
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
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage.equals("The Contact field value must be less than 50 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "The Contact field value must be less than 50 characters. Please try again.", "Incorrect error message");
		}  else {
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
	public void teardown() throws InterruptedException {
		Thread.sleep(5000);
	driver.quit();

	}		
}
