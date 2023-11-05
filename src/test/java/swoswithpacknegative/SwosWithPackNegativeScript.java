package swoswithpacknegative;
import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SwosWithPackNegativeScript {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("SwosWithPackNegativeScript");
	
	public static String Custcode = "SWOS";
	public static String ShipVia = "FSMS(MPS:YES)_GN";	
	
	@Test(priority = 0)
	public void TestaddGroups() throws InterruptedException {
		OpenPs();
		CheckGroups();
	}
	public void CheckGroups() {
		try {
			Thread.sleep(2000);
			WebElement GropudButton = driver.findElement(By.id("btnGroups"));
			GropudButton.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@onclick='onSWOSGrSearchPSOkClick()']")).click(); // Search ok
			Thread.sleep(3000);
			// Locate the Code table and find all rows
			List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblSWOSGroupFormListPS']//td"));
			boolean codeFound = false;
			for (WebElement ele : alldata) {
				String value = ele.getText();
				if (value.contains(Custcode)) {
					codeFound = true;
					ele.click();// Select that group
					WebElement undoPackButton = driver.findElement(By.id("UndoButtonSGFPS"));
					if (undoPackButton.isEnabled()) {
						undoPackButton.click();
					//	Thread.sleep(2000);
						driver.findElement(By.id("btnConfirmBoxOk")).click();
				//		Thread.sleep(2000);	
						driver.findElement(By.id("ViewButtonSGFPS")).click();// click on view
				//		Thread.sleep(2000);
						driver.findElement(By.xpath("//*[@id='SWOSShipmentsList']/tbody//tr[1]")).click();// Select// Master
																						
					//	Thread.sleep(2000);
						driver.findElement(By.id("DoneButtonSSL")).click();
					//	Thread.sleep(2000);
						driver.findElement(By.id("btnOkButtonSGFPS")).click();
					//	Thread.sleep(2000);
						
						WebElement popup = driver.findElement(By.id("btnErrorBoxOk"));
						if (popup.isDisplayed()) {
							popup.click();
							CreatePackageCheck();
						}		
					} else {
						// If the "undo pack" button is disabled, click the "OK" button directly
						WebElement okButton = driver.findElement(By.id("btnOkButtonSGFPS"));
						okButton.click();
						Thread.sleep(3000);
						WebElement popup = driver.findElement(By.id("btnErrorBoxOk"));
						if (popup.isDisplayed()) {
							popup.click();
							CreatePackageCheck();
						}
					}
					Thread.sleep(2000);
					// Click the "OK" button
					WebElement ok = driver.findElement(By.id("btnOkButtonSGFPS"));
					 ok.click();
					break;
				}
			}
			if (!codeFound) {
				// If the code is not found, click the "Add" button
				WebElement addButton = driver.findElement(By.id("AddNewButtonSGFPS"));
				addButton.click();
				Thread.sleep(3000);
				WebElement Customercode = driver.findElement(By.id("SWOSFrmtxtCustomerCodePS"));
				Customercode.sendKeys(Custcode);
				WebElement dis = driver.findElement(By.id("SWOSFrmtxtDescriptionPS"));
				dis.sendKeys("Test");
				WebElement checkbox = driver.findElement(By.id("chkRecurrentPS"));
				checkbox.click();
				WebElement okButton = driver.findElement(By.xpath("//button[@onclick='OkClickSWOSGroupsForm()']"));
				okButton.click(); // Click "Ok" to create the new group
				Thread.sleep(2000);
				WebElement ok = driver.findElement(By.id("btnOkButtonSGFPS"));
				 ok.click();			 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void DeleteFunction() {
		driver.findElement(By.id("btnDeleteItems")).click();
	}	
	public void PackageDetailsDelete() throws InterruptedException {
		List<WebElement> PackageDetailsTable = driver
				.findElements(By.xpath("//table[@id='framePackDet']//input[@type='checkbox']"));
		int checkboxCount = PackageDetailsTable.size();
		int input=1;
	
	        driver.findElement(By.xpath("//input[contains(@id, 'radPack') and contains(@id, '0')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("framePackDetBOX0")).click();
			Thread.sleep(2000);
			WebElement pkQuantity = driver.findElement(By.xpath("//table[@id='framePackDet']//tr//td[4]"));
			String PackageDetailsQuantity = pkQuantity.getText();
			int quantity = Integer.parseInt(PackageDetailsQuantity);

			WebElement inputBox = driver.findElement(By.id("framePackDettxt0"));
			// Enter the quantity into the input box
			inputBox.clear();
			inputBox.sendKeys(String.valueOf(quantity));
			Thread.sleep(2000);
			 DeleteFunction();
				Thread.sleep(2000);
			 WebElement confirmMsg =driver.findElement(By.id("btnConfirmBoxOk"));
			 if(confirmMsg.isDisplayed()) {
				 confirmMsg.click();
					Thread.sleep(2000);
				 driver.findElement(By.xpath("//input[contains(@id, 'frameConsolidatedBOX') and contains(@id, '0')]")).click();
				 WebElement pkQuantity1 = driver.findElement(By.xpath("//table[@id='frameConsolidated']//tr//td[5]"));
					String PackageDetailsQuantity1 = pkQuantity1.getText();
					int quantity1 = Integer.parseInt(PackageDetailsQuantity1);
					WebElement inputBox2 = driver.findElement(By.id("frameConsolidatedtxt0"));
					// Enter the quantity into the input box
					inputBox2.clear();
					inputBox2.sendKeys(String.valueOf(quantity1));
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[contains(@id, 'radPack') and contains(@id, '0')]")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("btnAddItems")).click();
					
			 }else {
				 System.out.println("Do you want to delete the selected item."+" - Is not Displayed");
			 }
		}
public void CreatePackageCheck() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.id("cmdPack")).click(); // cmdPack
		Thread.sleep(2000);
		List<WebElement> checkboxes = driver
				.findElements(By.xpath("//input[contains(@id, 'frameConsolidatedBOX') and contains(@id, '')]"));// Check
																												// Box
		int checkboxCount = checkboxes.size();
		System.out.println("Number of Package in the table: " + checkboxCount);
		for (int i = 0; i <checkboxCount; i++) {
	    //	Thread.sleep(2000);
			driver.findElement(By.id("btnCreatePackage")).click(); // Create Package
		//	Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id, 'radPack') and contains(@id, '" + i + "')]")).click();
		//	Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id, 'frameConsolidatedBOX') and contains(@id, '0')]"))
					.click();
		//	Thread.sleep(2000);
			WebElement quantityElement = driver.findElement(By.xpath("//table[@id='frameConsolidated']//tr//td[5]"));
			String quantityText = quantityElement.getText();
			int quantity = Integer.parseInt(quantityText);
			WebElement inputBox = driver.findElement(By.id("frameConsolidatedtxt0"));
			// Enter the quantity into the input box
			inputBox.clear();
			inputBox.sendKeys(String.valueOf(quantity));
		//	Thread.sleep(2000);
			driver.findElement(By.id("btnAddItems")).click();
		}
		
		PackageDetailsDelete();
}	
	public void OpenPs() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
		Transaction.click();
		driver.findElement(By.id("menu_item_21")).click(); // To click on Process ShipmentS
		Thread.sleep(3000);
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}

}
