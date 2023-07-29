package javatester;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_13_Data_Type {

	private static final boolean False = false;
	private static final String Nữ = null;

	public static void main(String[] args) {
		// Kiểu dữ liệu nguyên thủy
		// byte chỉ chưa đc 256 số
		byte bnumber = 127;
		short sNumber = 32000;
		int iNumber = 499344389;
		long lNumber = 709823649;
		
		// Số thực: Fload/ Double
		float studentPoint  = 8.5f;
		double employeeSalary = 21.3d;
		
		// Logic: boolean
		boolean status = true; //Nam
		status = False; // Nữ
		
		// Ký tự: char
		char a = 'A';
		
		//  Kiểu dữ liệu tham chiếu
		//Class
		FirefoxDriver driver = new FirefoxDriver();
		
		// Interface: 
		WebElement firstNameTextbox;
		
		// String: cũng là kiểu Class, thể hiện cho những ký tự
		String firstName = "Jannie";
		
		// object: cũng là 1 kiểu class và là kiểu cha của tất cả các kiểu class khác
		Object people;
		
		// Array: Là mảng chứa nhiều giá trị của kiểu nguyên thủy hoặc kiểu tham chiếu
		String[] studentName = {"Nguyễn Bình An", "Trần Minh Hùng", "Nguyễn Minh Tâm"};
		// ký tự nằm trong dấu nháy đơn''; Chuỗi_String nằm trong dấu nháy đôi ""
		
		// Collection: List/ Set/ Queue 
		// Bao gồm nhiều kiểu dữ liệu thuộc kiểu nguyên thủy
		List<WebElement> checkboxes = driver.findElements(By.cssSelector(""));
		
		// Map: Bao gồm nhiều kiểu dữ liệu thuộc kiểu nguyên thủy
		Map<String, Integer> student = new HashMap<String, Integer>();
		
	}
	

}
