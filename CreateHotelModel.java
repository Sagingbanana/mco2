/**
 * Model class responsible for managing hotel creation logic in the Hotel Reservation System.
 * It interacts with the HotelReservationSystem to validate and add new hotels.
 */
public class CreateHotelModel {
    private final HotelReservationSystem hotelReservationSystem; // The main hotel reservation system instance

    /**
     * Constructs a CreateHotelModel with the specified HotelReservationSystem.
     *
     * @param hotelReservationSystem The HotelReservationSystem instance for managing hotels
     */
    public CreateHotelModel(HotelReservationSystem hotelReservationSystem) {
        this.hotelReservationSystem = hotelReservationSystem;
    }

    /**
     * Checks if the provided hotel name is valid.
     * A valid hotel name must not be empty and must start with an alphabetic character.
     *
     * @param hotelName The name of the hotel to validate
     * @return true if the hotel name is valid; false otherwise
     */
    public boolean isHotelNameValid(String hotelName) {
        // Check if the hotel name is not empty and starts with an alphabetic character
        return !hotelName.isEmpty() && Character.isLetter(hotelName.charAt(0));
    }

    /**
     * Adds a new hotel to the hotel reservation system.
     *
     * @param hotelName The name of the hotel to be added
     * @return true if the hotel was added successfully; false if the hotel already exists
     */
    public boolean addHotel(String hotelName) {
        return hotelReservationSystem.addHotel(hotelName); // Delegate the addition to the HotelReservationSystem
    }

    /**
     * Retrieves the number of currently operating hotels.
     *
     * @return The count of operating hotels in the hotel reservation system
     */
    public int getNumberOfOperatingHotels() {
        return hotelReservationSystem.getHotelList().size(); // Return the size of the hotel list
    }
}
