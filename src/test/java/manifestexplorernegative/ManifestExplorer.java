package manifestexplorernegative;
import java.time.Duration;
import java.util.List;

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

public class ManifestExplorer {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ManifestExplorer");

	@Test(priority = 0)
	public void testcarriers() throws InterruptedException {
		OpenManifestExplorer();
		Carriers("US Postal Service");
		Check();
	}
	@Test(priority = 1)
	public void ActiveManifestTest() throws InterruptedException {
		OpenManifestExplorer();
		Create();
		ActiveManifest();
		selectDate("August 2023", "1");
		captureError();
		selectDateFu("October 2023", "28");
		captureError();
	}
	@Test(priority = 2)
	public void FutureManifestTest() throws InterruptedException {
		FutureManifest();
		selectDate("July 2023", "1");
		captureError();
		selectDateFu("October 2023", "28");
		captureError();
	}
	public void Create() throws InterruptedException {
		String Confirm = "Are you sure you want to create a new manifest?";
		WebElement Createbutton = driver.findElement(By.id("MECreate"));
		Createbutton.click();
		Thread.sleep(5000);
		WebElement confirmMsg = driver.findElement(By.id("confirmMsg"));
		String msg = confirmMsg.getText();
		if (msg.equalsIgnoreCase(Confirm)) {
			driver.findElement(By.id("btnConfirmBoxOk")).click();
		}
	}

	public void ActiveManifest() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("Type1")).click();
	}

	public void FutureManifest() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("Type2")).click();
	}

	public void selectDate(String targetMonth, String targetDay) throws InterruptedException {

		Thread.sleep(5000);
		driver.findElement(By.id("txtManifestFormDatePicker")).click();
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
		driver.findElement(By.id("btnManifestsFormOk")).click();
	}
	public void selectDateFu(String targetMonth, String targetDay) throws InterruptedException {

		Thread.sleep(5000);
		driver.findElement(By.id("txtManifestFormDatePicker")).click();
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
		driver.findElement(By.id("btnManifestsFormOk")).click();
	}

	public void Check() throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='MEtblManifestDetailsList']//tr//td[1]"));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals("No matching records found")) {
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		if (dataStatus) {
			WebElement button = driver.findElement(By.id("MECreate"));
			button.isEnabled();
			Assert.assertTrue(button.isEnabled(), "Button 'MECreate' is expected to be enabled but is disabled.");
		} else {
			System.out.println(" No matching records found");
		}
	}

	public void Carriers(String carrier) {
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='MEddlCarriers']"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(carrier);
	}

	public void OpenManifestExplorer() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_24")).click(); // To click on VS
		Thread.sleep(3000);
	}

	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = driver.findElement(By.xpath(
				"//body[1]/div[102]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]"));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage
				.equals("An active manifest cannot be created for a date in the past. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,
					"An active manifest cannot be created for a date in the past. Please try again.",
					"Incorrect error message");
		} else if (actualErrorMessage.equals("Date has to be in the future. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "Date has to be in the future. Please try again.",
					"Incorrect error message");
		}else if (actualErrorMessage.equals("An active manifest cannot be created for a future date. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "An active manifest cannot be created for a future date. Please try again.",
					"Incorrect error message");
		}else if (actualErrorMessage.equals("Date cannot be more than 2 weeks in the future. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "Date cannot be more than 2 weeks in the future. Please try again.",
					"Incorrect error message");
		}
		else {
			Assert.fail("Unexpected error message: " + actualErrorMessage);
		}
		WebElement error = driver.findElement(By.xpath("//button[@type='button'][contains(.,'ok')]"));
		error.click();
	}
	public void capture(String errorMessageText) throws InterruptedException {
		// Wait for the error message to be displayed
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement errorMessage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'error-message')]")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");

		String actualErrorMessage = errorMessage.getText();

		// Handle different error messages
		if (actualErrorMessage.equals(errorMessageText)) {
			System.out.println("Handling error message: " + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, errorMessageText, "Incorrect error message");
		} else {
			Assert.fail("Unexpected error message: " + actualErrorMessage);
		}

		// Find and click the OK button
		WebElement okButton = driver.findElement(By.xpath("//button[contains(text(), 'OK')]"));
		okButton.click();
	}

	@BeforeClass
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();
	}
	@AfterClass
	public void teardown() {

		driver.quit();

	}
}
