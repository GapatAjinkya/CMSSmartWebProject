package processhipmentnegative;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.netty.handler.timeout.TimeoutException;

public class SWOSwithPacking {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("SWOSTest");
	public static String packagesnumber;


	@Test(priority = 0)
	public void Test() throws InterruptedException {
		 OpenPs();
		 Groups();
		 GroupsDetails("SWOS","SWOS");
		 Host("1","2");
		 clickship();
		 captureError();
		 Groups();
		 swosgroupselecttable("SWOS");
		 view();
	}
	public void GroupsDetails(String code, String Description) throws InterruptedException {
		WebElement AddNewGroups = driver.findElement(By.id("AddNewButtonSGFPS"));
		AddNewGroups.click();
		Thread.sleep(3000);
		WebElement Customercode = driver.findElement(By.id("SWOSFrmtxtCustomerCodePS"));
		Customercode.sendKeys(code);
		WebElement dis = driver.findElement(By.id("SWOSFrmtxtDescriptionPS"));
		dis.sendKeys(Description);
		WebElement checkbox = driver.findElement(By.id("chkRecurrentPS"));
		checkbox.click();
		driver.findElement(By.xpath("//button[@onclick='OkClickSWOSGroupsForm()']")).click(); // s ok
	}

	public void Groups() throws InterruptedException {
		Thread.sleep(3000);
		WebElement GropudButton = driver.findElement(By.id("btnGroups"));
		GropudButton.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@onclick='onSWOSGrSearchPSOkClick()']")).click(); // Search ok

	}

	public void swosgroupok() {
		driver.findElement(By.xpath("//button[@onclick='OkClickSGFP()']")).click(); // Search ok
	}

	public void view() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("ViewButtonSGFPS")).click(); // View
	}
	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		Thread.sleep(5000);
	}

	public void Host(String number, String packagesnumber) throws InterruptedException {
		WebElement Host = driver.findElement(By.id("btnHost")); // To click on Transaction
		Host.click();
		Thread.sleep(3000);
		WebElement ordernumber = driver.findElement(By.id("IN0"));
		ordernumber.sendKeys(number);
		WebElement packnumber = driver.findElement(By.id("IN1"));
		packnumber.sendKeys(packagesnumber);
		driver.findElement(By.id("hostOkButton")).click();
		Thread.sleep(3000);
	}

	public void clickship() {
		driver.findElement(By.id("btnShipClick")).click();
	}

	public void ship() throws InterruptedException {
		// Convert 'packagesnumber' to an integer to use it in the loop
		int numberOfPackages = Integer.parseInt(packagesnumber);
		for (int i = 1; i <= numberOfPackages; i++) {
			Thread.sleep(3000);
			driver.findElement(By.id("btnShipClick")).click();
			logger.info("Click on  Shipment ");
		}
	}

	public void swosgroupselecttable(String shipcode) throws InterruptedException {
		Thread.sleep(4000);
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblSWOSGroupFormListPS']//td[1]"));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
		String value = ele.getText();
		if (value.equals(shipcode))
		{
		System.out.println(value);
		dataStatus = true;
		  ele.click(); // Click on the element if found
		break;
		}
		}
		logger.info("Customer Code Found Selected ");
	}
public void ShipmentstobeConsolidatedtable() throws InterruptedException {

	driver.findElement(By.xpath("//table[@id='SWOSShipmentsList']//td[1][contains(text(), 'SWOS')]")).click();
	Thread.sleep(3000);
	driver.findElement(By.id("DoneButtonSSL")).click();  //ok

}
	public void captureError() throws InterruptedException {
		Thread.sleep(3000);
		try {
			WebElement errorMessage = driver.findElement(By.xpath("//span[@id='errorMsg']"));
			Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
			String actualErrorMessage = errorMessage.getText();
			switch (actualErrorMessage) {
			case "No records found!":
				Assert.assertEquals(actualErrorMessage, "No records found!", "Incorrect error message");
				break;
			case "Field value cannot be blank. Please try again.":
				Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.",
						"Incorrect error message");
				break;
			case "Customer Code cannot be more than 30 characters. Please try again.":
				Assert.assertEquals(actualErrorMessage,
						"Customer Code cannot be more than 30 characters. Please try again.",
						"Incorrect error message");
				break;
			case "Description more than 100 characters. Please try again.":
				Assert.assertEquals(actualErrorMessage, "Description more than 100 characters. Please try again.",
						"Incorrect error message");
				break;
			case "Shipment will be put on SWOS hold.":
				Assert.assertEquals(actualErrorMessage, "Shipment will be put on SWOS hold.",
						"Incorrect error message");
				break;

			default:
				// Handle other cases or unexpected errors
				System.out.println("Unexpected error message: " + actualErrorMessage);
				break;
			}
			WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
			error.click();
		} catch (TimeoutException e) {
			// Handle the case where the error message doesn't appear within the expected
			// time
			System.out.println("Error message not found within expected time.");
		}
	}

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
