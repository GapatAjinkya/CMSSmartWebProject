package billoflading;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BillofLading

{
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("BillofLading");
	@BeforeMethod
	public void setup() throws InterruptedException {

	
	    System.setProperty("webdriver.chrome.driver", "E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
	     wait = new WebDriverWait(driver, Duration.ofSeconds(30));

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}

	@Test
	public void Bolcreate() throws InterruptedException

	{
		WebElement Transaction= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2"))); 	// To click on Transaction
		Transaction.click();
		Thread.sleep(3000);
		WebElement BOL= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_26"))); 	// To click on Transaction
		BOL.click();
		Thread.sleep(5000);

		WebElement dropdown = driver.findElement(By.xpath("//select[@id='BOLddlCarriers']"));
		String Carriers = "LTLR_WL -- LTL Rated WorldLink";
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			// Check the visible text of each option
			String visibleText = option.getText();
			if (visibleText.equalsIgnoreCase(Carriers))
			{
				select.selectByVisibleText(visibleText);
				break;
			}
		}
		WebElement Create= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='BOLCreate']"))); 	// To click on Transaction
		Create.click();
		Thread.sleep(5000);
		logger.info("Click on  Transaction");
		WebElement confirmbutton= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='btnConfirmBoxOk']")));
		confirmbutton.click();
		Thread.sleep(5000);
		logger.info("Click on  confirm button");

		String code="TestBOL";              // New Code
		String Comapny="TestBOL";
		String Contact="AG";
		String Address1="123",Address2="123";
		String  City="lorton",State="VA",Zip="22079";
		String Country="UNITED STATES",FreightTerms="C&F/CPT - Cost/Freight",
				Department="Test--VA Shipping department",
		ShipVia="LTLR_WL_100--LTLR_WL_Class 100";   // This Shipvia is changed when we select Rated/non rated
		String payment="Prepaid";
		String Countrypayment="UNITED STATES";

	String	Instructions="Explosive22";
		WebElement CodeBol= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCode")));
		CodeBol.sendKeys(code);
		Thread.sleep(5000);
		logger.info("Enter Code Succesful");
		WebElement CompanyBol= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCompany")));
		CompanyBol.sendKeys(Comapny);
		logger.info("Comapny Succesful");
		WebElement Contactbol= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtContact")));
		Contactbol.sendKeys(Contact);
		logger.info("Comapny Succesful");
		WebElement address1= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtAddress1")));
		address1.sendKeys(Address1);

		logger.info("Address1 Succesful");

		WebElement address2= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtAddress2")));
		address2.sendKeys(Address2);

		logger.info("Address2 Succesful");

		WebElement city= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCity")));
		city.sendKeys(City);

		logger.info("City Succesful");

		WebElement state= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtState")));
		state.sendKeys(State);

		logger.info("state Succesful");

		WebElement zip= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtZip")));
		zip.sendKeys(Zip);
		Thread.sleep(3000);
		logger.info("state Succesful");

		WebElement country= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CDBOLCountry")));
		Select selectcountry=new Select(country);
		selectcountry.selectByVisibleText(Country);
		logger.info("country selected Succesful");

		WebElement freightTerms= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ddlFreightTerms")));
		Select selectFreightTerms=new Select(freightTerms);
		selectFreightTerms.selectByVisibleText(FreightTerms);
		logger.info("freight Terms selected Succesful");

		WebElement department= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ddlDepartment")));
		Select selectdepartment=new Select(department);
		selectdepartment.selectByVisibleText(Department);
		logger.info("department selected Succesful");

		Thread.sleep(3000);
		WebElement shipVias= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ddlShipVia")));
		Select selectShipVia=new Select(shipVias);
		selectShipVia.selectByVisibleText(ShipVia);
		logger.info("ShipVia selected Succesful");

		WebElement Payment= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ddlPayment")));
		Select selectpayment=new Select(Payment);
		selectpayment.selectByVisibleText(payment);
		logger.info("Payment selected Succesful");

		WebElement countrypayment= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("PIBOLCountry")));
		Select selectCountrypayment=new Select(countrypayment);
		selectCountrypayment.selectByVisibleText(Countrypayment);
		logger.info("Country selected Succesful");

		WebElement instructions= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnInstructions")));
		instructions.click();
		logger.info("click on instructions selected Succesful");
		Thread.sleep(5000);

		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblInstructionsList']//td"));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();

			if (value.equals(Instructions))
			{
				System.out.println(value);

				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Instructions not found");
		if (dataStatus) {
			Thread.sleep(6000);
			WebElement instructionsElement = driver.findElement(By.xpath("//table[@id='tblInstructionsList']//td[contains(text(), '" + Instructions + "')]"));
			instructionsElement.click();

		} else {
		    System.out.println("instructions not found");
		}

		driver.findElement(By.id("btnInstructionsOk")).click();
		Thread.sleep(6000);

		WebElement btnCreateBOL= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnCreateBOL")));
		btnCreateBOL.click();
		logger.info("Create BOL ok selected Succesful");
		Thread.sleep(10000);

		WebElement Button=driver.findElement(By.xpath("//button[@class='WLButton text-uppercase']"));
		boolean ok =Button.isDisplayed();

		assert ok;
		if(ok)
		{
			Button.click();
			logger.info("click on confirmation box  Succesful");

		}
	}
}