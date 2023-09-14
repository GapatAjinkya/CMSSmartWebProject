package archivedmanifestnegative;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ArchivedManifest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ArchivedManifest");

	@Test(priority = 0)
	public void TestAMDateFrom() throws InterruptedException {
		OpenManifestExplorer();
		Carriers("US Postal Service");
		selectDateFu("January 2026", "25");
		captureError();
		CancelClick();
	}
	@Test(priority = 1)
	public void TestAMDateTO() throws InterruptedException {
		OpenManifestExplorer();
		Carriers("US Postal Service");
		driver.findElement(By.id("AMEDateSearch")).click();
		Thread.sleep(5000);
		WebElement dateTo=driver.findElement(By.id("IWLDatePickerToAME"));
		dateTo.click();
		Thread.sleep(5000);
		WebElement month = driver.findElement(By.xpath("//th[@class='datepicker-switch']"));
		while (!month.getText().contains("June 2020")) {
			driver.findElement(By.xpath("//th[@class='prev']")).click();
		}
		List<WebElement> days = driver.findElements(By.xpath("//div[@class='datepicker-days']//td[@class='day']"));
		for (WebElement day : days) {
			String text = day.getText();
			if (text.equalsIgnoreCase("25")) {
				day.click();
				break;
			}
		}
		Thread.sleep(3000);
		driver.findElement(By.id("AMDFOkClick")).click();
		captureError();
	}
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = driver.findElement(By.id("errorMsg"));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage
				.equals("DateFrom cannot be greater than DateTo. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,
					"DateFrom cannot be greater than DateTo. Please try again.",
					"Incorrect error message");
		} else if (actualErrorMessage.equals("Date range cannot be more than 365 days. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "Date range cannot be more than 365 days. Please try again.",
					"Incorrect error message");
		}else if (actualErrorMessage.equals("DateFrom cannot be greater than DateTo. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "DateFrom cannot be greater than DateTo. Please try again.",
					"Incorrect error message");
			}
		else {
			Assert.fail("Unexpected error message: " + actualErrorMessage);
		}
		WebElement error = driver.findElement(By.id("btnErrorBoxOk"));
		error.click();
	}
	public void selectDateFu(String targetMonth, String targetDay) throws InterruptedException {
		driver.findElement(By.id("AMEDateSearch")).click();
		Thread.sleep(5000);
		WebElement dateFrom=driver.findElement(By.id("IWLDatePickerFromAME"));
		dateFrom.click();
		Thread.sleep(5000);
		driver.findElement(By.id("IWLDatePickerFromAME")).click();
		WebElement month = driver.findElement(By.xpath("//th[@class='datepicker-switch']"));
		while (!month.getText().contains(targetMonth)) {
			driver.findElement(By.xpath("//th[@class='next']")).click();
		}
		List<WebElement> days = driver.findElements(By.xpath("//div[@class='datepicker-days']//td[@class='day']"));
		for (WebElement day : days) {
			String text = day.getText();
			if (text.equalsIgnoreCase(targetDay)) {
				day.click();
				break;
			}
		}
		Thread.sleep(3000);
		driver.findElement(By.id("AMDFOkClick")).click();
	}

	public void selectDate(String targetMonth, String targetDay) throws InterruptedException {
		driver.findElement(By.id("AMEDateSearch")).click();
		Thread.sleep(5000);

		WebElement dateFrom=driver.findElement(By.id("IWLDatePickerFromAME"));
		dateFrom.click();
		WebElement month = driver.findElement(By.xpath("//th[@class='datepicker-switch']"));
		while (!month.getText().contains(targetMonth)) {
			driver.findElement(By.xpath("//th[@class='prev']")).click();
		}
		List<WebElement> days = driver.findElements(By.xpath("//div[@class='datepicker-days']//td[@class='day']"));
		for (WebElement day : days) {
			String text = day.getText();
			if (text.equalsIgnoreCase(targetDay)) {
				day.click();
				break;
			}
		}
		Thread.sleep(3000);
		driver.findElement(By.id("AMDFOkClick")).click();
	}
	public void CancelClick() {
		driver.findElement(By.id("AMDFCancelClick")).click();
	}
	public void Carriers(String carrier) {
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='AMEddlCarriers']"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(carrier);
	}
	public void OpenManifestExplorer() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_25")).click(); // To click on VS
		Thread.sleep(3000);
	}

	@BeforeClass
	public void setup() throws InterruptedException {

	    System.setProperty("webdriver.chrome.driver", "E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
	     ChromeOptions options = new ChromeOptions();
	   //  options.setBinary("E:\\ChromeTesting\\chrome-win64\\chrome.exe");
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
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}
}
