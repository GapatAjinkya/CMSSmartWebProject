package localTest;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Departments");
	
	
	@Test(dataProvider = "Logindata")
	public void Login (String username,String pass) throws InterruptedException {
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(5000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys(username);
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys(pass);
		  WebElement rememberMe = driver.findElement(By.id("chkRememberMe"));
		  rememberMe.  click(); // chkRememberMe
		WebElement loginButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		loginButton.click();

		captureError();
	}

	@DataProvider(name="Logindata")
	public Object[][] getdata(){
		
		Object[][] data= {{"admin1","password"},
			            	{"admin","Nilesh@123"},
			        	{"nilesh","Nilesh@123aa"},
				{"Wrong","Wrong"},
			        	};
		return data;
	}
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
	        String actualErrorMessage = errorMessage.getText();
	       
		if (actualErrorMessage.equals("Invalid username.")) {
            System.out.println("Handling first error message."+actualErrorMessage);
            Assert.assertEquals(actualErrorMessage, "Invalid username.", "Incorrect error message");
        } else if (actualErrorMessage.equals("Wrong password. Please try again.")) {
        	 System.out.println("Handling Second error message."+actualErrorMessage);
            Assert.assertEquals(actualErrorMessage, "Wrong password. Please try again.", "Incorrect error message");
        }  else {
            System.out.println("Unexpected error message: " + actualErrorMessage);
        }
		
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
		}
	
	@BeforeClass
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
	}
}
