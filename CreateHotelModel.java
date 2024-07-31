public class CreateHotelModel {
    private final HotelReservationSystem hotelReservationSystem;

    public CreateHotelModel(HotelReservationSystem hotelReservationSystem) {
        this.hotelReservationSystem = hotelReservationSystem;
    }

    public boolean isHotelNameValid(String hotelName) {
        // Check if the hotel name is not empty and starts with an alphabetic character
        return !hotelName.isEmpty() && Character.isLetter(hotelName.charAt(0));
    }


    public boolean addHotel(String hotelName) {
        return hotelReservationSystem.addHotel(hotelName);
    }

    public int getNumberOfOperatingHotels() {
        return hotelReservationSystem.getHotelList().size();
    }
}
