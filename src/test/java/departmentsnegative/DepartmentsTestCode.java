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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DepartmentsTestCode {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("CartonsCodeTest");


@Test
	public void SearchTest() throws InterruptedException {
	searchdepartment();
	captureError();
	}
private void searchdepartment() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(3000);
		logger.info("Click on Configuration successful");
		WebElement SupportTables = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#menu_item_45")));
		wait.until(ExpectedConditions.elementToBeClickable(SupportTables));
		SupportTables.click();
		Thread.sleep(3000);
		logger.info(" SupportTables Windo Open  successful");
		WebElement Departments = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_452")));
		wait.until(ExpectedConditions.elementToBeClickable(Departments));
		Departments.click();
		Thread.sleep(3000);
		logger.info("Click on Departments successful");
		String Criteria="code";
	    String code="abcd";
	    String DescriptionSearch="Books Test";
	    WebElement Search=driver.findElement(By.xpath("//input[@id='txtCSTDeptSearch']"));
		if(Criteria.equalsIgnoreCase("code")) {
			WebElement Code = driver.findElement(By.xpath("//input[@id='CSTDeptradCode']"));
			wait.until(ExpectedConditions.visibilityOf(Code));
			Code.click();
			logger.info("Code selected");
			Search.sendKeys(code);                                 //To search customer
		}else if(Criteria.equalsIgnoreCase("Description")) {
			WebElement Description = driver.findElement(By.xpath("//input[@id='CSTDeptradDesc']"));
			wait.until(ExpectedConditions.visibilityOf(Description));
			wait.until(ExpectedConditions.elementToBeClickable(Description));
			Description.click();
			Search.sendKeys(DescriptionSearch);
			logger.info("Description selected");
	}
		driver.findElement(By.xpath("//button[@onclick='onDepatmentSearchPrivateOkClick()']")).click();
	}

public void captureError() throws InterruptedException {
	Thread.sleep(5000);
	WebElement errorMessage = wait
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
	Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
	String actualErrorMessage = errorMessage.getText();
	if (actualErrorMessage.equals("No records found!")) {
		System.out.println("Handling error message." + actualErrorMessage);
		Assert.assertEquals(actualErrorMessage, "No records found!", "Incorrect error message");
	}  else {
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
	  @AfterClass
	  public void teardown() {
	//   driver.quit();

	  }
}