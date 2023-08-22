package com.cms.ManifestExplorerTest;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.cms.Base.BaseClass;
import com.cms.ManifestExplorer.ManifestExplorer;
import com.cms.ProcessShipment.ProcessShipment;
import com.cms.pages.Login;

public class MEcreateShipClosePostTest extends BaseClass {
	public static WebDriver driver;
	public static Login login;
	public static ManifestExplorer Me;
	public static ProcessShipment ps;

	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {

		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}

	@Test
	public void createShipClosePostTest() throws IOException, Exception {

		login = new Login(driver);
		login.LoginPage();
		Me = new ManifestExplorer(driver);
		ps = new ProcessShipment(driver);
		Me.clickOnTransaction();
		Me.ClickonManifestExplorer();
		Me.CreateActiveManifest();
		Me.clickOnTransaction();
		ps.Clickonprocessshipment();
		Thread.sleep(5000);
		ps.ClickonShipViaSearch();
		// ps.codeselect();
		ps.EnterSearchfor();
		ps.ClickonOk();
		ps.AddCustomerProcess();
		ps.EnterWeight();
		ps.ClickonShipbutton();
		
		Thread.sleep(3000);
		Me.clickOnTransaction();
		Me.ClickonManifestExplorer();
		Me.SelectCarriers(); // Select carrier type
		// Me.toSelectManifest(); // To select manifest as per requirement
		// Me.ButtonClose();
		// Me.ButtonPost();

		// logger.info(" --------MEcreateShipClosePost Test successful----------- ");
	}
}