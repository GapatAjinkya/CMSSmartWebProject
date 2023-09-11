package localTest;

import java.time.Duration;
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

public class Carriers {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Departments");

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
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}

	@Test(priority = 1)
	public void newDepartments() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");

		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		wait.until(ExpectedConditions.visibilityOf(SupportTables));
		wait.until(ExpectedConditions.elementToBeClickable(SupportTables));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");
		Thread.sleep(3000);
		WebElement Departments = driver.findElement(By.id("menu_item_452"));
		wait.until(ExpectedConditions.visibilityOf(Departments));
		wait.until(ExpectedConditions.elementToBeClickable(Departments));
		Departments.click();
		Thread.sleep(5000);
		logger.info("Click on Departments successful");

		WebElement okclick = driver.findElement(By.xpath("//button[@onclick='onDepatmentSearchPrivateOkClick()']"));
		wait.until(ExpectedConditions.visibilityOf(okclick));
		wait.until(ExpectedConditions.elementToBeClickable(okclick));
		okclick.click();
		logger.info("Click on ok successful");
		Thread.sleep(3000);
		WebElement addbutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("CSTDeptAdd")));
		addbutton.click();
		logger.info("Click on add button successful");
		Thread.sleep(3000);
		String Departmentcode = "Department5";
		String DescriptionText = "Test Department";

		WebElement codeDepartment = driver.findElement(By.id("DFtxtCode"));
		wait.until(ExpectedConditions.visibilityOf(codeDepartment));
		wait.until(ExpectedConditions.elementToBeClickable(codeDepartment));
		codeDepartment.sendKeys(Departmentcode);
		logger.info("Code selected  successful");

		WebElement Description = driver.findElement(By.id("DFtxtDescription"));
		wait.until(ExpectedConditions.visibilityOf(Description));
		wait.until(ExpectedConditions.elementToBeClickable(Description));
		Description.sendKeys(DescriptionText);
		logger.info("Description Selected successful");

		WebElement clickok = driver.findElement(By.xpath("//button[@onclick='OkClickDepartmentForm()']"));
		wait.until(ExpectedConditions.visibilityOf(clickok));
		wait.until(ExpectedConditions.elementToBeClickable(clickok));
		clickok.click();
		Thread.sleep(3000);
		 WebElement error = driver.findElement(By.id("btnErrorBoxOk"));
			boolean isErrorMessageDisplayed = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
			if (isErrorMessageDisplayed) {
				WebElement errorText = driver.findElement(By.id("errorMsg"));
				String text = errorText.getText();
				wait.until(ExpectedConditions.elementToBeClickable(error));
				error.click();
				logger.error("" + text);
				Thread.sleep(5000); // Consider using explicit waits instead of Thread.sleep
				//Assert.fail("Test case failed due to error: " + text);
			} else {
				logger.info("Test case passed: No error message displayed.");
			}
		
	}
	@Test(priority = 0)
	private void searchdepartment() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");

		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		wait.until(ExpectedConditions.visibilityOf(SupportTables));
		wait.until(ExpectedConditions.elementToBeClickable(SupportTables));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");

		WebElement Departments = driver.findElement(By.id("menu_item_452"));
		wait.until(ExpectedConditions.visibilityOf(Departments));
		wait.until(ExpectedConditions.elementToBeClickable(Departments));
		Departments.click();
		Thread.sleep(5000);
		logger.info("Click on Departments successful");

		String Criteria = "code";
		String code = "abcd";
		String DescriptionSearch = "Books Test";

		WebElement Search = driver.findElement(By.xpath("//input[@id='txtCSTDeptSearch']"));
		if (Criteria.equalsIgnoreCase("code")) {

			WebElement Code = driver.findElement(By.xpath("//input[@id='CSTDeptradCode']"));
			wait.until(ExpectedConditions.visibilityOf(Code));
			wait.until(ExpectedConditions.elementToBeClickable(Code));
			Code.click();
			logger.info("Code selected");
			Search.sendKeys(code); // To search customer

		} else if (Criteria.equalsIgnoreCase("Description")) {
			WebElement Description = driver.findElement(By.xpath("//input[@id='CSTDeptradDesc']"));
			wait.until(ExpectedConditions.visibilityOf(Description));
			wait.until(ExpectedConditions.elementToBeClickable(Description));
			Description.click();
			Search.sendKeys(DescriptionSearch);
			logger.info("Description selected");
		}

		driver.findElement(By.xpath("//button[@onclick='onDepatmentSearchPrivateOkClick()']")).click();
		Thread.sleep(3000);
		
		 WebElement error = driver.findElement(By.id("btnErrorBoxOk"));
		boolean isErrorMessageDisplayed = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
		if (isErrorMessageDisplayed) {
			WebElement errorText = driver.findElement(By.id("errorMsg"));
			String text = errorText.getText();
			wait.until(ExpectedConditions.elementToBeClickable(error));
			error.click();
			Thread.sleep(5000); // Consider using explicit waits instead of Thread.sleep
		//	Assert.fail("Test case failed due to error: " + text);
		} else {
			logger.info("Test case passed: No error message displayed.");
		}

	}
		public void captureError() throws InterruptedException {
			Thread.sleep(5000);
			WebElement errorMessage = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
			Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		        String actualErrorMessage = errorMessage.getText();
			if (actualErrorMessage.equals("Duplicate Department within selected Org/Site Group. Please try again.")) {
	            // Handle first error message
	            System.out.println("Handling first error message."+actualErrorMessage);
	            Assert.assertEquals(actualErrorMessage, "Duplicate Department within selected Org/Site Group. Please try again.", "Incorrect error message");
	        } else if (actualErrorMessage.equals("No records found!")) {
	            // Handle second error message
	            System.out.println("Handling second error message."+actualErrorMessage);
	            Assert.assertEquals(actualErrorMessage, "No records found!", "Incorrect error message");
	        } else {
	            // Handle other cases or unexpected errors
	            System.out.println("Unexpected error message: " + actualErrorMessage);
	        }
			WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
			error.click();
		}
	}
