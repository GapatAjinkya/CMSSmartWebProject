package viewShipmentTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VSManifestandShipvia {

public static void main(String[] args) throws InterruptedException {

		Logger logger = LogManager.getLogger("Internationalshipment");// object created for logger class and class name is passed

	//	DOMConfigurator.configure("Log4j.xml");
		//PropertyConfigurator.configure("");
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");

		WebDriver driver = new ChromeDriver(options);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("nilesh");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("Nilesh@123");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@onclick=\"LoginFormOkClick()\"]")).click();

		String ExpectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		String ActualTitle = driver.getTitle();

		if (ActualTitle.equalsIgnoreCase(ExpectedTitle)) {
			System.out.println("Title Matched");
		} else {
			System.out.println("Title didn't match");
		}
		logger.info("Login successful");

		WebElement Transaction = driver.findElement(By.cssSelector("#menu_item_2")); // To click on Transaction
		Transaction.click();
		logger.info("click on Transaction");

		Thread.sleep(5000);
		WebElement ViewShipment = driver.findElement(By.id("menu_item_23"));
		ViewShipment.click();
		logger.info(" click on View Shipment");
}
}