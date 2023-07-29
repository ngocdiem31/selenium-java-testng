package webdriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_2_Button_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		// Clicks to close pop up
		//driver.findElement(By.id("moe-dontallow_button")).click();
		// Clicks sang tab 'Dang nhap'
		driver.findElement(By.xpath("//ul[@class='popup-login-tab']/li[1]")).click();
		
		sleepInSecond(1);
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
		// Verify Login buton is enabled
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		// Verify Login button has background is grey
		 System.out.println(driver.findElement(loginButton).getCssValue("background"));
		 
		// Verify Login Button
		 Assert.assertTrue(driver.findElement(loginButton).getCssValue("background").contains("rgb(224, 224, 224)"));
		 
		 // input email & password
		 driver.findElement(By.id("login_username")).sendKeys("tangdien@gmail.com");
		 driver.findElement(By.id("login_password")).sendKeys("hidenlove2");
		 
		 // verify login button is disable
		 Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		 System.out.println(driver.findElement(loginButton).getCssValue("background"));
		 
		 String loginButtonBackgroundColor = driver.findElement(loginButton).getCssValue("background");
		 
		// Chuyển từ RGB/RGBA qua kiểu dữ liệu Color 
		Color loginButtonColor = Color.fromString(loginButtonBackgroundColor);
		
		 // asHex trả về kiểu String nên khai báo bằng String
		 String loginButtonHexa = loginButtonColor.asHex();
		 
		 // Đổi rgb to hexa then verify by hexa code
		 Assert.assertEquals(loginButtonHexa, "#c92127");
		 
		 
		 
	}

	//@Test
	public void TC_02_Default_Checkbox_Telerik() {
		driver.get("https://demos.telerik.com/aspnet-mvc/checkbox");
		
		By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		
		// Check check box [Dual-zone air conditioning] was check or uncheck
		if (!driver.findElement(dualZoneCheckbox).isSelected()) {
			driver.findElement(dualZoneCheckbox).click();
		}
		sleepInSecond(2); 
		
		//Verify check box is selected
		Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
		
		// Uncheck check box
		driver.findElement(dualZoneCheckbox).click();
		// Verify check box be uncheck
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
			
	}
	
	//@Test
	public void TC_03_Default_Checkbox_Telerik() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		By petroRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By dieselRadio = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input");
		
		// Clicks on petro radio button
		if (!driver.findElement(petroRadio).isSelected()) {
			driver.findElement(petroRadio).click();
		}
		sleepInSecond(2);
		
		// Verify radio button checked or not?
		Assert.assertTrue(driver.findElement(petroRadio).isSelected());
		
		// Uncheck radio button
		if (driver.findElement(petroRadio).isSelected()) {
			driver.findElement(dieselRadio).click();
		}
		
		// Verify the old radio button is uncheck and the new radio is checked
		Assert.assertFalse(driver.findElement(petroRadio).isSelected());
		Assert.assertTrue(driver.findElement(dieselRadio).isSelected());
	}
	
	//@Test
	public void TC_04_Default_Radio_angular() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		// Clicks on [Summer] radio button
		By ngModelRadio = By.xpath("//input[@id='mat-radio-4-input']");
		if (!driver.findElement(ngModelRadio).isSelected()) {
			driver.findElement(ngModelRadio).click();
		}
		sleepInSecond(2);
		// Verify radio be checked
		Assert.assertTrue(driver.findElement(ngModelRadio).isSelected());
	}
	//@Test
		public void TC_05_Default_Checkbox_angular() {
	driver.get("https://material.angular.io/components/checkbox/examples");
	By checkedCheckbox = By.xpath("//input[@id='mat-mdc-checkbox-1-input']");
	By indeterminateCheckbox = By.xpath("//input[@id='mat-mdc-checkbox-2-input']");
	 // Clicks on [Checked] & [Indeterminate] check box
	if (!driver.findElement(checkedCheckbox).isSelected()) {
		driver.findElement(checkedCheckbox).click();
	} 
	if (!driver.findElement(indeterminateCheckbox).isSelected()) {
		driver.findElement(indeterminateCheckbox).click();
	}
	
	// Verify 2 check box be selected
	Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
	Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
	
	// Uncheck 2 check boxes
	
	if (driver.findElement(checkedCheckbox).isSelected()) {
		driver.findElement(checkedCheckbox).click();
	}
	if (driver.findElement(indeterminateCheckbox).isSelected()) {
		driver.findElement(indeterminateCheckbox).click();
	// Verify 2 check boxes be unchecked
		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
	Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
	
	}
		}
	//@Test
	public void TC_06_Custom_Radio_Tiem_Chung_Covid() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		// Case custom radio bị ẩn ko thể thao tác đc
		// dùng thẻ input để click + verify nhưng dùng thư viện Java Script để click
By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(registerRadio));
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(registerRadio).isSelected());	
	}
	
	@Test
	public void TC_07_Checkbox_Radio_Google() {
	//Cách 2: Dùng getAttribute để verify trước và sau khi chọn
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By dnradio = By.xpath("//div[@aria-label='Đà Nẵng']");
	// Verify radio is uncheck
		Assert.assertEquals(driver.findElement(dnradio).getAttribute("aria-checked"),"false");
	// Clicks on radio 'Da Nang'
		driver.findElement(dnradio).click();
	// Verify radio is checked
		Assert.assertEquals(driver.findElement(dnradio).getAttribute("aria-checked"),"true");
		
	// select all checkboxes
	// lấy hết list check box
	List<WebElement> allCheckboxes = driver.findElements(By.xpath("//div[@role='checkbox' and  @aria-label]"));
    //Verify tất cả các check box chưa đc chọn thì chọn nó
	for (WebElement checkbox : allCheckboxes) {
		if (checkbox.getAttribute("aria-checked").equals("false")) {
			checkbox.click();
			sleepInSecond(1);
		}
	}
	// Verify all checkbox is checked
    for (WebElement checkbox : allCheckboxes) {
		Assert.assertEquals(checkbox.getAttribute("aria-checked"),"true");
			
		
	}
	
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