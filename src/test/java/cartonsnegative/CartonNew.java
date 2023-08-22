package cartonsnegative;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class CartonNew {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Cartonsadd");

	@Test
	public void TestCartons() throws InterruptedException {
		Createcarriers();
		CartonSearch("");
		ClickonCartonAdd();
		Codecheck("Test_21");	
		Dimensions();
		CartonOk();
		captureError();
	}
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-md-10 my-auto pr-0 mx-auto']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
	        String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage.equals("Entry already exists in the database. Please try again.")) {
            // Handle first error message
            System.out.println("Handling first error message.");
            Assert.assertEquals(actualErrorMessage, "Entry already exists in the database. Please try again.", "Incorrect error message");
        } else {
            // Handle other cases or unexpected errors
            System.out.println("Unexpected error message: " + actualErrorMessage);
        }
		WebElement error = driver.findElement(By.xpath("//button[@class='WLButton text-uppercase']"));
		error.click();
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
	public void CartonSearch(String Searchcode) throws InterruptedException {
		Thread.sleep(5000);
		WebElement CartonSearch = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtSCSearch']")));
		CartonSearch.sendKeys(Searchcode);
		WebElement CartonOk = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@onclick='onCartonSearchOkClick()']")));
		CartonOk.click();
	}
	public void ClickonCartonAdd() throws InterruptedException {
		Thread.sleep(5000);
		WebElement CartonAdd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CartonAdd")));
		CartonAdd.click();
	}

	public void Codecheck(String code) {
		WebElement EntertxtCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CF_txtCode")));
		EntertxtCode.sendKeys(code);
		WebElement EnterDiscription = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("CF_txtDescription")));
		EnterDiscription.sendKeys("tset");
	}
	public void Dimensions() throws InterruptedException {
		Thread.sleep(5000);
		WebElement Length = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CF_txtLength")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '14';", Length);	
		WebElement Width = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CF_txtWidth")));
		JavascriptExecutor jsWidth = (JavascriptExecutor) driver;
		jsWidth.executeScript("arguments[0].value = '14';", Width);
		WebElement Height = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CF_txtHeight")));
		JavascriptExecutor jsHeight = (JavascriptExecutor) driver;
		jsHeight.executeScript("arguments[0].value = '12';", Height);
		WebElement Weight = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CF_txtWeight")));
		JavascriptExecutor jsWeight = (JavascriptExecutor) driver;
		jsWeight.executeScript("arguments[0].value = '147';", Weight);
		WebElement CF_txtUom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CF_txtUom")));
		CF_txtUom.sendKeys("CM");	
	}
	public void CartonOk() throws InterruptedException {
		Thread.sleep(5000);
		WebElement COk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='Carton_OK()']")));
		COk.click();
	}
	public void CartonCancel() throws InterruptedException {
		Thread.sleep(5000);
		WebElement Cancel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnSearchCancel']")));
		Cancel.click();
	}
	
	
	@BeforeClass
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 60);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
