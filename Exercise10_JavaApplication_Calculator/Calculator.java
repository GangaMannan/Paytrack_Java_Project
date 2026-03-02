package Practical;
import java.util.Scanner;
public class Calculator {
    static void printMenu() {
        System.out.println("\n=== Calculator ===");
        System.out.println("1) Add");
        System.out.println("2) Subtract");
        System.out.println("3) Multiply");
        System.out.println("4) Divide");
        System.out.println("5) Power (a^b)");
        System.out.println("6) Modulo (a % b)");
        System.out.println("7) Square root");
        System.out.println("0) Exit");
        System.out.print("Choose option: ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        	while (running) {
        	printMenu();
        	int choice = sc.nextInt();
        	double a, b;
            switch (choice) {
                case 1:
                    a = sc.nextDouble(); b = sc.nextDouble();
                    System.out.println("Result = " + (a + b));
                    break;
                case 2:
                    a = sc.nextDouble(); b = sc.nextDouble();
                    System.out.println("Result = " + (a - b));
                    break;
                case 3:
                    a = sc.nextDouble(); b = sc.nextDouble();
                    System.out.println("Result = " + (a * b));
                    break;
                case 4:
                    a = sc.nextDouble(); b = sc.nextDouble();
                    if (b == 0)
                        System.out.println("Error: division by zero");
                    else
                        System.out.println("Result = " + (a / b));
                    break;
                case 5:
                    a = sc.nextDouble(); b = sc.nextDouble();
                    System.out.println("Result = " + Math.pow(a, b));
                    break;
                case 6:
                    int ai = sc.nextInt();
                    int bi = sc.nextInt();
                    if (bi == 0)
                        System.out.println("Error: modulo by zero");
                    else
                        System.out.println("Result = " + (ai % bi));
                    break;
                case 7:
                    a = sc.nextDouble();
                    if (a < 0)
                        System.out.println("Error: negative number");
                    else
                        System.out.println("Result = " + Math.sqrt(a));
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        sc.close();
        System.out.println("Calculator closed.");
    }
}