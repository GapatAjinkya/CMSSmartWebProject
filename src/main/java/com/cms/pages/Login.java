package com.cms.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import com.cms.Base.BaseClass;

public class Login extends BaseClass {
	public static WebDriverWait wait;
	public Login(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public String user= "nilesh";
	public String pass="Nilesh@123";


	@FindBy(id = "menu_item_1")
	private WebElement menuitem1;

	@FindBy(id = "menu_item_15")
	private WebElement menuitem15;

	@FindBy(id = "txtLPUserLogin")
	private WebElement UserLogin;

	@FindBy(id = "txtLPPassword")
	private WebElement password;

	@FindBy(id = "chkRememberMe")
	private WebElement rememberme;

	@FindBy(xpath = "//button[@onclick=\"LoginFormOkClick()\"]")
	private WebElement clickok;

	public Login clickOnLocalconfig() {
		click(menuitem1);
		return this;
	}

	public Login clickOnLoginas() {
		click(menuitem15);
		return this;
	}

	public Login UserLogin() throws InterruptedException {
		Thread.sleep(3000);
		sendkeys(UserLogin, user);
		return this;

	}

	public Login userPassword() {
		sendkeys(password,pass);
		return this;
	}

	public Login clickOnRemember() {
		rememberme.click();
		return this;
	}

	public Login clickOnOk() {
		click(clickok);
		return this;
	}
	public Login checkTital() {

		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		assert ActualTitle.equals(ExpectedTitle) : "Title does not match";
		return this;
	}

	public void LoginPage () throws InterruptedException {
		

//			System.setProperty("webdriver.chrome.driver",
//					"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
//			ChromeOptions options = new ChromeOptions();
//			// options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
//			// options.addArguments("--remote-allow-origins=*");
//
//			driver = new ChromeDriver(options);

			driver.manage().window().maximize();
		//	driver.get("http://localhost:8090/SmartWeb/#");
			Thread.sleep(3000);
			driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
			driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
			Thread.sleep(3000);
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
			Userlogin.sendKeys("admin");
			WebElement password = driver.findElement(By.id("txtLPPassword")); // password
			password.sendKeys("password");
			driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe
			WebElement ok = driver.findElement(By.xpath("//button[@onclick='LoginFormOkClick()']"));
			ok.click();
			Thread.sleep(5000);
		}
		
	}

