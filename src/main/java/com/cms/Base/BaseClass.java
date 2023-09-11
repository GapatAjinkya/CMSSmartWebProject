 package com.cms.Base;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {


	public static WebDriver driver;
	public static Properties prop;
	public static Properties prop2;
	public WebDriver setUp() throws IOException {
		
		prop = new Properties();
	    prop2 = new Properties();
	    
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
		prop.load(fis);
		
		FileInputStream fis2 = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config2.properties");
		prop2.load(fis2);

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
//		options.setExperimentalOption("excludeSwitches",new String[]{"enable-automation"});
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	
		System.out.println(prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

	public void waitForVisibilityWait(WebElement e) {
		try {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(e));
	}catch(Exception ex) {
		 System.out.println("An error occurred: " + ex.getMessage());
	}
	}
	public void elementToBeClickable(WebElement e) {
		try {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}catch(Exception ex) {
		 System.out.println("******An error occurred******: " + ex.getMessage());
	}
	}
	public void click(WebElement e) {
		
		waitForVisibilityWait(e);
		elementToBeClickable(e);
		e.click();
	}

	public void sendkeys(WebElement e, String Text) {
		
		waitForVisibilityWait(e);
		e.sendKeys(Text);
	}
	

	public void clearText(WebElement e) {
		waitForVisibilityWait(e);
		e.clear();
	}

	public String getText(WebElement e) {
		waitForVisibilityWait(e);
		return e.getText();
	}

	public String getAttribute(WebElement e, String attribute) {
		waitForVisibilityWait(e);
		return e.getAttribute(attribute);
	}

	public void acceptAlert(WebElement e) {
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		System.out.println("alert -----" + driver.switchTo().alert().getText());
		alert.accept();
		System.out.println("alert is accepted");
	}
	public void tearDown() throws InterruptedException {
		Thread.sleep(10000);
		driver.close();
	}
}