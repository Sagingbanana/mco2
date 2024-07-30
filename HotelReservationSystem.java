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

        if (!hotelName.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
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

        if (!newHotelName.matches(".*[a-zA-Z].*")) {
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
        return hotel.addRoom(nRoomsToCreate, type);
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
     * @param type         The type of rooms to update.
     * @return true if the base price was successfully updated, false otherwise.
     */
    public boolean updateHotelRoomBasePrice(Hotel hotel, double newBasePrice, Room.RoomType type) {
        return hotel.updateBasePrice(newBasePrice, type);
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
     * Displays the details of a specified hotel.
     *
     * @param hotel The hotel whose details are to be displayed.
     */
    public void viewHotel(Hotel hotel) {
        System.out.println("Name of hotel: " + hotel.getName());
        System.out.println("Base price of rooms: " + hotel.getRoomsList().get(0).getBasePrice());
        System.out.println("Total number of rooms: " + hotel.getRoomsList().size());
        System.out.println("Total number of reservations: " + hotel.getReservationsList().size());
        System.out.println("Total number of available rooms: " + hotel.getAvailableRoomsCount());
        System.out.println("Estimated earnings: " + (hotel.getRoomsList().get(0).getBasePrice() * 30 * hotel.getRoomsList().size()));            // changed from 31 to 30 since nights are being counted
        System.out.println("Actual earnings: " + hotel.getActualEarnings());
    }

    /**
     * Displays the rooms available for booking within a specified date range.
     *
     * @param hotel        The hotel whose rooms are to be checked.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     */
    public void viewRoomsByDate(Hotel hotel, int checkInDate, int checkOutDate) {
        int unavailableCount = 0;
        int availableCount = 0;

        System.out.println("\nThe rooms available on the specified check-in and check-out dates are: ");
        for (int i = 0; i < hotel.getRoomsList().size(); i++) {
            Room room = hotel.getRoomsList().get(i);
            if (room.isAvailableToReserve(checkInDate, checkOutDate)) {
                availableCount++;
                System.out.println("\t" + availableCount + ". Room " + room.getName());
            } else {
                unavailableCount++;
            }
        }

        if (availableCount == 0) {
            System.out.println("No rooms available for booking on the specified dates.\n");
        } else {
            System.out.println(" ");
        }

        if (unavailableCount == 0) {
            System.out.println("All rooms are available for booking.\n");
        } else {
            System.out.println("The rooms unavailable on the specified check-in and check-out dates are: ");
            int unavailableRoomIndex = 0;
            for (int i = 0; i < hotel.getRoomsList().size(); i++) {
                Room room = hotel.getRoomsList().get(i);
                if (!room.isAvailableToReserve(checkInDate, checkOutDate)) {
                    unavailableRoomIndex++;
                    System.out.println("\t" + unavailableRoomIndex + ". Room " + room.getName() + "\n");
                }
            }
        }
    }

    /**
     * Displays detailed information about a specific room.
     *
     * @param room The room whose details are to be displayed.
     */
    public void viewRoomInfo(Room room) {
        int counter = 0;
        System.out.println("Room number: " + room.getName());
        System.out.println("Price per night: " + room.getBasePrice());
        System.out.println("Status: " + room.getStatus() + "\n");

        if (!room.getReservationsList().isEmpty()) {
            System.out.println("The room is currently booked on the following dates: ");
            for (Reservation reservation : room.getReservationsList()) {
                counter++;
                System.out.println("\tReservation " + counter + ": " + reservation.getCheckInDate() + " - "
                        + reservation.getCheckOutDate());
            }
            System.out.println(" ");
        } else {
            System.out.println("The room currently has no reservations.\n");
        }
    }

    /**
     * Displays detailed information about a specific reservation.
     *
     * @param reservation The reservation whose details are to be displayed.
     */
    public void viewReservationInfo(Reservation reservation) {
        int totalDays = reservation.getCheckOutDate() - reservation.getCheckInDate();

        System.out.println("Room number: " + reservation.getRoom().getName());
        System.out.println("Guest name: " + reservation.getGuestName());
        System.out.println("Reservation ID: " + reservation.getReservationID());
        System.out.println("Check-in date: Day " + reservation.getCheckInDate());
        System.out.println("Check-out date: Day " + reservation.getCheckOutDate());
        System.out.println("Total price: " + reservation.getTotalPrice());
        System.out.println("Price Breakdown: (" + reservation.getRoom().getBasePrice() + " per night) * " + totalDays
                + " = " + reservation.getTotalPrice());
    }

    /**
     * Returns a list of rooms available for reservation within a specified date
     * range.
     *
     * @param hotel        The hotel whose rooms are to be checked.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return A list of available rooms.
     */
    public ArrayList<Room> availableRooms(Hotel hotel, int checkInDate, int checkOutDate) {
        ArrayList<Room> availableRooms = new ArrayList<>();

        for (Room room : hotel.getRoomsList()) {
            if (room.isAvailableToReserve(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    /**
     * Gets the list of hotels in the system.
     *
     * @return The list of hotels.
     */
    public ArrayList<Hotel> getHotelList() {
        return hotelList;
    }
}
