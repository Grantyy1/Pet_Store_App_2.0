package petstore.inventory;

/**
 * Represents a fish in the pet store inventory.
 * Extends the base Pet class with fish-specific properties.
 * @author Grant Peverett
 * @since March 28, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/Grantyy1/Pet_Store_App_2.0/blob/main/petstore/inventory/Fish.java">GitHub Repository</a>
 */
public class Fish extends Pet {

    /**
     * The type of water required for this fish.
     */
    private WaterType waterType;
    /**
     * The feeding schedule for this fish.
     */
    private FeedingSchedule feedingSchedule;

    /**
     * Creates a new fish with auto-generated ID.
     * @param name The name of the fish.
     * @param dateAcquired The date when the fish was acquired (MM-dd-yyyy format).
     * @param price The retail price of the fish.
     * @param waterType The type of water required for the fish.
     * @param feedingSchedule The feeding schedule for the fish.
     * @throws Exception If any validation errors occur during object creation.
     */
    public Fish(String name, String dateAcquired, double price, WaterType waterType, FeedingSchedule feedingSchedule) throws Exception {
        super(name, dateAcquired, price, HabitatType.AQUARIUM);
        setWaterType(waterType);
        setFeedingSchedule(feedingSchedule);
    }

    /**
     * Creates a fish with a specific ID (used when loading from file).
     * @param id The specific ID to assign to this fish.
     * @param name The name of the fish.
     * @param dateAcquired The date when the fish was acquired (MM-dd-yyyy format).
     * @param price The retail price of the fish.
     * @param waterType The type of water required for the fish.
     * @param feedingSchedule The feeding schedule for the fish.
     * @throws Exception If any validation errors occur during object creation.
     */
    public Fish(int id, String name, String dateAcquired, double price, WaterType waterType, FeedingSchedule feedingSchedule) throws Exception {
        super(id, name, dateAcquired, price, HabitatType.AQUARIUM);
        setWaterType(waterType);
        setFeedingSchedule(feedingSchedule);
    }

    /**
     * Gets the water type required for this fish.
     * @return The fish's water type.
     */
    public WaterType getWaterType() { return waterType; }

    /**
     * Sets the water type for this fish.
     * @param waterType The water type to set.
     */
    public void setWaterType(WaterType waterType) { this.waterType = waterType; }

    /**
     * Gets the feeding schedule for this fish.
     * @return The fish's feeding schedule.
     */
    public FeedingSchedule getFeedingSchedule() { return feedingSchedule; }

    /**
     * Sets the feeding schedule for this fish.
     * @param feedingSchedule The feeding schedule to set.
     */
    public void setFeedingSchedule(FeedingSchedule feedingSchedule) { this.feedingSchedule = feedingSchedule; }

    /**
     * Displays the fish's details in a formatted table row.
     * Extends the base class display method with fish-specific properties.
     */
    @Override
    public void displayPet() {
        super.displayPet();
        System.out.printf(" %-12s %-15s\n", waterType, feedingSchedule);
    }
}