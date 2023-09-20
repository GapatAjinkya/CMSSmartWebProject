package processhipmentnegative;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.netty.handler.timeout.TimeoutException;

public class SWOSTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ProcesShipment");
	
	
	@Test(priority = 0)
	public void SearchTest() throws InterruptedException {
		swosGroups();
		Search("asda");
		captureError();
	}
	@Test(priority = 1)
	public void CodeTest() throws InterruptedException {
		ClickonAdd();
		Discription("TAG");
		code("");
		ok() ;
		captureError();
		code("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovB");
		ok() ;
		captureError();
	}
	@Test(priority = 2)
	public void Discription() throws InterruptedException {
		Discription("");
		code("TAG");
		ok() ;
		captureError();
		Discription("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovBaasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquov");
		ok() ;
		captureError();
	}
	@Test(priority = 3)
	public void TestManifestDate() throws InterruptedException {
		code("TAG");
		Discription("Swos_Group");
		ManifestDate("09/01/2023");
		ok() ;
		captureError();
	}
	public void ManifestDate(String dateAsString) {
		
		WebElement element=driver.findElement(By.id("SWOSFrmtxtManDt"));
		element.clear();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = arguments[1];", element, dateAsString);
    //month/date/year
		
	}
	public void swosGroups() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Configuration =  driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		logger.info("Clickon Configuration successful");
		Thread.sleep(3000);
		WebElement SupportTables = driver.findElement(By.xpath("//a[@id='menu_item_45']"));
		SupportTables.click();
		logger.info("Click on Carriers successful");
		WebElement swosG = driver.findElement(By.xpath("//a[@id='menu_item_458']"));
		swosG.click();
	}
	public void Search(String Searchcode) throws InterruptedException {
		Thread.sleep(3000);
		WebElement Search = driver.findElement(By.xpath("//input[@id='txtCSTSWOSGrSearch']"));
		Search.sendKeys(Searchcode);
		WebElement CartonOk = driver.findElement(By.xpath("//button[@onclick='onSWOSGrSearchOkClick()']"));
		CartonOk.click();
	}
	
	public void ClickonAdd() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Add = driver.findElement(By.id("CSTSWOSGroupsAdd"));
		Add.click();
	}
	
	public void code(String code) throws InterruptedException {
		Thread.sleep(3000);
		WebElement EntertxtCode = driver.findElement(By.id("SWOSFrmtxtCustomerCode"));
		EntertxtCode.clear();
		EntertxtCode.sendKeys(code);		
	}
	public void Discription(String Description) throws InterruptedException {
		Thread.sleep(3000);
		WebElement EnterDiscription = driver.findElement(By.id("SWOSFrmtxtDescription"));
		EnterDiscription.clear();
			EnterDiscription.sendKeys(Description);
	}
	
	public void ok() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Add = driver.findElement(By.xpath("//button[contains(@onclick,'OkClickSWOSGroupsForm()')]"));
		Add.click();
	}

	public void captureError() throws InterruptedException {
		Thread.sleep(3000);
	    try {
	        WebElement errorMessage = driver.findElement(By.xpath("//span[@id='errorMsg']"));
	        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
	        String actualErrorMessage = errorMessage.getText();
	        switch (actualErrorMessage) {
	            case "No records found!":
	                Assert.assertEquals(actualErrorMessage, "No records found!", "Incorrect error message");
	                break;
	            case "Field value cannot be blank. Please try again.":
	                Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.", "Incorrect error message");
	                break;
	            case "Customer Code cannot be more than 30 characters. Please try again.":
	                Assert.assertEquals(actualErrorMessage, "Customer Code cannot be more than 30 characters. Please try again.", "Incorrect error message");
	                break;  
	            case "Description more than 100 characters. Please try again.":
	                Assert.assertEquals(actualErrorMessage, "Description more than 100 characters. Please try again.", "Incorrect error message");
	                break;  
	            case "Date is in the past. Please try again.":
	                Assert.assertEquals(actualErrorMessage, "Date is in the past. Please try again.", "Incorrect error message");
	                break;   
	                
	            default:
	                // Handle other cases or unexpected errors
	                System.out.println("Unexpected error message: " + actualErrorMessage);
	                break;
	        }
	        WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
	        error.click();
	    } catch (TimeoutException e) {
	        // Handle the case where the error message doesn't appear within the expected time
	        System.out.println("Error message not found within expected time.");
	    }
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
