package Practical;

//Animal.java(superclass)
public class Animal {
	protected String name;
	public Animal(String name) {
		this.name = name;
	}
public void speak() {
	System.out.println(name + " make a sound.");
}
public void info() {
	System.out.println("this is an animal named " + name);
}
}