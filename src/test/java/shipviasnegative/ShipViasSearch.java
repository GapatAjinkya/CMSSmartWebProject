package shipviasnegative;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class ShipViasSearch {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ShipViasSearch");
	@Test
	public void ShipviaSearch() throws InterruptedException {
		Shipvia();
		search("ABC");
		SearchNegative();
	}
	public void SearchNegative() throws InterruptedException  {
		Thread.sleep(10000);
			WebElement errorMessage = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='errorMsg']")));
			Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
			String expectedErrorMessage = "No records found!";
			String actualErrorMessage = errorMessage.getText();
			logger.error("Expected error message -" + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Incorrect error message");
			WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
			error.click();
		}
	public void Shipvia() throws InterruptedException {
		Thread.sleep(10000);
		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_4']")));
		Configuration.click();
		logger.info("Click on Configuration successful");
		Thread.sleep(5000);
		WebElement carriers = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_44']")));
		carriers.click();
		logger.info("Click on carriers successful");
		WebElement ShipVias = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_441']")));
		ShipVias.click();
		logger.info("Click on ShipVias successful");
	}
	public void search(String shipviacode) throws InterruptedException {
		Thread.sleep(5000);
		WebElement ShipViasSearh = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearchSVSF")));
		ShipViasSearh.sendKeys(shipviacode);
		logger.info("Search  ShipVias successful");
		WebElement ok = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='SVSFOkClick()']")));
		ok.click();
		logger.info("ok Click  successful");
		Thread.sleep(5000);
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
}