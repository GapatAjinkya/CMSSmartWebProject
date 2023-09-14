package departments;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Taskdepartments {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Taskdepartments");

	@BeforeMethod
	public void setup() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
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

	@AfterMethod
	public void teardown() throws InterruptedException {

		Thread.sleep(20000);
		driver.close();
	}

	@Test
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

		WebElement Departments = driver.findElement(By.id("menu_item_452"));
		wait.until(ExpectedConditions.visibilityOf(Departments));
		wait.until(ExpectedConditions.elementToBeClickable(Departments));
		Departments.click();
		Thread.sleep(5000);
		logger.info("Click on Departments successful");

//		WebElement Search = driver.findElement(By.xpath("//input[@id='txtCSTDeptSearch']"));
//		Search.sendKeys("asdsad");

		WebElement clickok = driver.findElement(By.xpath("//button[@onclick='onDepatmentSearchPrivateOkClick()']"));
		wait.until(ExpectedConditions.visibilityOf(clickok));
		wait.until(ExpectedConditions.elementToBeClickable(clickok));
		clickok.click();
		Thread.sleep(5000);

//		WebElement error=driver.findElement(By.id("btnErrorBoxOk"));
//		wait.until(ExpectedConditions.visibilityOf(error));
//		wait.until(ExpectedConditions.elementToBeClickable(error));
//		error.click();
//		logger.info(" Clicked on No records found");

		Thread.sleep(5000);
		List<WebElement> rowsnum= driver.findElements(By.xpath("//table[@id='tblCSTDepartmentList']//tr"));
		   System.out.println("Number of rows -"+rowsnum.size());

		   List<WebElement> alldata= driver.findElements(By.xpath("//table[@id='tblCSTDepartmentList']//td"));

		   boolean dataStatus=false;
		   for (WebElement ele : alldata) {
		       String value = ele.getText();
//		       System.out.println(value);

		       if (value.contains("Department3")) {
		    	   System.out.println(value);
		           ele.click();
		    	   dataStatus=true;
		           break;
		       }
		   }
		   Assert.assertTrue(dataStatus, "Department not found!");
	}
}