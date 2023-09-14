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

public class TestCityStateZip {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("TestCityStateZip");

	@Test(priority = 0)
	public void City() throws InterruptedException {
		CreateCustomers();
		SearchCustomer("");
		addcustomer();
		customerdetails();
		SendCity("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovb");
		SendState("VA");
		SendZip("22079");
		okclick();
		captureError();
	}
	@Test(priority = 1)
	public void TestCityBlank() throws InterruptedException {
		SendCity("");
		okclick();
		captureError();
	}
	@Test(priority = 2)
	public void TestState() throws InterruptedException {
		SendCity("Lorton");
		SendState("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovb");
		okclick();
		captureError();
	}
	@Test(priority = 3)
	public void TestStateBlank() throws InterruptedException {
		SendCity("Lorton");
		SendState("");
		okclick();
		captureError();
	}
	@Test(priority = 4)
	public void TestZip() throws InterruptedException {
		SendCity("Lorton");
		SendState("VA");
		SendZip("asdfghjkloiuytrewqasdaa");
		okclick();
		captureError();
	}
	@Test(priority = 5)
	public void TestZipBlank() throws InterruptedException {
		SendCity("Lorton");
		SendState("VA");
		SendZip("");
		okclick();
		captureError();
	}
	public void addcustomer() throws InterruptedException {
		Thread.sleep(5000);
		WebElement AddCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CSTCustAdd")));
		wait.until(ExpectedConditions.elementToBeClickable(AddCustomer));
		AddCustomer.click();
		logger.info(" Click on add Customer ");
	}
	public void SearchCustomer(String code) {
		WebElement CustomerCode = driver.findElement(By.id("txtCSTCustSearch"));
		wait.until(ExpectedConditions.elementToBeClickable(CustomerCode));
		CustomerCode.sendKeys(code);
		WebElement CustomerOkClick = driver
				.findElement(By.xpath("//button[@onclick='onCustomerSearchPrivateOkClick()']"));
		wait.until(ExpectedConditions.visibilityOf(CustomerOkClick));
		CustomerOkClick.click();
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
	public void customerdetails() {
		WebElement CustomerCode = driver.findElement(By.id("CFtxtXRef"));
		wait.until(ExpectedConditions.elementToBeClickable(CustomerCode));
		CustomerCode.clear();
		CustomerCode.sendKeys("CMS");
		WebElement Comp = driver.findElement(By.id("CFtxtName"));
		wait.until(ExpectedConditions.elementToBeClickable(Comp));
		Comp.clear();
		Comp.sendKeys("CMS");
		WebElement Contact = driver.findElement(By.id("CFtxtContact"));
		wait.until(ExpectedConditions.elementToBeClickable(Contact));
		Contact.clear();
		Contact.sendKeys("CMS");
		WebElement Address1	=driver.findElement(By.id("CFtxtAddress1"));
		Address1.clear();Address1.sendKeys("Testing1");
		WebElement Address2 =driver.findElement(By.id("CFtxtAddress2"));
		Address2.clear();Address2.sendKeys("Testing1");
		WebElement Address3=driver.findElement(By.id("CFtxtAddress3"));
		Address3.sendKeys("Testing1");

		String desiredText ="UNITED STATES";
		WebElement dropdown = driver.findElement(By.id("CFtxtCountry"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(desiredText);
		logger.info(" Customers Address  successful");

		driver.findElement(By.id("CFtxtAddPhone")).sendKeys("8600");
		driver.findElement(By.id("CFtxtFax")).sendKeys("555");
		driver.findElement(By.id("CFtxtEmail")).sendKeys("test@gamil.com");
	}

	public void SendCity(String city) {
		WebElement Citycode=driver.findElement(By.id("CFtxtAddCity"));
		Citycode.clear();
		Citycode.sendKeys(city);
	}
	public void SendState(String State) {
		WebElement AddState=driver.findElement(By.id("CFtxtAddState"));
		AddState.clear();
		AddState.sendKeys(State);
	}
	public void SendZip(String zip) {
		WebElement AddZip=driver.findElement(By.id("CFtxtAddZip"));
		AddZip.clear();
		AddZip.sendKeys(zip);
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
		if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("The Zip field value must be less than 10 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "The Zip field value must be less than 10 characters. Please try again.",
					"Incorrect error message");
		} else if (actualErrorMessage.equals("The State field value must be less than 40 characters. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "The State field value must be less than 40 characters. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("The City field value must be less than 50 characters. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The City field value must be less than 50 characters. Please try again.", "Incorrect error message");
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
	public void teardown() throws InterruptedException {
//		Thread.sleep(5000);
//		driver.quit();
	}
}