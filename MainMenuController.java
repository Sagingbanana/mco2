import javax.swing.*;

/**
 * The MainMenuController class manages the interactions between the main menu view
 * and the underlying model for the hotel reservation system. It handles button
 * actions and updates the view based on the model's state.
 */
public class MainMenuController {
    private final MainMenuView view;  // The view component of the main menu
    private final MainMenuModel model; // The model component containing hotel data

    /**
     * Constructs a MainMenuController with the specified view and model.
     *
     * @param view  The view component for the main menu.
     * @param model The model component containing hotel data.
     */
    public MainMenuController(MainMenuView view, MainMenuModel model) {
        this.view = view;
        this.model = model;

        // Initialize the view with the number of hotels
        updateHotelCount();

        // Add action listeners to the buttons in the view
        this.view.getManageHotelsButton().addActionListener(e -> manageHotels());
        this.view.getCreateReservationButton().addActionListener(e -> createReservation());
        this.view.getCreateHotelButton().addActionListener(e -> createHotel(model.getHotelReservationSystem()));
    }

    /**
     * Manages the selection of hotels. If there are no hotels available, an error dialog is shown.
     * If hotels are available, it opens the hotel selection view.
     */
    private void manageHotels() {
        if (model.areThereNoHotels()) {
            // Show an error dialog if no hotels are available to manage
            JOptionPane.showMessageDialog(view, "No hotels available to manage.",
                    "Manage Hotels Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Create and display the hotel selection view
            SelectHotelModel selectHotelModel = new SelectHotelModel(model.getHotelReservationSystem());
            SelectHotelView selectHotelView = new SelectHotelView();
            SelectHotelController selectHotelController = new SelectHotelController(selectHotelView, selectHotelModel, view);
            selectHotelView.setVisible(true); // Show the hotel selection view
            view.setVisible(false); // Hide the main menu view
        }
    }

    /**
     * Creates a reservation. If there are no hotels or available rooms, an error dialog is shown.
     * Otherwise, it opens the reservation creation view.
     */
    private void createReservation() {
        if (model.areThereNoHotels()) {
            // Show an error dialog if no hotels are available for reservation
            JOptionPane.showMessageDialog(view, "No hotels available.",
                    "Reservation Error", JOptionPane.ERROR_MESSAGE);
        } else if (model.areThereNoAvailableRooms()) {
            // Show an error dialog if no rooms are available for reservation
            JOptionPane.showMessageDialog(view, "No rooms available.",
                    "Reservation Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Create and display the reservation creation view
            CreateReservationModel createReservationModel = new CreateReservationModel(model.getHotelReservationSystem());
            CreateReservationView createReservationView = new CreateReservationView();
            CreateReservationController createReservationController = new CreateReservationController(createReservationView, createReservationModel, view);
            createReservationView.setVisible(true); // Show the reservation creation view
            view.setVisible(false); // Hide the main menu view
        }
    }

    /**
     * Creates a new hotel. Opens the hotel creation view for the user.
     *
     * @param hrs The hotel reservation system to which the new hotel will be added.
     */
    private void createHotel(HotelReservationSystem hrs) {
        // Create and display the hotel creation view
        CreateHotelModel createHotelModel = new CreateHotelModel(hrs);
        CreateHotelView createHotelView = new CreateHotelView();
        CreateHotelController createHotelController = new CreateHotelController(createHotelView, createHotelModel, view);
        createHotelView.setVisible(true); // Show the hotel creation view
        view.setVisible(false); // Hide the main menu view
    }

    /**
     * Updates the hotel count displayed in the main menu view.
     */
    private void updateHotelCount() {
        view.setHotelCount(model.getNumberOfOperatingHotels());
    }
}
