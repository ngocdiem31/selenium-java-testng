package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Textbox_Textarea_Excexices {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
    
	String firstName, lastName, employeeID, username, password;
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
	
		firstName = "Nam";
		lastName = "Hoai"; 
		username = "HoaiNam" + rand.nextInt(9999);
		password = "Jan123!@#";
		passportID ="578548994"; 
		issuedDate ="2021-05-05"; 
		expiryDate ="2031-05-04"; 
		comment ="Employer Passport\nIdentification Number";
	}

	@Test
	public void TC_01_OrangeHRM() {
		// login with role HR/ Manager
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		// clicks on [PIM] section
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		// clicks on [Add employee] button
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		// sendkey
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		// gán dữ liệu: trong quá trình chạy, lấy dữ liệu ra và gán vào 
		employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("_value");
		sleepInSecond(5);
		// click on [Create Login Details] switch button 
		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div/label/span")).click();
		sleepInSecond(3);
		// input data to fields: username, password, confirm password
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(10);
		// Verify is displayed data:firstname, lastname, employeeID
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("_value"), employeeID);
		// clicks on [Immigration] section
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(4);
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		sleepInSecond(3);
		// Điền thông tin: PassportID, issuedDate, ExpiryDate, Comment
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportID);
		driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).sendKeys(issuedDate);
		driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).sendKeys(expiryDate);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(5);
		// verify lại dữ liệu đã điền
		// Element has text: '578548994' = passportID
		// Muốn đưa biến "passportID" vào thì phải tách chuỗi(giữa dấu ' ') ra sau đó + biến passportID vào giữa => ko bị lỗi khi chạy dữu liệu
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + passportID + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + issuedDate + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + expiryDate + "']")).isDisplayed());
		// Clicks on icon [Pencil]
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		// verify trước khi edit
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportID);	
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).getAttribute("value"), issuedDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).getAttribute("value"), expiryDate);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		// log out
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);
		
		// Login with role Employee
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		sleepInSecond(5);
		// clicks on [My Info] section
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(3);
		// Verify is displayed data:firstname, lastname, employeeID
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("_value"), employeeID);
		// clicks on [Immigration] section
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(4);
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
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	//Dùng contains(.,’’) ko dùng contains(.=’’)
}  //update