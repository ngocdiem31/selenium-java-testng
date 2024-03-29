package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_016_BT2 {
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
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	@Test
    public void TC_01_Empty_Data() {
    driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
    // Verify
    
    Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
    Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
    Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
    Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
    Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
    Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại. ");
    }
	
	@Test
	public void TC_02_Invalid_Email1() {
    driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    //Action
    driver.findElement(By.id("txtFirstname")).sendKeys("Alex Homer");
    driver.findElement(By.id("txtEmail")).sendKeys("alex.com");
    driver.findElement(By.id("txtCEmail")).sendKeys("alex@homer.com");
    driver.findElement(By.id("txtPassword")).sendKeys("1234566");
    driver.findElement(By.id("txtCPassword")).sendKeys("1234566");
    driver.findElement(By.id("txtPhone")).sendKeys("0932473482");
    driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
    //Verify
    Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
    Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
		
	}
	@Test
	public void TC_03_Incorect_ReinputEmail() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	//Action
	driver.findElement(By.id("txtFirstname")).sendKeys("Alex Homer");
	driver.findElement(By.id("txtEmail")).sendKeys("alex@homer.com");
	driver.findElement(By.id("txtCEmail")).sendKeys("alexuhss");
	driver.findElement(By.id("txtPassword")).sendKeys("1234566");
	driver.findElement(By.id("txtCPassword")).sendKeys("1234566");
	driver.findElement(By.id("txtPhone")).sendKeys("0932473482");
	driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
	//Verify
	Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
		
	}
	@Test
	public void TC_04_Invalid_Password() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	//Action:
	driver.findElement(By.id("txtFirstname")).sendKeys("Alex Homer");
	driver.findElement(By.id("txtEmail")).sendKeys("alex@homer.com");
	driver.findElement(By.id("txtCEmail")).sendKeys("alex@homer.com");
	driver.findElement(By.id("txtPassword")).sendKeys("1234");
	driver.findElement(By.id("txtCPassword")).sendKeys("1234");
	driver.findElement(By.id("txtPhone")).sendKeys("0932473482");
	driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
	//Verify
	Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
	Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void TC_05_Incorrect_ReinputPassword() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	//Action
	driver.findElement(By.id("txtFirstname")).sendKeys("Alex Homer");
	driver.findElement(By.id("txtEmail")).sendKeys("alex@homer.com");
	driver.findElement(By.id("txtCEmail")).sendKeys("alex@homer.com");
	driver.findElement(By.id("txtPassword")).sendKeys("1234567");
	driver.findElement(By.id("txtCPassword")).sendKeys("1234");
	driver.findElement(By.id("txtPhone")).sendKeys("0932473482");
	driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
	//Verify
	Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
	}
	@Test
	public void TC_06_Invalid_PhoneNumber() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Action 1
		driver.findElement(By.id("txtFirstname")).sendKeys("Alex Homer");
		driver.findElement(By.id("txtEmail")).sendKeys("alex@homer.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("alex@homer.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("093");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số. ");
		//Action 2
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("09868667893420");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		//Verify2
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
		//Action 3
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("5463");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[text()='ĐĂNG KÝ']")).click();
		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	}
	
	