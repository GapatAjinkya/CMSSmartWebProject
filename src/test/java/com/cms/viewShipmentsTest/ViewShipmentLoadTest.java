package com.cms.viewShipmentsTest;
import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.cms.Base.BaseClass;
import com.cms.pages.Login;
import com.cms.viewshipment.ShipViaLoad;

public class ViewShipmentLoadTest extends BaseClass {

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
		shipViaLoad.datepickfrom();                        // Select the date 
		String loadShipment = prop.getProperty("Load");
		
		if (loadShipment.equals("Site")) {

			shipViaLoad.clickOnSitesLoad();
			shipViaLoad.clickOnSelectSite();
			shipViaLoad.clickOnSearchCriteriaOk();
			shipViaLoad.clickOnselectShipmentid();
			shipViaLoad.clickOncmdOk();
			
		} else if (loadShipment.equals("Ship Via")) {
			shipViaLoad.clickOnShipViaLoad();
			shipViaLoad.clickOnUserGroup();
			shipViaLoad.clickOnSelectGroupOkClick();
			shipViaLoad.clickOnSelectShipvia();          // To select loaded shipvia
			shipViaLoad.EnterOnShipmentIdFrom();         // to give specific id to find specific shipment 
			shipViaLoad.datepickfrom();              // Date
			shipViaLoad.clickOnSearchCriteriaOk();
		
			//shipViaLoad.SvIdSelect();
			shipViaLoad.clickOnselectShipmentid();
			shipViaLoad.clickOncmdOk();

		} else if (loadShipment.equals("Organizations")) {
			
			shipViaLoad.clickOnOrganizationsLoad();
			shipViaLoad.clickOnSelectOrganizations();        
			shipViaLoad.clickOnSearchCriteriaOk();
			shipViaLoad.clickOncmdOk();
			
		} else if (loadShipment.equals("Carriers")) {
			
			shipViaLoad.clickOnCarriersLoadAccount();  // TO load Carriers
			shipViaLoad.clickOnSelectCarriers();
		//	shipViaLoad.clickOnProship1();
			shipViaLoad.clickOnSearchCriteriaOk();     // SearchCriteria OK button 
			shipViaLoad.clickOnCarrier();
			shipViaLoad.clickOncmdOk();                // Carriers OK	
		}
	}
}