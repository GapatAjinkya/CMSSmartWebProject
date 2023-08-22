package com.cms.ProcessShipment;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cms.Base.BaseClass;

import com.cms.pages.Login;

public class ProcessShipmentTest extends BaseClass {
	public static WebDriver driver;
	public static Login login;
	public static ProcessShipment ps;
	

	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {

		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}
	Logger logger = LogManager.getLogger("ProcessShipmentTest");
	@Test
	public void ProcessShipmentTestnew() throws IOException, Exception {

		login = new Login(driver);
		login.LoginPage();
		ps = new ProcessShipment(driver);
		ps.clickOnTransaction();
		ps.Clickonprocessshipment();
		ps.ClickonShipViaSearch();
	//	ps.codeselect();
		ps.EnterSearchfor();        //SelectShipvia from prop file
		ps.ClickonOk();
		ps.AddCustomerProcess();   //EnterCustomerSearch from prop2 file
		ps.EnterWeight();
		ps.ClickonShipbutton();	
}
}