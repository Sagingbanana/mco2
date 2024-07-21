/**
 * Represents a reservation for a room in a hotel.
 */
public class Reservation {
    private final String guestName;
    private final int checkInDate;
    private final int checkOutDate;
    private final Room room;
    private final double totalPrice;
    private final String reservationID;
    private String discountCode;

    /**
     * Constructs a new Reservation with the specified details.
     *
     * @param guestName The name of the guest.
     * @param checkInDate The check-in date.
     * @param checkOutDate The check-out date.
     * @param room The room being reserved.
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room, String discountCode){
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.totalPrice = calculateTotalPrice();  // Calculate the total price of the reservation
        this.reservationID = generateReservationID();  // Generate a unique reservation ID  
        this.discountCode = discountCode;
    }

    /**
     * Generates a unique reservation ID based on room name, guest initial, check-in, and check-out dates.
     *
     * @return The generated reservation ID.
     */
    private String generateReservationID() {
        String roomNumber = room.getName();
        char guestInitial = guestName.toUpperCase().charAt(0);

        // Create a reservation ID using room number, guest initial, and dates
        return roomNumber + guestInitial + String.format("%02d", checkInDate) + String.format("%02d", checkOutDate);
    }

    /**
     * Calculates the total price for the reservation based on the number of days and room base price.
     *
     * @return The total price for the reservation.
     */
    private double calculateTotalPrice() {
        int totalDays = checkOutDate - checkInDate;  // Calculate the number of days for the stay
        double price = 0;

       
        for (int date = checkInDate; date < checkOutDate; date++) {                     // Loop from check-in date to the day before check-out date
        price += room.getRoomPrice() * room.getHotel().getDatePriceModifier(date);      // Add the price for each day
    }
        if ("I_WORK_HERE".equals(discountCode)) {
            price *= 0.9; // 10% discount
        } else if ("STAY4_GET1".equals(discountCode) && totalDays >= 5) {
            price -= room.getRoomPrice(); // First day free
        } else if ("PAYDAY".equals(discountCode) && (checkInDate <= 15 && checkOutDate > 15 || checkInDate <= 30 && checkOutDate > 30)) {
            price *= 0.93; // 7% discount
        }


        return price;               // Return the final price
    }

    /**
    * Compares this reservation with another reservation for equality.
    *
    * @param other The reservation to compare with.
    * @return true if this reservation is equal to the other reservation, false otherwise.
    */
    public boolean isEqual(Reservation other) {
        if (this == other) return true; // return true if equal
        if (other == null) return false; // return false
        return this.checkInDate == other.checkInDate &&
                this.checkOutDate == other.checkOutDate &&
                this.totalPrice == other.totalPrice &&
                this.guestName.equals(other.guestName) &&
                this.room.getName().equals(other.room.getName()) &&
                this.reservationID.equals(other.reservationID); // return true if equal
    }

    /**
     * Gets the guest name for the reservation.
     *
     * @return The guest name.
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * Gets the check-in date for the reservation.
     *
     * @return The check-in date.
     */
    public int getCheckInDate(){
        return checkInDate;
    }

    /**
     * Gets the check-out date for the reservation.
     *
     * @return The check-out date.
     */
    public int getCheckOutDate(){
        return checkOutDate;
    }

    /**
     * Gets the room associated with the reservation.
     *
     * @return The room.
     */
    public Room getRoom(){
        return room;
    }

    /**
     * Gets the reservation ID.
     *
     * @return The reservation ID.
     */
    public String getReservationID(){
        return reservationID;
    }

    /**
     * Gets the total price for the reservation.
     *
     * @return The total price.
     */
    public double getTotalPrice(){
        return totalPrice;
    }
}
