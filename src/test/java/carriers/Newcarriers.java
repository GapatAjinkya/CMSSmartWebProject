package carriers;

import java.time.Duration;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Newcarriers {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Newcarriers");

	@BeforeMethod
	public void setup() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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

		String expectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		String actualTitle = driver.getTitle();
		//assert actualTitle.equalsIgnoreCase(expectedTitle) : "Title didn't match";
		System.out.println("Title Matched");
		Thread.sleep(10000);
	}

	@AfterMethod
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}

	@Test(priority = 0)

	public void newcarriers() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");

		WebElement carriers = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_44']")));

		carriers.click();

		logger.info("Click on Carriers successful");

		WebElement carriersaccount = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_440']")));

		carriersaccount.click();

		logger.info("Click on carriers account successful");

		WebElement Search = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("CASFtxtSearch")));

		Search.sendKeys("afad");
		Thread.sleep(3000);
		logger.info(" Search successful");

		WebElement ok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='CASFOkClick()']")));

		ok.click();
		Thread.sleep(3000);
		logger.info(" ok click successful");

		WebElement error=driver.findElement(By.id("btnErrorBoxOk"));
		boolean errortab=driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
		Thread.sleep(5000);
		if(errortab)
		{
			WebElement errorText=driver.findElement(By.id("errorMsg"));
			String text=errorText.getText();
			wait.until(ExpectedConditions.visibilityOf(error));
			wait.until(ExpectedConditions.elementToBeClickable(error));
			error.click();
			logger.info("Test case Fail Because- "+text);
			Thread.sleep(3000);
		}else {
			logger.info("Department Search ");
		}

		Thread.sleep(3000);
		WebElement addbutton = driver.findElement(By.id("CAAddbutton"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAAddbutton")));
		wait.until(ExpectedConditions.elementToBeClickable(addbutton));
		addbutton.click();
		logger.info("Click on add button successful");
		Thread.sleep(3000);

		String carriercode = "UPSPS_Test";
		String Description = "Proshiptestag";
		String Account="12141315";
		String SCAC="903";
		String Type="UPS -- United Parcel Service";
		String ServerType="ProShip";
		String Organization="ALL";
		String EODReport="NONE";
		String Shippercode="DAR";
		String contactname="CMS";
		String Moduletype="BWTI_UPS.UPS";
		String Services="Test";


		Thread.sleep(6000);
		WebElement dropdown = driver.findElement(By.id("CAF_cmbType"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(Type);
		logger.info(" Type Select  successful");
		Thread.sleep(3000);



		WebElement code = driver.findElement(By.id("CAF_txtCode"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtCode")));
		code.sendKeys(carriercode);
		logger.info("carrier code add  successful");
		Thread.sleep(3000);
		WebElement Des = driver.findElement(By.id("CAF_txtDescription"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtDescription")));
		Des.sendKeys(Description);
		logger.info("Description add  successful");

		WebElement account = driver.findElement(By.id("CAF_txtAccount"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtAccount")));
		account.sendKeys(Account);
		logger.info("Account add  successful");

		WebElement scac = driver.findElement(By.id("CAF_txtSCAC"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtSCAC")));
		scac.sendKeys(SCAC);
		logger.info("scac add  successful");

		WebElement Shipcode = driver.findElement(By.id("CAF_txtShipperCode"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtShipperCode")));
		Shipcode.sendKeys(Shippercode);
		logger.info("Shipper code add  successful");



		WebElement Server = driver.findElement(By.id("CAF_cmbServer"));
		Select Servertype = new Select(Server);
		Servertype.selectByVisibleText(ServerType);
		logger.info(" Server Type Select  successful");


		WebElement organization = driver.findElement(By.id("CAF_cmbOrgs"));
		Select organizationselect = new Select(organization);
		organizationselect.selectByVisibleText(Organization);
		logger.info(" organization  Select  successful");


		WebElement Name = driver.findElement(By.id("CAF_txtName"));
		Name.sendKeys(contactname);
		logger.info(" contact name  Select  successful");


		WebElement EOD = driver.findElement(By.id("CAF_cmbReports"));
		Select EODreport = new Select(EOD);
		EODreport.selectByVisibleText(EODReport);
		logger.info(" EOD  Select  successful");

		WebElement cmbModule = driver.findElement(By.id("CAF_cmbModule"));
		Select SelectM = new Select(cmbModule);
		SelectM.selectByVisibleText(Moduletype);
		logger.info(" Module type Select  successful");

		WebElement Service = driver.findElement(By.id("CAF_txtServices1"));
		Service.sendKeys(Services);

		logger.info(" Service Select  successful");

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

		WebElement okclick = driver.findElement(By.xpath("//button[@onclick='CAF_OkClick()']"));
		okclick.click();
		logger.info(" Click on ok successful");
		Thread.sleep(4000);
	   boolean isButtonVisible = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();

	 if (isButtonVisible)
	 {
		 WebElement text=driver.findElement(By.id("errorMsg"));
		 String errortext=text.getText();
		 WebElement button = driver.findElement(By.id("btnErrorBoxOk"));
           button.click();

           Assert.fail("Test case Fail Because -- "+errortext);
	 }else {

		 logger.info("Department  Selected successful"+"Test case Pass");
	 }
	}
	 @Test(enabled = false)
	 public void international() throws InterruptedException {

			WebElement Configuration = driver.findElement(By.id("menu_item_4"));
			wait.until(ExpectedConditions.visibilityOf(Configuration));
			wait.until(ExpectedConditions.elementToBeClickable(Configuration));
			Configuration.click();
			Thread.sleep(5000);
			logger.info("Click on Configuration successful");

			WebElement carriers = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_44']")));

			carriers.click();

			logger.info("Click on Carriers successful");

			WebElement carriersaccount = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_440']")));

			carriersaccount.click();
			Thread.sleep(5000);
			logger.info("Click on carriers account successful");

			WebElement Search = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.id("CASFtxtSearch")));

			Search.sendKeys("afad");
			Thread.sleep(5000);
			logger.info(" Search successful");

			WebElement ok = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='CASFOkClick()']")));

			ok.click();
			Thread.sleep(5000);
			logger.info(" ok click successful");

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
				logger.info("Department Search ");
			}


			WebElement addbutton = driver.findElement(By.id("CAAddbutton"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAAddbutton")));
			wait.until(ExpectedConditions.elementToBeClickable(addbutton));
			addbutton.click();
			logger.info("Click on add button successful");
			Thread.sleep(8000);

			String carriercode = "FEX_Test1";
			String Description = "FedEx US FSMS";
			String Account="12141315";
			String SCAC="903";
			String Type="FEX -- Federal Express";
			String ServerType="FedEx Ship Manager® Server";
			String Organization="ALL";
			String EODReport="NONE";
			String Label="CMS";
			String FsmsServer="23.254.204.198;2000";
			String MPS="No";
			String Meter="119183328";
			String contactname="CMS";
			String Services="Test";


			Thread.sleep(6000);
			WebElement dropdown = driver.findElement(By.id("CAF_cmbType"));
			Select select = new Select(dropdown);
			select.selectByVisibleText(Type);
			logger.info(" Type Select  successful");



			WebElement code = driver.findElement(By.id("CAF_txtCode"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtCode")));
			code.sendKeys(carriercode);
			logger.info("carrier code add  successful");

			WebElement Des = driver.findElement(By.id("CAF_txtDescription"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtDescription")));
			Des.sendKeys(Description);
			logger.info("Description add  successful");

			WebElement account = driver.findElement(By.id("CAF_txtAccount"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtAccount")));
			account.sendKeys(Account);
			logger.info("Account add  successful");


			WebElement scac = driver.findElement(By.id("CAF_txtSCAC"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAF_txtSCAC")));
			scac.sendKeys(SCAC);
			logger.info("scac add  successful");


			WebElement SelectFsmsServer = driver.findElement(By.id("CAF_cmbFsmsServer"));
			Select FS = new Select(SelectFsmsServer);
			FS.selectByVisibleText(FsmsServer);
			logger.info("Fsms Server add  successful");


			WebElement organization = driver.findElement(By.id("CAF_cmbOrgs"));
			Select organizationselect = new Select(organization);
			organizationselect.selectByVisibleText(Organization);
			logger.info(" organization  Select  successful");


			WebElement Name = driver.findElement(By.id("CAF_txtName"));
			Name.sendKeys(contactname);
			logger.info(" contact name  Select  successful");


			WebElement EOD = driver.findElement(By.id("CAF_cmbReports"));
			Select EODreport = new Select(EOD);
			EODreport.selectByVisibleText(EODReport);
			logger.info(" EOD  Select  successful");


			WebElement lableSource = driver.findElement(By.id("CAF_cmbLabelType"));
			Select ls = new Select(lableSource);
			ls.selectByVisibleText(Label);
			logger.info(" lable   Select  successful");
			Thread.sleep(3000);


			WebElement use = driver.findElement(By.id("CAF_cmbFsmsUseMPS"));
			Select Selectuse = new Select(use);
			Selectuse.selectByVisibleText(MPS);
			logger.info(" MPS  Select  successful");
			Thread.sleep(3000);

			WebElement Meteradd = driver.findElement(By.id("CAF_txtMeter"));
			Meteradd.sendKeys(Meter);
			logger.info(" Meter  add  successful");
			Thread.sleep(3000);

			WebElement Service = driver.findElement(By.id("CAF_txtServices1"));
			Service.sendKeys(Services);

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

			WebElement okclick = driver.findElement(By.xpath("//button[@onclick='CAF_OkClick()']"));
			okclick.click();
			logger.info(" Click on ok successful");

		   Thread.sleep(5000);
		   boolean isButtonVisible = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();

		 if (isButtonVisible)
		 {
			 WebElement text=driver.findElement(By.id("errorMsg"));
			 String errortext=text.getText();
			 WebElement button = driver.findElement(By.id("btnErrorBoxOk"));
	           button.click();

	           Assert.fail("Test case Fail Because -- "+errortext);
		 }else {

			 logger.info("Department  Selected successful"+"Test case Pass");
		 }

	}
}