package com.cms.SupportTableTest;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cms.Base.BaseClass;

import com.cms.configurations.SupportTables;
import com.cms.pages.Login;

public class STCustomer extends BaseClass {
	public static WebDriver driver;
	public static Login login;
	public static SupportTables st;
	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}
	
	@Test
	public void STCreateCustomer() throws IOException, Exception {
		login = new Login(driver);
		login.LoginPage();
		st = new SupportTables(driver);
		st.clickonConfiguration();
		st.clickonSupportTables();
		st.ClickonCustomers();
        st.ClickonCustomersCode();
	//	st.EnterCustomersSearch();          // To search customer from properties file 
	    st.CustomerOkClick();
	    st.ClickonAddCustomer();
	    st.EnterCustomerCode();
	    st.EnterCompany();
	    st.EnterContact();
	    st.EnterAddressall();
	    st.EnterAddCityStateZip();
	    st.SelectCountry();
	    st.EnterPhone();
	    st.EnterFax();
	    st.EnterEmail();
	    st.ClickCustomerFormOK();
	    
}
}