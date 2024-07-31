/**
 * The Main class serves as the entry point for the Hotel Reservation System application.
 * It initializes the necessary components and sets up the Model-View-Controller (MVC) architecture.
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     * It initializes the hotel reservation system, the model, the view, and the controller.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create an instance of HotelReservationSystem to manage hotel and reservation data
        HotelReservationSystem hotelReservationSystem = new HotelReservationSystem();

        // Create an instance of MainMenuModel with the hotelReservationSystem
        // This model interacts with the system data to provide information to the view
        MainMenuModel model = new MainMenuModel(hotelReservationSystem);

        // Create an instance of MainMenuView to present the user interface
        MainMenuView view = new MainMenuView();

        // Create an instance of MainMenuController with the view and model
        // This controller handles user interactions and updates the view based on model data
        MainMenuController controller = new MainMenuController(view, model);

        // Make the view visible to the user, initiating the application interface
        view.setVisible(true);
    }
}
