public class Main {
    public static void main(String[] args) {
        // Create an instance of HotelReservationSystem
        HotelReservationSystem hotelReservationSystem = new HotelReservationSystem();

        // Create an instance of MainMenuModel with the hotelReservationSystem
        MainMenuModel model = new MainMenuModel(hotelReservationSystem);

        // Create an instance of MainMenuView
        MainMenuView view = new MainMenuView();

        // Create an instance of MainMenuController with the view and model
        MainMenuController controller = new MainMenuController(view, model);

        // Make the view visible
        view.setVisible(true);
    }
}
