package petstore.inventory;

/**
 * @author Your Group Member Names Here
 * @since March 26, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/yourusername/petstoreapp">GitHub Repository</a>
 */
public class Fish extends Pet {
    private WaterType waterType;
    private FeedingSchedule feedingSchedule;

    public Fish(String name, String dateAcquired, double price, WaterType waterType, FeedingSchedule feedingSchedule) throws Exception {
        super(name, dateAcquired, price, HabitatType.AQUARIUM);
        setWaterType(waterType);
        setFeedingSchedule(feedingSchedule);
    }

    public Fish(int id, String name, String dateAcquired, double price, WaterType waterType, FeedingSchedule feedingSchedule) throws Exception {
        super(id, name, dateAcquired, price, HabitatType.AQUARIUM);
        setWaterType(waterType);
        setFeedingSchedule(feedingSchedule);
    }

    public WaterType getWaterType() { return waterType; }

    public void setWaterType(WaterType waterType) { this.waterType = waterType; }

    public FeedingSchedule getFeedingSchedule() { return feedingSchedule; }

    public void setFeedingSchedule(FeedingSchedule feedingSchedule) { this.feedingSchedule = feedingSchedule; }

    @Override
    public void displayPet() {
        super.displayPet();
        System.out.printf(" %-12s %-15s\n", waterType, feedingSchedule);
    }
}