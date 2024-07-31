import java.util.*;

/**
 * Represents a Hotel with rooms and reservations.
 */
public class Hotel {
    private String name;
    private final ArrayList<Room> roomsList;
    private final ArrayList<Reservation> reservationsList;
    private final Map<Integer, Double> datePriceModifier;

    /**
     * Constructs a new Hotel with the specified name.
     *
     * @param name The name of the hotel.
     */
    public Hotel(String name) {
        this.name = name;
        this.roomsList = new ArrayList<>();
        this.reservationsList = new ArrayList<>();
        this.datePriceModifier = new HashMap<>();
        initializeDPM();
    }

    /**
     * Initializes the date price modifiers to 100% for all dates.
     */
    private void initializeDPM() {
        for (int date = 1; date <= 30; date++) {
            datePriceModifier.put(date, 1.0); // Default modifier is 100% (1.0)
        }
    }

    /**
     * Sets the price modifier for a specific date.
     *
     * @param date     The date to set the modifier for.
     * @param modifier The price modifier (e.g., 0.9 for 90%, 1.1 for 110%).
     */
    public void setDatePriceModifier(int date, double modifier) {
        if (date >= 1 && date <= 30 && modifier >= 0.5 && modifier <= 1.5) {
            datePriceModifier.put(date, modifier);
        }
    }

    /**
     * Gets the price modifier for a specific date.
     *
     * @param date The date to get the modifier for.
     *
     * @return The price modifier for the specified date.
     */
    public double getDatePriceModifier(int date) {
        return datePriceModifier.getOrDefault(date, 1.0);
    }

    /**
     * Calculates the price for a room on a specific date based on the base price and the date price modifier.
     *
     * @param room The room to calculate the price for.
     * @param date The date to calculate the price for.
     *
     * @return The price for the specified date.
     */
    public double calculatePriceForRoomOnDate(Room room, int date) {
        return room.getBasePrice() * getDatePriceModifier(date) * room.getType().getPriceMultiplier();
    }

    /**
     * Adds a specified number of rooms to the hotel with the given room type.
     *
     * @param nRoomsToCreate The number of rooms to create.
     * @param type           The type of rooms to create.
     *
     * @return true if rooms were successfully added, false otherwise.
     */
    public boolean addRooms(int nRoomsToCreate, Room.RoomType type) {
        int existingRooms = roomsList.size();
        int maxRooms = 50;

        // Check if the number of rooms to create is valid
        if (nRoomsToCreate > 0 && nRoomsToCreate <= (maxRooms - existingRooms)) {
            for (int i = 1; i <= nRoomsToCreate; i++) {
                int roomNumber = existingRooms + i;
                int floorNumber = (roomNumber - 1) / 10 + 1;
                int roomOnFloor = roomNumber % 10 == 0 ? 10 : roomNumber % 10;
                int sum = floorNumber * 100 + roomOnFloor;

                // Generate room name with the first letter of the room type
                String roomTypeInitial = type.name().charAt(0) + ""; // Get first letter of the room type
                String roomName = sum + roomTypeInitial; // Append the room type initial
                roomsList.add(new Room(roomName, type));
            }
            return true; // Rooms successfully added
        }

        return false; // Invalid number of rooms to create
    }


    /**
     * Removes multiple rooms from the hotel if they have no reservations.
     *
     * @param rooms The list of rooms to remove.
     *
     * @return A list of rooms that were successfully removed.
     */
    public List<Room> removeRooms(List<Room> rooms) {
        List<Room> removedRooms = new ArrayList<>();

        for (Room room : rooms) {
            // Check if the room has no reservations before removing it
            if (room.getReservationsList().isEmpty()) {
                roomsList.remove(room);
                removedRooms.add(room); // Add to the list of successfully removed rooms
            }
        }

        return removedRooms; // Return the list of removed rooms
    }


    /**
     * Updates the base price for all rooms in the hotel if there are no reservations.
     *
     * @param newBasePrice The new base price to set.
     *
     * @return true if the base price was successfully updated, false otherwise.
     */
    public boolean updateBasePrice(double newBasePrice) {
        // Check if there are no reservations and the new base price is valid
        if (!reservationsList.isEmpty() || newBasePrice < 100.0) return false;
        for (Room room : roomsList) {
            room.setBasePrice(newBasePrice); // Update base price for all rooms
        }
        return true;
    }


