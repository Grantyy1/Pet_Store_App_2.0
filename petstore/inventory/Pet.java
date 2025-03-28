package petstore.inventory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Base class for all pets in the pet store inventory system.
 * Provides common properties and behaviors for all pet types.
 * @author Grant Peverett
 * @since March 28, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/Grantyy1/Pet_Store_App_2.0/blob/main/petstore/inventory/Pet.java">GitHub Repository</a>
 */
public class Pet {

    /**
     * Tracks the last assigned ID to ensure unique IDs for each pet.
     */
    private static int lastId = 0;
    /**
     * Date formatter for consistent date string representation.
     * */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    /**
     * Unique identifier for each pet.
     */
    protected final int id;
    /**
     * Name of the pet.
     */
    protected String name;
    /**
     * Date when the pet was acquired by the store.
     */
    protected LocalDate dateAcquired;
    /**
     * Retail price of the pet in dollars
     */
    protected double price;
    /**
     * Type of habitat required for the pet
     */
    protected HabitatType habitat;
    /**
     * Additional description or notes about the pet
     */
    protected String description;

    /**
     * Constructor for creating a new pet with auto-generated ID.
     * @param name The name of the pet.
     * @param dateAcquired The date when the pet was acquired (MM-dd-yyyy format).
     * @param price The retail price of the pet.
     * @param habitat The type of habitat required for the pet.
     * @throws Exception If any validation errors occur during object creation.
     */
    public Pet(String name, String dateAcquired, double price, HabitatType habitat) throws Exception {
        this.id = ++Pet.lastId;
        setName(name);
        setDateAcquired(dateAcquired);
        setPrice(price);
        setHabitat(habitat);
    }

    /**
     * Constructor for creating a pet with a specific ID (used when loading from file).
     * @param id The specific ID to assign to this pet.
     * @param name The name of the pet.
     * @param dateAcquired The date when the pet was acquired (MM-dd-yyyy format).
     * @param price The retail price of the pet.
     * @param habitat The type of habitat required for the pet.
     * @throws Exception If any validation errors occur during object creation.
     */
    public Pet(int id, String name, String dateAcquired, double price, HabitatType habitat) throws Exception {
        this.id = id;
        setName(name);
        setDateAcquired(dateAcquired);
        setPrice(price);
        setHabitat(habitat);
    }

    /**
     * Sets the static lastId counter when loading pets from file.
     * to ensure new pets get unique IDs.
     * @param lastId The highest ID found in the loaded inventory.
     */
    public static void setLastId(int lastId) { Pet.lastId = lastId; }
    /**
     * Gets the unique identifier of this pet.
     * @return The pet's ID.
     */
    public int getId() { return id; }
    /**
     * Gets the name of the pet.
     * @return The pet's name.
     */
    public String getName() { return name; }

    /**
     * Sets the name of the pet with validation.
     * @param name The name to set.
     * @throws Exception If the name is blank or empty.
     */
    public void setName(String name) throws Exception {
        name = name.trim();

        if (name.isBlank()) {
            throw new Exception("Invalid! Name cannot be empty.");
        }

        this.name = name;
    }

    /**
     * Gets the date the pet was acquired in formatted string form.
     * @return The formatted date string (MM-dd-yyyy).
     */
    public String getDateAcquired() {
        return dateAcquired.format(Pet.formatter);
    }

    /**
     * Sets the acquired date from a string with validation.
     * @param dateAcquired The date string in MM-dd-yyyy format.
     * @throws Exception If the date format is invalid.
     */
    public void setDateAcquired(String dateAcquired) throws Exception {
        try {
            this.dateAcquired = LocalDate.parse(dateAcquired, Pet.formatter);
        } catch (Exception e) {
            throw new Exception("Invalid date! Must be MM-DD-YYYY");
        }
    }

    /**
     * Gets the retail price of the pet.
     * @return The pet's price.
     */
    public double getPrice() { return price; }

    /**
     * Sets the retail price with validation.
     * @param price The price to set.
     * @throws Exception If the price is negative.
     */
    public void setPrice(double price) throws Exception {
        if (price < 0) {
            throw new Exception("Invalid price! Price cannot be negative.");
        }
        this.price = price;
    }

    /**
     * Gets the habitat type required for the pet.
     * @return The pet's habitat type.
     */
    public HabitatType getHabitat() { return habitat; }

    /**
     * Sets the habitat type for the pet.
     * @param habitat The habitat type to set.
     */
    public void setHabitat(HabitatType habitat) { this.habitat = habitat; }

    /**
     * Gets the additional description of the pet.
     * @return The pet's description.
     */
    public String getDescription() { return description; }

    /**
     * Sets the description of the pet.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description != null ? description.trim() : "";
    }

    /**
     * Displays the pet's details in a formatted table row.
     * This method is meant to be extended by subclasses.
     */
    public void displayPet() {
        System.out.printf("%3d %-15s %10s $%-8.2f %-12s", id, name, getDateAcquired(), price, habitat);
    }

    /**
     * Returns a string representation of the pet.
     * @return A string with the pet's basic information.
     */
    @Override
    public String toString() {
        return id + " " + name + " " + getDateAcquired() + " $" + price + " " + habitat;
    }
}