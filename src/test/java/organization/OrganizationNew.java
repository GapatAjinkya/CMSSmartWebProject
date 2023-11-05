package organization;

import java.time.Duration;
import java.util.Arrays;

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

public class OrganizationNew {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("OrganizationNew");

	@Test(priority = 0)
	public void SearchOrgTest() throws InterruptedException {
		Organization();
		Search("asdsads");
		captureError();
		addbutton();
	}
	@Test(priority = 1, dataProvider = "invalidData")
	public void testGeneralInformation(String code, String OrgName, String Taxid,String city,String State, String zip,
			String Address) throws InterruptedException {
		Entercode(code, OrgName, Taxid);
		SendCity(city);
		SendState(State);
		SendZip(zip);
		address(Address);
		EClickOK();
		captureError();
	}
	@Test(priority = 2, dataProvider = "validData")
	public void AddNew(String code, String OrgName, String Taxid,String city,String State, String zip,
			String Address) throws InterruptedException {
		Entercode(code, OrgName, Taxid);
		SendCity(city);
		SendState(State);
		SendZip(zip);
		address(Address);
		EClickOK();
	}
	@DataProvider(name = "validData")
	public Object[][] validData() {
		return new Object[][] {
			{ "CMS_AG", "CMS GlobalSoft", "1234567897", "Lorton", "VA","22079", "9010 Lorton Station Blvd" }};
		}
	@DataProvider(name = "invalidData")
	public Object[][] invalidData() {
		// Define negative test data here
		return new Object[][] {
				{ "aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovB", "ValidOrgName", "ValidTaxID", "Lorton", "VA",
						"22079", "9010 Lorton Station Blvd" }, // Invalid code
				{ "ValidCode", "aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovB", "ValidTaxID", "Lorton", "VA",
						"22079", "9010 Lorton Station Blvd" }, // Invalid organization name
				{ "ValidCode", "ValidOrgName", "aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovB", "Lorton", "VA",
						"22079", "9010 Lorton Station Blvd" }, // Invalid tax ID
				{ "ValidCode", "ValidOrgName", "1234567897", "aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovB1", "VA","22079", "9010 Lorton Station Blvd" },
		};
	}

	public void addbutton() throws InterruptedException {
		Thread.sleep(3000);
		WebElement addshipvia = driver.findElement(By.id("OrgsAdd"));
		addshipvia.click();
		logger.info("Click on add successful");
	}

	public void Entercode(String code, String OrgName, String Taxid) throws InterruptedException {
		Thread.sleep(2000);
		WebElement codeinputtab = driver.findElement(By.id("txtOrganizationCode"));
		codeinputtab.clear();
		codeinputtab.sendKeys(code);
		Thread.sleep(2000);
		WebElement OrgNameinputtab = driver.findElement(By.id("txtOrganizationName"));
		OrgNameinputtab.clear();
		OrgNameinputtab.sendKeys(OrgName);
		Thread.sleep(2000);
		WebElement TaxidNumber = driver.findElement(By.id("txtTaxidNumber"));
		TaxidNumber.clear();
		TaxidNumber.sendKeys(Taxid);
	}

	public void address(String Address) {
		WebElement address = driver.findElement(By.id("OrgtxtAddress1"));
		address.clear();
		address.sendKeys(Address);
	}

	public void EClickOK() {
		driver.findElement(By.id("OkClickOrgsForm")).click();

	}

	public void SendCity(String city) {
		WebElement Citycode = driver.findElement(By.id("OrgtxtAddCity"));
		Citycode.clear();
		Citycode.sendKeys(city);
	}

	public void SendState(String State) {
		WebElement AddState = driver.findElement(By.id("OrgtxtAddState"));
		AddState.clear();
		AddState.sendKeys(State);
	}

	public void SendZip(String zip) {
		WebElement AddZip = driver.findElement(By.id("OrgtxtAddZip"));
		AddZip.clear();
		AddZip.sendKeys(zip);
	}

	public void Organization() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		WebElement Org = driver.findElement(By.xpath("//a[@id='menu_item_40']"));
		Org.click();
		Thread.sleep(3000);
	}

	public void Search(String Orgcode) throws InterruptedException {
		WebElement Search = driver.findElement(By.id("OrgstxtSearch"));
		Search.sendKeys(Orgcode);
		driver.findElement(By.xpath("//button[@onclick='OrgsSearchOkClick()']")).click();
		Thread.sleep(3000);
	}

	public void captureError() throws InterruptedException {
		Thread.sleep(3000);
		try {
			WebElement errorMessage = driver.findElement(By.xpath("//span[@id='errorMsg']"));
			String expectedMessage = errorMessage.getText();
			String[] validMessages = { "No records found!", "Field value cannot be blank. Please try again.",
					"Value should be less than 50 characters. Please try again.",
					"Value should be less than 15 characters. Please try again." };
			boolean isValidMessage = Arrays.asList(validMessages).contains(expectedMessage);

			if (isValidMessage) {
				Assert.assertEquals(expectedMessage, errorMessage.getText(), "Incorrect error message");
				WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
				error.click();
			} else {
				System.out.println("Unexpected error message: " + expectedMessage);
			}
		} catch (TimeoutException e) {
			System.out.println("Error message not found within expected time.");
		}
	}

	@BeforeClass
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		driver = new ChromeDriver(options);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
//			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}
}
