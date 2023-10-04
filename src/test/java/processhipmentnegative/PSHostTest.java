package processhipmentnegative;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PSHostTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("PSHostTest");

	@Test
	public void TestHost() throws InterruptedException {
		PSwithHost("1","2");
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
		// Convert 'packagesnumber' to an integer to use it in the loop
	    int numberOfPackages = Integer.parseInt(packagesnumber);
	    for (int i = 1; i <= numberOfPackages; i++) {
	    		Thread.sleep(3000);
	    		driver.findElement(By.id("btnShipClick")).click();
	    		logger.info("Click on  Shipment ");
	    	}
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
