import java.util.ArrayList;

/**
 * The HotelInfoModel class represents the data model for hotel information,
 * including details about the hotel, available rooms, and reservations.
 */
public class HotelInfoModel {
    private final Hotel hotel; // The hotel associated with this model

    /**
     * Constructs a HotelInfoModel with the specified hotel.
     *
     * @param hotel The hotel to associate with this model.
     */
    public HotelInfoModel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Returns the hotel associated with this model.
     *
     * @return The hotel instance.
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Calculates the total income earned by the hotel.
     *
     * @return The total income as a double.
     */
    public double getTotalIncome() {
        return hotel.getActualEarnings();
    }

    /**
     * Retrieves a list of available rooms for the specified check-in and check-out dates.
     *
     * @param checkInDate  The date of check-in.
     * @param checkOutDate The date of check-out.
     * @return A list of available rooms for the specified dates.
     */
    public ArrayList<Room> getAvailableRooms(int checkInDate, int checkOutDate) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRoomsList()) {
            if (room.isAvailableToReserve(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    /**
     * Retrieves a list of all reservations made in the hotel.
     *
     * @return A list of all reservations associated with the hotel.
     */
    public ArrayList<Reservation> getReservations() {
        ArrayList<Reservation> allReservations = new ArrayList<>();
        for (Room room : hotel.getRoomsList()) {
            allReservations.addAll(room.getReservationsList());
        }
        return allReservations;
    }
}
