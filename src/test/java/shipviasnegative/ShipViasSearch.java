package shipviasnegative;
import java.util.concurrent.TimeUnit;
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
import io.github.bonigarcia.wdm.WebDriverManager;
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
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
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
		WebElement ok = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();
		String expectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		String actualTitle = driver.getTitle();
		assert actualTitle.equalsIgnoreCase(expectedTitle) : "Title didn't match";
		System.out.println("Title Matched");
		Thread.sleep(10000);
	}
}