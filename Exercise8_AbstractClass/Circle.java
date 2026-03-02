package Practical;

public class Circle extends Shape {
	public Circle(int radius, int ignored) {super(radius,ignored);} //b unused
	@Override
	public void printArea() {
		double area = Math.PI * a * a;
		System.out.println("Circle area = "+ area);
	}
}