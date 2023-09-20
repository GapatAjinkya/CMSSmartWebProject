package carriersNegative;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CarriersSearch {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("CarriersSearch");
	@Test
	public void testcase() throws InterruptedException {
		Createcarriers();
		SearchcarriersCodeNegative("TestAG6");
	}

	public void SearchcarriersCodeNegative(String code) throws InterruptedException {

		Thread.sleep(5000);
		WebElement carriersCode = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='CASFtxtSearch']")));
		carriersCode.sendKeys(code);
		WebElement ok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='CASFOkClick()']")));
		ok.click();
		Thread.sleep(10000);
			Thread.sleep(5000);
			WebElement errorMessage = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='errorMsg']")));
			Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
			String expectedErrorMessage = "No records found!";
			String actualErrorMessage = errorMessage.getText();
			logger.info("Expected error message -" + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Incorrect error message");

			WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
			error.click();
		}
	public void Createcarriers() throws InterruptedException {
		WebElement Configuration =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		Configuration.click();
	//	logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement carriers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_44']")));
		carriers.click();
	//	logger.info("Click on Carriers successful");
		Thread.sleep(5000);
		WebElement CarrierAccounts = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_440']")));
		CarrierAccounts.click();
	}

	@BeforeClass
	public void setup() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
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
