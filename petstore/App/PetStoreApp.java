
package petstore.App;

import petstore.inventory.FeedingSchedule;
import petstore.inventory.HabitatType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author Grant Peverett
 *  @since 3/26/2025
 *  @version 1.0 beta
 *  @see <a href="{https://github.com/Grantyy1/Pet_Store_App_2.0/blob/main/petstore/App/Input.java}">GitHub Repository</a>
 *
 */
public class PetStoreApp {

    private static final String INVENTORY_FILE = "LibraryData.txt";
    
    private static final String DOUBLE_DASH_LINE = String.format("%50s", "").replace(' ', '=');

    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');

    private final List<petstore.inventory.Bird> inventory;

    public PetStoreApp(){
        this.inventory = new ArrayList<>();
    } // end of constructor

    private void displayAppHeading() {

        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Library App");
        System.out.println(DOUBLE_DASH_LINE);

    } // end of displayAppHeading method

    private void deleteItem(){
        System.out.println("Delete Inventory");
        System.out.println(SINGLE_DASH_LINE);

        int id = Input.getInt("Please enter the inventory id: ");

        for (petstore.inventory.Bird item : inventory){
            System.out.println(id);
            if (item.getId() == id){
                inventory.remove(item);
                System.out.println("Successful Delete: " + item);
                Input.getLine("Press enter to continue...");
                return;
            }
        }

        System.out.println("ERROR: Inventory ID:" + id + " NOT found!");

    } // end of deleteItem method

    private petstore.inventory.Fish addBook(String title, String dateReceived, String description) throws Exception {

        petstore.inventory.Fish book;
        int userInput;
        String author;
        FeedingSchedule genre = null;

        author = Input.getString("Author: ");

        try {
            userInput = Input.getIntRange("Genre 1=Fiction, 2=Children, 3=Poetry: ", 1, 3);
            genre = FeedingSchedule.values()[userInput - 1];
        } catch (Exception e){
            throw new Exception("Invalid data! Book Genre = " + genre);
        }

        book = new petstore.inventory.Fish(title, dateReceived, author, genre);
        book.setDescription(description);

        return book;
    } // end of addBook method

    private WaterType addPeriodical(String title, String dateReceived, String description) throws Exception {

        WaterType periodical;
        String publisher;
        HabitatType category = null;

        publisher = Input.getString("Publisher: ");

        try {
            int userInput = Input.getIntRange("Category 1=Magazine, 2=Journal, 3=Newspaper: ", 1, 3);
            category = HabitatType.values()[userInput - 1];
        } catch (Exception e){
            throw new Exception("Invalid data! Periodical Category = " + category);
        }

        periodical = new WaterType(title, dateReceived, publisher, category);
        periodical.setDescription(description);

        return periodical;
    } // end of addPeriodical method

    private void addItem() throws Exception {

        System.out.println("Add Inventory");
        System.out.println(SINGLE_DASH_LINE);

        System.out.println("Please enter the following inventory information:");
        String title = Input.getString("Title: ");
        String dateReceived = Input.getDate("Date Received (MM-DD-YYYY): ");
        String description = Input.getLine("Description or press enter to continue: ");

        int inventoryType = Input.getIntRange("Type 1=Book, 2=Periodical: ", 1, 2);

        switch(inventoryType){
            case 1:
                petstore.inventory.Fish b = addBook(title, dateReceived, description);
                inventory.add(b);
                System.out.println("Successful Add: " + b);
                Input.getLine("Press enter to continue...");
                break;
            case 2:
                WaterType p = addPeriodical(title, dateReceived, description);
                inventory.add(p);
                System.out.println("Successful Add: " + p);
                Input.getLine("Press enter to continue...");
                break;
            case 3:
                break;
            default:
                throw new Exception("Invalid Input! Inventory Type = " + inventoryType);
        } // end of switch

    } // end of addItem method

    private void displayInventory(){
        System.out.println("Book Inventory");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("ID  Title           Date Rec'd Author          Genre");
        System.out.println("--- --------------- ---------- --------------- ----------");
        for (petstore.inventory.Bird item : inventory) {
            if (item instanceof petstore.inventory.Fish){
                item.displayItem();
            }
        }
        System.out.println();

        System.out.println("Periodical Inventory");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("ID  Title           Date Rec'd Publisher       Category");
        System.out.println("--- --------------- ---------- --------------- ----------");
        for (petstore.inventory.Bird item : inventory) {
            if (item instanceof WaterType){
                item.displayItem();
            }
        }
        System.out.println();

        Input.getLine("Press enter to continue...");
    } // end of displayInventory

