import java.util.List;

/**
 * Model for managing hotel selection operations within the hotel reservation system.
 */
public class SelectHotelModel {
    private final HotelReservationSystem hrs; // The hotel reservation system instance

    /**
     * Constructs a new SelectHotelModel with the specified hotel reservation system.
     *
     * @param hrs The hotel reservation system to be associated with this model.
     */
    public SelectHotelModel(HotelReservationSystem hrs) {
        this.hrs = hrs;
    }

    /**
     * Gets the hotel reservation system associated with this model.
     *
     * @return The hotel reservation system.
     */
    public HotelReservationSystem getHrs() {
        return hrs;
    }

    /**
     * Retrieves the list of hotels managed by the hotel reservation system.
     *
     * @return A list of hotels.
     */
    public List<Hotel> getHotels() {
        return hrs.getHotelList(); // Return the list of hotels from the reservation system
    }

    /**
     * Gets the number of operating hotels currently available in the system.
     *
     * @return The number of operating hotels.
     */
    public int getNumberOfOperatingHotels() {
        return hrs.getHotelList().size(); // Return the count of hotels in the list
    }

    /**
     * Checks if there are any hotels available in the system.
     *
     * @return true if there are hotels available, false otherwise.
     */
    public boolean hasHotels() {
        return !getHotels().isEmpty(); // Return true if the hotel list is not empty
    }
}
