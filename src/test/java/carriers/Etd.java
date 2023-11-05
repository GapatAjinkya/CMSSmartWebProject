package carriers;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Etd {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Etd");
	
	
	
	
	@Test(priority = 0)
	public void TestNewCarriers() throws InterruptedException {
		openCarrier();
		Search("FEX_Test1");
	}
	
	public void Search(String searchcarrier) throws InterruptedException {
		
		WebElement Search = driver.findElement(By.id("CASFtxtSearch"));
		Search.sendKeys(searchcarrier);
		logger.info(" Search successful");
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='CASFOkClick()']"));
		ok.click();
		Thread.sleep(5000);
		logger.info(" ok click successful");		
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblCarrierAccountsList']//td[1]"));
		for(WebElement element:alldata) {	
			String value = element.getText();
			if(value.equals(searchcarrier)) {
				Thread.sleep(3000);
				element.click();
				logger.info(" element ok click successful");
				driver.findElement(By.id("CAbuttonFedExETD")).click();
				logger.info("ETD  ok click successful");
				
			}
			}
		}
	public void openCarrier() throws InterruptedException {
		Thread.sleep(2000);
		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		Thread.sleep(3000);
		logger.info("Click on Configuration successful");

		WebElement carriers = driver.findElement(By.xpath("//a[@id='menu_item_44']"));
		carriers.click();
		Thread.sleep(3000);
		logger.info("Click on Carriers successful");

		WebElement carriersaccount = driver.findElement(By.xpath("//a[@id='menu_item_440']"));

		carriersaccount.click();
		Thread.sleep(3000);
		logger.info("Click on carriers account successful");
	}
	@BeforeClass
	public void setup() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
	  //  options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		driver = new ChromeDriver(options);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}
}
