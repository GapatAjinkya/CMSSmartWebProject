package com.cms.SupportTableTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cms.Base.BaseClass;

import com.cms.configurations.SupportTables;
import com.cms.pages.Login;

public class STDeletCustomer extends BaseClass {
	
	public static WebDriver driver;
	public static Login login;
	public static SupportTables st;
	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}
	@Test
	public void DeleteCustomerTest() throws IOException, Exception {

		login = new Login(driver);
		login.LoginPage();
		st = new SupportTables(driver);
		st.clickonConfiguration();
		st.clickonSupportTables();
		st.ClickonCustomers();
		st.ClickonCustomersCode();
		st.EnterCustomersSearch();       // To search specific customer to delete 
		st.CustomerOkClick();
		st.selectcustomerfromtable();
		st.clickonDeleteCustomer();
		st.ConfirmBoxOk();
		
}
}