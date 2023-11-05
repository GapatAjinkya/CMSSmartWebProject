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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class OrgSiteGroupsTest {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("OrgSiteGroupsTest");
	
	
	public String UserGroupcode="TestAg";
	public String Description="AdministratorTest";
	public String orggroup="Test -- Test";
	
	
	@Test(priority = 0)
	public void Orgadd() throws InterruptedException {
		Organization();
		Searchorg("Test");
		edit();
		organization("globalsoft");
		Sites("CMS_NE");
		savesettingbutton();	
	}
	@Test(priority = 1,dependsOnMethods = "Orgadd")
	public void usergroup() throws InterruptedException {
		login();
		Organization();
		SearcCode("Test");
		orgcodecheck("Test");		
		usergroupadd();
		Carriersindex("All");        // in this we can change the carriers selection 
		AccessRights("All");
	    driver.findElement(By.id("UserGroupsSaveSettings")).click();
		Thread.sleep(3000);
	    driver.findElement(By.id("btnConfirmBoxOk")).click();
		Thread.sleep(3000);
		WebElement error=driver.findElement(By.id("errorMsg"));
		String text=error.getText();
		if(text.equals("Users have to log out and log back in for the changes to take effect.")) {
			driver.findElement(By.id("btnErrorBoxOk")).click();	
			Thread.sleep(3000);
			logout();
		}
		
	}
	public void usergroupadd() throws InterruptedException {
		WebElement UserGroupsbutton = driver.findElement(By.id("OrgSitesUserGroups"));
		UserGroupsbutton.click();
		Thread.sleep(3000);
		WebElement error= driver.findElement(By.id("btnErrorBoxOk"));
		 if (error.isDisplayed()) {
				error.click();
				Thread.sleep(3000);
				UsergroupGeneralInformation();
	        } else {
	        	userGroupsCodeSelect();
	            System.out.println("No records found!. Continuing with the next step.");
	        }
	}
	public void UsergroupGeneralInformation() throws InterruptedException {

		WebElement addbutton = driver.findElement(By.id("UserGroupsAdd"));
		addbutton.click();
		Thread.sleep(3000);
		
		WebElement codeinput=driver.findElement(By.id("UserGroupsCode"));
		codeinput.sendKeys(UserGroupcode);
		
		WebElement disinput=driver.findElement(By.id("UserGroupDescription"));
		disinput.sendKeys(Description);
		
		WebElement orgselect=driver.findElement(By.id("OrgSitesGroupDDL"));
		Select group=new Select(orgselect);
		group.selectByVisibleText(orggroup);
		
		WebElement allowaccess= driver.findElement(By.id("chkAccessGlobalAddBook"));
		allowaccess.click();
		 
		WebElement okbutton=driver.findElement(By.id("UserGroupsOKbtn"));
		okbutton.click();	
	}
	
	public void Carriers() throws InterruptedException {
		List<WebElement> checkboxes=driver.findElements(By.xpath("//table[@id='UserGroupsCarriersList']//td[1]//input[@type='checkbox']"));
	    for (WebElement checkbox : checkboxes) {
	        if (checkbox.isSelected()) {
	            checkbox.click();
	        }
	    }
	    
	    String arg[]= {"AMZCEN" ,"BAX", "CANPAR", "CAP","DGF", "DGFEU","DHLAPI ","DHLG"," DHLUK ","EXL" ,"EXP", "FDXE"," FEX"," FIRSTMILE",
	    		"GEN", "LTL", "ONTRAC"," PLFCGLS"," PLFCW" ,"PLFCWI" ,"PUR" ,"PURL"," PURV9"," ROYALMAIL", "ROYALMAILRECSP" ,"SPD" ,"TNL" ,"TUK"," UMI", "UPS" ,"UPS", "SCS" ,"UPSAF","USP"};
		Thread.sleep(3000);
		int count=arg.length;
		List<WebElement> list=driver.findElements(By.xpath("//table[@id='UserGroupsCarriersList']//td[2]"));
		for(int i=0;i<count;i++) {
	
	    WebElement checkbox = driver.findElement(By.xpath("//td[contains(., '"+arg[i]+"')]//preceding::td[1]//input[@type='checkbox']"));
		  checkbox.click();
			Thread.sleep(1000);         
		}
	}
public void Carriersindex(String selectoptions) throws InterruptedException {
	Thread.sleep(3000);
	if(selectoptions.equalsIgnoreCase("All")) {
		driver.findElement(By.xpath("//table[@id='UserGroupsCarriersList']//input[@name='btSelectAll']")).click();
		
	}else {
		
	   int[] checkboxIndices = {0, 1, 2,4,6,7,9,10,8,11,30};
	
	List<WebElement> checkboxes=driver.findElements(By.xpath("//table[@id='UserGroupsCarriersList']//td[1]//input[@type='checkbox']"));
	   // Uncheck all checkboxes
 for (WebElement checkbox : checkboxes) {
     if (checkbox.isSelected()) {
         checkbox.click();
     }
 }
	Thread.sleep(3000);
    // Loop through the indices and select the checkboxes
    for (int index : checkboxIndices) {
        WebElement checkbox = driver.findElements(By.xpath("//table[@id='UserGroupsCarriersList']//td[.//input[@type='checkbox']]")).get(index);
        
        // Check the checkbox if it's not already selected
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
}
}
public void AccessRights(String AccessSelect) throws InterruptedException {
	Thread.sleep(3000);
	if(AccessSelect.equalsIgnoreCase("All")) {
		driver.findElement(By.xpath("//table[@id='UserGroupsAccessRightsList']//input[@name='btSelectAll']")).click();
		
	}else {
	   int[] checkboxIndices = {0, 1, 2,4,6,7,9,10,8,11,30};
	
	List<WebElement> checkboxes=driver.findElements(By.xpath("//table[@id='UserGroupsAccessRightsList']//td[1]//input[@type='checkbox']"));
	   // Uncheck all checkboxes
 for (WebElement checkbox : checkboxes) {
     if (checkbox.isSelected()) {
         checkbox.click();
     }
 }
	Thread.sleep(3000);
    // Loop through the indices and select the checkboxes
    for (int index : checkboxIndices) {
        WebElement checkbox = driver.findElements(By.xpath("//table[@id='UserGroupsAccessRightsList']//td[.//input[@type='checkbox']]")).get(index);
        
        // Check the checkbox if it's not already selected
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

}
}	
	public void organization(String orgcode) throws InterruptedException {
		List<WebElement> checkboxes=driver.findElements(By.xpath("//table[@id='XREFOrgsList']//td[1]//input[@type='checkbox']"));
		   // Uncheck all checkboxes
	    for (WebElement checkbox : checkboxes) {
	        if (checkbox.isSelected()) {
	            checkbox.click();
	        }
	    }
		Thread.sleep(3000);
		List<WebElement> list=driver.findElements(By.xpath("//table[@id='XREFOrgsList']//td[2]"));
		for(WebElement text:list) {
			String codetext=text.getText();			
			if(codetext.equals(orgcode)) {
				 WebElement checkbox = driver.findElement(By.xpath("//td[contains(., '"+orgcode+"')]//preceding::td[1]//input[@type='checkbox']"));
		            boolean isSelected = checkbox.isSelected();

		            if (isSelected) {
		                checkbox.click();
		            } else {
		                checkbox.click();
		            }
			}
		}
	}
	public void Sites(String Sitescode) throws InterruptedException {
		List<WebElement> checkboxes=driver.findElements(By.xpath("//table[@id='XREFSitesList']//td//input[@type='checkbox']"));
		   // Uncheck all checkboxes
	    for (WebElement checkbox : checkboxes) {
	        if (checkbox.isSelected()) {
	            checkbox.click();
	        }
	    }
		Thread.sleep(3000);
		List<WebElement> list=driver.findElements(By.xpath("//table[@id='XREFSitesList']//td[2]"));
		for(WebElement text:list) {
			String codetext=text.getText();	
			if(codetext.equals(Sitescode)) {
				 WebElement checkbox = driver.findElement(By.xpath("//td[contains(., '"+Sitescode+"')]//preceding::td[1]//input[@type='checkbox']"));
		            boolean isSelected = checkbox.isSelected();
		            if (isSelected) {
		                checkbox.click();
		            } else {      
		                checkbox.click();
		            }
			}}
	}
	public void SearcCode(String code ) throws InterruptedException {
		WebElement Configuration = driver.findElement(By.id("txtXREFSearch"));
		Configuration.sendKeys(code);
		driver.findElement(By.xpath("//button[@onclick='XREFGroupsSearchOkClick()']")).click();
		Thread.sleep(3000);
	}
	public void Searchorg(String org) throws InterruptedException {
		
		WebElement Configuration = driver.findElement(By.id("txtXREFSearch"));
		Configuration.sendKeys(org);
		driver.findElement(By.xpath("//button[@onclick='XREFGroupsSearchOkClick()']")).click();
		Thread.sleep(3000);
		try {
		WebElement Norecord =driver.findElement(By.id("btnErrorBoxOk"));
		if(Norecord.isDisplayed()) {
			Norecord.click();
			Thread.sleep(3000);
			addfunctionality("Test","Test");
		}else{
			orgcodecheck(org);
		}
		}
		catch(NoSuchElementException e) {
			
		}
	}
	public void addfunctionality(String codeorg ,String des) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("OrgSitesGroupsAdd")).click();
		Thread.sleep(3000);
		WebElement code= driver.findElement(By.id("XREFCode"));
		code.clear();
		code.sendKeys(codeorg);
	
		WebElement description = driver.findElement(By.id("XREFDescription"));
		description.clear();
		description.sendKeys(des);
		
		WebElement grouptype=driver.findElement(By.id("XREFGroupTypeDDL"));
		Select dropdown= new Select(grouptype);
		dropdown.selectByVisibleText("User Group");
		
		driver.findElement(By.xpath("//input[@id='radXREFMergeAutomatically']")).click(); 
		
		driver.findElement(By.xpath("//button[@onclick='XRefGroupsOKClick()']")).click();

	}
	public void orgcodecheck(String code ) {
		
		List<WebElement> orgcode =driver.findElements(By.xpath("//table[@id='OrgSitesGroupsList']"));
		
		for(WebElement text:orgcode) {
			String codetext=text.getText();
			if(codetext.equals(code)) {
				text.click();
			}
			
		}
	}
	
	public void userGroupsCodeSelect() {
		
List<WebElement> orgcode =driver.findElements(By.xpath("//table[@id='UserGroupsList']"));
		
		for(WebElement text:orgcode) {
			String codetext=text.getText();
			if(codetext.equals(UserGroupcode)) {
				text.click();
		
			}
			
		}
	}
	public void edit () throws InterruptedException {
		WebElement editbutton=driver.findElement(By.id("OrgSitesGroupsEdit"));
		editbutton.click();
		Thread.sleep(3000);
		WebElement code= driver.findElement(By.id("XREFCode"));
		code.clear();
		code.sendKeys("Test");
	
		WebElement description = driver.findElement(By.id("XREFDescription"));
		description.clear();
		description.sendKeys("Test");
		
		WebElement grouptype=driver.findElement(By.id("XREFGroupTypeDDL"));
		Select dropdown= new Select(grouptype);
		dropdown.selectByVisibleText("User Group");
		
		driver.findElement(By.xpath("//input[@id='radXREFMergeAutomatically']")).click(); 
		
		driver.findElement(By.xpath("//button[@onclick='XRefGroupsOKClick()']")).click();
	}
	public void savesettingbutton() throws InterruptedException {
	
		driver.findElement(By.id("OrgSitesGroupsSaveSettings")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("btnConfirmBoxOk")).click();
		Thread.sleep(3000);
		WebElement error=driver.findElement(By.id("errorMsg"));
		String text=error.getText();
		if(text.equals("Users have to log out and log back in for the changes to take effect.")) {
			driver.findElement(By.id("btnErrorBoxOk")).click();	
			Thread.sleep(3000);
			logout();
		}	
	}
	public void logout() throws InterruptedException {
		driver.findElement(By.id("menu_item_1")).click(); 
		WebElement loagout=driver.findElement(By.id("menu_item_16"));
		loagout.click();
		Thread.sleep(3000);
		driver.findElement(By.id("btnConfirmBoxOk")).click();	
	}
	public void login() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		WebElement loagout=driver.findElement(By.id("menu_item_15"));
		loagout.click();
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.clear();
		Userlogin.sendKeys("admin");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.clear();
		password.sendKeys("password");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
		WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
		ok.click();
	}
	public void Organization() throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		Configuration.click();
		WebElement Org = driver.findElement(By.id("menu_item_42"));
		Org.click();
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
