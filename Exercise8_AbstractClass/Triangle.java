package Practical;

public class Triangle extends Shape {
	public Triangle(int base, int height) {super(base, height);}
	@Override
	public void printArea() {
		double area = 0.5 * a * b;
		System.out.println("Triangle Area = "+ area);
	}
}
