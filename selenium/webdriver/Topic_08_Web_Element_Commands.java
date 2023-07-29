package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Web_Element_Commands {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	
	}

	@Test
	public void TC_01_WebElement() {
		// Element: textbox/ textarea/ dropdown/ checkbox/ radio/ link/ text ...
		// Chỉ tương tác lên Element này 1 lần (ko cần phải khai báo biến)
        driver.findElement(By.id("send2")).click();
        
        // Element: dùng lại nhiều lần chỉ trong trang hiện tại ( khai báo biến)
        // Không dùng 1 biến cho 2 trang cùng 1 lúc vì khi nhảy từ trang này qua trang kia, trạng thái của element đã đc cập nhật lại
        WebElement loginButton = driver.findElement(By.id("send2"));
        // Login page
        loginButton.click();
        //...
        // Register page
        loginButton.click();
        
        // Biến loginButton có thể dùng đi dùng lại nhiều lần
        loginButton.isDisplayed();
        loginButton.getCssValue("");
        loginButton.click();
        
        // khai báo biến dùng cho nhiều page
        By loginButtonBy = By.id("send2");
        // By đi tìm element chỉ là biến dạng chuỗi, lưu giữu lại id.. nào đó 
        
        // Hàm .clear: xóa dữ liệu trong 1 textbox/ textarea/ drop-down (editable)
        driver.findElement(By.id("")).clear();
        
        // Hàm .sendKey: nhập dữ liệu vào 1 textbox/ textarea/ drop-down (editable)
        driver.findElement(By.id("")).sendKeys("ngocdiem@gmail.com");
        
       // Hàm .findElments: tìm kiếm nhiều element => trả về list element
        List<WebElement> textboxes = driver.findElements(By.xpath("//input[@type='text']"));
        
       // Hàm .findElement:  tìm kiếm element
        driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()='My Account']"));
        driver.findElement(By.xpath("//div[@class=\"footer\"]")).findElement(By.xpath("//a[text()='My Account']"));
        
        // lấy ra nội dung thuộc tính
        driver.findElement(By.id("search")).getAttribute("value");
        
        //
        driver.findElement(By.id("search")).getCssValue("thuộc tính");
        driver.findElement(By.id("search")).getCssValue("background-color");
        driver.findElement(By.id("search")).getCssValue("font-size");
        driver.findElement(By.id("search")).getCssValue("font-family");
        // lấy ra kích thước của element
        Dimension loginButtonSize = driver.findElement(By.id("search")).getSize();
        loginButtonSize.getHeight();
        
        // findElement.getLocation(“”): lấy ra tọa độ bên ngoài của 1 element so với độ phân giải màn hình
        Point loginButtonLocator = driver.findElement(By.id("search")).getLocation();
        loginButtonLocator.getX();
        
        // lấy ra cả tọa độ và kích thước của element
        Rectangle loginButtonRect = driver.findElement(By.id("search")).getRect();
        loginButtonSize = loginButtonRect.getDimension();
        loginButtonLocator = loginButtonRect.getPoint();
        
        // chụp màn hình với nhiều định dạng
        // Report HTML + Take Screenshot //*
        File screenshotFile = driver.findElement(By.id("search")).getScreenshotAs(OutputType.FILE);
        String screenshotBased64 = driver.findElement(By.id("search")).getScreenshotAs(OutputType.BASE64);
        
        // findElement.getTagname
        driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()='My Account']")).getTagName();
        // => a- tagname
        // Lấy ra tên thẻ khi dùng các loại Locator mà ko biết trước tên thẻ là gì
        String searchTextboxTagname = driver.findElement(By.cssSelector("#search")).getTagName();
        // -> input 
        // Đầu ra của element trên là đầu ra của element dưới
        driver.findElement(By.xpath("//" + searchTextboxTagname + "[@id='email']"));
        
        // .getText: lấy text của 1 elelemt hoặc lấy ra text của element và text của các node con bên trong nó
        driver.findElement(By.cssSelector("search")).getText();
        String benefitText = driver.findElement(By.cssSelector("ul.benefits")).getText();
        System.out.println(benefitText);
        
        // 
        driver.findElement(By.id("")).isDisplayed();
        
        driver.findElement(By.id("")).isEnabled();
        // Chỉ áp dụng cho 3 loại elements: checkbox/ radio button/ dropdown (dùng thư viện Select)
        // Kiểm tra xem 1 element đã được chọn hay chưa
        driver.findElement(By.id("")).isSelected();
        // chỉ gởi dữ liệu trong form lên server
        driver.findElement(By.id("submit")).submit();
        
        
	}
        
        
        

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	Dùng contains(.,’’) ko dùng contains(.=’’)
}  //update