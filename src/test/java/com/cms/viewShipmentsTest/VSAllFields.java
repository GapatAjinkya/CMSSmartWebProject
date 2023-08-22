package com.cms.viewShipmentsTest;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.cms.Base.BaseClass;
import com.cms.pages.Login;
import com.cms.viewshipment.ShipViaLoad;

public class VSAllFields extends BaseClass {

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
		String SearchShipment = prop.getProperty("Search");
		
		if (SearchShipment.equals("Shipment Id")) {
			shipViaLoad.EnterOnShipmentIdFrom();
			shipViaLoad.clickOnSearchCriteriaOk();
			shipViaLoad.clickOnSelectShipment();
			shipViaLoad.clickOncmdOk();
		}else if (SearchShipment.equals("Package Id")) {
			
			shipViaLoad.EnterOnPackageIdFrom();
			shipViaLoad.clickOnSearchCriteriaOk();
			shipViaLoad.clickOnSelectShipment();
			shipViaLoad.clickOncmdOk();
		}else if (SearchShipment.equals("Tracking Number")) {
			shipViaLoad.EnterOnTrackingNumber();
			shipViaLoad.clickOnSearchCriteriaOk();
			shipViaLoad.clickOnSelectShipment();
			shipViaLoad.clickOncmdOk();
		}else if (SearchShipment.equals("Customer/Company")) {
			shipViaLoad.EnterOnCustomerCompany();
			shipViaLoad.clickOnSearchCriteriaOk();
			shipViaLoad.clickOnSelectShipment();
			shipViaLoad.clickOncmdOk();
		}
	}	
}
