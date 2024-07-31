import java.util.ArrayList;

/**
 * Manages the hotel reservation system.
 */
public class HotelReservationSystem {
    private final ArrayList<Hotel> hotelList;

    /**
     * Constructs a new HotelReservationSystem with an empty list of hotels.
     */
    public HotelReservationSystem() {
        hotelList = new ArrayList<>();
    }

    /**
     * Adds a new hotel to the system if it does not already exist.
     *
     * @param hotelName The name of the hotel to be added.
     * @return true if the hotel is added, false otherwise.
     */
    public boolean addHotel(String hotelName) {
        // Check if the hotel already exists
        for (Hotel hotel : hotelList) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                return false;
            }
        }

        // Validate hotel name
        if (hotelName.isEmpty() || hotelName.isBlank() || hotelName.equals("\n")) {
            return false;
        }

        // Check if the hotel name starts with a number
        if (Character.isDigit(hotelName.trim().charAt(0))) {
            return false;
        }

        // Allow alphanumeric characters and dashes with whitespace
        if (!hotelName.matches("^[a-zA-Z0-9-\\s]+$")) {
            return false;
        }

        // Add new hotel
        Hotel newHotel = new Hotel(hotelName);
        hotelList.add(newHotel);
        return true;
    }

    /**
     * Updates the name of an existing hotel.
     *
     * @param newHotelName The new name for the hotel.
     * @param hotel        The hotel to be updated.
     * @return true if the hotel name is updated, false otherwise.
     */
    public boolean updateHotelName(String newHotelName, Hotel hotel) {
        // Check if the new name already exists
        for (Hotel hotel1 : hotelList) {
            if (hotel1.getName().equalsIgnoreCase(newHotelName)) {
                return false;
            }
        }

        // Validate new hotel name
        if (newHotelName.isEmpty() || newHotelName.isBlank() || newHotelName.equals("\n")) {
            return false;
        }

        // Check if the new hotel name starts with a number
        if (Character.isDigit(newHotelName.trim().charAt(0))) {
            return false;
        }

        // Allow alphanumeric characters and dashes with whitespace
        if (!newHotelName.matches("^[a-zA-Z0-9-\\s]+$")) {
            return false;
        }

        // Update hotel name
        hotelList.get(hotelList.indexOf(hotel)).setName(newHotelName);
        return true;
    }

    /**
     * Adds rooms to the specified hotel with the given room type.
     *
     * @param hotel          The hotel to add rooms to.
     * @param nRoomsToCreate The number of rooms to create.
     * @param type           The type of rooms to create.
     * @return true if rooms were successfully added, false otherwise.
     */
    public boolean addRoomsToHotel(Hotel hotel, int nRoomsToCreate, Room.RoomType type) {
        return hotel.addRooms(nRoomsToCreate, type);
    }

    /**
     * Sets the price modifier for a specific date in the specified hotel.
     *
     * @param hotel    The hotel to set the modifier for.
     * @param date     The date to set the modifier for.
     * @param modifier The price modifier (e.g., 0.9 for 90%, 1.1 for 110%).
     */
    public void setHotelDatePriceModifier(Hotel hotel, int date, double modifier) {
        hotel.setDatePriceModifier(date, modifier);
    }

    /**
     * Updates the base price for rooms of a specific type in the specified hotel.
     *
     * @param hotel        The hotel to update the base price for.
     * @param newBasePrice The new base price to set.
     *
     * @return true if the base price was successfully updated, false otherwise.
     */
    public boolean updateHotelRoomBasePrice(Hotel hotel, double newBasePrice) {
        return hotel.updateBasePrice(newBasePrice);
    }

    /**
     * Removes a hotel from the system if it has no active reservations.
     *
     * @param hotel The hotel to be removed.
     * @return true if the hotel is removed, false otherwise.
     */
    public boolean removeHotel(Hotel hotel) {
        // Check if the hotel has active reservations
        if (!hotelList.get(hotelList.indexOf(hotel)).getReservationsList().isEmpty()) {
            return false;
        }
        hotelList.remove(hotel);
        return true;
    }

    /**
     * Returns a list of rooms of a specific type in a hotel.
     *
     * @param hotel The hotel to get the rooms from.
     * @param type  The type of rooms to filter by.
     * @return A list of rooms of the specified type.
     */
    public ArrayList<Room> getRoomsByType(Hotel hotel, Room.RoomType type) {
        ArrayList<Room> roomsOfType = new ArrayList<>();
        for (Room room : hotel.getRoomsList()) {
            if (room.getType() == type) {
                roomsOfType.add(room);
            }
        }
        return roomsOfType;
    }

    /**
     * Returns a list of available rooms for reservation within a specified date range.
     *
     * @param hotel        The hotel to check for available rooms.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return A list of available rooms.
     */
    public ArrayList<Room> getAvailableRooms(Hotel hotel, int checkInDate, int checkOutDate) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRoomsList()) {
            if (room.isAvailableToReserve(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    /**
     * Returns a list of unavailable rooms for reservation within a specified date range.
     *
     * @param hotel        The hotel to check for unavailable rooms.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return A list of unavailable rooms.
     */
    public ArrayList<Room> getUnavailableRooms(Hotel hotel, int checkInDate, int checkOutDate) {
        ArrayList<Room> unavailableRooms = new ArrayList<>();
        for (Room room : hotel.getRoomsList()) {
            if (!room.isAvailableToReserve(checkInDate, checkOutDate)) {
                unavailableRooms.add(room);
            }
        }
        return unavailableRooms;
    }

    /**
     * Gets the list of hotels in the system.
     *
     * @return The list of hotels.
     */
    public ArrayList<Hotel> getHotelList() {
        return hotelList;
    }

    /**
     * Gets a list of available hotels in the system.
     * A hotel is considered available if it has at least one room that is not fully booked.
     *
     * @return A list of available hotels.
     */
    public ArrayList<Hotel> getAvailableHotels() {
        ArrayList<Hotel> availableHotels = new ArrayList<>();

        for (Hotel hotel : hotelList) {
            // Check if there is at least one room in the hotel that is not fully booked
            if (hotel.getAvailableRoomsCount() > 0) {
                availableHotels.add(hotel);
            }
        }

        return availableHotels;
    }
}
