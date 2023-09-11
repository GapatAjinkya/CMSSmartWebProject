package stCustomer;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditCustomer {
	
	@Test
	public void Search() throws InterruptedException {
	
	Logger logger = LogManager.getLogger("EditCustomer");
	ChromeOptions options = new ChromeOptions();
    WebDriverManager.chromedriver().setup();
    options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
    options.addArguments("--remote-allow-origins=*");
    WebDriver driver = new ChromeDriver(options);
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
	
	WebElement Configuration = driver.findElement(By.id("menu_item_4"));
	wait.until(ExpectedConditions.visibilityOf(Configuration));
	wait.until(ExpectedConditions.elementToBeClickable(Configuration));
	Configuration.click();
	Thread.sleep(5000);
	logger.info("Clickon Configuration successful");
	
	WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
	wait.until(ExpectedConditions.visibilityOf(SupportTables));
	wait.until(ExpectedConditions.elementToBeClickable(SupportTables));
	SupportTables.click();
	logger.info(" SupportTables Windo Open  successful");
	
	WebElement Customers = driver.findElement(By.xpath("//a[@id='menu_item_451']"));
	wait.until(ExpectedConditions.visibilityOf(Customers));
	wait.until(ExpectedConditions.elementToBeClickable(Customers));
	Customers.click();
	logger.info(" Customers Windo Open  successful");
	Thread.sleep(6000);
	
	
	//To edit the code with 
	WebElement CSTCustRadCode = driver.findElement(By.xpath("//input[@id='CSTCustRadCode']"));
	wait.until(ExpectedConditions.visibilityOf(CSTCustRadCode));
	wait.until(ExpectedConditions.elementToBeClickable(CSTCustRadCode));
	CSTCustRadCode.click();
	logger.info("Code selected");
	
   	String Searchcode ="USAG";
	
	 WebElement Search=driver.findElement(By.xpath("//input[@id='txtCSTCustSearch']"));
	 Search.sendKeys(Searchcode);
	 
	 driver.findElement(By.xpath("//button[@onclick='onCustomerSearchPrivateOkClick()']")).click();
	Thread.sleep(6000);
	 WebElement select=driver.findElement(By.xpath("//tr[@data-index='0']"));
	 select.click();
	
	 WebElement EditButton = driver.findElement(By.id("CSTCustEdit"));
		wait.until(ExpectedConditions.visibilityOf(EditButton));
		wait.until(ExpectedConditions.elementToBeClickable(EditButton));
		EditButton.click();
		Thread.sleep(6000);
		
		
		//To edit values
		String editwith="company";             //code,company
		String Editcode="";
		String EditCompany="TestAG5";
		
		if(editwith.equalsIgnoreCase("code")) {
			
		WebElement CustomerCode = driver.findElement(By.id("CFtxtXRef"));
		wait.until(ExpectedConditions.elementToBeClickable(CustomerCode));
		CustomerCode.clear();
		CustomerCode.sendKeys(Editcode);
		
		}else if(editwith.equalsIgnoreCase("company")) {
		WebElement Company = driver.findElement(By.id("CFtxtName"));
		wait.until(ExpectedConditions.elementToBeClickable(Company));
		Company.clear();
		Company.sendKeys(EditCompany);
		}
		
		try {
			driver.findElement(By.xpath("//button[@onclick='OkClickCustomerForm()']")).click();    
	    } catch (Exception e) {
	        Assert.fail("Failed to click on _SearchCarton. Exception: " + e.getMessage());
	    }
}
}
	