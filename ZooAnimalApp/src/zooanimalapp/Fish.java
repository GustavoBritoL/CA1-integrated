/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zooanimalapp;

/**
 *
 * @author gustavobrito22icloud.com
 */
// Fish class inherits from the Animal abstract class
public class Fish extends Animal {
        // Constructor to initialize the Fish-specific details
    public Fish(String type, String species, String name, String habitat, String dob, double weight, String characteristics) {
        // Call the parent (Animal) constructor to initialize fields
        super(type, species, name, habitat, dob, weight, characteristics);
    }

    // Implement the abstract eat() method for Fish
    @Override
    public void eat() {
        // Print how fish typically eat
        System.out.println(getName() + " is eating algae or smaller fish.");
    }

    // Implement the abstract makeSound() method for Fish
    @Override
    public void makeSound() {
        // Print the sound behavior for fish
        System.out.println(getName() + " is making bubbling noises.");
    }

    // Implement the abstract move() method for Fish
    @Override
    public void move() {
        // Print how fish typically move
        System.out.println(getName() + " is swimming.");
    }
}
