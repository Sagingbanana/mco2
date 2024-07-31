import java.util.List;

/**
 * The CreateReservationModel class handles the data management for creating reservations.
 * It interacts with the HotelReservationSystem to retrieve available hotels and rooms
 * and to create reservations.
 */
public class CreateReservationModel {
    private final HotelReservationSystem hotelReservationSystem; // The hotel reservation system for managing hotels and reservations

    /**
     * Constructs a CreateReservationModel instance with the specified hotel reservation system.
     *
     * @param hotelReservationSystem The hotel reservation system to interact with
     */
    public CreateReservationModel(HotelReservationSystem hotelReservationSystem) {
        this.hotelReservationSystem = hotelReservationSystem;
    }

    /**
     * Retrieves the list of available hotels from the hotel reservation system.
     *
     * @return A list of available hotels
     */
    public List<Hotel> getAvailableHotels() {
        return hotelReservationSystem.getAvailableHotels(); // Call to the system to get available hotels
    }

    /**
     * Retrieves the list of available rooms for a specified hotel and date range.
     *
     * @param hotel The hotel for which to retrieve available rooms
     * @param checkInDate The check-in date for the reservation
     * @param checkOutDate The check-out date for the reservation
     * @return A list of available rooms for the specified hotel and date range
     */
    public List<Room> getAvailableRooms(Hotel hotel, int checkInDate, int checkOutDate) {
        return hotelReservationSystem.getAvailableRooms(hotel, checkInDate, checkOutDate); // Call to get available rooms
    }

    /**
     * Creates a reservation for a specified guest in a given hotel and room, applying a discount code if provided.
     *
     * @param guestName The name of the guest making the reservation
     * @param checkInDate The check-in date for the reservation
     * @param checkOutDate The check-out date for the reservation
     * @param room The room being reserved
     * @param hotelName The name of the hotel where the reservation is made
     * @param discountCode The discount code applied to the reservation, if any
     * @return True if the reservation was successfully created, false otherwise
     */
    public boolean createReservation(String guestName, int checkInDate, int checkOutDate, Room room, String hotelName, String discountCode) {
        // Loop through the list of hotels to find the matching hotel
        for (Hotel hotel : hotelReservationSystem.getHotelList()) {
            if (hotel.getName().equals(hotelName)) {
                // Call the hotel's method to create the reservation
                return hotel.createReservation(guestName, checkInDate, checkOutDate, room, discountCode);
            }
        }
        return false; // Return false if the hotel was not found
    }
}
