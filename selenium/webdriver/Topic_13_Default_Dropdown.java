package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Default_Dropdown {
	WebDriver driver;
	// chèn thư viện select - tạo biến new lên dùng 1 lần thì ko cần chèn thư viện
	Select select;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
    String firstName, lastName, email, companyName, password	;
    String date, month, year; 
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows 10")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");	
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		// khởi tạo driver
		driver = new FirefoxDriver();
		System.out.println(driver.toString());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	firstName = "Moonly";
	lastName = "Lee";
	email = firstName + lastName + rand.nextInt(9999) + "@gmail.com";
	companyName = "Mission impossiple";
	password = "1019Moon";
	date = "1";
	month = "May";
	year ="1985";
	}

	//@Test
	public void TC_01_Url() {
        driver.get("https://www.facebook.com/reg/");
        // khởi tạo và chỉ dùng nhều lần 
        //select = new Select(driver.findElement(By.cssSelector("select#day")));
        //select.selectByVisibleText("11");
        // nếu chỉ dùng duy nhất 1 lần thì dùng cách này
        new Select(driver.findElement(By.cssSelector("select#day"))).selectByVisibleText("11");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "11");
        
        // Chọn tháng
        new Select(driver.findElement(By.cssSelector("select#month"))).selectByVisibleText("Jun");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Jun");
        // Chọn năm
        new Select(driver.findElement(By.cssSelector("select#year"))).selectByVisibleText("2015");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "2015");
		 
	}
        
	@Test
	public void TC_02_BT04_dropdown_nopCommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("a.ico-register"));
	// Input user information
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
	// Select date of birth - ko truyen du lieu cung vao test case
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(date);
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);
		sleepInSecond(3);
		driver.findElement(By.id("Email")).sendKeys(email);	
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
	// Clicks Register button
		driver.findElement(By.id("register-button")).click();
		sleepInSecond(3);
	// Verify da vao trang hompage
	// Cach 1: Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText(), "Your registration completed");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.registration-result-page div.result")).getText(), "Your registration completed");
	// page be error, user have to log in by themself 
		driver.findElement(By.cssSelector("a.ico-login")).click();
		
	// Input info
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
	// Clicks on [My account] button
		driver.findElement(By.cssSelector("a.ico-account")).click();
	// verify data on page
		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);
		
	// tại thời điển này page đã được load lại và chuyển sang trang khác nên => phải khởi tạo lại biến select để verify
	    Assert.assertEquals(new Select(driver.findElement(By.cssSelector("[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), date);
	    Assert.assertEquals(new Select(driver.findElement(By.cssSelector("[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), month);
	    Assert.assertEquals(new Select(driver.findElement(By.cssSelector("[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), year);
	    Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);
	    Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);
	    
// Verify đủ 13 item trong drop-down Month
	    Assert.assertEquals(new Select(driver.findElement(By.cssSelector("[name='DateOfBirthMonth']"))).getOptions().size(), 13);
	    // nếu muốn in ra phải lưu thành 1 danh sách (getoptions: trả veef1 list) sau đó dùng vòng lặp
	    List<WebElement> months = new Select(driver.findElement(By.cssSelector("[name='DateOfBirthMonth']"))).getOptions();
	    
	    Assert.assertEquals(months.size(), 13);
	    for (WebElement month : months) {
	      System.out.println(month.getText());
	    }
		
	   // Verify đủ 32 item trong drop-down Day
	    Assert.assertEquals(new Select(driver.findElement(By.cssSelector("[name='DateOfBirthDay']"))).getOptions().size(), 32);
	    List<WebElement> days = new Select(driver.findElement(By.cssSelector("[name='DateOfBirthDay']"))).getOptions();
	    // sau khi get list thif phair verify lai
	    Assert.assertEquals(days.size(), 32);
	    for (WebElement day : days) {
			System.out.println(day.getText());
		}
		}


	private void sleepInSecond(long timeout) {
		 try {
			    Thread.sleep(timeout*1000);
			}   catch (InterruptedException e) {
				  e.printStackTrace();
			}
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	// Dùng contains(.,’’) ko dùng contains(.=’’)
}  //update