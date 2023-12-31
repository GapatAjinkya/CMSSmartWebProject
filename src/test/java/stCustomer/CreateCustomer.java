package stCustomer;

import java.time.Duration;

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
import org.testng.annotations.Test;

public class CreateCustomer {
	String Customer ="USAG";
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("CreateCustomer");
	@Test
	public void Customeradd() throws InterruptedException {

		    System.setProperty("webdriver.chrome.driver", "E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
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
	WebElement CustomerOkClick = driver.findElement(By.xpath("//button[@onclick='onCustomerSearchPrivateOkClick()']"));
	wait.until(ExpectedConditions.visibilityOf(CustomerOkClick));
	wait.until(ExpectedConditions.elementToBeClickable(CustomerOkClick));
	CustomerOkClick.click();
	logger.info(" Customers Windo Open  successful");
	Thread.sleep(3000);
	WebElement error2=driver.findElement(By.id("btnErrorBoxOk"));
	boolean errortab2=wait
			.until(ExpectedConditions.presenceOfElementLocated(By.id("btnErrorBoxOk"))).isDisplayed();
	if(errortab2)
	{
		WebElement errorText=driver.findElement(By.id("errorMsg"));
		String text=errorText.getText();
		wait.until(ExpectedConditions.visibilityOf(error2));
		wait.until(ExpectedConditions.elementToBeClickable(error2));
		error2.click();
		logger.info("Test case Fail Because- "+text);
		Thread.sleep(5000);
//		Assert.fail("Test case failed due to error: " + text);
	}else {
		 logger.info("Test case passed: No error message displayed.");
	}
	Thread.sleep(3000);
	WebElement AddCustomer = driver.findElement(By.id("CSTCustAdd"));
	wait.until(ExpectedConditions.visibilityOf(AddCustomer));
	wait.until(ExpectedConditions.elementToBeClickable(AddCustomer));
	AddCustomer.click();
	logger.info(" Click on add Customer ");
	Thread.sleep(5000);

	WebElement CustomerCode = driver.findElement(By.id("CFtxtXRef"));
	wait.until(ExpectedConditions.elementToBeClickable(CustomerCode));
	CustomerCode.sendKeys(Customer);     //Code

	WebElement Company = driver.findElement(By.id("CFtxtName"));
	wait.until(ExpectedConditions.elementToBeClickable(Company));
	Company.sendKeys("TestAG1");

	WebElement Contact = driver.findElement(By.id("CFtxtContact"));
	wait.until(ExpectedConditions.elementToBeClickable(Company));
	Contact.sendKeys("Ajinkya");

	driver.findElement(By.id("CFtxtAddress1")).sendKeys("Testing1");
	driver.findElement(By.id("CFtxtAddress2")).sendKeys("Testing1");
	driver.findElement(By.id("CFtxtAddress3")).sendKeys("Testing1");

	driver.findElement(By.id("CFtxtAddCity")).sendKeys("Toronto");
	driver.findElement(By.id("CFtxtAddState")).sendKeys("Ontario");
	driver.findElement(By.id("CFtxtAddZip")).sendKeys("23456");

	String desiredText ="UNITED STATES";
	WebElement dropdown = driver.findElement(By.id("CFtxtCountry"));
	Select select = new Select(dropdown);
	select.selectByVisibleText(desiredText);
	logger.info(" Customers Address  successful");

	driver.findElement(By.id("CFtxtAddPhone")).sendKeys("555-555-5555");
	driver.findElement(By.id("CFtxtFax")).sendKeys("");
	driver.findElement(By.id("CFtxtEmail")).sendKeys("test@gamil.com");

	try {
		driver.findElement(By.xpath("//button[@onclick='OkClickCustomerForm()']")).click();

    } catch (Exception e) {
        Assert.fail("Failed to click on _SearchCarton. Exception: " + e.getMessage());
    }
	logger.info(" New Customer Create successful");

	Thread.sleep(4000);
	WebElement error=driver.findElement(By.id("btnErrorBoxOk"));
	boolean errortab=wait
			.until(ExpectedConditions.presenceOfElementLocated(By.id("btnErrorBoxOk"))).isDisplayed();
	if(errortab)
	{
		WebElement errorText=driver.findElement(By.id("errorMsg"));
		String text=errorText.getText();
		wait.until(ExpectedConditions.visibilityOf(error));
		wait.until(ExpectedConditions.elementToBeClickable(error));
		error.click();
		logger.info("Test case Fail Because- "+text);
		Thread.sleep(5000);
		Assert.fail("Test case failed due to error: " + text);
	}else {
		 logger.info("Test case passed: No error message displayed.");
	}
	}

}