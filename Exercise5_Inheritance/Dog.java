package Practical;

//dog.java(subclass implementing interface)
public class Dog extends Animal implements Pet{
	private String owner;
	public Dog(String name, String owner) {
		super(name);   //call superclass constructor
		this.owner = owner;
	}
	@Override
	public void speak() {
		System.out.println(name + " barks: Woof!");
	}
	@Override
	public void play() {
		System.out.println(name + " plays fetch with " + owner);
	}
	@Override
	public String getOwnerName() {
		return owner;
	}
	//additional method
	public void wagTail() {
		System.out.println(name + " is wagging its tail.");
	}
}
