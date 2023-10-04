package swosnegative;
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

public class PackTestNegative {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("SwosWithPackVS");

	@Test(priority = 0)
	public void TestHost() throws InterruptedException {
		PSwithHost("1","2");
		captureError();
	//	Groups();
//		GroupsDetails("SWOS1", "SWOS1");
	}
	@Test(priority = 1)
	public void Select() throws InterruptedException {
		Groups();
		SelectMaster("SWOS");
		captureError();
		Pack();
		Thread.sleep(3000);
		driver.findElement(By.id("btnCompletePack")).click();//submit button
		captureError();
		driver.findElement(By.id("btnCreatePackage")).click();  //Create Package
		driver.findElement(By.xpath("//input[contains(@id, 'frameConsolidatedBOX') and contains(@id, '0')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("btnAddItems")).click();
		captureError();
		Thread.sleep(2000);
		driver.findElement(By.id("frameConsolidatedtxt0")).sendKeys("10");
		Thread.sleep(2000);
		driver.findElement(By.id("btnCompletePack")).click();//submit button
		Exit();
	}
	public void Exit() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("btnExit")).click();//submit button
	}
	public void CreatePackage() throws InterruptedException {
		Thread.sleep(3000);
			driver.findElement(By.id("btnCreatePackage")).click();  //Create Package
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id, 'radPack') and contains(@id, '0')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id, 'frameConsolidatedBOX') and contains(@id, '0')]")).click();
			WebElement onebook=driver.findElement(By.id("frameConsolidatedtxt0"));
			onebook.sendKeys("1");
			Thread.sleep(2000);
			driver.findElement(By.id("btnAddItems")).click();
		driver.findElement(By.id("btnCompletePack")).click();//submit button
	}

	public void Pack() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("cmdPack")).click(); // cmdPack
	}

	public void GroupsDetails(String code, String Description) throws InterruptedException {
		Thread.sleep(3000);
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

	public void SelectMaster(String SwosGroups) throws InterruptedException {
			Thread.sleep(5000);
			List<WebElement> alldatao = driver.findElements(By.xpath("//table[@id='tblSWOSGroupFormListPS']//td[1]"));
			boolean dataStatuso = false;
			for (WebElement ele : alldatao) {
				String value = ele.getText();
				if (value.equals(SwosGroups))
				{
					System.out.println(value);
					ele.click();
					dataStatuso = true;
					break;
				}
			}
			Assert.assertTrue(dataStatuso, "");
			Thread.sleep(3000);
			//Click on view
			driver.findElement(By.id("ViewButtonSGFPS")).click();

			Thread.sleep(3000);
			List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='SWOSShipmentsList']//td[1]"));
			boolean dataStatus = false;
			for (WebElement ele : alldata) {
				String value = ele.getText();
				if (value.equals(SwosGroups))
				{
					System.out.println(value);
					ele.click();
					dataStatus = true;
					break;
				}
			}
			Assert.assertTrue(dataStatus, "");
			Thread.sleep(3000);
			driver.findElement(By.id("DoneButtonSSL")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("btnOkButtonSGFPS")).click();
		}
	public void Groups() throws InterruptedException {
		Thread.sleep(2000);
		WebElement GropudButton = driver.findElement(By.id("btnGroups"));
		GropudButton.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@onclick='onSWOSGrSearchPSOkClick()']")).click(); // Search ok
	}
	public void PSwithHost(String number,String packagesnumber) throws InterruptedException {
		OpenPs();
		Host();
		Thread.sleep(3000);
		WebElement ordernumber = driver.findElement(By.id("IN0"));
		ordernumber.sendKeys(number);
		Thread.sleep(3000);
		WebElement packnumber = driver.findElement(By.id("IN1"));
		packnumber.sendKeys(packagesnumber);
		driver.findElement(By.id("hostOkButton")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("btnShipClick")).click();
		}
	public void Host() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Host = driver.findElement(By.id("btnHost")); // To click on Transaction
		Host.click();
	}
	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		Thread.sleep(5000);
	}
	public void captureError() throws InterruptedException {
		Thread.sleep(3000);
		try {
			WebElement errorMessage = driver.findElement(By.xpath("//span[@id='errorMsg']"));
			Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
			String actualErrorMessage = errorMessage.getText();
			switch (actualErrorMessage) {
			case "No records found!":
			case "Field value cannot be blank. Please try again.":
			case "Please remember to adjust the Box Information for this SWOS shipment.":
			case "Please remember to create package(s) and assign all of the items for this SWOS Shipment within the pack screen.":
			case "Shipment will be put on SWOS hold.":
			case "Entry already exists in the database. Please try again.":
			case "NO CONSOLIDATION":
			case "Customer Code cannot be more than 30 characters. Please try again.":
			case "Delete the package, with Line Item equal to 0":
			case "Please assign all the item(s) to the packages":
				System.out.println("Handling error message: " + actualErrorMessage);
				Assert.assertEquals(actualErrorMessage, actualErrorMessage, "Incorrect error message");
				break;
			default:
				System.out.println("Unexpected error message: " + actualErrorMessage);// Handle other cases or																						// unexpected errors
				break;
			}
			Thread.sleep(2000);
			WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
			error.click();
		} catch (TimeoutException e) {
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}
}
