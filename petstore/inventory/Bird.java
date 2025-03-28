package petstore.inventory;

/**
 * Represents a bird in the pet store inventory.
 * Extends the base Pet class with bird-specific properties.
 * @author Grant Peverett
 * @since March 28, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/Grantyy1/Pet_Store_App_2.0/blob/main/petstore/inventory/Bird.java">GitHub Repository</a>
 */
public class Bird extends Pet {
    /**
     * Indicates whether the bird can fly.
     */
    private boolean canFly;
    /**
     * The feeding schedule for this bird.
     */
    private FeedingSchedule feedingSchedule;

    /**
     * Creates a new bird with auto-generated ID.
     * @param name The name of the bird.
     * @param dateAcquired The date when the bird was acquired (MM-dd-yyyy format).
     * @param price The retail price of the bird.
     * @param canFly Whether the bird can fly or not.
     * @param feedingSchedule The feeding schedule for the bird.
     * @throws Exception If any validation errors occur during object creation.
     */
    public Bird(String name, String dateAcquired, double price, boolean canFly, FeedingSchedule feedingSchedule) throws Exception {
        super(name, dateAcquired, price, HabitatType.CAGE);
        setCanFly(canFly);
        setFeedingSchedule(feedingSchedule);
    }

    /**
     * Creates a bird with a specific ID (used when loading from file).
     * @param id The specific ID to assign to this bird.
     * @param name The name of the bird.
     * @param dateAcquired The date when the bird was acquired (MM-dd-yyyy format).
     * @param price The retail price of the bird.
     * @param canFly Whether the bird can fly or not.
     * @param feedingSchedule The feeding schedule for the bird.
     * @throws Exception If any validation errors occur during object creation.
     */
    public Bird(int id, String name, String dateAcquired, double price, boolean canFly, FeedingSchedule feedingSchedule) throws Exception {
        super(id, name, dateAcquired, price, HabitatType.CAGE);
        setCanFly(canFly);
        setFeedingSchedule(feedingSchedule);
    }

    /**
     * Gets whether the bird can fly.
     * @return true if the bird can fly, false otherwise.
     */
    public boolean getCanFly() { return canFly; }

    /**
     * Sets whether the bird can fly.
     * @param canFly true if the bird can fly, false otherwise.
     */
    public void setCanFly(boolean canFly) { this.canFly = canFly; }

    /**
     * Gets the feeding schedule for this bird.
     * @return The bird's feeding schedule.
     */
    public FeedingSchedule getFeedingSchedule() { return feedingSchedule; }

    /**
     * Sets the feeding schedule for this bird.
     * @param feedingSchedule The feeding schedule to set.
     */
    public void setFeedingSchedule(FeedingSchedule feedingSchedule) { this.feedingSchedule = feedingSchedule; }

    /**
     * Displays the bird's details in a formatted table row.
     * Extends the base class display method with bird-specific properties.
     */
    @Override
    public void displayPet() {
        super.displayPet();
        System.out.printf(" %-12s %-15s\n", canFly ? "Can Fly" : "Cannot Fly", feedingSchedule);
    }
}