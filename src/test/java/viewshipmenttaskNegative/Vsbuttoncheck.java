package viewshipmenttaskNegative;

import java.util.List;
import java.util.concurrent.TimeUnit;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Vsbuttoncheck {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Vsbuttoncheck");
	
	@Test
	public void ButtonsTest() throws InterruptedException {
		OpenPs();
		ShipmentId("12121");
		ok();
		ShipmentsTable();
		Listoffbuttons();
	}
	public void Listoffbuttons() throws InterruptedException {
		// Specify the text of the two buttons that should be enabled
		Thread.sleep(5000);
		String button1Text = "Query";
		String button2Text = "Disp. Last List";

		List<WebElement> buttons = driver.findElements(By.xpath("//*[@id='mainContaint']/div[1]/div/div[1]/div//button"));

		for (WebElement button : buttons) {
		    String buttonText = button.getText();
		    if (buttonText.equals(button1Text) || buttonText.equals(button2Text)) {
		        if (button.isEnabled()) {
		            System.out.println("Button '" + buttonText + "' is enabled.");
		        } else {
		            Assert.fail("Button '" + buttonText + "' is expected to be enabled but is disabled.");
		        }
		    } else {
		        if (button.isEnabled()) {
		            Assert.fail("Button '" + buttonText + "' is expected to be disabled but is enabled.");
		        } else {
		            System.out.println("Button '" + buttonText + "' is disabled.");
		        }
		    }
		}
	}
	
	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_23")).click(); // To click on VS
		Thread.sleep(3000);
	}
	
	public void ShipmentId(String id) {
		
		WebElement shipid=driver.findElement(By.id("txtInvoiceFrom"));
		shipid.sendKeys(id);
	}	
	public void ok() {
		driver.findElement(By.id("SearchCriteriaOkClick")).click();
	}
	public void ShipmentsTable() throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblShipmentList']//tr//td[1]"));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals("No matching records found"))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Expected 'No matching records found' text not found.");
		driver.findElement(By.id("cmdCancel")).click();
	}
	@BeforeClass
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 60);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();
	}
}
