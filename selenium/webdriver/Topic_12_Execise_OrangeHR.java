package webdriver;

import java.util.Random;

//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Execise_OrangeHR {
	WebDriver driver;
	Random rand = new Random();
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String firstName, lastName, employeeID, userName, password;
	String passportID, issuedDate, expiryDate, comment; 
	
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows 10")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		firstName = "Minh";
		lastName = "Lam";
		userName = "Minhlam" + rand.nextInt(9999);
		password = "MinhLam012@";
		passportID = "578548994";
		issuedDate = "2021-05-05";
		expiryDate = "2031-05-04";
		comment = "Employer Passport\nIdentification Number";
		
	} 

	@Test
	public void TC_02_OrangeHR() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(5);
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		// Lấy employeeID
		employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("_value");
		sleepInSecond(5);
		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.cssSelector("button.orangehrm-left-space")).click();
		sleepInSecond(10);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(4);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportID);
		driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).sendKeys(issuedDate);
		driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).sendKeys(expiryDate);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(3);
		// Verify data display on page
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + passportID + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + issuedDate + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + expiryDate + "']")).isDisplayed());
		// clicks on icon pencil (Edit)
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(5);
		// verify data before edit
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div//following-sibling::div/input")).getAttribute("value"), passportID);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).getAttribute("value"), issuedDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).getAttribute("value"), expiryDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		// log out
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(5);
		// log in with role employee
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		// Clicks on [My infor]
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(7);
		// Verify is displayed data:firstname, lastname, employeeID
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		// clicks on [Immigration] 
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		// verify data on page
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + passportID + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + issuedDate + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + expiryDate + "']")).isDisplayed());
		// clicks on pencil icon 
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(5);
		// verify data on page
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportID);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).getAttribute("value"), issuedDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).getAttribute("value"), expiryDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		
		
		
	
	}
	private Object ActionChains(WebDriver driver2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private WebElement assertTrue(WebElement findElement) {
		// TODO Auto-generated method stub
		return null;
	}

	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	// Dùng contains(.,’’) ko dùng contains(.=’’)
} // update