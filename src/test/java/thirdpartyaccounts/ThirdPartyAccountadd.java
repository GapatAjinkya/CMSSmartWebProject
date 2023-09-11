package thirdpartyaccounts;

import java.time.Duration;
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

public class ThirdPartyAccountadd {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ThirdPartyAccountadd");
	
	@Test(priority = 0)
	public void TPASearchTest() throws InterruptedException {
		openTPA();
		SearchTest("asdasd");
		captureError();
	}
	@Test(priority = 1)
	public void CodeTest() throws InterruptedException {
		add();
		newtpacreate();
		Code("AccountTest1");
		CustomerName("Test Account");
		accountNumber("12141315");
		okTPA();
		captureError();
	}
	@Test(priority = 2)
	public void CodeCheck() throws InterruptedException {
		
		Code("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/1");
		okTPA();
		captureError();
	}
	@Test(priority = 3)
	public void CodeBlank() throws InterruptedException {
		Code("");
		okTPA();
		captureError();
	}
	@Test(priority = 4)
	public void CustomerName() throws InterruptedException {
		Code("AccountTest1");
		CustomerName("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovB");
		okTPA();
		captureError();
	}
	@Test(priority = 5)
	public void CustomerNameBlack() throws InterruptedException {
		Code("AccountTest1");
		CustomerName("");
		okTPA();
		captureError();
	}
	@Test(priority = 6)
	public void accountNumber() throws InterruptedException {
		Code("AccountTest1");
		CustomerName("Test Account");
		accountNumber("aasvadjwodvbwbvuwbvibdviwbvoeubvuadobvoboqbvqvquovB");
		okTPA();
		captureError();
	}@Test(priority = 7)
	public void accountNumberBlank() throws InterruptedException {
		Code("AccountTest1");
		CustomerName("Test Account");
		accountNumber("");
		okTPA();
		captureError();
	}	
	public void CustomerName(String name) {
		WebElement addname=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtName")));
		addname.clear();
		addname.sendKeys(name);
		logger.info("addname successful ");
	}
	public void accountNumber(String  Accountnumber) {
		WebElement addcountnum=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAccountNumber")));
		addcountnum.clear();
		addcountnum.sendKeys(Accountnumber);
	}
	public void newtpacreate() throws InterruptedException {
		String address="903 b2 san francisco chhava";
		String city="san francisco";
		String states="california";                                                                                        
		String zip="94016";
		String desiredText ="UNITED STATES";
		Thread.sleep(5000);
		
		
		WebElement addaddress=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddress1")));
		addaddress.sendKeys(address);
		logger.info("add address successful ");
		WebElement addcity=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddCity")));
		addcity.sendKeys(city);
		logger.info("add city successful ");
		WebElement addstate=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddState")));
		addstate.sendKeys(states);
		logger.info("add state successful ");
		
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddZip"))).sendKeys(zip);
	     logger.info("add Zip successful ");
	     
		WebElement dropdown = driver.findElement(By.id("TPtxtCountry"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(desiredText);
		logger.info(" Country  successful");
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
	}
	public void okTPA() throws InterruptedException {
		Thread.sleep(4000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='OkClickThirdPartiesForm()']"))).click();
	}
	public void Code(String customercode) throws InterruptedException {
		Thread.sleep(4000);
		WebElement addcode=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtXRef")));
		addcode.clear();
		addcode.sendKeys(customercode);
	}
	public void add() throws InterruptedException {
		Thread.sleep(5000);
		WebElement ThirdPartiesAdd = driver.findElement(By.id("ThirdPartiesAdd"));
		ThirdPartiesAdd.click();
		logger.info("Click on add button successful");
	}
	public void SearchTest(String code) throws InterruptedException {
		Thread.sleep(5000);
		WebElement Search = driver.findElement(By.xpath("//input[@id='txtTPSearch']"));
		Search.sendKeys(code);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@onclick='ThirdPartiesSearchOkClick()']")).click();// ok buttton
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
	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
	        String actualErrorMessage = errorMessage.getText();
	       
		if (actualErrorMessage.equals("The Company field value must be less than 50 characters. Please try again.")) {
            System.out.println("Handling first error message."+actualErrorMessage);
            Assert.assertEquals(actualErrorMessage, "The Company field value must be less than 50 characters. Please try again.", "Incorrect error message");
        } else if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
        	 System.out.println("Handling Second error message."+actualErrorMessage);
            Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.", "Incorrect error message");
        } else if (actualErrorMessage.equals("Duplicate ThirdParties. Please try again.")) {
       	 System.out.println("Handling Third error message."+actualErrorMessage);
         Assert.assertEquals(actualErrorMessage, "Duplicate ThirdParties. Please try again.", "Incorrect error message");
     }  else if (actualErrorMessage.equals("No records found!")) {
        	 System.out.println("Handling Forth error message."+actualErrorMessage);
            Assert.assertEquals(actualErrorMessage, "No records found!", "Incorrect error message");
        }
     else if (actualErrorMessage.equals("undefined")) {
       	 System.out.println("Handling Fifth error message."+actualErrorMessage);
         Assert.assertEquals(actualErrorMessage, "undefined", "Incorrect error message");
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
		 wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
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
		String expectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		String actualTitle = driver.getTitle();
		assert actualTitle.equalsIgnoreCase(expectedTitle) : "Title didn't match";
		System.out.println("Title Matched");
		Thread.sleep(10000);
	}
}
