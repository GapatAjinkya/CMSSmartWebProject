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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BestWayClone {
	public static WebDriver driver;
	Logger logger = LogManager.getLogger("BestWayClone");

	@Test
	public void Shipviaprient() throws InterruptedException {
		OpenBestWay();
		SearchBestWayCode("TestBestway");
		Clone();
		Okbutton();
	}

	public void Clone() throws InterruptedException {
		Thread.sleep(5000);
		WebElement deletebutton = driver.findElement(By.xpath("//button[@id='BestWayClone']"));
		deletebutton.click();
		Thread.sleep(5000);
		WebElement Confirm = driver.findElement(By.xpath("//button[@id='btnConfirmBoxOk']"));
		Confirm.click();
	}

	public void SearchBestWayCode(String bestwaycode) throws InterruptedException {
		Thread.sleep(3000);
		WebElement bestWayVias = driver.findElement(By.xpath("//input[@id='txtSearchBWSF']"));
		bestWayVias.sendKeys(bestwaycode);

		WebElement ok = driver.findElement(By.xpath("//button[@onclick='BWSFOkClick()']"));
		ok.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//table[@id='BestWayList']//td[1][contains(text(), '" + bestwaycode + "')]"))
				.click();
	}

	public void OpenBestWay() throws InterruptedException {
		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement carriers = driver.findElement(By.xpath("//a[@id='menu_item_44']"));
		carriers.click();
		logger.info("Click on Carriers successful");
		Thread.sleep(5000);
		WebElement bestWayVias = driver.findElement(By.xpath("//a[@id='menu_item_442']"));
		bestWayVias.click();
	}

	public void Okbutton() throws InterruptedException {

		WebElement okbutton = driver.findElement(By.xpath("//button[@id='OkClickBWF']"));
		okbutton.click();
		Thread.sleep(3000);
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
	}

	@BeforeClass
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}

}
