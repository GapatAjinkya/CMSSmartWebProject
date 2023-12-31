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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomersCode2 {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("CustomersCode2");

	@Test
	public void CustomerSearchTest() throws InterruptedException {
		CreateCustomers();
		SearchCustomer("USA_AG");
		captureError();
	}
	@Test
	public void addCustomerTest() throws InterruptedException {
		addcustomer();
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
		CustomerCode.sendKeys(code);
		WebElement CustomerOkClick = driver.findElement(By.xpath("//button[@onclick='onCustomerSearchPrivateOkClick()']"));
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
		else if (actualErrorMessage.equals("The Contact field value must be less than 50 characters. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The Contact field value must be less than 50 characters. Please try again.", "Incorrect error message");
		} else if (actualErrorMessage.equals("The Address Line1 field value must be less than 50 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The Address Line1 field value must be less than 50 characters. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"Field value cannot be blank. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("The Address Line2 field value must be less than 50 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The Address Line2 field value must be less than 50 characters. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("The Address Line3 field value must be less than 50 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The Address Line3 field value must be less than 50 characters. Please try again.", "Incorrect error message");
		}

		else if (actualErrorMessage.equals("The City field value must be less than 50 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The City field value must be less than 50 characters. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("The State field value must be less than 40 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The State field value must be less than 40 characters. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("The Zip field value must be less than 10 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The Zip field value must be less than 10 characters. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("The Fax field value must be less than 20 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The Fax field value must be less than 20 characters. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("The Email Address field value must be less than 50 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The Email Address field value must be less than 50 characters. Please try again.", "Incorrect error message");
		}
		else {
			System.out.println("Unexpected error message: " + actualErrorMessage);
		}

		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
	}

	public void addcustomer() throws InterruptedException {
		WebElement AddCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CSTCustAdd")));
		wait.until(ExpectedConditions.elementToBeClickable(AddCustomer));
		AddCustomer.click();
		logger.info(" Click on add Customer ");
	}

	@BeforeClass
	public void setup() throws InterruptedException {

	    System.setProperty("webdriver.chrome.driver", "E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
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
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}

	@AfterClass
	public void teardown() {
		driver.quit();

	}


}
