package com.cms.configurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cms.Base.BaseClass;

public class SupportTableDepartments extends BaseClass {
	
	
	public SupportTableDepartments(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	Logger logger = LogManager.getLogger("SupportTableDepartments");

	
	@FindBy(id="menu_item_4")
	private WebElement Configuration;
	public SupportTableDepartments clickonConfiguration() throws InterruptedException {
		Thread.sleep(5000);
		click(Configuration);
		logger.info("clickOnConfiguration successful");
		return this;
	}
	
	@FindBy(id="menu_item_45")
	private WebElement SupportTables;
	public SupportTableDepartments clickonSupportTables() throws InterruptedException {
		Thread.sleep(3000);
		click(SupportTables);
		logger.info("clickOnSupportTables successful");
		return this;
	}
	@FindBy(xpath ="//a[@id='menu_item_452']")
	private WebElement Departments;
	public SupportTableDepartments ClickonDepartments() throws InterruptedException {
		
		Thread.sleep(5000);
		click(Departments);
		logger.info("ClickonDepartments successful");
		return this;
	
}
	@FindBy(id="txtCSTDeptSearch")
	private WebElement 	SearchforDep;
	public SupportTableDepartments SendKeystoSearchfor() throws InterruptedException {
		Thread.sleep(3000);
		sendkeys(SearchforDep,prop.getProperty("DepartmentsCode"));
		logger.info("Send Keys to Search successful");
		return this;
}
	@FindBy(id="DFtxtCode")
	private WebElement 	DFtxtCode;
	public SupportTableDepartments SendKeystocode() throws InterruptedException {
		Thread.sleep(3000);
		sendkeys(DFtxtCode,prop.getProperty("DepartmentsCode"));
		logger.info(" Enter Department code  successful");
		return this;
}
	
	@FindBy(id="CSTDeptradCode")
	private WebElement 	code;
	public SupportTableDepartments selectcode() throws InterruptedException {
		Thread.sleep(3000);
		click(code);
		logger.info("Clickon selectcode successful");
		return this;
	}
	@FindBy(xpath ="//button[@onclick='onDepatmentSearchPrivateOkClick()']")
	private WebElement 	ok;
	public SupportTableDepartments Searchcriteriaok() throws InterruptedException {
		Thread.sleep(3000);
		click(ok);
		logger.info("Clickon Searchcriteriaok successful");
		return this;
	}
	@FindBy(xpath ="//tr[@data-index='0']")
	private WebElement 	selectdep;
	public SupportTableDepartments selectdepartments() throws InterruptedException {
		Thread.sleep(3000);
		click(selectdep);
		logger.info("Clickon selectdepartments successful");
		return this;
	}
	
	@FindBy(id ="CSTDeptEdit")
	private WebElement 	editbutton;
	public SupportTableDepartments Clickoneditbutton() throws InterruptedException {
		Thread.sleep(3000);
		click(editbutton);
		logger.info("Clickoneditbutton successful");
		return this;
	}
	
	@FindBy(id ="CSTDeptAdd")
	private WebElement 	CSTDeptAdd;
	public SupportTableDepartments ClickonDeptAdd() throws InterruptedException {
		Thread.sleep(3000);
		click(CSTDeptAdd);
		logger.info("Click on DeptAdd successful");
		return this;
	}
	
	
	
	
	
	@FindBy(id ="DFtxtDescription")
	private WebElement 	DFtxtDescription;
	public SupportTableDepartments enterDescription() throws InterruptedException {
		Thread.sleep(3000);
		sendkeys(DFtxtDescription,prop.getProperty("Description"));
		logger.info("Send Keys to Description successful");
		return this;
	}
	
	@FindBy(id ="DFtxtDescription")
	private WebElement 	editDescription;
	public SupportTableDepartments editDescription() throws InterruptedException {
		
		Thread.sleep(3000);
		editDescription.clear();
		sendkeys(DFtxtDescription,"Test");
		logger.info("Edit to Description successful");
		return this;
	}
	@FindBy(xpath ="//button[@onclick='OkClickDepartmentForm()']")
	private WebElement 	OkClickDepartmentForm;
	public SupportTableDepartments okclickDepartmentForm() throws InterruptedException {
		Thread.sleep(3000);
		click(OkClickDepartmentForm);
		logger.info("okclickDepartmentForm successful");
		return this;
	}
	@FindBy(id ="CSTDeptDelete")
	private WebElement 	CSTDeptDelete;
	public SupportTableDepartments okclickCSTDeptDelete() throws InterruptedException {
		Thread.sleep(3000);
		click(CSTDeptDelete);
		logger.info("click on CSTDeptDelete successful");
		return this;
	}
	@FindBy(id ="btnConfirmBoxOk")
	private WebElement 	ConfirmBoxOk;
	public SupportTableDepartments ConfirmBoxOk() throws InterruptedException {
		Thread.sleep(3000);
		click(ConfirmBoxOk);
		logger.info("ConfirmBoxOk successful");
		return this;
	}
	
}