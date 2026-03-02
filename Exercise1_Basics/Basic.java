package Practical;

public class Basic {
	public static void main(String[] args) {
		// primitives
		int a = 10;
		double b = 8.0;
		char letter = 'A';
		boolean isJavaFun = true;
		
		//arithmetic and casting
		int sum = a + (int) b;       //cast double int 
		double division = a/b;       //double division
		int remainder = a%3;         // modulus
		
		//string concatenation (order matters)
		String msg = "Value of a = "+ a +" ,b = "+b;
		
		System.out.println("Ganga Mannan, 2462611");
		System.out.println(msg);
		System.out.println("sum = " + sum);
		System.out.println("Division = " + division);
		System.out.println("remainder = " + remainder);
		System.out.println("letter = " + letter + " ,is Java Fun: " + isJavaFun);
	}
}