    /**
     * Creates a reservation for a guest in the specified room.
     *
     * @param guestName    The name of the guest.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @param room         The room to reserve.
     *
     * @return true if the reservation was successfully created, false otherwise.
     */
    public boolean createReservation(String guestName, int checkInDate, int checkOutDate, Room room) {
        // Validate the check-in and check-out dates
        if ((checkInDate < 1 || checkInDate > 30 || checkOutDate < 2 || checkOutDate > 31)
                && checkInDate >= checkOutDate)
            return false;

        // Check if the room is available for the specified dates
        if (room.isAvailableToReserve(checkInDate, checkOutDate)) {
            // Add the reservation to the room and hotel reservation lists
            Reservation reservation = new Reservation(guestName, checkInDate, checkOutDate, room, this);
            room.getReservationsList().add(reservation);
            reservationsList.add(reservation);
            room.updateStatus();
            return true;
        }
        return false;
    }

    /**
     * Creates a reservation for a guest in the specified room with a discount code.
     *
     * @param guestName    The name of the guest.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @param room         The room to reserve.
     * @param discountCode The discount code to apply.
     *
     * @return true if the reservation was successfully created, false otherwise.
     */
    public boolean createReservation(String guestName, int checkInDate, int checkOutDate, Room room, String discountCode) {
        // Validate the check-in and check-out dates
        if ((checkInDate < 1 || checkInDate > 30 || checkOutDate < 2 || checkOutDate > 31)
                && checkInDate >= checkOutDate)
            return false;

        // Check if the room is available for the specified dates
        if (room.isAvailableToReserve(checkInDate, checkOutDate)) {
            // Add the reservation to the room and hotel reservation lists
            Reservation reservation = new Reservation(guestName, checkInDate, checkOutDate, room, this, discountCode);
            room.getReservationsList().add(reservation);
            reservationsList.add(reservation);
            room.updateStatus();
            return true;
        }
        return false;
    }

    /**
     * Cancels an existing reservation.
     *
     * @param reservation The reservation to cancel.
     *
     * @return true if the reservation was successfully cancelled, false otherwise.
     */
    public boolean cancelReservation(Reservation reservation) {
        Room room = reservation.getRoom();

        int roomIndex = roomsList.indexOf(room);
        // Find the actual reservation object in the room's reservation list
        Reservation reservationToRemove = null;

        for (Reservation r : roomsList.get(roomIndex).getReservationsList()) {
            if (r.isEqual(reservation)) { // Call isEqual to compare attributes of reservations
                reservationToRemove = r;
                break;
            }
        }

        // Check if the reservation exists in both the hotel's and room's reservation
        // lists
        if (reservationToRemove != null && reservationsList.contains(reservation)) {
            // Remove the reservation from both the room and hotel reservation lists
            roomsList.get(roomIndex).getReservationsList().remove(reservationToRemove);
            reservationsList.remove(reservation);
            room.updateStatus();
            return true;
        }

        return false;
    }

    /**
     * Calculates the total earnings from all reservations. </p>
     *
     * This method iterates through the list of reservations and sums up the total
     * price of each reservation to calculate the total earnings for the hotel.
     * If there are no reservations, it returns 0.0.
     *
     * @return The total earnings from all reservations.
     */
    public double getActualEarnings() {
        double totalEarnings = 0.0;

        if (reservationsList.isEmpty()) {
            return totalEarnings;
        }

        for (Reservation reservation : reservationsList) {
            totalEarnings += reservation.getTotalPrice();
        }
        return totalEarnings;
    }

    /**
     * Sets the name of the hotel.
     *
     * @param name The new name of the hotel.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Calculates the number of available rooms in the hotel.
     * Rooms with status "Fully booked" are considered unavailable.
     *
     * @return The count of available rooms.
     */
    public long getAvailableRoomsCount() {
        // Using stream to filter rooms that are not fully booked and counting them
        return roomsList.stream()
                .filter(room -> !room.getStatus().equals("Fully booked"))
                .count();
    }

    /**
     * Gets the name of the hotel.
     *
     * @return The name of the hotel.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets base price of rooms.
     *
     * @return Base price of room.
     */
    public double getBasePrice(){
        return roomsList.get(0).getBasePrice();
    }

    /**
     * Gets the list of rooms in the hotel.
     *
     * @return The list of rooms.
     */
    public ArrayList<Room> getRoomsList() {
        return roomsList;
    }

    /**
     * Gets the list of reservations in the hotel.
     *
     * @return The list of reservations.
     */
    public ArrayList<Reservation> getReservationsList() {
        return reservationsList;
    }
}
