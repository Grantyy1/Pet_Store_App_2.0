package petstore.App;

import petstore.inventory.*; //asterisks call all the java classes and enums related to inventory package.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Main application class for the Pet Store inventory management system.
 * Provides functionality for adding, deleting, displaying, saving, and loading pet inventory.
 * @author Grant Peverett
 * @since March 28, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/Grantyy1/Pet_Store_App_2.0/blob/main/petstore/App/PetStoreApp.java">GitHub Repository</a>
 */
public class PetStoreApp {

    /**
     * File name for saving/loading the inventory data.
     */
    private static final String INVENTORY_FILE = "PetStoreData.txt";
    /**
     * Formatted string of equals signs for double-line headers.
     */
    private static final String DOUBLE_DASH_LINE = String.format("%65s", "").replace(' ', '=');
    /**
     * Formatted string of hyphens for single-line headers.
     */
    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');
    /**
     * List to store all pets in the inventory.
     */
    private final List<Pet> inventory;

    /**
     * Initializes an empty inventory list.
     */
    public PetStoreApp() {
        this.inventory = new ArrayList<>();
    } // end of constructor

    /**
     * Displays the application header with formatting.
     */
    private void displayAppHeading() {
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Pet Store App");
        System.out.println(DOUBLE_DASH_LINE);
    } // end of displayAppHeading method

    /**
     * Handles the process of deleting a pet from the inventory.
     * Prompts the user for a pet ID and removes the matching pet if found.
     */
    private void deleteItem() {
        System.out.println("Delete Pet");
        System.out.println(SINGLE_DASH_LINE);

        int id = petstore.App.Input.getInt("Please enter the pet ID: ");

        for (Pet pet : inventory) {
            if (pet.getId() == id) {
                inventory.remove(pet);
                System.out.println("Successfully Deleted: " + pet);
                petstore.App.Input.getLine("Press enter to continue...");
                return;
            }
        }

        System.out.println("ERROR: Pet ID:" + id + " NOT found!");
        petstore.App.Input.getLine("Press enter to continue...");
    } // end of deleteItem method

    /**
     * Creates a new Fish object with user input.
     * @param name The name of the fish.
     * @param dateAcquired The date the fish was acquired.
     * @param price The price of the fish.
     * @param description Additional description of the fish.
     * @return The newly created Fish object.
     * @throws Exception If validation errors occur during object creation.
     */
    private Fish addFish(String name, String dateAcquired, double price, String description) throws Exception {
        Fish fish;
        WaterType waterType = null;
        FeedingSchedule feedingSchedule = null;

        try {
            int userInput = petstore.App.Input.getIntRange("Water Type 1=Freshwater, 2=Saltwater, 3=Brackish: ", 1, 3);
            waterType = WaterType.values()[userInput - 1];
        } catch (Exception e) {
            throw new Exception("Invalid data! Water Type = " + waterType);
        }

        try {
            int userInput = petstore.App.Input.getIntRange("Feeding Schedule 1=Once Daily, 2=Twice Daily, 3=Three Times Daily, 4=Weekly, 5=Biweekly: ", 1, 5);
            feedingSchedule = FeedingSchedule.values()[userInput - 1];
        } catch (Exception e) {
            throw new Exception("Invalid data! Feeding Schedule = " + feedingSchedule);
        }

        fish = new Fish(name, dateAcquired, price, waterType, feedingSchedule);
        fish.setDescription(description);

        return fish;
    } // end of addFish method

    /**
     * Creates a new Bird object with user input.
     * @param name The name of the bird.
     * @param dateAcquired The date the bird was acquired.
     * @param price The price of the bird.
     * @param description Additional description of the bird.
     * @return The newly created Bird object.
     * @throws Exception If validation errors occur during object creation.
     */
    private Bird addBird(String name, String dateAcquired, double price, String description) throws Exception {
        Bird bird;
        boolean canFly;
        FeedingSchedule feedingSchedule = null;

        canFly = petstore.App.Input.getBoolean("Can the bird fly? (yes/no): ");

        try {
            int userInput = petstore.App.Input.getIntRange("Feeding Schedule 1=Once Daily, 2=Twice Daily, 3=Three Times Daily, 4=Weekly, 5=Biweekly: ", 1, 5);
            feedingSchedule = FeedingSchedule.values()[userInput - 1];
        } catch (Exception e) {
            throw new Exception("Invalid data! Feeding Schedule = " + feedingSchedule);
        }

        bird = new Bird(name, dateAcquired, price, canFly, feedingSchedule);
        bird.setDescription(description);

        return bird;
    } // end of addBird method

