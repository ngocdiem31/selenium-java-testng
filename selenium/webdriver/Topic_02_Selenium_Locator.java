package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows 10")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");	
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Mở trang Register ra
		driver.get("https://demo.nopcommerce.com/register");
	
	}
		// HTML của FirstName textbox
		// <input type="text" data-val="true" data-val-required="First name is required." 
		// id="FirstName" name="FirstName">
	
		//
	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
	}

	@Test
	public void TC_02_Class() {
		//Mở màn hình Search
		driver.get("https://demo.nopcommerce.com/search");
		// Nhập text vào Search textbox
		driver.findElement(By.className("search-text")).sendKeys("MacbookPro");
	}

	@Test
	public void TC_03_Name() {
	// Click vào Advance Search check box
		driver.findElement(By.name("advs")).click();
		
	}
	@Test
	public void TC_04_TagName() {
	// Tìm ra bao nhiêu thẻ input trên màn hình hiện tại
		System.out.println(driver.findElements(By.tagName("input")).size());
		
	}
	@Test
	public void TC_05_LinkText() {
	// Click vào đường link Addresses (bản truyển vào đoan text tuyệt đối-chứa toàn bộ)
		driver.findElement(By.linkText("Addresses")).click();
	
	}
	@Test
	public void TC_06_PartialLinkText() {
		// partialLinkText: chứa 1 phần nội dung đoạn text Apply for vendor account
				driver.findElement(By.partialLinkText("vendor account")).click();
	}@Test
	public void TC_07_Css() {
	// Chứa nhiều kỹ thuật nhất
		// Mở lại trang Register
		driver.get("https://demo.nopcommerce.com/register");
		// cách 1:
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		// Cách 2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Operator");
		// Cách 3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("Ngocdiem@gmail.com");
	}
	@Test
	public void TC_08_Xpath() {
	// Chứa nhiều kỹ thuật nhất
		// Mở lại trang Register
		driver.get("https://demo.nopcommerce.com/register");
		// cách 1:
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");
		// Cách 2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Operator");
		// Cách 3
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("Ngocdiem@gmail.com");
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}  //update