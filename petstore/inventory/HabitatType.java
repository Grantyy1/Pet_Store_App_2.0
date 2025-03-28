package petstore.inventory;

/**
 * Enumeration of possible habitat types for pets in the store.
 * Defines the different environments where pets can live.
 * @author Grant Peverett
 * @since March 28, 2025
 * @version 1.0 beta
 * @see <a href="https://github.com/Grantyy1/Pet_Store_App_2.0/blob/main/petstore/inventory/HabitatType.java">GitHub Repository</a>
 */
public enum HabitatType {
    /**
     * For birds and other pets requiring cages
     */
    CAGE,
    /**
     * For fish and aquatic pets
     */
    AQUARIUM,
    /**
     * For reptiles, amphibians, and certain insects
     */
    TERRARIUM,
    /**
     * For pets that don't require enclosures
     */
    OPEN_SPACE
}