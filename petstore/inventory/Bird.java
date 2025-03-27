package petstore.inventory;

/**
 * @author Your Group Member Names Here
 * @since March 26, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/yourusername/petstoreapp">GitHub Repository</a>
 */
public class Bird extends Pet {
    private boolean canFly;
    private FeedingSchedule feedingSchedule;

    public Bird(String name, String dateAcquired, double price, boolean canFly, FeedingSchedule feedingSchedule) throws Exception {
        super(name, dateAcquired, price, HabitatType.CAGE);
        setCanFly(canFly);
        setFeedingSchedule(feedingSchedule);
    }

    public Bird(int id, String name, String dateAcquired, double price, boolean canFly, FeedingSchedule feedingSchedule) throws Exception {
        super(id, name, dateAcquired, price, HabitatType.CAGE);
        setCanFly(canFly);
        setFeedingSchedule(feedingSchedule);
    }

    public boolean getCanFly() { return canFly; }

    public void setCanFly(boolean canFly) { this.canFly = canFly; }

    public FeedingSchedule getFeedingSchedule() { return feedingSchedule; }

    public void setFeedingSchedule(FeedingSchedule feedingSchedule) { this.feedingSchedule = feedingSchedule; }

    @Override
    public void displayPet() {
        super.displayPet();
        System.out.printf(" %-12s %-15s\n", canFly ? "Can Fly" : "Cannot Fly", feedingSchedule);
    }
}