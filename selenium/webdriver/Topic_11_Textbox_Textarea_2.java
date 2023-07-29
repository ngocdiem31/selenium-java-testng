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

public class Topic_11_Textbox_Textarea_2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	Random rand = new Random();
	
    
    String emailAddress = "autotest" + rand.nextInt(99999) + "@gmail.com";
    String firstName = "JOnh";
    String lastName = "Wick";
    String fullName = firstName + " " + lastName;
    String password = "098765";

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
	public void TC_01_TechPanda_Register() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		// input valid value
	driver.findElement(By.id("firstname")).sendKeys(firstName);
	driver.findElement(By.id("lastname")).sendKeys(lastName);
	driver.findElement(By.id("email_address")).sendKeys(emailAddress);
	driver.findElement(By.id("password")).sendKeys(password);
	driver.findElement(By.id("confirmation")).sendKeys(password);
	
	driver.findElement(By.xpath("//button[@title='Register']")).click();
	
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
	
	String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div//following-sibling::div/p")).getText();
	System.out.println(contactInfo);
	// khi text nhiều và có xuống dòng thì ko verify tuyệt đối (2 cái bằng nhau) mà chỉ verify tương đối - check contains
	Assert.assertTrue(contactInfo.contains(fullName));
	Assert.assertTrue(contactInfo.contains(emailAddress));
	
	driver.findElement(By.xpath("//a[text()='Account Information']")).click();
	Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"),firstName );
	//getAttribute("value") - vì giá trị cần lấy nằm trong thuộc tính 'value'
	Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"),lastName);
	Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"),emailAddress);
	
	driver.findElement(By.xpath("//a[@class='skip-link skip-account']/span[@class='label']")).click();
	driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	sleepInSecond(5);
	
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//img")).isDisplayed());
	
	// .findElement(): cũng là 1 cách wait rất hay
	// bị ảnh hưởng bởi implicitlyWait là 30s
	// Giây đầu tiên ko tìm thấy sẽ tiếp tục chờ và tìm lại 
	// Selenium mặc định: cứ 0,5s tìm lại 1 lần => 1s tìm 2 lần, 5s đầu tiền sẽ tìm 10 lần
	// giấy thứ 5,5 thì element là image xuất hiện -> tìm tháy -> trả về True (isDisplayed)	
	// AssertTrue(true) => passed this step	
	
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