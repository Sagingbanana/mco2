/**
 * Controller class responsible for handling user interactions in the Create Hotel view.
 * It communicates between the CreateHotelView and CreateHotelModel to manage hotel creation.
 */
public class CreateHotelController {
    private final CreateHotelView view; // The view associated with hotel creation
    private final CreateHotelModel model; // The model that manages hotel data
    private final MainMenuView mainMenuView; // The main menu view to navigate back to

    /**
     * Constructs a CreateHotelController with the specified view, model, and main menu view.
     *
     * @param view The CreateHotelView instance for the user interface
     * @param model The CreateHotelModel instance for hotel management logic
     * @param mainMenuView The MainMenuView instance for navigation
     */
    public CreateHotelController(CreateHotelView view, CreateHotelModel model, MainMenuView mainMenuView) {
        this.view = view;
        this.model = model;
        this.mainMenuView = mainMenuView;

        // Add action listener to the create hotel button to handle hotel creation
        this.view.getCreateHotelButton().addActionListener(e -> createHotel());

        // Add action listener to the back button to navigate back to the main menu
        this.view.getBackButton().addActionListener(e -> goBackToMainMenu());
    }

    /**
     * Creates a new hotel using the name provided in the hotel name field.
     * Validates the hotel name and updates the view with success or error messages.
     */
    private void createHotel() {
        String hotelName = view.getHotelNameField().getText().trim(); // Get the hotel name input

        // Check if the hotel name is valid
        if (!model.isHotelNameValid(hotelName)) {
            view.setMessage("<html>Hotel name cannot start with <br>a number or symbol. Try again.</html>");
            return; // Exit early if the hotel name is invalid
        }

        // Check if the hotel can be added and provide feedback
        if (model.addHotel(hotelName)) {
            view.setMessage("Hotel created successfully!"); // Success message
            view.getCreateHotelButton().setEnabled(false); // Disable the button to prevent duplicate submissions
        } else {
            view.setMessage("Hotel already exists. Try again."); // Error message if the hotel already exists
        }
    }

    /**
     * Navigates back to the main menu view.
     * Closes the current view and updates the hotel count displayed in the main menu.
     */
    private void goBackToMainMenu() {
        view.dispose(); // Close the current view
        mainMenuView.setVisible(true); // Show the main menu view
        mainMenuView.setHotelCount(model.getNumberOfOperatingHotels()); // Update the hotel count
    }
}
