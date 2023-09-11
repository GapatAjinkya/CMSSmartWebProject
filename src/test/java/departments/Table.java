package departments;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonSetter.Value;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Table {
	public static WebDriver driver;
	public static WebDriverWait wait;
	Logger logger = LogManager.getLogger("Table");

	@BeforeMethod
	public void setup() throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		logger.info("Browser opend");
		driver.manage().window().maximize();
		driver.get("http://cmsxiapp.cmsglobalsoft.com:2320/Smartweb/#");
		driver.findElement(By.id("menu_item_1")).click(); // To click on LocalConfig Menu
		driver.findElement(By.id("menu_item_15")).click(); // To click on Login Tab
		Thread.sleep(3000);
		WebElement Userlogin = driver.findElement(By.id("txtLPUserLogin")); // Userlogin
		Userlogin.sendKeys("nilesh");
		WebElement password = driver.findElement(By.id("txtLPPassword")); // password
		password.sendKeys("Nilesh@123");
		driver.findElement(By.id("chkRememberMe")).click(); // chkRememberMe

		WebElement ok = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='LoginFormOkClick()']")));
		ok.click();

		String expectedTitle = "CMS WorldLink Xi 23 (2.0) - XI 23.2.0- SQL - WLDB_XI2320DB";
		String actualTitle = driver.getTitle();
		assert actualTitle.equalsIgnoreCase(expectedTitle) : "Title didn't match";
		System.out.println("Title Matched");
		Thread.sleep(10000);
	}

	@AfterMethod
	public void teardown() throws InterruptedException {

//		Thread.sleep(10000);
//		driver.close();
	}

	@Test
	public void newDepartments() throws InterruptedException {
		
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
		
		WebElement Departments = driver.findElement(By.id("menu_item_452"));
		wait.until(ExpectedConditions.visibilityOf(Departments));
		wait.until(ExpectedConditions.elementToBeClickable(Departments));
		Departments.click();
		Thread.sleep(5000);
		logger.info("Click on Departments successful");
		
		WebElement okclick = driver.findElement(By.xpath("//button[@onclick='onDepatmentSearchPrivateOkClick()']"));
		wait.until(ExpectedConditions.visibilityOf(okclick));
		wait.until(ExpectedConditions.elementToBeClickable(okclick));
		okclick.click();
		logger.info("Click on ok successful");
        Thread.sleep(3000);
        
 /*       
     // Init table element (in this case by tag name)
        WebElement tableElement = driver.findElement(By.tagName("table"));

        // Init TR elements from table we found into list
        List<WebElement> trCollection = tableElement.findElements(By.tagName("tr"));

        // Define TD elements collection
        List<WebElement> tdCollection;

        // Loop every row in the table and find the desired row
        WebElement desiredRow = null;
        for (WebElement element : trCollection) {
            tdCollection = element.findElements(By.tagName("td"));

            // Check if the row matches the desired criteria
            String column1 = tdCollection.get(0).getText();
            if (column1.equals("Test-A")) {
                desiredRow = element;
                break;
            }
        }

        // Perform actions on the desired row if found
        if (desiredRow != null) {
            // Access the columns or perform actions on the row
        	
        	 List<WebElement> columns = desiredRow.findElements(By.tagName("td"));
             String position = columns.get(1).getText(); // Assuming position is in the second column (index 1)
             System.out.println("Employee Position: " + position);
             
      
        } else {
            System.out.println("Desired row not found");
        }   
        
 */       
      //table[@id='tblCSTDepartmentList']
        //Columns 
      //table[@id='tblCSTDepartmentList']//th
        //rows
        //table[@id='tblCSTDepartmentList']//tr
        //alldata
        //table[@id='tblCSTDepartmentList']//td
        
        //Columns 1,2
        //table[@id='tblCSTDepartmentList']//tr//td[1]    
       
         List<WebElement> allHeaders = driver.findElements(By.xpath("//table[@id='tblCSTDepartmentList']//th"));

         System.out.println(allHeaders.size());
         Assert.assertEquals(allHeaders.size(), 3, "column count is not the same");

         boolean status=false;
         for (WebElement ele : allHeaders) {
             String value = ele.getText();
             System.out.println(value);

             if (value.contains("Org/Site Groups")) {
                 status=true;
                 break;
             }
         }
         Assert.assertTrue(status);

   List<WebElement> rowsnum= driver.findElements(By.xpath("//table[@id='tblCSTDepartmentList']//tr"));
   System.out.println("Number of rows -"+rowsnum.size());
  // Assert.assertEquals(rowsnum.size(),10 , "column count is not the same");
        
   List<WebElement> alldata= driver.findElements(By.xpath("//table[@id='tblCSTDepartmentList']//td"));    
        
   boolean dataStatus=false;
   for (WebElement ele : alldata) {
       String value = ele.getText();
       System.out.println(value);

       if (value.contains("Department1")) {
    	   dataStatus=true;
           break;
       }
   }
   
   Assert.assertTrue(dataStatus,"Record not found ");
   
   if (dataStatus) {
	    WebElement department1Element = driver.findElement(By.xpath("//table[@id='tblCSTDepartmentList']//td[contains(text(), 'Department1')]"));
	    department1Element.click();
	} else {
	    // Handle the case when "Department1" element is not found
	}


/*
   List<WebElement> Discolumns= driver.findElements(By.xpath("//table[@id='tblCSTDepartmentList']//tr"));
   System.out.println("Number of rows"+Discolumns.size());
  // Assert.assertEquals(rowsnum.size(),13 , "column count is not the same");
        
      
        
   boolean DiscolumnsStatus=false;
   for (WebElement ele : Discolumns) {
       String value = ele.getText();
       System.out.println(value);

       if (value.contains("Test Department")) {
    	   DiscolumnsStatus=true;
           break;
       }
   }
   Assert.assertTrue(DiscolumnsStatus," Dis Record not found ");
   
        //this for to identify the element 
        driver.findElement(By.xpath("")).click();
        
        for(int i=0;i<allHeaders.size();i++) 
        {
        	WebElement element=allHeaders.get(i);
        	String value=element.getText();
        if(value.contains("ag"))
        {
        	status=true;
        }
        }*/
}}
