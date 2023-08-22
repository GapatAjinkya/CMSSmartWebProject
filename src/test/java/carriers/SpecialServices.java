package carriers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class SpecialServices {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static  boolean loopSatisfied = false;

	//Logger logger = LogManager.getLogger("SpecialServicess");
	@BeforeClass
	public void setup() throws InterruptedException 
	{	
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 60);	
	//	logger.info("Browser opend");
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
//		logger.info("Title Matched");
		Thread.sleep(10000);
	}
	@Test
	public void shipVia() throws InterruptedException {	
		
		openProcessShipmentMenu();
		searchAndSelectShipVia("FEXTest4");
		addCustomer("CMS");			
		
		String[] specialServices = {"Auto POD","Tireloss",};
		int count=specialServices.length;
		int k = 1;		    
	  for (String service : specialServices) 
	    {
	        selectSpecialService(service);
	        if(k==count)
	        {
	        	loopSatisfied= true;
	        }else {
	        	loopSatisfied= false;
	        }
	        if(loopSatisfied==false) {
	        	addCustomer("CMS");			
	        }
	        k++;
	    }
	}
	private void openProcessShipmentMenu() throws InterruptedException 
	{	
	//	logger.info("Opening Process Shipment Menu");
		Thread.sleep(5000);
		WebElement transaction = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_2")));
		wait.until(ExpectedConditions.elementToBeClickable(transaction)).click();
	//	logger.info("Click on Transaction Menu Successful");
		Thread.sleep(5000);
		WebElement process = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_21")));
		wait.until(ExpectedConditions.elementToBeClickable(process)).click();
	//	logger.info("Click on Process Shipment Menu Successful");
	}
	private void searchAndSelectShipVia(String shipviacode) throws InterruptedException {
		Thread.sleep(5000);
		WebElement shipviaSearch = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@onclick,'btnSearch_PS()')]"))); // Search																													// the
		wait.until(ExpectedConditions.elementToBeClickable(shipviaSearch)).click();
	//	logger.info("Clicked on shipviaSearch");
		Thread.sleep(5000);
		WebElement sendcode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSCSearchSS")));
		wait.until(ExpectedConditions.visibilityOf(shipviaSearch));
		sendcode.sendKeys(shipviacode);
	//	logger.info("shipviaSearch Enter Value Successful");
	WebElement ok = driver.findElement(By.id("btnSearchOk_PS"));
		wait.until(ExpectedConditions.visibilityOf(ok));
		ok.click();
	//	logger.info("Click on Ship Via ok search Successful");
		Thread.sleep(5000);
		
	}
	private void addCustomer(String customerName) throws InterruptedException
	{	
		WebElement customer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='AddressesClick()']")));
		wait.until(ExpectedConditions.elementToBeClickable(customer));
		customer.click();
	//	logger.info("Clicked on Customer");	
		Thread.sleep(5000);
		WebElement searchCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSCSearch"))); // searchcustomer
		searchCustomer.sendKeys(customerName);
		WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selCutomerList")));
		Select customerList = new Select(list);
		customerList.selectByValue("1"); // To select Global
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']"))).click(); // click on ok
		Thread.sleep(5000);
	//	logger.info("Customer Searched");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), '" + customerName + "')]")))
				.click();
		WebElement customerOk = driver.findElement(By.id("addressformOk"));
		customerOk.click(); // Click on OK
		Thread.sleep(5000);
		//logger.info("Customer Added");
	}
	private void EnterWeight() throws InterruptedException
	{	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='txtManual']"))).sendKeys("1.00");
	//	logger.info("Manual Weight is fill ");	
	}
	private void clickRate() throws InterruptedException {		
	WebElement Rate=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmdRate")));
	wait.until(ExpectedConditions.elementToBeClickable(Rate));	
	Rate.click();
		//logger.info("Click on Rate ");
	}
    private void Clickship() throws InterruptedException
    {
	Thread.sleep(5000);
	WebElement ship=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnShipClick']")));
	wait.until(ExpectedConditions.elementToBeClickable(ship));	
	ship.click();				
	}
	private void selectSpecialService(String service) throws InterruptedException
	{
		WebElement buttonSpecialServices = driver.findElement(By.id("btnSpecialServices"));
		wait.until(ExpectedConditions.elementToBeClickable(buttonSpecialServices)).click();
		//logger.info("Click on Special Services successful");
//	        WebElement serviceElement = wait
//	                .until(ExpectedConditions.elementToBeClickable(By.id(service)));
//	        serviceElement.click();
		Thread.sleep(5000);
		
		if (service.equalsIgnoreCase("Hold At Location")) 
		{
			WebElement HoldAtLocation = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX2")));
			HoldAtLocation.click();
			driver.findElement(By.xpath("//input[@onclick='ShowFedExWSHoldAtLocationAddress();']")).click(); // address
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[@id='btnfedexholdAtLocationAddressDataModalOk']")).click(); // modify
			Thread.sleep(3000);
			driver.findElement(By.id("txtCompanyNameHoldFedex")).sendKeys("Test");
			driver.findElement(By.id("txtContactNameHoldFedex")).sendKeys("Test");
			driver.findElement(By.id("txtAddress1HoldFedex")).sendKeys("Testaddress");
			driver.findElement(By.id("txtCityHoldFedex")).sendKeys("lorton");
			driver.findElement(By.id("txtStateHoldFedex")).sendKeys("va");
			driver.findElement(By.id("txtZipHoldFedex")).sendKeys("22079");
			WebElement country = driver.findElement(By.id("txtCountryFedexHold"));
			Select countrys = new Select(country);
			countrys.selectByVisibleText("UNITED STATES");
			driver.findElement(By.id("btnfedexholdAtLocationAddressDataModalOk")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
			Thread.sleep(5000);
//			logger.info("Click on ok successful");
//			logger.info("HoldAtLocation Selected");
			EnterWeight();
			clickRate();
			Clickship();
			
		} else if (service.equalsIgnoreCase("Non Standard Container"))
		{		
			WebElement NonStandardContainer = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX0")));
			NonStandardContainer.click();
			Thread.sleep(5000);
			WebElement okbutton = driver.findElement(By.xpath("//*[@id='btnOk']"));
			okbutton.click();
//			logger.info("Click on ok successful");
//			logger.info("NonStandardContainer Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("Auto POD")) 
		{
			WebElement AutoPOD = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX1")));
			AutoPOD.click();
			WebElement okbutton3 = driver.findElement(By.xpath("//*[@id='btnOk']"));
			okbutton3.click();
//			logger.info("Click on ok successful");
//			logger.info("AutoPOD Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("Drop Off Flag")) 
		{
			WebElement Dropoff = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX4")));
			Dropoff.click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
//			logger.info("Dropoff Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("Recipient Ship Alert")) {
			WebElement RecipientShipAlert = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX5")));
			RecipientShipAlert.click();
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
			Thread.sleep(5000);
//			logger.info("RecipientShipAlert Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("Hazardous Materials")) 
		{
			WebElement HazardousMaterials = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX6")));
			HazardousMaterials.click();
			Thread.sleep(5000);
			WebElement selectmaterial = driver.findElement(By.id("SL6"));
			Select dropdown = new Select(selectmaterial);
			dropdown.selectByVisibleText("Hazardous Materials");
			Thread.sleep(10000);
			WebElement okbutton = driver.findElement(By.xpath("//*[@id='btnOk']"));
			okbutton.click();
//			logger.info("Click on ok successful");
//			logger.info("HazardousMaterials Selected");
			// To Add Details
			WebElement Details = driver.findElement(By.id("btnDetails"));
			Details.click(); // Details Button
			Thread.sleep(5000);
			WebElement SelectProduct = driver.findElement(By.id("btnSelectP"));
			SelectProduct.click(); // SelectProduct Button
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@value=\"Code\"]")).click();
			WebElement Productname = driver.findElement(By.id("txtProductSearch"));
			Productname.sendKeys("Books");
//			logger.info("Books input given ");
			driver.findElement(By.id("btnPOk")).click(); // Click on oK
			Thread.sleep(10000);
			WebElement Books = driver.findElement(By.xpath("//td[contains(text(),'Books')]"));
			Books.click();
		//	logger.info("Books Selected ");
			Thread.sleep(10000);
			driver.findElement(By.id("btnProductOk")).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='btnPDOk']")).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='btnPdOk']")).click();
			Thread.sleep(10000);
		//	logger.info("Details added");	
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("FedEx International Controlled Export"))
		{		
			WebElement FedExInternationaControlledExport = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("BOX7")));
			FedExInternationaControlledExport.click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
			Thread.sleep(5000);
		//	logger.info("FedExInternationaControlledExport Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("COD")) 
		{
			WebElement COD = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("BOX8")));
			COD.click();
			
			WebElement cod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SL8")));
			Select codselect = new Select(cod);
			codselect.selectByVisibleText("Cash");
			 Thread.sleep(5000);
			 WebElement codamout=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("INSS8")));
			 codamout.clear();
			 Thread.sleep(5000);
			 codamout.sendKeys("10");
			 Thread.sleep(5000);
	      	WebElement Address=	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@onclick,'ShowCODAddress();')]")));
			 wait.until(ExpectedConditions.elementToBeClickable(Address));
			 Address.click();
			 Thread.sleep(5000);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btncodAddressDataModalOk']"))).click();
			 Thread.sleep(5000);
			 WebElement CompanyNameCOD=	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtCompanyNameCOD")));
			 CompanyNameCOD.clear();
			 Thread.sleep(5000);
			 CompanyNameCOD.sendKeys("CMS GlobalSoft Org Name"); 
			 WebElement ContactNameCOD=	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtContactNameCOD")));
			 ContactNameCOD.clear();
		
			 ContactNameCOD.sendKeys("Professional Services Org Contact"); 
			 WebElement Address1COD=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtAddress1COD")));
			 Address1COD.clear();
			
			 Address1COD.sendKeys("1751 THOMPSON ST");
			 WebElement CityCOD=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtCityCOD")));
			 CityCOD.clear();

			 CityCOD.sendKeys("AURORA");  
			 WebElement StateCOD=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtStateCOD")));
			 StateCOD.clear();
		
			 StateCOD.sendKeys("OH"); 	 
		 WebElement ZipCOD=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtZipCOD")));
			 ZipCOD.clear();
			 Thread.sleep(5000);
			 ZipCOD.sendKeys("44202"); 		 
			 WebElement CountryCOD =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtCountryCOD")));
				Select countrysC = new Select(CountryCOD);
				countrysC.selectByVisibleText("UNITED STATES");
				 WebElement PhoneCOD=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtPhoneCOD")));
				 PhoneCOD.clear();
				 Thread.sleep(5000);
				 PhoneCOD.sendKeys("7034558292");		 
				 WebElement Save= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncodAddressDataModalOk")));		 
				 wait.until(ExpectedConditions.elementToBeClickable(Save));
				 Save.click();		 
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnOk']"))).click(); 
			EnterWeight();
			clickRate();
			Clickship();
			
		} else if (service.equalsIgnoreCase("FedEx ShipAlert")) {
			
			WebElement FedExShipAlert = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX9")));
			FedExShipAlert.click();
			driver.findElement(By.xpath("//*[@id='button3']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("chkShipperShipAlert"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("btnShipAlertOk"))).click();
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			 jsExecutor.executeScript("window.scrollBy(0, 1000);");
				Thread.sleep(5000);
			WebElement email=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnEmailNotification")));
			email.click();
			Thread.sleep(8000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSenderEmail")))
					.sendKeys("cms@gmail.com");
		//	logger.info("Email Send");
		WebElement btnModalOk=	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnModalOk")));
		btnModalOk.click();
		//	logger.info("FedExShipAlert Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("Residential")) {
			WebElement Residential = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX10")));
			Residential.click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
			Thread.sleep(5000);
		//	logger.info("Residential Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("Tireloss")) {	
			WebElement Tireloss = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX11")));
			Tireloss.click();
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
			Thread.sleep(5000);
		//	logger.info("Tireloss Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("FedEx Delivery Signature Options")) 
		{
			WebElement FedExDeliverySignatureOptions = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("BOX12")));
			FedExDeliverySignatureOptions.click();
			/*
			WebElement selecta = driver.findElement(By.id("SL12"));
			Select select = new Select(selecta);
			select.selectByVisibleText("Signature Required");
			Thread.sleep(5000);
			*/
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
			Thread.sleep(5000);
		//	logger.info("FedExDeliverySignatureOptions Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("Oversize")) {
			WebElement Oversize = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX13")));
			Oversize.click();
			WebElement Oversizeselect = driver.findElement(By.id("SL13"));
			Select os = new Select(Oversizeselect);
			os.selectByVisibleText("Oversized Handling");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
			Thread.sleep(5000);
		//	logger.info("Oversize Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("Ecod"))
		{
			WebElement Ecod = wait.until(ExpectedConditions.elementToBeClickable(By.id("BOX14")));
			Ecod.click();
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		
		//	logger.info("Ecod Selected");
			EnterWeight();
			clickRate();
			Clickship();
		} else if (service.equalsIgnoreCase("Declared Value/Third Party Insurance")) {
			
			Thread.sleep(3000);
			WebElement DeclaredValueThirdPartyInsurance = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("BOX15")));
			DeclaredValueThirdPartyInsurance.click();
			Thread.sleep(5000);
			WebElement DV = driver.findElement(By.id("SL15"));
			Select os = new Select(DV);
			os.selectByVisibleText("Declared Value");
			driver.findElement(By.xpath("//*[@id='btnOk']")).click();
		//	logger.info("DeclaredValueThirdPartyInsurance Selected");
			EnterWeight();
			clickRate();
			Clickship();
	
		}
	}
	
}
