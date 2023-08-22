package productsNegative;

import java.util.concurrent.TimeUnit;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductsCode {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ProductsCode");
	
	@Test(priority = 0)
	public void ProductCodeTest() throws InterruptedException {
		productWindo();
		SearchProduct("");
		addButton();
		newproduct("ProductAG");
		okproduct();
		captureError();
	}
	@Test(priority = 1)
	public void codetest() throws InterruptedException {
		code("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/1");
		okproduct();
		captureError();
	}
	@Test(priority = 2)
	public void codeBlanck() throws InterruptedException {
		code("");
		okproduct();
		captureError();
	}
	@Test(priority = 3)
	public void descriptiontest() throws InterruptedException {
		code("ProductAG");
		description("asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/asdf2@%.?/1");
		okproduct();
		captureError();
	}
	@Test(priority = 4)
	public void descriptiontestBlanck() throws InterruptedException {
		description("");
		okproduct();
		captureError();
	}
	public void code(String Code) throws InterruptedException {
		Thread.sleep(2000);
		WebElement codesend=driver.findElement(By.xpath("//input[@id='PFtxtCode']"));
		codesend.clear();
		codesend.sendKeys(Code);
	}
	public void description (String Description) throws InterruptedException {
		Thread.sleep(2000);
		WebElement pDescription=driver.findElement(By.id("PFtxtDescription"));
		pDescription.clear();
		pDescription.sendKeys(Description);
	}
public void addButton() throws InterruptedException {

	WebElement addproduct= driver.findElement(By.xpath("//button[@id='CSTProdAdd']"));
	addproduct.click();
	logger.info(" Click on add  successful");
	}
	public void productWindo() throws InterruptedException {
		
		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Clickon Configuration successful");
		
		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		wait.until(ExpectedConditions.visibilityOf(SupportTables));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");
		
		Thread.sleep(3000);
		WebElement products=driver.findElement(By.xpath("//a[@id='menu_item_455']"));
		wait.until(ExpectedConditions.visibilityOf(products));
		products.click();
		Thread.sleep(3000);
		logger.info(" products Windo Open  successful");
	}		
		public void SearchProduct(String code) throws InterruptedException {
		    WebElement Search=driver.findElement(By.xpath("//input[@id='txtCSTProdSearch']"));
			WebElement Code = driver.findElement(By.xpath("//input[@id='CSTProdRadCode']"));
			wait.until(ExpectedConditions.visibilityOf(Code));
			Code.click();
			logger.info("Code selected");
			Search.sendKeys(code); 			
		WebElement okclick=driver.findElement(By.xpath("//button[@onclick='onProductSearchFormOkClick()']"));
		wait.until(ExpectedConditions.visibilityOf(okclick));
		okclick.click();
		Thread.sleep(5000);
	
}
		public void captureError() throws InterruptedException {
			Thread.sleep(5000);
			WebElement errorMessage = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
			Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		        String actualErrorMessage = errorMessage.getText();
		       
			if (actualErrorMessage.equals("Value should be less than 50 characters. Please try again.")) {
	            System.out.println("Handling first error message."+actualErrorMessage);
	            Assert.assertEquals(actualErrorMessage, "Value should be less than 50 characters. Please try again.", "Incorrect error message");
	        } else if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
	        	 System.out.println("Handling Second error message."+actualErrorMessage);
	            Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.", "Incorrect error message");
	        } else if (actualErrorMessage.equals("Product could not be added in the database.")) {
	        	 System.out.println("Handling Third error message."+actualErrorMessage);
	            Assert.assertEquals(actualErrorMessage, "Product could not be added in the database.", "Incorrect error message");
	        } else {
	            System.out.println("Unexpected error message: " + actualErrorMessage);
	        }
			WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
			error.click();
		}
		public void newproduct(String newcode) throws InterruptedException {
			String Description="Books Test";
			String Countryoforigin="UNITED STATES";
			String commodity="123123";
			WebElement codesend=driver.findElement(By.xpath("//input[@id='PFtxtCode']"));
			codesend.sendKeys(newcode);
			WebElement unit=driver.findElement(By.xpath("//input[@id='PFtxtUnitPrice']"));
			unit.clear();
			unit.sendKeys("1");
			WebElement Weight=driver.findElement(By.id("PFtxtWeight"));
			Weight.clear();
			Weight.sendKeys("1");
			WebElement pDescription=driver.findElement(By.id("PFtxtDescription"));
			pDescription.sendKeys(Description);	
			WebElement uom=driver.findElement(By.id("PFtxtUOM"));
			uom.sendKeys("Each");
			WebElement uomwgt=driver.findElement(By.id("PFtxtWeightUOM"));
			uomwgt.sendKeys("LBS");	
			WebElement ECCN=driver.findElement(By.id("PFtxtECCN"));          //ECCN
			ECCN.sendKeys("001");	
			WebElement Country=driver.findElement(By.xpath("//select[@id='PFcmbCO']"));        
			Select Countryselect=new Select(Country);
			Countryselect.selectByVisibleText(Countryoforigin);
			WebElement CommodityCode=driver.findElement(By.id("PFtxtCommodity"));
			wait.until(ExpectedConditions.visibilityOf(CommodityCode));
			CommodityCode.sendKeys(commodity);
			String month="August 2023";
		//	String day="30";
			
			WebElement datepicker=driver.findElement(By.id("PFtxtLicenseExpiryDate"));
			datepicker.click();
			
			//boolean istrue=true;
			while(true){
			String text=driver.findElement(By.xpath("//th[@class='datepicker-switch']")).getText();
			if(text.equals(month)) 
			{
				break;
			}
			else 
			{
				driver.findElement(By.xpath("//body[1]/div[102]/div[1]/table[1]/thead[1]/tr[1]/th[3]")).click();//Next
			}
			}
			driver.findElement(By.xpath("(//td[@class='day'])[30]")).click();
			Thread.sleep(5000);
			logger.info("Date  Selected  successful");
			//To Add  Dangerous Goods----------------------------------------------------------------
			
			WebElement DangerousGoods=driver.findElement(By.id("PFchkDG"));
			wait.until(ExpectedConditions.visibilityOf(DangerousGoods));
			wait.until(ExpectedConditions.elementToBeClickable(DangerousGoods));
			if (!DangerousGoods.isSelected()) 
			{
	            // Click on the checkbox
	            DangerousGoods.click();
	        }
	        // Verify that the checkbox is selected
	        Assert.assertTrue(DangerousGoods.isSelected());
	        logger.info(" DangerousGoods Selected  successful");
		
			String HazardClassText="Flammable Liquids";
			WebElement  HazardClass =driver.findElement(By.xpath("//select[@id='PFcmbClass']"));
			Select selectclass=new Select(HazardClass);
			selectclass.selectByVisibleText(HazardClassText);
			logger.info(" Hazard Class Selected  successful");
			Thread.sleep(5000);
			
			String HazardousDivisionText="Flashpoint below -18°C(0°F)";
			WebElement  HazardousDivision =driver.findElement(By.id("PFcmbHazardDivision"));
			Select selectHD=new Select(HazardousDivision);
			selectHD.selectByVisibleText(HazardousDivisionText);
			
			logger.info(" Hazardous DivisionText Selected  successful");
			
			WebElement SpecialProvision=driver.findElement(By.id("specialProvision"));
			if (!SpecialProvision.isSelected()) 
			{
	            // Click on the checkbox
				SpecialProvision.click();
	        }
			  Assert.assertTrue(SpecialProvision.isSelected());
			logger.info("Special Provision Selected  successful");
			
			driver.findElement(By.id("PFtxtAnyText1")).sendKeys("any");
			driver.findElement(By.id("txtRegistrantId")).sendKeys("52");
		}
		public void okproduct() {
			WebElement prodok=driver.findElement(By.id("btnProdFrmOk"));
			prodok.click();
		}
		@BeforeClass
		public void setup() throws InterruptedException {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			wait = new WebDriverWait(driver, 60);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
}