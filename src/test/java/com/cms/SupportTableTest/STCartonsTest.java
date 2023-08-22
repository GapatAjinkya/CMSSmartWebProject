package com.cms.SupportTableTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cms.Base.BaseClass;

import com.cms.configurations.SupportTables;
import com.cms.pages.Login;

public class STCartonsTest extends  BaseClass{
	
	public static WebDriver driver;
	public static Login login;
	public static SupportTables st;
	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}
	Logger logger = LogManager.getLogger("STCartonsTest");
	@Test
	public void CartonCreateTest() throws IOException, Exception {

		login = new Login(driver);
		login.LoginPage();
		st = new SupportTables(driver);
		
		st.clickonConfiguration();
		st.clickonSupportTables();
		st.clickonCartons();
		st.CartonSearchOkClick();
		st.ClickonCartonAdd();
		st.EntertxtCode();
		st.EnterDescription();
		//st.ClearTab();
		st.EnterLength();
		st.EnterWidth();
		st.EnterHeight();
		st.EnterWeight();
		st.Enteruom();
		st.ClickonCartonOK();
		
}	
}