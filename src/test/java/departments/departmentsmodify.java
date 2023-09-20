package departments;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class departmentsmodify {

	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("departmentsmodify");

	@BeforeClass
	public void setup() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"E:\\Ajinkyaworkspace\\CMSSmartWebProject\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		// options.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver(options);
		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://localhost:8090/SmartWeb/#");
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
	}

	@AfterClass
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}

	@Test(priority = 0)
	public void editTest() throws InterruptedException {
		edit("Department5");
	}

	@Test(priority = 2)
	public void testDelete() throws InterruptedException {
		delete("TestAG1"); // newcode
	}

	public void edit(String Code) throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);
		logger.info("Click on Configuration successful");

		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		wait.until(ExpectedConditions.visibilityOf(SupportTables));
		wait.until(ExpectedConditions.elementToBeClickable(SupportTables));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");
		Thread.sleep(3000);

		WebElement Departments = driver.findElement(By.id("menu_item_452"));
		wait.until(ExpectedConditions.visibilityOf(Departments));
		wait.until(ExpectedConditions.elementToBeClickable(Departments));
		Departments.click();
		Thread.sleep(8000);
		WebElement Search = driver.findElement(By.xpath("//input[@id='txtCSTDeptSearch']"));
		// departmentTest2
		Search.sendKeys(Code);

		driver.findElement(By.xpath("//button[@onclick='onDepatmentSearchPrivateOkClick()']")).click();// ok buttton
		Thread.sleep(3000);

		WebElement selectproduct = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@data-index='0']")));
		wait.until(ExpectedConditions.visibilityOf(selectproduct));
		selectproduct.click();

		logger.info(" departments select  successful");

		WebElement editbutton = driver.findElement(By.id("CSTDeptEdit"));
		editbutton.click();
		Thread.sleep(5000);
		logger.info(" Click on edit Button  successful");

		String editwith = "code";
		String newcode = "TestAG1"; // To edit the product new code ----------------------------------
		String Description = "departments Test";

		if (editwith.equalsIgnoreCase("code")) {
			Thread.sleep(5000);
			WebElement codesend = driver.findElement(By.xpath("//input[@id='DFtxtCode']"));
			wait.until(ExpectedConditions.visibilityOf(codesend));
			wait.until(ExpectedConditions.elementToBeClickable(codesend));
			codesend.clear();
			Thread.sleep(4000);
			codesend.sendKeys(newcode);
			logger.info("Code edit successful");
		} else if (editwith.equalsIgnoreCase("Description")) {
			Thread.sleep(5000);
			WebElement pDescription = driver.findElement(By.id("DFtxtDescription"));
			pDescription.clear();
			Thread.sleep(4000);
			pDescription.sendKeys(Description);
			logger.info("Description edit successful");
		}

		WebElement clickok = driver.findElement(By.xpath("//button[@onclick='OkClickDepartmentForm()']"));
		wait.until(ExpectedConditions.visibilityOf(clickok));
		wait.until(ExpectedConditions.elementToBeClickable(clickok));
		clickok.click();
		logger.info(" Click on ok successful");
		Thread.sleep(10000);

		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblCSTDepartmentList']//td"));

		if (editwith.equalsIgnoreCase("code")) {
			boolean dataStatus = false;
			for (WebElement ele : alldata) {
				String value = ele.getText();
				if (value.equals("TestAG1")) {
					System.out.println(value);
					dataStatus = true;
					break;
				}
			}
			Assert.assertTrue(dataStatus, "Department is not found!");
			logger.info("Department Found in table  successful");

		} else if (editwith.equalsIgnoreCase("Description")) {
			boolean dataStatus = false;
			for (WebElement ele : alldata) {
				String value = ele.getText();
				if (value.equals(Description)) {
					System.out.println(value);
					dataStatus = true;
					break;
				}
			}
			Assert.assertTrue(dataStatus, "Department Description is not found!");
			logger.info("Department Description Found in table  successful");
		}
	}

	public void delete(String newcode) throws InterruptedException {

		WebElement Configuration = driver.findElement(By.id("menu_item_4"));
		wait.until(ExpectedConditions.visibilityOf(Configuration));
		wait.until(ExpectedConditions.elementToBeClickable(Configuration));
		Configuration.click();
		Thread.sleep(5000);

		logger.info("Click on Configuration successful");

		WebElement SupportTables = driver.findElement(By.cssSelector("#menu_item_45"));
		wait.until(ExpectedConditions.visibilityOf(SupportTables));
		wait.until(ExpectedConditions.elementToBeClickable(SupportTables));
		SupportTables.click();
		logger.info(" SupportTables Windo Open  successful");
		Thread.sleep(5000);

		WebElement Departments = driver.findElement(By.id("menu_item_452"));
		wait.until(ExpectedConditions.visibilityOf(Departments));
		wait.until(ExpectedConditions.elementToBeClickable(Departments));
		Departments.click();
		Thread.sleep(8000);

		WebElement Search = driver.findElement(By.xpath("//input[@id='txtCSTDeptSearch']"));
		// To delete this department
		Search.sendKeys(newcode);

		driver.findElement(By.xpath("//button[@onclick='onDepatmentSearchPrivateOkClick()']")).click();// ok buttton
		Thread.sleep(8000);

		WebElement selectproduct = driver
				.findElement(By.xpath("//table[@id='tblCSTCustomerList']//td[contains(text(), '" + newcode + "')]"));
		wait.until(ExpectedConditions.visibilityOf(selectproduct));
		wait.until(ExpectedConditions.elementToBeClickable(selectproduct));
		selectproduct.click();
		logger.info(" departments select  successful");
		Thread.sleep(5000);

		WebElement delete = driver.findElement(By.id("CSTDeptDelete"));
		delete.click();

		Thread.sleep(5000);
		driver.findElement(By.id("btnConfirmBoxOk")).click();
		Thread.sleep(5000);

		List<WebElement> alldata = driver.findElements(By.xpath("//table[@id='tblCSTDepartmentList']//td"));

		boolean dataStatus = false;
		for (WebElement ele : alldata) {
			String value = ele.getText();
			System.out.println(value + "1");
			if (value.equals("No matching records found")) {
				System.out.println(value);
				dataStatus = true;
				break;
			}
		}
		Assert.assertTrue(dataStatus, "Department is not Deleted");
		logger.info("Delete The Department is successful ");
	}
}