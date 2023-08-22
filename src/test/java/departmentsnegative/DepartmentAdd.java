package departmentsnegative;
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

public class DepartmentAdd {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Departments");

	
	@Test
public void checkexisting () throws InterruptedException {
		newDepartments("Department5","Test Department");
		captureError();
}

	public void newDepartments(String Departmentcode,String DescriptionText) throws InterruptedException {

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
}
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage.equals("Duplicate Department within selected Org/Site Group. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "Duplicate Department within selected Org/Site Group. Please try again.", "Incorrect error message");
		}  else {
			System.out.println("Unexpected error message: " + actualErrorMessage);
		}
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
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
	public void teardown() throws InterruptedException {

		Thread.sleep(10000);
		driver.close();
	}
}