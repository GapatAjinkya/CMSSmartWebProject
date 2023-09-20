package cartonsnegative;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartoneditDelete {


	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("CartoneditDelete");

	@Test(priority = 0)
	public void TestCartonsedit() throws InterruptedException {
		Createcarriers();
		CartonSearch("Test_21");
		edit("Test_21");
		CartonOk();
	}
	@Test(priority = 1)
	public void TestCartonDelete() throws InterruptedException {
		Delete("Test_new");
	}
	public void CartonSearch(String Searchcode) throws InterruptedException {

		Thread.sleep(5000);
		WebElement CartonSearch = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtSCSearch']")));
		CartonSearch.sendKeys(Searchcode);
		WebElement CartonOk = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@onclick='onCartonSearchOkClick()']")));
		CartonOk.click();
	}
	public void edit(String code) throws InterruptedException {
		Thread.sleep(3000);
		String newcode = "Test_new";
		WebElement SelectElement = driver.findElement(By.xpath("//table[@id='CartonsList']//td[contains(text(), '" + code + "')]"));
		SelectElement.click();
		Thread.sleep(3000);
		driver.findElement(By.id("CartonEdit")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("CF_txtCode")).clear();
		driver.findElement(By.id("CF_txtCode")).sendKeys(newcode);
	}
	public void Delete(String Delete) throws InterruptedException {
		WebElement SelectElement = driver.findElement(By.xpath("//table[@id='CartonsList']//td[contains(text(), '" + Delete + "')]"));
		SelectElement.click();
		driver.findElement(By.id("CartonDelete")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("btnConfirmBoxOk")).click();

	}
	public void CartonOk() throws InterruptedException {
		Thread.sleep(5000);
		WebElement COk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='Carton_OK()']")));
		COk.click();

	}
	public void Createcarriers() throws InterruptedException {
		Thread.sleep(5000);
		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		Configuration.click();
		logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement SupportTables = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_45']")));
		SupportTables.click();
		logger.info("Click on Carriers successful");
		WebElement Cartons = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_450']")));
		Cartons.click();
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
}
