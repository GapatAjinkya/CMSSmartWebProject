package manifestexplorer;

import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignBOLsTest {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("AssignBOLsTest");

	@Test(priority = 0)
	public void testcarriers() throws InterruptedException {
		OpenManifestExplorer();
		Carriers("LTL Carriers");
		Selecttype("LTLNR");
		AssignBols();
		capturepopup();
	}
 
	public void Selecttype(String shipcode) throws InterruptedException {

		Thread.sleep(3000);
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='MEtblManifestDetailsList']//td[1]"));
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(shipcode)) {
				System.out.println(value);
				ele.click(); // Click on the element if found
				break;
			}
		}
		logger.info("Customer Code Found Selected ");

	}
	public void Create() throws InterruptedException {
		String Confirm = "Are you sure you want to create a new manifest?";
		WebElement Createbutton = driver.findElement(By.id("MECreate"));
		Createbutton.click();
		Thread.sleep(5000);
		WebElement confirmMsg = driver.findElement(By.id("confirmMsg"));
		String msg = confirmMsg.getText();
		if (msg.equalsIgnoreCase(Confirm)) {
			driver.findElement(By.id("btnConfirmBoxOk")).click();
		}
	}
	public void ActiveManifest() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("Type1")).click();
	}
	public void selectDate(String condition,String targetMonth, String targetDay) throws InterruptedException {

		if(condition.equalsIgnoreCase("f")) {
		Thread.sleep(5000);
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
		}else {
			
			driver.findElement(By.id("btnManifestsFormOk")).click();
		}
	}
	public void AssignBols() throws InterruptedException {
		Thread.sleep(3000);
		WebElement ABOLs = driver.findElement(By.id("MEAssignBOL")); // To click on AssignBols button
		ABOLs.click();
		Thread.sleep(3000);
		String master = "41234567891246507";
		String a[] = { "41234567891246002", "41234567891246200", "41234567891246507" };
		int leng = a.length;
		for (int i = 0; i < leng; i++) {
			String value = a[i];
			driver.findElement(
					By.xpath("//td[contains(text(),'" + value + "')]/preceding-sibling::td/input[@id='Checkbox']"))
					.click();

			WebElement pro = driver.findElement(By.xpath("//td[contains(text(),'" + value
					+ "')]/following-sibling::td/input[@class='form-control form-control-sm']"));
			pro.clear();
			pro.sendKeys("1");
		}

		driver.findElement(By.xpath("//tr[td[contains(text(),'" + master + "')]]/td[2]/input[1]")).click();
		driver.findElement(By.id("btnBOLShipmentOk")).click();

	}

	public void AssignBols2() throws InterruptedException {
		WebElement ABOLs = driver.findElement(By.id("MEAssignBOL")); // To click on AssignBols button
		ABOLs.click();
		Thread.sleep(3000);
		String master = "41234567891246507";
		String elementsToSelect[] = { "41234567891246002", "41234567891246200", "41234567891246507" };
		int leng = elementsToSelect.length;
		for (String elementText : elementsToSelect) {
			// Find the element containing the specified text
			driver.findElement(
					By.xpath("//td[contains(text(),'" + elementText + "')]/preceding-sibling::td/input[@id='Checkbox']"))
					.click();

			WebElement pro = driver.findElement(By.xpath("//td[contains(text(),'" + elementText
					+ "')]/following-sibling::td/input[@class='form-control form-control-sm']"));
			pro.sendKeys("3");
		}
		driver.findElement(By.xpath("//tr[td[contains(text(),'" + master + "')]]/td[2]/input[1]")).click();
		driver.findElement(By.id("btnBOLShipmentOk")).click();
	}

	public void capturepopup() throws InterruptedException {
		Thread.sleep(3000);
		WebElement error = driver.findElement(By.id("errorMsg"));
		String errortext = error.getText();

		if (errortext.equalsIgnoreCase("Selected BOLs were added/removed successfully to the Manifest.")) {
			driver.findElement(By.id("btnErrorBoxOk")).click();
		} else {
			Assert.assertFalse(false, "errortext Is Not Maching ");
		}
	}

	public void Carriers(String carrier) throws InterruptedException {
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='MEddlCarriers']"));
		dropdown.click();
		Select select = new Select(dropdown);
		select.selectByVisibleText(carrier);

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
