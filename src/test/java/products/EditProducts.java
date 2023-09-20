package products;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditProducts {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("EditProducts");



	@BeforeClass
	public void setup() throws InterruptedException {

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
	}

	@AfterMethod
	public void teardown() throws InterruptedException {

		Thread.sleep(10000);
		driver.close();
	}
	@Test(priority = 1)
	public void edit() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Clickon Configuration successful");

		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");

		WebElement products=driver.findElement(By.xpath("//a[@id='menu_item_455']"));
		products.click();
		logger.info(" products Windo Open  successful");
		Thread.sleep(6000);
		// To search the code to edit

		 String Searchwith="ProductAG2";     //To search the product

		 WebElement Search=driver.findElement(By.xpath("//input[@id='txtCSTProdSearch']"));

		 Search.sendKeys(Searchwith);
		 logger.info(" Search with   successful");

		driver.findElement(By.xpath("//button[@onclick='onProductSearchFormOkClick()']")).click();
		logger.info(" click on ok Search with   successful");
		Thread.sleep(5000);

		WebElement selectproduct=driver.findElement(By.xpath("//tr[@data-index='0']"));
		 selectproduct.click();
		logger.info(" Product select  successful");

		Thread.sleep(3000);
		WebElement editbutton=driver.findElement(By.xpath("//button[@id='CSTProdEdit']"));
		 editbutton.click();
		logger.info(" Click on edit Button select  successful");
		Thread.sleep(6000);

	String editwith="code";
	String newcode="ProductAG1";         //To edit the product new code ----------------------------------
	String Description="ProductAG1";

	if(editwith.equalsIgnoreCase("code"))
	{
	Thread.sleep(5000);
	WebElement codesend=driver.findElement(By.xpath("//input[@id='PFtxtCode']"));
	codesend.clear();
	Thread.sleep(3000);
	codesend.sendKeys(newcode);
	logger.info("Code edit successful");
	}
	else if(editwith.equalsIgnoreCase("Description"))
	{
	Thread.sleep(5000);
	WebElement pDescription=driver.findElement(By.id("PFtxtDescription"));
	pDescription.clear();
	Thread.sleep(3000);
	pDescription.sendKeys(Description);
	logger.info("Description edit successful");
	}

	try {
		Thread.sleep(5000);
		WebElement prodok=driver.findElement(By.id("btnProdFrmOk"));
		prodok.click();
	}catch(Exception e){

		Assert.fail("Failed to click on _ok. Exception: " + e.getMessage());
	}
	}

	@Test(priority = 2)
	public void delete() throws InterruptedException {
		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Clickon Configuration successful");

		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));

		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");

		WebElement products=driver.findElement(By.xpath("//a[@id='menu_item_455']"));

		products.click();
		logger.info(" products Windo Open  successful");
		Thread.sleep(6000);
		// To search the code to edit

		 String Searchwith="ProductAG1";     //To search the product to delete----------------------------------

		 WebElement Search=driver.findElement(By.xpath("//input[@id='txtCSTProdSearch']"));
		 Search.sendKeys(Searchwith);

		 logger.info(" Search with   successful");
		driver.findElement(By.xpath("//button[@onclick='onProductSearchFormOkClick()']")).click();
		logger.info(" click on ok Search with   successful");
		Thread.sleep(3000);

		WebElement selectproduct=driver.findElement(By.xpath("//tr[@data-index='0']"));
		 selectproduct.click();
		logger.info(" Product select  successful");

		 WebElement deletebutton=driver.findElement(By.xpath("//button[@id='CSTProdDelete']"));
		 deletebutton.click();
		 logger.info(" Click on Delete successful");

		 Thread.sleep(3000);
		 WebElement Confirmdelete=driver.findElement(By.xpath("//button[@id='btnConfirmBoxOk']"));
		 Confirmdelete.click();
		 logger.info(" Click on Confirm ok  successful");
	}

}
