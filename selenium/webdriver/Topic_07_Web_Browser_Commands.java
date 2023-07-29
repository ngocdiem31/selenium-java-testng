package webdriver;



import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Browser_Commands {
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
	    driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Browser() {
		// các commands = hàm để tương tác với các Browser thì phải thông qua biến driver
		// Mở ra 1 page/Link url nào đó
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
		driver.get("http://live.techpanda.org/index.php/");
		
			
		// Đang đứng tại page nào lấy của page đó
		driver.getCurrentUrl();
		// Cách 1: Verify trực tiếp 
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		// Cách 2 _ khai báo biến (để hứng/ gán dữ liệu) sau đó dùng hàm assert để verify lại
		String homeUrl = driver.getCurrentUrl();
		Assert.assertEquals(homeUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		//Hàm .getPageSource: lấy ra code HTML/ Css/ Js của page hiện tại
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Please enter the following information to create your account."));
		
		// Hàm .getTitlle: lấy title của page hiện tại
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "Mobile");
		
		// Xử lý Windown/ Tab
		// Lấy ra Id của Tab/ Windown hiện tại
		// Hàm .getWindownHandle();
		// Lấy ra tất cả Id của các Tab/ Windown hiện tại
	    // Hàm .getWindownHanldes();
		
		// Hàm .manage
		driver.manage().
		// Xử lý - lấy cookie
		driver.manage().addCookie(Cookie);
		driver.manage().getCookies();
		
		driver.navigate().refresh();
		// kết hợp getCookie và Navigation-Refresh để lấy cookie và log in thành công
		// hàm .log : lấy ra log của trình duyệt
		driver.manage().logs().get(LogType.BROWSER);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		// Lấy time chờ cho page đc load xong
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
		// Để chờ cho 1 đoan script được thực thi xong trong vòng bao lâu
		// JavascriptExecutor
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		// hàm manage.Window
		driver.manage().window().fullscreen();
		
		driver.manage().window().maximize();   => * 
		// Test GUI - giao diện
		// getPosition: bên ngoài - lấy vị trí của Brower so với độ phân giải của màn hình
		
		Point point = driver.manage().window().getPosition();
		point.getX();
		// setPosition: set tọa độ cho browser tại 1 vị trí nào đó
		driver.manage().window().setPosition(new Point(1920,1080));
		// Size: bên trong -> kích thước chiều rộng hoặc cao của browser - ko lien quan đến độ phân giải màn hình 
		// để test responsive - phóng to thu nhỏ màn hình
		driver.manage().window().getSize();
		
		Dimension dimension = driver.manage().window().getSize();
		dimension.getHeight();
		dimension.getWidth();
		// Set chiều rông/cao cho Browser
		driver.manage().window().setSize(new Dimension(1366,768));
		
		// Hàm Navigate - Điều hướng
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("http://live.techpanda.org/"); // tracking history tốt hơn
		driver.navigate().to(new URL("http://live.techpanda.org/"));
		// the same with: driver.get("http://live.techpanda.org/"); - ko tracking history
		
		// Hàm switchTo
		// Windown/ tab
		// Frame/ Iframe
		// Alert
		driver.switchTo().alert();   => * 
		driver.switchTo().frame(1);    => * 
		driver.switchTo().window("");   => * 
		
		
		
	}

    
	@Test
	public void TC_02_Element() {

	}
	
	@Test
	public void TC_03_Tips() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

