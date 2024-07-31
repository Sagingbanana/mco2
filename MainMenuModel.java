/**
 * The MainMenuModel class serves as the model for the main menu in the hotel reservation system. 
 * It provides access to hotel data and methods to query the state of the system, such as 
 * the number of operating hotels and availability of rooms.
 */
public class MainMenuModel {
    private final HotelReservationSystem hotelReservationSystem; // The hotel reservation system instance

    /**
     * Constructs a MainMenuModel with the specified hotel reservation system.
     *
     * @param hotelReservationSystem The hotel reservation system to be associated with this model.
     */
    public MainMenuModel(HotelReservationSystem hotelReservationSystem) {
        this.hotelReservationSystem = hotelReservationSystem;
    }

    /**
     * Returns the hotel reservation system associated with this model.
     *
     * @return The hotel reservation system instance.
     */
    public HotelReservationSystem getHotelReservationSystem() {
        return hotelReservationSystem;
    }

    /**
     * Returns the number of currently operating hotels in the system.
     *
     * @return The count of currently operating hotels.
     */
    public int getNumberOfOperatingHotels() {
        // Return the size of the hotel list, indicating the number of hotels
        return hotelReservationSystem.getHotelList().size();
    }

    /**
     * Checks if there are any hotels in the system.
     *
     * @return true if there are no hotels, false otherwise.
     */
    public boolean areThereNoHotels() {
        // Return true if the hotel list is empty, indicating no hotels are present
        return hotelReservationSystem.getHotelList().isEmpty();
    }

    /**
     * Checks if there are any available rooms across all hotels in the system.
     *
     * @return true if there are no available rooms, false otherwise.
     */
    public boolean areThereNoAvailableRooms() {
        // Iterate through the list of hotels to check for available rooms
        for (Hotel hotel : hotelReservationSystem.getHotelList()) {
            // If any hotel has available rooms, return false
            if (hotel.getAvailableRoomsCount() > 0) {
                return false;
            }
        }
        // If no hotels have available rooms, return true
        return true;
    }
}
