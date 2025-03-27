package petstore.inventory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Your Group Member Names Here
 * @since March 26, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/yourusername/petstoreapp">GitHub Repository</a>
 */
public class Pet {

    private static int lastId = 0;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    protected final int id;
    protected String name;
    protected LocalDate dateAcquired;
    protected double price;
    protected HabitatType habitat;
    protected String description;

    public Pet(String name, String dateAcquired, double price, HabitatType habitat) throws Exception {
        this.id = ++Pet.lastId;
        setName(name);
        setDateAcquired(dateAcquired);
        setPrice(price);
        setHabitat(habitat);
    }

    public Pet(int id, String name, String dateAcquired, double price, HabitatType habitat) throws Exception {
        this.id = id;
        setName(name);
        setDateAcquired(dateAcquired);
        setPrice(price);
        setHabitat(habitat);
    }

    public static void setLastId(int lastId) { Pet.lastId = lastId; }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) throws Exception {
        name = name.trim();

        if (name.isBlank()) {
            throw new Exception("Invalid! Name cannot be empty.");
        }

        this.name = name;
    }

    public String getDateAcquired() {
        return dateAcquired.format(Pet.formatter);
    }

    public void setDateAcquired(String dateAcquired) throws Exception {
        try {
            this.dateAcquired = LocalDate.parse(dateAcquired, Pet.formatter);
        } catch (Exception e) {
            throw new Exception("Invalid date! Must be MM-DD-YYYY");
        }
    }

    public double getPrice() { return price; }

    public void setPrice(double price) throws Exception {
        if (price < 0) {
            throw new Exception("Invalid price! Price cannot be negative.");
        }
        this.price = price;
    }

    public HabitatType getHabitat() { return habitat; }

    public void setHabitat(HabitatType habitat) { this.habitat = habitat; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description != null ? description.trim() : "";
    }

    public void displayPet() {
        System.out.printf("%3d %-15s %10s $%-8.2f %-12s", id, name, getDateAcquired(), price, habitat);
    }

    @Override
    public String toString() {
        return id + " " + name + " " + getDateAcquired() + " $" + price + " " + habitat;
    }
}