/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zooanimalapp;

/**
 *
 * @author gustavobrito22icloud.com
 */

// Mammal class inherits from the Animal abstract class
public class Mammal extends Animal {
     // Constructor to initialize the Mammal-specific details. 
    public Mammal(String type, String species, String name, String habitat, String dob, double weight, String characteristics) {
        // Call the parent (Animal) constructor to initialize fields
        super(type, species, name, habitat, dob, weight, characteristics);
    }

    // Abstract eat() method for Mammals
    @Override 
    public void eat() {
        // Print how mammals typically eat.
        System.out.println(getName() + " is eating plants or meat.");
    }

    // Abstract makeSound() method for Mammals.
    @Override
    public void makeSound() {
        // Print the sound behavior for mammals.
        System.out.println(getName() + " is making a mammal sound.");
    }

    //  Abstract move() method for Mammals.
    @Override
    public void move() {
        // Print how mammals typically move.
        System.out.println(getName() + " is walking or running.");
    }
}
