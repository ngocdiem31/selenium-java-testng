package javatester;

public class Topic_15_And_Or {

	public static void main(String[] args) {
		boolean statusA;
		boolean statusB;
		// And - &&
		// 1 trong 2 điều kiện Sai
		statusA = true;
		statusB = false;
		System.out.println("Kết quả: " + (statusA && statusB));
		// 1 trong 2 điều kiện Đúng
		statusA = false;
		statusB = true;
		System.out.println("Kết quả: " + (statusA && statusB));
		// Cả 2 điều kiện đều Sai		
		statusA = false;
		statusB = false;
		System.out.println("Kết quả: " + (statusA && statusB));
		// Cả 2 điều kiện đều Đúng
		statusA = true;
		statusB = true;
		System.out.println("Kết quả: " + (statusA && statusB));

	    // Or - ||
	// 1 trong 2 điều kiện Sai => Đúng
			statusA = true;
			statusB = false;
			System.out.println("Kết quả=  " + (statusA || statusB));
			// 1 trong 2 điều kiện Đúng => Đúng
			statusA = false;
			statusB = true;
			System.out.println("Kết quả=  " + (statusA || statusB));
			// Cả 2 điều kiện đều Sai => Sai		
			statusA = false;
			statusB = false;
			System.out.println("Kết quả=  " + (statusA || statusB));
			// Cả 2 điều kiện đều Đúng => Đúng
			statusA = true;
			statusB = true;
			System.out.println("Kết quả=  " + (statusA || statusB));

}
}
