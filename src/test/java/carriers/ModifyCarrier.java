package carriers;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ModifyCarrier {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ModifyCarrier");

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
	public void teardown() throws InterruptedException {

		Thread.sleep(10000);
		driver.close();
	}

	@Test(priority = 0)
	public void edit() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		Thread.sleep(3000);
		logger.info("Click on Configuration successful");

		WebElement carriers = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_44']")));

		carriers.click();
		Thread.sleep(3000);
		logger.info("Click on Carriers successful");

		WebElement carriersaccount = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_440']")));
		carriersaccount.click();
		logger.info("Click on carriers account successful");
		Thread.sleep(5000);

		String carriercode="UPSPS_Test";
		WebElement Search = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("CASFtxtSearch")));
		Search.sendKeys(carriercode);
		Thread.sleep(3000);
		logger.info(" Search successful");
		WebElement ok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='CASFOkClick()']")));
		ok.click();
		Thread.sleep(3000);

		WebElement selectcarrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@data-index='0']")));
		selectcarrier.click();
		logger.info(" Selectcarrier successful");

		Thread.sleep(3000);
		WebElement editbutton = driver.findElement(By.id("CAEditbutton"));
		editbutton.click();
		logger.info(" edit button successful");

		String editwith = "code";
	 	String newcode = "TestAG1";             // To edit the product new code ----------------------------------
		String Description = "departments Test";
		String Carriertype="";
		Thread.sleep(5000);

		if (editwith.equalsIgnoreCase("code")) {
			Thread.sleep(3000);
			WebElement codesend = driver.findElement(By.xpath("//input[@id='CAF_txtCode']"));
			codesend.clear();
			Thread.sleep(3000);
			codesend.sendKeys(newcode);
			logger.info("Code edit successful");
		} else if (editwith.equalsIgnoreCase("Description")) {
			Thread.sleep(3000);
			WebElement pDescription = driver.findElement(By.id("CAF_txtDescription"));
			pDescription.clear();
			Thread.sleep(3000);
			pDescription.sendKeys(Description);
			logger.info("Description edit successful");
		} else if (editwith.equalsIgnoreCase("Carriertype")) {

			WebElement dropdown = driver.findElement(By.id("CAF_cmbType"));
			dropdown.clear();
			Thread.sleep(3000);
			Select select = new Select(dropdown);
			select.selectByVisibleText(Carriertype);
			logger.info(" Type Select  successful");
		}

		WebElement okclick = driver.findElement(By.xpath("//button[@onclick='CAF_OkClick()']"));
		okclick.click();
		Thread.sleep(3000);
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblCarrierAccountsList']//td"));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			System.out.println(value+"1");
			if (value.equals(newcode))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Carrier  is not edit");
		logger.info("Carrier edit is successful ");
}
	@Test(priority = 1)
	public void delete() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		Thread.sleep(3000);
		logger.info("Click on Configuration successful");

		WebElement carriers = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_44']")));

		carriers.click();
		Thread.sleep(3000);
		logger.info("Click on Carriers successful");

		WebElement carriersaccount = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_440']")));
		carriersaccount.click();
		logger.info("Click on carriers account successful");
		Thread.sleep(3000);

		String carriercode="UPSPS_Test";
		WebElement Search = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("CASFtxtSearch")));
		Search.sendKeys(carriercode);
		Thread.sleep(2000);
		logger.info(" Search successful");
		WebElement ok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='CASFOkClick()']")));
		ok.click();
		Thread.sleep(2000);
		WebElement selectCarrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@data-index='0']")));
		selectCarrier.click();
		Thread.sleep(3000);
		logger.info("Select  successful");
		WebElement delete = driver.findElement(By.id("CADeletebutton"));
		delete.click();

		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnConfirmBoxOk"))).click();
		Thread.sleep(5000);

		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblCarrierAccountsList']//td"));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			System.out.println(value+"1");
			if (value.equals("No matching records found"))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Account is  Deleted");
		logger.info("Delete Account is successful ");
}

}