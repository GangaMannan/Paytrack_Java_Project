package Practical;

public abstract class Shape {
	protected int a,b; // interpret differntly inn subclass eg radius uses a 
	public Shape(int a, int b) {
		this.a=a;
		this.b=b;
	}
	//print area abstract method
	public abstract void printArea();
}
