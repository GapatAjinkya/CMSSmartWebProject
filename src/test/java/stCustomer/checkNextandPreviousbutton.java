package stCustomer;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class checkNextandPreviousbutton {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("checkNextandPreviousbutton");

	@BeforeMethod
	public void setup() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		 wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		logger.info("Browser opend");
		driver.manage().window().maximize();
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

		Thread.sleep(10000);
		driver.close();
	}

	@Test
	public void checkbutton() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Clickon Configuration successful");

		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		wait.until(ExpectedConditions.visibilityOf(SupportTables));
		wait.until(ExpectedConditions.elementToBeClickable(SupportTables));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");

		WebElement Customers = driver.findElement(By.xpath("//a[@id='menu_item_451']"));
		wait.until(ExpectedConditions.visibilityOf(Customers));
		wait.until(ExpectedConditions.elementToBeClickable(Customers));
		Customers.click();
		logger.info(" Customers Windo Open  successful");

		Thread.sleep(6000);
		WebElement CustomerOkClick = driver.findElement(By.xpath("//button[@onclick='onCustomerSearchPrivateOkClick()']"));
		wait.until(ExpectedConditions.visibilityOf(CustomerOkClick));
		wait.until(ExpectedConditions.elementToBeClickable(CustomerOkClick));
		CustomerOkClick.click();
		logger.info(" Click on ok  successful");

		String check = "Previous"; // to specify the next or previous value
		WebElement buttonNext = driver.findElement(By.xpath("//button[@id='CSTCustNext']"));
		WebElement buttonPrevious = driver.findElement(By.xpath("//button[@id='CSTCustPrevious']"));

		   assert buttonNext.isEnabled() && buttonPrevious.isEnabled() : "Initial state is incorrect.";

		if (check.equals("Next")) {
			boolean nextEnabled = buttonNext.isEnabled();
			if (nextEnabled) {
				wait.until(ExpectedConditions.elementToBeClickable(buttonNext));
				buttonNext.click();
				logger.info("Test Case Pass - Next button is displayed and clicked");
			} else {
				logger.info("Test Case Fail - Next button is not enabled");
			}
		} else if (check.equals("Previous")) {
			boolean previousEnabled = buttonPrevious.isEnabled();
			System.out.println("previous Button is Enabled -" + previousEnabled);
			Thread.sleep(5000);
			buttonNext.click();
			Thread.sleep(5000);
			logger.info("Next button is clicked");
			wait.until(ExpectedConditions.elementToBeClickable(buttonPrevious));
			buttonPrevious.click();
			logger.info("Test Case Pass - Previous button is displayed and clicked");

		} else {
			logger.info("Test Case Fail - Previous button is not enabled");
		}

	}
}
