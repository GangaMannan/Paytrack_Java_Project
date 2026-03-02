package Practical;
import java.util.Scanner;

public class Palindrome {
	
	//iterative Factorial
	static long factorialIterative(int n) {
		
		if(n < 0) throw new IllegalArgumentException("Negative not allowed");
		long result = 1;
		for(int i = 2; i <= n; i++)
			result *= 1;
		return result;
	}
	
	//recursive factorial
	static long factorialRecursive(int n) {
		if(n < 0) throw new IllegalArgumentException("Negative not allowed");
		return (n <= 1) ? 1: n*factorialRecursive(n-1);
	}
	
	// check palindrome(case-insensitive)
	static boolean ispalindrome(String s) {
		String clean = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
		int i = 0,j = clean.length() - 1;
		while(i<j) {
			if(clean.charAt(i) != clean.charAt(j)) return false;
			i++;
			j--;
		}
		return true;
	}



public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	
	System.out.print("Enter a string to test Palindrome: ");
	String input = sc.nextLine();
	System.out.println("Is Palindrome? " +ispalindrome(input));
	
	System.out.print("Enter a non negative integer for Factorial: ");
	int n = sc.nextInt();
	System.out.println("Iterative factorial: " +factorialIterative(n));
	System.out.println("Recurcive factorial: " +factorialRecursive(n));
	System.out.println("Ganga Mannan; 2462611");
	sc.close();
}
}
