package webdriver;

import java.util.List;
//import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Custom_Dropdown {
	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
    WebDriverWait explicitWait;
    
    
	
    
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows 10")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");	
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// Lưu ý: khởi tạo explicitWait sau khi khởi tạo driver vì explicitWait dùng driver
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_Dropdown_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// Clicks vào 1 thẻ nào đó để nó xổ ra hết các item
		driver.findElement(By.cssSelector("span#number-button")).click();
		
		// Chờ cho tất cá các item đc load ra hết- trong vòng bao lâu (30s)
		// chờ cái nào đại diện cho tất cả các item => lấy locator đại diện cho tất cả các item ( đi từ ul > li > div (tới thẻ có chứa nội dung của item))
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu>li>div")));
	// Dùng presenceOfAllElementsLocatedBy vì presence chỉ quan tâm tới tất cả item chứa trong HTML, còn Visible chỉ quan tâm tới cái hiển thị 
		
		// Nếu như item cần chọn hiển thị ra thì chọn luôn
		// Dùng vòng lặp For lấy hết tất cả ra, sau đó kiểm tra từng item, getText ra nếu đúng thì dừng lại và chọn nó
	// Chi tiết các bước thực hiện
		//Lấy hết tất cả item trong dropdown lưu thành List
		List<WebElement> allitems = driver.findElements(By.cssSelector("ul#number-menu>li>div"));
		
		  // cách 1
		// Duyệt qua từng item
		for (int i = 0; i < allitems.size(); i++) {
			// Get text của từng item
			String itemText = allitems.get(i).getText();
			// Kiểm tra text có đúng với item mình cần chọn ko
			if (itemText.equals("9")) {
				// Clicks vào
				allitems.get(i).click();
				// Thoát khởi vòng lặp
				break;
			}
			
		}
		// cách 2: dùng ForEach
		for (WebElement tempElement : allitems) {
			String itemText = tempElement.getText();
			
			if (itemText.equals("9")) {
				tempElement.click();
				break;
				
			}
		}
		}
		@Test
		public void TC_02_Jquery() {
			driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
			
			selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "2");
			Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "2");
		
			selectItemInCustomDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li/div", "Slower");
			Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Slower");
			sleepInSecond(10);
			selectItemInCustomDropdown("//span[@id='files-button']", "//ul[@id='files-menu']/li/div", "Some unknown file");
			Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']/span[@class='ui-selectmenu-text']")).getText(), "Some unknown file");
			 
			selectItemInCustomDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li/div", "Prof.");
			Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(), "Prof.");
		}
			
			// Clicks cho nhiều dropdown trên page thì thêm string
		public void selectItemInCustomDropdown(String xpathParent, String xpathChild, String expectedText) {
		
			driver.findElement(By.xpath(xpathParent)).click();
			sleepInSecond(2);
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
		
			List<WebElement> allitems = driver.findElements(By.xpath(xpathChild));
			
			for (WebElement tempElement : allitems) {
				String itemText = tempElement.getText();
				System.out.println(itemText);
				
				if (itemText.equals(expectedText)) {
					//Scroll tới element
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
					sleepInSecond(1);
					tempElement.click();
					sleepInSecond(1);
					break;
		}
			}
		
	}
		
		//@Test
		public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");
		}
		
		//@Test
		public void TC_04_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Second Option");
		// chon item roi moi inspect len de bat locator - in this case locator is parent
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
		
		}
		//@Test
		public void TC_05_Editable_Dropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectitemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Algeria");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Algeria");
		
		selectitemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Anguilla");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Anguilla");
		}
		public void selectitemInEditableDropdown(String xpathTextbox, String xpathChild, String expectedText) {
			
			driver.findElement(By.xpath(xpathTextbox)).clear();;
			driver.findElement(By.xpath(xpathTextbox)).sendKeys(expectedText);
			sleepInSecond(2);
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
		
			List<WebElement> allitems = driver.findElements(By.xpath(xpathChild));
			
			for (WebElement tempElement : allitems) {
				String itemText = tempElement.getText();
				System.out.println(itemText);
				
				if (itemText.equals(expectedText)) {
					//Scroll tới element
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
					sleepInSecond(1);
					tempElement.click();
					sleepInSecond(1);
					break;
		}
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
}  