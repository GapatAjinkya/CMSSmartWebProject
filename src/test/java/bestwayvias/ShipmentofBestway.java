package bestwayvias;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShipmentofBestway {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("BestWaysTest");
	String bestwaycode = "TestAG2";

	@Test(priority = 0)
	public void bestwaynew() throws InterruptedException {
		CreateBestWay();
		SearchBestWayCode("TestAG2");
		newbestway("TestAG2","TestAG2","User_Group -- User_Group");
		Showall();
		Okbutton();
	}
	@Test(priority = 1)
	public void Doshipment() throws InterruptedException {
		shipment();
		addCustomer("CMS");
		Weight();
		Rate();
		ShipViasthatqualifiedforrating();
		ShipButton();

	}
	public void CreateBestWay() throws InterruptedException {
		WebElement Configuration =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_4']")));
		Configuration.click();
		logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement carriers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_44']")));
		carriers.click();
		logger.info("Click on Carriers successful");
		Thread.sleep(5000);
		WebElement bestWayVias = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_442']")));
		wait.until(ExpectedConditions.elementToBeClickable(bestWayVias));
		bestWayVias.click();
	}

	public void SearchBestWayCode(String bestwaycode) throws InterruptedException {
		Thread.sleep(3000);
		WebElement bestWayVias = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtSearchBWSF']")));
		bestWayVias.sendKeys(bestwaycode);
		WebElement ok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='BWSFOkClick()']")));
		ok.click();
		Thread.sleep(10000);
		WebElement error = driver.findElement(By.id("btnErrorBoxOk"));
		if (error.isDisplayed()) {
			WebElement error1 = driver.findElement(By.id("errorMsg"));
			String text = error1.getText();
			logger.info("The Error is _" + text);
			driver.findElement(By.id("btnErrorBoxOk")).click();
			logger.info("This Code is Not present ");
			logger.info("Creating New Record ");
		}
		 else {
            Assert.fail("The Code is Present");
        }
    }
	public void newbestway(String bestwaycode,String Description,String org) throws InterruptedException {
		Thread.sleep(5000);
		WebElement add = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='BestWayAdd']")));
		add.click();
		Thread.sleep(5000);
		WebElement txtCodeBWF = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeBWF")));
		txtCodeBWF.sendKeys(bestwaycode);
		WebElement Des = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionBWF")));
		Des.sendKeys(Description);

		WebElement OrgSiteGroup = driver.findElement(By.id("cmbGroupBWF"));
		Select select = new Select(OrgSiteGroup);
		select.selectByVisibleText(org);

		String[] shipvia = { "ProUPS1_GND", "FEUS_FSMS_GN","LTLNR_WL_100","Holl_XA",""};
		for (String value : shipvia) {
	        WebElement checkbox = driver.findElement(By.xpath("//u[contains(.,'" + value + "')]//preceding::td[1]"));
	       checkbox.click();
	       Thread.sleep(5000);
	    }
	}
	public void Showall() {
		WebElement ShowSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='ShowAllBWF']")));
		ShowSelected.click();
	}
	public void Okbutton() throws InterruptedException {
		Thread.sleep(3000);
			WebElement okbutton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='OkClickBWF']")));
			okbutton.click();
		}
	public void shipment() throws InterruptedException {
		Thread.sleep(5000);
		WebElement Transaction = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2")));
		Transaction.click();
		Thread.sleep(3000);
		WebElement ProcessS = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_21")));
		ProcessS.click();
		Thread.sleep(5000);

		WebElement shipviaSearch = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@onclick='btnSearch_PS()']")));
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");
		Thread.sleep(2000);
		driver.findElement(By.id("txtSCSearchSS")).sendKeys(bestwaycode);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnSearchOk_PS"))).click();
		logger.info("shipvia is selected");

//		WebElement SelectShipvia = wait.until(ExpectedConditions.presenceOfElementLocated(
//				By.xpath("//select[@id='txtShipVia']//option[contains(.,'" + bestwaycode + "')]")));
//		SelectShipvia.click();

	}

	public void Weight() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Weight = driver.findElement(By.xpath("//input[@id='txtManual']"));

		Weight.sendKeys("1.00");
		logger.info("Manual Weight is fill ");
	}

	public void Rate() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Ratebutton = driver.findElement(By.xpath("//button[@id='cmdRate']"));
		Ratebutton.click();
		logger.info("Click on Rate ");
	}

	private void addCustomer(String customerName) throws InterruptedException {
		Thread.sleep(3000);
		WebElement customer = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='AddressesClick()']")));
		wait.until(ExpectedConditions.elementToBeClickable(customer));
		customer.click();
		// logger.info("Clicked on Customer");
		Thread.sleep(3000);
		WebElement searchCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSCSearch"))); // searchcustomer
		searchCustomer.sendKeys(customerName);
		WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selCutomerList")));
		Select customerList = new Select(list);
		customerList.selectByValue("1"); // To select Global
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']"))).click(); // ok
		Thread.sleep(3000);
		// logger.info("Customer Searched");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), '" + customerName + "')]"))).click();
		WebElement customerOk = driver.findElement(By.id("addressformOk"));
		customerOk.click(); // Click on OK
		Thread.sleep(3000);
		// logger.info("Customer Added");
	}
	public void ShipViasthatqualifiedforrating() throws InterruptedException {

		List<WebElement> Svqfr = driver.findElements(By.xpath("//table[@id='tblBestWayRatesList']//tr//td[1]"));

		for (WebElement ele : Svqfr)
		{
			String value = ele.getText();
			logger.info("ShipVias that qualified for rating:- "+value);
			ele.click();
			break;
	}
		Thread.sleep(5000);
		WebElement ok = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='btnOk_BestWayRatesForm']")));
		ok.click();


	List<WebElement> not = driver.findElements(By.xpath("//table[@id='tblBWRErrorList']//tr//td[1]"));

	for (WebElement elenot : not)
	{
		String value = elenot.getText();
		logger.info("ShipVias that Not qualified for rating:- "+value);
	}
}

public void ShipButton() {

		WebElement SB = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='btnShipClick']")));
		SB.click();
	}

//---------------------------
@BeforeClass
public void setup() throws InterruptedException {

	System.setProperty("webdriver.chrome.driver",
			"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
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
}

}
