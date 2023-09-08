package manifestexplorernegative;
import java.util.concurrent.TimeUnit;
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

public class ForcePost {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ForcePost");

	@Test(priority = 0)
	public void testcarriers() throws InterruptedException {
		OpenManifestExplorer();
		Carriers("United Parcel Service");
		opencheck();
	}
	@Test(priority = 1)
	public void PostTest() throws InterruptedException {
		OpenManifestExplorer();
		Carriers("United Parcel Service");
		post();
		
	}
	public void opencheck() throws InterruptedException {
		WebElement  alldata = driver.findElement(By.xpath("//table[@id='MEtblManifestDetailsList']//tr[1]//td[5]"));
		  String column5Text = alldata.getText();
		  
		 if( column5Text.equals("Open")) {
			 driver.findElement(By.id("MEForcePost")).click();
				Thread.sleep(3000);
			 WebElement username=driver.findElement(By.id("MEtxtUsername"));
			 username.sendKeys("");
			 driver.findElement(By.id("btnMFPOk")).click();
				Thread.sleep(3000);
			 WebElement errorMessage = wait
					 .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
			 String actualErrorMessage = errorMessage.getText();
			 
			 if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
				 System.out.println("Handling first error message."+actualErrorMessage); 
				 Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.", "Incorrect error message");
				
				 WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
				 error.click();
				 driver.findElement(By.id("btnMFPCancel")).click();
		 }
			 else {
			 System.out.println("Unexpected error message: " + actualErrorMessage);
		 }
		 }
	}
	public void post() {
		WebElement  alldata = driver.findElement(By.xpath("//table[@id='MEtblManifestDetailsList']//tr[1]//td[5]"));
		  String column5Text = alldata.getText();
		  WebElement  button = driver.findElement(By.id("MEPost"));
		 if( column5Text.equals("Open")) {
			 Assert.assertFalse(button.isEnabled(), "The button is not disabled.");
			 System.out.println("The post button  disabled.");
	}
		 }
	public void Carriers(String carrier) {
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='MEddlCarriers']"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(carrier);
	}
	public void OpenManifestExplorer() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_24")).click(); // To click on VS
		Thread.sleep(3000);
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