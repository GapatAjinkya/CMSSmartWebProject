package thirdpartybillingaccounts;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TPBAadd {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("TPBAadd");

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

	@AfterMethod
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}

	@Test
	public void addaccount() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");

		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		wait.until(ExpectedConditions.visibilityOf(SupportTables));
		wait.until(ExpectedConditions.elementToBeClickable(SupportTables));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");
		Thread.sleep(5000);


		WebElement tpba = driver.findElement(By.xpath("//a[@id='menu_item_459']"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_459']")));
		wait.until(ExpectedConditions.visibilityOf(tpba));
		wait.until(ExpectedConditions.elementToBeClickable(tpba));
		tpba.click();
		Thread.sleep(5000);
		logger.info("Click on Third Party Billing Accounts successful");


		WebElement Search = driver.findElement(By.xpath("//input[@id='txtTPSearch']"));
		Search.sendKeys("asdsad");
		Thread.sleep(8000);

		driver.findElement(By.xpath("//button[@onclick='ThirdPartiesSearchOkClick()']")).click();// ok buttton
		Thread.sleep(6000);

		WebElement error=driver.findElement(By.id("btnErrorBoxOk"));
		boolean errortab=driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
		Thread.sleep(6000);
		if(errortab)
		{
			WebElement errorText=driver.findElement(By.id("errorMsg"));
			String text=errorText.getText();
			wait.until(ExpectedConditions.visibilityOf(error));
			wait.until(ExpectedConditions.elementToBeClickable(error));
			error.click();
			logger.info("Test case Fail Because- "+text);
			Thread.sleep(5000);
		}else {
			logger.info("Account Search ");
		}
		Thread.sleep(6000);

		WebElement ThirdPartiesAdd = driver.findElement(By.id("ThirdPartiesAdd"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ThirdPartiesAdd")));
		ThirdPartiesAdd.click();

		logger.info("Click on add button successful");

		String customercode = "AccountTest2";
		String Customername = "Test Account";
		String Accountnumber="12141315";
		String address="903 b2 san francisco chhava";
		String city="san francisco";
		String states="california";
		String zip="94016";
		String desiredText ="UNITED STATES";

		Thread.sleep(8000);
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
		WebElement addcity=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddCity")));
		addcity.sendKeys(city);
		logger.info("add city successful ");
		WebElement addstate=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddState")));
		addstate.sendKeys(states);
		logger.info("add state successful ");

		 wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TPtxtAddZip"))).sendKeys(zip);
	     logger.info("add Zip successful ");

		Thread.sleep(5000);


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
      //
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='OkClickThirdPartiesForm()']"))).click();
        logger.info("add Contact Name successful ");

        Thread.sleep(5000);
		 boolean isButtonVisible = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();

		 if (isButtonVisible)
		 {
			 WebElement text=driver.findElement(By.id("errorMsg"));
			 String errortext=text.getText();
			 WebElement button = driver.findElement(By.id("btnErrorBoxOk"));
	            button.click();

	            Assert.fail("Test case Fail Because- "+errortext);
		 }else {

			 logger.info("Department  Selected successful"+"Test case Pass");

		 }

        List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='ThirdPartiesList']//td"));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(customercode))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "customercode is not found ");
		logger.info("Third Party Billing Accounts Created successful ");


}
}