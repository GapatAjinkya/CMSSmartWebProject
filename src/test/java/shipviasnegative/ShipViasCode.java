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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShipViasCode {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ShipViasSearch");
	
	@Test
	public void ShipviacodeTest() throws InterruptedException {
		Shipvia();	
		search("");
		addshipvia();
		Checkcode("asdfghjkloiuytrewqasd");
		captureError();
	}
	
	@Test(dependsOnMethods = "ShipviacodeTest")
public void CheckcodeBlank() throws InterruptedException {

		WebElement Shipviacode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeSVF")));
		Shipviacode.clear();
		Shipviacode.sendKeys("");
		Thread.sleep(5000);
		logger.info("Send ship viacode  successful");
		WebElement Dis = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionSVF")));
		Dis.clear();
		Dis.sendKeys("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/");
		Thread.sleep(5000);
		logger.info("Send Discription  successful");
		driver.findElement(By.id("OkClickSVF")).click();
		captureError();
	}

	public void addshipvia() {
		WebElement addshipvia = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ShipViasAdd")));
		addshipvia.click();
		logger.info("Click on add ShipVias successful");
	}
	public void Checkcode(String code) throws InterruptedException {
		
		WebElement Shipviacode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeSVF")));
		Shipviacode.clear();
		Shipviacode.sendKeys(code);
		Thread.sleep(5000);
		logger.info("Send ship viacode  successful");
		
		WebElement Dis = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionSVF")));
		Dis.clear();
		Dis.sendKeys("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/");
		Thread.sleep(5000);
		logger.info("Send Discription  successful");		
		driver.findElement(By.id("OkClickSVF")).click();
	}
	public void captureError() throws InterruptedException {	
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
	        String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
            // Handle first error message
            System.out.println("Handling first error message.");
            Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.", "Incorrect error message");
        } else if (actualErrorMessage.equals("Value should be 20 characters long. Please try again.")) {
            // Handle second error message
            System.out.println("Handling second error message.");
            Assert.assertEquals(actualErrorMessage, "Value should be 20 characters long. Please try again.", "Incorrect error message");
        } else {
            // Handle other cases or unexpected errors
            System.out.println("Unexpected error message: " + actualErrorMessage);
            
        }
		
			WebElement error=driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		    error.click();      
	}
	
	public void Shipvia() throws InterruptedException {
		Thread.sleep(10000);
		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_4']")));
		Configuration.click();
		logger.info("Click on Configuration successful");
		Thread.sleep(3000);
		WebElement carriers = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_44']")));
		carriers.click();
		logger.info("Click on carriers successful");
		Thread.sleep(3000);
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
	
	@AfterClass
	void teardown() throws InterruptedException {
		Thread.sleep(10000);
		driver.close();
	}
}
