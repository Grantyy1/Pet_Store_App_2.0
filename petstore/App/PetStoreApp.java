package petstore.App;

import petstore.inventory.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Your Group Member Names Here
 * @since March 26, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/yourusername/petstoreapp">GitHub Repository</a>
 */
public class PetStoreApp {

    private static final String INVENTORY_FILE = "PetStoreData.txt";

    private static final String DOUBLE_DASH_LINE = String.format("%65s", "").replace(' ', '=');
    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');

    private final List<Pet> inventory;

    public PetStoreApp() {
        this.inventory = new ArrayList<>();
    } // end of constructor

    private void displayAppHeading() {
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Pet Store App");
        System.out.println(DOUBLE_DASH_LINE);
    } // end of displayAppHeading method

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