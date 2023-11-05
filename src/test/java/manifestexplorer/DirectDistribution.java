package manifestexplorer;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class DirectDistribution {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("DirectDistribution");
	
	@Test
	public void DirectDistributionTest() throws InterruptedException {
		processshipment("FSMS(MPS)IPD");
		OpenManifestExplorer();
		Carriers("Federal Express");
		Selecttype("FSMS(MPS)");
		DirectDistributon();
		Checkstatus();
	}

	public void Checkstatus() throws InterruptedException {
	    Thread.sleep(3000);
	    WebElement table = driver.findElement(By.xpath("//table[@id='frameDirectDistribution']"));
	    List<WebElement> rows = table.findElements(By.tagName("tr"));
	    
	    boolean conditionMet = false; // Initialize the flag
	    
	    for (WebElement row : rows) {
	        List<WebElement> cells = row.findElements(By.tagName("td"));
	        if (cells.size() >= 6) {
	            String column1Text = cells.get(2).getText();
	            String column2Text = cells.get(5).getText();
	            
	            if (column1Text.equals("FSMS(MPS)IPD") && column2Text.equals("Open")) {
	                row.click();
	                driver.findElement(By.id("btnDDFCloseButton")).click();
	                Thread.sleep(2000);
	                driver.findElement(By.id("btnConfirmBoxOk")).click();
	                
	                // Set the flag to true if the condition is met
	                conditionMet = true;
	            }
	        }
	    }
	    WebElement checkButton = driver.findElement(By.id("btnDDFCloseButton"));
	    wait.until(ExpectedConditions.elementToBeClickable(checkButton));
	    if (!conditionMet) {
	        // Fail the test if the condition was not met in any row
	        Assert.fail("The expected condition was not met in any row.");
	    }
	}

	public void DirectDistributon() throws InterruptedException {
		Thread.sleep(4000);
		WebElement Directbuton = driver.findElement(By.id("MEDirectDistributon"));																							// Shipvias list
		Directbuton.click();
	}
	
	public void processshipment(String Shipvia) throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction= driver.findElement(By.id("menu_item_2")); 	// To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		// Select the Shipvia
		Thread.sleep(8000);
		WebElement shipviaSearch = driver.findElement(By.xpath("//*[@onclick=\"btnSearch_PS()\"]")); // Search the																								// Shipvias list
		shipviaSearch.click();
		logger.info("Clicked on shipviaSearch");

		Thread.sleep(8000);
		WebElement ShipviaSearchcode = driver.findElement(By.xpath("//input[@id=\"radCodeSS\"]"));
		ShipviaSearchcode.click();
		driver.findElement(By.id("txtSCSearchSS")).sendKeys(Shipvia);
		driver.findElement(By.id("btnSearchOk_PS")).click();
		logger.info("shipvia is selected");
		Thread.sleep(8000);

		WebElement customer = driver.findElement(By.xpath("//button[@onclick='AddressesClick()']"));
		customer.click();
		logger.info("Clicked on Customer");

		// To Customer Search Criteria
		Thread.sleep(8000);
		WebElement customercode = driver.findElement(By.id("radCode")); // customercode
		customercode.click();
		WebElement searchcustomer = driver.findElement(By.id("txtSCSearch")); // searchcustomer
		searchcustomer.sendKeys("CAN");
		WebElement List = driver.findElement(By.id("selCutomerList"));
		Select CustomerList = new Select(List);
		CustomerList.selectByValue("1"); // To select Global
		driver.findElement(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")).click(); // click on ok
		Thread.sleep(8000);
		logger.info("Customer Searched");

		driver.findElement(By.xpath("//*[@id='tblCustomerList']/tbody/tr/td[1]")).click();
		WebElement Customerok= driver.findElement(By.id("addressformOk"));
		Customerok.click();          // Click on OK
		Thread.sleep(8000);
		logger.info("Customer Added");

       

		logger.info("Details added");
		WebElement Details= driver.findElement(By.id("btnDetails"));
		Details.click();       // Details Button
		Thread.sleep(5000);
	    WebElement selectProduct = driver.findElement(By.id("btnSelectP"));
	    selectProduct.click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[@value=\"Code\"]")).click();
	    WebElement productSearch = driver.findElement(By.id("txtProductSearch"));
	    productSearch.sendKeys("Book");
	    Thread.sleep(5000);
	    driver.findElement(By.id("btnPOk")).click();
	    Thread.sleep(5000);		    
	    driver.findElement(By.id("btnProductOk")).click(); 
	    Thread.sleep(5000);	
	    driver.findElement(By.id("btnPDOk")).click(); 
	    Thread.sleep(5000);	
	    driver.findElement(By.xpath("//*[@id='btnPdOk']")).click();
	   
		Thread.sleep(5000);
		
		WebElement element = driver.findElement(By.id("btniternationalData"));

		// Create a JavaScriptExecutor instance
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll to the element
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		// To click on International Button
		element.click();
		Thread.sleep(5000);

		// To select FTR Exemption

		WebElement  FTRExemption =driver.findElement(By.id("cmbFTRExemption"));
		Select dropdown= new Select(FTRExemption);
		dropdown.selectByIndex(4);
		logger.info("FTRE added");

		// TO addTerms of sale
		WebElement Termsofsale=driver.findElement(By.id("cmbIDTermOfSale"));
		Select sale= new Select(Termsofsale);
		sale.selectByVisibleText("DDP - Delivery Duty Paid");
		logger.info("Termsofsale added");

		// TO add FedEx IOR Codes
		driver.findElement(By.xpath("//button[@onclick='OnClickfedExIORCode()']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[contains(text(),'IPD-YYCI')]")).click();
		driver.findElement(By.id("btnFedExIOROKClick")).click();
		logger.info("FedEx IOR Codes added");

		Thread.sleep(5000);
		WebElement packagdropdown=driver.findElement(By.id("cmbAdmissibilityPackageType"));
		Select Package= new Select(packagdropdown);
		Package.selectByVisibleText("BOX -- Box");
		driver.findElement(By.id("btnInternationalOk")).click();
		Thread.sleep(5000);	
		WebElement packagecount = driver.findElement(By.id("txtParcelCount"));
		packagecount.clear();
		packagecount.sendKeys("2");
		
		WebElement txtSimilarCount = driver.findElement(By.id("txtSimilarCount"));
		txtSimilarCount.clear();
		txtSimilarCount.sendKeys("2");
		
		  WebElement ManualWeight = driver.findElement(By.xpath("//input[@id='txtManual']"));
			ManualWeight.sendKeys("1.00");
			logger.info("Manual Weight is fill ");

			driver.findElement(By.id("btnShipClick")).click();
			logger.info("International Shipment is Done");
		
		
	}
	public void Carriers(String carrier) throws InterruptedException {
		Thread.sleep(8000);
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='MEddlCarriers']"));
		dropdown.click();
		Select select = new Select(dropdown);
		select.selectByVisibleText(carrier);
	}
	
	public void OpenManifestExplorer() throws InterruptedException {
		Thread.sleep(8000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_24")).click(); // To click on me
		Thread.sleep(3000);
	}
	
	public void Selecttype(String shipcodediscription) throws InterruptedException {

		Thread.sleep(8000);
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='MEtblManifestDetailsList']//td[2]"));
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(shipcodediscription)) {
				System.out.println(value);
				ele.click(); // Click on the element if found
				break;
			}
		}
		

	}
	@BeforeClass
	public void setup() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		 options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		driver = new ChromeDriver(options);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(480));
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
		Thread.sleep(3000);
	}
}
