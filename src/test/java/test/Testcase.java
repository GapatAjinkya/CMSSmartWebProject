package test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
	// Logger logger = LogManager.getLogger("Testcase");

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
	public void shipvia() throws InterruptedException {

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
																																																												// the
		wait.until(ExpectedConditions.elementToBeClickable(shipviaSearch));
		shipviaSearch.click();
		// logger.info("Clicked on shipviaSearch");
		System.out.println("Clicked on shipviaSearch");
		Thread.sleep(5000);
		String shipviacode = "LTLR_WL_55";
		WebElement sendcode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSCSearchSS")));
		wait.until(ExpectedConditions.visibilityOf(sendcode));
		sendcode.sendKeys(shipviacode);
		// logger.info("shipviaSearch Enter Value Successful");
		System.out.println("shipviaSearch Enter Value Successful");

		WebElement ok = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSearchOk_PS")));
		wait.until(ExpectedConditions.visibilityOf(ok));
		wait.until(ExpectedConditions.elementToBeClickable(ok));
		ok.click();

		// ----------------------------------------------------------------------------------------------------------


		By checkBoxLocator = By.xpath("//input[@type='checkbox' and contains(@id,'BOX')]");
		int numberOfIterations = 16;
		// creating an Empty Integer List
		List<Integer> arr = new ArrayList<>(3);
		arr.add(2);
//		arr.add(8);
//		arr.add(11);
		for (int i = 0; i < numberOfIterations; i++)
		{
			if (arr.contains(i))
			{
				// Select the customer

				WebElement customer =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='AddressesClick()']")));
				wait.until(ExpectedConditions.elementToBeClickable(customer));
				customer.click();
				// logger.info("Clicked on Customer");
				System.out.println("Clicked on Customer");
				// To Customer Search Criteria
				Thread.sleep(5000);
				WebElement searchcustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSCSearch"))); // searchcustomer
				wait.until(ExpectedConditions.visibilityOf(searchcustomer));
				searchcustomer.sendKeys("CMS");

				WebElement List = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selCutomerList")));
				Select CustomerList = new Select(List);
				CustomerList.selectByValue("1"); // To select Global

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick='onCustomerSearchOkClick()']"))).click(); // click on ok
				Thread.sleep(5000);
				// logger.info("Customer Searched");
				System.out.println("Customer Searched");
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//table[@id='tblCustomerList']//td[1][contains(text(), 'CMS')]"))).click();
				WebElement Customerok = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addressformOk")));
				wait.until(ExpectedConditions.elementToBeClickable(Customerok));
				Customerok.click(); // Click on OK

				Thread.sleep(5000);
				// logger.info("Customer Added");
				System.out.println("Customer Added");

				// Select the service button
				WebElement buttonSpecialServices = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSpecialServices")));
				wait.until(ExpectedConditions.visibilityOf(buttonSpecialServices));
				wait.until(ExpectedConditions.elementToBeClickable(buttonSpecialServices));
				buttonSpecialServices.click();
				Thread.sleep(5000);

				List<WebElement> checkboxes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(checkBoxLocator));
				if (i < checkboxes.size())
				{
					WebElement checkbox = checkboxes.get(i);
					checkboxes.get(i).click();
					    String checkboxText = checkbox.getText();

					System.out.println("-----------===----------" + i+checkboxText);

				}

				if(i==2) {

					driver.findElement(By.xpath("//input[@onclick='ShowFedExWSHoldAtLocationAddress();']")).click(); // address
					Thread.sleep(3000);

			/*		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnfedexholdAtLocationAddressDataModalOk']"))).click();
			     // modify
					Thread.sleep(3000);
					 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtCompanyNameHoldFedex"))).sendKeys("Test");

					driver.findElement(By.id("txtContactNameHoldFedex")).sendKeys("Test");
					driver.findElement(By.id("txtAddress1HoldFedex")).sendKeys("Testaddress");
					driver.findElement(By.id("txtCityHoldFedex")).sendKeys("lorton");
					driver.findElement(By.id("txtStateHoldFedex")).sendKeys("va");
					driver.findElement(By.id("txtZipHoldFedex")).sendKeys("22079");
					Thread.sleep(3000);

					WebElement country =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtCountryFedexHold")));
					Select countrys = new Select(country);
					countrys.selectByVisibleText("UNITED STATES");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnfedexholdAtLocationAddressDataModalOk"))).click();
					*/
					Thread.sleep(3000);
				}

				if(i==8)
				{
					WebElement cod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SL8")));
					Select codselect = new Select(cod);
					codselect.selectByVisibleText("Cash");
					 Thread.sleep(5000);
					 WebElement codamout=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("INSS8")));
					 codamout.clear();
					 Thread.sleep(5000);
					 codamout.sendKeys("10");
					 Thread.sleep(5000);
					 /*
					 Thread.sleep(5000);
				WebElement Address=	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button1")));
					 wait.until(ExpectedConditions.elementToBeClickable(Address));
					 Address.click();
					 Thread.sleep(5000);

					 driver.findElement(By.xpath("//button[@id='btncodAddressDataModalOk']")).click();


					 Thread.sleep(5000);
					 WebElement CompanyNameCOD=	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtCompanyNameCOD")));
					 CompanyNameCOD.clear();
					 CompanyNameCOD.sendKeys("CMS GlobalSoft Org Name");

					 WebElement ContactNameCOD=	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CompanyNameCOD")));
					 ContactNameCOD.clear();
					 ContactNameCOD.sendKeys("Professional Services Org Contact");

					 WebElement Address1COD=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtAddress1COD")));
					 Address1COD.clear();
					 ContactNameCOD.sendKeys("1751 THOMPSON ST");

					 WebElement CityCOD=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtCityCOD")));
					 CityCOD.clear();
					 CityCOD.sendKeys("AURORA");

					 WebElement StateCOD=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtStateCOD")));
					 StateCOD.clear();
					 StateCOD.sendKeys("OH");

					 WebElement ZipCOD=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtZipCOD")));
					 ZipCOD.clear();
					 ZipCOD.sendKeys("44202");

					 WebElement CountryCOD =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtCountryCOD")));
						Select countrysC = new Select(CountryCOD);
						countrysC.selectByVisibleText("UNITED STATES");

						 WebElement PhoneCOD=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtPhoneCOD")));
						 PhoneCOD.clear();
						 PhoneCOD.sendKeys("7034558292");

						 WebElement Save= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncodAddressDataModalOk")));
						 wait.until(ExpectedConditions.elementToBeClickable(Save));
						 Save.click();
						 */
				}
				if(i==11)
				{
					WebElement Tirelossselect =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SL11")));
					Select countrys = new Select(Tirelossselect);
					countrys.selectByVisibleText("Label Format 4X10.5");

				}

				WebElement okclick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnOk']")));
				okclick.click();
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtManual']"))).sendKeys("1.00");
				// logger.info("Manual Weight is fill ");
				System.out.println("Manual Weight is fill ");
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cmdRate"))).click();

				System.out.println("Click on Rate");
				Thread.sleep(5000);
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnShipClick"))).click(); // Click on ship
				// Wait for the next iteration (you may need to add an explicit wait here)
				Thread.sleep(5000);
		}
	}
	}}