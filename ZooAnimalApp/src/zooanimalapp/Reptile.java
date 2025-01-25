/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zooanimalapp;

/**
 *
 * @author gustavobrito22icloud.com
 */
// Reptile class inherits from the Animal abstract class
public class Reptile extends Animal {
     // Constructor to initialize the Reptile-specific details
    public Reptile (String type, String species, String name, String habitat, String dob, double weight, String characteristics) {
        // Call the parent (Animal) constructor to initialize fields
        super(type, species, name, habitat, dob, weight, characteristics);
    }

    // Implement the abstract eat() method for Reptiles
    @Override
    public void eat() {
        // Print how reptiles typically eat
        System.out.println(getName() + " is eating insects or small animals.");
    }

    // Implement the abstract makeSound() method for Reptiles
    @Override
    public void makeSound() {
        // Print the sound behavior for reptiles
        System.out.println(getName() + " is hissing or remaining silent.");
    }

    // Implement the abstract move() method for Reptiles
    @Override
    public void move() {
        // Print how reptiles typically move
        System.out.println(getName() + " is crawling or slithering.");
    }
}
