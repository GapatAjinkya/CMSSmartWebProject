package billoflading;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ModifyBOL {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("ModifyBOL");
	public static String newcode; //to edit 

	@Test(priority = 0)
	public void setup() throws InterruptedException 
	{
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(5000);
		WebElement Userlogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtLPUserLogin"))); // Userlogin
		Userlogin.sendKeys("nilesh");
		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtLPPassword"))); // password
		password.sendKeys("Nilesh@123");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("chkRememberMe"))).click(); // chkRememberMe	
		WebElement ok = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();
		String expectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		String actualTitle = driver.getTitle();
		assert actualTitle.equalsIgnoreCase(expectedTitle) : "Title didn't match";
		System.out.println("Title Matched");
		Thread.sleep(10000);
	}

	@Test(priority = 1)
	public void Boledit() throws InterruptedException
	{		
		WebElement Transaction= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_2"))); 	// To click on Transaction
		wait.until(ExpectedConditions.elementToBeClickable(Transaction));
		Transaction.click();
		WebElement BOL= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_26"))); 	// To click on Transaction
		wait.until(ExpectedConditions.elementToBeClickable(BOL));
		BOL.click();		
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='BOLddlCarriers']")));
		String Carriers = "LTLR_WL -- LTL Rated WorldLink";
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			// Check the visible text of each option
			String visibleText = option.getText();
			if (visibleText.equalsIgnoreCase(Carriers))
			{
				select.selectByVisibleText(visibleText);
				break; 
			}
		}
		logger.info("Carriers Selected ");
		Thread.sleep(10000);
		String	CustomerCode="TestBOL";      //To search the code 
		List<WebElement> alldata = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='ELtblBOLExplorerList']//tr//td[2]")));
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
			WebElement instructionsElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='ELtblBOLExplorerList']//td[contains(text(), '" + CustomerCode + "')]")));
			instructionsElement.click();
		} else {
		    System.out.println("CustomerCode not found");
		}	
		logger.info("Customer Code Found Selected ");
		Thread.sleep(10000);		
		WebElement edit= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BOLViewBOL"))); 
		edit.click();
		//Thread.sleep(3000);
		logger.info("Click on edit  ");
		
		String editwith="code";	
		newcode="Test_AG1";         //To edit the  new code ----------------------------------
		String Counrty="INDIA";
		String ShipVia="LTLNR_WL_200--LTLNR_WL_Class 200";
		
		if(editwith.equalsIgnoreCase("code")) 
		{
		Thread.sleep(5000);
		WebElement codesend=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='txtCode']")));
		codesend.clear();
		Thread.sleep(5000);
		codesend.sendKeys(newcode);
		logger.info("Code edit successful");
		}
		else if(editwith.equalsIgnoreCase("Counrty")) 
		{
		Thread.sleep(5000);
		WebElement country= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("CDBOLCountry"))); 	
		Select selectcountry=new Select(country);
		selectcountry.selectByVisibleText(Counrty);
		logger.info("country selected Succesful");
		
		}else if(editwith.equalsIgnoreCase("ShipVia")) 
		{
			WebElement shipVias= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ddlShipVia"))); 	
			Select selectShipVia=new Select(shipVias);
			selectShipVia.selectByVisibleText(ShipVia);
			logger.info("ShipVia selected Succesful");
		}	
		WebElement btnCreateBOL= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnCreateBOL")));
		 wait.until(ExpectedConditions.elementToBeClickable((btnCreateBOL)));
		btnCreateBOL.click();
		WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='WLButton text-uppercase']"))); 	
		wait.until(ExpectedConditions.elementToBeClickable(confirm));
		confirm.click();
		logger.info("Create BOL ok selected Succesful");
		logger.info("Edit BOL  Succesful");
}
	@Test(priority = 2)
public void close() throws InterruptedException 
	{
		Thread.sleep(5000);
		try {
		List<WebElement> alldata = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='ELtblBOLExplorerList']//tr//td[2]")));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(newcode))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "CustomerCode not found");
		if (dataStatus) 
		{
			Thread.sleep(6000);
			WebElement SelectElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='ELtblBOLExplorerList']//td[contains(text(), '" + newcode + "')]")));
			 wait.until(ExpectedConditions.elementToBeClickable((SelectElement)));
			 SelectElement.click();
			 
			 WebElement closebutton =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='BOLClose']")));
			 wait.until(ExpectedConditions.elementToBeClickable((closebutton)));
			 closebutton.click();	 
				Thread.sleep(5000);
			 WebElement ok =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnConfirmBoxOk']")));
			 ok.click();
		
      logger.info("Customer is close ");
		} else {
		    System.out.println("CustomerCode not found");
		}
		} catch (StaleElementReferenceException e)
		{    
			
		}		
}
	@Test(priority = 3)
	public void delete() throws InterruptedException
	{
		Thread.sleep(5000);
		try {
		List<WebElement> alldata = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='ELtblBOLExplorerList']//tr//td[2]")));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			if (value.equals(newcode))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "CustomerCode not found");
		if (dataStatus) 
		{
			Thread.sleep(6000);
			WebElement instructionsElement =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='ELtblBOLExplorerList']//td[contains(text(), '" + newcode + "')]")));
			 wait.until(ExpectedConditions.elementToBeClickable((instructionsElement)));
			instructionsElement.click();		
			
     WebElement delete= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BOLDelete")));
     wait.until(ExpectedConditions.elementToBeClickable(delete));
     delete.click();
     
     WebElement ConfirmBoxOk= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnConfirmBoxOk")));
     wait.until(ExpectedConditions.elementToBeClickable(ConfirmBoxOk));
     ConfirmBoxOk.click();
     
     Thread.sleep(5000);
     WebElement okdelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'ok')]")));
     wait.until(ExpectedConditions.elementToBeClickable(okdelete));
     okdelete.click();
     
      logger.info("Customer is Delete ");
		} else {
		    System.out.println("CustomerCode not found");
		}
		} catch (StaleElementReferenceException e)
		{    
			
		}
	}
}


