//Github link bellow:
//https://github.com/GustavoBritoL/CA1-integrated

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package zooanimalapp;

import java.io.BufferedReader; // For reading the file line by line.
import java.io.FileReader;     // For opening and reading files.
import java.io.IOException;    // For handling file-related exceptions.
import java.util.ArrayList;    // Provides a resizable array implementation (used for storing animal objects).
import java.util.Arrays;       // Utility class for working with arrays (used to convert array to a list).
import java.util.HashSet;      // Implements a hash-based collection (used for fast lookup of valid habitats).
import java.util.List;         // Interface for a list of animals.
import java.util.Scanner;      // Used for reading user input from the console.
import java.util.Set;          // Defines an unordered collection with unique elements (interface for HashSet).
/**
 *
 * @author gustavobrito22icloud.com
 */
public class ZooAnimalApp {
    private final List<Animal> animals; // List to store all the animals in the zoo
 
    // Array of valid habitats to compare users entries.
    private static final String[] VALID_HABITATS = { // "final" key word 
        "Rainforest",  "Savanna", "Desert", "Ocean", "Arctic", "Mountain",
        "Grass", "Jungle", "Freshwater"
    };
    
    // Convert the array into a Set for fast lookup.
    private static final Set<String> VALID_HABITATS_SET = new HashSet<>(Arrays.asList(VALID_HABITATS));

    // Constructor to initialize the zoo (animals list).
    public ZooAnimalApp() {
        animals = new ArrayList<>(); // Creates an empty ArrayList to store animal objects.
    }   

