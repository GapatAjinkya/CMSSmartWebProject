package archivedmanifesttest;

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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorldEaseTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	 Logger logger = LogManager.getLogger("PLDTransmit");

	@Test(priority = 0)
	public void testcase() throws InterruptedException {
		openAM();
		selectcarrier("United Parcel Service");
		ShipcodeAm("TestAG1");
		WorldEase();
		logger.info("TestCase Pass");
	}
	public void WorldEase() {

		WebElement WorldEasebutton =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='AMEWorldEase']")));
		WorldEasebutton.click();

	}
	public void ShipcodeAm(String shipcode) throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> alldata = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='tblArchivedManifestList']//tr//td[1]")));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(shipcode))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Code not found");
		if (dataStatus) {
			Thread.sleep(5000);
			WebElement code =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='tblArchivedManifestList']//td[contains(text(), '" + shipcode + "')]")));
			code.click();

		} else {
		    System.out.println("Code not found");
		}
		logger.info("Customer Code Found Selected ");
	}
	@BeforeClass
	public void setup() throws InterruptedException {
		  System.setProperty("webdriver.chrome.driver", "E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
		     ChromeOptions options = new ChromeOptions();
		    //options.setBinary("E:\\ChromeTesting\\chrome-win64\\chrome.exe");
		    options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		   //options.addArguments("--remote-allow-origins=*");

		    driver = new ChromeDriver(options);
			logger.info("Browser opend");
			driver.manage().window().maximize();
			driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
			Thread.sleep(3000);
			driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
			driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			Thread.sleep(3000);
			WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
			Userlogin.sendKeys("Nilesh");
			WebElement password = driver.findElement(By.id("txtLPPassword")); // password
			password.sendKeys("Nilesh@123");
			driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
			WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
			ok.click();
	}

	public void openAM() throws InterruptedException {
		Thread.sleep(5000);
		WebElement transaction = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2")));
		wait.until(ExpectedConditions.elementToBeClickable(transaction)).click();
		 logger.info("Click on Transaction Menu Successful");
		Thread.sleep(5000);
		WebElement ArchivedManifests = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_25")));
		wait.until(ExpectedConditions.elementToBeClickable(ArchivedManifests)).click();
	}

	public void selectcarrier(String carriername) throws InterruptedException {
		Thread.sleep(2000);
		WebElement Carrier = driver.findElement(By.xpath("//select[@id='AMEddlCarriers']"));
		Select dropdown = new Select(Carrier);
		dropdown.selectByVisibleText(carriername);
		logger.info(" selectcarriers successful");
	}
}
