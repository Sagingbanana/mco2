import java.util.List;

public class SelectHotelModel {
    private final HotelReservationSystem hotelReservationSystem;

    public SelectHotelModel(HotelReservationSystem hotelReservationSystem) {
        this.hotelReservationSystem = hotelReservationSystem;
    }

    public List<Hotel> getHotels() {
        return hotelReservationSystem.getHotelList();
    }

    public boolean hasHotels() {
        return !getHotels().isEmpty();
    }
}
