package products;

import java.time.Duration;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewProducts {

	
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("CreateNewProducts");
	
	@BeforeClass
	public void setup() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();
		
//		String expectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
//		String actualTitle = driver.getTitle();
//		assert actualTitle.equalsIgnoreCase(expectedTitle) : "Title didn't match";
//		System.out.println("Title Matched");
//		Thread.sleep(10000);
	}

	@AfterClass
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}
	
	@Test(priority = 1)
	public void newproduct() throws InterruptedException {
		
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
		Thread.sleep(3000);
		WebElement okclick=driver.findElement(By.xpath("//button[@onclick='onProductSearchFormOkClick()']"));
		okclick.click();
		Thread.sleep(5000);
	
		WebElement addproduct=driver.findElement(By.xpath("//button[@id='CSTProdAdd']"));
		wait.until(ExpectedConditions.visibilityOf(addproduct));
		wait.until(ExpectedConditions.elementToBeClickable(addproduct));
		addproduct.click();
		Thread.sleep(5000);
		logger.info(" Click on add  successful");
		//Product Details
	
		String newcode="ProductAG";
		String Description="Books Test";
		String Countryoforigin="UNITED STATES";
		String commodity="123123";

		
		WebElement codesend=driver.findElement(By.xpath("//input[@id='PFtxtCode']"));
		wait.until(ExpectedConditions.visibilityOf(codesend));
		wait.until(ExpectedConditions.elementToBeClickable(codesend));
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

//		WebElement DeclaredValue=driver.findElement(By.id("PFtxtDV"));
//		DeclaredValue.clear();
//		DeclaredValue.sendKeys("12");
		
		WebElement ECCN=driver.findElement(By.id("PFtxtECCN"));          //ECCN
		ECCN.sendKeys("001");
		
		WebElement Country=driver.findElement(By.xpath("//select[@id='PFcmbCO']"));        
		wait.until(ExpectedConditions.visibilityOf(Country));
		Select Countryselect=new Select(Country);
		Countryselect.selectByVisibleText(Countryoforigin);
		
		
		WebElement CommodityCode=driver.findElement(By.id("PFtxtCommodity"));
		wait.until(ExpectedConditions.visibilityOf(CommodityCode));
		CommodityCode.sendKeys(commodity);
	/*	
		WebElement element=driver.findElement(By.id("PFtxtLicenseExpiryDate"));
		JavascriptExecutor js= ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].value ='06/15/2023';", element);//month/date/year
*/
		
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
		
	//	/html/body/div[102]/div[1]/table[1]/tbody/tr/td[contains(text(),'30')],"+day+"

		
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
		wait.until(ExpectedConditions.visibilityOf(HazardClass));
		wait.until(ExpectedConditions.elementToBeClickable(HazardClass));
		Select selectclass=new Select(HazardClass);
		selectclass.selectByVisibleText(HazardClassText);
		logger.info(" Hazard Class Selected  successful");
		Thread.sleep(5000);
		
		String HazardousDivisionText="Flashpoint below -18°C(0°F)";
		WebElement  HazardousDivision =driver.findElement(By.id("PFcmbHazardDivision"));
		wait.until(ExpectedConditions.visibilityOf(HazardousDivision));
		wait.until(ExpectedConditions.elementToBeClickable(HazardousDivision));
		Select selectHD=new Select(HazardousDivision);
		selectHD.selectByVisibleText(HazardousDivisionText);
		
		logger.info(" Hazardous DivisionText Selected  successful");
		
		WebElement SpecialProvision=driver.findElement(By.id("specialProvision"));
		wait.until(ExpectedConditions.visibilityOf(SpecialProvision));
		wait.until(ExpectedConditions.elementToBeClickable(SpecialProvision));
		
		if (!SpecialProvision.isSelected()) 
		{
            // Click on the checkbox
			SpecialProvision.click();
        }
		  Assert.assertTrue(SpecialProvision.isSelected());
		logger.info("Special Provision Selected  successful");
		
		driver.findElement(By.id("PFtxtAnyText1")).sendKeys("any");
		driver.findElement(By.id("txtRegistrantId")).sendKeys("52");
		WebElement prodok=driver.findElement(By.id("btnProdFrmOk"));
		prodok.click();
		logger.info("Product Added Successful");
		
		
		
	}
	
	@Test(priority = 2)
	public void Searchproduct() throws InterruptedException {
		
		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Clickon Configuration successful");
		
		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		wait.until(ExpectedConditions.visibilityOf(SupportTables));
		wait.until(ExpectedConditions.elementToBeClickable(SupportTables));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");
		
		Thread.sleep(3000);
		WebElement products=driver.findElement(By.xpath("//a[@id='menu_item_455']"));
		wait.until(ExpectedConditions.visibilityOf(products));
		wait.until(ExpectedConditions.elementToBeClickable(products));
		products.click();
		Thread.sleep(3000);
		logger.info(" products Windo Open  successful");
		
		Thread.sleep(6000);
		
		String Criteria="Description";
	    String code="ProductAG";
	    String DescriptionSearch="Books Test";
	    String CommoditySearch="UNITED STATES";
	    String ECCNSearch="123123";
	    WebElement Search=driver.findElement(By.xpath("//input[@id='txtCSTProdSearch']"));
		if(Criteria.equalsIgnoreCase("code")) {
			
			WebElement Code = driver.findElement(By.xpath("//input[@id='CSTProdRadCode']"));
			wait.until(ExpectedConditions.visibilityOf(Code));
			wait.until(ExpectedConditions.elementToBeClickable(Code));
			Code.click();
			logger.info("Code selected");
			Search.sendKeys(code);                                 //To search customer
		
		 
		}else if(Criteria.equalsIgnoreCase("Description")) {
			WebElement Description = driver.findElement(By.xpath("//input[@id='CSTProdradDescription']"));
			wait.until(ExpectedConditions.visibilityOf(Description));
			wait.until(ExpectedConditions.elementToBeClickable(Description));
			Description.click();
			Search.sendKeys(DescriptionSearch);    
			logger.info("Description selected");
			
		}else if(Criteria.equalsIgnoreCase("Commodity")) {
			
			WebElement Commodity = driver.findElement(By.xpath("//input[@id='CSTProdradCommodity']"));
			wait.until(ExpectedConditions.visibilityOf(Commodity));
			wait.until(ExpectedConditions.elementToBeClickable(Commodity));
			Commodity.click();
			Search.sendKeys(CommoditySearch);    
			
		}
		else if(Criteria.equalsIgnoreCase("ECCN")) {
			
			WebElement CustomerCity = driver.findElement(By.xpath("//input[@id='CSTProdradEccn']"));
			wait.until(ExpectedConditions.visibilityOf(CustomerCity));
			wait.until(ExpectedConditions.elementToBeClickable(CustomerCity));
			CustomerCity.click();
			Search.sendKeys(ECCNSearch);    
			
		}
		driver.findElement(By.xpath("//button[@onclick='onProductSearchFormOkClick()']")).click();
	}

}
