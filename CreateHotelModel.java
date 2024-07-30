public class CreateHotelModel {
    private final HotelReservationSystem hotelReservationSystem;

    public CreateHotelModel(HotelReservationSystem hotelReservationSystem) {
        this.hotelReservationSystem = hotelReservationSystem;
    }

    public boolean isHotelNameValid(String hotelName) {
        return hotelName.matches("^[A-Za-z][A-Za-z0-9]*$");
    }

    public boolean addHotel(String hotelName) {
        return hotelReservationSystem.addHotel(hotelName);
    }

    public int getNumberOfOperatingHotels() {
        return hotelReservationSystem.getHotelList().size();
    }
}
