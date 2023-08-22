package com.cms.ManifestExplorerTest;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cms.Base.BaseClass;
import com.cms.ManifestExplorer.ManifestExplorer;
import com.cms.pages.Login;
public class ActiveMECreatenewTest extends BaseClass {

	public static WebDriver driver;
	public static Login login;
	public static ManifestExplorer Me;
	

	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {

		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}

	@Test
	public void ActiveMEnewTest() throws IOException, Exception {

		login = new Login(driver);
		login.LoginPage();
		Me = new ManifestExplorer(driver);
		Me.clickOnTransaction();
		Me.ClickonManifestExplorer();
		Me.SelectCarriers();
		Me.clickOnMECreate();
		Me.clickOnConfirmBoxOk();
		Me.ActiveManifestradio();
		Me.CarrierAccount();               // Select CarrierAccount from prop2
		Me.EnterMeReference();
		Me.ClickonbuttonManifestsFormOk();
	}
}