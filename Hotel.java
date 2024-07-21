import java.util.ArrayList;
import java.util.List;

import Room.RoomType;

/**
 * Represents a Hotel with rooms and reservations.
 */
public class Hotel {
    private String name;
    private final ArrayList<Room> roomsList;
    private final ArrayList<Reservation> reservationsList;
    private ArrayList<Double> datePriceModifiers;

    /**
     * Constructs a new Hotel with the specified name.
     *
     * @param name The name of the hotel.
     */
    public Hotel(String name) {
        this.name = name;
        this.roomsList = new ArrayList<>();
        this.reservationsList = new ArrayList<>();
        this.datePriceModifiers = new ArrayList<>(31);
            for (int i = 0; i < 31; i++) {
                datePriceModifiers.add(1.0); // Initialize all days with default modifier of 100% of the base price (1.0) for all 31 days
            }
    }

    /**
     * Adds a specified number of rooms to the hotel.
     *
     * @param nRoomsToCreate The number of rooms to create.
     * @return true if rooms were successfully added, false otherwise.
     */
    public boolean addRoom(int nRoomsToCreate, RoomType roomType) {
        int existingRooms = roomsList.size();
        int maxRooms = 50;

        // Check if the number of rooms to create is valid
        if (nRoomsToCreate > 0 && nRoomsToCreate <= (maxRooms - existingRooms)) {
            for (int i = 1; i <= nRoomsToCreate; i++) {
                int roomNumber = existingRooms + i;
                int floorNumber = (roomNumber - 1) / 10 + 1;
                int roomOnFloor = roomNumber % 10 == 0 ? 10 : roomNumber % 10;
                int sum = floorNumber * 100 + roomOnFloor;

                String roomName = Integer.toString(sum);
                roomsList.add(new Room(roomName, roomType));
            }
            return true; // Rooms successfully added
        }

        return false; // Invalid number of rooms to create
    }

    /**
     * Removes a room from the hotel if it has no reservations.
     *
     * @param room The room to remove.
     * @return true if the room was successfully removed, false otherwise.
     */
    public boolean removeRoom(Room room) {
        // Check if the room has no reservations before removing it
        if (room.getReservationsList().isEmpty()) {
            roomsList.remove(room);
            return true;
        }

        return false;
    }

    /**
     * Updates the base price for all rooms in the hotel if there are no
     * reservations.
     *
     * @param newBasePrice The new base price to set.
     * @return true if the base price was successfully updated, false otherwise.
     */
    public boolean updateBasePrice(double newBasePrice) {
        // Check if there are no reservations and the new base price is valid
        if (!reservationsList.isEmpty() || newBasePrice < 100.0)
            return false;
        for (Room room : roomsList) {
            room.setBasePrice(newBasePrice);
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
            room.getReservationsList().add(new Reservation(guestName, checkInDate, checkOutDate, room));
            reservationsList.add(new Reservation(guestName, checkInDate, checkOutDate, room));
            room.updateStatus();
            return true;
        }
        return false;
    }

    /**
     * Cancels an existing reservation.
     *
     * @param reservation The reservation to cancel.
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
     * Calculates the total earnings from all reservations.
     *
     * This method iterates through the list of reservations and sums up the total
     * price of each reservation
     * to calculate the total earnings for the hotel. If there are no reservations,
     * it returns 0.0.
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

    public void setDatePriceModifier(int date, double modifier) {
        if (date >= 1 && date <= 31 && modifier >= 0.5 && modifier <= 1.5) {
            datePriceModifiers.set(date - 1, modifier);
        }
    }

    public double getDatePriceModifier(int date) {
        if (date >= 1 && date <= 31) {
            return datePriceModifiers.get(date - 1);
        }
        return 1.0;
    }

    public List<Room> getRoomsByType(RoomType type) {
    List<Room> filteredRooms = new ArrayList<>();   // Create a new list to store rooms that match the specified type
    for (Room room : roomsList) {       /// loop through each room in the list of rooms
        if (room.getRoomType() == type) {   // if it matches, add to filteredRooms list
            filteredRooms.add(room);
        }
    }
    return filteredRooms;   // Return the list of rooms that match the specified type
}
}
