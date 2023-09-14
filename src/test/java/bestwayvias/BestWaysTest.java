package bestwayvias;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BestWaysTest {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("BestWaysTest");

	@Test
	public void bestwaynew() throws InterruptedException {
		CreateBestWay();
		SearchBestWayCode("TestAG1");
		newbestway("TestAG1","TestAG1","VA_UG -- Virginia User Group");
		Showall();
		Okbutton();
	}
	public void CreateBestWay() throws InterruptedException {
		WebElement Configuration =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement carriers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_44']")));
		wait.until(ExpectedConditions.elementToBeClickable(carriers));
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

		String[] shipvia = { "DHL_PRo12_DX", "ProUPS1_GND","LTLNR_WL_100","FMD-DHLPPGround",""};
		for (String value : shipvia) {
	        WebElement checkbox = driver.findElement(By.xpath("//u[contains(.,'" + value + "')]//preceding::td[1]"));
	       checkbox.click();
	       Thread.sleep(5000);
	    }
	}
	public void Showall() {
		WebElement ShowAllBWF = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='ShowAllBWF']")));
		ShowAllBWF.click();
	}
public void Okbutton() throws InterruptedException {
	Thread.sleep(3000);
		WebElement okbutton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='OkClickBWF']")));
		okbutton.click();
	}
	public void Transaction() throws InterruptedException {
		Thread.sleep(5000);
		WebElement Transaction = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2")));
		Transaction.click();
	}
	public void ProcessShipment() {
		WebElement ProcessS = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_21")));
		ProcessS.click();
	}
	public void shipvia() throws InterruptedException {
		Thread.sleep(5000);
		WebElement shipviaSearch = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@onclick=\"btnSearch_PS()\"]")));
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");
	}
	public void shipviacode(String Shiviacode) throws InterruptedException {
		Thread.sleep(10000);
		WebElement ShipviaSearchcode = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id=\"radCodeSS\"]")));
		ShipviaSearchcode.click();
		driver.findElement(By.id("txtSCSearchSS")).sendKeys(Shiviacode);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnSearchOk_PS"))).click();
		logger.info("shipvia is selected");
	}
	private void addCustomer(String customerName) throws InterruptedException {
		WebElement customer = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='AddressesClick()']")));
		wait.until(ExpectedConditions.elementToBeClickable(customer));
		customer.click();
		// logger.info("Clicked on Customer");
		Thread.sleep(5000);
		WebElement searchCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSCSearch"))); // searchcustomer
		searchCustomer.sendKeys(customerName);
		WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selCutomerList")));
		Select customerList = new Select(list);
		customerList.selectByValue("1"); // To select Global
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']"))).click();																												// ok
		Thread.sleep(5000);
		// logger.info("Customer Searched");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), '" + customerName + "')]"))).click();
		WebElement customerOk = driver.findElement(By.id("addressformOk"));
		customerOk.click(); // Click on OK
		Thread.sleep(5000);
		// logger.info("Customer Added");
	}
	@BeforeClass
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

}
