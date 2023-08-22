package com.cms.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cms.Base.BaseClass;

public class Login extends BaseClass {

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
		clickOnLocalconfig();
		clickOnLoginas();
		UserLogin();
		userPassword();
		clickOnRemember();
		clickOnOk();
		checkTital();
		Thread.sleep(5000);
	}
}
