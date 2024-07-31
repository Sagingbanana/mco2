import java.util.ArrayList;

public class HotelInfoModel {
    private final Hotel hotel;

    public HotelInfoModel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public double getTotalIncome() {
        return hotel.getActualEarnings();
    }

    public ArrayList<Room> getAvailableRooms(int checkInDate, int checkOutDate) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : hotel.getRoomsList()) {
            if (room.isAvailableToReserve(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public ArrayList<Reservation> getReservations() {
        ArrayList<Reservation> allReservations = new ArrayList<>();
        for (Room room : hotel.getRoomsList()) {
            allReservations.addAll(room.getReservationsList());
        }
        return allReservations;
    }
}
