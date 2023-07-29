package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_WebElement_Excercise_Part2 {
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

	//@Test
	public void TC_01_Displayed() {
       driver.get("https://automationfc.github.io/basic-form/index.html");
       
       if (driver.findElement(By.id("mail")).isDisplayed()) {
    	   System.out.println("Email textbox is Displayed");
    	   driver.findElement(By.id("Mail")).sendKeys("Automation Testing");
	} else {
         System.out.println("Email textbox is not displayed");
	}
       
       if (driver.findElement(By.id("under_18")).isDisplayed()) {
		System.out.println("Age under_18 is displayed");
		driver.findElement(By.id("under_18")).click();
	} else {
        System.out.println("Age under_18 is not displayed");
	}
       
       if (driver.findElement(By.id("edu")).isDisplayed()) {
    	   System.out.println("Education textarea is displayed");
    	   driver.findElement(By.id("edu")).sendKeys("Automation Testing");		
	} else {
           System.out.println("Education textarea is not displayed");
           
	}
       
       if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
		System.out.println("Name User 5 is Displayed");
	} else {
        System.out.println("Name User 5 is not Displayed");
	}
	}
        
	//@Test
	public void TC_02_Enabled() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");		
		
		if (driver.findElement(By.id("disable_password")).isEnabled()) {
			System.out.println("Password textbox is enable");
		} else {
           System.out.println("Password textbox is disable");
		}
		
		if (driver.findElement(By.id("mail")).isEnabled()) {
			System.out.println("Mail textbox is enable");
		} else {
           System.out.println("Mail textbox is disable");
		}
		
		if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
			System.out.println("Radio button is enable");	
		} else {
           System.out.println("Radio buttob is disable");
		}
		
		if (driver.findElement(By.id("development")).isEnabled()) {
			System.out.println("Interest checkbox is enabled");
		} else {
            System.out.println("Interest checkbox is disabled");
		}
		
		if (driver.findElement(By.id("job1")).isEnabled()) {
			System.out.println("Job Role 1 single dropdown is enabled");
		} else {
            System.out.println("Job Role 1 single dropdown is disabled");
		}
		
		if (driver.findElement(By.id("slider-1")).isEnabled()) {
			System.out.println("Slider-1 is enabled");
		} else {
            System.out.println("Slider-1 is disabled");
		}
		
		
	}

	//@Test
	public void TC_03_Selected() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.id("under_18")).click();
	driver.findElement(By.id("java")).click();
	sleepInSecond(2);
	
	// Use to verify an element selected successfully - opposite is assertFalse
	// .isSelected(): if an element be choose successfully, it will return is True/ if not will return False
	Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
	Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
	
	driver.findElement(By.id("under_18")).click();
	driver.findElement(By.id("java")).click();
	sleepInSecond(2);
	
	Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
	// Verify be unchecked or not?
	Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
	}
	
	@Test
	public void TC_04_MailChimp() {
	driver.get("https://login.mailchimp.com/signup/");
	driver.findElement(By.xpath("//button[@class='onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon']")).click();
	driver.findElement(By.id("create-account-enabled")).click();
	sleepInSecond(5);
	
	// Verify sign up button is enabled
	Assert.assertTrue(driver.findElement(By.id("create-account-enabled")).isEnabled());
	
	// Verify error messages is display
	Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::span")).getText(), "An email address must contain a single @.");
	Assert.assertEquals(driver.findElement(By.xpath("//input[@id='new_username']/following-sibling::span")).getText(), "Please enter a value");
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']/span")).getText(), "One lowercase character");
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']/span")).getText(), "One uppercase character");
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='number-char not-completed']/span")).getText(), "One number");
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='special-char not-completed']/span")).getText(), "One special character");
	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='8-char not-completed']/span")).getText(), "8 characters minimum");
	
	driver.findElement(By.id("email")).sendKeys("ngocdiem@gmail.com");
	
	// case 1: input number
	driver.findElement(By.id("new_password")).sendKeys("123");
	driver.findElement(By.id("create-account-enabled")).click();
	sleepInSecond(3);
	
	Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//button[@id='create-account-enabled'and text()='Sign up']")).isDisplayed());
	
	// case 2: Lower case
	driver.findElement(By.id("new_password")).clear();
	driver.findElement(By.id("new_password")).sendKeys("truong");
	driver.findElement(By.id("create-account-enabled")).click();
	sleepInSecond(1);
	Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
	//Assert.assertTrue(driver.findElement(By.xpath("//button[@id='create-account-enabled'and text()='Sign up']")).isDisplayed());
	
	// Case 3: Uppercase
	driver.findElement(By.id("new_password")).clear();
	driver.findElement(By.id("new_password")).sendKeys("LAVIE");
	driver.findElement(By.id("create-account-enabled")).click();
	sleepInSecond(1);
	Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
	//Assert.assertTrue(driver.findElement(By.xpath("//button[@id='create-account-enabled'and text()='Sign up']")).isDisplayed());
	
	// case 4: special characters
	driver.findElement(By.id("new_password")).clear();
	driver.findElement(By.id("new_password")).sendKeys("!@#$");
	driver.findElement(By.id("create-account-enabled")).click();
	sleepInSecond(1);
	Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
	//Assert.assertTrue(driver.findElement(By.xpath("//button[@id='create-account-enabled'and text()='Sign up']")).isDisplayed());
	
	// case 5: greater than 7 characters
	driver.findElement(By.id("new_password")).clear();
	driver.findElement(By.id("new_password")).sendKeys("123456789");
	driver.findElement(By.id("create-account-enabled")).click();
	sleepInSecond(1);
	Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
	//Assert.assertTrue(driver.findElement(By.xpath("//button[@id='create-account-enabled'and text()='Sign up']")).isDisplayed());
	
	// case 6: valid value
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("123ABcd!@");
		sleepInSecond(1);
		Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
	//Assert.assertTrue(driver.findElement(By.xpath("//button[@id='create-account-enabled'and text()='Sign up']")).isDisplayed());
		
	// Check box
		driver.findElement(By.id("marketing_newsletter")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
		
	}

	private WebElement assertTrue(WebElement findElement) {
		// TODO Auto-generated method stub
		return null;
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