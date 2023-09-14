package com.com.ArchivedManifestsTest;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cms.Base.BaseClass;
import com.cms.archivedmanifests.ArchivedManifests;
import com.cms.pages.Login;

public class AMSelectWithDateTest extends BaseClass {
	public static WebDriver driver;
	public static Login login;
	public static ArchivedManifests am;

	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {

		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}
	@Test
	public void ArchivedManifestsTest() throws IOException, Exception {

		login = new Login(driver);
		login.LoginPage();
		am = new ArchivedManifests(driver);
		am.clickOnTransaction();
		am.clickonArchivedManifests();
		am.selectcarriers();             //To select carriers
		am.ClickonDateSearchButton();
		am.Toselectdatefrom();
		am.Selectcarrierfromtable("UPSVA_PS");
		am.clickonView();
		am.CarrierfromShipmentstable();
	}

}