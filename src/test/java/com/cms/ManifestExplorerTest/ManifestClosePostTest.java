package com.cms.ManifestExplorerTest;
import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.cms.Base.BaseClass;
import com.cms.ManifestExplorer.ManifestExplorer;
import com.cms.pages.Login;

public class ManifestClosePostTest extends BaseClass {
	public static WebDriver driver;
	public static Login login;
	public static ManifestExplorer Me;
	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}
	
	@Test
	public void VSCarriers() throws IOException, Exception {
		
		login = new Login(driver);
		login.LoginPage();
		Me = new ManifestExplorer(driver);
		Me.clickOnTransaction();
		Me.ClickonManifestExplorer();
		Me.SelectCarriers();
		// Select carrier type 
		Me.toSelectManifest();          // To select manifest as per requirement 
		Me.ButtonClose();
		Me.clickOnConfirmBoxOk();
		Me.toSelectManifest();
		Me.ButtonPost();
		Me.clickOnConfirmBoxOk();
		Me.clickOnConfirmBoxOk();
}
}