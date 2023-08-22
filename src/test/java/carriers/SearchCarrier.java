package carriers;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchCarrier {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("SearchCarrier");

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

	@AfterMethod
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}

	@Test
	public void Search() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");

		WebElement carriers = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_44']")));

		carriers.click();
		Thread.sleep(5000);
		logger.info("Click on Carriers successful");

		WebElement carriersaccount = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_440']")));

		carriersaccount.click();
		logger.info("Click on carriers account successful");
		Thread.sleep(5000);
	
		
		WebElement Search = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("CASFtxtSearch")));
		
		String Criteria="code";
	    String code="UPSAP_CS";
	    String Accountno="";
	    String Description="FedEx US FSMS";
	    
	    WebElement Searchbox = driver.findElement(By.xpath("//input[@id='CASFtxtSearch']"));
		if(Criteria.equalsIgnoreCase("code")) {
			Thread.sleep(3000);
			WebElement Code = driver.findElement(By.xpath("//input[@id='CASFradCode']"));
			wait.until(ExpectedConditions.visibilityOf(Code));
			wait.until(ExpectedConditions.elementToBeClickable(Code));
			Code.click();
			logger.info("Code selected");
			Search.sendKeys(code);                                 //To search customer
			
		}else if(Criteria.equalsIgnoreCase("Description")) {
			Thread.sleep(3000);
			WebElement Dis = driver.findElement(By.xpath("//input[@id='CASFradDescription']"));
			wait.until(ExpectedConditions.visibilityOf(Dis));
			wait.until(ExpectedConditions.elementToBeClickable(Dis));
			Dis.click();
			Search.sendKeys(Description);    
			logger.info("CustomerName selected");
		}
			else if(Criteria.equalsIgnoreCase("CustomerName")) {
				Thread.sleep(3000);
				WebElement ACCOUNTNUMBER = driver.findElement(By.xpath("//input[@id='CASFradAccountNo']"));
				wait.until(ExpectedConditions.visibilityOf(ACCOUNTNUMBER));
				wait.until(ExpectedConditions.elementToBeClickable(ACCOUNTNUMBER));
				ACCOUNTNUMBER.click();
				Search.sendKeys(Accountno);    
				logger.info("CustomerName selected");
}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='CASFOkClick()']")).click();
}}