package com.nt.servlet;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TenantScripte {
	ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;
	static String UID;
	static String PWD;

	@BeforeSuite
	public void setup() throws IOException {
		htmlReporter = new ExtentSparkReporter("extentReport_Tenant.html");
		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		WebDriverManager.chromedriver().setup();

	}

	@BeforeClass
	public void Excelsetup() throws InterruptedException, EncryptedDocumentException, IOException {
		File Myfile = new File("C:\\Users\\shubham.nawghare\\Desktop\\CredentialData.xlsx");
		Sheet Mysheet = WorkbookFactory.create(Myfile).getSheet("Sheet1");
		InputStream inp = new FileInputStream("C:\\Users\\shubham.nawghare\\Desktop\\CredentialData.xlsx");
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		// Row firstRow = sheet.getRow(1);

		int cell = 0;
		int row = 1;
		for (int i = row; i <= 1; i++) {
			String UN = sheet.getRow(row).getCell(cell).getStringCellValue();
			System.out.println("User Name : " + UN);

			UID = UN;
			cell++;

			String PW = sheet.getRow(row).getCell(cell).getStringCellValue();
			System.out.println("Password : " + PW);

			PWD = PW;

		}

	}

	@BeforeMethod
	public void setup1() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium_New\\Browser\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
		driver.get("http://localhost:8080/aeui/#/login?return=%2F");
		driver.findElement(By.id("uname")).sendKeys("ShubhamN");
		driver.findElement(By.id("pswd")).sendKeys("Shubh@123");
		driver.findElement(By.id("signin")).click();
	}

	@Test(priority = 1)
	public void uploadUser() throws InterruptedException {
		test = extent.createTest("VerifyuploadUser");
		Thread.sleep(3000);
		WebElement user = driver.findElement(By.xpath("//i[@class='fa fa-fw fa-users']"));
		user.click();
		driver.findElement(By.name("dropdown-selector")).click();
		driver.findElement(By.xpath("//*[@id=\"main-component\"]/ng-component/div[1]/div/div[2]/ul/li[2]/a/span"))
				.click();
		driver.findElement(By.name("down-sample")).click();
		WebElement Dropdown = driver.findElement(By.id("userType"));
		Dropdown.click();
		Select s = new Select(Dropdown);
		s.selectByIndex(1);
		driver.findElement(By.id("csvFile"))
				.sendKeys("D:\\Selenium_New\\Browser\\chromedriver-win64\\chromedriver.exe");
		driver.findElement(By.name("submit")).click();
		String actual = "Result";
		WebElement expected = driver.findElement(By.xpath("//h4[text()='Result']"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);
	}

	@Test(priority = 2)
	public void VerifyUploadUserButtonStatus() throws InterruptedException {
		test = extent.createTest("VerifyUploadUserButtonStatus");
		Thread.sleep(5000);
		WebElement user = driver.findElement(By.xpath("//i[@class='fa fa-fw fa-users']"));
		user.click();
		driver.findElement(By.name("dropdown-selector")).click();
		driver.findElement(By.xpath("//*[@id=\"main-component\"]/ng-component/div[1]/div/div[2]/ul/li[2]/a/span"))
				.click();
		Thread.sleep(2000);
		Boolean Display = driver.findElement(By.name("submit")).isDisplayed();
		Assert.assertFalse(false, "upload user button is enabled");

	}

	@Test(priority = 3)
	public void assigningAgent() throws InterruptedException {
		test = extent.createTest("VerifyassignAgent");
		Thread.sleep(3000);
		// driver.findElement(By.xpath("//i[@id='sideBarArrow']")).click();
		driver.findElement(By.xpath("//span[text()='Agents']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Workflow Assignment']")).click();
		driver.findElement(By.id("edit")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("3_desktop")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("save")).click();
		driver.findElement(By.id("popup-button-ok")).click();
		Thread.sleep(3000);
		String actual = "Assignment saved successfully.";
		WebElement expected = driver.findElement(By.xpath("//p[text()='Assignment saved successfully.']"));
		Thread.sleep(2000);
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);
	}

	@Test(priority = 4)
	public void VerifyAgentName() throws InterruptedException {
		test = extent.createTest("VerifyAgentName");
		Thread.sleep(3000);
		// driver.findElement(By.xpath("//i[@id='sideBarArrow']")).click();
		driver.findElement(By.xpath("//span[text()='Agents']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Workflow Assignment']")).click();
		String actual = "shubham.nawghare@AELPT-1466";
		WebElement expected = driver.findElement(By.xpath("//font[@color='green']"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);
	}

	@Test(priority = 5)
	public void deletingTenantuser() throws InterruptedException {
		test = extent.createTest("Verifydeleteduser");
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//i[@id='sideBarArrow']")).click();
		WebElement AddTenants = driver.findElement(By.xpath("//i[@class='fa fa-fw fa-users']"));
		AddTenants.click();

		driver.findElement(By.id("delete-68")).click();
		driver.findElement(By.id("popup-button-ok")).click();

		String actual = "User deleted successfully";
		WebElement expected = driver.findElement(By.xpath("//p[text()='User deleted successfully']"));
		Thread.sleep(3000);
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);

	}

	@Test(priority = 6)
	public void VerifyDeletePopup() throws InterruptedException {
		test = extent.createTest("VerifyDeletePopup");

		// driver.findElement(By.xpath("//i[@id='sideBarArrow']")).click();
		WebElement AddTenants = driver.findElement(By.xpath("//i[@class='fa fa-fw fa-users']"));
		AddTenants.click();
		Thread.sleep(2000);
		driver.findElement(By.id("delete-64")).click();
		Thread.sleep(2000);
		String actual = "Are you sure you want to delete this user?";
		WebElement expected = driver.findElement(By.xpath("//div[@class='card-body']"));
		Thread.sleep(3000);
		System.out.println(expected.getText());

		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);

	}

	@Test(priority = 7)
	public void requestTab() throws InterruptedException {
		test = extent.createTest("Verifyrequesttab");
		Thread.sleep(4000);
		// driver.findElement(By.xpath("//i[@id='sideBarArrow']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Requests']")).click();
		String actual = "Complete";
		WebElement expected = driver.findElement(By.xpath("//span[@class='text-success']"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);
	}

	@Test(priority = 8)
	public void IncorrectChangePassword() throws InterruptedException {
		test = extent.createTest("VerifyIncorrectChangePassword");
		Thread.sleep(2000);
		driver.findElement(By.id("login-username")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("change-pswd")).click();
		driver.findElement(By.id("oldpswd")).sendKeys("Nawghare@123");
		driver.findElement(By.id("newpswd")).sendKeys("Shubh@123");
		driver.findElement(By.id("confirmpswd")).sendKeys("Shubh@123");
		Thread.sleep(5000);
		driver.findElement(By.name("save")).click();
		Thread.sleep(5000);
		String actual = "Incorrect old password";
		WebElement expected = driver.findElement(By.xpath("//p[text()='Incorrect old password']"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);
	}

	@Test(priority = 9)
	public void IncorrectChangePasswordWithSmallLetter() throws InterruptedException {
		test = extent.createTest("IncorrectChangePasswordWithSmallLetter()");
		Thread.sleep(2000);
		driver.findElement(By.id("login-username")).click();
		driver.findElement(By.id("change-pswd")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("oldpswd")).sendKeys("Shubh@123");
		driver.findElement(By.id("newpswd")).sendKeys("shubh@123");
		driver.findElement(By.id("confirmpswd")).sendKeys("shubh@123");
		String actual = "At least 1 uppercase letter(s)";
		Thread.sleep(2000);
		WebElement expected = driver.findElement(By.xpath("//li[text()=' uppercase letter(s)'][1]"));
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);
	}

	@Test(priority = 10)
	public void VerifyChangePasswordMismatch() throws InterruptedException {
		test = extent.createTest("VerifyChangePasswordMismatch");
		Thread.sleep(2000);
		driver.findElement(By.id("login-username")).click();
		driver.findElement(By.id("change-pswd")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("oldpswd")).sendKeys("Shubh@123");
		driver.findElement(By.id("newpswd")).sendKeys("Shubh@1234");
		driver.findElement(By.id("confirmpswd")).sendKeys("Shubh@12345");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//form[@class='ng-dirty ng-touched ng-invalid']")).click();

		String actual = "Passwords Mismatch!";

		Thread.sleep(2000);
		WebElement expected = driver.findElement(By.xpath("//div[contains(text(),'Passwords Mismatch!')]"));
		Thread.sleep(2000);
		System.out.println(expected.getText());
		String expectedResult = expected.getText();
		Assert.assertEquals(actual, expectedResult);
	}

	@Test(priority = 11)
	public void invalidCredentialsLoginPage() throws InterruptedException {
		test = extent.createTest("VerifyInvalidCredntials of login page");
		driver.findElement(By.id("login-username")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("sign-out")).click();
		Thread.sleep(2000);
		driver.get("http://localhost:8080/aeui/#/login?return=%2F");
		driver.findElement(By.id("uname")).sendKeys(UID);
		Thread.sleep(2000);
		driver.findElement(By.id("pswd")).sendKeys(PWD);
		Thread.sleep(2000);
		driver.findElement(By.id("signin")).click();
		String actual = "Invalid username or password";
		Thread.sleep(2000);
		WebElement expected = driver.findElement(By.xpath("//p[text()='Invalid username or password']"));
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

		// write results into the file
		extent.flush();
	}

}
