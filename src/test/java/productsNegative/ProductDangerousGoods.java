package productsNegative;

import java.time.Duration;

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

public class ProductDangerousGoods {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ProductsCode");

    @Test(priority = 0)
	public void TestHazardClass() throws InterruptedException {
    	productWindo();
		SearchProduct("");
		addButton();
		newproduct("ProductAG2");
    	DangerousGoodsCheckBox();
    	HazardClass("Flammable Liquids");
    	okproduct();
    	captureError();
	}


	public void HazardClass(String HazardClassText) throws InterruptedException {
		WebElement  HazardClass =driver.findElement(By.xpath("//select[@id='PFcmbClass']"));
		Select selectclass=new Select(HazardClass);
		selectclass.selectByVisibleText(HazardClassText);
		logger.info(" Hazard Class Selected  successful");
		Thread.sleep(5000);
	}
	public void DangerousGoodsCheckBox() throws InterruptedException {

		WebElement DangerousGoods=driver.findElement(By.id("PFchkDG"));

		if (!DangerousGoods.isSelected())
		{
            // Click on the checkbox
            DangerousGoods.click();
        }
        // Verify that the checkbox is selected
        Assert.assertTrue(DangerousGoods.isSelected());
        logger.info(" DangerousGoods Selected  successful");
	}
	public void DangerousGoodsCheckBoxuncheck() {
		WebElement DangerousGoods=driver.findElement(By.id("PFchkDG"));
		 if (DangerousGoods.isSelected()) {
	        	DangerousGoods.click();
	        }
	}

	public void HDivision(String HazardousDivisionText) {
		WebElement  HazardousDivision =driver.findElement(By.id("PFcmbHazardDivision"));
		Select selectHD=new Select(HazardousDivision);
		selectHD.selectByVisibleText(HazardousDivisionText);
		logger.info(" Hazardous DivisionText Selected  successful");

	}
	public void addButton() throws InterruptedException {
		Thread.sleep(3000);
		WebElement addproduct= driver.findElement(By.xpath("//button[@id='CSTProdAdd']"));
		addproduct.click();
		logger.info(" Click on add  successful");
		}

	public void newproduct(String newcode) throws InterruptedException {
		Thread.sleep(3000);
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
		String month="January 2024";
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
	public void SearchProduct(String code) throws InterruptedException {
		WebElement Search = driver.findElement(By.xpath("//input[@id='txtCSTProdSearch']"));
		WebElement Code = driver.findElement(By.xpath("//input[@id='CSTProdRadCode']"));
		wait.until(ExpectedConditions.visibilityOf(Code));
		Code.click();
		logger.info("Code selected");
		Search.sendKeys(code);
		WebElement okclick = driver.findElement(By.xpath("//button[@onclick='onProductSearchFormOkClick()']"));
		wait.until(ExpectedConditions.visibilityOf(okclick));
		okclick.click();
	}

	public void captureError() throws InterruptedException {
		Thread.sleep(5000);
		WebElement errorMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='errorMsg']")));
		Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed");
		String actualErrorMessage = errorMessage.getText();

		if (actualErrorMessage.equals("No records found!")) {
			System.out.println("Handling first error message." + actualErrorMessage);
			Assert.assertEquals(actualErrorMessage, "No records found!", "Incorrect error message");
		}  else if (actualErrorMessage.equals("Field value cannot be blank. Please try again.")) {
       	 System.out.println("Handling Second error message."+actualErrorMessage);
         Assert.assertEquals(actualErrorMessage, "Field value cannot be blank. Please try again.", "Incorrect error message");
     }else {
			System.out.println("Unexpected error message: " + actualErrorMessage);
		}
		WebElement error = driver.findElement(By.xpath("//button[@id='btnErrorBoxOk']"));
		error.click();
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
		WebElement products = driver.findElement(By.xpath("//a[@id='menu_item_455']"));
		wait.until(ExpectedConditions.visibilityOf(products));
		products.click();
		Thread.sleep(3000);
		logger.info(" products Windo Open  successful");
	}

	public void okproduct() {
		WebElement prodok = driver.findElement(By.id("btnProdFrmOk"));
		prodok.click();
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
}