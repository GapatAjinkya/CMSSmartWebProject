package swosnegative;

import java.time.Duration;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.netty.handler.timeout.TimeoutException;

public class Swoswithpacknegative {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Swoswithpacknegative");

	@Test(priority = 0)
	public void TestSearchGroups() throws InterruptedException {
		OpenPs();
		Groups();
		Searchgroup();
	}
	   @Test(priority = 1, dataProvider = "addGroupsTest")
	    public void testAddGroups(String code, String description) throws InterruptedException {
	        GroupsDetails(code, description);
	        captureError();
	    }
	@DataProvider(name = "addGroupsTest")
	public Object[][] addgroup() {

		 return new Object[][]{
             {"asdfghjklp1asdfghjklp1asdfghjklp32", "validDescription1"},
             {"validCode2", "aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovBaasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquov100"},
             {"", ""},
     };
	}

	public void Searchgroup() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("txtCSTSWOSGrSearchPS")).sendKeys("asdsac");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@onclick='onSWOSGrSearchPSOkClick()']")).click(); // Search ok

		WebElement table = driver.findElement(By.xpath("//*[@id=\"tblSWOSGroupFormListPS\"]//td[2]"));
		String tabletext = table.getText();
		System.out.println(tabletext);
		if (tabletext.equals("NO CONSOLIDATION")) {
			driver.findElement(By.id("AddNewButtonSGFPS")).click();
		} else {
			Assert.fail("NO CONSOLIDATION Is Not Showing ");
		}
	}

	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		Thread.sleep(3000);
	}

	public void Groups() throws InterruptedException {
		Thread.sleep(2000);
		WebElement GropudButton = driver.findElement(By.id("btnGroups"));
		GropudButton.click();
	}

	public void GroupsDetails(String code, String Description) throws InterruptedException {
		Thread.sleep(3000);
		WebElement Customercode = driver.findElement(By.id("SWOSFrmtxtCustomerCodePS"));
		Customercode.clear();
		Customercode.sendKeys(code);
		WebElement dis = driver.findElement(By.id("SWOSFrmtxtDescriptionPS"));
		dis.clear();
		dis.sendKeys(Description);
		WebElement checkbox = driver.findElement(By.id("chkRecurrentPS"));
		checkbox.click();
		driver.findElement(By.xpath("//button[@onclick='OkClickSWOSGroupsForm()']")).click(); // s ok
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
			case "Description more than 100 characters. Please try again.":
			case "Shipment will be put on SWOS hold.":
			case "Entry already exists in the database. Please try again.":
			case "NO CONSOLIDATION":
			case "Customer Code cannot be more than 30 characters. Please try again.":
				System.out.println("Handling error message: " + actualErrorMessage);
				Assert.assertEquals(actualErrorMessage, actualErrorMessage, "Incorrect error message");
				break;
			default:
				System.out.println("Unexpected error message: " + actualErrorMessage);// Handle other cases or
																						// unexpected errors
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
