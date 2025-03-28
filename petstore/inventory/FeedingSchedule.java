package petstore.inventory;
/**
 * Defines the frequency at which different pets need to be fed.
 * @author Grant Peverett
 * @since March 28, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/Grantyy1/Pet_Store_App_2.0/blob/main/petstore/inventory/FeedingSchedule.java">GitHub Repository</a>
 */

public enum FeedingSchedule {
    /**
     * Feed once per day.
     */
    ONCE_DAILY,
    /**
     * Feed twice per day.
     */
    TWICE_DAILY,
    /**
     * Feed three times per day.
     */
    THREE_TIMES_DAILY,
    /**
     * Feed once per week.
     */
    WEEKLY,
    /**
     * Feed once every two weeks.
     */
    BIWEEKLY,
}
