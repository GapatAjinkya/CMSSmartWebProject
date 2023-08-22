package archivedmanifesttest;

import java.time.LocalDate;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Search {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Newcarriers");

	@BeforeClass
	public void setup() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
	@Test(priority = 0)
	private void ArchivedManifests() throws InterruptedException {
		// logger.info("Opening Process Shipment Menu");
		Thread.sleep(5000);
		WebElement transaction = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2")));
		wait.until(ExpectedConditions.elementToBeClickable(transaction)).click();
		// logger.info("Click on Transaction Menu Successful");
		Thread.sleep(5000);
		WebElement ArchivedManifests = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_25")));
		wait.until(ExpectedConditions.elementToBeClickable(ArchivedManifests)).click();
		// logger.info("Click on Archived Manifests Menu Successful");
	}
	@Test(priority = 1)
	private void Searchwithdate() throws InterruptedException {
		WebElement buttondate = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='AMEDateSearch']")));
		wait.until(ExpectedConditions.elementToBeClickable(buttondate)).click();

	}
	@Test(priority = 2)
	public void DateSearchFrom() throws InterruptedException {

		Thread.sleep(3000);
		WebElement buttondate = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='ABOL_DateAction()']")));
		wait.until(ExpectedConditions.elementToBeClickable(buttondate)).click();

		Thread.sleep(3000);
		WebElement from = driver.findElement(By.xpath("//input[@id='IWLDatePickerFromABOL']"));
		from.click();

		WebElement month = driver.findElement(By.xpath("//th[@class='datepicker-switch']"));
		LocalDate currentDate = LocalDate.now();
		System.out.println(currentDate);

		while (!month.getText().contains("May 2023")) {
			driver.findElement(By.xpath("//th[@class='prev']")).click();
		}
		List<WebElement> days = driver.findElements(By.xpath("//div[@class='datepicker-days']//td[@class='day']"));
		for (WebElement day : days) {
			String text = day.getText();
			if (text.equalsIgnoreCase("20")) {
				day.click();
				System.out.println(text);
				driver.findElement(By.id("AMDFOkClick")).click();
				break;
			}
		}
	}
}
