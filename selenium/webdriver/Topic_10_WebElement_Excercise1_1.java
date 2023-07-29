package webdriver;

//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_WebElement_Excercise1_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExcutor jsExecutor;
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows 10")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		jsExecutor = driver;
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	   // set size màn hình chạy test script
		//driver.manage().window().setSize(new Dimension(1366, 786));
	}

	@Test
	public void Login_1_1_get_Curent_URL() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

	}

	//@Test
	public void Login_1_2_Get_Title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	//@Test
	public void Login_1_3_Navigate_Function() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		sleepInSecond(2);
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		sleepInSecond(2);
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	//@Test
	public void Login_1_4_get_page_Source() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	    Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='                Login or Create an Account            ']")).getText(), "LOGIN OR CREATE AN ACCOUNT");
	   
	    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	    Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Create an Account']")).getText(), "CREATE AN ACCOUNT");
	}

	//@Test
	public void Login_2_1_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(By.id("mail")).isDisplayed()) {
			System.out.println("Email textbox is displayed");
			driver.findElement(By.id("mail")).sendKeys("diemdiem");
		} else {
            System.out.println("Email textbox is not displayed");
		}
		if (driver.findElement(By.id("under_18")).isDisplayed()) {
			System.out.println("Age radio button is displayed");
			driver.findElement(By.id("under_18")).click();
		} else {
            System.out.println("Age radio button is not displayed");
		}
		if (driver.findElement(By.id("edu")).isDisplayed()) {
			System.out.println("Education areabox is displayed");
			driver.findElement(By.id("edu")).sendKeys("University");
			
		} else {
           System.out.println("Education areabox is not displayed");
		}
		if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
			System.out.println("Name User 5 is Displayed");
		} else {
            System.out.println("Name User 5 is not Displayed");
		}
	}
	
	//@Test
	public void Login_2_2_Enable_Disable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (driver.findElement(By.id("mail")).isEnabled()) {
			System.out.println("Email textbox is enable");
		} else {
            System.out.println("Email textbox is disable");
		}
		if (driver.findElement(By.id("under_18")).isEnabled()) {
			System.out.println("Age radio button is enable");
		} else {
            System.out.println("Age radio button is disable");
		}
		if (driver.findElement(By.id("edu")).isEnabled()) {
			System.out.println("Education textarea is enable");
		} else {
            System.out.println("Education textarea is disable");
		}
		if (driver.findElement(By.id("job1")).isEnabled()) {
			System.out.println("Job Role 01 single dropdown is enable");
		} else {
            System.out.println("Job Role 01 single dropdown is disable");
		}
		if (driver.findElement(By.id("job2")).isEnabled()) {
			System.out.println("Job Role 02 multiple dropdown is enable");
		} else {
            System.out.println("Job Role 02 muliple dropdown is disable");
		}
		if (driver.findElement(By.id("development")).isEnabled()) {
			System.out.println("Interests checkbox is enable");
		} else {
            System.out.println("Interests checkbox is disable");
		}
		if (driver.findElement(By.id("slider-1")).isEnabled()) {
			System.out.println("slider-1 is enable");
		} else {
            System.out.println("slider-1 is disable");
		}
		
		if (driver.findElement(By.id("disable_password")).isEnabled()) {
			System.out.println("Password is disabled");
		} else {
            System.out.println("Password is enabled");
		}
		if (driver.findElement(By.id("disable_password")).isEnabled()) {
			System.out.println("Password is disabled");
		} else {
            System.out.println("Password is enabled");
		}
		if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
			System.out.println("Age radio button is disabled");
		} else {
            System.out.println("Age radio button is enabled");
		}
		if (driver.findElement(By.id("bio")).isEnabled()) {
			System.out.println("Biography textarea is disabled");
		} else {
            System.out.println("Biography textarea is enabled");
		}
		if (driver.findElement(By.id("job3")).isEnabled()) {
			System.out.println("Job Role 03 dropdown is disabled");
		} else {
            System.out.println("Job Role 03 dropdown is enabled");
		}
		if (driver.findElement(By.id("check-disbaled")).isEnabled()) {
			System.out.println("Interests checkbox is disabled");
		} else {
            System.out.println("Interests checkbox is enabled");
		}
		if (driver.findElement(By.id("slider-2")).isEnabled()) {
			System.out.println("slider-2 is disabled");
		} else {
            System.out.println("slider-2 is enabled");
		}
	}
	
	//@Test
	public void Login_2_3_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
		
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
	}
	
	@Test
	public void Login_2_4_MailChip_isDisplayed_Enabled_Selected() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.xpath("//button[@class='onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon']")).click();
		driver.findElement(By.id("create-account-enabled")).click();
		
		
		// Verify Sign up button is Enable
		Assert.assertTrue(driver.findElement(By.id("create-account-enabled")).isEnabled());
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::span")).getText(), "An email address must contain a single @.");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='new_username']/following-sibling::span")).getText(), "Please enter a value");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(), "One lowercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(), "One uppercase character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).getText(), "One number");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(), "One special character");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(), "8 characters minimum");
		
		driver.findElement(By.id("email")).sendKeys("diemdiem@gmail.com");
		// TC01: nhập số
		driver.findElement(By.id("new_password")).sendKeys("321");
		
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.id("create-account-enabled")));
		sleepInSecond(2);
		
		driver.findElement(By.id("create-account-enabled")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// TC02: lower case
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("acv");
		driver.findElement(By.id("create-account-enabled")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// TC03: upper case
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("QWERTY");
		driver.findElement(By.id("create-account-enabled")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
				
		// TC04: SPECIAL CHARACTER

		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("!@#$");
		driver.findElement(By.id("create-account-enabled")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// TC05: Greater than 7 char
		
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("12345678");
		driver.findElement(By.id("create-account-enabled")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		
		// TC06: input valid password
		
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("123Abc!@");
		driver.findElement(By.id("create-account-enabled")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		
		driver.findElement(By.id("marketing_newsletter")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
		
	} 
	
	@Test
	public void Login_3_1_Login_Empty_Email_Password1() {
	driver.get("http://live.techpanda.org/");
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	
	driver.findElement(By.id("email")).sendKeys("");
	driver.findElement(By.id("pass")).sendKeys("");
	driver.findElement(By.id("send2")).click();
	
	Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
	Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	@Test
	public void Login_3_2_Login_Invalid_Email() {
	driver.get("http://live.techpanda.org/");
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	
	driver.findElement(By.id("email")).sendKeys("123@12345");
	driver.findElement(By.id("pass")).sendKeys("123456");
	driver.findElement(By.id("send2")).click();
	
	Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	
	}
	
	@Test
	public void Login_3_3_Password_Less_than_6() {	
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("diemdiem@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test
	public void Login_3_4_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys("diemdiem@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("987654");
		driver.findElement(By.id("send2")).click();	
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
		
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