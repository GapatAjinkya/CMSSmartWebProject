package com.cms.processreturnTest;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.cms.Base.BaseClass;
import com.cms.pages.Login;
import com.cms.processreturn.ProcessReturn;

public class ProcessRTest extends BaseClass{
	
	public static WebDriver driver;
	public static Login login;
	public static ProcessReturn pr;
	
	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {

		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}
	Logger logger = LogManager.getLogger("ProcessRTest");
	@Test
	public void ProcessReturnTest() throws IOException, Exception {

		login = new Login(driver);
		login.LoginPage();
		pr = new ProcessReturn(driver);
		pr.clickOnTransaction();
		pr.clickOnprocessReturns();
		pr.SelectReturnMethod();
		pr.Clickonsearchshipvia();
		pr.SendkeysSearchfor();
		pr.Clickonok();
		pr.SelectDate();
		pr.Clickoncustomer();
		pr.Entersearchcustomer();
		pr.selCutomerList();
		pr.ClickonCustomerSearchOkClick();
		pr.selectcustomerfromlist();
		pr.ClickonOKcustomer();
		
		
		
		
}
}