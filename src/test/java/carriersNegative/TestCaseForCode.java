package carriersNegative;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCaseForCode {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("CarriersSearch");

	@Test(priority = 0)
	public void testcase() throws InterruptedException {
		CarrierAccount();
		AddButton();
		CodeEnter("asdf2@%a101");
		Okbutton();
		captureError();
	}

	@Test(priority = 1)
	public void testBlanck() throws InterruptedException {

		WebElement txtCodeBWF = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtCode")));
		txtCodeBWF.clear();
		txtCodeBWF.sendKeys("");
		Okbutton();
		Thread.sleep(8000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		String ErrorText = errorMessage.getText();
		// logger.error("Expected error message -"+ErrorText);
		System.out.println("Expected error message" + ErrorText);
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String expectedErrorMessage = "CODE Field cannot be blank. Please try again.";
		String actualErrorMessage = errorMessage.getText();
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Incorrect error message");
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
	}
	@Test(enabled = false)
	public void TestChekExistingcode() throws InterruptedException {
		newcarriers();
	}

	public void captureError() throws InterruptedException {
		Thread.sleep(8000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String expectedErrorMessage = "Invalid Code length.";
		String actualErrorMessage = errorMessage.getText();
		logger.info("Actual Error Message "+actualErrorMessage);
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Incorrect error message");
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
	}
	public void AddButton() throws InterruptedException {
		Thread.sleep(5000);
		WebElement add = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='CAAddbutton']")));
		add.click();
	}
	public void CodeEnter(String Code) throws InterruptedException {
		Thread.sleep(10000);
		WebElement txtCodeBWF = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtCode")));
		txtCodeBWF.sendKeys(Code);
		Thread.sleep(5000);
		WebElement Des = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtDescription")));
		Des.sendKeys("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/");
	}
	
	public void CarrierAccount() throws InterruptedException {
		WebElement Configuration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_4")));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		 logger.info("Clickon Configuration successful");
		Thread.sleep(5000);
		WebElement carriers = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_44']")));
		wait.until(ExpectedConditions.elementToBeClickable(carriers));
		carriers.click();
		 logger.info("Click on Carriers successful");
		Thread.sleep(5000);
		WebElement CarrierAccounts = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='menu_item_440']")));
		wait.until(ExpectedConditions.elementToBeClickable(CarrierAccounts));
		CarrierAccounts.click();
		Thread.sleep(8000);
		WebElement ok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='CASFOkClick()']")));
		ok.click();
	}
	
	public void newcarriers() throws InterruptedException {
	    String carriercode ="FEX_Test_A";
		String Description = "FedEx US FSMS";
		String Account="12141315";
		String SCAC="903";
		String Type="FEX -- Federal Express";
		String ServerType="FedEx Ship Manager® Server";
		String Organization="ALL";
		String EODReport="NONE";
		String Shippercode="DAR";
		String contactname="CMS";
		String Moduletype="BWTI_UPS.UPS";
		String Services="Test";
		
		String Label="CMS";
		String FsmsServer="23.254.204.198;2000";
		String MPS="No";
		String Meter="119183328";
		Thread.sleep(15000);
		WebElement dropdown = driver.findElement(By.id("CAF_cmbType"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(Type);
		logger.info(" Type Select  successful");
		Thread.sleep(3000);
		
		WebElement Server = driver.findElement(By.id("CAF_cmbServer"));
		Select Servertype = new Select(Server);
		Servertype.selectByVisibleText(ServerType);
		logger.info(" Server Type Select  successful");
		Thread.sleep(3000);
		
		WebElement code = driver.findElement(By.id("CAF_txtCode"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtCode")));
		code.clear();
		code.sendKeys(carriercode);
		logger.info("carrier code add  successful");
		Thread.sleep(3000);
		WebElement Des = driver.findElement(By.id("CAF_txtDescription"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtDescription")));
		Des.clear();
		Des.sendKeys(Description);
		logger.info("Description add  successful");
		
		WebElement account = driver.findElement(By.id("CAF_txtAccount"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtAccount")));
		account.sendKeys(Account);
		logger.info("Account add  successful");
		Thread.sleep(3000);
		WebElement scac = driver.findElement(By.id("CAF_txtSCAC"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtSCAC")));
		scac.sendKeys(SCAC);
		logger.info("scac add  successful");
		Thread.sleep(3000);

		WebElement organization = driver.findElement(By.id("CAF_cmbOrgs"));
		Select organizationselect = new Select(organization);
		organizationselect.selectByVisibleText(Organization);
		logger.info(" organization  Select  successful");
		Thread.sleep(3000);
		
		WebElement Name = driver.findElement(By.id("CAF_txtName"));
		Name.sendKeys(contactname);
		logger.info(" contact name  Select  successful");
		Thread.sleep(3000);
		
		WebElement EOD = driver.findElement(By.id("CAF_cmbReports"));
		Select EODreport = new Select(EOD);
		EODreport.selectByVisibleText(EODReport);
		logger.info(" EOD  Select  successful");
		Thread.sleep(3000);
		
boolean cmbModule = driver.findElement(By.id("CAF_txtShipperCode")).isDisplayed();
		 if (cmbModule)
		 {	
			 WebElement cmbModuleS = driver.findElement(By.id("CAF_cmbModule"));
				Select SelectM = new Select(cmbModuleS);
				SelectM.selectByVisibleText(Moduletype);
				logger.info(" Module type Select  successful");
		 }
				
		WebElement Service = driver.findElement(By.id("CAF_txtServices1"));
		Service.sendKeys(Services);
		
boolean Shipcode = driver.findElement(By.id("CAF_txtShipperCode")).isDisplayed();
		 if (Shipcode)
		 {	
			 WebElement ShipcodeSelect = driver.findElement(By.id("CAF_txtShipperCode"));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtShipperCode")));
				ShipcodeSelect.sendKeys(Shippercode);
				logger.info("Shipper code add  successful");
		 }
		logger.info(" Service Select  successful");
		Thread.sleep(3000);
		WebElement checkbox = driver.findElement(By.id("CAF_chkHoliday"));
		if(checkbox.isSelected()) 
		{
			logger.info(" checkbox is all ready Selected  ");
		}else
		{
			logger.info(" checkbox is  Selected  ");
			checkbox.click();
		}
		Thread.sleep(3000);
		
		//To Fedex
		
 boolean lableSource = driver.findElement(By.id("CAF_cmbLabelType")).isDisplayed();
		 if (lableSource)
		 {
			 WebElement SelectLS = driver.findElement(By.id("CAF_cmbLabelType"));
				Select ls = new Select(SelectLS);
				ls.selectByVisibleText(Label);
				logger.info(" lable   Select  successful");
				Thread.sleep(3000);
		}else {
			System.out.println("Label Source is not present");
		}
		 Thread.sleep(3000);
boolean SelectFsmsServer = driver.findElement(By.id("CAF_cmbFsmsServer")).isDisplayed();
		 if (SelectFsmsServer)
		 {
			 WebElement SelectFsmsServers = driver.findElement(By.id("CAF_cmbFsmsServer"));
				Select FS = new Select(SelectFsmsServers);
				FS.selectByVisibleText(FsmsServer);                     //--------------------------
				logger.info("Fsms Server add  successful");
		 }else {
			 System.out.println("SelectFsmsServer present");
		 }
		
boolean use = driver.findElement(By.id("CAF_cmbFsmsUseMPS")).isDisplayed();
		 if (use)
		 {
			 WebElement useselect = driver.findElement(By.id("CAF_cmbFsmsUseMPS"));
				Select Selectuse = new Select(useselect);
				Selectuse.selectByVisibleText(MPS);
				logger.info(" MPS  Select  successful");
				Thread.sleep(3000);
		 }else {
			 System.out.println("use not present");
		 }
			
boolean Meteradd = driver.findElement(By.id("CAF_cmbFsmsUseMPS")).isDisplayed();
		 if (Meteradd)
		 {
				WebElement Meteraddsend = driver.findElement(By.id("CAF_txtMeter"));
				Meteraddsend.sendKeys(Meter);
				logger.info(" Meter  add  successful");
				Thread.sleep(3000);
		 }else {
			 System.out.println("Meteradd present");
		 }
		WebElement okclick = driver.findElement(By.xpath("//button[@onclick='CAF_OkClick()']"));
		okclick.click();
		logger.info(" Click on ok successful");	

	 Thread.sleep(10000);

	}
			
	public void Okbutton() throws InterruptedException {
		WebElement okbutton = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='CAF_OkClick()']")));
		okbutton.click();
	}
	@BeforeClass
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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

	@AfterClass
	public void teardown() {
		driver.quit();

	}
}
