package com.cms.processreturnTest;

import java.time.Duration;

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

public class ProcessReturnTest {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static void main(String[] args) throws InterruptedException {

		Logger logger = LogManager.getLogger("ProcessReturnTest");
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
		Thread.sleep(5000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2"));// To click on Transaction
		Transaction.click();
		logger.info("Transaction menu opned");


		WebElement returns =driver.findElement(By.id("menu_item_22"));
		returns.click();                                                  // To Click on processReturns

		logger.info("processReturns window opned ");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		Thread.sleep(5000);
		WebElement Rmethod=driver.findElement(By.xpath("//select[@id='PRtxtReturnMethod']"));
		Select returnmethod= new Select (Rmethod);
		returnmethod.selectByVisibleText("Email Label");
		logger.info(" Return Method");

		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@onclick='btnSearch_PS()']")));
		WebElement dropdown=driver.findElement(By.xpath("//span[@onclick='btnSearch_PS()']"));
		dropdown.click();
		Thread.sleep(2000);
		WebElement sendkeys=driver.findElement(By.id("txtSCSearchSS"));
		sendkeys.sendKeys("FSMS(MPS:YES)_GN");
		driver.findElement(By.xpath("//button[@id='btnSearchOk_PS']")).click();
		Thread.sleep(2000);


//		Select shipvia=new Select(dropdown);
//		shipvia.selectByValue("305");                      // to select the Shipvia by value

		WebElement element = driver.findElement(By.id("PRIWLDatePicker"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].value ='9/20/2023';", element); // month/date/year


//		driver.findElement(By.id("PRIWLDatePicker")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("(//td[@class='day'][contains(.,'28')]")).click();

		Thread.sleep(2000);
		driver.findElement(By.id("PRbtnCustomerSearch")).click();
		Thread.sleep(2000);
		WebElement customercode = driver.findElement(By.id("radCode")); // customercode
		customercode.click();
		WebElement searchcustomer = driver.findElement(By.id("txtSCSearch")); // searchcustomer
		searchcustomer.sendKeys("CMS");

		WebElement List = driver.findElement(By.id("selCutomerList"));
		Select CustomerList = new Select(List);
		CustomerList.selectByValue("1"); // To select Global
		driver.findElement(By.xpath("//button[@onclick='onCustomerSearchOkClick()']")).click(); // click on ok
		Thread.sleep(3000);

		driver.findElement(By.xpath("//tr[@data-index=\"0\"]")).click();     // to select customer from list
		WebElement Customerok = driver.findElement(By.id("addressformOk"));
		Customerok.click(); // Click on OK
		Thread.sleep(2000);
		logger.info("Customer Added");
		Thread.sleep(2000);
		//driver.findElement(By.id("PRtxtParcelWeight")).sendKeys("1");
				driver.findElement(By.xpath("//button[@id='btnShipClickPR']")).click();
				logger.info("Process Returns done");

}
}