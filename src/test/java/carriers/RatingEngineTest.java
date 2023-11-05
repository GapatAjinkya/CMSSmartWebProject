package carriers;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RatingEngineTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("RatingEngineTest");
	private boolean textValue = false;
	
	//For Carrier
	String carriercode = "GEN";
	String Description = "Generic Carrier";
	String Account="12141315";
	String SCAC="903";
	String Type="GEN -- Generic Carrier";
	String ServerType="WL";
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
				
	//--------------------------For Ship via Under That Same Carrier--------------------------------------

		String shipviacode="GEN_Ground";
		String shipviacodenew="GEN_Ground2";
		String ShipviaDescription="Proshiptestag";
		String ShipviaServiceFex = "21264;Ground®;";
		String ShipviaServiceUps = "3686420;Ground;";			          
		String Carriercode=carriercode+" -- " +Description;		
								
	@Test(priority = 0)
public void TestNewCarriers() throws InterruptedException {
		Createnewcarriers();
		CreateShipvia();
		ProcessShipment();
}
	public void Createnewcarriers() throws InterruptedException {
		Thread.sleep(2000);
		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");

		WebElement carriers = driver.findElement(By.xpath("//a[@id='menu_item_44']"));
		carriers.click();
		Thread.sleep(5000);
		logger.info("Click on Carriers successful");

		WebElement carriersaccount = driver.findElement(By.xpath("//a[@id='menu_item_440']"));

		carriersaccount.click();
		Thread.sleep(3000);
		logger.info("Click on carriers account successful");

		WebElement Search = driver.findElement(By.id("CASFtxtSearch"));
		Search.sendKeys("afad");
		logger.info(" Search successful");
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='CASFOkClick()']"));
		ok.click();
		Thread.sleep(3000);
		logger.info(" ok click successful");

		WebElement error = driver.findElement(By.id("btnErrorBoxOk"));
		boolean errortab = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
		Thread.sleep(3000);

		if (errortab) {
			WebElement errorText = driver.findElement(By.id("errorMsg"));
			String text = errorText.getText();
			error.click();
			Thread.sleep(3000);
			if(Type.equalsIgnoreCase("TNL -- TNT NL")) {
				
				
			}
		} else {
			logger.info("Carrier Search ");
		}
		Thread.sleep(2000);
		WebElement addbutton = driver.findElement(By.id("CAAddbutton"));
		addbutton.click();
		logger.info("Click on add button successful");

		Thread.sleep(2000);
		WebElement dropdown = driver.findElement(By.id("CAF_cmbType"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(Type);
		logger.info(" Type Select  successful");
		Thread.sleep(3000);

		WebElement Server = driver.findElement(By.id("CAF_cmbServer"));
		Select Servertype = new Select(Server);
		//Servertype.selectByVisibleText(ServerType);
		logger.info(" Server Type Select  successful");
		Thread.sleep(2000);

		WebElement code = driver.findElement(By.id("CAF_txtCode"));
		code.sendKeys(carriercode);
		logger.info("carrier code add  successful");
		
		WebElement Des = driver.findElement(By.id("CAF_txtDescription"));
		Des.sendKeys(Description);
		logger.info("Description add  successful");

		WebElement account = driver.findElement(By.id("CAF_txtAccount"));
		account.sendKeys(Account);
		logger.info("Account add  successful");

		WebElement scac = driver.findElement(By.id("CAF_txtSCAC"));
		scac.sendKeys(SCAC);
		logger.info("scac add  successful");
		

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

		boolean cmbModule = driver.findElement(By.id("CAF_txtShipperCode")).isDisplayed();
		if (cmbModule) {
			WebElement cmbModuleS = driver.findElement(By.id("CAF_cmbModule"));
			Select SelectM = new Select(cmbModuleS);
			SelectM.selectByVisibleText(Moduletype);
			logger.info(" Module type Select  successful");
		}

		WebElement Service = driver.findElement(By.id("CAF_txtServices1"));
		Service.sendKeys(Services);

		boolean Shipcode = driver.findElement(By.id("CAF_txtShipperCode")).isDisplayed();
		if (Shipcode) {
			WebElement ShipcodeSelect = driver.findElement(By.id("CAF_txtShipperCode"));
			ShipcodeSelect.sendKeys(Shippercode);
			logger.info("Shipper code add  successful");
		}
		logger.info(" Service Select  successful");
		
		WebElement checkbox = driver.findElement(By.id("CAF_chkHoliday"));
		if (checkbox.isSelected()) {
			logger.info(" checkbox is all ready Selected  ");
		} else {
			logger.info(" checkbox is  Selected");
			checkbox.click();
		}

		// To Fedex

		boolean lableSource = driver.findElement(By.id("CAF_cmbLabelType")).isDisplayed();
		if (lableSource) {
			WebElement SelectLS = driver.findElement(By.id("CAF_cmbLabelType"));
			Select ls = new Select(SelectLS);
			ls.selectByVisibleText(Label);
			logger.info(" lable   Select  successful");
			
		} else {
			System.out.println("Label Source is not present");
		}
		boolean SelectFsmsServer = driver.findElement(By.id("CAF_cmbFsmsServer")).isDisplayed();
		if (SelectFsmsServer) {
			WebElement SelectFsmsServers = driver.findElement(By.id("CAF_cmbFsmsServer"));
			Select FS = new Select(SelectFsmsServers);
			FS.selectByVisibleText(FsmsServer); // --------------------------
			logger.info("Fsms Server add  successful");
		} else {
			System.out.println("SelectFsmsServer present");
		}

		boolean use = driver.findElement(By.id("CAF_cmbFsmsUseMPS")).isDisplayed();
		if (use) {
			WebElement useselect = driver.findElement(By.id("CAF_cmbFsmsUseMPS"));
			Select Selectuse = new Select(useselect);
			Selectuse.selectByVisibleText(MPS);
			logger.info(" MPS  Select  successful");
		
		} else {
			System.out.println("use not present");
		}

		boolean Meteradd = driver.findElement(By.id("CAF_cmbFsmsUseMPS")).isDisplayed();
		if (Meteradd) {
			WebElement Meteraddsend = driver.findElement(By.id("CAF_txtMeter"));
			Meteraddsend.sendKeys(Meter);
			logger.info(" Meter  add  successful");
		
		} else {
			System.out.println("Meteradd present");
		}
		WebElement okclick = driver.findElement(By.xpath("//button[@onclick='CAF_OkClick()']"));
		okclick.click();
		Thread.sleep(3000);
		logger.info(" Click on ok successful");
		String text = "You are being auto-redirected to the Rat. Eng. configuration screen to complete the setup.";
		WebElement popup = driver.findElement(By.id("btnErrorBoxOk"));
		if (popup.isDisplayed()) {
			WebElement RatingEngineTxt = driver.findElement(By.id("btnErrorBoxOk"));
			String RETxt = RatingEngineTxt.getText();
			System.out.println(RETxt);
			popup.click();
			
			if(Type.equalsIgnoreCase("FEX -- Federal Express")) {
			RatingConfiguration();
			GeneralConfiguration();
			ProNumber();
			FuleSurcharge();
			Reokclick();	
			}else if(Type.equalsIgnoreCase("GEN -- Generic Carrier")){
				genreteng();
			}
		} else {
			WebElement buttton	= wait.until(
					ExpectedConditions.elementToBeClickable(By.id("CARatingbutton")));
			buttton.click();
			logger.info(" Click on Rating button  successful");
		}
		Thread.sleep(3000);

		
	}
	public void genreteng() throws InterruptedException {
		
		Thread.sleep(3000);
		WebElement MPFrom = driver.findElement(By.id("txtTrackingFrom"));
		MPFrom.clear();
		MPFrom.sendKeys("1111111111");
		WebElement MPto = driver.findElement(By.id("txtTrackingTo"));
		MPto.clear();
		MPto.sendKeys("9999999999");
		WebElement current = driver.findElement(By.id("txtTracking"));
		current.clear();
		current.sendKeys("1111111111");
		WebElement CarrierLabel = driver.findElement(By.xpath("//select[@id='cmbLabel']"));
		Select Selectc=new Select(CarrierLabel);
		Selectc.selectByValue("WL_GENERIC_CARRIER");
		WebElement CarrierManifest = driver.findElement(By.xpath("//select[@id='cmbManifest']"));
		Select data=new Select(CarrierManifest);
		data.selectByValue("WL_MANIFEST.RPT");
		
			String Origin = "USA";
			String radiotext = "Point-To-Point Rating";
			String[] elementIds = {
		            "Rated Carrier Account ?",
		            "Use Product Classes?",
		        };
		for(String element:elementIds) {		
			WebElement RatedCarrierAccount = driver.findElement(By.xpath("//key[text()='"+element+"']/preceding-sibling::input[@type='checkbox']")); // checkbox for Rated Carrier Account
			RatedCarrierAccount.click();
		}
			Thread.sleep(3000);
		
			WebElement click = driver
					.findElement(By.xpath("//key[text()='" + Origin + "']/preceding-sibling::input[@type='radio']"));
			click.click();	
			WebElement clickradiotext = driver
					.findElement(By.xpath("//key[text()='" + radiotext + "']/preceding-sibling::input[@type='radio']"));
			clickradiotext.click();
			
			WebElement VolFactor = driver.findElement(By.id("txtVolFactor"));
			VolFactor.clear();
			VolFactor.sendKeys("1");
			WebElement OriginAirport = driver.findElement(By.id("txtAirportCode"));
			OriginAirport.clear();
			OriginAirport.sendKeys("US");
			WebElement OriginPoint = driver.findElement(By.id("txtCityCode"));
			OriginPoint.clear();
			OriginPoint.sendKeys("22079");
				  
			driver.findElement(By.id("chkUseFuelSur")).click();
	
			WebElement okclick = driver.findElement(By.xpath("//button[@onclick='GENRE_OK_Click()']"));
			okclick.click();
	
	}
	
	public void fedexreteng() throws InterruptedException {
		Thread.sleep(3000);
		WebElement buttton	= wait.until(
				ExpectedConditions.elementToBeClickable(By.id("CARatingbutton")));
		buttton.click();
		System.out.println("CARatingbutton click done");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@onclick='RTFSMS_RegisterClick()']")).click();
		Thread.sleep(2000);
		WebElement ContactName = driver.findElement(By.id("CAFWSR_txtContactName"));
		ContactName.sendKeys("CMS");
		WebElement CompanyName = driver.findElement(By.id("CAFWSR_txtCompanyName"));
		CompanyName.sendKeys("CMS");
		WebElement address = driver.findElement(By.id("CAFWSR_txtStreetAddress"));
		address.sendKeys("any where");
		WebElement city = driver.findElement(By.id("CAFWSR_txtCity"));
		city.sendKeys("Richmond");
		WebElement State = driver.findElement(By.id("CAFWSR_txtState"));
		State.sendKeys("VA");
		WebElement postalcode = driver.findElement(By.id("CAFWSR_txtPostalCode"));
		postalcode.sendKeys("23173");
		WebElement Select = driver.findElement(By.id("CAFWSR_txtCountry"));
		Select CarrierLabel = new Select(Select);
		CarrierLabel.selectByVisibleText("UNITED STATES");	
		WebElement Phonenumber = driver.findElement(By.id("CAFWSR_txtPhoneNo"));
		Phonenumber.sendKeys("12345678901");	
		WebElement Email = driver.findElement(By.id("CAFWSR_txtEmailAddress"));
		Email.sendKeys("Emailnew@cms.com");	
	}
	
	public void BillingAddress() {
		driver.findElement(By.id("CAFWSR_chkSameVal")).click();//Same as Contact Information
	}
	
	public void fexok() {
		driver.findElement(By.xpath("//button[@onclick='CAFWSR_OKClick()']"));
	}

	public void GeneralConfiguration() throws InterruptedException {

		WebElement CodeRE = driver.findElement(By.id("LTLtxtCode"));
		CodeRE.sendKeys("LTL_Test1");

		WebElement Select = driver.findElement(By.id("cmbLabelLTL"));
		Select CarrierLabel = new Select(Select);
		CarrierLabel.selectByVisibleText("WL_GENERIC_CARRIER");

		WebElement CM = driver.findElement(By.id("cmbManifestLTL"));
		Select cmselect = new Select(CM);
		cmselect.selectByVisibleText("WL_SLI_NOGROUPING.RPT");
		Thread.sleep(2000);
		WebElement TxP = driver.findElement(By.id("LTLtxtTablePrefix"));
		TxP.clear();
		TxP.sendKeys("LTLTest");
	}

	public void ProNumber() throws InterruptedException {
		Thread.sleep(3000);
		WebElement MPFrom = driver.findElement(By.id("LTLtxtTrackingFrom"));
		MPFrom.clear();
		MPFrom.sendKeys("1111111111");
		WebElement MPto = driver.findElement(By.id("LTLtxtTrackingTo"));
		MPto.clear();
		MPto.sendKeys("9999999999");
		WebElement current = driver.findElement(By.id("LTLtxtTracking"));
		current.clear();
		current.sendKeys("1111111111");
	}

	public void RatingConfiguration() throws InterruptedException {
		
		String Origin = "USA";
		String radiotext = "Point-To-Point Rating";
		String[] elementIds = {
	            "Rated Carrier Account ?",
	            "Use Product Classes?",
	        };
	for(String element:elementIds) {		
		WebElement RatedCarrierAccount = driver.findElement(By.xpath("//key[text()='"+element+"']/preceding-sibling::input[@type='checkbox']")); // checkbox for Rated Carrier Account
		RatedCarrierAccount.click();
	}
		Thread.sleep(3000);
	
		WebElement click = driver
				.findElement(By.xpath("//key[text()='" + Origin + "']/preceding-sibling::input[@type='radio']"));
		click.click();	
		WebElement clickradiotext = driver
				.findElement(By.xpath("//key[text()='" + radiotext + "']/preceding-sibling::input[@type='radio']"));
		clickradiotext.click();
		
		WebElement VolFactor = driver.findElement(By.id("LTLtxtVolFactor"));
		VolFactor.clear();
		VolFactor.sendKeys("1");
		WebElement OriginAirport = driver.findElement(By.id("LTLtxtAirportCode"));
		OriginAirport.clear();
		OriginAirport.sendKeys("US");
		WebElement OriginPoint = driver.findElement(By.id("txtCityCode"));
		OriginPoint.clear();
		OriginPoint.sendKeys("22079");
		
	}
	public void FuleSurcharge() {
		  
		driver.findElement(By.id("LTLchkUseFuelSur")).click();
	}
	public void Reokclick() {
		WebElement okclick = driver.findElement(By.xpath("//button[@onclick='LTLCarrier_OK_Click()']"));
		okclick.click();
	}
  public void CreateShipvia() throws InterruptedException {
	  WebElement Shipvias = driver.findElement(By.id("CAShipViaButton"));
		Shipvias.click();
		logger.info("Click on Ship vias successful");
		Thread.sleep(3000);
		boolean isError = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();

		if (isError) {
			WebElement okerror = driver.findElement(By.id("btnErrorBoxOk"));
			okerror.click();
			System.out.println("Adding new ship via");
		} else {
			System.out.println("---");
		}
		Thread.sleep(3000);

		WebElement addshipvia = driver.findElement(By.id("ShipViasAdd"));
		addshipvia.click();
		logger.info("Click on add Ship vias successful");
		Thread.sleep(3000);

//--------------------------For Ship via--------------------------------------
		Thread.sleep(3000);
		WebElement Shipviacode = driver.findElement(By.id("txtCodeSVF"));
		Shipviacode.sendKeys(shipviacode);
		logger.info("Send ship viacode  successful");

		WebElement Dis = driver.findElement(By.id("txtDescriptionSVF"));
		Dis.sendKeys(ShipviaDescription);
		logger.info("Send Discription  successful");

		WebElement selectcarrier = driver.findElement(By.xpath("//select[@id='cmbAccountSVF']"));
		Select ca = new Select(selectcarrier);
		ca.selectByVisibleText(Carriercode); // FEX_Test1 -- FedEx US FSMS
		System.out.println(Carriercode);

		Thread.sleep(3000);
//To select service
		WebElement ShipviaServicet = driver.findElement(By.xpath("//select[@id='cmbServiceSVF']"));
		Select SS = new Select(ShipviaServicet);

		if (Type.equalsIgnoreCase("FEX -- Federal Express")) {
			SS.selectByValue("21264;Ground®;");
			logger.info("select service  successful");
		} else if (Type.equalsIgnoreCase("UPS -- United Parcel Service")) {
			SS.selectByVisibleText(ShipviaServiceUps);
			logger.info("select service  successful");	
		}
//To select Payment
		WebElement payment = driver.findElement(By.id("cmbPaymentSVF"));
		Select pay = new Select(payment);
		pay.selectByVisibleText("Shipper");
	
		logger.info("select Payment  successful");
		// To select bill
		WebElement bill = driver.findElement(By.id("cmbBillDutyTaxToSVF"));
		Select billselect = new Select(bill);
		billselect.selectByVisibleText("Recipient");
	
		logger.info("select bill successful");

		WebElement Billas = driver.findElement(By.id("cmbBillAsSVF"));
		Select BA = new Select(Billas);
		BA.selectByVisibleText("[SAME] Bill As This ShipVia");
		Thread.sleep(2000);
		logger.info("select billAs successful");

		if (Type.equals("FEX -- Federal Express")) {
			boolean DeclaredInsuredValue = driver.findElement(By.id("BOX8388608")).isDisplayed();

			if (DeclaredInsuredValue) {
				WebElement DeclaredInsuredValuet = driver.findElement(By.id("BOX8388608"));
				DeclaredInsuredValuet.click();
				logger.info(" DeclaredInsuredValue successful");

			} else
				System.out.println(" DeclaredInsuredValue Not  Selected");
		}

		if (Type.equals("UPS -- United Parcel Service")) {
			boolean dryice = driver.findElement(By.id("BOX1024")).isDisplayed();
			if (dryice) {
				WebElement dryiceclick = driver.findElement(By.id("BOX1024"));
				dryiceclick.click();
				logger.info(" dryice click successful");
			} else {
				System.out.println("dryice Not Selected successful ");
			}
		}
		driver.findElement(By.id("OkClickSVF")).click();
		logger.info(" Click on ok  successful");
		Thread.sleep(2000);
		boolean ShipviaisError = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();

		if (ShipviaisError) {
			WebElement errorMsg = driver.findElement(By.id("errorMsg"));
			logger.info("Error Message -" + errorMsg);
			WebElement Shipokclick = driver.findElement(By.id("btnErrorBoxOk"));
			Shipokclick.click();
			textValue=true;		
			Thread.sleep(2000);
		if(textValue) {		
			WebElement Shipviacodenew = driver.findElement(By.id("txtCodeSVF"));
			Shipviacodenew.clear();
			Thread.sleep(2000);
			Shipviacodenew.sendKeys(shipviacodenew);
			logger.info("Send ship viacode  successful");
			Thread.sleep(2000);
			driver.findElement(By.id("OkClickSVF")).click();
		}
		}
//-------------------------------------------------------------------------------------------------------------
  }
	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_440")).click(); // To click on Process ShipmentS
		Thread.sleep(3000);
	}
	
	public void ProcessShipment() throws InterruptedException {
		//-------------------------------------------------------------------------------------------------------------
		//Process Shipment

				logger.info(" Opening Process Shipment Menu");
				Thread.sleep(3000);
				WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
				Transaction.click();
				logger.info(" Click on Transaction Menu Successful");
				Thread.sleep(3000);
				WebElement Process = driver.findElement(By.id("menu_item_21"));// To click on Process ShipmentS
				Process.click();
				logger.info(" Click on Process Shipment Menu Successful");

				// Select the Shipvia
				Thread.sleep(5000);
				WebElement shipviaSearch = driver.findElement(By.xpath("//span[contains(@onclick,'btnSearch_PS()')]")); // Search
																														// t
				shipviaSearch.click();
				logger.info("Clicked on shipviaSearch");


				Thread.sleep(5000);
				WebElement ShipviaSearchcode = driver.findElement(By.xpath("//input[@id='radCodeSS']"));
				ShipviaSearchcode.click();
				logger.info("shipviaSearch code Selected");
				
				// Search
				if(textValue) {
				driver.findElement(By.id("txtSCSearchSS")).sendKeys(shipviacodenew);
				logger.info("shipviaSearch Enter Value Successful");		
				}
				else {
					Thread.sleep(2000);
					driver.findElement(By.id("txtSCSearchSS")).sendKeys(shipviacode);
				}
				Thread.sleep(3000);
				driver.findElement(By.id("btnSearchOk_PS")).click();
				logger.info("Click on Ship Via ok search  Successful");
				logger.info("shipvia is selected");

				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
				Thread.sleep(2000);
				driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
				WebElement List2 = driver.findElement(By.id("selCutomerList"));
				Select CustomerList2 = new Select(List2);
				CustomerList2.selectByValue("1"); // To select Global
				wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")))
						.click();

				Thread.sleep(3000);
				logger.info("Customer Searched");
				driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][1][contains(text(), 'CMS')]")).click();
				driver.findElement(By.id("addressformOk")).click(); // Click on OK
				Thread.sleep(3000);
				logger.info("Customer Added");
				driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
				logger.info("Manual Weight is fill ");
				Thread.sleep(2000);

				driver.findElement(By.id("cmdRate")).click();
				logger.info("Click on Rate ");
				Thread.sleep(2000);
				
				
				boolean PoNumber = driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
				WebElement text=driver.findElement(By.id("errorMsg"));
				String potext=text.getText();
				
				if(PoNumber) {
					if(potext.equalsIgnoreCase("'PO Number' is required for LTL carriers.")) {
					driver.findElement(By.id("btnErrorBoxOk")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("txtPO")).sendKeys("121");
					driver.findElement(By.id("cmdRate")).click();	
					}else {
						logger.info("Fail-"+potext);
					}
				}
				
				Thread.sleep(3000);		
					driver.findElement(By.id("btnShipClick")).click(); // Click on ship
				}

	@BeforeClass
	public void setup() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
	  //  options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		driver = new ChromeDriver(options);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}

}
