import java.util.ArrayList;

public class UpdateHotelAttributesModel {
    private final Hotel hotel;
    private final HotelReservationSystem hrs; // Add HotelReservationSystem

    public UpdateHotelAttributesModel(Hotel hotel, HotelReservationSystem hrs) {
        this.hotel = hotel;
        this.hrs = hrs; // Initialize the HotelReservationSystem
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String getSelectedHotelName() {
        return hotel.getName();
    }

    public boolean isHotelNameValid(String newHotelName) {
        return !newHotelName.isEmpty() && Character.isLetter(newHotelName.charAt(0));
    }

    public HotelReservationSystem getHrs() {
        return hrs; // Expose HotelReservationSystem if needed
    }

    /**
     * Cancels an existing reservation.
     *
     * @param reservation The reservation to cancel.
     * @return true if the reservation was successfully cancelled, false otherwise.
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

    // Assuming you have a method to retrieve a reservation by its ID
    public Reservation getReservationById(String reservationId) {
        ArrayList<Hotel> hotels = hrs.getHotelList();

        for (Hotel hotel : hotels) {
            for (Reservation reservation : hotel.getReservationsList()) {
                if (reservation.getReservationID().equals(reservationId)) { // Assuming Reservation has an getId() method
                    return reservation; // Return the found reservation
                }
            }
        }
        return null; // Reservation not found
    }
}
