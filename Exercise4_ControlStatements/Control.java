package Practical;
import java.util.Scanner;
public class Control {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter an integer: ");
		int n = sc.nextInt();
		//if-else
		if(n < 0) {
			System.out.println("Negative");
		}
		else if(n == 0) {
			System.out.println("Zero(0)");
		}
		else {
			System.out.println("Positive");
		}
		
		//Nested Condition Example
		if(n % 2 == 0) {
			System.out.println("Its Even");
		}
		else {
			System.out.println("Its Odd");
		}
		int day = n % 7 + 1; // 1...7 // switch statement (Demonstrate Mapping)
		switch(day) {
		case 1 -> System.out.println("Monday");
		case 2 -> System.out.println("Tuesday");
		case 3 -> System.out.println("Wednesday");
		case 4 -> System.out.println("Thursday");
		case 5 -> System.out.println("Friday");
		case 6 -> System.out.println("Satuarday");
		default-> System.out.println("Sunday");
		}
		System.out.println("Ganga Mannan, 2462611");
		sc.close();
	}
}
