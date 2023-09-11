package billoflading;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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

public class Resumecheck {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("BillofLading");
	String CustomerCode = "TestBOL";
	String Carriers = "LTLR_WL -- LTL Rated WorldLink";// Search Carrier
	public static String newcode = "TestBOL";

	@BeforeClass
	public void setup() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void SelectBOLcustomer() throws InterruptedException {
		WebElement Transaction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_2"))); // To
																													// click																											// Transaction
		wait.until(ExpectedConditions.elementToBeClickable(Transaction));
		Transaction.click();
		WebElement BOL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_26"))); // To click
																											// on
																											// Transaction
		wait.until(ExpectedConditions.elementToBeClickable(BOL));
		BOL.click();
		WebElement dropdown = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='BOLddlCarriers']")));
		Select select = new Select(dropdown);
		select.selectByVisibleText(Carriers);
		logger.info("Carriers Selected ");

//--------------------------		

		List<WebElement> alldata = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='ELtblBOLExplorerList']//tr//td[2]")));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(CustomerCode)) {
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "CustomerCode not found");
		if (dataStatus) {
			Thread.sleep(5000);
			WebElement Customercode = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//table[@id='ELtblBOLExplorerList']//td[contains(text(), '" + CustomerCode + "')]")));
			Customercode.click();
		} else {
			System.out.println("CustomerCode not found");
		}
		logger.info("Customer Code Found Selected ");
	}

	@Test(priority = 1)
	public void close() throws InterruptedException {
		Thread.sleep(5000);
		try {
			List<WebElement> alldata = wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='ELtblBOLExplorerList']//tr//td[2]")));

			boolean dataStatus = false;
			for (WebElement ele : alldata) {
				String value = ele.getText();
				if (value.equals(newcode)) {
					System.out.println(value);
					dataStatus = true;
					break;
				}
			}
			Assert.assertTrue(dataStatus, "CustomerCode not found");
			if (dataStatus) {
				Thread.sleep(6000);
				WebElement SelectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//table[@id='ELtblBOLExplorerList']//td[contains(text(), '" + newcode + "')]")));
				wait.until(ExpectedConditions.elementToBeClickable((SelectElement)));
				SelectElement.click();

				WebElement closebutton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='BOLClose']")));
				wait.until(ExpectedConditions.elementToBeClickable((closebutton)));
				closebutton.click();
				Thread.sleep(5000);
				WebElement ok = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnConfirmBoxOk']")));
				ok.click();

				logger.info("Customer is close ");
			} else {
				System.out.println("CustomerCode not found");
			}
		} catch (StaleElementReferenceException e) {
		}
	}
	@Test(priority = 2)
	public void resume() throws InterruptedException {
		try {
			List<WebElement> alldata = wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='ELtblBOLExplorerList']//tr//td[2]")));

			boolean dataStatus = false;
			for (WebElement ele : alldata) {
				String value = ele.getText();
				if (value.equals(newcode)) {
					System.out.println(value);
					dataStatus = true;
					break;
				}
			}
			Assert.assertTrue(dataStatus, "CustomerCode not found");
			if (dataStatus) {
				
				Thread.sleep(6000);
				WebElement SelectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//table[@id='ELtblBOLExplorerList']//td[contains(text(), '" + newcode + "')]")));
				wait.until(ExpectedConditions.elementToBeClickable((SelectElement)));
				SelectElement.click();

				WebElement closebutton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='BOLClose']")));
				wait.until(ExpectedConditions.elementToBeClickable((closebutton)));
				closebutton.click();
				Thread.sleep(5000);
				WebElement ok = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnConfirmBoxOk']")));
				ok.click();

				logger.info("Customer is Resume ");
			} else {
				System.out.println("CustomerCode not found");
			}
		} catch (StaleElementReferenceException e) {

		}
	}

}
