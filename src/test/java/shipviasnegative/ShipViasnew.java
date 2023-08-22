package shipviasnegative;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShipViasnew {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("CarriersSearch");
	
	
	@Test
	public void TeatShipvia() throws InterruptedException {
		Shipvia();
		search("");
		addshipvia();
		newshipvia();
		captureError();
	}
	public void newshipvia() throws InterruptedException {
		
		String shipviacode = "FEX_Test1_GN1";
		String ShipviaDescription = "Ground";
		String ShipviaService = "GN -- Ground®";
		String Carriercode = "FEX_Test1 -- FedEx US FSMS";
		String SpServices1 = "Dry Ice";
		Thread.sleep(20000);
		WebElement selectcarrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbAccountSVF")));
		Select ca = new Select(selectcarrier);
		ca.selectByVisibleText(Carriercode);

		Thread.sleep(5000);
//To select service 	

		WebElement ShipviaServicet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbServiceSVF")));
		Select SS = new Select(ShipviaServicet);
		SS.selectByVisibleText(ShipviaService);
		logger.info("select service  successful");
//To select Payment
		WebElement payment = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbPaymentSVF")));
		Select pay = new Select(payment);
		pay.selectByVisibleText("Shipper");
		logger.info("select Payment  successful");
//To select bill	
		WebElement bill = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbBillDutyTaxToSVF")));
		Select billselect = new Select(bill);
		billselect.selectByVisibleText("Recipient");
		logger.info("select bill successful");

		WebElement Billas = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbBillAsSVF")));
		Select BA = new Select(Billas);
		BA.selectByVisibleText("[SAME] Bill As This ShipVia");
		logger.info("select billAs successful");
		WebElement Shipviacode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeSVF")));
		Shipviacode.sendKeys(shipviacode);
		Thread.sleep(5000);
		logger.info("Send ship viacode  successful");
		WebElement Dis = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionSVF")));
		Dis.sendKeys(ShipviaDescription);
		Thread.sleep(5000);
		logger.info("Send Discription  successful");
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//table[@id='xmlTableSVF']//tr"));
		String desiredText = "Alcohol";
	
		for (WebElement checkbox : checkboxes) {
		    String checkboxText = checkbox.getText();
		    if (checkboxText.equals(desiredText)) {
		        if (checkbox.isDisplayed()) {
		            checkbox.click();
		            System.out.println("Checkbox selected successfully.");
		            break;
		        } else {
		            System.out.println("Checkbox is not displayed.");
		        }
		    }
		}		
		Thread.sleep(5000);
		wait
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='OkClickSVF']"))).click();
		logger.info(" Click on ok  successful");
	}
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String expectedErrorMessage = "Duplicate ShipVia within selected Org/Site Group. Please try again.";
		String actualErrorMessage = errorMessage.getText();
		logger.info("Actual Error Message :-"+actualErrorMessage);
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Incorrect error message");
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
	}
	public void addshipvia() throws InterruptedException {
		Thread.sleep(10000);
		WebElement addshipvia = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ShipViasAdd")));
		addshipvia.click();
		logger.info("Click on add ShipVias successful");
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
		Thread.sleep(10000);
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
