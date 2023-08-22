package viewShipmentTask;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cms.Base.BaseClass;
import com.cms.pages.Login;
import com.cms.viewshipment.ShipViaLoad;

public class VSSearchWithPackageId extends BaseClass{
	public static WebDriver driver;
	public static Login login;
	public static ShipViaLoad shipViaLoad;

	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {

		driver = setUp();
		System.out.println("******* starting Test" + m.getName() + " *******");
	}

	@Test
	public void VSCarriers() throws IOException, Exception {

		login = new Login(driver);
		login.LoginPage();
		shipViaLoad = new ShipViaLoad(driver);
		shipViaLoad.clickOnTransaction();
		shipViaLoad.clickOnViewShipment();
		shipViaLoad.EnterOnPackageIdFrom();
		shipViaLoad.EnterOnPackageIdTo();
		shipViaLoad.datepickfrom();
		shipViaLoad.clickOnSearchCriteriaOk();
		shipViaLoad.clickOnSelectShipment();
		shipViaLoad.clickOncmdOk();
		
}
}