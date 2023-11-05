package manifestexplorer;

import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ActivateTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ActivateTest");
	
	@Test(priority = 0)
	public void ActivateTestME() throws InterruptedException {
		OpenManifestExplorer();
		Carriers("Federal Express");
		checkstatus();
	}
	public void checkstatus() throws InterruptedException {
		Thread.sleep(3000);
	     WebElement table = driver.findElement(By.xpath("//table[@id='MEtblManifestDetailsList']"));
	     List<WebElement> rows = table.findElements(By.tagName("tr"));
	     for (WebElement row : rows) {
	            List<WebElement> cells = row.findElements(By.tagName("td"));
	            if (cells.size() >= 2) {
	                String column1Text = cells.get(3).getText();
	                String column2Text = cells.get(4).getText();
	                if (column1Text.equals("Future") && column2Text.equals("Open")) {
	                   
	                	row.click();
	                	driver.findElement(By.id("MEClose")).click();
	                	Thread.sleep(3000);
	                	driver.findElement(By.id("btnConfirmBoxOk")).click();   
	                	driver.findElement(By.id("MEActivate")).click();
	                	Alert alert = driver.switchTo().alert();
	                    // Accept the alert (click OK)
	                    alert.accept();
	                }else if(column1Text.equals("Future") && column2Text.equals("Closed")) {
	                	row.click();
	                	driver.findElement(By.id("MEActivate")).click();
	                	Alert alert = driver.switchTo().alert();
	                    alert.accept();
	                }
	            }
	        }
	}
	public void selectCarrierAccount(String Account) throws InterruptedException {
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='MFcmbAccount']"));
		dropdown.click();
		Select select = new Select(dropdown);
		select.selectByVisibleText(Account);
	}
	public void selectDate(String targetMonth, String targetDay) throws InterruptedException {
		driver.findElement(By.id("txtManifestFormDatePicker")).click();
		WebElement month = driver.findElement(By.xpath("//th[@class='datepicker-switch']"));
		while (!month.getText().contains(targetMonth)) {
			driver.findElement(By.xpath("//th[@class='next']")).click();
		}
		List<WebElement> days = driver.findElements(By.xpath("//div[@class='datepicker-days']//td[@class='day']"));
		for (WebElement day : days) {
			String text = day.getText();
			if (text.equalsIgnoreCase(targetDay)) {
				day.click();
				break;
			}
		}
		Thread.sleep(3000);
		driver.findElement(By.id("btnManifestsFormOk")).click();
	}
	public void Carriers(String carrier) throws InterruptedException {
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='MEddlCarriers']"));
		dropdown.click();
		Select select = new Select(dropdown);
		select.selectByVisibleText(carrier);

	}
	public void Create() throws InterruptedException {
		String Confirm = "Are you sure you want to create a new manifest?";
		WebElement Createbutton = driver.findElement(By.id("MECreate"));
		Createbutton.click();
		Thread.sleep(3000);
		WebElement confirmMsg = driver.findElement(By.id("confirmMsg"));
		String msg = confirmMsg.getText();
		if (msg.equalsIgnoreCase(Confirm)) {
			driver.findElement(By.id("btnConfirmBoxOk")).click();
		}
	}

	public void FutureManifest() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("Type2")).click();
	}
	public void OpenManifestExplorer() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_24")).click(); // To click on me
		Thread.sleep(3000);
	}
	
	@BeforeClass
	public void setup() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
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