    // Menu to display options and handle user input
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in); // New Scanner object to read file.
        int choice = 0; // int variable created to store users choice.
        
        do { // do loop make sure to run at least once and display menu while user does not select "3 - Quit". 
            // Print the menu options
            System.out.println("\n=== Zoo Animal App ===");
            System.out.println("1 - Data Input (Read file and add animals)");
            System.out.println("2 - Find and Display Animals");
            System.out.println("3 - Quit");
            System.out.print("Enter your choice using number: ");
            
            // Validate input to ensure it's an integer.
            if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number between 1 and 3.");
            scanner.next(); // Consume the invalid input
            continue; // Restart the loop.
            }
            
            // Read user input.
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character.
            
            // Validade input is between 1 and 3.
            if( choice < 1 || choice > 3) {
                System.out.println("Invalid choice! Please enter a number between 1 and 3.");
                continue; // Restart the loop.
            }

            // Handle menu choices.
            switch (choice) {  // Takes the users choice as parameter for the case.
                case 1:
                    System.out.print("Enter the filename path to read data from: ");
                    String filename = scanner.nextLine(); // Stores filepath in variable filename.
                    loadAnimalsFromFile(filename); // Call method to read file. 
                    break;

                case 2:
                    findAndDisplayAnimals(scanner); // Calls method to display animals.
                    break;

                case 3:
                    System.out.println("Exiting the program. Goodbye!"); // Exit program. 
                    break;

                default:
                    System.out.println("Invalid choice! Please try again."); // Deafult message for ivalid choice. 
            }
        } while (choice != 3); // Repeat until the user selects "Quit". 
    }

    // Method to read and validade animals data from a file.
    public void loadAnimalsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line; //Stores current read line.
            while ((line = br.readLine()) != null) { // while line is not empty, keep reading.
                
                // Spliting first line: Type, Species, Name
                String[] firstLine = line.split(","); // Method to split line by comma and store elements in array. 
                // Check is first line contains three elements, Type, Species and Name.
                if (firstLine.length != 3) { 
                    System.out.println("Invalid format for Type, Species, Name: " + line + ". It must be three elements separeted by \",\"(comma).");
                    skipInvalidAnimal(br, 3); // Skip the remaining lines for the the invalid animal 
                    continue; // Skip this animal  
                }

                String type = firstLine[0]; // Stores Type 
                String species = firstLine[1]; // Stores Species
                String name = firstLine[2]; // Stores Name 
                
                
                // Validate type (must contain only letters and spaces)
                if (!type.matches("^[A-Za-z ]+$")) { 
                System.out.println("Invalid animal type: " + type + ". Animal Type must be only letters."); // Error message
                skipInvalidAnimal(br, 3);// Skip the remaining lines for the the invalid animal
                continue; 
                }
        
                // Validate species (must contain only letters and spaces)
                if (!species.matches("^[A-Za-z ]+$")) {
                System.out.println("Invalid species: " + species + ". Species must be only letters."); // Error message
                skipInvalidAnimal(br, 3);
                continue;
                }
        
                // Validate name (can contain letters, numbers, and spaces)
                if (!name.matches("^[A-Za-z0-9 ]+$")) {
                System.out.println("Invalid name: " + name + ". Name must be only letters and numbers."); // Error message
                skipInvalidAnimal(br, 3);
                continue; 
                }
                

                // Read second line - Habitat.
                String habitat = br.readLine(); 
                // Checking if habitat is not null or empty, if it is a string and if it maches with valid habitat list.
                if(habitat == null || habitat.trim().isEmpty() || !habitat.matches("^[A-Za-z ]+$") || !VALID_HABITATS_SET.contains(habitat)){ 
                    System.out.println("Invalid habitat: " + habitat +". It must not be empty, be letters and matchs valid habitat list: Rainforest, Savanna, Desert, Ocean, Arctic, Mountain, Wetlands, Grass, Jungle, Freshwater");
                    skipInvalidAnimal(br, 2); // Skip the remaining lines of the invalid animal
                    continue;
                }
                 
                // Read third line - Date of Birth and Weight from next line. 
                line = br.readLine(); //Read line and stores it in "variable line".
                String[] thirdLine = line.split(","); // Method to split line by comma and store elements in array. 
                
                if (thirdLine.length != 2) { // Check for valid format, two elements
                System.out.println("Invalid format for DoB and weight: " + line + ". It must be: yyyy/mm/dd, 00.0"); // Errro message.
                skipInvalidAnimal(br, 1); // Skip the remaining lines of the invalid animal.
                continue; // Skip this animal.
                }

                String dob = thirdLine[0]; // Stores Date of birth.
                String weightString = thirdLine[1]; // Stores weight as string.
                
                // Validate Date of Birth (must match yyyy/MM/dd format).
                if (!dob.matches("^\\d{4}/\\d{2}/\\d{2}$")) { // Check format yyyy/MM/dd.
                 System.out.println("Invalid Date of Birth: " + dob + "Date of Birth must match yyyy/MM/dd format"); // Error message. 
                continue;
                }
                
                // Parse and validate weight, must be positive number.              
                double weight = Double.parseDouble(weightString); // Convert weight to double
                if (weight <= 0) { // check weight is less or equal to zero
                System.out.println("Invalid weight: " + weightString + ". Must be a positive number."); // Error message.
                skipInvalidAnimal(br, 1); // Skip the remaining lines of the invalid animal 
                continue; 
                }     

                // Read fourth line - Characteristics.
                String characteristics = br.readLine();
                if (characteristics == null || characteristics.trim().isEmpty()|| !characteristics.matches("^[A-Za-z0-9, ]+$")) {
                System.out.println("Invalid characteristics: " + characteristics + "; for: " + name + ". It must be not empty and contains only letters, numbers, spaces, and commas.");
                // Skip no lines as this is the last expected line for the current animal.
                continue; // Skip this animal.
                }

                // Create animal object and add to the zoo list.
                Animal animal = createAnimal(type, species, name, habitat, dob, weight, characteristics); // Create object. 
                if (animal != null) { 
                    addAnimal(animal); // Adding object to the list.
                }
            }
        } catch (IOException e) { // catch exception. 
            System.out.println("Error reading file: " + e.getMessage()); // Error message for user.
        }
    }

    // Method to add an animal to the zoo.
    public void addAnimal(Animal animal) {
        animals.add(animal);  // Adds object to the list of animals.
        System.out.println(animal.getName() + " has been added to the zoo!"); // Prints confirmation message.
    }

    // Helper method to skip invalid animal data.
    private static void skipInvalidAnimal(BufferedReader br, int linesToSkip) throws IOException {
        for (int i = 0; i < linesToSkip; i++) { // Loop to skip the given number of lines.
            br.readLine(); // Reads and discards the current line.
        }
    }

    // Factory method to create an animal object based on aninal type (mammal, bird, reptile, fish).
    private Animal createAnimal(String type, String species, String name, String habitat, String dob, double weight, String characteristics) {
        switch (type.toLowerCase()) { // Convert type to lowercase for comparison.
            case "mammal":
                return new Mammal(type, species, name, habitat, dob, weight, characteristics); // Create and return Mammal object.
            case "bird":
                return new Bird(type, species, name, habitat, dob, weight, characteristics); // Create and return Bird object
            case "reptile":
                return new Reptile(type, species, name, habitat, dob, weight, characteristics); // Create and return Reptile object.
            case "fish":
                return new Fish(type, species, name, habitat, dob, weight, characteristics); // Create and return Fish object.
            default:
                System.out.println("Unknown animal type: " + type); // Print error message if type is unrecognized.
                return null; // Return null for invalid types.
        }
    }

    // Method to find and display animals by criteria.
    public void findAndDisplayAnimals(Scanner scanner) {
     while (true) {
        // Print Menu choices. 
        System.out.println("\nFind Animals By:");
        System.out.println("1 - Type");
        System.out.println("2 - Habitat");
        System.out.println("3 - Name");
        System.out.println("4 - Species");
        System.out.print("Enter your choice using number: ");
        
        // Handle incorrect input to prevent input mismatch.
        if (!scanner.hasNextInt()) {
        System.out.println("Invalid input! Please enter a number between 1 and 4.");
        scanner.next(); // Consume the invalid input.
        continue; // Exit the method to prevent further errors.
        }
        
        int choice = scanner.nextInt(); // Variable to store users choice from input.
        
        scanner.nextLine(); // Consume newline.
        
        // Validate choice.
        if (choice < 1 || choice > 4) {
        System.out.println("Invalid choice! Please enter a number between 1 and 4.");
        continue;
        }
        
        System.out.print("Enter the search term. (eg. \"mammal\" for type, \"grass\" for habitat, \"dog\" for species or name):"); // Print message to enter. 
        String searchTerm = scanner.nextLine();

        // Filter animals based on criteria and collect animals into a new list to display them or print message for animal not found.
        List<Animal> foundAnimals = animals.stream()
                .filter(animal -> { // Filter animals based on search criteria.
                    switch (choice) { // Takes the users choice as parameter for the case.
                        case 1: return animal.getType().equalsIgnoreCase(searchTerm); // Search by type.
                        case 2: return animal.getHabitat().equalsIgnoreCase(searchTerm); // Search by habitat.
                        case 3: return animal.getName().equalsIgnoreCase(searchTerm); // Search by name.
                        case 4: return animal.getSpecies().equalsIgnoreCase(searchTerm); // Search by species.
                        default: return false; // If the choice is invalid, return false (no matches).                      
                    }
               }).toList(); // Put results into a list.
        
            if (foundAnimals.isEmpty()) {
               System.out.println("No animals found matching your search."); // Message for animal not found.
            } else {
               foundAnimals.forEach(System.out::println);
            }
            
        // Implementation to ask if user wants to search again or return to main menu.
        System.out.print("Would you like to search for another animal? (yes/no): "); // Ask user for "yes" or "no".
        String response = scanner.nextLine().trim().toLowerCase(); // Stores users input in lowercase for better check.
        if (!response.equals("yes")) {
            break; // Exit the loop and return to the main menu.
        }
      }
    }

    // Main method to run the program.
    public static void main(String[] args) {
        ZooAnimalApp app = new ZooAnimalApp(); // Create an instance of the ZooAnimalApp.
        app.displayMenu(); // Start the application by displaying the main menu.
    }
}