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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DisableBestway {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("DisableBestway");

	@Test
	public void Enable() throws InterruptedException {
		OpenBestWay();
		SearchBestWayCode("TestBestWayvia");
		editbutton();
		Disablebestway();
		Okbutton();
	}

	public void Disablebestway() throws InterruptedException {
			Thread.sleep(3000);
			WebElement checkbox = driver.findElement(By.xpath("//input[@id='chkDisableBestwayBWF']"));
			boolean isChecked = checkbox.isSelected();
		        // Toggle the checkbox state
		        if (isChecked) {
		            // Uncheck the checkbox
		        	logger.info("Disable Bestway via Record ");
		            checkbox.click();
		        } else {
		            // Check the checkbox
		            checkbox.click();
		            logger.info("Enabled Bestway via Record ");
		        }
	}
	public void editbutton() throws InterruptedException {
		Thread.sleep(5000);
		WebElement editbutton = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='BestWayEdit']")));
		editbutton.click();
	}
	public void SearchBestWayCode(String bestwaycode) throws InterruptedException {
		Thread.sleep(3000);
		WebElement bestWayVias = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtSearchBWSF']")));
		bestWayVias.sendKeys(bestwaycode);

		WebElement ok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='BWSFOkClick()']")));
		ok.click();
		Thread.sleep(5000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//table[@id='BestWayList']//td[1][contains(text(), '" + bestwaycode + "')]"))).click();

		}

	public void OpenBestWay() throws InterruptedException
	{
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
public void Okbutton() throws InterruptedException {

		WebElement okbutton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='OkClickBWF']")));
		okbutton.click();
		Thread.sleep(3000);
		WebElement error=driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
	    error.click();
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

	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
