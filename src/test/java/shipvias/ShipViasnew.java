package shipvias;

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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShipViasnew {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ShipViasnew");
	@Test(priority = 0)
	public void Shipvianew() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(2000);
		logger.info("Click on Configuration successful");

		WebElement carriers = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='menu_item_44']")));

		carriers.click();
		Thread.sleep(3000);
		logger.info("Click on carriers successful");

		WebElement ShipVias = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu_item_441")));
		ShipVias.click();
		logger.info("Click on ShipVias successful");
		Thread.sleep(3000);

		WebElement ShipViasSearh = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtSearchSVSF")));
		ShipViasSearh.sendKeys("AbC");
		logger.info("Search  ShipVias successful");

		WebElement ShipViasok = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@onclick='SVSFOkClick()']")));
		ShipViasok.click();
		logger.info("Click on ShipVias ok  successful");

		Thread.sleep(5000);

		boolean isError = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']")).isDisplayed();

		 if (isError)
		 {
		WebElement text=driver.findElement(By.id("errorMsg"));
		WebElement okerror=driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		wait.until(ExpectedConditions.visibilityOf(okerror));
		wait.until(ExpectedConditions.elementToBeClickable(okerror));
		Thread.sleep(5000);
		okerror.click();
		 System.out.println("Adding new ship via");
		 }else {
			 System.out.println("---");
		 }
		 Thread.sleep(5000);

		WebElement addshipvia = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ShipViasAdd")));
		addshipvia.click();
		logger.info("Click on add ShipVias successful");

		String shipviacode = "FEX_Test1_GN1";
		String ShipviaDescription = "Ground";
		String ShipviaService = "GN -- FedEx Ground®";
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
		logger.info("select Payment  successful");
//To select bill
		WebElement bill = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbBillDutyTaxToSVF")));
		Select billselect = new Select(bill);
		billselect.selectByVisibleText("Recipient");
	
		logger.info("select bill successful");

		WebElement Billas = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cmbBillAsSVF")));
		Select BA = new Select(Billas);
		BA.selectByVisibleText("[SAME] Bill As This ShipVia");

		logger.info("select billAs successful");

		WebElement Shipviacode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCodeSVF")));
		Shipviacode.sendKeys(shipviacode);
	
		logger.info("Send ship viacode  successful");

		WebElement Dis = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtDescriptionSVF")));
		Dis.sendKeys(ShipviaDescription);
	
		logger.info("Send Discription  successful");
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
	@Test(priority = 1)
	public void edit() throws InterruptedException {
		Thread.sleep(3000);
		WebElement editbutton = driver.findElement(By.id("ShipViasEdit"));
		editbutton.click();
		logger.info(" edit button successful");

		String editwith = "code";
	 	String newcode = "TestAG1";             // To edit the product new code ----------------------------------
		String Description = "departments Test";
		String Carriertype="";
		Thread.sleep(5000);

		if (editwith.equalsIgnoreCase("code")) {
			Thread.sleep(3000);
			WebElement codesend = driver.findElement(By.xpath("//input[@id='txtCodeSVF']"));
			codesend.clear();
			Thread.sleep(3000);
			codesend.sendKeys(newcode);
			logger.info("Code edit successful");
		} else if (editwith.equalsIgnoreCase("Description")) {
			Thread.sleep(3000);
			WebElement pDescription = driver.findElement(By.id("txtDescriptionSVF"));
			pDescription.clear();
			Thread.sleep(3000);
			pDescription.sendKeys(Description);
			logger.info("Description edit successful");
		} else if (editwith.equalsIgnoreCase("Carriertype")) {

			WebElement dropdown = driver.findElement(By.id("cmbAccountSVF"));
			dropdown.clear();
			Thread.sleep(3000);
			Select select = new Select(dropdown);
			select.selectByVisibleText(Carriertype);
			logger.info(" Type Select  successful");
		}

		WebElement okclick = driver.findElement(By.id("OkClickSVF"));
		okclick.click();
		Thread.sleep(3000);
		/*
		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='ShipViaList']//td[1]"));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			System.out.println(value+"1");
			if (value.equals(newcode))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Carrier  is not edit");
		logger.info("Carrier edit is successful ");
		*/
}
	@Test(priority = 2)
	public void delete() throws InterruptedException {

		WebElement deletebutton =driver.findElement(By.id("ShipViasDelete"));
		deletebutton.click();
		WebElement ok =driver.findElement(By.id("btnConfirmBoxOk"));
		ok.click();

		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblCarrierAccountsList']//td"));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			System.out.println(value+"1");
			if (value.equals("No matching records found"))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Account is  Deleted");
		logger.info("Delete Account is successful ");
}
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
@AfterClass
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}
	}
