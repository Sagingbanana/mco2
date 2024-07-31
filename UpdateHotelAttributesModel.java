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

    // Add methods for updating hotel attributes as needed
}
