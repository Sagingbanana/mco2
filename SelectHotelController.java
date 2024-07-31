/**
 * Controller for selecting a hotel from a list and performing operations on it.
 */
public class SelectHotelController {
    private final SelectHotelView view;            // The view associated with this controller
    private final SelectHotelModel model;          // The model holding hotel data
    private final MainMenuView mainMenuView;       // Reference to the main menu view

    /**
     * Constructs a new SelectHotelController with the specified view and model.
     *
     * @param view The view associated with this controller.
     * @param model The model that contains hotel data.
     * @param mainMenuView The main menu view for navigation.
     */
    public SelectHotelController(SelectHotelView view, SelectHotelModel model, MainMenuView mainMenuView) {
        this.view = view;
        this.model = model;
        this.mainMenuView = mainMenuView;

        // Initialize the view with the list of hotels
        populateHotelList();

        // Add action listeners to buttons for user interactions
        this.view.getProceedButton().addActionListener(e -> proceed());
        this.view.getBackButton().addActionListener(e -> goBackToMainMenu());
    }

    /**
     * Populates the hotel combo box in the view with the available hotels from the model.
     */
    private void populateHotelList() {
        view.getHotelComboBox().removeAllItems(); // Clear existing items in the combo box
        if (model.hasHotels()) {
            // Add hotels to the combo box
            for (Hotel hotel : model.getHotels()) {
                view.getHotelComboBox().addItem(hotel.getName());
            }
        } else {
            // No hotels available, inform the user and disable proceed button
            view.setMessage("No hotels available.");
            view.getProceedButton().setEnabled(false);
        }
    }

    /**
     * Refreshes the hotel list in the view to reflect any changes in the model.
     */
    public void refreshHotelList() {
        populateHotelList(); // Repopulate the hotel list
    }

    /**
     * Handles the proceed action when a hotel is selected.
     * Opens the appropriate view based on the selected operation.
     */
    private void proceed() {
        String selectedHotelName = (String) view.getHotelComboBox().getSelectedItem(); // Get the selected hotel name
        if (selectedHotelName == null) {
            view.setMessage("Please select a hotel."); // Inform user to select a hotel
            return;
        }

        // Check if a valid operation is selected
        if (!view.getUpdateAttributesOption().isSelected() && !view.getViewInformationOption().isSelected()) {
            view.setMessage("Please select a hotel operation."); // Prompt user for operation selection
            return;
        }

        // Perform action based on the selected operation
        Hotel selectedHotel = model.getHotels().stream()
                .filter(hotel -> hotel.getName().equals(selectedHotelName))
                .findFirst()
                .orElse(null);

        if (selectedHotel != null) {
            if (view.getViewInformationOption().isSelected()) {
                // Open the HotelInfoView with HotelInfoController
                HotelInfoView hotelInfoView = new HotelInfoView(selectedHotel.getName());
                HotelInfoModel hotelInfoModel = new HotelInfoModel(selectedHotel);
                new HotelInfoController(hotelInfoView, hotelInfoModel, this, view);
                hotelInfoView.setVisible(true); // Show hotel information view
                this.view.setVisible(false); // Hide the current view
            } else if (view.getUpdateAttributesOption().isSelected()) {
                // Open the UpdateHotelAttributesView with UpdateHotelAttributesController
                UpdateHotelAttributesView updateView = new UpdateHotelAttributesView(selectedHotel.getName());
                UpdateHotelAttributesModel updateModel = new UpdateHotelAttributesModel(selectedHotel, model.getHrs());
                new UpdateHotelAttributesController(updateView, updateModel, this, view);
                updateView.setVisible(true); // Show update hotel attributes view
                this.view.setVisible(false); // Hide the current view
            }
        }
    }

    /**
     * Navigates back to the main menu and updates the hotel count display.
     */
    public void goBackToMainMenu() {
        view.dispose(); // Close the current view
        mainMenuView.setVisible(true); // Show the main menu
        mainMenuView.setHotelCount(model.getNumberOfOperatingHotels()); // Update hotel count display
    }
}
