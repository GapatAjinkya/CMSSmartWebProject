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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Customerinps {

	@Test
	public void pswithcustomer() throws InterruptedException {

	Logger logger = LogManager.getLogger("Customerinps");
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

//------------------------------------------------------

	WebElement Transaction = driver.findElement(By.id("menu_item_2"));
	wait.until(ExpectedConditions.visibilityOf(Transaction));
	wait.until(ExpectedConditions.elementToBeClickable(Transaction));
	Transaction.click();
	logger.info("click on Transaction ");

	Thread.sleep(5000);
	WebElement ProcessShipmentS = driver.findElement(By.id("menu_item_21"));
	wait.until(ExpectedConditions.visibilityOf(ProcessShipmentS));
	wait.until(ExpectedConditions.elementToBeClickable(ProcessShipmentS));
	ProcessShipmentS.click();
	logger.info("click on Process Shipment");

	Thread.sleep(10000);
	//WebElement shipviaSearch = driver.findElement(By.xpath("//*[@onclick='btnSearch_PS()']"));
	WebElement shipviaSearch = driver.findElement(By.xpath("//body/div[@id='mainContaint']/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/form[1]/div[1]/div[3]/span[1]"));
	wait.until(ExpectedConditions.visibilityOf(shipviaSearch));
	wait.until(ExpectedConditions.elementToBeClickable(shipviaSearch));
	shipviaSearch.click();
	logger.info("click on shipviaSearch ");

	Thread.sleep(5000);
	WebElement ShipviaSearchcode = driver.findElement(By.xpath("//input[@id='radCodeSS']"));
	wait.until(ExpectedConditions.visibilityOf(ShipviaSearchcode));
	wait.until(ExpectedConditions.elementToBeClickable(ShipviaSearchcode));
	ShipviaSearchcode.click();

	driver.findElement(By.id("txtSCSearchSS")).sendKeys("ProUPS1_GND");

	WebElement okbutton = driver.findElement(By.xpath("//button[@id='btnSearchOk_PS']"));
	wait.until(ExpectedConditions.visibilityOf(okbutton));
	okbutton.click();
	logger.info("shipvia is selected");
	WebElement customer = driver.findElement(By.xpath("//button[@onclick='AddressesClick()']"));
	wait.until(ExpectedConditions.visibilityOf(customer));
	wait.until(ExpectedConditions.elementToBeClickable(customer));
	customer.click();
	logger.info("Clicked on Customer");

	Thread.sleep(5000);
	WebElement customercode = driver.findElement(By.xpath("//input[@id='radCode']")); // customercode
	wait.until(ExpectedConditions.visibilityOf(customercode));
	wait.until(ExpectedConditions.elementToBeClickable(customercode));
	customercode.click();

	String newcode="USAG";
	WebElement searchcustomer = driver.findElement(By.id("txtSCSearch")); // searchcustomer
	searchcustomer.sendKeys(newcode); //----new customer code

	WebElement List = driver.findElement(By.id("selCutomerList"));
	Select CustomerList = new Select(List);
	CustomerList.selectByValue("1"); // To select Global
	driver.findElement(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")).click(); // click on ok
	Thread.sleep(10000);
	logger.info("Customer Searched");
	Thread.sleep(5000);

	driver.findElement(By.xpath("//tr[@data-index='0']")).click();  // to select customer

	WebElement Customerok= driver.findElement(By.id("addressformOk"));
	Customerok.click();          // Click on OK
	logger.info("Customer Added");

	WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
	ManualWeight.sendKeys("1.00");
	logger.info("Manual Weight is fill ");

	try {

		WebElement ShipButton = driver.findElement(By.id("btnShipClick")); // customercode
		wait.until(ExpectedConditions.visibilityOf(ShipButton));
		wait.until(ExpectedConditions.elementToBeClickable(ShipButton));
		ShipButton.click();

    } catch (Exception e) {
        Assert.fail("Failed to click on _ShipButton. Exception: " + e.getMessage());

    }

	logger.info(" Shipment is Done");
	Thread.sleep(10000);
	 WebElement element=driver.findElement(By.xpath("//*[@id=\"shipmentHistoryTable\"]/tbody/tr/td[4]/a/u"));
	 String text=element.getText();
	 System.out.println("this is the company ---"+text);
	 String company="TestAG1";
	 Assert.assertEquals(company, text);
}
}