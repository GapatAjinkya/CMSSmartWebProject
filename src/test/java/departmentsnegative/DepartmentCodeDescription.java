package departmentsnegative;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DepartmentCodeDescription {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("DepartmentCodeDescription");


	@Test(priority = 0)
	public void TestCode() throws InterruptedException {
		departments();
		search("");
		Add();
		Depcode("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/1");
		DepDescription("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquov");
		okbutton();
		captureError();
	}

	@Test(priority = 1)
	public void TestCodeBlanck() throws InterruptedException {
		Depcode("");
		okbutton();
		captureError();
	}

	@Test(priority = 2)
	public void TestDescription() throws InterruptedException {
		Depcode("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquo");
		DepDescription("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovBaasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquov");
		okbutton();
		captureError();

	}
	@Test(priority = 3)
	public void TestDescriptionBlanck() throws InterruptedException {
		Depcode("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquo");
		DepDescription("");
		okbutton();
		captureError();
	}
	public void Depcode(String code) throws InterruptedException {
		Thread.sleep(4000);
		WebElement DepartmentCode = driver.findElement(By.id("DFtxtCode"));
		DepartmentCode.clear();
		DepartmentCode.sendKeys(code);
	}
	public void DepDescription(String Description) throws InterruptedException {
		Thread.sleep(4000);
		WebElement Descriptiontxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DFtxtDescription")));
		Descriptiontxt.clear();
		Descriptiontxt.sendKeys(Description);
	}
	public void Add() throws InterruptedException {
		Thread.sleep(2000);
		WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CSTDeptAdd")));
		addButton.click();
	}
	public void okbutton() {
		WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='OkClickDepartmentForm()']")));
		okButton.click();
	}
	public void search(String code) throws InterruptedException {
		WebElement Search = driver.findElement(By.xpath("//input[@id='txtCSTDeptSearch']"));
		Search.sendKeys(code);
		driver.findElement(By.xpath("//button[@onclick='onDepatmentSearchPrivateOkClick()']")).click();// ok buttton
		Thread.sleep(5000);
	}
	public void departments() throws InterruptedException {
		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");
		WebElement SupportTables = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#menu_item_45")));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");
		Thread.sleep(3000);
		WebElement Departments =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_452")));
		Departments.click();
		Thread.sleep(5000);
		logger.info("Click on Departments successful");
	}
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
	        String actualErrorMessage = errorMessage.getText();

		if (actualErrorMessage.equals("Value should be less than 50 characters. Please try again.")) {
            System.out.println("Handling first error message."+actualErrorMessage);
            Assert.assertEquals(actualErrorMessage, "Value should be less than 50 characters. Please try again.", "Incorrect error message");
        } else if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
        	 System.out.println("Handling Second error message."+actualErrorMessage);
            Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.", "Incorrect error message");
        } else if (actualErrorMessage.equals("Value should be less than 100 characters. Please try again.")) {
        	 System.out.println("Handling Third error message."+actualErrorMessage);
            Assert.assertEquals(actualErrorMessage, "Value should be less than 100 characters. Please try again.", "Incorrect error message");
        } else {
            System.out.println("Unexpected error message: " + actualErrorMessage);
        }
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
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

}
