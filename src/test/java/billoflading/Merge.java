package billoflading;

import java.time.Duration;
import java.util.List;
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

public class Merge {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("BillofLading");
	String BOLNumbers="4123456789123834";
	String CustomerCode = "TestBOL";
	String Carriers = "LTLR_WL -- LTL Rated WorldLink";//Search Carrier
	
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
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
	
	@Test(priority =0)
	public void SelectBOLcustomer() throws InterruptedException
	{		
		WebElement Transaction= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_2"))); 	// To click on Transaction
		wait.until(ExpectedConditions.elementToBeClickable(Transaction));
		Transaction.click();
		WebElement BOL= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu_item_26"))); 	// To click on Transaction
		wait.until(ExpectedConditions.elementToBeClickable(BOL));
		BOL.click();		
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='BOLddlCarriers']")));
		Select select = new Select(dropdown);
		select.selectByVisibleText(Carriers);
		logger.info("Carriers Selected ");
		
//--------------------------		
	
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
		if (dataStatus) 
		{
			Thread.sleep(5000);
			WebElement Customercode =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='ELtblBOLExplorerList']//td[contains(text(), '" + CustomerCode + "')]")));
			Customercode.click();			
		} else {
		    System.out.println("CustomerCode not found");
		}	
		logger.info("Customer Code Found Selected ");        
}
	
	@Test(priority =1)
	public void Mergebol() throws InterruptedException
	{		
			
		WebElement Mergebol= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='BOLMerge']")));
	    wait.until(ExpectedConditions.elementToBeClickable(Mergebol));
	    Mergebol.click();
	    Thread.sleep(5000);
	    
		WebElement btnConfirmBoxOk= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnConfirmBoxOk']")));
        wait.until(ExpectedConditions.elementToBeClickable(btnConfirmBoxOk));
        btnConfirmBoxOk.click();
    	Thread.sleep(5000);
        List<WebElement> alldata = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@id='tblBOLMergeList']//tr//td")));
		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();		
			if (value.equals(BOLNumbers))
			{
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "CustomerCode not found");
		if (dataStatus) 
		{
		
			WebElement Customercode =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='tblBOLMergeList']//td[contains(., '" + BOLNumbers + "')]")));
			Customercode.click();			
		} else {
		    System.out.println("CustomerCode not found");
		}	
		logger.info("Customer Code Found Selected ");  
		
		WebElement ok= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnMergeBOLOk']")));
	    wait.until(ExpectedConditions.elementToBeClickable(ok));
	    ok.click();
		Thread.sleep(5000);
	    WebElement errorok= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btnErrorBoxOk']")));
	    wait.until(ExpectedConditions.elementToBeClickable(errorok));
	    errorok.click();
		
}
	}

