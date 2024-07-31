import java.util.ArrayList;

/**
 * Represents a room in a hotel.
 */
public class Room {
    private final String name;                          // The name of the room
    private final RoomType type;                        // The type of the room
    private double basePrice;                           // Base price of the room
    private String status;                               // Status of the room (e.g., available, booked)
    private final ArrayList<Reservation> reservationsList; // List of reservations for the room

    /**
     * Constructs a new Room with the specified name and type.
     *
     * @param name The name of the room.
     * @param type The type of the room.
     */
    public Room(String name, RoomType type) {
        this.name = name;
        this.basePrice = 1299.0; // Default base price for the room
        this.status = "Available for booking"; // Initial status of the room
        this.type = type;
        this.reservationsList = new ArrayList<>(); // Initialize the list of reservations
    }

    /**
     * Checks if the room is available for reservation between the specified dates.
     *
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return true if the room is available, false otherwise.
     */
    public boolean isAvailableToReserve(int checkInDate, int checkOutDate) {
        // Check if the reservation dates overlap with any existing reservation
        for (Reservation reservation : reservationsList) {
            if (checkInDate < reservation.getCheckOutDate() && checkOutDate > reservation.getCheckInDate()) {
                return false; // Room is not available due to overlapping reservation
            }
        }
        return true; // Room is available for the specified dates
    }

    /**
     * Updates the status of the room based on its reservation availability.
     */
    public void updateStatus() {
        boolean fullyBooked = true; // Assume room is fully booked unless found available
        for (int date = 1; date < 31; date++) { // Check for availability in a month
            // Check if the room is available for any day in the month
            if (isAvailableToReserve(date, date + 1)) {
                fullyBooked = false; // Found at least one available day
                break; // No need to check further
            }
        }
        // Update status based on availability
        if (fullyBooked) {
            this.status = "Fully booked"; // Update status if the room is fully booked
        }
    }

    /**
     * Sets the base price for the room.
     *
     * @param basePrice The base price to set.
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice; // Update the base price of the room
    }

    /**
     * Gets the name of the room.
     *
     * @return The name of the room.
     */
    public String getName() {
        return name; // Return the room name
    }

    /**
     * Gets the base price of the room.
     *
     * @return The base price of the room.
     */
    public double getBasePrice() {
        return basePrice; // Return the base price
    }

    /**
     * Gets the type of the room.
     *
     * @return The type of the room.
     */
    public RoomType getType() {
        return type; // Return the room type
    }

    /**
     * Gets the status of the room.
     *
     * @return The status of the room.
     */
    public String getStatus() {
        return status; // Return the status of the room
    }

    /**
     * Gets the list of reservations for the room.
     *
     * @return The list of reservations.
     */
    public ArrayList<Reservation> getReservationsList() {
        return reservationsList; // Return the list of reservations
    }

    /**
     * Enum representing the type of room.
     */
    public enum RoomType {
        STANDARD(1.0),
        DELUXE(1.2),
        EXECUTIVE(1.35);

        private final double priceMultiplier; // Multiplier for calculating the room price based on type

        RoomType(double priceMultiplier) {
            this.priceMultiplier = priceMultiplier; // Initialize price multiplier
        }

        /**
         * Gets the rate at which the base price is multiplied for the room type.
         *
         * @return The price multiplier.
         */
        public double getPriceMultiplier() {
            return priceMultiplier; // Return the price multiplier for the room type
        }
    }
}
