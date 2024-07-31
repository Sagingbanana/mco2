import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The CreateReservationController class handles the interactions between the
 * CreateReservationView and CreateReservationModel. It manages the logic for
 * creating a reservation, populating available hotels and rooms, and validating user input.
 */
public class CreateReservationController {
    private final CreateReservationView view; // The view for creating a reservation
    private final CreateReservationModel model; // The model that manages reservation data
    private final MainMenuView mainMenuView; // The main menu view to navigate back to

    /**
     * Constructs a CreateReservationController instance and initializes the view and model.
     * It also sets up listeners for user interactions.
     *
     * @param view The view for creating reservations
     * @param model The model that manages reservation data
     * @param mainMenuView The main menu view for navigation
     */
    public CreateReservationController(CreateReservationView view, CreateReservationModel model, MainMenuView mainMenuView) {
        this.view = view;
        this.model = model;
        this.mainMenuView = mainMenuView;

        populateHotels(); // Populate the hotel dropdown
        setupListeners(); // Set up input validation listeners

        // Action listener for hotel selection to populate available rooms
        view.getHotelComboBox().addActionListener(e -> populateRooms());

        // Action listener for confirming reservation
        view.getConfirmButton().addActionListener(e -> createReservation());

        // Action listener for canceling the reservation and returning to the main menu
        view.getCancelButton().addActionListener(e -> {
            view.dispose();
            mainMenuView.setVisible(true);
        });
    }

    /**
     * Populates the hotel dropdown with available hotels.
     */
    private void populateHotels() {
        view.getHotelComboBox().addItem(null); // Add a null item for unselected state
        for (Hotel hotel : model.getAvailableHotels()) {
            view.getHotelComboBox().addItem(hotel.getName()); // Add hotel names to the dropdown
        }
    }

    /**
     * Populates the room dropdown based on the selected hotel and date inputs.
     */
    private void populateRooms() {
        view.getRoomComboBox().removeAllItems(); // Clear the previous room selections
        String hotelName = (String) view.getHotelComboBox().getSelectedItem();
        if (hotelName == null || hotelName.isEmpty()) {
            return; // Exit if no hotel is selected
        }
        int checkInDate = Integer.parseInt(view.getCheckInDateField().getText());
        int checkOutDate = Integer.parseInt(view.getCheckOutDateField().getText());
        for (Hotel hotel : model.getAvailableHotels()) {
            if (hotel.getName().equals(hotelName)) {
                // Populate available rooms for the selected hotel and date range
                for (Room room : model.getAvailableRooms(hotel, checkInDate, checkOutDate)) {
                    view.getRoomComboBox().addItem(room.getName());
                }
                break; // Exit the loop once the hotel is found
            }
        }
    }

    /**
     * Creates a reservation with the input data from the view.
     */
    private void createReservation() {
        String guestName = view.getGuestNameField().getText();
        int checkInDate;
        int checkOutDate;
        try {
            checkInDate = Integer.parseInt(view.getCheckInDateField().getText());
            checkOutDate = Integer.parseInt(view.getCheckOutDateField().getText());
        } catch (NumberFormatException e) {
            // Show error if dates are not valid numbers
            JOptionPane.showMessageDialog(view, "Check-in and check-out dates must be numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate check-in and check-out dates
        if (checkInDate < 1 || checkOutDate < 2 || checkOutDate > 31 || checkInDate >= checkOutDate) {
            JOptionPane.showMessageDialog(view, "Invalid check-in or check-out dates.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String hotelName = (String) view.getHotelComboBox().getSelectedItem();
        String roomName = (String) view.getRoomComboBox().getSelectedItem();
        String discountCode = view.getDiscountCodeField().getText().trim();

        // Validate guest name format
        if (!guestName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(view, "Guest name must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate hotel and room selection
        if (hotelName == null || roomName == null) {
            JOptionPane.showMessageDialog(view, "Hotel and Room must be selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the discount code is valid
        boolean isValidDiscountCode = discountCode.equals("I_WORK_HERE") || discountCode.equals("STAY4_GET1") || discountCode.equals("PAYDAY");

        // Check for the selected hotel and room to create a reservation
        for (Hotel hotel : model.getAvailableHotels()) {
            if (hotel.getName().equals(hotelName)) {
                for (Room room : model.getAvailableRooms(hotel, checkInDate, checkOutDate)) {
                    if (room.getName().equals(roomName)) {
                        boolean reservationCreated = model.createReservation(guestName, checkInDate, checkOutDate, room, hotelName, discountCode);
                        // Show success or failure messages based on reservation creation
                        if (reservationCreated) {
                            if (discountCode.isEmpty()) {
                                JOptionPane.showMessageDialog(view, "Reservation created successfully. No discount code applied.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else if (isValidDiscountCode) {
                                JOptionPane.showMessageDialog(view, "Reservation created successfully with discount.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(view, "Reservation created successfully. Invalid discount code.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            view.dispose(); // Close the view after successful reservation
                            mainMenuView.setVisible(true); // Show the main menu
                            return;
                        } else {
                            // Show error if reservation creation fails
                            JOptionPane.showMessageDialog(view, "Failed to create reservation due to invalid inputs.", "Error", JOptionPane.ERROR_MESSAGE);
                            view.dispose();
                            mainMenuView.setVisible(true);
                        }
                    }
                }
            }
        }
    }

    /**
     * Sets up listeners for input fields to enable/disable the confirm button based on validation.
     */
    private void setupListeners() {
        DocumentListener validationListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateInput(); // Validate input when text is inserted
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateInput(); // Validate input when text is removed
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateInput(); // Validate input when the document changes
            }
        };

        // Add document listeners to input fields for validation
        view.getGuestNameField().getDocument().addDocumentListener(validationListener);
        view.getCheckInDateField().getDocument().addDocumentListener(validationListener);
        view.getCheckOutDateField().getDocument().addDocumentListener(validationListener);
        view.getHotelComboBox().addActionListener(e -> validateInput()); // Validate when hotel is selected
        view.getRoomComboBox().addActionListener(e -> validateInput()); // Validate when room is selected
    }

    /**
     * Validates user input and enables/disables the confirm button accordingly.
     */
    private void validateInput() {
        String guestName = view.getGuestNameField().getText();
        boolean isGuestNameValid = guestName.matches("[a-zA-Z]+");
        boolean areDatesValid = !view.getCheckInDateField().getText().isEmpty() && !view.getCheckOutDateField().getText().isEmpty();
        boolean isHotelSelected = view.getHotelComboBox().getSelectedItem() != null;
        boolean isRoomSelected = view.getRoomComboBox().getSelectedItem() != null;

        // Determine if all input fields are valid
        boolean isInputValid = isGuestNameValid && areDatesValid && isHotelSelected && isRoomSelected;
        view.getConfirmButton().setEnabled(isInputValid); // Enable confirm button if input is valid
    }
}
