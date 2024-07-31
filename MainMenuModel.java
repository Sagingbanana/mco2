public class MainMenuModel {
    private final HotelReservationSystem hotelReservationSystem;

    public MainMenuModel(HotelReservationSystem hotelReservationSystem) {
        this.hotelReservationSystem = hotelReservationSystem;
    }

    public HotelReservationSystem getHotelReservationSystem() {
        return hotelReservationSystem;
    }

    /**
     * Returns the number of currently operating hotels.
     *
     * @return The count of currently operating hotels.
     */
    public int getNumberOfOperatingHotels() {
        return hotelReservationSystem.getHotelList().size();
    }

    /**
     * Checks if there are any hotels in the system.
     *
     * @return true if there are no hotels, false otherwise.
     */
    public boolean areThereNoHotels() {
        return hotelReservationSystem.getHotelList().isEmpty();
    }

    /**
     * Checks if there are any available rooms across all hotels.
     *
     * @return true if there are no available rooms, false otherwise.
     */
    public boolean areThereNoAvailableRooms() {
        for (Hotel hotel : hotelReservationSystem.getHotelList()) {
            if (hotel.getAvailableRoomsCount() > 0) {
                return false;
            }
        }
        return true;
    }

}