package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Checkbox_Radio {
	WebDriver driver;
	// Khai báo thư viện 
	JavascriptExecutor  jsExecutor;
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
	
		//Khởi tạo
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	

	@Test
	public void TC_01_Default_CheckBox() {
		
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		// ko nên bắt eleemnt bằng id='eq5' vì nó tương đương với index, nên lấy từ thẻ label đi lên thẻ input
		By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		
	// TRước khi Click vào check box phải kiểm tra nó đã đc chọn hay chưa, nếu chưa thì mới click
		if (!driver.findElement(dualZoneCheckbox).isSelected()) {
			driver.findElement(dualZoneCheckbox).click();
			sleepInSecond(2);
		} 
		
	// Verify check box đã đc chọn
		Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
	
	// Click bỏ chọn check box
		if (driver.findElement(dualZoneCheckbox).isSelected()) {
			driver.findElement(dualZoneCheckbox).click();
			sleepInSecond(2);
		} 
	// Verify check box đã bị bỏ chọn
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
		

	}

	//@Test
	public void TC_02_Default_Radio_button() {
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By petrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By dieselRadio = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input");
		
	// Clicks vào radio
		if (!driver.findElement(petrolRadio).isSelected()) {
			driver.findElement(petrolRadio).click();
			sleepInSecond(3);
		}
		// Verify radio đã đc chọn	
	Assert.assertTrue(driver.findElement(petrolRadio).isSelected());
	
	// Bỏ chọn radio cũ và chọn radio mới
	if (driver.findElement(petrolRadio).isSelected()) {
		driver.findElement(dieselRadio).click();
		sleepInSecond(3);
		
	}
	// Verify radio cũ đã bỏ chọn và radio mới đã đc chọn
	Assert.assertFalse(driver.findElement(petrolRadio).isSelected());
	Assert.assertTrue(driver.findElement(dieselRadio).isSelected());
	
	}

	//@Test
	public void TC_03_Select_All_Checkbox() {
	driver.get("https://automationfc.github.io/multiple-fields");
	// dùng 1 list element để chứa hết tất cả check box
	List<WebElement> allcheckboxes = driver.findElements(By.cssSelector("div.form-single-column input.form-checkbox"));
	
	// Clicks hết toàn bộ check box
	// use forEach loop cause foreach use for list
	// checkbox khai bao cho bien tam co kieu list va dung bien de duyet qua tung thang trog allcheckboxes
	for (WebElement checkbox : allcheckboxes) {
		if (!checkbox.isSelected()) {
			checkbox.click();	
		}
	}
	// Verify hết toàn bộ
	
		for (WebElement checkbox : allcheckboxes) {
	Assert.assertTrue(checkbox.isSelected());
	}
	}
	
	
	//@Test
	public void TC_04_Select_Checkbox_Radio_By_Condition() {
		driver.get("https://automationfc.github.io/multiple-fields");
		// dùng 1 list element để chứa hết tất cả check box
		List<WebElement> allcheckboxes = driver.findElements(By.cssSelector("div.form-single-column input.form-checkbox"));
		
	// Clicks on checbox that has name "Gallstones"
	for (WebElement checkbox : allcheckboxes) {
		if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Gallstones")) {
			checkbox.click();
		}
	}
	
	// Verify only checkbox Gallstones has been chosen
	for (WebElement checkbox : allcheckboxes) {
		if (checkbox.getAttribute("value").equals("Gallstones")) {
			Assert.assertTrue(checkbox.isSelected());
		}
	}
	}
	
	//@Test
	public void TC_05_Select_Radio_By_Condition() {
		driver.get("https://automationfc.github.io/multiple-fields");
		// Lưu hết các radio của Exercise
	List<WebElement> exercise = driver.findElements(By.xpath("//label[contains(text(),'Exercise')]/following-sibling::div//input[@type='radio']"));
	
	// Click chọn tất cả radio
	for (WebElement radio : exercise) {
		if (!radio.isSelected() && radio.getAttribute("value").equals("5+ days")) {
			radio.click();
		}
	}
	// Verify radio "5+ days" is selected
	for (WebElement radio : exercise) {
		if (radio.getAttribute("value").equals("5+ days")) {
			Assert.assertTrue(radio.isSelected());
		}
	}
	}
	
	//@Test
	public void TC_06_Custom_Radio() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
	//	By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		// Case 1: Nếu như dùng thẻ input thì ko click đc nhưng lại verify đc
		// driver.findElement(registerRadio).click();
        // Assert.assertTrue(driver.findElement(registerRadio).isSelected());
	
       // Case 2: dùng thẻ khác hiển thị để click nhưng ko verify đc (vì thẻ khác thẻ input ko có trạng thái selected => nen nó luôn trả về False)
	   
//	By registerRadioDiv = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']");
//	driver.findElement(registerRadioDiv).click();
//	Assert.assertTrue(driver.findElement(registerRadioDiv).isSelected());
	
	  //Case 3: Dùng thẻ khác để input + dùng input để verify
//	By registerRadioVerify = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
//	By registerRadioClick = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/div[@class='mat-radio-outer-circle']");
//	
//	driver.findElement(registerRadioClick).click();
//	sleepInSecond(3);
//	Assert.assertTrue(driver.findElement(registerRadioVerify).isSelected());
	// => thực tế ko sử dụng vì 1 element mà phải dùng 2 locator để defined (khai báo biến) sẽ mất time
	
	// Case 4: Vẫn dùng thẻ input để click + verify nhưng dùng thư viện Java Script để click
	// JS ko quan tâm element có bị che hay ko
		By registerRadio = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(registerRadio));
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(registerRadio).isSelected());
	}
	

		@Test
		public void TC_07_Custom_Radio_Google() {
			
			driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	// Cách 1: dùng isDisplayed to verify HCM radio no be chosen - ko thể verify sau khi đã chọn vì aria-checked đổi sang true
		
		// define biến
		//By hcmRadio = (By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked='false']"));
		// Verify Ho Chí Minh radio not be chosen
		//Assert.assertTrue(driver.findElement(hcmRadio).isDisplayed());
		// Clicks vào Hồ chí minh radio
		//driver.findElement(hcmRadio).click();
		
	// Cách 2: Dùng getAttribute để verify trước và sau khi chọn
		By hcmRadio = (By.xpath("//div[@aria-label='Hồ Chí Minh']"));
		
		
	// Verify Ho Chí Minh radio not be chosen
		Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "false");
		
		
	// Clicks vào Hồ chí minh radio
		driver.findElement(hcmRadio).click();	
		
		// Verify Ho Chí Minh radio be chosen	
		Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"), "true");
		
	// Chọn hết tất cả các check box
	// List ra hết tất cả check box
		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//div[@role='checkbox' and @aria-label]"));
		
	// clicks tất cả check box	
		for (WebElement checkbox : allCheckboxes) {
			if (checkbox.getAttribute("aria-checked").equals("false")) {
				checkbox.click();
				sleepInSecond(2);
				
			}
		}
	// Verify tất cả check box đã đc chọn
       for (WebElement checkbox : allCheckboxes) {
		//if (checkbox.getAttribute("aria-chekced").equals("true"));
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
			
			
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