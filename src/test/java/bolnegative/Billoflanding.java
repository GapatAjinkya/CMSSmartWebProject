package bolnegative;


import java.time.Duration;
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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Billoflanding {

	public static WebDriver driver;
	public static WebDriverWait wait;
	 Logger logger = LogManager.getLogger("Billoflanding");

	 @Test(priority = 0)
		public void TestAMDateFrom() throws InterruptedException {
		 OpenBOLExplorer();
		 CarriersSelect("LTLNR_WL -- LTLNR_WL");
		 close();
		}

	 @Test(priority =1)
	 public void MergeTest() throws InterruptedException {
		 Merge();
		 captureError();
	 }
	 @Test(priority =2)
	 public void TestRate() throws InterruptedException {
		 Rate();
	 }
	 public void Merge() throws InterruptedException {
			Thread.sleep(2000);
		 driver.findElement(By.id("BOLMerge"));
			Thread.sleep(2000);
		WebElement ok=driver.findElement(By.id("btnConfirmBoxOk"));
		ok.click();
	 }
	 public void Rate() throws InterruptedException {
			Thread.sleep(3000);
		 driver.findElement(By.id("BOLRateBOL"));
			Thread.sleep(2000);
			WebElement ok=driver.findElement(By.xpath("//button[@type='button'][contains(.,'ok')]"));

		if(ok.isDisplayed()) {
			WebElement text=driver.findElement(By.xpath("//*[@id='jconfirm-box31187']/div/div[2]"));
		String textofrate=	text.getText();
		System.out.println(textofrate);
		}

	 }
	 public void checkbol() throws InterruptedException {
		 String	CustomerCode="CMS";      //To search the code
			List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='ELtblBOLExplorerList']//tr//td[2]"));
			boolean dataStatus = false;
			for (WebElement ele : alldata) {
				String value = ele.getText();
				if (value.equals(CustomerCode))
				{
					System.out.println(value);
					dataStatus = true;
					break;
				}
			}
			Assert.assertTrue(dataStatus, "CustomerCode not found");
			if (dataStatus) {
				//Thread.sleep(5000);
				WebElement instructionsElement =driver.findElement(By.xpath("//table[@id='ELtblBOLExplorerList']//td[contains(text(), '" + CustomerCode + "')]"));
				instructionsElement.click();
			} else {
			    System.out.println("CustomerCode not found");
			}
			logger.info("Customer Code Found Selected ");
			Thread.sleep(10000);
			WebElement BOLViewBOL= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BOLViewBOL")));
			BOLViewBOL.click();
			//Thread.sleep(3000);
			logger.info("Click on BOLViewBOL  ");
	 }
	 public void close() throws InterruptedException {
			Thread.sleep(5000);
			 String	newcode="CMS";
			try {
				List<WebElement> alldata = wait.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='ELtblBOLExplorerList']//tr//td[2]")));

				boolean dataStatus = false;
				for (WebElement ele : alldata) {
					String value = ele.getText();
					if (value.equals(newcode)) {
						System.out.println(value);
						dataStatus = true;
						break;
					}
				}
				Assert.assertTrue(dataStatus, "CustomerCode not found");
				if (dataStatus) {
					Thread.sleep(6000);
					WebElement SelectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//table[@id='ELtblBOLExplorerList']//td[contains(text(), '" + newcode + "')]")));
					wait.until(ExpectedConditions.elementToBeClickable((SelectElement)));
					SelectElement.click();

					WebElement closebutton = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='BOLClose']")));
					wait.until(ExpectedConditions.elementToBeClickable((closebutton)));
					closebutton.click();
					Thread.sleep(5000);
					WebElement ok = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnConfirmBoxOk']")));
					ok.click();

					logger.info("Customer is close ");
				} else {
					System.out.println("CustomerCode not found");
				}
			} catch (StaleElementReferenceException e) {
			}
		}
	 public void CarriersSelect(String Carriers) throws InterruptedException {

		 WebElement dropdown = driver.findElement(By.xpath("//select[@id='BOLddlCarriers']"));
			Select select = new Select(dropdown);
			select.selectByVisibleText(Carriers);
	 }

	 public void OpenBOLExplorer() throws InterruptedException {
			Thread.sleep(3000);
			WebElement Transaction = driver.findElement(By.id("menu_item_2")); // To click on Transaction
			Transaction.click();
			Thread.sleep(3000);
			driver.findElement(By.id("menu_item_26")).click(); // To click on BOL
			Thread.sleep(3000);
		}
	 public void captureError() throws InterruptedException {
			Thread.sleep(5000);
			WebElement errorMessage = driver.findElement(By.id("errorMsg"));
			Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
			String actualErrorMessage = errorMessage.getText();
			if (actualErrorMessage
					.equals("No BOL to Merge!")) {
				System.out.println("Handling error message." + actualErrorMessage);
				Assert.assertEquals(actualErrorMessage,
						"No BOL to Merge!",
						"Incorrect error message");
			} else if (actualErrorMessage.equals("Date range cannot be more than 365 days. Please try again.")) {
				System.out.println("Handling  error message." + actualErrorMessage);
				Assert.assertEquals(actualErrorMessage, "Date range cannot be more than 365 days. Please try again.",
						"Incorrect error message");
			}else if (actualErrorMessage.equals("DateFrom cannot be greater than DateTo. Please try again.")) {
				System.out.println("Handling  error message." + actualErrorMessage);
				Assert.assertEquals(actualErrorMessage, "DateFrom cannot be greater than DateTo. Please try again.",
						"Incorrect error message");
				}
			else {
				Assert.fail("Unexpected error message: " + actualErrorMessage);
			}
			WebElement error = driver.findElement(By.id("btnErrorBoxOk"));
			error.click();
		}
	 @BeforeClass
		public void setup() throws InterruptedException {

		    System.setProperty("webdriver.chrome.driver", "E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
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

}