    /**
     * Handles the process of adding a new pet to the inventory.
     * Collects common pet information, then specific information based on the type of pet being added.
     * @throws Exception If validation errors occur during the process.
     */
    private void addItem() throws Exception {
        System.out.println("Add Pet");
        System.out.println(SINGLE_DASH_LINE);

        System.out.println("Please enter the following pet information:");
        String name = petstore.App.Input.getString("Name: ");
        String dateAcquired = petstore.App.Input.getDate("Date Acquired (MM-DD-YYYY): ");
        double price = petstore.App.Input.getDouble("Price: $");
        String description = petstore.App.Input.getLine("Description or press enter to continue: ");

        int petType = petstore.App.Input.getIntRange("Type 1=Fish, 2=Bird: ", 1, 2);

        switch (petType) {
            case 1:
                Fish f = addFish(name, dateAcquired, price, description);
                inventory.add(f);
                System.out.println("Successfully Added: " + f);
                petstore.App.Input.getLine("Press enter to continue...");
                break;
            case 2:
                Bird b = addBird(name, dateAcquired, price, description);
                inventory.add(b);
                System.out.println("Successfully Added: " + b);
                petstore.App.Input.getLine("Press enter to continue...");
                break;
            default:
                throw new Exception("Invalid Input! Pet Type = " + petType);
        } // end of switch
    } // end of addItem method

    /**
     * Displays all pets in the inventory, categorized by type.
     * Formats the output in a tabular format for readability.
     */
    private void displayInventory() {
        System.out.println("Fish Inventory");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("ID  Name            Date Acq'd  Price     Habitat      Water Type    Feeding");
        System.out.println("--- --------------- ---------- --------- ------------ ------------- ---------------");
        for (Pet pet : inventory) {
            if (pet instanceof Fish) {
                pet.displayPet();
            }
        }
        System.out.println();

        System.out.println("Bird Inventory");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("ID  Name            Date Acq'd  Price     Habitat      Flight        Feeding");
        System.out.println("--- --------------- ---------- --------- ------------ ------------- ---------------");
        for (Pet pet : inventory) {
            if (pet instanceof Bird) {
                pet.displayPet();
            }
        }
        System.out.println();

        petstore.App.Input.getLine("Press enter to continue...");
    } // end of displayInventory

