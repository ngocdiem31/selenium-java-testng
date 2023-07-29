package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 // //import org.openqa.selenium.support.Colors;
public class Topic_15_Button_Checkbox_Radio1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows 10")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");	
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		// Clicks to close pop up
		//driver.findElement(By.id("moe-dontallow_button")).click();
		
		// Clicks to move [Log in ] tab
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		sleepInSecond(3);
		
		// Define biến loginButton để verify
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		// Verify log in button is disable (ko thấy đc)
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		// Verify login button with background color = rbg(224,224,224) => dùng thuộc tính background-color để lấy ra Css value
		// System.out.println(driver.findElement(loginButton).getCssValue("background")); _ print để lấy ra mã màu
		
	Assert.assertTrue(driver.findElement(loginButton).getCssValue("background").contains("rgb(224, 224, 224"));
		// Enter email/password
		driver.findElement(By.id("login_username")).sendKeys("ngocdiem@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		sleepInSecond(3);
		
		// Verify log in button is enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		// Convert qua mã hexa vì firefox chạy mã rgba
		
		// Lấy ra mã màu của element ( chorme/edge: RGB but Firefox: RGBA)
		String loginButtonBackgroundColor = driver.findElement(loginButton).getCssValue("background");
		
		// Chuyển từ RGB/RGBA qua kiểu dữ liệu Color (1 kiểu class của selenium) - tìm trong Color selenium
		Color loginButtonColor = Color.fromString(loginButtonBackgroundColor);
	
		
		// Color có hàm để chuyển qua hexa
		String loginButtonHexa = loginButtonColor.asHex().toUpperCase();
		
		// Verify login button with background color = rgb(201, 33, 39) = #C92127
		// Mã hexa phải viết Hoa chữ cái đầu tiên
		Assert.assertEquals(loginButtonHexa, "#C92127");
		// Viết gọn code lại
//		Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background")).asHex().toUpperCase(), "#C92127");
		
		}



	

	@Test
	public void TC_02_() {
		
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