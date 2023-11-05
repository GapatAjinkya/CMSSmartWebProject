package orgSiteGroups;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class UserAddingTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("UserAddingTest");
	
	
	public String UserGroupcode="Ajinkya";
	public String Description="AdministratorTest";
	public String orggroup="Test -- Test"; 
	public String org="TestAjinkya";
	
	
    @Test
	public void TestUser() throws InterruptedException {
		openUser();
		CheckUser();
	}
	public void CheckUser() throws InterruptedException {
		Thread.sleep(3000);
		WebElement user = driver.findElement(By.id("txtUsersSearchUSF"));
		user.sendKeys(org);
		driver.findElement(By.xpath("//button[@onclick='UsersSearchFormOkClick()']")).click();
		Thread.sleep(3000);
		 boolean Norecordpopup = false;
		try {
		 Norecordpopup=driver.findElement(By.id("btnErrorBoxOk")).isDisplayed();
         }catch(NoSuchElementException e) {
			
		}
		if(Norecordpopup) {
			WebElement Norecord =driver.findElement(By.id("btnErrorBoxOk"));
			Norecord.click();
			Thread.sleep(3000);
			addfunctionality("TestAjinkya","Test");
			Resetpass();
			Logout();
			login();
			LicenseAgreement();
		}else{
			orgcodecheck();
			Resetpass();	
			Logout();
			Thread.sleep(3000);
			login();
			LicenseAgreement();			
		}		
		}
	public void orgcodecheck() {
		
		List<WebElement> orgcode =driver.findElements(By.xpath("//table[@id='tblUsers_UsersList']"));
		
		for(WebElement text:orgcode) {
			String codetext=text.getText();
			if(codetext.equals(org)) {
				text.click();
			}
			
		}
	}
	public void addfunctionality(String codeorg ,String des) throws InterruptedException {
		WebElement add=driver.findElement(By.id("UsersPageAdd"));
		add.click();
		Thread.sleep(3000);
		WebElement code= driver.findElement(By.id("UFtxtCode"));
		code.clear();
		code.sendKeys(codeorg);
	
		WebElement username = driver.findElement(By.id("UFtxtUserName"));
		username.clear();
		username.sendKeys(des);
		
		WebElement grouptype=driver.findElement(By.xpath("//select[@id='UFcmbGroup']"));
		Select dropdown= new Select(grouptype);
		dropdown.selectByVisibleText(orggroup);		
		
	//	driver.findElement(By.id("UFchkPassword")).click();
		driver.findElement(By.xpath("//button[@onclick='OkClickUsersForm()']")).click();
	}
	public void Resetpass() throws InterruptedException {
		Thread.sleep(3000);
		WebElement password=driver.findElement(By.id("UsersPageResetPwd"));
		password.click();
		Thread.sleep(3000);
		WebElement ConfirmResetPassword =driver.findElement(By.id("btnConfirmBoxOk"));
		
		if(ConfirmResetPassword.isDisplayed()) {
			ConfirmResetPassword.click();
			Thread.sleep(5000);
             WebElement error= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnErrorBoxOk")));
             error.click();
		}else{
         Assert.fail("Test Fail -Password was reset successfully and user will be forced to change password after first login. Not Displayed");
		}
		
	}
	public void editmethod() throws InterruptedException {
		Thread.sleep(3000);
		WebElement edit=driver.findElement(By.id("UsersPageEdit"));
		edit.click();
	}
	public void openUser() throws InterruptedException {
		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		Thread.sleep(3000);
		WebElement usermodul = driver.findElement(By.xpath("//*[@id='Users']"));
		usermodul.click();
		Thread.sleep(3000);
		WebElement user = driver.findElement(By.id("menu_item_431"));
		user.click();
	}
	public void LicenseAgreement() throws InterruptedException {
		try {
		Thread.sleep(3000);
		WebElement iagree=driver.findElement(By.xpath("//input[@id='radYes']"));
		iagree.click();
		driver.findElement(By.xpath("//button[@id='btnAgreementOK']")).click();	
		}catch(NoSuchElementException e) {
			
		}
		
		Thread.sleep(3000);
	    WebElement changepassword= driver.findElement(By.id("errorMsg"));
	    String text=changepassword.getText();
	    if(text.equals("Your current password has to be changed after first login. You will be re-directed to 'Change Password' page.")) {
	    	driver.findElement(By.id("btnErrorBoxOk")).click();
	    	Thread.sleep(3000);
	    	WebElement currentpass=driver.findElement(By.xpath("//input[@id='txtCurrentPassword']"));
	    	currentpass.sendKeys("password");  	
	    	WebElement newpassword=driver.findElement(By.xpath("//input[@id='txtNewPassword']"));
	    	newpassword.sendKeys("Test@123");
	    	WebElement confirmpassword=driver.findElement(By.xpath("//input[@id='txtConfirmPassword']"));
	    	confirmpassword.sendKeys("Test@123");
	    	driver.findElement(By.xpath("//button[@onclick='ChangePasswordClick()']")).click();
	    	Thread.sleep(3000); 	
	    	
	     WebElement error =driver.findElement(By.id("btnErrorBoxOk"));
	     boolean okerror=error.isDisplayed();
	    	
	    	if(okerror) {
	    		error.click();
	
	    	}
					
	    }
	}
	public void login() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.clear();
		Userlogin.sendKeys(org);
		Thread.sleep(3000);
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.clear();
		password.sendKeys("password");
		Thread.sleep(3000);
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']")).click();
	
	}
	
	public void Logout() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_1")).click(); 
		WebElement loagout=driver.findElement(By.id("menu_item_16"));
		loagout.click();
		Thread.sleep(3000);
		driver.findElement(By.id("btnConfirmBoxOk")).click();
		Thread.sleep(3000);
	}
	@BeforeClass
	public void setup() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\new\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		driver = new ChromeDriver(options);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("password");
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
		Thread.sleep(3000);
	}
}
