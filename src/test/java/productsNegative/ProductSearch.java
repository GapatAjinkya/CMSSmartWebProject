package productsNegative;

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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductSearch {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ProductsCode");
	@Test
	public void ProductSearchTest() throws InterruptedException {
		productWindo();
		SearchProduct("acac");
		captureError();
	}
	public void SearchProduct(String code) throws InterruptedException {
	    WebElement Search=driver.findElement(By.xpath("//input[@id='txtCSTProdSearch']"));
		WebElement Code = driver.findElement(By.xpath("//input[@id='CSTProdRadCode']"));
		wait.until(ExpectedConditions.visibilityOf(Code));
		Code.click();
		logger.info("Code selected");
		Search.sendKeys(code);
	WebElement okclick=driver.findElement(By.xpath("//button[@onclick='onProductSearchFormOkClick()']"));
	wait.until(ExpectedConditions.visibilityOf(okclick));
	okclick.click();
	}

	public void captureError() throws InterruptedException {
	Thread.sleep(5000);
	WebElement errorMessage = wait
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
	Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
        String actualErrorMessage = errorMessage.getText();

	if (actualErrorMessage.equals("No records found!")) {
        System.out.println("Handling first error message."+actualErrorMessage);
        Assert.assertEquals(actualErrorMessage, "No records found!", "Incorrect error message");
    } else {
        System.out.println("Unexpected error message: " + actualErrorMessage);
    }
	WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
	error.click();
}
	public void okproduct() {
		WebElement prodok=driver.findElement(By.id("btnProdFrmOk"));
		prodok.click();
	}
	public void productWindo() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Clickon Configuration successful");

		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		wait.until(ExpectedConditions.visibilityOf(SupportTables));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");

		Thread.sleep(3000);
		WebElement products=driver.findElement(By.xpath("//a[@id='menu_item_455']"));
		wait.until(ExpectedConditions.visibilityOf(products));
		products.click();
		Thread.sleep(3000);
		logger.info(" products Windo Open  successful");
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
}
