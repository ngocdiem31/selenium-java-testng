package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_2_Custom_Dropdown {
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	//@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.findElement(By.cssSelector("span#speed-button")).click();
		
	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu>li>div")));
	List<WebElement> allitems = driver.findElements(By.cssSelector("ul#speed-menu>li>div"));
	
	for (int i = 0; i < allitems.size(); i++) {
		String itemText = allitems.get(i).getText();
		if (itemText.equals("Fast")) {
			allitems.get(i).click();
			break;
			
		}
	}
	}

	//@Test
	public void TC_02_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
	
	selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "3");
	Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "3");
	
    selectItemInCustomDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li/div", "Fast");
	Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Fast");
	
    selectItemInCustomDropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li/div", "Medium");
    Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Medium");
    
    selectItemInCustomDropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li/div", "Prof.");
    Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(), "Prof.");
	}

	public void selectItemInCustomDropdown(String xpathParent, String xpathChild, String expectedText) {
		
		driver.findElement(By.xpath(xpathParent)).click();
		sleepInSecond(3);
	    explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
	    List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));
	    
		for (WebElement tempElement : allItems) {
			String itemText = tempElement.getText();
			System.out.println(itemText);
			
		if (itemText.equals(expectedText)) {

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
			sleepInSecond(5);
			
			//((JavascriptExecutor)driver).executeScript("arguments[0].click();", tempElement);

			tempElement.click();
			sleepInSecond(1);
			break;
				
			}
		}
	}
	
	//@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Elliot Fu" );
		// Sau khi bắt locator của xpathParent > bắt locator của xpathChild > inspect element vừa mới chọn > bắt locator của element đó
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Elliot Fu");
		
	}
	
	//@Test
	public void TC_04_VueJS() {
	driver.get("https://mikerodham.github.io/vue-dropdowns/");
	selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Second Option");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='btn-group']/li")).getText(), "Second Option");
	}
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TC_05_Editable_Dropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItemInCustomDropdown("//input[@class='search']", "//div[@role='option']/span", "Andorra");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Andorra");
	
	selectItemInCustomDropdown("//input[@class='search']", "//div[@role='option']/span", "Aland Islands");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Aland Islands");
	
	}
	
	public void selectItemInEditableDropdown (String xpathTextbox, String xpathChild, String expectedText) {
	
	driver.findElement(By.xpath(xpathTextbox)).clear();
	driver.findElement(By.xpath(xpathTextbox)).sendKeys(expectedText);
	sleepInSecond(3);
	
	explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
	
	List<WebElement> allitems =  driver.findElements(By.xpath(xpathChild));
	for (WebElement tempElement : allitems) {
		String itemText = tempElement.getText();
		if (itemText.equals(expectedText)) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
			sleepInSecond(1);
			tempElement.click();
			sleepInSecond(3);
			break;
			
		}
	}
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	//Dùng contains(.,’’) ko dùng contains(.=’’)
}  //update