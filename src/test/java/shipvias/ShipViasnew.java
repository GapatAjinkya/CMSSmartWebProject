package shipvias;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShipViasnew {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ShipViasnew");

	@BeforeMethod
	public void setup() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

		wait = new WebDriverWait(driver, 20);
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
	public void Shipvianew() throws InterruptedException {

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
		logger.info("Click on carriers successful");

		WebElement ShipVias = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_441")));
		ShipVias.click();
		logger.info("Click on ShipVias successful");
		Thread.sleep(8000);
		
		WebElement ShipViasSearh = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtSearchSVSF")));
		ShipViasSearh.sendKeys("AbC");
		logger.info("Search  ShipVias successful");
		Thread.sleep(5000);
		
		WebElement ShipViasok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='SVSFOkClick()']")));
		ShipViasok.click();
		logger.info("Click on ShipVias ok  successful");
		
		Thread.sleep(15000);
		
		boolean isError = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']")).isDisplayed();
		 
		 if (isError)
		 {
		WebElement text=driver.findElement(By.id("errorMsg"));
		WebElement okerror=driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		wait.until(ExpectedConditions.visibilityOf(okerror));
		wait.until(ExpectedConditions.elementToBeClickable(okerror));
		Thread.sleep(10000);
		okerror.click();
		 System.out.println("Adding new ship via");
		 }else {
			 System.out.println("---");
		 }
		 Thread.sleep(8000);

		WebElement addshipvia = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ShipViasAdd")));
		addshipvia.click();
		logger.info("Click on add ShipVias successful");

		String shipviacode = "FEX_Test1_GN1";
		String ShipviaDescription = "Ground";
		String ShipviaService = "GN -- Ground®";
		String Carriercode = "FEX_Test1 -- FedEx US FSMS";
		String SpServices1 = "Dry Ice";
		Thread.sleep(10000);
		WebElement selectcarrier = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbAccountSVF")));
		Select ca = new Select(selectcarrier);
		ca.selectByVisibleText(Carriercode);

		Thread.sleep(5000);
//To select service 	

		WebElement ShipviaServicet = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbServiceSVF")));
		Select SS = new Select(ShipviaServicet);
		SS.selectByVisibleText(ShipviaService);
		logger.info("select service  successful");
		Thread.sleep(5000);

//To select Payment
		WebElement payment = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbPaymentSVF")));
		Select pay = new Select(payment);
		pay.selectByVisibleText("Shipper");
		Thread.sleep(5000);
		logger.info("select Payment  successful");
//To select bill	
		WebElement bill = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbBillDutyTaxToSVF")));
		Select billselect = new Select(bill);
		billselect.selectByVisibleText("Recipient");
		Thread.sleep(5000);
		logger.info("select bill successful");

		WebElement Billas = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbBillAsSVF")));
		Select BA = new Select(Billas);
		BA.selectByVisibleText("[SAME] Bill As This ShipVia");
		Thread.sleep(5000);
		logger.info("select billAs successful");

		WebElement Shipviacode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeSVF")));
		Shipviacode.sendKeys(shipviacode);
		Thread.sleep(5000);
		logger.info("Send ship viacode  successful");

		WebElement Dis = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionSVF")));
		Dis.sendKeys(ShipviaDescription);
		Thread.sleep(5000);
		logger.info("Send Discription  successful");
		
//		 boolean SaturdayDelivery = driver.findElement(By.id("BOX2048")).isDisplayed();
//		 if (SaturdayDelivery)
//		 {
//			 WebElement SaturdayDeliveryt= driver.findElement(By.id("BOX2048"));
//				SaturdayDeliveryt.click();
//				Thread.sleep(5000);	
//				logger.info(" Saturday Delivery  successful");
//		 }
//		boolean DeclaredInsuredValue = driver.findElement(By.id("BOX8388608")).isDisplayed();
//		if (DeclaredInsuredValue) {
//			WebElement DeclaredInsuredValuet = driver.findElement(By.id("BOX8388608"));
//			DeclaredInsuredValuet.click();
//			Thread.sleep(5000);
//			logger.info(" DeclaredInsuredValue successful");
//		} else {
//			System.out.println("");
//		}

		
//		driver.findElement(By.xpath("//input[@id='BOX14318']")).click();
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//table[@id='xmlTableSVF']//tr"));
		String desiredText = "Alcohol";
		

		for (WebElement checkbox : checkboxes) {
		    String checkboxText = checkbox.getText();
		    System.out.println(checkboxText);

		    if (checkboxText.equals(desiredText)) {
		        if (checkbox.isDisplayed()) {
		            checkbox.click();
		            System.out.println("Checkbox selected successfully.");
		            break;
		        } else {
		            System.out.println("Checkbox is not displayed.");
		        }
		    }
		}

		
		Thread.sleep(5000);
		driver.findElement(By.id("OkClickSVF")).click();
		logger.info(" Click on ok  successful");

	}
}