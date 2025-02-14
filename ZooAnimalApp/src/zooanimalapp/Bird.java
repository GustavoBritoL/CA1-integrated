/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zooanimalapp;

/**
 *
 * @author gustavobrito22icloud.com
 */
// Bird class inherits from the Animal abstract class.
public class Bird extends Animal {
     // Constructor to initialize the Bird-specific details.
    public Bird(String type, String species, String name, String habitat, String dob, double weight, String characteristics) {
        // Call the parent (Animal) constructor to initialize fields.
        super(type, species, name, habitat, dob, weight, characteristics);
    }

    // Abstract eat() method for Birds.
    @Override
    public void eat() {
        // Print how birds typically eat.
        System.out.println(getName() + " is pecking at seeds or insects.");
    }

    // Abstract makeSound() method for Birds.
    @Override
    public void makeSound() {
        // Print the sound behavior for birds.
        System.out.println(getName() + " is chirping or singing.");
    }

    // Abstract move() method for Birds
    @Override
    public void move() {
        // Print how birds typically move.
        System.out.println(getName() + " is flying.");
    }
}