    public void saveInventory(){
        System.out.println("Saving data! Please wait...");

        /*
        Try-With-Resources: shortcut way to declare and initialize in one step
        when you use this way of opening the file as part of the try statement
        Java will automatically close the file so there is no need to write a close statement
        NOTE: Java doesn't automatically close the file if the file is opened inside the block
        */
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(library.app.Pet.INVENTORY_FILE))) {

            for(petstore.inventory.Bird item : inventory){

                // The file will be piped delimited so each field is separated by a |
                if (item instanceof petstore.inventory.Fish)
                    bw.write("BOOK|");
                else if (item instanceof WaterType)
                    bw.write("PERIODICAL|");

                bw.write(item.getId() + "|" + item.getTitle() + "|" + item.getDateReceived() + "|" + item.getDescription() + "|");

                if (item instanceof petstore.inventory.Fish)
                    bw.write(((petstore.inventory.Fish) item).getAuthor() + "|" + ((petstore.inventory.Fish) item).getGenre() + "\n");
                else if (item instanceof WaterType)
                    bw.write(((WaterType) item).getPublisher() + "|" + ((WaterType) item).getCategory() + "\n");
            }

            bw.flush();
            // No explicit close needed - automatically handled when using Try-With-Resources

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(inventory.size() + " Inventory records successfully written to " + library.app.Pet.INVENTORY_FILE);
        Input.getLine("Please any key to continue...");

    }

    public void loadInventory(){
        System.out.println("Loading data! Please wait...");

        inventory.clear(); //empty the ArrayList so we can load the data from the file

        /*
        Try-With-Resources: shortcut way to declare and initialize in one step
        when you use this way of opening the file as part of the try statement
        Java will automatically close the file so there is no need to write a close statement
        NOTE: Java doesn't automatically close the file if the file is opened inside the block
        */
        try (BufferedReader br = new BufferedReader(new FileReader(library.app.Pet.INVENTORY_FILE))) {

            String inLine;

            while ((inLine = br.readLine()) != null) {  // exclude newline

                String[] data = inLine.split("[|]"); // [|] is a regex for splitting by the pipe character

                //0=item 1=id, 2=title, 3=date, 4=description, 5=author/publisher, 6=genre/category
                switch(data[0]){
                    case "BOOK":
                        petstore.inventory.Fish b = new petstore.inventory.Fish(Integer.parseInt(data[1]), data[2], data[3], data[5], FeedingSchedule.valueOf(data[6]));
                        b.setDescription(data[4]);
                        inventory.add(b);
                        break;
                    case "PERIODICAL":
                        WaterType p = new WaterType(Integer.parseInt(data[1]), data[2], data[3], data[5], HabitatType.valueOf(data[6]));
                        p.setDescription(data[4]);
                        inventory.add(p);
                        break;
                    default:
                        throw new Exception("Invalid inventory type: " + data[0]);
                } // end of switch

            } // end of while loop

            // No explicit close needed - automatically handled when using Try-With-Resources

            petstore.inventory.Bird.setLastId(inventory.get(inventory.size() - 1).getId());

        } catch (Exception e) {
            e.getMessage();
        } // end of try-catch

        System.out.println(inventory.size() + " Inventory records successfully loaded from " + library.app.Pet.INVENTORY_FILE);
        Input.getLine("Please any key to continue...");
    } // end of loadInventory method

    private void mainMenu() throws Exception {

        boolean keepRunning = true;

        while (keepRunning) {

            System.out.println(SINGLE_DASH_LINE);
            System.out.println("Main Menu");
            System.out.println(SINGLE_DASH_LINE);

            System.out.println("0 = End Program");
            System.out.println("1 = Add Item");
            System.out.println("2 = Delete Item");
            System.out.println("3 = Display Inventory");
            System.out.println("4 = Save Inventory");
            System.out.println("5 = Load Inventory");

            System.out.println(SINGLE_DASH_LINE);
            int userInput = Input.getIntRange("Menu Choice: ", 0, 5);
            System.out.println(SINGLE_DASH_LINE);

            switch (userInput) {
                case 0:
                    keepRunning = false;
                    break;
                case 1:
                    try {
                        this.addItem();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        Input.getLine("Press enter to continue...");
                    }
                    break;
                case 2:
                    try {
                        this.deleteItem();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        Input.getLine("Press enter to continue...");
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

        library.app.Pet app = new library.app.Pet();

        app.displayAppHeading();

        try {
            app.mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Sorry but this program ended with an error. Please contact Princess Debbie!");
        }

        Input.sc.close();

    } // end of main method

} // end of LibraryApp class