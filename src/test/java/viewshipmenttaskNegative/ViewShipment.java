package viewshipmenttaskNegative;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewShipment {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ViewShipment");

	@Test(priority = 0)
	public void TestViewShipment() throws InterruptedException {
		OpenPs();
		selectDate("October 2023", "25");
		ok();
		ShipmentsTable();
	}

	@Test(priority = 1)
	public void testshipid() throws InterruptedException {
		OpenPs();
		ShipmentId("12121");
		ok();
		ShipmentsTable();
	}
	@Test(priority = 2)
	public void TestwithShipvia() throws InterruptedException {
		OpenPs();
		ShipmentId("12121");
		ViewShipmentMenu("Ship Via","FEX_Test1_GN1 -- Ground");
		ok();
		ShipmentsTable();
	}
	@Test(priority = 3)
	public void TestwithShipviaManifest() throws InterruptedException {
		OpenPs();
		ShipmentId("12121");
		ViewShipmentMenu("Ship Via","FEX_Test1_GN1 -- Ground");
		selectDate("October 2023", "25");
		ok();
		ShipmentsTable();
	}
	@Test(priority = 4,dataProvider = "package")
	public void packagetypeTest(String visibleText) throws InterruptedException {
		OpenPs();
		PackageType(visibleText);
		ok();
		ShipmentsTable();
	}
	@DataProvider(name="package")
	public Object[][] getdata(){

		Object[][] data= {{"On Hold"},{"Return"},{"Shipped"},{"Voided"}};
		return data;
	}
	public void PackageType(String visibleText) {

		WebElement dropdown = driver.findElement(By.id("cmbPackType"));
		Select select = new Select(dropdown);
		 select.selectByVisibleText(visibleText);
	}
	public void Listoffbuttons() {
		List<WebElement> buttons = driver.findElements(By.xpath("//*[@id='mainContaint']/div[1]/div/div[1]/div//button"));

		for (WebElement button : buttons) {
		    if (button.isEnabled()) {
		        System.out.println("Button '" + button.getText() + "' is enabled.");
		    } else {
		        System.out.println("Button '" + button.getText() + "' is disabled.");
		    }
		}
	}
public void ShipmentId(String id) {

	WebElement shipid=driver.findElement(By.id("txtInvoiceFrom"));
	shipid.sendKeys(id);
}
	public void ViewShipmentMenu(String loadShipment,String data) throws InterruptedException {
		if (loadShipment.equals("Site")) {
			driver.findElement(By.id("btnSitesLoad")).click();
			WebElement dropdown = driver.findElement(By.id("VScmbSite"));
			Select shipvia = new Select(dropdown);
			shipvia.selectByVisibleText(data);
			Thread.sleep(3000);

		} else if (loadShipment.equals("Ship Via")) {
			driver.findElement(By.id("btnShipViaLoad")).click();
			Thread.sleep(3000);
			WebElement usergroup = driver.findElement(By.xpath("//Select[@id='ddlUserGroup']"));
			Select Vavg = new Select(usergroup);
			Vavg.selectByValue("45"); // VA_UG
			driver.findElement(By.xpath("//button[@onclick='SelectGroupOkClick()']")).click();
			Thread.sleep(3000);
			WebElement dropdown = driver.findElement(By.id("cmbShipVia"));
			Select shipvia = new Select(dropdown);
			shipvia.selectByVisibleText(data);

		} else if (loadShipment.equals("Organizations")) {
			driver.findElement(By.id("btnOrgLoad")).click();
			Thread.sleep(3000);

		} else if (loadShipment.equals("Carriers")) {
			driver.findElement(By.id("btnLoadAccount")).click();
			Thread.sleep(3000);
			WebElement dropdown = driver.findElement(By.id("VScmbSite"));
			Select shipvia = new Select(dropdown);
			shipvia.selectByVisibleText(data);
			Thread.sleep(3000);
		}

	}

	public void Query() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("VScmdQuery")).click();
	}
	public void ShipmentsTable() throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblShipmentList']//tr//td[1]"));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals("No matching records found"))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Expected 'No matching records found' text not found.");
		driver.findElement(By.id("cmdCancel")).click();
	}
	public void selectDate(String targetMonth, String targetDay) throws InterruptedException {

		Thread.sleep(5000);
		driver.findElement(By.id("IWLDatePickerFrom")).click();
		WebElement month = driver.findElement(By.xpath("//th[@class='datepicker-switch']"));
		while (!month.getText().contains(targetMonth)) {
			driver.findElement(By.xpath("//th[@class='next']")).click();
		}
		List<WebElement> days = driver.findElements(By.xpath("//div[@class='datepicker-days']//td[@class='day']"));
		for (WebElement day : days) {
			String text = day.getText();
			if (text.equalsIgnoreCase(targetDay)) {
				day.click();
				break;
			}
		}
		Thread.sleep(3000);
	}
public void ok() {
	driver.findElement(By.id("SearchCriteriaOkClick")).click();
}
	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_23")).click(); // To click on VS
		Thread.sleep(3000);
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
