import java.util.ArrayList;

/**
 * The UpdateHotelAttributesModel class handles the data and logic
 * related to updating hotel attributes, including managing reservations.
 * It interacts with the HotelReservationSystem and provides methods to
 * validate hotel names, cancel reservations, and retrieve reservations by ID.
 */
public class UpdateHotelAttributesModel {
    private final Hotel hotel; // The hotel being managed
    private final HotelReservationSystem hrs; // The system managing all hotels

    /**
     * Constructs an UpdateHotelAttributesModel with the specified hotel and
     * hotel reservation system.
     *
     * @param hotel The hotel to be managed.
     * @param hrs   The HotelReservationSystem instance managing hotels.
     */
    public UpdateHotelAttributesModel(Hotel hotel, HotelReservationSystem hrs) {
        this.hotel = hotel; // Initialize the hotel
        this.hrs = hrs; // Initialize the HotelReservationSystem
    }

    /**
     * Gets the hotel associated with this model.
     *
     * @return The hotel being managed.
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Gets the name of the selected hotel.
     *
     * @return The name of the hotel.
     */
    public String getSelectedHotelName() {
        return hotel.getName();
    }

    /**
     * Validates the new hotel name to ensure it is not empty and starts with a letter.
     *
     * @param newHotelName The new name of the hotel.
     * @return true if the hotel name is valid; false otherwise.
     */
    public boolean isHotelNameValid(String newHotelName) {
        return !newHotelName.isEmpty() && Character.isLetter(newHotelName.charAt(0)); // Check if not empty and starts with a letter
    }

    /**
     * Gets the HotelReservationSystem associated with this model.
     *
     * @return The HotelReservationSystem instance.
     */
    public HotelReservationSystem getHrs() {
        return hrs; // Expose HotelReservationSystem if needed
    }

    /**
     * Cancels an existing reservation.
     *
     * @param reservation The reservation to cancel.
     * @return true if the reservation was successfully cancelled; false otherwise.
     */
    public boolean cancelReservation(Reservation reservation) {
        ArrayList<Hotel> hotels = hrs.getHotelList(); // Get the list of hotels

        // Iterate through each hotel to find the reservation
        for (Hotel hotel : hotels) {
            // Check if the reservation exists in the hotel's reservation list
            if (hotel.getReservationsList().contains(reservation)) {
                return hotel.cancelReservation(reservation); // Call cancelReservation from the hotel
            }
        }
        return false; // Reservation not found in any hotel
    }

    /**
     * Retrieves a reservation by its ID.
     *
     * @param reservationId The ID of the reservation to retrieve.
     * @return The reservation if found; null otherwise.
     */
    public Reservation getReservationById(String reservationId) {
        ArrayList<Hotel> hotels = hrs.getHotelList(); // Get the list of hotels

        // Iterate through each hotel and their reservations
        for (Hotel hotel : hotels) {
            for (Reservation reservation : hotel.getReservationsList()) {
                // Check if the reservation ID matches
                if (reservation.getReservationID().equals(reservationId)) { // Assuming Reservation has a getReservationID() method
                    return reservation; // Return the found reservation
                }
            }
        }
        return null; // Reservation not found
    }
}
