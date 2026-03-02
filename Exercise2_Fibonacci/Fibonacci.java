package Practical;
import java.util.Scanner;
public class Fibonacci {
	static void printFibonacci(int n) {
		if (n <= 0) {
			System.out.println("Nothing to Print");
			return;
		}
		long a=0,b = 1;
		System.out.println("Fibonacci: ");
		for (int i = 0; i<n; i++) {
			System.out.print(a + (i == n-1 ? "\n" : ","));
			long next  = a + b;
			a= b;
			b = next;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many Fibonacci numbers?: ");
		int n = sc.nextInt();
		printFibonacci(n);
		
		System.out.println("Enter an Integer to test even/odd:");
		int x = sc.nextInt();
		System.out.println(x + (x % 2 == 0 ? " is even" : " is odd"));
		System.out.println("Ganga Mannan, 2462611");
		
		sc.close();
		
		
	}
}
