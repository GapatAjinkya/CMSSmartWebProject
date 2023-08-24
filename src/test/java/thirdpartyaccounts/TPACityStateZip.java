package thirdpartyaccounts;

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

public class TPACityStateZip {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ThirdPartyAccountadd");
	
	@Test(priority = 0)
	public void Testcity() throws InterruptedException {
		openTPA();
		SearchTest("");
		add();
		addaccount();
		City("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovB");
		State("california");
		Zip("94016");
		okTPA();
		captureError();
		
		City("");
		okTPA();
		captureError();
	}
	public void TestState() {
		
	}
	public void City(String city) throws InterruptedException {
		Thread.sleep(3000);
		WebElement addcity=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddCity")));
		addcity.clear();
		addcity.sendKeys(city);
	}
	public void State(String states) throws InterruptedException {
		Thread.sleep(2000);
		WebElement addstate=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddState")));
		addstate.clear();
		addstate.sendKeys(states);
	}
	public void Zip(String zip) throws InterruptedException {
		Thread.sleep(2000);
		WebElement ZipAdd= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddZip")));
		ZipAdd.clear();
		ZipAdd.sendKeys(zip);
	}
		
public void openTPA() throws InterruptedException {
		
		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");
		WebElement SupportTables = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#menu_item_45")));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");
		Thread.sleep(5000);
		
		WebElement tpba = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_459']")));
		tpba.click();
		Thread.sleep(5000);
		logger.info("Click on Third Party Billing Accounts successful");		
	}
public void SearchTest(String code) throws InterruptedException {
	Thread.sleep(3000);
	WebElement Search = driver.findElement(By.xpath("//input[@id='txtTPSearch']"));
	Search.sendKeys(code);

	driver.findElement(By.xpath("//button[@onclick='ThirdPartiesSearchOkClick()']")).click();// ok buttton
}
public void add() throws InterruptedException {
	Thread.sleep(3000);
	WebElement ThirdPartiesAdd = driver.findElement(By.id("ThirdPartiesAdd"));
	ThirdPartiesAdd.click();
	logger.info("Click on add button successful");
}
	public void addaccount() throws InterruptedException {
		String customercode = "AccountTest1";
		String Customername = "Test Account";
		String Accountnumber="12141315";
		String address="903 b2 san francisco chhava";                                                                                        
		String desiredText ="UNITED STATES";
		
		Thread.sleep(3000);
		WebElement addcode=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtXRef")));
		addcode.sendKeys(customercode);
		logger.info("addcode successful ");
		WebElement addname=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtName")));
		addname.sendKeys(Customername);
		logger.info("addname successful ");
		WebElement addcountnum=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAccountNumber")));
		addcountnum.sendKeys(Accountnumber);
		logger.info("add  AccountNumber successful ");
		WebElement addaddress=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddress1")));
		addaddress.sendKeys(address);
		logger.info("add address successful ");
		Thread.sleep(2000);
		WebElement dropdown = driver.findElement(By.id("TPtxtCountry"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(desiredText);
		logger.info(" Country  successful");
		
		//
		String ContactName="jon";  //TPtxtContact
		String EmailAddress="cms@gmail.com";//TPtxtEmail
		String Phone="1234567890";//TPtxtAddPhone
		String fax="1225588";//TPtxtFax
		
		WebElement addcn=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtContact")));
		addcn.sendKeys(ContactName);
		logger.info("add Contact Name successful ");	
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtContact"))).sendKeys(ContactName);
        logger.info("add Contact Name successful ");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtEmail"))).sendKeys(EmailAddress);
        logger.info("add Email Address successful ");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddPhone"))).sendKeys(Phone);
        logger.info("add Phone successful ");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtFax"))).sendKeys(fax);
        logger.info("add Phone successful ");
 
	}public void okTPA() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='OkClickThirdPartiesForm()']"))).click();
	}
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String actualErrorMessage = errorMessage.getText();
		if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("The Zip field value must be less than 10 characters. Please try again.")) {
			System.out.println("Handling error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "The Zip field value must be less than 10 characters. Please try again.",
					"Incorrect error message");
		} else if (actualErrorMessage.equals("The State field value must be less than 40 characters. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "The State field value must be less than 40 characters. Please try again.", "Incorrect error message");
		}
		else if (actualErrorMessage.equals("The City field value must be less than 50 characters. Please try again.")) {
			System.out.println("Handling  error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage,"The City field value must be less than 50 characters. Please try again.", "Incorrect error message");
		}
		else {
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
		wait = new WebDriverWait(driver, 60);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("nilesh");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("Nilesh@123");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = wait	
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();
		String expectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		String actualTitle = driver.getTitle();
		assert actualTitle.equalsIgnoreCase(expectedTitle) : "Title didn't match";
		System.out.println("Title Matched");
		Thread.sleep(10000);
	}
}
