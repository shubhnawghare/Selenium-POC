package com.nt.servlet;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtendReportTestNG {
//creating driver object
	public static WebDriver driver;
//create the htmlReporter object
	ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void setup() {
		htmlReporter = new ExtentSparkReporter("extentReport_System.html");
		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// initializing and starting the browser
		//WebDriverManager.chromedriver().setup();
		// driver = new ChromeDriver();
	}

	@BeforeMethod
	public void setup1() {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium_New\\Browser\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/aeui/#/login?return=%2F");
		driver.findElement(By.id("uname")).sendKeys("sysadmin");
		driver.findElement(By.id("pswd")).sendKeys("Shubh@123");
		driver.findElement(By.id("signin")).click();
	}

	@Test(priority = 1)
	public void VerifyaddNewTenants() throws InterruptedException {
		test = extent.createTest("To verrfyaddNewTenants");
		Thread.sleep(3000);
		driver.findElement(By.name("add-tenant")).click();
		driver.findElement(By.id("tenantName")).sendKeys("Shubh123");
		Thread.sleep(1000);
		driver.findElement(By.id("description")).sendKeys("TDescription");
		driver.findElement(By.id("orgCode")).sendKeys("Ow12808");
		driver.findElement(By.name("submit")).click();
		String actual = "Tenant created successfully";
		WebElement expected = driver.findElement(By.xpath("//p[text()='Tenant created successfully']"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);

	}

	@Test(priority = 2)
	public void VerifyDialogboxForAddNewTenant() throws InterruptedException {
		test = extent.createTest("VerifyDialogboxForAddNewTenant");
		Thread.sleep(3000);
		WebElement AddTenants = driver.findElement(By.name("add-tenant"));
		AddTenants.click();
		String actual = "Add New Tenant";
		WebElement expected = driver.findElement(By.xpath("//h5[normalize-space()='Add New Tenant']"));
		Thread.sleep(3000);
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);

	}

	@Test(priority = 3)
	public void VerifyMandatoryFieldForAddNewTenant() throws InterruptedException {
		test = extent.createTest("VerifyMandatoryFieldForAddNewTenant");
		Thread.sleep(3000);
		WebElement AddTenants = driver.findElement(By.name("add-tenant"));
		AddTenants.click();
		Thread.sleep(2000);
		driver.findElement(By.name("submit")).click();
		String actual = "Tenant Name is mandatory";
		WebElement expected = driver.findElement(By.xpath("//div[contains(text(),'Tenant Name is mandatory')]"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);

	}

	@Test(priority = 4)
	public void VerifycreateSystemUser() throws InterruptedException {
		test = extent.createTest("To verify createSystemUser"); // Thread.sleep(5000); 
		//driver.findElement(By.xpath("//div[@class='responsive-toggle-Sidebar ml-1']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@class='collapsed'])[1]")).click();
		driver.findElement(By.xpath("//li[@class='active']")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("add-new")).click();
		WebElement Dropdown1 = driver.findElement(By.xpath("//div[@class='mul-dropdown-button']"));
		Dropdown1.click();
		driver.findElement(By.xpath("//span[@class='mul-checkmark']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("fname")).sendKeys("Shubh112808");
		driver.findElement(By.id("lname")).sendKeys("Naw123566");
		driver.findElement(By.id("useremail")).sendKeys("Shubhn2808@gmail.com");
		driver.findElement(By.id("username")).sendKeys("Shubhn2808");
		driver.findElement(By.id("pswd")).sendKeys("Shubh@145612808");
		driver.findElement(By.id("confirmPswd")).sendKeys("Shubh@145612808");
		WebElement Dropdown2 = driver.findElement(By.id("role"));
		Dropdown2.click();
		Select s = new Select(Dropdown2);
		s.selectByIndex(1);
		Thread.sleep(3000);
		driver.findElement(By.name("submit")).click();
		String actual = "User created successfully";
		WebElement expected = driver.findElement(By.xpath("//p[text()='User created successfully']"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);

	}

	@Test(priority = 5)
	public void VerifyMandatoryFieldForcreateSystemUser() throws InterruptedException {
		test = extent.createTest("VerifyMandatoryFieldForAddNewTenant");
		Thread.sleep(3000);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//a[@class='collapsed'])[1]")).click();
		driver.findElement(By.xpath("//li[@class='active']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("add-new")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("submit")).click();
		String actual = "Role is mandatory";
		WebElement expected = driver.findElement(By.xpath("//div[contains(text(),'Role is mandatory')]"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);

	}

	@Test(priority = 6)
	public void VerifyMismatchPasswordValidationForcreateSystemUser() throws InterruptedException {
		test = extent.createTest("VerifyMismatchPasswordValidationForcreateSystemUser");
		Thread.sleep(3000);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//a[@class='collapsed'])[1]")).click();
		driver.findElement(By.xpath("//li[@class='active']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("add-new")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("pswd")).sendKeys("Shubh@123456140");
		driver.findElement(By.id("confirmPswd")).sendKeys("Shubh@1234561405");
		driver.findElement(By.name("submit")).click();
		Thread.sleep(2000);

		String actual = "Passwords Mismatch!";
		WebElement expected = driver.findElement(By.xpath("//div[contains(text(),'Passwords Mismatch!')]"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);

	}

	@Test(priority = 7)
	public void VerifyEditSystemUser() throws InterruptedException {

		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//div[@class='responsive-toggle-Sidebar
		// ml-1']")).click(); test =
		extent.createTest("VerifyEditSystemUser");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[@class='collapsed'])[1]")).click();
		driver.findElement(By.xpath("//li[@class='active']")).click();
		driver.findElement(By.xpath("//span[@class='fa fa-edit hand-cursor']")).click();
		WebElement emailField = driver.findElement(By.id("useremail"));
		Actions act = new Actions(driver);
		act.click(emailField).perform();
		for (int i = 1; i <= 30; i++) {
			act.sendKeys(Keys.BACK_SPACE).perform();
			Thread.sleep(100);
		}
		Thread.sleep(2000);
		emailField.sendKeys("newemail1@example.com");

		driver.findElement(By.id("pswd")).sendKeys("Shubh@321");
		driver.findElement(By.id("confirmPswd")).sendKeys("Shubh@321");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//button[@name='save'])[2]")).click();
		String actual = "User updated successfully";
		WebElement expected = driver.findElement(By.xpath("//p[text()='User updated successfully']"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);

	}

	@Test(priority = 8)
	public void VerifyAboutPage() throws InterruptedException {
		test = extent.createTest("VerifyAboutPage");
//Thread.sleep(4000);
//driver.findElement(By.xpath("//div[@class='responsive-toggle-Sidebar ml-1']")).click();

		driver.findElement(By.id("login-username")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("about")).click();
		Thread.sleep(2000);
		String actual = "About";
		WebElement expected = driver.findElement(By.xpath("//h5[normalize-space()='About']"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);

	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getTestName());
		} else {
			test.log(Status.SKIP, result.getTestName());
		}

		driver.quit();
		test.pass("closed the browser");
	}

	@AfterSuite
	public void tearDown() {

		test.info("test completed");
       //write results into the file
		extent.flush();
	}
}