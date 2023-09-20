package thirdpartybillingaccounts;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TPBASearch {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("TPBASearch");

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

	@AfterMethod
	public void teardown() throws InterruptedException {

		Thread.sleep(10000);
		driver.close();
	}

	@Test
	public void Searchaccount() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");

		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		wait.until(ExpectedConditions.visibilityOf(SupportTables));
		wait.until(ExpectedConditions.elementToBeClickable(SupportTables));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");
		Thread.sleep(6000);

		WebElement tpba = driver.findElement(By.xpath("//a[@id='menu_item_459']"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_459']")));
		wait.until(ExpectedConditions.visibilityOf(tpba));
		wait.until(ExpectedConditions.elementToBeClickable(tpba));
		tpba.click();
		Thread.sleep(6000);
		logger.info("Click on Third Party Billing Accounts successful");

		String Criteria="code";
	    String code="AccountTest1";
	    String Customer="";
	    String CustomerAccount="";


	    WebElement Search = driver.findElement(By.xpath("//input[@id='txtTPSearch']"));
		if(Criteria.equalsIgnoreCase("code")) {
			Thread.sleep(3000);
			WebElement Code = driver.findElement(By.xpath("//input[@id='radCode']"));
			wait.until(ExpectedConditions.visibilityOf(Code));
			wait.until(ExpectedConditions.elementToBeClickable(Code));
			Code.click();
			logger.info("Code selected");
			Search.sendKeys(code);                                 //To search customer

		}else if(Criteria.equalsIgnoreCase("CustomerName")) {
			Thread.sleep(3000);
			WebElement CustomerName = driver.findElement(By.xpath("//input[@id='radCustomerName']"));
			wait.until(ExpectedConditions.visibilityOf(CustomerName));
			wait.until(ExpectedConditions.elementToBeClickable(CustomerName));
			CustomerName.click();
			Search.sendKeys(Customer);
			logger.info("CustomerName selected");

		}else if(Criteria.equalsIgnoreCase("CustomerAccount")) {
			Thread.sleep(3000);
			WebElement Account = driver.findElement(By.xpath("//input[@id='radAccount']"));
			wait.until(ExpectedConditions.visibilityOf(Account));
			wait.until(ExpectedConditions.elementToBeClickable(Account));
			Account.click();
			Search.sendKeys(CustomerAccount);
			logger.info("CustomerAccount selected");
}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='ThirdPartiesSearchOkClick()']")).click();

		  List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='ThirdPartiesList']//td"));

			boolean dataStatus = false;
			for (WebElement ele : alldata) {
				String value = ele.getText();
				if (value.equals(code))         // we can change compare string
				{
					System.out.println(value);
					dataStatus = true;
					break;
				}
			}
			Assert.assertTrue(dataStatus, "customercode is not found ");
			logger.info(" Search With  "+Criteria+"  successful");
}

}
