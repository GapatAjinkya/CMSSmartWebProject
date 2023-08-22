package bestwayviasNegative;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class BestwayviasSearch {

	public static WebDriver driver;
	public static WebDriverWait wait;
//	Logger logger = LogManager.getLogger("BestwayviasSearch");
	@Test
	public void testcase() throws InterruptedException {
		CreateBestWay();
		SearchBestWayCodeNegative("TestAG6");
	}
	public void SearchBestWayCodeNegative(String bestwaycode) throws InterruptedException {
		
	    Thread.sleep(5000);
	    WebElement bestWayVias = wait
	            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtSearchBWSF']")));
	    bestWayVias.sendKeys(bestwaycode);
	    WebElement ok = wait
	            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='BWSFOkClick()']")));
	    ok.click();
	    Thread.sleep(10000);  
	    try {
	        WebElement error = driver.findElement(By.id("btnErrorBoxOk"));
	        if (error.isDisplayed()) {
	            WebElement error1 = driver.findElement(By.id("errorMsg"));
	            String text = error1.getText();
	       //     logger.info("The Error is _" + text);
	            driver.findElement(By.id("btnErrorBoxOk")).click();
	          //  logger.info("This Code is Not present as expected");
	        }
	    } catch (NoSuchElementException e) {
	
	     //   logger.error("Expected error message was not displayed.");
	        Assert.fail("Expected error message was not displayed.");
	    }
	}
	public void CreateBestWay() throws InterruptedException {
		WebElement Configuration =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
	//	logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement carriers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_44']")));
		wait.until(ExpectedConditions.elementToBeClickable(carriers));
		carriers.click();
	//	logger.info("Click on Carriers successful");
		Thread.sleep(5000);
		WebElement bestWayVias = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_442']")));
		wait.until(ExpectedConditions.elementToBeClickable(bestWayVias));
		bestWayVias.click();
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
	//	logger.info("Browser opend");
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
	public void teardown() {
		driver.quit();
		
	}
	
}
