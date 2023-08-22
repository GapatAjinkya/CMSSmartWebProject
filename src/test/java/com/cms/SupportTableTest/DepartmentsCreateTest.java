package com.cms.SupportTableTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cms.Base.BaseClass;
import com.cms.configurations.SupportTableDepartments;
import com.cms.pages.Login;

public class DepartmentsCreateTest extends BaseClass {
	
	public static WebDriver driver;
	public static Login login;
	public static SupportTableDepartments std;
	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}
	@Test
	public void STDepartmentsCreate() throws IOException, Exception {

		login = new Login(driver);
		login.LoginPage();
		std = new SupportTableDepartments(driver);
		std.clickonConfiguration();
		std.clickonSupportTables();
		std.ClickonDepartments();
		std.Searchcriteriaok();
		std.ClickonDeptAdd();
		std.SendKeystocode();
		std.enterDescription();
		std.okclickDepartmentForm();
		//std.tearDown();
}
}