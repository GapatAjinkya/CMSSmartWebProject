package carriersNegative;

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

public class TestCaseForDescription {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("CarriersSearch");

	@Test(priority = 0)
	public void testcase() throws InterruptedException {
		CarrierAccount();
		AddButton();
		Description("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/11");
		Okbutton();
		captureError();
	}

	@Test(priority = 1)
	public void testBlanck() throws InterruptedException {

		WebElement txtDescription = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtDescription")));
		txtDescription.clear();
		txtDescription.sendKeys("");
		Okbutton();
		Thread.sleep(8000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		String ErrorText = errorMessage.getText();
		// logger.error("Expected error message -"+ErrorText);
		System.out.println("Expected error message" + ErrorText);
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String expectedErrorMessage = "DESCRIPTION Field cannot be blank. Please try again.";
		String actualErrorMessage = errorMessage.getText();
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Incorrect error message");
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
	}
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
	        String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage.equals(" Value should be less than 50 characters. Please try again.")) {
            // Handle first error message
            System.out.println("Handling first error message.");
            Assert.assertEquals(actualErrorMessage, "Value should be less than 50 characters. Please try again.", "Incorrect error message");
        } else if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
            // Handle second error message
            System.out.println("Handling second error message.");
            Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.", "Incorrect error message");
        } else {
            // Handle other cases or unexpected errors
            System.out.println("Unexpected error message: " + actualErrorMessage);
        }
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
	}
	public void AddButton() throws InterruptedException {
		Thread.sleep(5000);
		WebElement add = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='CAAddbutton']")));
		add.click();
	}
	public void Description(String description) throws InterruptedException {
		Thread.sleep(10000);
		WebElement txtCodeBWF = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtCode")));
		txtCodeBWF.sendKeys("asdf2@%a10");
		Thread.sleep(5000);
		WebElement Des = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtDescription")));
		Des.sendKeys(description);
	}
	
	public void CarrierAccount() throws InterruptedException {
		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		 logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement carriers = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_44']")));
		wait.until(ExpectedConditions.elementToBeClickable(carriers));
		carriers.click();
		 logger.info("Click on Carriers successful");
		Thread.sleep(5000);
		WebElement CarrierAccounts =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_440']")));
		wait.until(ExpectedConditions.elementToBeClickable(CarrierAccounts));
		CarrierAccounts.click();
		Thread.sleep(8000);
		WebElement ok = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='CASFOkClick()']")));
		ok.click();
	}
	public void Okbutton() throws InterruptedException {
		WebElement okbutton = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='CAF_OkClick()']")));
		okbutton.click();
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
