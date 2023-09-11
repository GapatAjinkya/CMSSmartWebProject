package carriers;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Testcase {

	public static WebDriver driver;
	public static WebDriverWait wait;
	//Logger logger = LogManager.getLogger("Testcase");

	@Test
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	//	logger.info("Browser opend");
       System.out.println("Browser opend");		
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_1"))).click();// To click on LocalConfig Menu 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_15"))).click(); // To click on Login Tab
		Thread.sleep(3000);
		
		WebElement Userlogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtLPUserLogin"))); // Userlogin))
		Userlogin.sendKeys("nilesh");
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtLPPassword"))); // password
		password.sendKeys("Nilesh@123");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe

		WebElement ok = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();

		String expectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		String actualTitle = driver.getTitle();
		assert actualTitle.equalsIgnoreCase(expectedTitle) : "Title didn't match";
		System.out.println("Title Matched");
		Thread.sleep(5000);
	}

	@Test
	public void shipvia() throws InterruptedException {

	//	logger.info(" Opening Process Shipment Menu");
		 System.out.println(" Opening Process Shipment Menu");

		WebElement Transaction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_2"))); // Transaction
		wait.until(ExpectedConditions.elementToBeClickable(Transaction));
		Transaction.click();
	//	logger.info(" Click on Transaction Menu Successful");
		 System.out.println("Click on Transaction Menu Successful");
		Thread.sleep(5000);
		WebElement Process = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_21")));// To click
		wait.until(ExpectedConditions.elementToBeClickable(Process));
		Process.click();
	//	logger.info(" Click on Process Shipment Menu Successful");
		 System.out.println("Click on Process Shipment Menu Successful");
		// Select the Shipvia
		 Thread.sleep(5000);
		 
		WebElement shipviaSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@onclick,'btnSearch_PS()')]"))); // Search																													// the
		wait.until(ExpectedConditions.elementToBeClickable(shipviaSearch));
		shipviaSearch.click();
		//logger.info("Clicked on shipviaSearch");
		 System.out.println("Clicked on shipviaSearch");
			Thread.sleep(5000);
		String shipviacode = "FEXTest4";
		WebElement sendcode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSCSearchSS")));
		wait.until(ExpectedConditions.visibilityOf(sendcode));
		sendcode.sendKeys(shipviacode);
	//	logger.info("shipviaSearch Enter Value Successful");
		 System.out.println("shipviaSearch Enter Value Successful");

		WebElement ok =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSearchOk_PS")));
		wait.until(ExpectedConditions.visibilityOf(ok));
		wait.until(ExpectedConditions.elementToBeClickable(ok));
		ok.click();
	//	logger.info("Click on Ship Via ok search  Successful");
		 System.out.println("Click on Ship Via ok search  Successful");	

		Thread.sleep(5000);

		// -------------------------------------------------------
		
		WebElement customer = driver.findElement(By.xpath("//button[@onclick='AddressesClick()']"));
		wait.until(ExpectedConditions.elementToBeClickable(customer));
		customer.click();
	//	logger.info("Clicked on Customer");
		 System.out.println("Clicked on Customer");	
		// To Customer Search Criteria
		Thread.sleep(5000);

		WebElement searchcustomer = driver.findElement(By.id("txtSCSearch")); // searchcustomer
		wait.until(ExpectedConditions.visibilityOf(searchcustomer));
		searchcustomer.sendKeys("CMS");

		WebElement List = driver.findElement(By.id("selCutomerList"));
		Select CustomerList = new Select(List);
		CustomerList.selectByValue("1"); // To select Global
		driver.findElement(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")).click(); // click on ok
		Thread.sleep(5000);
	//	logger.info("Customer Searched");
		 System.out.println("Customer Searched");	
		 
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]"))).click();
		
		WebElement Customerok = driver.findElement(By.id("addressformOk"));
		wait.until(ExpectedConditions.elementToBeClickable(Customerok));
		Customerok.click(); // Click on OK
		Thread.sleep(5000);
	//	logger.info("Customer Added");
		 System.out.println("Customer Added");	
		 
		WebElement buttonSpecialServices = driver.findElement(By.id("btnSpecialServices"));
		wait.until(ExpectedConditions.visibilityOf(buttonSpecialServices));
		wait.until(ExpectedConditions.elementToBeClickable(buttonSpecialServices));
		buttonSpecialServices.click();
		Thread.sleep(5000);
		
		List<WebElement> checkboxes = driver
				.findElements(By.xpath("//input[@type='checkbox' and contains(@id,'BOX')]"));
		
		for (int i = 0; i < checkboxes.size(); i++) {
			
			try {
				WebElement checkbox = (WebElement) checkboxes.get(i);
				// Check if the checkbox is selected
				if (!checkbox.isSelected()) 
				{	
					 checkbox.click();
					System.out.println("// Select the checkbox" + checkbox);				
				System.out.println(i);
				
				WebElement okclick = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnOk']")));
				okclick.click();
				// Perform some actions on the selected checkbox (if needed)
				Thread.sleep(5000);
				driver.findElement(By.xpath("//button[@onclick='AddressesClick()']")).click();// Customers
				Thread.sleep(5000);
				driver.findElement(By.id("txtSCSearch")).sendKeys("CMS"); // searchcustomer
				Thread.sleep(5000);
				WebElement List2 = driver.findElement(By.id("selCutomerList"));
				Select CustomerList2 = new Select(List2);

				CustomerList2.selectByValue("1"); // To select Global
				WebElement okcustomer=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")));
				okcustomer.click();
				Thread.sleep(5000);
		//		logger.info("Customer Searched");
				 System.out.println("Customer Searched");	
				Thread.sleep(5000);
				WebElement selectcustomer=driver.findElement(By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]"));
				selectcustomer.click();
				driver.findElement(By.id("addressformOk")).click(); // Click on OK
				Thread.sleep(5000);
			//	logger.info("Customer Added");
				 System.out.println("Customer Added");	
				 
				driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
			//	logger.info("Manual Weight is fill ");
				 System.out.println("Manual Weight is fill ");	
				driver.findElement(By.id("cmdRate")).click();

//				logger.info("Click on Rate ");
				 System.out.println("Click on Rate");			
				Thread.sleep(5000);
				driver.findElement(By.id("btnShipClick")).click(); // Click on ship
				

				driver.findElement(By.id("btnSpecialServices")).click();
		//		logger.info("click  btnSpecialServices");
				System.out.println("click  btnSpecialServices");
				Thread.sleep(5000);
				checkbox.click();
		//		logger.info("click  checkbox");		
				System.out.println("click  checkbox");
			}
			}
			catch (StaleElementReferenceException e)
			{
				// Handle the StaleElementReferenceException here, for example, you can log an
				// error message
			//	logger.error("StaleElementReferenceException occurred. Trying to recover...");
				System.out.println("StaleElementReferenceException occurred. Trying to recover...");
				checkboxes = driver.findElements(By.xpath("//input[@type='checkbox' and contains(@id,'BOX')]"));
			}

		
	}
}}