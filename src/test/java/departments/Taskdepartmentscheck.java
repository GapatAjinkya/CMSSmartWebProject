package departments;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Taskdepartmentscheck {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Taskdepartmentscheck");

	@BeforeMethod
	public void setup() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 10);

		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(5000);
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

		Thread.sleep(15000);
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
		Thread.sleep(8000);
		WebElement Departments = driver.findElement(By.id("menu_item_452"));
		wait.until(ExpectedConditions.visibilityOf(Departments));
		wait.until(ExpectedConditions.elementToBeClickable(Departments));
		Departments.click();
		Thread.sleep(8000);
		logger.info("Click on Departments successful");
		
		WebElement Search = driver.findElement(By.xpath("//input[@id='txtCSTDeptSearch']"));
		Search.sendKeys("asdsad");
		Thread.sleep(8000);
		
		driver.findElement(By.xpath("//button[@onclick='onDepatmentSearchPrivateOkClick()']")).click();// ok buttton
		Thread.sleep(6000);
		
		WebElement error=driver.findElement(By.id("btnErrorBoxOk"));
		boolean errortab=driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
		Thread.sleep(6000);
		if(errortab)
		{
			WebElement errorText=driver.findElement(By.id("errorMsg"));
			String text=errorText.getText();
			wait.until(ExpectedConditions.visibilityOf(error));
			wait.until(ExpectedConditions.elementToBeClickable(error));
			error.click();
			logger.info("Test case Fail Because- "+text);
			Thread.sleep(5000);
		}else {
			logger.info("Department Search ");
		}
		Thread.sleep(6000);
		WebElement addbutton = driver.findElement(By.id("CSTDeptAdd"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CSTDeptAdd")));
		wait.until(ExpectedConditions.visibilityOf(addbutton));
		wait.until(ExpectedConditions.elementToBeClickable(addbutton));
		addbutton.click();
		logger.info("Click on add button successful");
		
		String Departmentcode = "DepartmentBOl";
		String DescriptionText = "Test Department for BOL";
		Thread.sleep(5000);
		
		WebElement codeDepartment = driver.findElement(By.id("DFtxtCode"));
		wait.until(ExpectedConditions.visibilityOf(codeDepartment));
		wait.until(ExpectedConditions.elementToBeClickable(codeDepartment));
		codeDepartment.sendKeys(Departmentcode);       //Department code 
		logger.info("Code selected  successful");
		Thread.sleep(5000);
		
		WebElement Description = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("DFtxtDescription")));;
		wait.until(ExpectedConditions.visibilityOf(Description));
		wait.until(ExpectedConditions.elementToBeClickable(Description));
		Description.sendKeys(DescriptionText);     // Description
		logger.info("Description Selected successful");
	
		Thread.sleep(5000);
		WebElement clickok = driver.findElement(By.xpath("//button[@onclick='OkClickDepartmentForm()']"));
		wait.until(ExpectedConditions.visibilityOf(clickok));
		wait.until(ExpectedConditions.elementToBeClickable(clickok));	
		clickok.click();

		Thread.sleep(5000);
		 boolean isButtonVisible = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
		 
		 if (isButtonVisible)
		 {
			 WebElement text=driver.findElement(By.id("errorMsg"));
			 String errortext=text.getText();
			 WebElement button = driver.findElement(By.id("btnErrorBoxOk"));
	            button.click();
	            
	            Assert.fail("Test case Fail Because -- "+errortext);
		 }else {
			 
			 logger.info("Department  Selected successful"+"Test case Pass");
			
		 }	
		
	}
}