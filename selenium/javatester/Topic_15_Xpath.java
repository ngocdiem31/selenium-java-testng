package javatester;

public class Topic_15_Xpath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String firstName = "Michael";
		String lastName = "Jackson";
		// Nối chuỗi: cách 1
		System.out.println(firstName + " " + lastName);
		// Nối chuỗi: cách 2
		System.out.println(firstName.concat(" " + lastName));
		System.out.println(firstName.concat(" ").concat(lastName));
	}

}
