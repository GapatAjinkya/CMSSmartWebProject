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

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Carrierstask {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Carrierstask");

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

	@AfterMethod
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}

	@Test
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
		Thread.sleep(5000);
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
			logger.info("Carrier Search ");
		}
		Thread.sleep(5000);
		WebElement addbutton = driver.findElement(By.id("CAAddbutton"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CAAddbutton")));
		wait.until(ExpectedConditions.elementToBeClickable(addbutton));
		addbutton.click();
		logger.info("Click on add button successful");
		Thread.sleep(8000);
		
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

//-----------------------------------------------------------------------------------------------------------	
	 
	
	 WebElement Shipvias = driver.findElement(By.id("CAShipViaButton"));
		wait.until(ExpectedConditions.visibilityOf(Shipvias));
		wait.until(ExpectedConditions.elementToBeClickable(Shipvias));
		Shipvias.click();
		Thread.sleep(10000);
		logger.info("Click on Ship vias successful");	
		
		boolean isError = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
		 
		 if (isError)
		 {
		WebElement text=driver.findElement(By.id("errorMsg"));
		WebElement okerror=driver.findElement(By.id("btnErrorBoxOk"));
		wait.until(ExpectedConditions.visibilityOf(okerror));
		wait.until(ExpectedConditions.elementToBeClickable(okerror));
		Thread.sleep(8000);
		okerror.click();
		 System.out.println("Adding new ship via");
		 }else {
			 System.out.println("---");
		 }
		 Thread.sleep(8000);
		 
		 WebElement addshipvia = driver.findElement(By.id("ShipViasAdd"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ShipViasAdd")));
			addshipvia.click();
			logger.info("Click on add Ship vias successful");	
			Thread.sleep(10000);
			
			
//--------------------------For Ship via--------------------------------------
			
			String shipviacode="FEXTest_S_A";
			String ShipviaDescription="FEXGround";
	        String ShipviaServiceFex="GN -- Ground®";
	        String ShipviaServiceUps="GND -- Ground";
			String Carriercode=carriercode+" -- " +Description;          //FEX_Test1 -- FedEx US FSMS
			String SpServices1="Dry Ice";
	
	WebElement selectcarrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbAccountSVF")));
	Select ca=new Select(selectcarrier);
	ca.selectByVisibleText(Carriercode); // FEX_Test1 -- FedEx US FSMS
	System.out.println(Carriercode);

	Thread.sleep(5000);
//To select service 	

	
	
	WebElement ShipviaServicet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbServiceSVF")));
	Select SS=new Select(ShipviaServicet);
	
	if(Type.equals("FEX -- Federal Express")) {
		SS.selectByVisibleText(ShipviaServiceFex);
		logger.info("select service  successful");	
		Thread.sleep(3000);
	}else if(Type.equals("UPS -- United Parcel Service")) {
		SS.selectByVisibleText(ShipviaServiceUps);
		logger.info("select service  successful");	
		Thread.sleep(3000);
	}
	

//To select Payment
	WebElement payment = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbPaymentSVF")));
	Select pay=new Select(payment);
	pay.selectByVisibleText("Shipper");	
	Thread.sleep(2000);
	logger.info("select Payment  successful");	
	//To select bill	
	WebElement bill = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbBillDutyTaxToSVF")));
	Select  billselect=new Select(bill);
	billselect.selectByVisibleText("Recipient");	
	Thread.sleep(2000);
	logger.info("select bill successful");		
	
	WebElement Billas = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbBillAsSVF")));
	Select  BA=new Select(Billas);
	BA.selectByVisibleText("[SAME] Bill As This ShipVia");	
	Thread.sleep(2000);		
	logger.info("select billAs successful");	
	
	WebElement Shipviacode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeSVF")));
	Shipviacode.sendKeys(shipviacode);
	Thread.sleep(2000);	
	logger.info("Send ship viacode  successful");	
	
	WebElement Dis = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionSVF")));
	Dis.sendKeys(ShipviaDescription);
	Thread.sleep(2000);	
	logger.info("Send Discription  successful");
	
	
	
  if(Type.equals("FEX -- Federal Express")) 
  {
	  boolean DeclaredInsuredValue = driver.findElement(By.id("BOX8388608")).isDisplayed();
		
	  if (DeclaredInsuredValue)
		 {
			 WebElement DeclaredInsuredValuet = driver.findElement(By.id("BOX8388608"));
				DeclaredInsuredValuet.click();
				Thread.sleep(2000);	
				logger.info(" DeclaredInsuredValue successful");
				
		 }
	  else 
				 System.out.println(" DeclaredInsuredValue Not  Selected");
			 }
  
  
  if(Type.equals("UPS -- United Parcel Service"))
	 {
	  boolean dryice = driver.findElement(By.id("BOX1024")).isDisplayed();
	  if (dryice)
		 {
			 WebElement dryiceclick = driver.findElement(By.id("BOX1024"));
			 dryiceclick.click();
				Thread.sleep(2000);	
				logger.info(" dryice click successful");
		 }else {
			 System.out.println("dryice Not Selected successful ");
		 }
	 }
  
	Thread.sleep(5000);	
	driver.findElement(By.id("OkClickSVF")).click();
	logger.info(" Click on ok  successful");
	
	boolean ShipviaisError = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
	 
	 if (ShipviaisError)
	 {
		 WebElement errorMsg=driver.findElement(By.id("errorMsg"));
		 logger.info("Error Message -"+errorMsg);
		 WebElement Shipokclick=driver.findElement(By.id("btnErrorBoxOk"));
		 Shipokclick.click();
	 }
	
	
//-------------------------------------------------------------------------------------------------------------	
//Process Shipment 	
	
	logger.info(" Opening Process Shipment Menu");
	Thread.sleep(8000);
	 wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
	WebElement Transaction= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2"))); 	// To click on Transaction
	wait.until(ExpectedConditions.elementToBeClickable(Transaction));
	Transaction.click();
	logger.info(" Click on Transaction Menu Successful");
	Thread.sleep(10000);
	WebElement Process=	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_21")));// To click on Process ShipmentS
	wait.until(ExpectedConditions.elementToBeClickable(Process));
	Process.click();
	logger.info(" Click on Process Shipment Menu Successful");
	
	
	
	 Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();//Customers 
		 Thread.sleep(5000);
		driver.findElement(By.id("txtSCSearch")).sendKeys("AdminVA"); // searchcustomer
		 Thread.sleep(5000);
		WebElement List2 = driver.findElement(By.id("selCutomerList"));
		Select CustomerList2 = new Select(List2);	
		
		CustomerList2.selectByValue("1"); // To select Global
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']"))).click();
		
		Thread.sleep(5000);
		logger.info("Customer Searched");
		 Thread.sleep(5000);
		driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][1][contains(text(), 'AdminVA')]")).click();
	
		driver.findElement(By.id("addressformOk")).click();          // Click on OK 
		Thread.sleep(5000);
		logger.info("Customer Added");
	
	
	
	
	
	
	
	// Select the Shipvia
	Thread.sleep(10000);
	WebElement shipviaSearch = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@onclick,'btnSearch_PS()')]"))); // Search the																								// Shipvias list
	wait.until(ExpectedConditions.elementToBeClickable(shipviaSearch));
	shipviaSearch.click();
	logger.info("Clicked on shipviaSearch");
	
	Thread.sleep(10000);
	WebElement ShipviaSearchcode = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='radCodeSS']")));
	wait.until(ExpectedConditions.elementToBeClickable(ShipviaSearchcode));
	ShipviaSearchcode.click();
	logger.info("shipviaSearch code Selected");
	// Search
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtSCSearchSS"))).sendKeys(shipviacode);
	logger.info("shipviaSearch Enter Value Successful");
	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnSearchOk_PS"))).click();
	logger.info("Click on Ship Via ok search  Successful");
	logger.info("shipvia is selected");
	
	
	 
		driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		logger.info("Manual Weight is fill ");
		
		driver.findElement(By.id("cmdRate")).click();
	
		logger.info("Click on Rate ");
		Thread.sleep(5000);
		WebElement error5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-default'][1]")));
		boolean error6 = error5.isDisplayed();
		 if (error6)
		 {	
			error5.click();
		driver.findElement(By.id("btnShipClick")).click(); //Click on ship
      }
	
	
	
	Thread.sleep(10000);
	}}