    /**
     * Saves the current inventory to a text file in a pipe-delimited format.
     * Each pet is saved with its type and all relevant properties.
     */
    public void saveInventory() {
        System.out.println("Saving data! Please wait...");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PetStoreApp.INVENTORY_FILE))) {
            for (Pet pet : inventory) {
                // The file will be piped delimited so each field is separated by a |
                if (pet instanceof Fish)
                    bw.write("FISH|");
                else if (pet instanceof Bird)
                    bw.write("BIRD|");

                bw.write(pet.getId() + "|" + pet.getName() + "|" + pet.getDateAcquired() + "|" +
                        pet.getPrice() + "|" + pet.getHabitat() + "|" + pet.getDescription() + "|");

                if (pet instanceof Fish)
                    bw.write(((Fish) pet).getWaterType() + "|" + ((Fish) pet).getFeedingSchedule() + "\n");
                else if (pet instanceof Bird)
                    bw.write(((Bird) pet).getCanFly() + "|" + ((Bird) pet).getFeedingSchedule() + "\n");
            }

            bw.flush();
            // No explicit close needed - automatically handled when using Try-With-Resources

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(inventory.size() + " Pet records successfully written to " + PetStoreApp.INVENTORY_FILE);
        petstore.App.Input.getLine("Press any key to continue...");
    } // end of saveInventory

    /**
     * Loads the inventory from a text file.
     * Clears the current inventory and replaces it with the data from the file.
     * The file should be in the pipe-delimited format created by saveInventory().
     */
    public void loadInventory() {
        System.out.println("Loading data! Please wait...");

        inventory.clear(); // empty the ArrayList so we can load the data from the file

        try (BufferedReader br = new BufferedReader(new FileReader(PetStoreApp.INVENTORY_FILE))) {
            String inLine;

            while ((inLine = br.readLine()) != null) {  // exclude newline
                String[] data = inLine.split("[|]"); // [|] is a regex for splitting by the pipe character

                // 0=type, 1=id, 2=name, 3=date, 4=price, 5=habitat, 6=description, 7=specific1, 8=specific2
                switch (data[0]) {
                    case "FISH":
                        Fish f = new Fish(
                                Integer.parseInt(data[1]),
                                data[2],
                                data[3],
                                Double.parseDouble(data[4]),
                                WaterType.valueOf(data[7]),
                                FeedingSchedule.valueOf(data[8])
                        );
                        f.setDescription(data[6]);
                        inventory.add(f);
                        break;
                    case "BIRD":
                        Bird b = new Bird(
                                Integer.parseInt(data[1]),
                                data[2],
                                data[3],
                                Double.parseDouble(data[4]),
                                Boolean.parseBoolean(data[7]),
                                FeedingSchedule.valueOf(data[8])
                        );
                        b.setDescription(data[6]);
                        inventory.add(b);
                        break;
                    default:
                        throw new Exception("Invalid pet type: " + data[0]);
                } // end of switch
            } // end of while loop

            // No explicit close needed - automatically handled when using Try-With-Resources

            if (!inventory.isEmpty()) {
                Pet.setLastId(inventory.get(inventory.size() - 1).getId());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } // end of try-catch

        System.out.println(inventory.size() + " Pet records successfully loaded from " + PetStoreApp.INVENTORY_FILE);
        petstore.App.Input.getLine("Press any key to continue...");
    } // end of loadInventory method

    /**
     * Displays the main menu and handles user navigation through the application.
     * Provides options for adding, deleting, displaying, saving, and loading inventory.
     * @throws Exception If an error occurs during menu operations.
     */
    private void mainMenu() throws Exception {
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println(SINGLE_DASH_LINE);
            System.out.println("Main Menu");
            System.out.println(SINGLE_DASH_LINE);

            System.out.println("0 = End Program");
            System.out.println("1 = Add Pet");
            System.out.println("2 = Delete Pet");
            System.out.println("3 = Display Inventory");
            System.out.println("4 = Save Inventory");
            System.out.println("5 = Load Inventory");

            System.out.println(SINGLE_DASH_LINE);
            int userInput = petstore.App.Input.getIntRange("Menu Choice: ", 0, 5);
            System.out.println(SINGLE_DASH_LINE);

            switch (userInput) {
                case 0:
                    keepRunning = false;
                    break;
                case 1:
                    try {
                        this.addItem();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        petstore.App.Input.getLine("Press enter to continue...");
                    }
                    break;
                case 2:
                    try {
                        this.deleteItem();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        petstore.App.Input.getLine("Press enter to continue...");
                    }
                    break;
                case 3:
                    displayInventory();
                    break;
                case 4:
                    saveInventory();
                    break;
                case 5:
                    loadInventory();
                    break;
                default:
                    throw new Exception("Invalid menu choice: " + userInput);
            } // end of switch
        } // end of while loop
    } // end of mainMenu

    /**
     * Main method to launch the Pet Store application.
     * Creates an instance of PetStoreApp, displays the heading,
     * and enters the main menu loop.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        PetStoreApp app = new PetStoreApp();

        app.displayAppHeading();

        try {
            app.mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Sorry but this program ended with an error. Please contact support!");
        }

        petstore.App.Input.sc.close();
    } // end of main method
} // end of PetStoreApp class