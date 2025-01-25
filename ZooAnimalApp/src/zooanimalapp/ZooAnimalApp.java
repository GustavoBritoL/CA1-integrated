/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package zooanimalapp;

import java.io.BufferedReader;        // For reading the file line by line
import java.io.FileReader;           // For opening and reading files
import java.io.IOException;          // For handling file-related exceptions
import java.time.LocalDate;          // For handling date (Date of Birth)
import java.time.format.DateTimeFormatter; // For parsing and formatting dates
import java.time.format.DateTimeParseException; // For handling invalid date formats
import java.util.ArrayList;          // For storing a list of animals
import java.util.List;               // Interface for a list of animals
/**
 *
 * @author gustavobrito22icloud.com
 */
public class ZooAnimalApp {
 private List<Animal> animals;  // List to store all the animals in the zoo

    public ZooAnimalApp() {
        animals = new ArrayList<>();
    }
 
    
     // Method to add an animal to the zoo
    public void addAnimal(Animal animal) {
        animals.add(animal); // Add the animal to the list
        System.out.println(animal.getName() + " has been added to the zoo!"); // Notify the user
    }
    
        // Method to display all animals in the zoo
    public void displayAnimals() {
        System.out.println("\nAnimals in the zoo:"); // Header message
        for (Animal animal : animals) { // Loop through all animals
            // Print the animal's details
            System.out.println(animal.toString());
            // Trigger the animal's behavior
            animal.eat();
            animal.makeSound();
            animal.move();
            System.out.println(); // Add a blank line for better readability
        }
    }

    // Method to read animals from the file
    public void loadAnimalsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) { // Read lines until EOF
                // Parse Type, Species, Name from the first line
                String[] basicInfo = line.split(",");
                if (basicInfo.length != 3) { // Check for valid format
                    System.err.println("Invalid format for basic info: " + line);
                    skipInvalidAnimal(br, 3); // Skip invalid entries
                    continue;
                }

                String type = basicInfo[0];    // Type of the animal (e.g., Mammal)
                String species = basicInfo[1]; // Species of the animal (e.g., Lion)
                String name = basicInfo[2];    // Name of the animal (e.g., Simba)

                // Read Habitat from the next line
                String habitat = br.readLine();

                // Read Date of Birth and Weight from the next line
                line = br.readLine();
                String[] dobWeight = line.split(",");
                if (dobWeight.length != 2) { // Check for valid format
                    System.err.println("Invalid format for DoB and weight: " + line);
                    skipInvalidAnimal(br, 2); // Skip invalid entries
                    continue;
                }

                String dob = dobWeight[0];         // Date of birth (yyyy/MM/dd)
                String weightString = dobWeight[1]; // Weight as a string

                // Read Characteristics from the next line
                String characteristics = br.readLine();

                // Validate the data before creating the animal object
                if (!isValidData(type, species, name, habitat, dob, weightString, characteristics)) {
                    skipInvalidAnimal(br, 1); // Skip invalid entries
                    continue;
                }

                // Convert weight to a double
                double weight = Double.parseDouble(weightString);

                // Create an animal object based on the type
                Animal animal = createAnimal(type, species, name, habitat, dob, weight, characteristics);

                // Add the animal to the zoo if it was successfully created
                if (animal != null) {
                    addAnimal(animal);
                }
            }
        } catch (IOException e) { // Handle file reading errors
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Helper method to skip invalid animal data
    private void skipInvalidAnimal(BufferedReader br, int linesToSkip) throws IOException {
        // Skip the specified number of lines to ignore invalid data
        for (int i = 0; i < linesToSkip; i++) {
            br.readLine();
        }
    }

    // Method to validate the animal data
    private boolean isValidData(String type, String species, String name, String habitat, String dob, String weightString, String characteristics) {
        // Validate species (must contain only letters and spaces)
        if (!species.matches("^[A-Za-z ]+$")) {
            System.err.println("Invalid species: " + species);
            return false;
        }

        // Validate name (can contain letters, numbers, and spaces)
        if (!name.matches("^[A-Za-z0-9 ]+$")) {
            System.err.println("Invalid name: " + name);
            return false;
        }

        // Validate habitat (must not be empty)
        if (habitat == null || habitat.trim().isEmpty()) {
            System.err.println("Invalid habitat: " + habitat);
            return false;
        }

        // Validate Date of Birth (must match yyyy/MM/dd format)
        if (!dob.matches("^\\d{4}/\\d{2}/\\d{2}$")) {
            System.err.println("Invalid Date of Birth format: " + dob);
            return false;
        }
        try {
            // Ensure the date can be parsed
            LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (DateTimeParseException e) {
            System.err.println("Invalid Date of Birth: " + dob);
            return false;
        }

        // Validate weight (must be a positive double)
        try {
            double weight = Double.parseDouble(weightString);
            if (weight <= 0) {
                System.err.println("Invalid weight: " + weightString);
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid weight format: " + weightString);
            return false;
        }

        // Validate characteristics (must not be empty)
        if (characteristics == null || characteristics.isEmpty()) {
            System.err.println("Invalid characteristics: " + characteristics);
            return false;
        }

        return true; // All validations passed
    }

    // Factory method to create an animal object based on its type
    private Animal createAnimal(String type, String species, String name, String habitat, String dob, double weight, String characteristics) {
        switch (type.toLowerCase()) {
            case "mammal":
                return new Mammal(type, species, name, habitat, dob, weight, characteristics);
            case "bird":
                return new Bird(type, species, name, habitat, dob, weight, characteristics);
            case "reptile":
                return new Reptile(type, species, name, habitat, dob, weight, characteristics);
            case "fish":
                return new Fish(type, species, name, habitat, dob, weight, characteristics);
            default:
                System.err.println("Unknown animal type: " + type); // Handle unknown types
                return null;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
                ZooAnimalApp zoo = new ZooAnimalApp(); // Create an instance of ZooAnimalApp

        // Specify the input file containing animal data
        String filename = "/Users/gustavobrito22icloud.com/Desktop/animals.txt";

        // Load animals from the file
        zoo.loadAnimalsFromFile(filename);

        // Display all animals in the zoo
        zoo.displayAnimals();
    }  
}
