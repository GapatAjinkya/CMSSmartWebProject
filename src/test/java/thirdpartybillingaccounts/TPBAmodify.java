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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TPBAmodify {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("TPBAmodify");

	@BeforeMethod
	public void setup() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
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
	}

	@AfterMethod
	public void teardown() throws InterruptedException {

		Thread.sleep(10000);
		driver.close();
	}

	@Test(priority =0)         //(enabled = false)
	public void editaccount() throws InterruptedException {

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


		WebElement Search = driver.findElement(By.xpath("//input[@id='txtTPSearch']"));
		Search.sendKeys("AccountTest1");         //

		driver.findElement(By.xpath("//button[@onclick='ThirdPartiesSearchOkClick()']")).click();// ok buttton
		Thread.sleep(8000);
		WebElement selectproduct = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@data-index='0']")));
		selectproduct.click();

		Thread.sleep(5000);
		WebElement editbutton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='ThirdPartiesEdit']")));
		editbutton.click();
		Thread.sleep(5000);
		//To edit the information
		String Criteria = "code";
		String code = "TestAG1";
		String Customer = "";
		String CustomerAccount = "";


		if (Criteria.equalsIgnoreCase("code")) {
			Thread.sleep(3000);
			WebElement Code = driver.findElement(By.xpath("//input[@id='TPtxtXRef']"));
			wait.until(ExpectedConditions.visibilityOf(Code));
			wait.until(ExpectedConditions.elementToBeClickable(Code));
			Code.clear();
			Thread.sleep(3000);
			Code.sendKeys(code); // To search customer
			logger.info("Code edit ");

		} else if (Criteria.equalsIgnoreCase("CustomerName")) {
			Thread.sleep(3000);
			WebElement CustomerName = driver.findElement(By.xpath("//input[@id='TPtxtName']"));
			wait.until(ExpectedConditions.visibilityOf(CustomerName));
			wait.until(ExpectedConditions.elementToBeClickable(CustomerName));
			CustomerName.clear();
			Thread.sleep(3000);
			CustomerName.sendKeys(Customer);
			logger.info("CustomerName Edit");

		} else if (Criteria.equalsIgnoreCase("CustomerAccount")) {
			Thread.sleep(3000);
			WebElement Account = driver.findElement(By.xpath("//input[@id='TPtxtAccountNumber']"));
			wait.until(ExpectedConditions.visibilityOf(Account));
			wait.until(ExpectedConditions.elementToBeClickable(Account));
			Account.clear();
			Thread.sleep(3000);
			Account.sendKeys(CustomerAccount);
			logger.info("CustomerAccount edit");
		}
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[@onclick='OkClickThirdPartiesForm()']")).click();

			List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='ThirdPartiesList']//td"));

			boolean dataStatus = false;

			for (WebElement ele : alldata) {
				String value = ele.getText();
				if (value.equals(code)) // we can change compare string
				{
					System.out.println(value);
					dataStatus = true;
					break;
				}
			}
			Assert.assertTrue(dataStatus, "customercode is not found ");
			logger.info(" edit  With  " + Criteria + "  successful");
		}

	@Test(priority = 1)
	public void deleteAccount() throws InterruptedException {

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


		WebElement Search = driver.findElement(By.xpath("//input[@id='txtTPSearch']"));
		Search.sendKeys("TestAG1");         //To delete the account

		driver.findElement(By.xpath("//button[@onclick='ThirdPartiesSearchOkClick()']")).click();// ok buttton
		Thread.sleep(8000);
		logger.info("Search successful");

		WebElement selectproduct = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@data-index='0']")));
		selectproduct.click();
		Thread.sleep(5000);
		logger.info("Select  successful");
		WebElement delete = driver.findElement(By.id("ThirdPartiesDelete"));
		delete.click();

		Thread.sleep(5000);
		driver.findElement(By.id("btnConfirmBoxOk")).click();
		Thread.sleep(5000);

		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='ThirdPartiesList']//td"));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			System.out.println(value+"1");
			if (value.equals("No matching records found"))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Account is  Deleted");
		logger.info("Delete Account is successful ");
}
}