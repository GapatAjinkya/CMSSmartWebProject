package archivedmanifesttest;

import java.time.Duration;
import java.util.List;

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

public class ViewAM {
	public static WebDriver driver;
	public static WebDriverWait wait;
	// Logger logger = LogManager.getLogger("ViewAM");

	@Test(priority = 0)
	public void testcase() throws InterruptedException {
		openAM();
		selectcarrier("United Parcel Service");
		Previousdays();
		Nextdays();
		ShipcodeAm("TestAG1");
		viewAm();
		shipmentid("95221");
	}
	@Test
	public void testcasetwo() throws InterruptedException {


	}

	public void Previousdays() {
		WebElement AMEPrevious = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("AMEPrevious")));
		wait.until(ExpectedConditions.elementToBeClickable(AMEPrevious)).click();

	}
	public void Nextdays() {
		WebElement AMENext = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("AMENext")));
		wait.until(ExpectedConditions.elementToBeClickable(AMENext)).click();

	}
	public void ShipcodeAm(String shipcode) throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> alldata = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='tblArchivedManifestList']//tr//td[1]")));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(shipcode))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Code not found");
		if (dataStatus) {
			//Thread.sleep(5000);
			WebElement code =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='tblArchivedManifestList']//td[contains(text(), '" + shipcode + "')]")));
			code.click();

		} else {
		    System.out.println("Code not found");
		}
		//logger.info("Customer Code Found Selected ");
	}
  public void viewAm() throws InterruptedException {
		Thread.sleep(3000);
		WebElement View = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='AMEView']")));
		View.click();
	}
  public void shipmentid(String shipid) throws InterruptedException {

	  Thread.sleep(5000);
	  List<WebElement> alldata = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='tblShipmentList']//tr//td[1]")));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(shipid))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Shipment id not found");
		if (dataStatus) {
			//Thread.sleep(5000);
			WebElement id =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='tblShipmentList']//td[contains(text(), '" + shipid + "')]")));
			id.click();

		} else {
		    System.out.println("Shipment id not found");
		}
		WebElement okship =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='cmdOk']")));
		  okship.click();
  }

	@BeforeClass
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// logger.info("Browser opend");
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

	public void openAM() throws InterruptedException {
		Thread.sleep(5000);
		WebElement transaction = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2")));
		wait.until(ExpectedConditions.elementToBeClickable(transaction)).click();
		// logger.info("Click on Transaction Menu Successful");
		Thread.sleep(5000);
		WebElement ArchivedManifests = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_25")));
		wait.until(ExpectedConditions.elementToBeClickable(ArchivedManifests)).click();
	}

	public void selectcarrier(String carriername) throws InterruptedException {
		Thread.sleep(2000);
		WebElement Carrier = driver.findElement(By.xpath("//select[@id='AMEddlCarriers']"));
		Select dropdown = new Select(Carrier);
		dropdown.selectByVisibleText(carriername);
		// logger.info(" selectcarriers successful");
	}
}
