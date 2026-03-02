package Practical;

//InheritanceInterfaceDemo.java (driver)
public class Inheritance {
	public static void main(String[] args) {
		Animal generic = new Animal("Generic");
		generic.speak();
		
		Dog d = new Dog("Buddy", "Alics");
		d.speak();
		d.play();
		d.info();
		d.wagTail();
		
		//Polumorphism: Treat Dog as Animal
		Animal petAsAnimal = new Dog("Rex", "Bob");
		petAsAnimal.speak();
		//petAsAnimal.play();	// compile error reference type Animal doesnt have play
		
		// if you want to call play(),cast:
		if(petAsAnimal instanceof Pet) {
			((Pet) petAsAnimal).play();
		}
		}
}
