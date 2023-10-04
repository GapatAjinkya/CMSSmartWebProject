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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShipViasDescription {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ShipViasDescription");

	@Test
	public void ShipviaDescriptionTest() throws InterruptedException {
		Shipvia();
		search("");
		addshipvia();
		CheckDescription("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/1");
		captureError();
	}

	@Test(dependsOnMethods = "ShipviaDescriptionTest")
public void CheckcodeBlank() throws InterruptedException {
		Thread.sleep(5000);
		WebElement Shipviacode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeSVF")));
		Shipviacode.clear();
		Shipviacode.sendKeys("Testcode");
		Thread.sleep(5000);
		logger.info("Send ship viacode  successful");
		WebElement Dis = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionSVF")));
		Dis.clear();
		Dis.sendKeys("");
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
	public void CheckDescription(String Description) throws InterruptedException {
		Thread.sleep(5000);
		WebElement Shipviacode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeSVF")));
		Shipviacode.clear();
		Shipviacode.sendKeys("asdfghjkloiuytrewqas");

		logger.info("Send ship viacode  successful");

		WebElement Dis = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionSVF")));
		Dis.clear();
		Dis.sendKeys(Description);
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
		if (actualErrorMessage.equals("Value should be less than 50 characters. Please try again.")) {
            // Handle first error message
            System.out.println("Handling first error message.");
            Assert.assertEquals(actualErrorMessage, "Value should be less than 50 characters. Please try again.", "Incorrect error message");
        } else if (actualErrorMessage.equals("Field cannot be blank. Please try again.")) {
            // Handle second error message
            System.out.println("Handling second error message.");
            Assert.assertEquals(actualErrorMessage, "Field cannot be blank. Please try again.", "Incorrect error message");
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
	void teardown() throws InterruptedException {
		Thread.sleep(10000);
		driver.close();
	}
}
