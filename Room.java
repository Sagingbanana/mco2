import java.util.ArrayList;

/**
 * Represents a room in a hotel.
 */
public class Room {
    private final String name;
    private double basePrice;
    private String status;
    private final ArrayList<Reservation> reservationsList;
    private String type;

    /**
     * Constructs a new Room with the specified name.
     *
     * @param name The name of the room.
     */
    public Room(String name) {
        this.name = name;
        this.basePrice = 1299.0; // Default base price for the room
        this.status = "Available for booking"; // Initial status of the room
        this.reservationsList = new ArrayList<>(); // Initialize the list of reservations
        this.type = type;
    }

    /**
     * Checks if the room is available for reservation between the specified dates.
     *
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return true if the room is available, false otherwise.
     */
    public boolean isAvailableToReserve(int checkInDate, int checkOutDate) {
        for (Reservation reservation : reservationsList) {
            // Check if the reservation dates overlap with any existing reservation
            if (checkInDate < reservation.getCheckOutDate() && checkOutDate > reservation.getCheckInDate())
                return false;
        }
        return true;
    }

    /**
     * Updates the status of the room based on its reservation availability.
     */
    public void updateStatus() {
        boolean fullyBooked = true;
        for (int date = 1; date < 31; date++) {
            // Check if the room is available for any day in the month
            if (isAvailableToReserve(date, date + 1)) {
                fullyBooked = false;
                break;
            }
        }
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
        this.basePrice = basePrice;
    }

    /**
     * Gets the name of the room.
     *
     * @return The name of the room.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the base price of the room depending on the room type
     *
     * @return The base price of the room.
     */
    public double getPrice() {
        switch (type) {
            case "Deluxe":
                return basePrice * 1.20;
            case "Executive":
                return basePrice * 1.35;
            default:
                return basePrice;
        }
    }

    /**
     * Gets the status of the room.
     *
     * @return The status of the room.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the list of reservations for the room.
     *
     * @return The list of reservations.
     */
    public ArrayList<Reservation> getReservationsList() {
        return reservationsList;
    }

    public String getType() {
        return type;
    }
}
