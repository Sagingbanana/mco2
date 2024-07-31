import java.util.List;

public class SelectHotelModel {
    private final HotelReservationSystem hrs;

    public SelectHotelModel(HotelReservationSystem hrs) {
        this.hrs = hrs;
    }

    public HotelReservationSystem getHrs() {
        return hrs;
    }

    public List<Hotel> getHotels() {
        return hrs.getHotelList();
    }

    public int getNumberOfOperatingHotels() {
        return hrs.getHotelList().size();
    }

    public boolean hasHotels() {
        return !getHotels().isEmpty();
    }
}
