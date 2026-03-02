package Practical;

public class Rectangle extends Shape {
	public Rectangle(int width, int height) {super(width, height);}
	@Override
	public void printArea() {
		System.out.println("Rectangle area = "+ (a * b));
	}

}
