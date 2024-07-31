import java.util.List;

public class CreateReservationModel {
    private HotelReservationSystem hotelReservationSystem;

    public CreateReservationModel(HotelReservationSystem hotelReservationSystem) {
        this.hotelReservationSystem = hotelReservationSystem;
    }

    public List<Hotel> getAvailableHotels() {
        return hotelReservationSystem.getAvailableHotels();
    }

    public List<Room> getAvailableRooms(Hotel hotel, int checkInDate, int checkOutDate) {
        return hotelReservationSystem.getAvailableRooms(hotel, checkInDate, checkOutDate);
    }

    public boolean createReservation(String guestName, int checkInDate, int checkOutDate, Room room, String hotelName, String discountCode) {
        for (Hotel hotel : hotelReservationSystem.getHotelList()) {
            if (hotel.getName().equals(hotelName)) {
                return hotel.createReservation(guestName, checkInDate, checkOutDate, room, discountCode);
            }
        }
        return false;
    }
}
