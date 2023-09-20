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

public class CustomerPS {
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

	Thread.sleep(10000);

	WebElement Configuration = driver.findElement(By.xpath("//a[@id='menu_item_4']"));
	Configuration.click();
	Thread.sleep(5000);
	logger.info("Clickon Configuration successful");

	WebElement SupportTables = driver.findElement(By.xpath("//a[@id='menu_item_45']"));
	SupportTables.click();
	logger.info(" SupportTables Windo Open  successful");

	WebElement Customers = driver.findElement(By.xpath("//a[@id='menu_item_451']"));
	Customers.click();
	logger.info(" Customers Windo Open  successful");
	Thread.sleep(6000);
	WebElement CustomerOkClick = driver.findElement(By.xpath("//button[@onclick='onCustomerSearchPrivateOkClick()']"));
	CustomerOkClick.click();
	logger.info(" Customers Windo Open  successful");
	Thread.sleep(3000);
	WebElement AddCustomer = driver.findElement(By.id("CSTCustAdd"));
	AddCustomer.click();
	logger.info(" Click on add Customer ");
	Thread.sleep(10000);
	String newcode="USAG2";      // Change the code as per Requirement
	WebElement CustomerCode = driver.findElement(By.id("CFtxtXRef"));
	CustomerCode.sendKeys(newcode);   //For the new customer code
	WebElement Company = driver.findElement(By.id("CFtxtName"));
	Company.sendKeys("TestAG1");
	WebElement Contact = driver.findElement(By.id("CFtxtContact"));
	Contact.sendKeys("Ajinkya");

	driver.findElement(By.id("CFtxtAddress1")).sendKeys("Testing1");
	driver.findElement(By.id("CFtxtAddress2")).sendKeys("Testing1");
	driver.findElement(By.id("CFtxtAddress3")).sendKeys("Testing1");

	driver.findElement(By.id("CFtxtAddCity")).sendKeys("US");
	driver.findElement(By.id("CFtxtAddState")).sendKeys("US");
	driver.findElement(By.id("CFtxtAddZip")).sendKeys("23456");

	String desiredText ="UNITED STATES";
	WebElement dropdown = driver.findElement(By.id("CFtxtCountry"));
	Select select = new Select(dropdown);
	select.selectByVisibleText(desiredText);
	logger.info(" Customers Address  successful");

	driver.findElement(By.id("CFtxtAddPhone")).sendKeys("18600145361");
	driver.findElement(By.id("CFtxtFax")).sendKeys("555");
	driver.findElement(By.id("CFtxtEmail")).sendKeys("test@gamil.com");

	try {
		driver.findElement(By.xpath("//button[@onclick='OkClickCustomerForm()']")).click();

    } catch (Exception e) {
        Assert.fail("Failed to click on _SearchCarton. Exception: " + e.getMessage());

    }
	logger.info(" New Customer Create successful");

//--------------------

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
	Thread.sleep(15000);

	Thread.sleep(15000);
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
	wait.until(ExpectedConditions.elementToBeClickable(okbutton));
	okbutton.click();
	logger.info("shipvia is selected");



	WebElement click = driver.findElement(By.xpath("//input[@id='txtCustomerCode']"));
	wait.until(ExpectedConditions.visibilityOf(click));
	wait.until(ExpectedConditions.elementToBeClickable(click));
	click.click();

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

	//
	WebElement searchcustomer = driver.findElement(By.id("txtSCSearch")); // searchcustomer
	searchcustomer.sendKeys(newcode); //----new customer code

	WebElement List = driver.findElement(By.id("selCutomerList"));
	Select CustomerList = new Select(List);
	CustomerList.selectByValue("1"); // To select Global
	Thread.sleep(3000);

	driver.findElement(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")).click(); // click on ok
	logger.info("Customer Searched");
	Thread.sleep(5000);

WebElement selectcustomer=	driver.findElement(By.xpath("//tr[@data-index='0']")); // to select customer
wait.until(ExpectedConditions.visibilityOf(selectcustomer));
wait.until(ExpectedConditions.elementToBeClickable(selectcustomer));
selectcustomer.click();

	WebElement Customerok= driver.findElement(By.id("addressformOk"));
	Customerok.click();          // Click on OK
	Thread.sleep(3000);
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