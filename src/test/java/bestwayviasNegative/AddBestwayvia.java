package bestwayviasNegative;

import java.time.Duration;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddBestwayvia {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("AddBestwayvia");

	@Test
	public void chekadd() throws InterruptedException {
		CreateBestWay();
		newbestway("TestAG1", "TestAG1", "User_Group -- User_Group");
		Showall();
		Okbutton();
		captureError();
	}

	public void captureError() throws InterruptedException {

		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='errorMsg']")));
		 logger.error("Expected error message -" + errorMessage);
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String expectedErrorMessage = "Duplicate BestWay within selected Org/Site Group is enabled. Please disable it to add new BestWay.";
		String actualErrorMessage = errorMessage.getText();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Incorrect error message");

		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
	}

	public void CreateBestWay() throws InterruptedException {

		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement carriers = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_44']")));
		wait.until(ExpectedConditions.elementToBeClickable(carriers));
		carriers.click();
		logger.info("Click on Carriers successful");
		Thread.sleep(5000);
		WebElement bestWayVias = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_442']")));
		wait.until(ExpectedConditions.elementToBeClickable(bestWayVias));
		bestWayVias.click();
		Thread.sleep(5000);
		WebElement ok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='BWSFOkClick()']")));
		ok.click();
		Thread.sleep(10000);
	}

	public void newbestway(String bestwaycode, String Description, String org) throws InterruptedException {
		Thread.sleep(5000);
		WebElement add = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='BestWayAdd']")));
		add.click();
		Thread.sleep(5000);
		WebElement txtCodeBWF = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeBWF")));
		txtCodeBWF.sendKeys(bestwaycode);
		WebElement Des = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionBWF")));
		Des.sendKeys(Description);

		WebElement OrgSiteGroup = driver.findElement(By.id("cmbGroupBWF"));
		Select select = new Select(OrgSiteGroup);
		select.selectByVisibleText(org);

		String[] shipvia = { "DHL_PRo12_DX", "ProUPS1_GND", "LTLNR_WL_100", "FMD-DHLPPGround", "" };
		for (String value : shipvia) {
			WebElement checkbox = driver.findElement(By.xpath("//u[contains(.,'" + value + "')]//preceding::td[1]"));
			checkbox.click();
			Thread.sleep(5000);
		}
	}

	public void Showall() {
		WebElement ShowAllBWF = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='ShowAllBWF']")));
		ShowAllBWF.click();
	}

	public void Okbutton()  {
		WebElement okbutton = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='OkClickBWF']")));
		okbutton.click();
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
