package com.cms.ProcessShipment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cms.Base.BaseClass;

public class Internationalshipment extends BaseClass {
	
	public Internationalshipment(WebDriver rdriver) {
		driver= rdriver;
		PageFactory.initElements(rdriver, this);
	}
	Logger logger = LogManager.getLogger("Internationalshipment");
	
	
	
	
	
}
