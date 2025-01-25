/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zooanimalapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author gustavobrito22icloud.com
 */
public abstract class Animal {
    
     // Fields for storing animal information
    private String type;            // The type of animal (e.g., Mammal, Bird)
    private String species;         // The species of the animal (e.g., Lion, Parrot)
    private String name;            // The name of the animal (e.g., Simba, Polly)
    private String habitat;         // The habitat where the animal lives (e.g., Savanna)
    private LocalDate dob;          // Date of birth of the animal
    private double weight;          // Weight of the animal in kilograms
    private String characteristics; // Characteristics or traits of the animal

    // Constructor to initialize all fields
    public Animal(String type, String species, String name, String habitat, String dob, double weight, String characteristics) {
        this.type = type;
        this.species = species;
        this.name = name;
        this.habitat = habitat;
        // Parse the date of birth from the provided string in yyyy/MM/dd format
        this.dob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.weight = weight;
        this.characteristics = characteristics;
    }

    // Getter for the animal type
    public String getType() {
        return type;
    }

    // Getter for the animal species
    public String getSpecies() {
        return species;
    }

    // Getter for the animal name
    public String getName() {
        return name;
    }

    // Getter for the animal habitat
    public String getHabitat() {
        return habitat;
    }

    // Getter for the animal's age (calculated from the Date of Birth)
    public int getAge() {
        // Calculate the current year and subtract the birth year
        int currentYear = LocalDate.now().getYear();
        return currentYear - dob.getYear();
    }

    // Getter for the animal's weight
    public double getWeight() {
        return weight;
    }

    // Getter for the animal's characteristics
    public String getCharacteristics() {
        return characteristics;
    }

    // Abstract method to define how the animal eats (implemented by subclasses)
    public abstract void eat();

    // Abstract method to define how the animal makes sound (implemented by subclasses)
    public abstract void makeSound();

    // Abstract method to define how the animal moves (implemented by subclasses)
    public abstract void move();

    // Override the toString() method to display animal details in the specified format
    @Override
    public String toString() {
        return String.format(
                "Type: %s\nSpecies: %s\nName: %s\nHabitat: %s\nDate of Birth: %s\nWeight: %.2f\nCharacteristics: %s\n",
                type, species, name, habitat, dob.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), weight, characteristics
        );
    }
    
    
}
