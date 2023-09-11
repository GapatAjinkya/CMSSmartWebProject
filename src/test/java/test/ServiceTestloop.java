package test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class ServiceTestloop {
	public static WebDriver driver;
	public static WebDriverWait wait;
	// Logger logger = LogManager.getLogger("ServiceTestloop");

	@Test
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		 wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// logger.info("Browser opend");
		System.out.println("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_1"))).click();// To click on
																								// LocalConfig Menu
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_15"))).click(); // To click on Login
																									// Tab
		Thread.sleep(3000);

		WebElement Userlogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtLPUserLogin"))); // Userlogin))
		Userlogin.sendKeys("nilesh");
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtLPPassword"))); // password
		password.sendKeys("Nilesh@123");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe

		WebElement ok = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();

		String expectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		String actualTitle = driver.getTitle();
		assert actualTitle.equalsIgnoreCase(expectedTitle) : "Title didn't match";
		System.out.println("Title Matched");
		Thread.sleep(5000);
	}

	@Test
	public void service() throws InterruptedException {

		// logger.info(" Opening Process Shipment Menu");
		System.out.println(" Opening Process Shipment Menu");

		WebElement Transaction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_2"))); // Transaction
		wait.until(ExpectedConditions.elementToBeClickable(Transaction));
		Transaction.click();
		// logger.info(" Click on Transaction Menu Successful");
		System.out.println("Click on Transaction Menu Successful");
		Thread.sleep(5000);
		WebElement Process = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_21")));// To
																												// click
		wait.until(ExpectedConditions.elementToBeClickable(Process));
		Process.click();
		// logger.info(" Click on Process Shipment Menu Successful");
		System.out.println("Click on Process Shipment Menu Successful");
		// Select the Shipvia
		Thread.sleep(5000);

		WebElement shipviaSearch = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@onclick,'btnSearch_PS()')]"))); // Search
																															// //
																															// the
		wait.until(ExpectedConditions.elementToBeClickable(shipviaSearch));
		shipviaSearch.click();
		// logger.info("Clicked on shipviaSearch");
		System.out.println("Clicked on shipviaSearch");
		Thread.sleep(5000);
		String shipviacode = "FEXTest4";
		WebElement sendcode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSCSearchSS")));
		wait.until(ExpectedConditions.visibilityOf(sendcode));
		sendcode.sendKeys(shipviacode);
		// logger.info("shipviaSearch Enter Value Successful");
		System.out.println("shipviaSearch Enter Value Successful");

		WebElement ok = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSearchOk_PS")));
		wait.until(ExpectedConditions.visibilityOf(ok));
		wait.until(ExpectedConditions.elementToBeClickable(ok));
		ok.click();
		
//----------------------------------------------------------------------------------------------------------	
		
		  By checkBoxLocator = By.xpath("//input[@type='checkbox' and contains(@id,'BOX')]");
		 // Replace this with the number of checkboxes you want to select in each iteration
        int numberOfCheckBoxesToSelect = 2;

        // Replace this with the number of iterations you want to perform
        int numberOfIterations = 15;

        for (int i = 0; i < numberOfIterations; i++) {
            // Select the customer
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
    		 

            // Select the service button
    		 WebElement buttonSpecialServices = driver.findElement(By.id("btnSpecialServices"));
    			wait.until(ExpectedConditions.visibilityOf(buttonSpecialServices));
    			wait.until(ExpectedConditions.elementToBeClickable(buttonSpecialServices));
    			buttonSpecialServices.click();
    			Thread.sleep(5000);

            // Select the desired checkboxes
            List<WebElement> checkboxes = driver.findElements(checkBoxLocator);
            for (int j = 0; j < numberOfCheckBoxesToSelect && j < checkboxes.size(); j++) {
                checkboxes.get(j).click();
            }

            // Close the checkbox table (you may need to add an action here to close it)
            WebElement okclick = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnOk']")));
			okclick.click();
            
			 
			driver.findElement(By.xpath("//input[@id='txtManual']")).sendKeys("1.00");
		//	logger.info("Manual Weight is fill ");
			 System.out.println("Manual Weight is fill ");	
			driver.findElement(By.id("cmdRate")).click();

//			logger.info("Click on Rate ");
			 System.out.println("Click on Rate");			
			Thread.sleep(5000);
			driver.findElement(By.id("btnShipClick")).click(); // Click on ship
            // Wait for the next iteration (you may need to add an explicit wait here)
			Thread.sleep(5000);
        }	
}
}
		
		
		
	
